package com.musicStore.biz;
import com.musicStore.bean.User;

public interface UserBiz {
	void register(User user);							//注册用户
	User checkLogin(String username, String password);	//检查登录
	void updateUser(User user);							//更新用户
	User fetchById(int id);								//获取用户Id
	User fetchUserByUsername(String username);			//根据用户名获取用户（可用于验证重复性）
}
