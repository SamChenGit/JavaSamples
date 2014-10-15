package com.musicStore.biz;

import java.util.List;

import com.musicStore.bean.Album;

public interface AlbumBiz {
	List<Album> getHotAlbums(int count);	//获取最新的count张唱片
	int getRowCountByConditions(int genreId, String title);	//分页－获取总行数
	List<Album> getAlbumsByConditions(int genreId, String title, int page, int pageSize);//分页－获取当前页记录
	Album fetchAlbumById(int albumId);		//根据Id获取一张唱片
	void addAlbum(Album album);				//添加唱片
	void updateAlbum(Album album);			//修改唱片
	List<String> getAlbumTitlesByPrefix(String prefix, int count); 	//根据前缀模糊获取电影名称(AutoComplete用)
	List<Album> getAlbumsUnderStcokLine(int stockLine);				//获取低于库存线stockLine的唱片
	void updateStock(int albumId, int amount);			//调整库存，正数增加、负数减少
	List<Object[]> getAlbumsSalesQtyToday();
}
