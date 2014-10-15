package com.musicStore.action;

import java.util.List;

import com.musicStore.bean.Genre;
import com.musicStore.biz.GenreBiz;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class GenreListSubAction extends ActionSupport {
	
	private GenreBiz genreBiz;
	public void setGenreBiz(GenreBiz genreBiz){
		this.genreBiz=genreBiz;
	}
	
	private List<Genre> genres = null;
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public String execute(){
		this.genres = genreBiz.getAll();
		return SUCCESS;
	}
	
	//解决验证出错时<s:action>不执行的问题
	public void validate(){
		this.clearErrorsAndMessages();
	}
}
