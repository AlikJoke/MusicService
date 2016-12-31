package music.service.mvc.controllers.actions;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.servlet.ModelAndView;

public interface SendAction {

	public ModelAndView send(String message, User userFrom, String userTo);
	
	public ModelAndView show(String userTo, User authUser);
}
