package music.service.mvc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import music.service.mvc.controllers.actions.SendActionImpl;

@Controller
public class MessageController extends SendActionImpl {
	
	private static final String PATH = "send";
	
	@RequestMapping(value = PATH, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ModelAndView send(@RequestParam("message") String message, 
			@RequestParam("userTo") String userToName, @AuthenticationPrincipal User user) {
		return super.send(message, user, userToName);
	}
	
	@RequestMapping(value = PATH, method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView show(@PathVariable("user") String userToName, @AuthenticationPrincipal User authUser) {
		return super.show(userToName, authUser);
	}
}
