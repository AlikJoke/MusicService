package music.service.mvc.controllers.actions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.servlet.ModelAndView;

import music.service.mvc.service.CRUDMVC;
import music.service.mvc.utils.BuilderURI;
import music.service.mvc.utils.HttpJacksonParser;
import music.service.mvc.utils.Methods;
import music.service.mvc.utils.Params;

public class SearchActionImpl implements SearchAction {

	@Autowired
	private CRUDMVC mvcCRUD;
	
	Map<String, String> params;
	ModelAndView mv;
	
	@Override
	public ModelAndView get(String query, User authUser) {
		params = new HashMap<String, String>();
		mv = new ModelAndView(Methods.SEARCH);
		params.put(Params.SEARCH_Q, query);
		params.put(Params.GET_COUNT, "300");
		music.service.orm.entites.User user = mvcCRUD.findByUserName(authUser.getUsername());
		mv.addObject("musicList", HttpJacksonParser.getList(BuilderURI.buildURI(Methods.SEARCH, params), user));
		return mv;
	}

	@Override
	public void add(String recordId, User authUser) {
		params = new HashMap<String, String>();
		params.put(Params.GET_AUDIOS, recordId);
		music.service.orm.entites.User user = mvcCRUD.findByUserName(authUser.getUsername());
		mvcCRUD.createList(HttpJacksonParser.getList(BuilderURI.buildURI(Methods.GET_BY_ID, params), user));		
	}

}
