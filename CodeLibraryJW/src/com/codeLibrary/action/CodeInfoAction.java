package com.codeLibrary.action;

import java.util.List;

import com.codeLibrary.biz.CategoryBiz;
import com.codeLibrary.biz.CodeInfoBiz;
import com.codeLibrary.biz.ResourceBiz;
import com.codeLibrary.entity.Category;
import com.codeLibrary.entity.CodeInfo;
import com.codeLibrary.entity.Resource;
import com.opensymphony.xwork2.ActionSupport;

public class CodeInfoAction extends ActionSupport {
	private CategoryBiz categoryBiz;
	private CodeInfoBiz codeInfoBiz;
	private ResourceBiz resourceBiz;
	public void setResourceBiz(ResourceBiz resourceBiz) {
		this.resourceBiz = resourceBiz;
	}
	public void setCategoryBiz(CategoryBiz categoryBiz) {
		this.categoryBiz = categoryBiz;
	}
	public void setCodeInfoBiz(CodeInfoBiz codeInfoBiz) {
		this.codeInfoBiz = codeInfoBiz;
	}

	private int cid;
	private String key;
	private String title;
	private List<Category> categories;
	private List<CodeInfo> codeInfoList;
	private List<Resource> resourceList;
	private CodeInfo codeInfo;
	private int id;
	private JsonResult jsonResult;
	
	public JsonResult getJsonResult() {
		return jsonResult;
	}
	public void setJsonResult(JsonResult jsonResult) {
		this.jsonResult = jsonResult;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CodeInfo getCodeInfo() {
		return codeInfo;
	}
	public void setCodeInfo(CodeInfo codeInfo) {
		this.codeInfo = codeInfo;
	}
	public List<CodeInfo> getCodeInfoList() {
		return codeInfoList;
	}
	public void setCodeInfoList(List<CodeInfo> codeInfoList) {
		this.codeInfoList = codeInfoList;
	}
	public List<Resource> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public String index(){
		this.categories = categoryBiz.getAllInTreeStyle("&nbsp;&nbsp;");
		return SUCCESS;
	}
	
	public String list(){
		this.codeInfoList = codeInfoBiz.getCodeInfosByCondition(cid, key, title);
		return "list_success";
	}
	
	public String detail(){
		this.codeInfo = codeInfoBiz.fetchByKey(this.id);
		this.resourceList = resourceBiz.getResourcesByCodeInfoId(this.id);
		return "detail_success";
	}
	
	public String edit(){
		this.categories = categoryBiz.getAllInTreeStyle("&nbsp;&nbsp;");
		if(this.id > 0)
			this.codeInfo = codeInfoBiz.fetchByKey(this.id);
		return "edit_success";
	}
	
	public String save(){
		try {
			if(this.codeInfo.getId()>0){
				codeInfoBiz.update(this.codeInfo);
			}else{
				codeInfoBiz.add(this.codeInfo);
			}
			this.jsonResult = new JsonResult(true, "保存成功。", this.codeInfo.getId());
		} catch (Exception e) {
			this.jsonResult = new JsonResult(false, "保存失败。（错误原因："+e.getMessage()+"）");
		}
		return "save_completed";
	}
	
	public String delete(){
		try {
			codeInfoBiz.delete(this.id);
			this.jsonResult = new JsonResult(true, "删除成功。");
		} catch (Exception e) {
			this.jsonResult = new JsonResult(false, "删除失败。（错误原因："+e.getMessage()+"）");
		}
		return "delete_completed";
	}	
}
