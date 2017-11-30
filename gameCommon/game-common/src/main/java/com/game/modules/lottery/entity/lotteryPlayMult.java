/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.modules.lottery.entity;

import com.game.common.persistence.DataEntity;

/**
 * 玩法多奖金Entity
 * @author Terry
 * @version 2017-11-15
 */
public class lotteryPlayMult extends DataEntity<lotteryPlayMult> {

	private static final long serialVersionUID = 1L;

	/**
	 * 彩种代码
	 */
	private String lotteryCode;

	/**
	 * 玩法代码
	 */
	private String playCode;

	/**
	 * 奖金键值
	 */
	private String number;

	/**
	 * 中奖概率
	 */
	private String winningProbability;

	/**
	 * 玩法说明
	 */
	private String explain;

	/**
	 * 玩法示例
	 */
	private String example;

	public lotteryPlayMult() {
		super();
	}

	public String getLotteryCode() {
		return lotteryCode;
	}

	public void setLotteryCode(String lotteryCode) {
		this.lotteryCode = lotteryCode;
	}

	public String getPlayCode() {
		return playCode;
	}

	public void setPlayCode(String playCode) {
		this.playCode = playCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getWinningProbability() {
		return winningProbability;
	}

	public void setWinningProbability(String winningProbability) {
		this.winningProbability = winningProbability;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}
}