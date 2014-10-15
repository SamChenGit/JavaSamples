package com.musicStore.action;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.musicStore.biz.AlbumBiz;
import com.opensymphony.xwork2.ActionSupport;

public class SearchAutoCompleteAction extends ActionSupport {
	private AlbumBiz albumBiz;
	
	@JSON(serialize=false)
	public AlbumBiz getAlbumBiz() {
		return albumBiz;
	}
	public void setAlbumBiz(AlbumBiz albumBiz) {
		this.albumBiz = albumBiz;
	}

	private List<String> titles;
	private String prefix;
	public List<String> getTitles() {
		return titles;
	}
	public void setTitles(List<String> titles) {
		this.titles = titles;
	}

	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String execute(){
		this.titles = albumBiz.getAlbumTitlesByPrefix(this.prefix, 10);
		return SUCCESS;
	}
}
