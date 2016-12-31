package music.service.mvc.controllers.actions;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.servlet.ModelAndView;

public interface SearchAction {

	public ModelAndView get(String query, User authUser);
	
	public void add(String recordId, User authUser);
}
