package music.service.orm.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import music.service.orm.entites.Message;
import music.service.orm.entites.Music;
import music.service.orm.entites.User;

@Repository
public class DaoCRUDImpl implements DaoCRUD {

	@Autowired
    private SessionFactory sessionFactory;
	private Session session;
	
	@PostConstruct
	private void init() {     
		try {         
			session = sessionFactory.getCurrentSession();  
		} catch (org.hibernate.HibernateException he) {  
			session = sessionFactory.openSession();     
		}             
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public <T> void create(T entity) {
		session.save(entity);
		session.flush();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public <T> void update(T entity) {
		session.refresh(entity);
		session.flush();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public <T> void delete(T entity) {
		session.delete(entity);
		session.flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public <T> T find(String id, Class<T> cls) {
		return (T) session.get(cls, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public <T> List<T> findByUserId(String id) {
		Query query = session.createQuery("SELECT e FROM "
				+ Music.class.getCanonicalName() + " e WHERE e.user IN (SELECT t FROM " + User.class.getCanonicalName()
				 + " t WHERE e.userId = t.userId AND t.userId = '" + id + "')");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public <T> T findByUserName(String username) {
		Query query = session.createQuery("SELECT e FROM "
				+ User.class.getCanonicalName() + " e WHERE e.userName LIKE '" + username + "'");
		return (T) query.setMaxResults(1).uniqueResult();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public <T> int getCount(Class<T> cls) {
		Query query = session.createQuery("FROM " + cls.getCanonicalName());
		return query.list().size();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteByCompositeKey(String recordId, Integer userId) {
		Query query = session.createSQLQuery("DELETE FROM performers_list e WHERE e.music_id = '" + 
				recordId + "' AND user_id = " + userId);
		query.executeUpdate();
		query = session.createQuery("DELETE FROM " + Music.class.getCanonicalName() + " e WHERE e.id = '" + 
				recordId + "' AND e.user IN (SELECT t from " + User.class.getCanonicalName()
				+ " t WHERE t.userId = " + userId + ")");
		query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public <T> T findByCompositeKey(String recordId, Integer userId) {
		return (T) session.createQuery("SELECT e FROM " + Music.class.getCanonicalName() + " e WHERE e.id = '" + 
				recordId + "' AND e.user IN (SELECT t from " + User.class.getCanonicalName()
				+ " t WHERE t.userId = " + userId + ")").setMaxResults(1).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public <T> List<T> history(User userTo, User userFrom) {
		return (List<T>) session.createQuery("SELECT E FROM " + Message.class.getCanonicalName() +" e WHERE e.userTo = '" +
				userTo.getUserId() + "' AND e.userFrom = '" + userFrom.getUserId() + "'").list();
		
	}

}
