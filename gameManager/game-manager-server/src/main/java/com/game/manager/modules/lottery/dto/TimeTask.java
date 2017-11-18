package com.game.manager.modules.lottery.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.game.manager.common.persistence.DataEntity;

/**
 * 定时任务管理Entity
 * @author vinton
 * @version 2017-11-08
 */
public class TimeTask extends DataEntity<TimeTask> {
	
	private static final long serialVersionUID = 1L;
	private String lotteryCode;
	private String lotteryIssueNo;
	private Date runAtTime;
	private Date runStartTime;
	private Date runEndTime;
	
//	private String taskId;		// 任务bean_ID
//	private String taskDescribe;		// 任务描述
//	private String cronExpression;		// cron表达式
//	private String isEffect;		// 是否生效： 0未生效,1生效	
//	private String isStart;		// 是否运行：0停止,1运行
//	private String className;		// 任务类名
//	private String runServerIp;		// 任务运行服务器IP
//	private String runServer;		// 远程主机（域名/IP+项目路径）
	
	public TimeTask() {
		super();
	}

	public TimeTask(String id){
		super(id);
	}

	public String getLotteryCode() {
		return lotteryCode;
	}

	public void setLotteryCode(String lotteryCode) {
		this.lotteryCode = lotteryCode;
	}

	public String getLotteryIssueNo() {
		return lotteryIssueNo;
	}

	public void setLotteryIssueNo(String lotteryIssueNo) {
		this.lotteryIssueNo = lotteryIssueNo;
	}

	public Date getRunAtTime() {
		return runAtTime;
	}

	public void setRunAtTime(Date runAtTime) {
		this.runAtTime = runAtTime;
	}

	public Date getRunStartTime() {
		return runStartTime;
	}

	public void setRunStartTime(Date runStartTime) {
		this.runStartTime = runStartTime;
	}

	public Date getRunEndTime() {
		return runEndTime;
	}

	public void setRunEndTime(Date runEndTime) {
		this.runEndTime = runEndTime;
	}

}