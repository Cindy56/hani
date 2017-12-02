/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.modules.lottery.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.Length;

import com.game.common.persistence.DataEntity;

/**
 * 玩法基本信息管理Entity
 * @author Terry
 * @version 2017-11-15
 */
public class LotteryPlayConfig extends DataEntity<LotteryPlayConfig> {

	private static final long serialVersionUID = 1L;

	/**
	 * 彩票代码 父类
	 */
	private LotteryType lotteryCode;

	/**
	 * 玩法代码
	 */
	private String playCode;

	/**
	 * 玩法名称
	 */
	private String name;

	/**
	 * 玩法模式
	 */
	private String playType;

	/**
	 * 中奖概率
	 */
	private String winningProbability;

	/**
	 * 多奖金模式下存储多概率数据，json格式，大字段
	 */
	private String lotteryPlayMult;

	/**
	 * 返水级别
	 */
	private BigDecimal commissionRateMax;

	/**
	 * 最低返水级别
	 */
	private BigDecimal commissionRateMin;

	/**
	 * 单注金额
	 */
	private Integer betUnit;

	/**
	 * 单人单期投注倍数限制
	 */
	private Integer betRateLimit;

	/**
	 * 是否启用
	 */
	private String isEnable;

	/**
	 * 玩法说明
	 */
	private String explain;

	/**
	 * 玩法示例
	 */
	private String example;
	/**
	 * 返点信息
	 */
	private Map<String, List<Map<String, Object>>> map;

	/**
	 * 玩法多奖金数据页面封装数据
	 */
	private List<lotteryPlayMult> lotteryPlayMultList;

	public Map<String, List<Map<String, Object>>> getMap() {
		return map;
	}

	public void setMap(Map<String, List<Map<String, Object>>> map) {
		this.map = map;
	}

	public LotteryPlayConfig() {
		super();
	}

	public LotteryPlayConfig(String id) {
		super(id);
	}

	public LotteryPlayConfig(LotteryType lotteryCode) {
		this.lotteryCode = lotteryCode;
	}

	public LotteryType getLotteryCode() {
		return lotteryCode;
	}

	public void setLotteryCode(LotteryType lotteryCode) {
		this.lotteryCode = lotteryCode;
	}

	@Length(min = 1, max = 50, message = "玩法代码长度必须介于 1 和 50 之间")
	public String getPlayCode() {
		return playCode;
	}

	public void setPlayCode(String playCode) {
		this.playCode = playCode;
	}

	@Length(min = 1, max = 50, message = "玩法名称长度必须介于 1 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min = 1, max = 1, message = "玩法模式长度必须介于 1 和 1 之间")
	public String getPlayType() {
		return playType;
	}

	public void setPlayType(String playType) {
		this.playType = playType;
	}

	public String getWinningProbability() {
		return winningProbability;
	}

	public void setWinningProbability(String winningProbability) {
		this.winningProbability = winningProbability;
	}

	public String getLotteryPlayMult() {
		return lotteryPlayMult;
	}

	public void setLotteryPlayMult(String lotteryPlayMult) {
		this.lotteryPlayMult = lotteryPlayMult;
	}

	public BigDecimal getCommissionRateMax() {
		return commissionRateMax;
	}

	public void setCommissionRateMax(BigDecimal commissionRateMax) {
		this.commissionRateMax = commissionRateMax;
	}

	public BigDecimal getCommissionRateMin() {
		return commissionRateMin;
	}

	public void setCommissionRateMin(BigDecimal commissionRateMin) {
		this.commissionRateMin = commissionRateMin;
	}

	public Integer getBetUnit() {
		return betUnit;
	}

	public void setBetUnit(Integer betUnit) {
		this.betUnit = betUnit;
	}

	public Integer getBetRateLimit() {
		return betRateLimit;
	}

	public void setBetRateLimit(Integer betRateLimit) {
		this.betRateLimit = betRateLimit;
	}

	@Length(min = 0, max = 1, message = "是否启用长度必须介于 0 和 1 之间")
	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	@Length(min = 0, max = 5000, message = "玩法说明长度必须介于 0 和 5000 之间")
	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	@Length(min = 0, max = 5000, message = "玩法实例长度必须介于 0 和 5000 之间")
	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public List<lotteryPlayMult> getLotteryPlayMultList() {
		return lotteryPlayMultList;
	}

	public void setLotteryPlayMultList(List<lotteryPlayMult> lotteryPlayMultList) {
		this.lotteryPlayMultList = lotteryPlayMultList;
	}
}