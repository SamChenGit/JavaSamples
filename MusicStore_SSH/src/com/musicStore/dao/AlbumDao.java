package com.musicStore.dao;

import java.util.Date;
import java.util.List;
import com.musicStore.bean.Album;

public interface AlbumDao {
	int getRowCountByConditions(int genreId, String title);
	List<Album> getAlbumsByConditions(int genreId, String title, int page, int pageSize);
	Album fetchAlbumById(int id);
	void addAlbum(Album album);
	void updateAlbum(Album album);
	List<Album> getHotAlbums(int count);
	List<String> getAlbumTitlesByPrefix(String prefix, int count);
	public List<Object[]> getGenreAlbumsNum();
	public List<Album> getAlbumsUnderStcokLine(int stockLine);
	public List<Object[]> getAlbumsSalesQty(Date begin, Date end);
}
