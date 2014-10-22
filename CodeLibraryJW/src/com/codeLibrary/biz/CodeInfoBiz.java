package com.codeLibrary.biz;

import java.util.List;

import com.codeLibrary.entity.CodeInfo;

public interface CodeInfoBiz {
	public void add(CodeInfo info);
	public void delete(int id);
	public CodeInfo fetchByKey(int id);
	public void update(CodeInfo info);
	public List<CodeInfo> getCodeInfosByCondition(final int categoryId, final String key,final String title);	
}
