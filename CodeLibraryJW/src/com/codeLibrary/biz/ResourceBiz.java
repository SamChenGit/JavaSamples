package com.codeLibrary.biz;

import java.util.List;

import com.codeLibrary.entity.Resource;

public interface ResourceBiz {
	List<Resource> getResourcesByCodeInfoId(int codeInfoId);
	void delete(int resourceId);
	void add(Resource resource);
	void update(Resource resource);
	Resource fetchByKey(int resourceId);
}
