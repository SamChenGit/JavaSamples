package com.musicStore.dao;

import java.util.List;
import com.musicStore.entity.Genre;

public interface GenreDao {
	List<Genre> getAll();
	Genre fetchByKey(int genreId);
	boolean deleteGenre(int genreId);
	boolean addGenre(Genre genre);
	boolean updateGenre(Genre genre);
}
