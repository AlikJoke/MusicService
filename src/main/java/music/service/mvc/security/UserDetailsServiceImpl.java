package music.service.mvc.security;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String arg) throws UsernameNotFoundException {
		Map<String, Object> userMap = userService.getUserByUsername(arg);

        if (userMap == null)
            throw new UsernameNotFoundException("User details not found with this username: " + arg);
 
        String username = (String) userMap.get("username");
        String password = (String) userMap.get("password");
        String role = (String) userMap.get("role");
        List<SimpleGrantedAuthority> authList = getAuthorities(role);
        
        User user = new User(username, password, authList);
 
        return user;
	}
	
	private List<SimpleGrantedAuthority> getAuthorities(String role) {
        List<SimpleGrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority(role));
        return authList;
    }

}
