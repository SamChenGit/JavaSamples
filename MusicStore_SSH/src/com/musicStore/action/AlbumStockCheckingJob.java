package com.musicStore.action;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.musicStore.bean.Album;
import com.musicStore.biz.AlbumBiz;

public class AlbumStockCheckingJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext ctx)
			throws JobExecutionException {
		//从Spring容器中获取ServletContext,并获取Files文件夹的真实路径
	    WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext servletContext = webApplicationContext.getServletContext();
		String path = servletContext.getRealPath("Files/");
		//构建文件名称
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String filePath = path+ "\\唱片库存提示(" + format.format(new Date())+").xls";
		//获取AlbumBiz对象和库存警告线阈值
		AlbumBiz albumBiz = (AlbumBiz)ctx.getJobDetail().getJobDataMap().get("albumBiz");
		int stockLine = Integer.valueOf(ctx.getJobDetail().getJobDataMap().get("stockLine").toString());
		List<Album> albums = albumBiz.getAlbumsUnderStcokLine(stockLine);
		//保存到文件中
		try {
			System.out.println(filePath);
			FileOutputStream fos = new FileOutputStream(filePath);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos,"UTF-8"));
			String info = "<table border='1'>";
			bw.write(info);
			bw.newLine();
			info = "<tr><th>唱片ID</th><th>唱片标题</th><th>库存量</th></tr>";
			bw.write(info);
			bw.newLine();
			for(Album a : albums){
				String line = String.format("<tr><td>%d</td><td>%s</td><td>%d</td></tr>", a.getId(), a.getTitle(), a.getStock());
				bw.write(line);
				bw.newLine();
			}
			info = "</table>";
			bw.write(info);
			bw.newLine();
			bw.flush();
			bw.close();
			fos.close();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
