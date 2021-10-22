package _00_init.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.hibernate.SessionFactory;

@WebListener
public class CreateSessionFactoryListener implements ServletContextListener {
	SessionFactory factory;
	
//    public CreateSessionFactoryListener() {
//    	
//    }

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		factory = HibernateUtils.getSessionFactory();

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		factory.close();
	}

}
