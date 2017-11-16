/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.order.entity;

import com.game.manager.modules.sys.entity.User;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.game.manager.common.persistence.DataEntity;

/**
 * 订单明细Entity
 * @author antonio
 * @version 2017-11-16
 */
public class LotteryOrder extends DataEntity<LotteryOrder> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 用户ID
	private String lotteryCode;		// 彩票代码
	private String betIssueNo;		// 投注期号
	private String betType;		// 投注类型
	private String betDetail;		// 投注内容
	private String betAmount;		// 投注金额
	private String betRate;		// 投注倍数
	private String playModeMoney;		// 奖金模式
	private String playModeCommissionRate;		// 佣金比例
	private String playModeMoneyType;		// 玩法模式
	private String orderSource;		// 注单来源
	private String orderType;		// 注单类型
	private String schemaId;		// 方案外键
	private String winAmount;		// 中奖金额
	private String withdrawAmount;		// 撤单金额
	private String status;		// 注单状态
	
	public LotteryOrder() {
		super();
	}

	public LotteryOrder(String id){
		super(id);
	}

	@NotNull(message="用户ID不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=1, max=50, message="彩票代码长度必须介于 1 和 50 之间")
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
	
	@Length(min=1, max=50, message="投注类型长度必须介于 1 和 50 之间")
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
	
	public String getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(String betAmount) {
		this.betAmount = betAmount;
	}
	
	@Length(min=1, max=6, message="投注倍数长度必须介于 1 和 6 之间")
	public String getBetRate() {
		return betRate;
	}

	public void setBetRate(String betRate) {
		this.betRate = betRate;
	}
	
	@Length(min=1, max=6, message="奖金模式长度必须介于 1 和 6 之间")
	public String getPlayModeMoney() {
		return playModeMoney;
	}

	public void setPlayModeMoney(String playModeMoney) {
		this.playModeMoney = playModeMoney;
	}
	
	@Length(min=1, max=6, message="佣金比例长度必须介于 1 和 6 之间")
	public String getPlayModeCommissionRate() {
		return playModeCommissionRate;
	}

	public void setPlayModeCommissionRate(String playModeCommissionRate) {
		this.playModeCommissionRate = playModeCommissionRate;
	}
	
	@Length(min=1, max=1, message="玩法模式长度必须介于 1 和 1 之间")
	public String getPlayModeMoneyType() {
		return playModeMoneyType;
	}

	public void setPlayModeMoneyType(String playModeMoneyType) {
		this.playModeMoneyType = playModeMoneyType;
	}
	
	@Length(min=1, max=1, message="注单来源长度必须介于 1 和 1 之间")
	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}
	
	@Length(min=1, max=1, message="注单类型长度必须介于 1 和 1 之间")
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	@Length(min=0, max=50, message="方案外键长度必须介于 0 和 50 之间")
	public String getSchemaId() {
		return schemaId;
	}

	public void setSchemaId(String schemaId) {
		this.schemaId = schemaId;
	}
	
	public String getWinAmount() {
		return winAmount;
	}

	public void setWinAmount(String winAmount) {
		this.winAmount = winAmount;
	}
	
	public String getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(String withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}
	
	@Length(min=1, max=1, message="注单状态长度必须介于 1 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}