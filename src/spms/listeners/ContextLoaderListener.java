package spms.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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
			applicationContext = new ApplicationContext();

			String resource = "spms/dao/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			applicationContext.addBean("sqlSessionFactory", sqlSessionFactory);

			ServletContext sc = event.getServletContext();
			String propertiesPath = sc.getRealPath(sc.getInitParameter("contextConfigLocation"));

			applicationContext.prepareObjectsByProperties(propertiesPath);
			applicationContext.prepareObjectsByAnnotation("");
			applicationContext.injectDependency();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}