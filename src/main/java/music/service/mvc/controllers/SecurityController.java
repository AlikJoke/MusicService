package music.service.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import music.service.mvc.security.auth.Auth;
import music.service.orm.entites.User;

@Controller
@RequestMapping(value = "/login")
public class SecurityController {
	
	@Autowired
	private Auth auth;
	
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView doLogin() {
        return auth.doLogin();
    }
    
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView doAuth(@ModelAttribute User user) {
    	return auth.doAuth();
    }
    
    @RequestMapping(value="/auth", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ModelAndView doSignUp(@ModelAttribute User user) {
    	return auth.doSignUp(user);
    }
}
