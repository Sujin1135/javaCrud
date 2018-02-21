package spms.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import spms.context.ApplicationContext;

public class ContextLoaderListener implements ServletContextListener {
	static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext () {
		return applicationContext;
	}

	/*
	 * 공용 자원을 해제하는 코드를 작성하기 좋은 최적의 장소이다.
	 */

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		try {
			ServletContext sc = event.getServletContext(); // 서블릿 컨택스트 생성
			String propertiesPath = sc.getRealPath(sc.getInitParameter("contextConfigLocation")); // /WEB-INF/web.xml
			applicationContext = new ApplicationContext(propertiesPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}