package music.service.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import music.service.orm.dao.DaoCRUD;
import music.service.orm.entites.User;

@Service("CRUDService")
@Transactional
public class CRUDMVCImpl implements CRUDMVC {

	@Autowired
	private DaoCRUD daoCRUD;
	
	@Override
	public <T> void create(T entity) {
		daoCRUD.create(entity);
	}

	@Override
	public <T> void update(T entity) {
		daoCRUD.update(entity);
	}

	@Override
	public <T> void delete(T entity) {
		daoCRUD.delete(entity);
	}

	@Override
	public <T> T find(String id, Class<T> cls) {
		return daoCRUD.find(id, cls);
	}

	@Override
	public <T> void createList(List<T> entities) {
		for (T entity : entities)
			daoCRUD.create(entity);
	}

	@Override
	public <T> List<T> findByUserId(String id) {
		return daoCRUD.findByUserId(id);
	}

	@Override
	public <T> T findByUserName(String username) {
		return daoCRUD.findByUserName(username);
	}

	@Override
	public <T> int getCount(Class<T> cls) {
		return daoCRUD.getCount(cls);
	}

	@Override
	public void deleteByCompositeKey(String recordId, Integer userId) {
		daoCRUD.deleteByCompositeKey(recordId, userId);
	}

	@Override
	public <T> List<T> history(User userTo, User userFrom) {
		return daoCRUD.history(userTo, userFrom);
	}
}
