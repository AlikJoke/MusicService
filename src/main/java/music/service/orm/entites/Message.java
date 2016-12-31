package music.service.orm.entites;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Table;

import music.service.orm.utils.HStorage;

@Entity
@Proxy
@javax.persistence.Table(name = "message_list")
@Table(appliesTo = "message_list", indexes = {
		@Index(name = "index_message_list_message_text", columnNames = "message_text"),
		@Index(name = "index_message_list_user_from", columnNames = "user_from"),
		@Index(name = "index_message_list_user_to", columnNames = "user_to"),
		@Index(name = "index_message_list_user_to_id", columnNames = "user_to_id"),
		@Index(name = "index_message_list_user_from_id", columnNames = "user_from_id"),
		@Index(name = "index_message_list_date", columnNames = "date")
		})
public class Message implements Serializable {

	@Id
	@Column(name = "message_id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String messageId;
	
	@Column(name = "message_text", nullable = false, length = 2048)
	private String messageText;
	
	@Column(name = "user_from", nullable = false, length = 32)
	private String userFromName;
	
	@Column(name = "user_to", nullable = false, length = 32)
	private String userToName;

	@Column(name = "date", nullable = false)
	private Date date;

	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name = "user_from_id", nullable = false)
	@ForeignKey(name = "user_from_id")   
	@NotFound(action = NotFoundAction.EXCEPTION)
	private User userFrom;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name = "user_to_id", nullable = false)
	@ForeignKey(name = "user_to_id")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private User userTo;

	public Message() {}
	
	public Message(String messageText, User userFrom, User userTo) {
		this.messageText = messageText;
		this.date = new Date();
		this.userFrom = userFrom;
		this.userTo = userTo;
		this.userFromName = userFrom.getUserName();
		this.userToName = userTo.getUserName();
	}
	
	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public String getUserFromName() {
		return userFromName;
	}

	public void setUserFromName(String userFromName) {
		this.userFromName = userFromName;
	}

	public String getUserToName() {
		return userToName;
	}

	public void setUserToName(String userToName) {
		this.userToName = userToName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUserFrom() {
		return HStorage.unproxy(userFrom);
	}

	public void setUserFrom(User userFrom) {
		this.userFrom = userFrom;
	}

	public User getUserTo() {
		return HStorage.unproxy(userTo);
	}

	public void setUserTo(User userTo) {
		this.userTo = userTo;
	}
}
