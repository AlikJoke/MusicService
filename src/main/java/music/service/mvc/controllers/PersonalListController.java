package music.service.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import music.service.mvc.controllers.actions.PersonalListAction;

@Controller
public class PersonalListController {

	@Autowired
	private PersonalListAction plAction;
	
	private static final String PATH = "mylist";
	
	@RequestMapping(value = PATH, method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.FOUND)
	public ModelAndView getList(@AuthenticationPrincipal User authUser) {
		return plAction.getList(authUser);
	}
	
	@RequestMapping(value = PATH + "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") String id, @AuthenticationPrincipal User authUser) {
		plAction.delete(id, authUser);
	}
}
