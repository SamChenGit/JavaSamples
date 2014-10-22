package com.codeLibrary.dao.sqlite;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.codeLibrary.dao.CategoryDao;
import com.codeLibrary.dao.CodeInfoDao;
import com.codeLibrary.entity.Category;
import com.codeLibrary.entity.CodeInfo;

public class CodeInfoDaoImpl extends HibernateDaoSupport implements CodeInfoDao {
	
	private CategoryDao categoryDao;
	public CategoryDao getCategoryDao() {
		return categoryDao;
	}
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public void add(CodeInfo info) {
		getHibernateTemplate().save(info);
	}
 
	public void delete(int id) {
		CodeInfo c = getHibernateTemplate().get(CodeInfo.class, id);
		getHibernateTemplate().delete(c);
	}

	public CodeInfo fetchByKey(int id) {
		return getHibernateTemplate().get(CodeInfo.class, id);
	}
	
	public void update(CodeInfo info) {
		getHibernateTemplate().update(info);
	}

	//根据条件获取信息，条件包括categoryId（分类ID,当小于等于0时表示没有该条件)、key（关键字，模糊查询，null或empty时表示没有该条件）、title（标题，模糊查询，null或empty时表示没有该条件）
	//目前暂不提供分类信息的递归查找
	@SuppressWarnings("unchecked")
	public List<CodeInfo> getCodeInfosByCondition(final int categoryId, final String key,final String title) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<CodeInfo>>() {
			public List<CodeInfo> doInHibernate(Session sess)
					throws HibernateException, SQLException {
				Map<String,Object> parameters = new HashMap<String, Object>();
				String hql = "from CodeInfo ci where 1=1";
				if(categoryId >0 ){
					hql += " and ci.category.id in ("+ CodeInfoDaoImpl.this.getAllChildrenIdsString(categoryId) +")";
				}
				if(key!=null && !key.isEmpty()){
					hql += " and ci.keys like :key";
					parameters.put("key", "%"+key+"%");
				}
				if(title!=null && !title.isEmpty()){
					hql += " and ci.title like :title";
					parameters.put("title", "%"+title+"%");
				}
				return sess.createQuery(hql)
							.setProperties(parameters)
							.list();
			}
		});
	}
	
	//用于获取某分类下面的所有子分类，可用作CodeInfo的查询条件
	private String getAllChildrenIdsString(int parentId) {
		List<Category> categories = categoryDao.getAll();
		StringBuilder result = new StringBuilder(); 
		getChildrenIdsString(categories, result, parentId);
		result.append(parentId+",");
		return result.substring(0,result.length()-1).toString();
	}
	//递归获取某分类下面的直系子分类，返回Ids字符串
	private void getChildrenIdsString(List<Category> categories,
			StringBuilder result, int parentId) {
		for(Category c : categories){
			if(c.getParentId() == parentId){
				result.append(c.getId()+",");
				getChildrenIdsString(categories, result, c.getId());
			}
		}
	}
	
	//单元测试
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		CodeInfoDao dao = (CodeInfoDao)ctx.getBean("codeInfoDao");
		System.out.println(dao.getCodeInfosByCondition(0, null, null).size());
		System.out.println(dao.getCodeInfosByCondition(1, null, null).size());
		System.out.println(dao.getCodeInfosByCondition(0, "struts", "").size());
		System.out.println(dao.getCodeInfosByCondition(0, "struts", "spring").size());
	}
}
