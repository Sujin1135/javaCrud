package spms.context;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.reflections.Reflections;

import spms.annotation.Component;

public class ApplicationContext {
    Hashtable<String, Object> objTable = new Hashtable<String, Object>();

    public Object getBean(String key) { // Hashtable 에서 key값에 맞는 value 값을 출력하도록 하는 메서드.
        return objTable.get(key);
    }

    public ApplicationContext(String propertiesPath) throws Exception { // 프로퍼티 정보들을 담아놓은 파일 경로를 매개변수로 받는다.
        Properties props = new Properties();
        props.load(new FileReader(propertiesPath)); // load 메서드는 FileReader를 통해 읽어들인 프로퍼티 내용을 키 = 값 형태로 내부 맵에 저장한다.

        prepareObjects(props); // props에 있는 키와 값을 분류하여 Hashtable에 담는다.
        prepareAnnotationObjects();
        injectDependency();
    }

    /**
     * 해당 클래스에서만 쓸 메서드들은 접근 제어자를 private로 설정한다.
     */

    private void prepareAnnotationObjects() {
        try {
            Reflections reflector = new Reflections("spms"); // spms 패키지를 검색 항목에 넣는다.
            Set<Class<?>> list = reflector.getTypesAnnotatedWith(Component.class); // spms 패키지 내부에서 Component 어노테이션을 사용한 클래스를 Set 인스턴스에 담는다.
            String key = null;

            for (Class<?> clazz : list) { // list에서 객체를 하나씩 꺼내어 objTable에 담는다.
                key = clazz.getAnnotation(Component.class).value();
                objTable.put(key, clazz.newInstance());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prepareObjects (Properties props) throws Exception { // Object를 생성하는 메서드
        Context ctx = new InitialContext(); // 톰캣 서버의 context.xml 문서의 내용을 읽어올 때 InitialContext가 필요하다.
        String key = null;
        String value = null;

        for (Object item : props.keySet()) { // props의 key를 하나씩 꺼내어 반복문을 돌린다.
            key = (String)item; // key에 props의 key를 대입한다.
            value = props.getProperty(key); // 해당 key값과 쌍인 value값을 가져온다.
            if (key.startsWith("jndi.")) { // key가 jndi로 시작할 경우
                objTable.put(key, ctx.lookup(value)); // Hashtable에 key값과 jndi에 해당하는 루트 값일 입력한다.
            } else {
                objTable.put(key, Class.forName(value).newInstance());
            }
        }
    }

    private void injectDependency() throws Exception { // Hashtable 에 있는 객체 중 jndi 객체가 아닌 것들을 callSetter에 매개변수 값을 넣고 실행시킨다.
        for (String key : objTable.keySet()) {
            if (!key.startsWith("jndi.")) {
                callSetter (objTable.get(key));
            }
        }
    }

    private void callSetter (Object obj) throws Exception { // 매개변수를 컨트롤러 객체를 Object형으로 받았다.
        Object dependency = null;
        for (Method m : obj.getClass().getMethods()) { // 해당 컨트롤러의 메서드를 하나씩 대조하는 for 문
            if(m.getName().startsWith("set")) { // 해당 메서드가 set으로 시작하면 if문을 실행한다.
                dependency = findObjectByType(m.getParameterTypes()[0]); // 메서드의 매개변수 타입을 Hashtable의 value와 대조시킨다.
                if (dependency != null) m.invoke(obj, dependency);
            }
        }
    }

    private Object findObjectByType (Class<?> type) { // 메서드의 매개변수 타입을 매개변수로 받아온다.
        for (Object obj : objTable.values()) { // Hashtable의 모든 값을 하나씩 꺼내와서 메서드의 매개변수 타입과 상속관계인지 대조시킨다.
            if (type.isInstance(obj)) return obj; // type의 타입과 obj의 타입이 같을 경우 obj를 반환한다.
        }
        return null; // type의 타입과 상속관계인 Hashtable의 value가 없을 경우 null을 리턴한다.
    }
}
