package com.musicStore.bean;

import java.sql.Date;

public class Album { 
	public Album(){}
	public Album(int id, String artist,
			String title, double price, Date dateReleased,
			String description) {
		super();
		this.id = id;
		this.artist = artist;
		this.title = title;
		this.price = price;
		this.dateReleased = dateReleased;
		this.description = description;
	}
	
	private int id;
	private Genre genre;
	private String artist;
	private String title;
	private double price;
	private int stock;
	private Date dateReleased;
	private String description;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
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
