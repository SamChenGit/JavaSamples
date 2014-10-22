package com.codeLibrary.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.codeLibrary.biz.BizException;
import com.codeLibrary.biz.CategoryBiz;
import com.codeLibrary.dao.CategoryDao;
import com.codeLibrary.entity.Category;

public class CategoryBizImpl implements CategoryBiz {
	
	private CategoryDao categoryDao;
	public CategoryDao getCategoryDao() {
		return categoryDao;
	}
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public void add(Category category) throws BizException {
		if(category.getName().trim().isEmpty())
			throw new BizException("请指定分类名称。");
		Category existCate = categoryDao.fetchCategoryByName(category.getName());
		if(existCate!=null)
			throw new BizException("分类名称已重复，请重新指定。");
		categoryDao.add(category);
	}

	public void delete(int id) throws BizException {
		boolean isInUse = categoryDao.checkCategoryIsInUse(id);
		if(isInUse)
			throw new BizException("当前分类已被使用，不能删除。");
		categoryDao.delete(id);
	}

	public List<Category> getAll() {
		return categoryDao.getAll();
	}

	public void update(Category category) throws BizException {
		if(category.getName().trim().isEmpty())
			throw new BizException("请指定分类名称。");
		if(category.getId() == category.getParentId())
			throw new BizException("父级分类不能是该分类自身，请重新指定。");
		Category existCate = categoryDao.fetchCategoryByName(category.getName());
		if(existCate!=null && existCate.getId()!=category.getId())
			throw new BizException("分类名称已重复，请重新指定。");
		categoryDao.update(category);
	}
	
	public List<Category> getAllInTreeStyle(String prefix){
		List<Category> treeStyleList = new ArrayList<Category>();
		List<Category> originalList = categoryDao.getAll();
		arrangeTreeListWithParent(originalList, treeStyleList, 0, 0, prefix);
		return treeStyleList;
	}
	
	private void arrangeTreeListWithParent(List<Category> originalList, List<Category> treeStyleList, int parentId, int level, String prefix){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<level; i++){
			sb.append(prefix);
		}
		for(Category c : originalList){
			if(c.getParentId() == parentId){
				c.setName(sb.toString()+c.getName());
				treeStyleList.add(c);
				arrangeTreeListWithParent(originalList, treeStyleList, c.getId(), level+1, prefix);
			}
		}
	}

	public Category fetchByKey(int id) {
		return categoryDao.fetchByKey(id);
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx  = new ClassPathXmlApplicationContext("applicationContext.xml");
		CategoryBiz cBiz = (CategoryBiz)ctx.getBean("categoryBiz");
		List<Category> list = cBiz.getAllInTreeStyle("--");
		for(Category c:list){
			System.out.printf("%d\t\t%s\t\t%d\n",c.getId(),c.getName(),c.getParentId());
		}
	}
}
