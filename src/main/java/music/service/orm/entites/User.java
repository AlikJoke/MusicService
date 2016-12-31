package music.service.orm.entites;

import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Table;

import music.service.mvc.security.auth.Auth;
import music.service.orm.utils.HStorage;

@Entity
@Proxy
@javax.persistence.Table(name = "users")
@Table(appliesTo = "users", indexes = { 
		@Index(name = "index_users_user_name", columnNames = "user_name"),
		@Index(name = "index_users_password", columnNames = "password"),
		@Index(name = "index_users_role", columnNames = "role")
		})
public class User {
	
	@Id
	@Column(name = "user_id", updatable = false, nullable = false)
	@Access(AccessType.FIELD)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	
	@Column(name = "user_name", nullable = false, length = 32)
	private String userName;
	
	@Column(name = "password", nullable = false, length = 32)
	private String password;
	
	@Column(name = "email", nullable = false, length = 32)
	private String email;
	
	@Column(name = "role", nullable = false, length = 16)
	private String role;

	@OneToMany(targetEntity = Music.class, mappedBy = "user")
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@NotFound(action = NotFoundAction.IGNORE)
	private Set<Music> musicList;
	
	@OneToMany(targetEntity = Message.class, mappedBy = "userFrom")
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@NotFound(action = NotFoundAction.IGNORE)
	private Set<Message> messageListFrom;
	
	@OneToMany(targetEntity = Message.class, mappedBy = "userTo")
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@NotFound(action = NotFoundAction.IGNORE)
	private Set<Message> messageListTo;
	
	public User(String userName, String password, String email, String role) {
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.role = role;
	}
	
	public User(String userName, String password, String email) {
		this(userName, password, email, Auth.Roles.ROLE_USER.toString());
	}
	
	public User() {}
	
	public Integer getUserId() {
		return this.userId;
	}
	
	public Set<Music> getMusicList() {
		return HStorage.unproxy(this.musicList);
	}
	
	public Set<Message> getMessageListFrom() {
		return HStorage.unproxy(this.messageListFrom);
	}
	
	public Set<Message> getMessageListTo() {
		return HStorage.unproxy(this.messageListTo);
	}
	
	
	
	public String getUserName() {
		return this.userName;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}
}
