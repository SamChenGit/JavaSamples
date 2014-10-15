package com.musicStore.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.musicStore.bean.Album;
import com.musicStore.bean.Genre;
import com.musicStore.biz.AlbumBiz;
import com.musicStore.biz.GenreBiz;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAlbumAction extends ActionSupport {
	private AlbumBiz albumBiz;
	private GenreBiz genreBiz;
	public AlbumBiz getAlbumBiz() {
		return albumBiz;
	}
	public void setAlbumBiz(AlbumBiz albumBiz) {
		this.albumBiz = albumBiz;
	}
	public GenreBiz getGenreBiz() {
		return genreBiz;
	}
	public void setGenreBiz(GenreBiz genreBiz) {
		this.genreBiz = genreBiz;
	}
	
	private final int PAGE_SIZE = 5;
	private List<Album> albumList;
	private List<Genre> genreList;
	private Album album;
	private int albumId;
	private int genreId;
	private String title;
	private int page = 1;
	private int pages;
	public int getPages(){
		return this.pages;
	}
	public void setPages(int pages){
		this.pages = pages;
	}
	public List<Album> getAlbumList() {
		return albumList;
	}
	public void setAlbumList(List<Album> albumList) {
		this.albumList = albumList;
	}
	public List<Genre> getGenreList() {
		return genreList;
	}
	public void setGenreList(List<Genre> genreList) {
		this.genreList = genreList;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	public int getAlbumId() {
		return albumId;
	}
	public void setAlbumId(int albumId) {
		this.albumId = albumId;
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
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public String albumManagement(){
		this.genreList = genreBiz.getAll();
		this.albumList = albumBiz.getAlbumsByConditions(genreId, title, page, PAGE_SIZE);
		double rows = albumBiz.getRowCountByConditions(genreId, title);
		this.pages = (int)Math.floor(rows/PAGE_SIZE);
		return SUCCESS;
	}
	
	public String albumEdit(){
		this.genreList = genreBiz.getAll();
		if(this.albumId > 0){
			this.album = albumBiz.fetchAlbumById(this.albumId);
		}
		return SUCCESS;
	}
	
	private File uploadCover;
	public File getUploadCover(){
		return this.uploadCover;
	}
	public void setUploadCover(File uploadCover){
		this.uploadCover = uploadCover;
	}
	
	public String albumSave() throws Exception {
		if(this.album.getId()>0){
			albumBiz.updateAlbum(this.album);
		}else{
			albumBiz.addAlbum(this.album);
		}
		if(this.uploadCover != null){
			String realPath = ServletActionContext.getServletContext().getRealPath("/CoverImages");
			File file = new File(realPath,this.album.getId() + ".jpg");
			FileUtils.copyFile(this.uploadCover, file);
		}
		return SUCCESS;
	}
}
