<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ page language="java" import="com.musicStore.entity.*"%>
<%@ page language="java" import="com.musicStore.dao.*"%>
<%@ page language="java" import="com.musicStore.dao.impl.*"%>
<%
	GenreDao genreDao = new GenreDaoImpl();
	List<Genre> genres = genreDao.getAll();
%>
			<div id="sidebar">
	            <h3>³ªÆ¬·ÖÀà</h3>
	            <ul class="categories">
	            	<% for(int i=0; i<genres.size(); i++) { %>
	            		<% if(i%2==1){ %>
		            <li><a href="/MusicStore/album_list.jsp?genreId=<%=genres.get(i).getId()%>"><%=genres.get(i).getName()%></a></li>
		            	<% }else{ %>
		            <li><a class="even" href="/MusicStore/album_list.jsp?genreId=<%=genres.get(i).getId()%>"><%=genres.get(i).getName()%></a></li>
		            	<% } %>
            		<% } %>
	            </ul>
            </div>