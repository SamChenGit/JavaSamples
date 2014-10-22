<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<table style="width:100%">
    <tr><th colspan="2">
         	<a id="editCodeInfo" href="_codeInfo_edit?id=${codeInfo.id}">${codeInfo.title}</a>
        </th>
    </tr>
    <tr><td>${codeInfo.keys }</td><td>${codeInfo.category.name}</td></tr>
    <tr><td colspan="2">
        ${codeInfo.content}
        </td>
    </tr>
    <tr><td colspan="2">
	    	<h4><a href="resource_list?codeInfoId=${codeInfo.id}">相关资源</a></h4>
	    	<c:if test="${fn:length(resourceList)!=0}">
	    	<ul>
				<c:forEach var="r" items="${resourceList}">
				<li><a href="download?resourceId=${r.id}" target="_blank">${r.title}</a></li>
				</c:forEach>
			</ul>
			</c:if>
   		</td>
    </tr>
</table>
