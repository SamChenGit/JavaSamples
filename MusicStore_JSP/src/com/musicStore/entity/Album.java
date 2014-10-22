package com.musicStore.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Album {
	public Album(){}
	public Album(int id, int genreId, String genreName, String artist,
			String title, BigDecimal price, Date dateReleased,
			String description) {
		super();
		this.id = id;
		this.genreId = genreId;
		this.genreName = genreName;
		this.artist = artist;
		this.title = title;
		this.price = price;
		this.dateReleased = dateReleased;
		this.description = description;
	}
	
	private int id;
	private int genreId;
	private String genreName;
	private String artist;
	private String title;
	private BigDecimal price;
	private Date dateReleased;
	private String description;
	
	public int getId() {
		return id;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public Date getDateReleased() {
		return dateReleased;
	}
	public void setDateReleased(Date dateReleased) {
		this.dateReleased = dateReleased;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGenreId() {
		return genreId;
	}
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public String getDescriptionTrancate(int maxLength){
		if(description.length() <= maxLength)
			return description;
		else
			return description.substring(0,maxLength-1) + "...";
	}
	public void setDescription(String description) {
		this.description = description;
	} 
}
