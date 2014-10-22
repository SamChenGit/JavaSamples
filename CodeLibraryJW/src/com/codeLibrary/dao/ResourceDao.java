package com.codeLibrary.dao;

import java.util.List;

import com.codeLibrary.entity.Resource;

public interface ResourceDao {
	List<Resource> getResourcesByCodeInfoId(int codeInfoId);
	void delete(int resourceId);
	void update(Resource resource);
	void add(Resource resource);
	Resource fetchByKey(int resourceId);
}
