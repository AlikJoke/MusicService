package music.service.orm.entites;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.Table;

import music.service.orm.utils.HStorage;

@Entity
@javax.persistence.Table(name = "music_common_information")
@Table(appliesTo = "music_common_information", indexes = {
		@Index(name = "index_music_common_record_duration", columnNames = "record_duration"),
		})
public class CommonInformation {
	
	@Id
	@Column(name = "record_id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Access(AccessType.FIELD)
	private Integer id;
	
	@Column(name = "record_genre", nullable = true, length = 32)
	private String genre;
	
	@Column(name = "record_date", nullable = true, length = 32)
	private String date;
	
	@Column(name = "record_duration", nullable = false)
	private Long duration;
	
	@OneToOne(mappedBy = "commonInformation", targetEntity = Music.class, cascade = CascadeType.ALL)
	private Music music;
	
	public CommonInformation(String genre, String date, Long duration, Music music) {
		this.music = music;
		this.genre = genre;
		this.date = date;
		this.duration = duration;
	}
	
	public CommonInformation() {}
	
	public Music getMusic() {
		return (Music) HStorage.unproxy(this.music);
	}
	
	public Integer getUNID() {
		return this.id;
	}
	
	public String getGenre() {
		return this.genre;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public Long getDuration() {
		return this.duration;
	}
	
	public void setUNID(Integer id) {
		this.id = id;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	
	
}
