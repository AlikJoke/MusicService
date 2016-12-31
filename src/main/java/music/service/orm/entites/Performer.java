package music.service.orm.entites;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Table;

import music.service.orm.utils.HStorage;

@Entity
@Proxy
@javax.persistence.Table(name = "performers_list")
@Table(appliesTo = "performers_list", indexes = {
		@Index(name = "index_performers_list_performer_name", columnNames = "performer_name"),
		@Index(name = "index_performers_list_music_id", columnNames = "music_id")
		})
public class Performer {
	
	@Id
	@Column(name = "performer_id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Access(AccessType.FIELD)
	private Integer unid;
	
	@Column(name = "performer_name", nullable = true, length = 128)
	private String performerName;	
	
	@ManyToOne(optional = true, targetEntity = Music.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumns({
        @JoinColumn(name = "music_id", referencedColumnName = "music_id", nullable = true),
        @JoinColumn(name="user_id", referencedColumnName="user_id", nullable = true)
    })
	private Music record;
	
	public Performer() {}
	
	public Performer(String performerName, Music record) {
		this.performerName = performerName;
		this.record = record;
	}
	
	public Integer getUNID() {
		return this.unid;
	}
	
	public String getPerformerName() {
		return this.performerName;
	}
	
	public Music getRecord() {
		return HStorage.unproxy(this.record);
	}
	
	public void setRecord(Music record) {
		this.record = record;
	}
	
	public void setUNID(Integer unid) {
		this.unid = unid;
	}
	
	public void setPerformerName(String performerName) {
		this.performerName = performerName;
	}
	

}
