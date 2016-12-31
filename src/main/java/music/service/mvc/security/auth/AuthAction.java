package music.service.mvc.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import music.service.mvc.service.CRUDMVC;
import music.service.orm.entites.User;

public class AuthAction implements Auth {
	
	@Autowired
	private CRUDMVC mvcCRUD;
	
	@Override
	public ModelAndView doLogin() {
		return new ModelAndView("login");
	}

	@Override
	public ModelAndView doAuth() {
		return new ModelAndView("auth");
	}

	@Override
	public ModelAndView doSignUp(User user) {
		if (StringUtils.hasLength(user.getUserName()) && StringUtils.hasLength(user.getPassword())) { 
			user.setRole(Auth.Roles.ROLE_USER.toString());
	    	mvcCRUD.create(user);
	        return doLogin();
		} else
			return error();
	}

	@Override
	public ModelAndView error() {
		return new ModelAndView("error");
	}
}
