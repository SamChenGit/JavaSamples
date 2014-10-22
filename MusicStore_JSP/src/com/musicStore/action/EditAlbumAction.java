package com.musicStore.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.musicStore.dao.AlbumDao;
import com.musicStore.dao.impl.AlbumDaoImpl;
import com.musicStore.entity.Album;

public class EditAlbumAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SmartUpload su = new SmartUpload();
		su.initialize(this.getServletConfig(),request,response);
		su.setCharset("gbk");
		su.setAllowedFilesList("jpg");
		try {
			su.upload();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		String title = su.getRequest().getParameter("title");
		String artist = su.getRequest().getParameter("artist");
		String dateReleased = su.getRequest().getParameter("dateReleased");
		
		String description = su.getRequest().getParameter("description");
		int genreId = Integer.valueOf(su.getRequest().getParameter("genreId"));
		BigDecimal price = new BigDecimal(su.getRequest().getParameter("price"));
		
		boolean success = false;
		AlbumDao albumDao = new AlbumDaoImpl();
		Album album = new Album(0,genreId,"",artist,title,price,Date.valueOf(dateReleased),description);
		if(su.getRequest().getParameter("id") == null){
			success = albumDao.addAlbum(album);
		}
		else{
			int id = Integer.valueOf(su.getRequest().getParameter("id"));
			album.setId(id);
			success = albumDao.updateAlbum(album);
		}
		
		if(success){
			try {
				File file = su.getFiles().getFile(0);
				if(!file.isMissing()){
					file.saveAs("CoverImages/" + album.getId() + ".jpg", File.SAVEAS_VIRTUAL);
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}
			String url = "/MusicStore/admin/album_management.jsp?genreId=" + genreId;
			response.sendRedirect(url);
		}
		else{
			request.setAttribute("error","ÃÌº” ß∞‹£¨«Î…“∫Û‘Ÿ ‘°£");
			request.getRequestDispatcher("/MusicStore/admin/album_edit.jsp").forward(request,response);
		}
	}
	
}
