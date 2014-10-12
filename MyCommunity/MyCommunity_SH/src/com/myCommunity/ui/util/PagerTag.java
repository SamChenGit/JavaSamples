package com.myCommunity.ui.util;


import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PagerTag extends SimpleTagSupport{
	private int pageSize = 10;
	private int numbers = 10;
	
	private int totalRows;
	private int curPage;
	private String urlFormat;
	
	public String getUrlFormat() {
		return urlFormat;
	}
	public void setUrlFormat(String urlFormat) {
		this.urlFormat = urlFormat;
	}

	private int totalPages;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getNumbers() {
		return numbers;
	}
	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		if(curPage<1)
			this.curPage = 1;
		else
			this.curPage = curPage;
	}

	@Override
	public void doTag() throws JspException, IOException {
		totalPages = (totalRows%pageSize == 0 ? totalRows/pageSize : totalRows/pageSize+1);
	
		int leftPageNum = 1;//起始页码数
		int rightPageNum = totalPages;//结束页码数
		int offset = ((numbers % 2==1)? numbers:numbers+1)/2;//页码个数左右偏移量
		if(totalPages>numbers){
			if(curPage<=offset){
				leftPageNum = 1;
				rightPageNum = numbers;
			}else{	//如果当前页大于左偏移
				//如果当前页码右偏移超出最大分页数
				if(curPage + offset >= totalPages+1){
					leftPageNum = totalPages-numbers+1;
				}else{
					//左右偏移都存在时的计算
					leftPageNum = curPage - offset;
					rightPageNum = (numbers % 2==1)? (curPage + offset):(curPage + offset-1);
				}
			}
		}  
     
		JspWriter out = this.getJspContext().getOut();
		out.println("<ul class=\"pagination\">");
		if(curPage > 1){
			out.println("<li><a href=\"" + String.format(urlFormat, 1) + "\">&lt;&lt;</a></li>");
			out.println("<li><a href=\"" + String.format(urlFormat, curPage-1) + "\">&lt;</a></li>");
		}else{
			out.println("<li class=\"disabled\"><a>&lt;&lt;</a></li>");
			out.println("<li class=\"disabled\"><a>&lt;</a></li>");
		}
		for(int i=leftPageNum; i<=rightPageNum; i++){
			if(i == curPage)
				out.println("<li class=\"active\"><a>" + i + "</a></li>");
			else
				out.println("<li><a href=\"" + String.format(urlFormat, i) + "\">" + i + "</a></li>");
		}
		if(curPage < totalPages){
			out.println("<li><a href=\"" + String.format(urlFormat, curPage+1) + "\">&gt;</a></li>");
			out.println("<li><a href=\"" + String.format(urlFormat, totalPages) + "\">&gt;&gt;</a></li>");
		}else{
			out.println("<li class=\"disabled\"><a>&gt;</a></li>");
			out.println("<li class=\"disabled\"><a>&gt;&gt;</a></li>");
		}
		out.println("</ul>");
		out.println("<span class=\"pager-info\">共"+this.totalPages+"页，第"+this.curPage+"页</span>");
	}
}
