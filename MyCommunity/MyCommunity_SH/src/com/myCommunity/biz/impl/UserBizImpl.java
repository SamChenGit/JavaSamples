package com.myCommunity.biz.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myCommunity.biz.BizException;
import com.myCommunity.biz.UserBiz;
import com.myCommunity.dao.UserDao;
import com.myCommunity.entity.Role;
import com.myCommunity.entity.User;
import com.myCommunity.ui.util.ContextUtils;

@Service("userBiz")
public class UserBizImpl implements UserBiz {

	@Resource
	private UserDao userDao;
	
	public User checkLogin(String username, String password) {
		return userDao.checkLogin(username, password);
	}

	public void register(User user) {
		if(!userDao.checkUsernameAvailable(user.getUsername()))
			throw new BizException("该用户名已被注册，请重新指定。");
		user.setGrade(0);
		user.setRole(new Role(Role.COMMON_USER,null));
		user.setSignature(null);
		user.setStatus(true);
		userDao.add(user);
		//添加新用户后，刷新用户缓存中的数据
		ContextUtils.setUsersMap(getAllUsersKeyValuePair());
	}

	public User fetchById(int id) {
		return userDao.fetchById(id);
	}

	public void update(User user) {
		userDao.update(user);
	}
	
	public boolean checkUsernameAvailable(String username){
		return userDao.checkUsernameAvailable(username);
	}

	public Map<String, Integer> getAllUsersKeyValuePair() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(Object[] arr : userDao.getAllUsersKeyValuePair()){
			map.put((String)arr[1], (Integer)arr[0]);
		}
		return map;
	}

}
