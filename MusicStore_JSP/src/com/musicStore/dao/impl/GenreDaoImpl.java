package com.musicStore.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.musicStore.dao.GenreDao;
import com.musicStore.entity.Genre;

public class GenreDaoImpl extends BaseDao implements GenreDao {
	
	public boolean addGenre(Genre genre) {
		String sql = "insert into Genre values(?,?)";
		boolean result = false;
		try {
			this.openConnection();
			result = this.executeUpdate(sql, new Object[]{
				genre.getName(),
				genre.getDescription()
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			this.closeResource();
		}
		return result;
	}

	public boolean deleteGenre(int genreId) {
		boolean result = false;
		String sql = "delete from Genre where Id=?";
		try {
			this.openConnection();
			result = this.executeUpdate(sql, new Object[]{genreId});
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			this.closeResource();
		}
		return result;
	}

	public Genre fetchByKey(int genreId) {
		Genre g = null;
		String sql = "select * from Genre where Id=?";
		try {
			this.openConnection();
			ResultSet rs = this.executeQuery(sql, new Object[]{ genreId });
			if(rs.next()){
				g = new Genre();
				g.setId(rs.getInt("Id"));
				g.setName(rs.getString("Name"));
				g.setDescription(rs.getString("Description"));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			this.closeResource();
		}
		return g;
	}

	public List<Genre> getAll() {
		List<Genre> list = new ArrayList<Genre>();
		String sql = "select * from Genre";
		try {
			this.openConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Genre g = new Genre();
				g.setId(rs.getInt("Id"));
				g.setName(rs.getString("Name"));
				g.setDescription(rs.getString("Description"));
				list.add(g);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			this.closeResource();
		}
		return list;
	}

	public boolean updateGenre(Genre genre) {
		boolean result = false;
		String sql = "update Genre set Name=?,Description=? where Id=?";
		try {
			this.openConnection();
			result = this.executeUpdate(sql, new Object[]{
					genre.getName(),
					genre.getDescription(),
					genre.getId()
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
