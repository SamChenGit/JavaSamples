package com.codeLibrary.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.codeLibrary.biz.CodeInfoBiz;
import com.codeLibrary.biz.ResourceBiz;
import com.codeLibrary.entity.CodeInfo;
import com.codeLibrary.entity.Resource;
import com.opensymphony.xwork2.ActionSupport;

public class ResourceAction extends ActionSupport {
	private ResourceBiz resourceBiz;
	private CodeInfoBiz codeInfoBiz;
	
	public void setResourceBiz(ResourceBiz resourceBiz) {
		this.resourceBiz = resourceBiz;
	}
	public void setCodeInfoBiz(CodeInfoBiz codeInfoBiz) {
		this.codeInfoBiz = codeInfoBiz;
	}

	private List<Resource> resourceList;
	private int codeInfoId;
	private Resource resource;
	private File upload;
	private String uploadFileName;
	private CodeInfo codeInfo;
	private int resourceId;
	
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	public CodeInfo getCodeInfo() {
		return codeInfo;
	}
	public void setCodeInfo(CodeInfo codeInfo) {
		this.codeInfo = codeInfo;
	}
	public List<Resource> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}
	public int getCodeInfoId() {
		return codeInfoId;
	}
	public void setCodeInfoId(int codeInfoId) {
		this.codeInfoId = codeInfoId;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String list(){
		this.codeInfo = codeInfoBiz.fetchByKey(codeInfoId);
		this.resourceList = resourceBiz.getResourcesByCodeInfoId(codeInfoId);
		if(this.resourceId>0)
			this.resource = resourceBiz.fetchByKey(this.resourceId);
		return "list_success";
	}
	
	public String save() throws IOException{
		if(resource.getId()>0){		//修改resource对象,仅可以修改标题
			Resource res = resourceBiz.fetchByKey(resource.getId());
			res.setTitle(resource.getTitle());
			resourceBiz.update(res);
		}
		else{
			if(resource.getTitle().isEmpty()){			//若没有填写标题,则用文件名做标题
				resource.setTitle(this.uploadFileName);
			}
			String ext = uploadFileName.substring(uploadFileName.lastIndexOf(".")+1);
			resource.setResourceType(ext);				//设置资源类型(文件扩展名)
			resource.setLocation("");					//字段非空,因此设置默认值
			resourceBiz.add(this.resource);
			String location = "UploadResources/"+resource.getId()+"."+ext;
			resource.setLocation(location);				//保存使用资源id重设资源路径并update实体
			resourceBiz.update(resource);
			//保存文件,新增时必须要上传文件
			String path = ServletActionContext.getServletContext().getRealPath(location);
			File destFile = new File(path);
			FileUtils.copyFile(upload, destFile);
		}
		return "save_success";
	}
	
	public String delete(){
		resourceBiz.delete(resource.getId());
		String path = ServletActionContext.getServletContext().getRealPath(resource.getLocation());
		new File(path).delete();
		return "save_success";
	}
}
