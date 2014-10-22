package com.codeLibrary.biz.impl;

import java.util.List;

import com.codeLibrary.biz.CodeInfoBiz;
import com.codeLibrary.dao.CodeInfoDao;
import com.codeLibrary.entity.CodeInfo;

public class CodeInfoBizImpl implements CodeInfoBiz {

	private CodeInfoDao codeInfoDao;
	public CodeInfoDao getCodeInfoDao() {
		return codeInfoDao;
	}
	public void setCodeInfoDao(CodeInfoDao codeInfoDao) {
		this.codeInfoDao = codeInfoDao;
	}

	public void add(CodeInfo info) {
		codeInfoDao.add(info);
	}

	public void delete(int id) {
		codeInfoDao.delete(id);
	}

	public CodeInfo fetchByKey(int id) {
		return codeInfoDao.fetchByKey(id);
	}

	public List<CodeInfo> getCodeInfosByCondition(int categoryId, String key,
			String title) {
		return codeInfoDao.getCodeInfosByCondition(categoryId, key, title);
	}

	public void update(CodeInfo info) {
		codeInfoDao.update(info);
	}
}
