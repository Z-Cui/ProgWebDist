package com.zc.bookmarkdb.object;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "bookmark")
public class Bookmark {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String name;
	private String url;
	private String savedUser;
	private int clickcount;
	private LocalDateTime lastclick;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "bookmark_tag",
			joinColumns = {@JoinColumn(name = "bookmark_id")},
			inverseJoinColumns = {@JoinColumn(name = "tag_id")})
	@JsonIgnoreProperties("bookmarks")
	private Set<Tag> tags = new HashSet<Tag>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<History> history = new HashSet<History>();
	
	public void addClick() {
		this.clickcount = this.clickcount + 1;
		this.lastclick = LocalDateTime.now();
	}
	
	public void addTag(Tag t) {		
		this.tags.add(t);
	}
	
	public void deleteTag(Tag t) {
		this.tags.remove(t);
	}
	
	public void addHistory(History h) {
		this.history.add(h);
	}
	
	// Constructor without argument
	public Bookmark() {
		super();
	}
	
	// Constructor without tags
	public Bookmark(String n, String u, String su) {
		super();
		this.name = n;
		this.url = u;
		this.savedUser = su;
		this.clickcount = 0;
	}
	
	// Copy Constructor
	public Bookmark(Bookmark b) {
		this.id = b.getId();
		this.name = b.getName();
		this.url = b.getUrl();
		this.savedUser = b.getSavedUser();
		this.clickcount = b.getClickcount();
		this.lastclick = b.getLastclick();
		this.history = b.getHistory();
		this.tags = b.getTags();
	}
	

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bookmark other = (Bookmark) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	//---------------------------------------------------
	// getters and setters
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSavedUser() {
		return savedUser;
	}

	public void setSavedUser(String savedUser) {
		this.savedUser = savedUser;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	public Set<Tag> getTags() {
		return tags;
	}
	
	public Set<History> getHistory() {
		return history;
	}	
	
	public void setHistory(Set<History> history) {
		this.history = history;
	}

	public int getClickcount() {
		return clickcount;
	}

	public void setClickcount(int clickcount) {
		this.clickcount = clickcount;
	}

	public LocalDateTime getLastclick() {
		return lastclick;
	}

	public void setLastclick(LocalDateTime lastclick) {
		this.lastclick = lastclick;
	}
	
	
}
