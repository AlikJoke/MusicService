package music.service.mvc.controllers.actions;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.servlet.ModelAndView;

public interface PersonalListAction {
	
	public ModelAndView getList(User user);
	
	public void delete(String id, User user);
}
