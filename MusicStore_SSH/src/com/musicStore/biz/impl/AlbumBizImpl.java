package com.musicStore.biz.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.musicStore.bean.Album;
import com.musicStore.biz.AlbumBiz;
import com.musicStore.biz.BizException;
import com.musicStore.dao.AlbumDao;
import com.musicStore.dao.impl.AlbumDaoImpl;
 
public class AlbumBizImpl implements AlbumBiz {

	private AlbumDao albumDao;
	
	public AlbumDao getAlbumDao() {
		return albumDao;
	}

	public void setAlbumDao(AlbumDao albumDao) {
		this.albumDao = albumDao;
	}

	public List<Album> getHotAlbums(int count) {
		return albumDao.getHotAlbums(count);
	}

	public void addAlbum(Album album) {
		albumDao.addAlbum(album);
	}
	
	//--以下为业务方法

	public Album fetchAlbumById(int albumId) {
		return albumDao.fetchAlbumById(albumId);
	}

	public List<Album> getAlbumsByConditions(int genreId, String title,
			int page, int pageSize) {
		return albumDao.getAlbumsByConditions(genreId, title, page, pageSize);
	}

	public int getRowCountByConditions(int genreId, String title) {
		return albumDao.getRowCountByConditions(genreId, title);
	}

	public void updateAlbum(Album album) {
		albumDao.updateAlbum(album);
	}

	public List<String> getAlbumTitlesByPrefix(String prefix, int count) {
		return albumDao.getAlbumTitlesByPrefix(prefix, count);
	}

	public void updateStock(int albumId, int amount) {
		Album album = albumDao.fetchAlbumById(albumId);
		if(album.getStock()+amount<0){
			throw new BizException("该唱片库存量不足销售数量。");
		}
		album.setStock(album.getStock()+amount);
		albumDao.updateAlbum(album);
	}

	public List<Album> getAlbumsUnderStcokLine(int stockLine) {
		return albumDao.getAlbumsUnderStcokLine(stockLine);
	}

	public List<Object[]> getAlbumsSalesQtyToday() {
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		cal.add(Calendar.DATE, 1);
		Date then = cal.getTime();
		Date today = new Date(now.getYear(),now.getMonth(),now.getDate());
		Date tomorrow = new Date(then.getYear(),then.getMonth(),then.getDate());
		return albumDao.getAlbumsSalesQty(today, tomorrow);
	}

}
