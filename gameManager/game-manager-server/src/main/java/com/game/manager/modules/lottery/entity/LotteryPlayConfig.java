/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.lottery.entity;

import org.hibernate.validator.constraints.Length;

import com.game.manager.common.persistence.DataEntity;

/**
 * 彩票玩法管理Entity
 * @author Terry
 * @version 2017-11-10
 */
public class LotteryPlayConfig extends DataEntity<LotteryPlayConfig> {

    private static final long serialVersionUID = 1L;

    /**
     * 彩票代码
     */
    private String lotteryCode;

    /**
     * 彩票名称
     */
    private String lotteryName;

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
     * 返水级别
     */
    private String commissionRate;

    /**
     * 单人单期投注倍数限制
     */
    private String betRateLimit;

    /**
     * 玩法说明
     */
    private String explain;

    /**
     * 玩法实例
     */
    private String example;

    public LotteryPlayConfig() {
        super();
    }

    public LotteryPlayConfig(String id) {
        super(id);
    }

    @Length(min = 1, max = 50, message = "彩票代码长度必须介于 1 和 50 之间")
    public String getLotteryCode() {
        return lotteryCode;
    }

    public void setLotteryCode(String lotteryCode) {
        this.lotteryCode = lotteryCode;
    }

    @Length(min = 1, max = 50, message = "玩法代码长度必须介于 1 和 50 之间")
    public String getPlayCode() {
        return playCode;
    }

    public void setPlayCode(String playCode) {
        this.playCode = playCode;
    }

    @Length(min = 1, max = 500, message = "玩法名称长度必须介于 1 和 500 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 1, max = 6, message = "玩法模式长度必须介于 1 和 6 之间")
    public String getPlayType() {
        return playType;
    }

    public void setPlayType(String playType) {
        this.playType = playType;
    }

    @Length(min = 0, max = 50, message = "中奖概率长度必须介于 0 和 50 之间")
    public String getWinningProbability() {
        return winningProbability;
    }

    public void setWinningProbability(String winningProbability) {
        this.winningProbability = winningProbability;
    }

    @Length(min = 0, max = 6, message = "返水级别长度必须介于 0 和 6 之间")
    public String getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(String commissionRate) {
        this.commissionRate = commissionRate;
    }

    @Length(min = 0, max = 4, message = "单人单期投注倍数限制长度必须介于 0 和 4 之间")
    public String getBetRateLimit() {
        return betRateLimit;
    }

    public void setBetRateLimit(String betRateLimit) {
        this.betRateLimit = betRateLimit;
    }

    @Length(min = 0, max = 500, message = "玩法说明长度必须介于 0 和 500 之间")
    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    @Length(min = 0, max = 500, message = "玩法实例长度必须介于 0 和 500 之间")
    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getLotteryName() {
        return lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
    }
}