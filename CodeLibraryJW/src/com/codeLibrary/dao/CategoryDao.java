package com.codeLibrary.dao;

import java.util.List;
import com.codeLibrary.entity.Category;

public interface CategoryDao {
	public List<Category> getAll();
	public Category fetchByKey(int id);
    public Category fetchCategoryByName(String name);
    public boolean checkCategoryIsInUse(int id);
    public void add(Category category);
	public void update(Category category);
	public void delete(int id);
}
