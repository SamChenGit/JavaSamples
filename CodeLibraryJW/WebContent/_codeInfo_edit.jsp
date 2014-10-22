<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<form id="editForm">
	<input type="hidden" name="codeInfo.id" value="${codeInfo.id}"/>
    <table style="width:100%">
        <tr>
            <td>标题</td>
            <td><input type="text" value="${codeInfo.title}" name="codeInfo.title" id="codeInfo_title"/>
				必填</td>
            <td>重要性：</td>
            <td><input type="text" value="${codeInfo.points}" name="codeInfo.points" id="codeInfo_points"/>
				必填 数字</td>
        </tr>
        <tr>
            <td>Keys：</td>
            <td><input type="text" value="${codeInfo.keys}" name="codeInfo.keys" id="codeInfo_keys"/>
				必填</td>    
            <td>分类：</td>
            <td><select name="codeInfo.category.id" id="codeInfo_category_id">
            		<option value="0">----</option>
            		<c:forEach var="c" items="${categories}">
            		<c:if test="${codeInfo.category.id==c.id}">
            		<option value="${c.id}" selected="selected">${c.name}</option>
            		</c:if>
            		<c:if test="${codeInfo.category.id!=c.id}">
            		<option value="${c.id}">${c.name}</option>
            		</c:if>
            		</c:forEach>
            	</select>
				必填
            </td>
        </tr>
        <tr>
            <td>内容：</td>
            <td colspan="3">
            	<textarea cols="80" name="codeInfo.content" rows="10"><c:out value="${codeInfo.content}" /></textarea>
            </td>   
        </tr>
        <tr><td></td>
            <td colspan="3">
                <button type="button" id="save">保存</button>
                <c:if test="${codeInfo!=null}">
                <button type="button" id="delete" value="${codeInfo.id}">删除</button>
				<a id="showCodeInfo" href="_codeInfo_detail?id=${codeInfo.id}">返回只读</a>
				</c:if>
            </td>
        </tr>
    </table>
    <label id="eidtError" class="error">${errorMessage}</label>
</form>