package com.musicStore.biz.impl;

import java.awt.Font;
import java.math.BigDecimal;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.musicStore.biz.ChartBiz;
import com.musicStore.dao.AlbumDao;
import com.musicStore.dao.OrderDetailDao;

public class ChartBizImpl implements ChartBiz {
	private AlbumDao albumDao;
	public AlbumDao getAlbumDao() {
		return albumDao;
	}
	public void setAlbumDao(AlbumDao albumDao) {
		this.albumDao = albumDao;
	}
	
	private OrderDetailDao orderDetailDao;
	public OrderDetailDao getOrderDetailDao() {
		return orderDetailDao;
	}
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}
	
	public JFreeChart createGenreAlbumChart() {
		DefaultCategoryDataset  data = new DefaultCategoryDataset ();
		List<Object[]> list = albumDao.getGenreAlbumsNum();
		for(Object[] arr : list){
			data.addValue((Long)arr[1], "", arr[0].toString());	
		}
		
        JFreeChart chart = ChartFactory.createBarChart("分类唱片统计","唱片分类","唱片数", // 图表标题
                data, // 数据集
                PlotOrientation.VERTICAL,
                true, // 是否显示图例(对于简单的柱状图必须是 false)
                false, // 是否生成工具
                false // 是否生成 URL 链接
                );

        //中文乱码
        CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();  
        CategoryAxis domainAxis = categoryplot.getDomainAxis();  
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("黑体", Font.PLAIN, 20));      
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));  
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));  
        numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
        numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));  
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
        return chart;
	}
	
	public JFreeChart createGenreSalesDetailChart() {
		DefaultPieDataset data = new DefaultPieDataset();
		List<Object[]> list = orderDetailDao.getGenresDetailsSummary();
		for(Object[] arr : list){
			data.setValue(arr[0].toString(), (BigDecimal)arr[1]);
		}
        JFreeChart chart = ChartFactory.createPieChart3D("分类销售数量统计", // 图表标题
                data, // 数据集
                true, // 是否显示图例(对于简单的柱状图必须是 false)
                false, // 是否生成工具
                false // 是否生成 URL 链接
                );

        //中文乱码
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setLabelFont(new Font("黑体", Font.PLAIN, 20));
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
        return chart;
	}
}
