package com.musicStore.action;

import java.util.Date;
import java.util.List;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.musicStore.biz.AlbumBiz;

public class AlbumSalesQtyCheckingJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext ctx)
			throws JobExecutionException {
		System.out.println("----------今日唱片销售情况----------");
		JobDataMap map = ctx.getJobDetail().getJobDataMap();
		AlbumBiz albumBiz = (AlbumBiz)map.get("albumBiz");
		List<Object[]> list = albumBiz.getAlbumsSalesQtyToday();
		System.out.println("唱片名称, 销量");
		for(Object[] arr : list){
			System.out.printf("%s, %d\n", arr[0], ((Number)arr[1]).intValue());
		}
		System.out.printf("-----统计时间：%s-----", new Date());
	}
}
