package com.musicStore.dao.impl;

import java.sql.*;
import java.util.*;

import com.musicStore.dao.AlbumDao;
import com.musicStore.entity.Album;

public class AlbumDaoImpl extends BaseDao implements AlbumDao {
	
	public int getRowCountByConditions(int genreId, String title){
		ArrayList paramList = new ArrayList();
		String sql = "select count(Id) from Album where 1=1";
		if(genreId > 0){
			sql += " and GenreId=?";
			paramList.add(genreId);
		}
		if(title != null && title != ""){
			sql += " and Title like ?";
			paramList.add("%" + title + "%");
		}
		int rowCount = 0;
		try {
			this.openConnection();
			ResultSet rs = this.executeQuery(sql, paramList.toArray());
			if(rs.next()){
				rowCount = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			this.closeResource();
		}
		return rowCount;
	}
	
	public List<Album> getAlbumsByConditions(int genreId, String title, int page, int pageSize) {
		List<Album> list = new ArrayList<Album>();
		ArrayList paramList = new ArrayList();
		String sql = "with x as (select row_number() over(order by a.Id Desc) as Nr,a.*,g.Name as GenreName from Album as a join Genre as g on a.GenreId=g.Id where 1=1";
		if(genreId > 0){
			sql += " and GenreId=?";
			paramList.add(genreId);
		}
		if(title != null && (!title.equals(""))){
			sql += " and Title like ?";
			paramList.add("%" + title + "%");
		}
		sql += ") select * from x where Nr>? and Nr<=?";
		paramList.add((page-1)*pageSize);
		paramList.add(page*pageSize);
		try {
			this.openConnection();
			ResultSet rs = this.executeQuery(sql, paramList.toArray());
			while(rs.next()){
				Album a = new Album();
				a.setId(rs.getInt("Id"));
				a.setArtist(rs.getString("Artist"));
				a.setTitle(rs.getString("Title"));
				a.setGenreId(rs.getInt("GenreId"));
				a.setGenreName(rs.getString("GenreName"));
				a.setPrice(rs.getBigDecimal("Price"));
				a.setDateReleased(rs.getDate("DateReleased"));
				a.setDescription(rs.getString("Description"));
				list.add(a);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			this.closeResource();
		}
		return list;
	}
	
	public Album fetchAlbumByKey(int albumId){
		Album a = null;
		String sql = "select a.*,g.Name as GenreName from Album as a join Genre as g on a.GenreId=g.Id where a.Id=?";
		try {
			this.openConnection();
			ResultSet rs = this.executeQuery(sql, new Object[]{albumId});
			if(rs.next()){
				a = new Album();
				a.setId(rs.getInt("Id"));
				a.setArtist(rs.getString("Artist"));
				a.setTitle(rs.getString("Title"));
				a.setGenreId(rs.getInt("GenreId"));
				a.setGenreName(rs.getString("GenreName"));
				a.setPrice(rs.getBigDecimal("Price"));
				a.setDateReleased(rs.getDate("DateReleased"));
				a.setDescription(rs.getString("Description"));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			this.closeResource();
		}
		return a;
	}
	
	public List<Album> getHotAlbums(int count){
		List<Album> list = new ArrayList<Album>();
		String sql = "select top (?) a.*,g.Name as GenreName from Album as a join Genre as g on a.GenreId=g.Id order by DateReleased Desc";
		try {
			this.openConnection();
			ResultSet rs = this.executeQuery(sql, new Object[]{count});
			while(rs.next()){
				Album a = new Album();
				a.setId(rs.getInt("Id"));
				a.setArtist(rs.getString("Artist"));
				a.setTitle(rs.getString("Title"));
				a.setGenreId(rs.getInt("GenreId"));
				a.setPrice(rs.getBigDecimal("Price"));
				a.setDateReleased(rs.getDate("DateReleased"));
				a.setDescription(rs.getString("Description"));
				list.add(a);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			this.closeResource();
		}
		return list;
	}
	
	public boolean addAlbum(Album album){
		boolean result = false;
		String sql = "INSERT [dbo].[Album] ([GenreId], [Artist], [Title], [Price], [DateReleased], [Description]) VALUES (?,?,?,?,?,?);select @@Identity;";
		try {
			this.openConnection();
			this.executeQuery(sql,new Object[]{
					album.getGenreId(),
					album.getArtist(),
					album.getTitle(),
					album.getPrice(),
					album.getDateReleased(),
					album.getDescription()
			});
			if(rs.next()){
				int id = rs.getInt(1);
				album.setId(id);
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			this.closeResource();
		}
		return result;
	}
	
	public boolean updateAlbum(Album album){
		boolean result = false;
		String sql = "update [dbo].[Album] set [GenreId]=?, [Artist]=?, [Title]=?, [Price]=?, [DateReleased]=?, [Description]=? where Id=?";
		try {
			this.openConnection();
			result = this.executeUpdate(sql,new Object[]{
					album.getGenreId(),
					album.getArtist(),
					album.getTitle(),
					album.getPrice(),
					album.getDateReleased(),
					album.getDescription(),
					album.getId()
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			this.closeResource();
		}
		return result;
	}
	
	public static void main(String[] args){
		AlbumDaoImpl dao = new AlbumDaoImpl();
		List<Album> list = dao.getAlbumsByConditions(0, null, 1, 5);
		int count = dao.getRowCountByConditions(0, null);
		
		System.out.println(list.size());
		System.out.println(count);
	}
}
