package com.zc.bookmarkdb.object;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "history")
public class History {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private LocalDateTime historyDateTime;
	private String description;
	
	public History() {
		super();
	}
	
	public History(String d) {
		super();
		this.description = d;
		this.historyDateTime = LocalDateTime.now();
	}
	
	//---------------------------------------------------
	// getters and setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getHistoryDateTime() {
		return historyDateTime;
	}

	public void setHistoryDateTime(LocalDateTime historyDateTime) {
		this.historyDateTime = historyDateTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
