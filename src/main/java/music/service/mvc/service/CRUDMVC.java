package music.service.mvc.service;

import java.util.List;

import music.service.orm.entites.User;

public interface CRUDMVC {

	public <T> void create(T entity);
	
	public <T> void update(T entity);
	
	public <T> void delete(T entity);
	
	public <T> T find(String id, Class<T> cls);
	
	public <T> List<T> findByUserId(String id);
	
	public <T> T findByUserName(String username);
	
	public <T> void createList(List<T> entities);
	
	public <T> int getCount(Class<T> cls);
	
	public void deleteByCompositeKey(String recordId, Integer userId);
	
	public <T> List<T> history(User userTo, User userFrom);
}
