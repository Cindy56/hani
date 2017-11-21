package com.game.hall.modules.bet.form;

import java.math.BigDecimal;

import com.game.hall.common.persistence.DataEntity;
import com.game.hall.modules.sys.entity.User;
import com.game.manager.modules.order.entity.LotteryOrder;

public class AddBetFormInput {



	/**
	 * @return the orderNum
	 */
	public String getOrderNum() {
		return orderNum;
	}
	/**
	 * @param orderNum the orderNum to set
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}
	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
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
	 * @return the betIssueNo
	 */
	public String getBetIssueNo() {
		return betIssueNo;
	}
	/**
	 * @param betIssueNo the betIssueNo to set
	 */
	public void setBetIssueNo(String betIssueNo) {
		this.betIssueNo = betIssueNo;
	}
	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return the betType
	 */
	public String getBetType() {
		return betType;
	}
	/**
	 * @param betType the betType to set
	 */
	public void setBetType(String betType) {
		this.betType = betType;
	}
	/**
	 * @return the betDetail
	 */
	public String getBetDetail() {
		return betDetail;
	}
	/**
	 * @param betDetail the betDetail to set
	 */
	public void setBetDetail(String betDetail) {
		this.betDetail = betDetail;
	}
	/**
	 * @return the betAmount
	 */
	public BigDecimal getBetAmount() {
		return betAmount;
	}
	/**
	 * @param betAmount the betAmount to set
	 */
	public void setBetAmount(BigDecimal betAmount) {
		this.betAmount = betAmount;
	}
	/**
	 * @return the betRate
	 */
	public Integer getBetRate() {
		return betRate;
	}
	/**
	 * @param betRate the betRate to set
	 */
	public void setBetRate(Integer betRate) {
		this.betRate = betRate;
	}
	/**
	 * @return the playModeMoney
	 */
	public String getPlayModeMoney() {
		return playModeMoney;
	}
	/**
	 * @param playModeMoney the playModeMoney to set
	 */
	public void setPlayModeMoney(String playModeMoney) {
		this.playModeMoney = playModeMoney;
	}
	/**
	 * @return the playModeCommissionRate
	 */
	public String getPlayModeCommissionRate() {
		return playModeCommissionRate;
	}
	/**
	 * @param playModeCommissionRate the playModeCommissionRate to set
	 */
	public void setPlayModeCommissionRate(String playModeCommissionRate) {
		this.playModeCommissionRate = playModeCommissionRate;
	}
	/**
	 * @return the playModeMoneyType
	 */
	public String getPlayModeMoneyType() {
		return playModeMoneyType;
	}
	/**
	 * @param playModeMoneyType the playModeMoneyType to set
	 */
	public void setPlayModeMoneyType(String playModeMoneyType) {
		this.playModeMoneyType = playModeMoneyType;
	}
	/**
	 * @return the orderSource
	 */
	public String getOrderSource() {
		return orderSource;
	}
	/**
	 * @param orderSource the orderSource to set
	 */
	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}
	/**
	 * @return the orderType
	 */
	public String getOrderType() {
		return orderType;
	}
	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	/**
	 * @return the schemaId
	 */
	public String getSchemaId() {
		return schemaId;
	}
	/**
	 * @param schemaId the schemaId to set
	 */
	public void setSchemaId(String schemaId) {
		this.schemaId = schemaId;
	}
	/**
	 * @return the winAmount
	 */
	public BigDecimal getWinAmount() {
		return winAmount;
	}
	/**
	 * @param winAmount the winAmount to set
	 */
	public void setWinAmount(BigDecimal winAmount) {
		this.winAmount = winAmount;
	}
	/**
	 * @return the withdrawAmount
	 */
	public BigDecimal getWithdrawAmount() {
		return withdrawAmount;
	}
	/**
	 * @param withdrawAmount the withdrawAmount to set
	 */
	public void setWithdrawAmount(BigDecimal withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
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
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private static final long serialVersionUID = 1L;
	private String orderNum;		// 订单编号
	private String userId;		// user_id
	private String userName;		// user_id	
	private String orgId;		// 机构id
	private String lotteryCode;		// lottery_code
	private String betIssueNo;		// 投注期号
	private String accountId;		// 账户id
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
	
	
}
