package com.game.manager.modules.lottery.schedule;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.manager.common.utils.SpringContextHolder;
import com.game.manager.common.utils.StringUtils;
import com.game.manager.modules.draw.LotteryBonusService;
import com.game.manager.modules.lottery.dto.OpenCaiResp;
import com.game.manager.modules.lottery.dto.OpenCaiResult;
import com.game.manager.modules.lottery.exception.LotteryNumDrawException;
import com.game.manager.modules.lottery.service.LotteryTimeNumService;

/**
 * 拉奖号码执行器
 * 分组名+任务名作为任务的唯一key("SSC_CQ:20171109086"), kv格式
 * @author Administrator
 *
 */
@Service
@DisallowConcurrentExecution
public class LotteryNumJob implements Job {
	private static final Logger  logger = LoggerFactory.getLogger(LotteryNumJob.class);
	
	@Autowired
    private LotteryTimeNumService lotteryTimeNumService;
	@Autowired
	private LotteryNumDrawService lotteryNumDrawService ;
	@Autowired
	private LotteryBonusService lotteryBonusService ;
	
	/* *
	 * 获取当前任务的key,比如:SSC_CQ:21071118085
	 * 从拉奖通道获取开奖提供方
	 * 调用服务，获取开奖数据
	 * 返回当前期号的开奖结果
	 * 如果获取到开奖号码，就把号码结果放到redis中，
	 * 结束当前job
	 * 如果网络异常，就更换其他通道
	 * 如果没取到号码，就继续运行job任务
	 * 
	 * 需要后台模块：拉奖通道管理，配置上述参数
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//获取当前任务的key,比如:SSC_CQ_21071118085
		JobKey jobKey = context.getJobDetail().getKey();
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String lotteryCode = dataMap.getString("lotteryCode");
        String issueNo = dataMap.getString("lotteryIssueNo");
		logger.debug("current job[{}], executing at[{}], lotteryCode[{}], issueNo[{}]", 
				new Object[] {jobKey, new Date(), lotteryCode, issueNo});
		try {
			//调用服务，获取开奖数据
			OpenCaiResult openCaiResult = lotteryNumDrawService.drawLotteryNum(lotteryCode, issueNo);
			//如果获取到开奖号码，就把号码结果放到redis中
			logger.debug("clotteryCode[{}], issueNo[{}], lotteryNum[{}]", 
					new Object[] {lotteryCode, issueNo,openCaiResult == null?null:openCaiResult.getOpencode()});
			
			if(null == openCaiResult) {
				return;//continue;
			}
			//拉奖号码更新到数据库
			lotteryTimeNumService.updateLotteryNum(openCaiResult.getOpencode(), lotteryCode, issueNo,"1",openCaiResult.getOpentime());
			//结束当前job:删除定时任务时   先暂停任务，然后再删除  
	        context.getScheduler().pauseJob(jobKey);  
	        context.getScheduler().deleteJob(jobKey); 
	        //派奖
	        lotteryBonusService.calculateOrderBonusFromDB(lotteryCode,issueNo);
		} catch (LotteryNumDrawException e) {
			//TODO:更换其他通道,  或者继续运行拉奖服务
			e.printStackTrace();
			logger.error("22");
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
