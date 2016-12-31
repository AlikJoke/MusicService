package music.service.mvc.controllers.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.servlet.ModelAndView;

import music.service.mvc.service.CRUDMVC;
import music.service.orm.utils.HStorage;

public class PersonalListActionImpl implements PersonalListAction {

	@Autowired
	private CRUDMVC mvcCRUD;

	@Override
	public ModelAndView getList(User authUser) {
		music.service.orm.entites.User user = HStorage.unproxy(mvcCRUD.findByUserName(authUser.getUsername()));
		if (user.getMusicList() != null) user.getMusicList().clear(); // действие глупое, но иначе org.hibernate.UnresolvableObjectException
		mvcCRUD.update(user);
		ModelAndView mv = new ModelAndView("mylist");
		mv.addObject("allRecords", user.getMusicList());
		return mv;
	}

	@Override
	public void delete(String id, User authUser) {
		music.service.orm.entites.User user = HStorage.unproxy(mvcCRUD.findByUserName(authUser.getUsername()));
		mvcCRUD.deleteByCompositeKey(id, user.getUserId());
	}
	
	
}
