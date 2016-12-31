package music.service.orm.entites;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Table;

import music.service.orm.utils.HStorage;

@SuppressWarnings("serial")
@Entity
@Proxy
@javax.persistence.Table(name = "music_list")
@Table(appliesTo = "music_list", indexes = {
		@Index(name = "index_music_list_link", columnNames = "link"),
		@Index(name = "index_music_list_music_name", columnNames = "music_name"),
		@Index(name = "index_music_list_common_information", columnNames = "commoninformation_record_id")
		})
public class Music implements Serializable {

	@Id
	@Column(name = "music_id", updatable = false, nullable = false)
	private String id;
	
	@Column(name = "music_name", nullable = false, length = 528)
	private String musicName;

	@Column(name = "music_owner_id", nullable = false)
	private String ownerId;
	
	@Column(name = "link", nullable = false, length = 528)
	private String link;
	
	@OneToOne(targetEntity = CommonInformation.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	private CommonInformation commonInformation;

	@OneToMany(targetEntity = Performer.class, mappedBy = "record")
	private Set<Performer> performers;
	
	@Id
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name = "user_id", nullable = true)
	@ForeignKey(name = "user_id")     
	private User user;
	
	public Music(String unid, String ownerId, String musicName, String link, CommonInformation commonInformation, User user) {
		this.id = unid;
		this.ownerId = ownerId;
		this.musicName = musicName;
		this.link = link;
		this.commonInformation = commonInformation;
		this.user = user;
	}
	
	public Music() {}
	
	public User getUser() {
		return HStorage.unproxy(this.user);
	}
	
	public Set<Performer> getPerformers() {
		return HStorage.unproxy(this.performers);
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getOwnerId() {
		return this.ownerId;
	}
	
	public String getMusicName() {
		return this.musicName;
	}
	
	public String getLink() {
		return this.link;
	}
	
	public CommonInformation getCommonInformation() {
		return (CommonInformation)HStorage.unproxy(this.commonInformation);
	}
	
	public void setCommonInformation(CommonInformation commonInformation) {
		this.commonInformation = commonInformation;
	}
	
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public void setId(String unid){
		this.id = unid;
	}
	
	public void setOwnerId(String ownerId){
		this.ownerId = ownerId;
	}
}
