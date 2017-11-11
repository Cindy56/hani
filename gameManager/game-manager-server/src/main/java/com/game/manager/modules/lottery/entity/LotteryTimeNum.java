/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.lottery.entity;

import java.util.Date;

import com.game.manager.common.persistence.DataEntity;

/**
 * 开奖时刻和开奖结果Entity
 * @author jerry
 * @version 2017-11-11
 */
public class LotteryTimeNum extends DataEntity<LotteryTimeNum> {
	
	private static final long serialVersionUID = 1L;
	private String lotteryCode;		// 彩票代码
	private String lotteryIssueNo;		// 开奖期号
	private Date betStartDate;		// 投注开始时间：只是用来记录由于存在封单时间，封单开始，用户就可以投注下一期，所以真实投注时间大于这个如果追号，也相当于提前投注
	private Date betEndDate;		// 投注截止时间，用于系统计算，如果当前时间晚于（截止时间-封单时间），就拒绝投注
	private Date betHaltDate;		// 封单时间，单位为秒
	private String drawNum;		// 当期开奖号码
	private String drawNumDetail;		// 开奖号码生成的玩法详情。暂时预留，方便以后出走势图
	private String status;		// 状态：0等待开奖1已经开奖2人工开奖3未开奖，人工撤单
	
	public LotteryTimeNum() {
		super();
	}

	public LotteryTimeNum(String id){
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
	
	public Date getBetStartDate() {
		return betStartDate;
	}

	public void setBetStartDate(Date betStartDate) {
		this.betStartDate = betStartDate;
	}
	
	public Date getBetEndDate() {
		return betEndDate;
	}

	public void setBetEndDate(Date betEndDate) {
		this.betEndDate = betEndDate;
	}
	
	public Date getBetHaltDate() {
		return betHaltDate;
	}

	public void setBetHaltDate(Date betHaltDate) {
		this.betHaltDate = betHaltDate;
	}
	
	public String getDrawNum() {
		return drawNum;
	}

	public void setDrawNum(String drawNum) {
		this.drawNum = drawNum;
	}
	
	public String getDrawNumDetail() {
		return drawNumDetail;
	}

	public void setDrawNumDetail(String drawNumDetail) {
		this.drawNumDetail = drawNumDetail;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}