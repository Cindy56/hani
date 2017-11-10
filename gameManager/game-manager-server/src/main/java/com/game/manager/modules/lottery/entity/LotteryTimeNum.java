/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.lottery.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.game.manager.common.persistence.DataEntity;

/**
 * 开奖时刻和开奖结果Entity
 * @author jerry
 * @version 2017-11-10
 */
public class LotteryTimeNum extends DataEntity<LotteryTimeNum> {
	
	private static final long serialVersionUID = 1L;
	private String lotteryCode;		// 彩票代码
	private String lotteryIssueNo;		// 开奖期号
	private Date betStartGmt;		// 投注开始时间
	private Date betEndGmt;		// 投注截止时间
	private Integer betHaltTime;		// 封单时间(秒)
	private String drawNum;		// 当期开奖号码
	private String drawNumDetail;		// 开奖号码生成的玩法详情。暂时预留，方便以后出走势图
	private String status;		// 状态：0等待开奖1已经开奖2人工开奖3未开奖，人工撤单
	
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
	@NotNull(message="投注开始时间不能为空")
	public Date getBetStartGmt() {
		return betStartGmt;
	}

	public void setBetStartGmt(Date betStartGmt) {
		this.betStartGmt = betStartGmt;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="投注截止时间不能为空")
	public Date getBetEndGmt() {
		return betEndGmt;
	}

	public void setBetEndGmt(Date betEndGmt) {
		this.betEndGmt = betEndGmt;
	}
	
	@NotNull(message="封单时间(秒)不能为空")
	public Integer getBetHaltTime() {
		return betHaltTime;
	}

	public void setBetHaltTime(Integer betHaltTime) {
		this.betHaltTime = betHaltTime;
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
	
	@Length(min=1, max=1, message="状态人工撤单长度必须介于 1 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}