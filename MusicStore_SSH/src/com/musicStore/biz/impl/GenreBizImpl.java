package com.musicStore.biz.impl;

import java.util.List;

import com.musicStore.bean.Genre;
import com.musicStore.biz.GenreBiz;
import com.musicStore.dao.GenreDao;
import com.musicStore.dao.impl.GenreDaoImpl;

public class GenreBizImpl implements GenreBiz {

	private GenreDao genreDao;
	
	public GenreDao getGenreDao() {
		return genreDao;
	}

	public void setGenreDao(GenreDao genreDao) {
		this.genreDao = genreDao;
	}

	public List<Genre> getAll() {
		return genreDao.getAll();
	}
}
