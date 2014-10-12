<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.myCommunity.com/tags" prefix="ct" %>
<ol class="breadcrumb">
  <li><a href="#">首页</a></li>
  <li><a>搜索结果</a></li>
</ol>
<div class="panel panel-default">
    <div class="panel-heading">搜索出标题包含 “${param.title}” 的记录 ${totalRows} 条。</div>
    <div class="panel-body">
    	<c:if test="${totalRows!=0}">
        <table class="table">
            <thead>
                <tr>
                    <th class="col-md-6">主题</th>
                    <th class="col-md-2">作者</th>
                    <th class="col-md-1">回复</th>
                    <th class="col-md-3">最后更新时间</th>
                </tr>
            </thead>
            <tbody>
               	<c:forEach var="t" items="${topics}">
                <tr>
                    <td><a href="topic/${t.id}">${t.title}</a></td>
                    <td>${t.author.nickname }</td>
                    <td>${t.commentTimes} / ${t.clicks}</td>
                    <td>${t.updateTime}</td>
                </tr>
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="4" class="pager-container">
                    	<ct:pager urlFormat="search_result?page=%d&title=${param.title}" totalRows="${totalRows}" curPage="${param.page}" pageSize="5"/>
                    </td>
                </tr>
            </tfoot>
        </table>
        </c:if>
    </div>
</div>