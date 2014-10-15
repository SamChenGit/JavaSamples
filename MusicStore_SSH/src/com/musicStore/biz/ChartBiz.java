package com.musicStore.biz;

import org.jfree.chart.JFreeChart;

public interface ChartBiz {

	JFreeChart createGenreAlbumChart();
	JFreeChart createGenreSalesDetailChart();

}