package music.service.mvc.controllers.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.servlet.ModelAndView;

import music.service.mvc.service.CRUDMVC;
import music.service.orm.entites.Message;

public class SendActionImpl implements SendAction {

	@Autowired
	private CRUDMVC mvcCRUD;

	ModelAndView mv = new ModelAndView("dialog");
	
	@Override
	public ModelAndView show(String userToName, User userAuthFrom) {
		music.service.orm.entites.User userFrom = mvcCRUD.findByUserName(userAuthFrom.getUsername());
		music.service.orm.entites.User userTo = mvcCRUD.findByUserName(userToName);
		mv.addObject("messageList", mvcCRUD.history(userTo, userFrom));
		return mv;
	}

	@Override
	public ModelAndView send(String message, User userAuthFrom, String userToName) {
		music.service.orm.entites.User userFrom = mvcCRUD.findByUserName(userAuthFrom.getUsername());
		music.service.orm.entites.User userTo = mvcCRUD.findByUserName(userToName);
		mvcCRUD.create(new Message(message, userFrom, userTo));
		return mv;
	}
}
