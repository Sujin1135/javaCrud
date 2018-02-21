package spms.bind;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Set;

import javax.servlet.ServletRequest;

public class ServletRequestDataBinder {
    public static Object bind(
            ServletRequest request, Class<?> dataType, String dataName
    ) throws Exception {
        if (isPrimitiveType(dataType)) { // 데이터의 타입이 기본형 타입일 경우 기본형 타입의 인스턴스를 생성 후 반환한다.
            return createValueObject(dataType, request.getParameter(dataName));
        }

        Set<String> paramNames = request.getParameterMap().keySet(); // request의 매개변수 이름을 저장.
        Object dataObject = dataType.newInstance(); // 데이터 타입의 인스턴스를 저장한다.
        Method m = null; // 데이터의 메서드를 알기 위해 생성하는 클래스.

        for (String paramName : paramNames) {
            m = findSetter(dataType, paramName);
            if (m != null) {
                m.invoke(dataObject, createValueObject(m.getParameterTypes()[0],
                        request.getParameter(paramName)));
            }
        }

        return dataObject;
    }

    private static boolean isPrimitiveType(Class<?> type) { // type이 기본형이라면 기본형 타입의 데이터를 반환한다.
        if (type.getName().equals("int") || type == Integer.class ||
                type.getName().equals("long") || type == Long.class ||
                type.getName().equals("float") || type == Float.class ||
                type.getName().equals("double") || type == Double.class ||
                type.getName().equals("boolean") || type == Boolean.class ||
                type == Date.class || type == String.class) return true;
        return false;
    }

    private static Object createValueObject(Class<?> type, String value) {
        if (type.getName().equals("int") || type == Integer.class) {
            return new Integer(value);
        } else if (type.getName().equals("float") || type == Float.class) {
            return new Float(value);
        } else if (type.getName().equals("double") || type == Double.class) {
            return new Double(value);
        } else if (type.getName().equals("long") || type == Long.class) {
            return new Long(value);
        } else if (type.getName().equals("boolean") || type == Boolean.class) {
            return new Boolean(value);
        } else if (type == Date.class) {
            return java.sql.Date.valueOf(value);
        } else {
            return value;
        }
    }

    private static Method findSetter (Class<?> type, String name) {
        // 컨트롤러에서 Object배열에 저장한 데이터 타입과 request의 매개변수로 넘어온 key를 비교한다.
        Method[] methods = type.getMethods(); // type의 메서드들을 저장한다.

        String propName = null;
        for (Method m : methods) {
            if(!m.getName().startsWith("set")) continue; // setter 메서드가 아닐 경우 넘긴다.
            propName = m.getName().substring(3); // setter 메서드일 경우 set 이후의 메서드 이름을 가져온다.
            if(propName.toLowerCase().equals(name.toLowerCase())) { // propName과 name이 대소문자 구분 없이 일치할 경우 Method를 반납한다.
                return m;
            }
        }

        return null;
    }
}
