package com.game.hall.modules.bet.entity;

import java.math.BigDecimal;



public class BetData {

	/**
	 * @return the lotteryCode
	 */
	public String getLotteryCode() {
		return lotteryCode;
	}

	/**
	 * @param lotteryCode
	 *            the lotteryCode to set
	 */
	public void setLotteryCode(String lotteryCode) {
		this.lotteryCode = lotteryCode;
	}

	/**
	 * @return the playDetailCode
	 */
	public String getPlayDetailCode() {
		return playDetailCode;
	}

	/**
	 * @param playDetailCode
	 *            the playDetailCode to set
	 */
	public void setPlayDetailCode(String playDetailCode) {
		this.playDetailCode = playDetailCode;
	}

	/**
	 * @return the bettingIssuseNo
	 */
	public String getBettingIssuseNo() {
		return bettingIssuseNo;
	}

	/**
	 * @param bettingIssuseNo
	 *            the bettingIssuseNo to set
	 */
	public void setBettingIssuseNo(String bettingIssuseNo) {
		this.bettingIssuseNo = bettingIssuseNo;
	}

	/**
	 * @return the bettingNumber
	 */
	public String getBettingNumber() {
		return bettingNumber;
	}

	/**
	 * @param bettingNumber
	 *            the bettingNumber to set
	 */
	public void setBettingNumber(String bettingNumber) {
		this.bettingNumber = bettingNumber;
	}

	/**
	 * @return the bettingCount
	 */
	public BigDecimal getBettingCount() {
		return bettingCount;
	}

	/**
	 * @param bettingCount
	 *            the bettingCount to set
	 */
	public void setBettingCount(BigDecimal bettingCount) {
		this.bettingCount = bettingCount;
	}

	/**
	 * @return the graduationCount
	 */
	public BigDecimal getGraduationCount() {
		return graduationCount;
	}

	/**
	 * @param graduationCount
	 *            the graduationCount to set
	 */
	public void setGraduationCount(BigDecimal graduationCount) {
		this.graduationCount = graduationCount;
	}

	/**
	 * @return the bettingMoney
	 */
	public BigDecimal getBettingMoney() {
		return bettingMoney;
	}

	/**
	 * @param bettingMoney
	 *            the bettingMoney to set
	 */
	public void setBettingMoney(BigDecimal bettingMoney) {
		this.bettingMoney = bettingMoney;
	}

	/**
	 * @return the bettingPoint
	 */
	public BigDecimal getBettingPoint() {
		return bettingPoint;
	}

	/**
	 * @param bettingPoint
	 *            the bettingPoint to set
	 */
	public void setBettingPoint(BigDecimal bettingPoint) {
		this.bettingPoint = bettingPoint;
	}

	/**
	 * @return the bettingModel
	 */
	public BigDecimal getBettingModel() {
		return bettingModel;
	}

	/**
	 * @param bettingModel
	 *            the bettingModel to set
	 */
	public void setBettingModel(BigDecimal bettingModel) {
		this.bettingModel = bettingModel;
	}

	private String lotteryCode; // 彩种编码 ,"1000"
	private String playDetailCode; // 玩法编码,"1000D11"
	private String bettingIssuseNo; // 期号 "20171113034"
	private String bettingNumber; // 投注号 "2,2,2"
	private BigDecimal bettingCount; // 投注数 1
	private BigDecimal graduationCount; // 倍数 1
	private BigDecimal bettingMoney; // 投注金额 2
	private BigDecimal bettingPoint; // 返点" "1900.00-0.0"
	private BigDecimal bettingModel; // 玩法模式 1

}
