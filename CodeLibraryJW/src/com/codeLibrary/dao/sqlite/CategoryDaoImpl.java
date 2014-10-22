package com.codeLibrary.dao.sqlite;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.codeLibrary.dao.CategoryDao;
import com.codeLibrary.entity.Category;

public class CategoryDaoImpl extends HibernateDaoSupport implements CategoryDao {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		CategoryDao dao = (CategoryDao)ctx.getBean("categoryDao");
		System.out.println(dao.getAll().size());
		Category c = dao.fetchCategoryByName(".NET");
		System.out.println(c.getId()+","+c.getName());
		System.out.println(dao.checkCategoryIsInUse(5)); 
	}

	public List<Category> getAll() {
		return getHibernateTemplate().find("from Category");
	}
	
	public void add(Category category){
		getHibernateTemplate().save(category);
	}
	
	public void update(Category category){
		getHibernateTemplate().update(category);
	}
	
	public void delete(int id){
		Category c = getHibernateTemplate().get(Category.class, id);
		getHibernateTemplate().delete(c);
	}

	//检查某一分类是否被引用了
	public boolean checkCategoryIsInUse(final int id) {
		return getHibernateTemplate().execute(new HibernateCallback<Boolean>() {
			public Boolean doInHibernate(Session sess)
					throws HibernateException, SQLException {
				Number count = (Number)sess.createQuery("select Count(c.id) from Category c where c.parentId=:id")
								.setInteger("id", id)
								.uniqueResult();
				if (count.longValue() > 0)
					return true;
				count = (Number)sess.createQuery("select Count(ci.id) from CodeInfo ci where ci.category.id=:cid")
		         				.setInteger("cid", id)
		         				.uniqueResult();
		        if (count.longValue() > 0)
		        	return true;
		        return false;
			}
		});
	}
	//根据分类名称获取分类对象,可用于验证分类是否重名
	public Category fetchCategoryByName(String name) {
		List<Category> list = getHibernateTemplate().find("from Category c where c.name=?",name);
		return list.size()==0?null:list.get(0);
	}

	public Category fetchByKey(int id) {
		return getHibernateTemplate().get(Category.class, id);
	}
}
