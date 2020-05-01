package com.zc.bookmarkdb.object;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "tag")
public class Tag {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	
	@SuppressWarnings("deprecation")
	@ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@JsonIgnoreProperties("tags")
	private Set<Bookmark> bookmarks = new HashSet<>();
	
	
	// add bookmark
	public void addBookmark(Bookmark b) {
		this.bookmarks.add(b);
	}
	
	// remove bookmark
	public void removeBookmark(Bookmark b) {
		this.bookmarks.remove(b);
	}
	
	// Constructor
	public Tag(String name) {
		super();
		this.name = name;
	}
	
	// Constructor without argument
	public Tag() {
		super();
		this.name = "default";
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
		Tag other = (Tag) obj;
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

	public Set<Bookmark> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(Set<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}
	
	
}
