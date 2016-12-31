package music.service.orm.utils;

import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;

public class HStorage {

	@Autowired
	SessionFactory sessionFactory;

	public static final HStorage INSTANCE = new HStorage();
	
	public static <T> T unproxy(final T entity) {

		if (entity instanceof HibernateProxy) {
            SessionImplementor initializerSession = 
                    ((HibernateProxy)entity).getHibernateLazyInitializer().getSession(); 

            if (initializerSession == null || initializerSession.isClosed()){
                SessionImplementor currentSession = (SessionImplementor) INSTANCE.sessionFactory.getCurrentSession();
                ((HibernateProxy)entity).getHibernateLazyInitializer().setSession(currentSession);
            }

            if (!org.hibernate.Hibernate.isInitialized(entity)) 
            	org.hibernate.Hibernate.initialize(entity);
            @SuppressWarnings("unchecked")
            final T entity1 = (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
            return entity1;
		} else
			return entity;
	}
}
