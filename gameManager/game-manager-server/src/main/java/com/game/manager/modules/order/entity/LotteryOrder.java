/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.order.entity;

import org.hibernate.validator.constraints.Length;
import com.game.manager.modules.sys.entity.User;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.game.manager.common.persistence.DataEntity;

/**
 * 订单明细Entity
 * @author antonio
 * @version 2017-11-17
 */
public class LotteryOrder extends DataEntity<LotteryOrder> {
	
	private static final long serialVersionUID = 1L;
	private String orderNum;		// 订单编号
	private User user;		// user_id
	private String lotteryCode;		// lottery_code
	private String betIssueNo;		// 投注期号
	private String betType;		// 投注类型：比如重庆时时彩后三直选，不同的类型对应到不同的算奖模式
	private String betDetail;		// 投注内容
	private BigDecimal betAmount;		// 投注金额，单位为元
	private Integer betRate;		// 投注倍数
	private String playModeMoney;		// 奖金模式：1800,1700,1960，需要在服务端做校验
	private String playModeCommissionRate;		// 佣金比例，返点比例，返水比例需要在应用服务器做校验，看看奖金模式和返佣比率是否符合公式
	private String playModeMoneyType;		// 玩法模式：0元1角2分
	private String orderSource;		// 注单来源：1浏览器2移动app
	private String orderType;		// 注单类型：1正常投注2追号注单2合买注单
	private String schemaId;		// 方案外键：如果是追号、合买，查看注单详情的时候链接到对应的方案
	private BigDecimal winAmount;		// 中奖金额，单位为元
	private BigDecimal withdrawAmount;		// 撤单金额
	private String status;		// 注单状态：0等待开奖1已中奖2未中奖3已撤单
	
	public LotteryOrder() {
		super();
	}

	public LotteryOrder(String id){
		super(id);
	}

	@Length(min=0, max=50, message="订单编号长度必须介于 0 和 50 之间")
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	@NotNull(message="user_id不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=1, max=50, message="lottery_code长度必须介于 1 和 50 之间")
	public String getLotteryCode() {
		return lotteryCode;
	}

	public void setLotteryCode(String lotteryCode) {
		this.lotteryCode = lotteryCode;
	}
	
	@Length(min=1, max=50, message="投注期号长度必须介于 1 和 50 之间")
	public String getBetIssueNo() {
		return betIssueNo;
	}

	public void setBetIssueNo(String betIssueNo) {
		this.betIssueNo = betIssueNo;
	}
	
	@Length(min=1, max=50, message="投注类型：比如重庆时时彩后三直选，不同的类型对应到不同的算奖模式长度必须介于 1 和 50 之间")
	public String getBetType() {
		return betType;
	}

	public void setBetType(String betType) {
		this.betType = betType;
	}
	
	@Length(min=1, max=4500, message="投注内容长度必须介于 1 和 4500 之间")
	public String getBetDetail() {
		return betDetail;
	}

	public void setBetDetail(String betDetail) {
		this.betDetail = betDetail;
	}
	
	public BigDecimal getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(BigDecimal betAmount) {
		this.betAmount = betAmount;
	}
	
	@Length(min=1, max=6, message="投注倍数长度必须介于 1 和 6 之间")
	public Integer getBetRate() {
		return betRate;
	}

	public void setBetRate(Integer betRate) {
		this.betRate = betRate;
	}
	
	@Length(min=1, max=6, message="奖金模式：1800,1700,1960，需要在服务端做校验长度必须介于 1 和 6 之间")
	public String getPlayModeMoney() {
		return playModeMoney;
	}

	public void setPlayModeMoney(String playModeMoney) {
		this.playModeMoney = playModeMoney;
	}
	
	@Length(min=1, max=6, message="佣金比例，返点比例，返水比例需要在应用服务器做校验，看看奖金模式和返佣比率是否符合公式长度必须介于 1 和 6 之间")
	public String getPlayModeCommissionRate() {
		return playModeCommissionRate;
	}

	public void setPlayModeCommissionRate(String playModeCommissionRate) {
		this.playModeCommissionRate = playModeCommissionRate;
	}
	
	@Length(min=1, max=1, message="玩法模式：0元1角2分长度必须介于 1 和 1 之间")
	public String getPlayModeMoneyType() {
		return playModeMoneyType;
	}

	public void setPlayModeMoneyType(String playModeMoneyType) {
		this.playModeMoneyType = playModeMoneyType;
	}
	
	@Length(min=1, max=1, message="注单来源：1浏览器2移动app长度必须介于 1 和 1 之间")
	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}
	
	@Length(min=1, max=1, message="注单类型：1正常投注2追号注单2合买注单长度必须介于 1 和 1 之间")
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	@Length(min=0, max=50, message="方案外键：如果是追号、合买，查看注单详情的时候链接到对应的方案长度必须介于 0 和 50 之间")
	public String getSchemaId() {
		return schemaId;
	}

	public void setSchemaId(String schemaId) {
		this.schemaId = schemaId;
	}
	
	public BigDecimal getWinAmount() {
		return winAmount;
	}

	public void setWinAmount(BigDecimal winAmount) {
		this.winAmount = winAmount;
	}
	
	public BigDecimal getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(BigDecimal withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}
	
	@Length(min=1, max=1, message="注单状态：0等待开奖1已中奖2未中奖3已撤单长度必须介于 1 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}