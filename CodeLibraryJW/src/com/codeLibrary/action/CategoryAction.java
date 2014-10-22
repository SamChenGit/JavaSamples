package com.codeLibrary.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.codeLibrary.biz.BizException;
import com.codeLibrary.biz.CategoryBiz;
import com.codeLibrary.entity.Category;
import com.opensymphony.xwork2.ActionSupport;

public class CategoryAction extends ActionSupport {

	private CategoryBiz categoryBiz;

	public CategoryBiz getCategoryBiz() {
		return categoryBiz;
	}

	public void setCategoryBiz(CategoryBiz categoryBiz) {
		this.categoryBiz = categoryBiz;
	}

	private List<Category> categories;
	private Category category;
	private int id;
	private String errorMessage;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@SkipValidation
	public String list() {
		this.categories = categoryBiz.getAllInTreeStyle("&nbsp;&nbsp;");
		if (this.id > 0)
			this.category = categoryBiz.fetchByKey(id);
		return "list_success";
	}

	public String save() {
		try {
			if (this.category.getId() > 0)
				categoryBiz.update(this.category);
			else
				categoryBiz.add(this.category);
			return "save_success";
		} catch (BizException e) {
			this.errorMessage = e.getMessage();
			return "input";
		}
	}
	
	public String delete(){
		try {
			categoryBiz.delete(this.category.getId());
			return "save_success";
		} catch (BizException e) {
			this.errorMessage = e.getMessage();
			return "input";
		}
	}

}