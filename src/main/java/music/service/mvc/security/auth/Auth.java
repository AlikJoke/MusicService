package music.service.mvc.security.auth;


import org.springframework.web.servlet.ModelAndView;

import music.service.orm.entites.User;

public interface Auth {

	public ModelAndView doLogin();
	
	public ModelAndView doAuth();
	
	public ModelAndView error();
	
	public ModelAndView doSignUp(User user);
	
	public enum Roles {
		ROLE_USER,
		
		ROLE_ADMIN,
		
		ROLE_MOD;
	}
}
