package com.musicStore.dao;

import java.util.List;
import com.musicStore.entity.Album;

public interface AlbumDao {
	int getRowCountByConditions(int genreId, String title);
	List<Album> getAlbumsByConditions(int genreId, String title, int page, int pageSize);
	Album fetchAlbumByKey(int albumId);
	boolean addAlbum(Album album);
	boolean updateAlbum(Album album);
	List<Album> getHotAlbums(int count);
}
