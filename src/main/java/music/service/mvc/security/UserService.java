package music.service.mvc.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import music.service.mvc.service.CRUDMVC;
import music.service.orm.entites.User;

@Service("userService")
public class UserService {
 
	@Autowired
	private CRUDMVC mvcCRUD;
	
    public Map<String, Object> getUserByUsername(String username) {
        Map<String, Object> userMap = null;
        User user = mvcCRUD.findByUserName(username);
        if (user != null && StringUtils.hasLength(username) && StringUtils.hasLength(user.getPassword())) {
            userMap = new HashMap<>();
            userMap.put("username", username);
            userMap.put("password", user.getPassword());
            userMap.put("role", user.getRole());
            return userMap;
        }
        return null;
    }
}
