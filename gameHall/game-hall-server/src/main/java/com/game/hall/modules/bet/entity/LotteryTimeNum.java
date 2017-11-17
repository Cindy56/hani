/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.hall.modules.bet.entity;

import java.util.Date;

import com.game.manager.common.persistence.DataEntity;

/**
 * test1Entity
 * 
 * @author test1
 * @version 2017-11-14
 */

// extends DataEntity<LotteryTimeNum>
public class LotteryTimeNum extends DataEntity<LotteryTimeNum> {

	private String id;
	private String lotteryCode; // 彩票代码
	private String lotteryIssueNo; // 开奖期号 
	private Date betStartDate; // 投注开始时间：只是用来记录由于存在封单时间，封单开始，用户就可以投注下一期，所以真实投注时间大于这个如果追号，也相当于提前投注
	private Date betEndDate; // 投注截止时间，用于系统计算，如果当前时间晚于（截止时间-封单时间），就拒绝投注
	private Date betHaltDate; // 封单时间，单位为秒
	private String drawNum; // 当期开奖号码
	private String drawNumDetail; // 开奖号码生成的玩法详情。暂时预留，方便以后出走势图
	private String status; // 状态：0等待开奖1已经开奖2人工开奖3未开奖，人工撤单
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the lotteryCode
	 */
	public String getLotteryCode() {
		return lotteryCode;
	}
	/**
	 * @param lotteryCode the lotteryCode to set
	 */
	public void setLotteryCode(String lotteryCode) {
		this.lotteryCode = lotteryCode;
	}
	/**
	 * @return the lotteryIssueNo
	 */
	public String getLotteryIssueNo() {
		return lotteryIssueNo;
	}
	/**
	 * @param lotteryIssueNo the lotteryIssueNo to set
	 */
	public void setLotteryIssueNo(String lotteryIssueNo) {
		this.lotteryIssueNo = lotteryIssueNo;
	}
	/**
	 * @return the betStartDate
	 */
	public Date getBetStartDate() {
		return betStartDate;
	}
	/**
	 * @param betStartDate the betStartDate to set
	 */
	public void setBetStartDate(Date betStartDate) {
		this.betStartDate = betStartDate;
	}
	/**
	 * @return the betEndDate
	 */
	public Date getBetEndDate() {
		return betEndDate;
	}
	/**
	 * @param betEndDate the betEndDate to set
	 */
	public void setBetEndDate(Date betEndDate) {
		this.betEndDate = betEndDate;
	}
	/**
	 * @return the betHaltDate
	 */
	public Date getBetHaltDate() {
		return betHaltDate;
	}
	/**
	 * @param betHaltDate the betHaltDate to set
	 */
	public void setBetHaltDate(Date betHaltDate) {
		this.betHaltDate = betHaltDate;
	}
	/**
	 * @return the drawNum
	 */
	public String getDrawNum() {
		return drawNum;
	}
	/**
	 * @param drawNum the drawNum to set
	 */
	public void setDrawNum(String drawNum) {
		this.drawNum = drawNum;
	}
	/**
	 * @return the drawNumDetail
	 */
	public String getDrawNumDetail() {
		return drawNumDetail;
	}
	/**
	 * @param drawNumDetail the drawNumDetail to set
	 */
	public void setDrawNumDetail(String drawNumDetail) {
		this.drawNumDetail = drawNumDetail;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	
}