package com.musicStore.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.musicStore.dao.UserDao;
import com.musicStore.entity.User;

public class UserDaoImpl extends BaseDao implements UserDao {
	public boolean addUser(User user){
		boolean result = false;
		String sql = "insert into [User] values(?,?,?,?,?,?,?,?)";
		try {
			this.openConnection();
			result = this.executeUpdate(sql, 
					new Object[]{user.getUsername(), user.getPassword(), 
					user.getName(), user.getAddress(), user.getPhone(), 
					user.getEmail(), user.getUserRoleId(), user.isInUse()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			this.closeResource();
		}
		return result;
	}
	public User checkLogin(String username, String password){
		User user = null;
		String sql = "select * from [User] where Username=? and Password=?";
		try {
			this.openConnection();
			ResultSet rs = this.executeQuery(sql, new Object[]{username, password});
			if(rs.next()){
				user = new User();
				user.setId(rs.getInt("Id"));
				user.setUsername(rs.getString("Username"));
				user.setPassword(rs.getString("Password"));
				user.setName(rs.getString("Name"));
				user.setAddress(rs.getString("Address"));
				user.setEmail(rs.getString("Email"));
				user.setPhone(rs.getString("Phone"));
				user.setUserRoleId(rs.getInt("userRoleId"));
				user.setInUse(rs.getBoolean("IsInUse"));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			this.closeResource();
		}
		return user;
	}
	
	public boolean updateUser(User user){
		boolean result = false;
		String sql = "update [User] set username=?,password=?,name=?,address=?,phone=?,email=?,userRoleId=?,isInUse=? where Id=?";
		try {
			this.openConnection();
			result = this.executeUpdate(sql, 
					new Object[]{user.getUsername(), user.getPassword(), 
					user.getName(), user.getAddress(), user.getPhone(), 
					user.getEmail(), user.getUserRoleId(), user.isInUse(), user.getId()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			this.closeResource();
		}
		return result;
	}
	
	public List<User> getAllUsers(){
		String sql = "select * from [User]";
		List<User> list = new ArrayList<User>();
		try {
			this.openConnection();
			ResultSet rs = this.executeQuery(sql,null);
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt("Id"));
				user.setUsername(rs.getString("Username"));
				user.setPassword(rs.getString("Password"));
				user.setName(rs.getString("Name"));
				user.setAddress(rs.getString("Address"));
				user.setEmail(rs.getString("Email"));
				user.setPhone(rs.getString("Phone"));
				user.setUserRoleId(rs.getInt("userRoleId"));
				user.setInUse(rs.getBoolean("IsInUse"));
				list.add(user);
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
	
	public boolean deleteUser(int id){
		boolean result = false;
		String sql = "delete from [User] where id=?";
		try {
			this.openConnection();
			result = this.executeUpdate(sql, new Object[]{id});
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			this.closeResource();
		}
		return result;
	}
}
