package com.musicStore.action;

import org.jfree.chart.JFreeChart;

import com.musicStore.biz.ChartBiz;
import com.opensymphony.xwork2.ActionSupport;

public class ChartAction extends ActionSupport {
	private JFreeChart chart;   
    public JFreeChart getChart() {   
        return chart;   
    } 
    private ChartBiz chartBiz;
	public ChartBiz getChartBiz() {
		return chartBiz;
	}
	public void setChartBiz(ChartBiz chartBiz) {
		this.chartBiz = chartBiz;
	}

	public String genreAlbumsChart(){
		this.chart = chartBiz.createGenreAlbumChart();
		return SUCCESS;
	}  
	
	public String genreOrderSalesDetailChart(){
		this.chart = chartBiz.createGenreSalesDetailChart();
		return SUCCESS;
	}  
}
