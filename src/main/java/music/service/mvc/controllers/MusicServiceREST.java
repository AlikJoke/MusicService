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

import music.service.mvc.controllers.actions.SearchActionImpl;
import music.service.mvc.utils.Methods;

@Controller
public class MusicServiceREST extends SearchActionImpl {
	
	private static final String PATH = Methods.SEARCH;
		
	@RequestMapping(value = PATH, method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView get(@RequestParam("query") String query, @AuthenticationPrincipal User user) {
		return super.get(query, user);
	}
	
	@RequestMapping(value = PATH + "/add/{id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public <T> void create(@PathVariable("id") String id, @AuthenticationPrincipal User authUser) {
		super.add(id, authUser);
	}
}
