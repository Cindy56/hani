/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.lottery.schedule;

import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.modules.lottery.dto.TimeTask;

/**
 * 定时任务管理Service
 * @author vinton
 * @version 2017-11-08
 */
@Service
@Transactional(readOnly = true)
public class TimeTaskService {
	private static final Logger logger = LoggerFactory.getLogger(TimeTaskService.class);
	
	@Autowired  
	private SchedulerFactoryBean schedulerFactoryBean;  

//	public TimeTask get(String id) {
//		return super.get(id);
//	}
//	
//	public List<TimeTask> findList(TimeTask timeTask) {
//		return super.findList(timeTask);
//	}
//	
//	public Page<TimeTask> findPage(Page<TimeTask> page, TimeTask timeTask) {
//		return super.findPage(page, timeTask);
//	}
	
//	@Transactional(readOnly = false)
//	public void save(TimeTask timeTask) {
//		super.save(timeTask);
//	}
//	
//	@Transactional(readOnly = false)
//	public void delete(TimeTask timeTask) {
//		super.delete(timeTask);
//	}
	
	//===================================================定时任务管理
    /**
     * 在容器启动后，启动定时器；
     */
    public void start() {  
        logger.info("start category update notify scheduler");  
        schedulerFactoryBean.start();  
    }  
	
    
    /**
     * 批量添加拉奖定时任务
     * @param timeTaskList
     * @throws SchedulerException
     */
    public void addJobs(List<TimeTask> timeTaskList) throws SchedulerException {
    	for(TimeTask timeTask:timeTaskList) {
    		addJob(timeTask);
    	}
    }
    
    /** 
     * 添加拉奖定时任务 ：SSC_CQ:21071118085
     * @param  
     * @throws SchedulerException 
     */  
    @Transactional(readOnly = false)
    public void addJob(TimeTask timeTask) throws SchedulerException {
    	StringBuilder taskname = new StringBuilder();
    	taskname.append(timeTask.getLotteryCode()).append(":").append(timeTask.getLotteryIssueNo());
    	JobDetail jobDetail = JobBuilder.newJob(LotteryNumJob.class)
    			.withIdentity(taskname.toString(), "lotteryNumJob-group001")
    			.usingJobData("lotteryCode", timeTask.getLotteryCode())
    			.usingJobData("lotteryIssueNo", timeTask.getLotteryIssueNo())
    			.build();
    	
    	Trigger trigger = TriggerBuilder.newTrigger()
	    		.withIdentity(taskname.toString(), "lotteryNumTrigger-group001")
	    		.withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(10, 5))
	    		.startAt(timeTask.getRunAtTime())
//	    		.withSchedule(CronScheduleBuilder.cronSchedule(timeTask.getCronExpression()))//设置定时任务执行的表达式
	    		.build();
    	
    	 schedulerFactoryBean.getScheduler().scheduleJob(jobDetail,  trigger);
    }
    
    /** 
     * 删除任务 
     * @param allPushMessage 
     * @throws SchedulerException 
     * @throws Exception 
     */  
    public void deleteJob(String key) throws SchedulerException{
    	//TODO:XXXXXX
        //删除定时任务时   先暂停任务，然后再删除  
        JobKey jobKey = new JobKey(key);
        Scheduler scheduler = schedulerFactoryBean.getScheduler();  
        scheduler.pauseJob(jobKey);  
        scheduler.deleteJob(jobKey);  
    }  
	
    /** 
     * 获取jobKey 
     * @param key 
     * @return 
     */  
    public JobKey getJobKey(String key) {
        return JobKey.jobKey(key);  
    }  
	
    /** 
     * 更新定时任务 
     * @param 
     * @param 
     * @throws SchedulerException 
     * @throws Exception 
     */  
    public void updateJob() throws SchedulerException{
    	//TODO:XXXXXX
        TriggerKey triggerKey =getTriggerKey("jobkey");  
        Scheduler scheduler = schedulerFactoryBean.getScheduler();  

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);  

        // 按新的cronExpression表达式重新构建trigger  
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).build();  

        // 按新的trigger重新设置job执行  
        scheduler.rescheduleJob(triggerKey, trigger);  
    }
    
    /**
     * 获取触发器key 
     * @param jobkey
     * @return
     */
    public TriggerKey getTriggerKey(String jobkey) {  
        return TriggerKey.triggerKey(jobkey);  
    }
    
    /** 
     * 暂停定时任务 
     * @param allPushMessage 
     * @throws SchedulerException 
     * @throws Exception 
     */  
    public void pauseJob() throws SchedulerException{
    	//TODO:XXXXXX
        JobKey jobKey = JobKey.jobKey("jobkey");  
        Scheduler scheduler = schedulerFactoryBean.getScheduler();  
        scheduler.pauseJob(jobKey);  
    }
    
    /** 
     * 恢复任务 
     * @param 
     * @param 
     * @param 
     * @throws SchedulerException 
     * @throws Exception 
     */  
    public void resumeJob() throws SchedulerException{
    	//TODO:XXXXXX
        JobKey jobKey = JobKey.jobKey("jobkey");  
        Scheduler scheduler = schedulerFactoryBean.getScheduler();  
        scheduler.resumeJob(jobKey);   
    }
    
    /** 
     * 运行一次任务 
     * @param allPushMessage 
     * @throws SchedulerException 
     * @throws Exception 
     */  
    public void runOnce() throws SchedulerException {
    	//TODO:XXXXXX
        JobKey jobKey = JobKey.jobKey("jobkey"); 
        Scheduler scheduler = schedulerFactoryBean.getScheduler();  
        scheduler.triggerJob(jobKey);
    } 
}