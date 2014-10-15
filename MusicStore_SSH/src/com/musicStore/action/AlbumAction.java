package com.musicStore.action;

import java.util.List;
import com.musicStore.bean.Album;
import com.musicStore.biz.AlbumBiz;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AlbumAction extends ActionSupport {

	//-----待Spring注入-----
	private AlbumBiz albumBiz;
	public void setAlbumBiz(AlbumBiz albumBiz) {
		this.albumBiz = albumBiz;
	}

	//-----属性------
	private int albumId = 0;
	private Album album = null;
	private List<Album> albums = null;
	private int pageSize = 4;
	private int page = 1;
	private int pages = 0;
	private int genreId = 0;
	private String title;
	public int getAlbumId() {
		return albumId;
	}
	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	public List<Album> getAlbums() {
		return albums;
	}
	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
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

	//-----action 方法-----
	public String index(){
		this.albums = albumBiz.getHotAlbums(9);
		return SUCCESS;
	}
	
	public String album(){
		this.album = albumBiz.fetchAlbumById(this.albumId);
		return SUCCESS;
	}
	
	public String albumList() throws Exception{
		this.albums = albumBiz.getAlbumsByConditions(genreId, title, page, pageSize);
		int rows = albumBiz.getRowCountByConditions(genreId, title);
		this.pages = (int)Math.ceil(((double)rows)/pageSize);
		return SUCCESS;
	}
	
	//-----getHotAlbums in JSON-----
	public String albumListInJSON(){
		this.albums = albumBiz.getAlbumsByConditions(0, title, 1, 10000);
		return SUCCESS;
	}
}
