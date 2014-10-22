package com.codeLibrary.biz;

import java.util.List;

import com.codeLibrary.entity.Category;

public interface CategoryBiz {
	public List<Category> getAll();
	public void add(Category category) throws BizException;
	public void update(Category category) throws BizException;
	public void delete(int id) throws BizException;
	public List<Category> getAllInTreeStyle(String prefix);
	public Category fetchByKey(int id);
}
