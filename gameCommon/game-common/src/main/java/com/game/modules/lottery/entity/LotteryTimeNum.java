/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.modules.lottery.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.game.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;

/**
 * 开奖时刻和开奖结果Entity
 * @author jerry
 * @version 2017-11-15
 */
public class LotteryTimeNum extends DataEntity<LotteryTimeNum> {
	
	private static final long serialVersionUID = 7891788858972610660L;
	private String lotteryCode;		// 彩票代码
	private String lotteryIssueNo;		// 开奖期号
	private Date betStartDate;		// 投注开始时间：只是用来记录由于存在封单时间，封单开始，用户就可以投注下一期，所以真实投注时间大于这个如果追号，也相当于提前投注
	private Date betEndDate;		// 投注截止时间，用于系统计算，如果当前时间晚于（截止时间-封单时间），就拒绝投注
	private Date betHaltDate;		// 封单时间，单位为秒
	private String openNum;		// 当期开奖号码
	private Date openDate;		// 开奖日期
	private String openTrend;		// 开奖号码生成的玩法详情。暂时预留，方便以后出走势图
	private String status;		// 状态：0等待开奖1已经开奖2人工开奖3未开奖，人工撤单
	private String nextIssueNo;		// 下期期号
	private Date nextHaltDate;		// 下期封单时间
	
	public LotteryTimeNum() {
		super();
	}

	public LotteryTimeNum(String id){
		super(id);
	}

	@Length(min=1, max=50, message="彩票代码长度必须介于 1 和 50 之间")
	public String getLotteryCode() {
		return lotteryCode;
	}

	public void setLotteryCode(String lotteryCode) {
		this.lotteryCode = lotteryCode;
	}
	
	@Length(min=1, max=50, message="开奖期号长度必须介于 1 和 50 之间")
	public String getLotteryIssueNo() {
		return lotteryIssueNo;
	}

	public void setLotteryIssueNo(String lotteryIssueNo) {
		this.lotteryIssueNo = lotteryIssueNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="投注开始时间：只是用来记录由于存在封单时间，封单开始，用户就可以投注下一期，所以真实投注时间大于这个如果追号，也相当于提前投注不能为空")
	public Date getBetStartDate() {
		return betStartDate;
	}

	public void setBetStartDate(Date betStartDate) {
		this.betStartDate = betStartDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="投注截止时间，用于系统计算，如果当前时间晚于（截止时间-封单时间），就拒绝投注不能为空")
	public Date getBetEndDate() {
		return betEndDate;
	}

	public void setBetEndDate(Date betEndDate) {
		this.betEndDate = betEndDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="封单时间，单位为秒不能为空")
	public Date getBetHaltDate() {
		return betHaltDate;
	}

	public void setBetHaltDate(Date betHaltDate) {
		this.betHaltDate = betHaltDate;
	}
	
	@Length(min=0, max=50, message="当期开奖号码长度必须介于 0 和 50 之间")
	public String getOpenNum() {
		return openNum;
	}

	public void setOpenNum(String openNum) {
		this.openNum = openNum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	
	public String getOpenTrend() {
		return openTrend;
	}

	public void setOpenTrend(String openTrend) {
		this.openTrend = openTrend;
	}
	
	@Length(min=1, max=1, message="状态：0等待开奖1已经开奖2人工开奖3未开奖，人工撤单长度必须介于 1 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=50, message="下期期号长度必须介于 0 和 50 之间")
	public String getNextIssueNo() {
		return nextIssueNo;
	}

	public void setNextIssueNo(String nextIssueNo) {
		this.nextIssueNo = nextIssueNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getNextHaltDate() {
		return nextHaltDate;
	}

	public void setNextHaltDate(Date nextHaltDate) {
		this.nextHaltDate = nextHaltDate;
	}
	
}