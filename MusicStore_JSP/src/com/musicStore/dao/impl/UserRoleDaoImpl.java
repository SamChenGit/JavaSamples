package com.musicStore.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.musicStore.dao.UserRoleDao;
import com.musicStore.entity.UserRole;

public class UserRoleDaoImpl extends BaseDao implements UserRoleDao {
	public List<UserRole> getAll(){
		List<UserRole> list = new ArrayList<UserRole>();
		String sql = "select * from UserRole";
		try {
			this.openConnection();
			ResultSet rs = this.executeQuery(sql, null);
			while(rs.next()){
				UserRole r = new UserRole();
				r.setId(rs.getInt("Id"));
				r.setName(rs.getString("Name"));
				list.add(r);
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
}
