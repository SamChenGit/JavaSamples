package com.musicStore.dao;

import java.util.List;

import com.musicStore.bean.UserRole;

public interface UserRoleDao {
	List<UserRole> getAll();
	UserRole fetchById(int id);
}
