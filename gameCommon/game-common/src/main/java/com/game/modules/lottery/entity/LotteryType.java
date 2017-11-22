/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.modules.lottery.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.game.common.persistence.DataEntity;
import com.google.common.collect.Lists;

/**
 * 彩种基本信息管理Entity
 * @author Terry
 * @version 2017-11-15
 */
public class LotteryType extends DataEntity<LotteryType> {
	private static final long serialVersionUID = 3677821608048480817L;

	/**
     * 彩种类型
     */
    private String parentCode;

    /**
     * 彩种代码
     */
    private String code;

    /**
     * 公司ID
     */
    private String companyId;

    /**
     * 彩种名称
     */
    private String name;

    /**
     * 是否自动开奖
     */
    private String isAuto;

    /**
     * 是否启用
     */
    private String isEnable;

    /**
     * 每日开奖期数
     */
    private String times;

    /**
     * 每期投注最高金额
     */
    private String amountMaxBet;

    /**
     * 子表列表
     */
    private List<LotteryTypeTime> lotteryTypeTimeList = Lists.newArrayList();

    public LotteryType() {
        super();
    }

    public LotteryType(String id) {
        super(id);
    }

    @Length(min = 1, max = 50, message = "彩种类型长度必须介于 1 和 50 之间")
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    @Length(min = 1, max = 50, message = "彩种代码长度必须介于 1 和 50 之间")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Length(min = 1, max = 50, message = "公司ID长度必须介于 1 和 50 之间")
    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Length(min = 1, max = 50, message = "彩种名称长度必须介于 1 和 50 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 1, max = 1, message = "是否自动开奖长度必须介于 1 和 1 之间")
    public String getIsAuto() {
        return isAuto;
    }

    public void setIsAuto(String isAuto) {
        this.isAuto = isAuto;
    }

    @Length(min = 1, max = 1, message = "是否启用长度必须介于 1 和 1 之间")
    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    @Length(min = 1, max = 6, message = "每日开奖期数长度必须介于 1 和 6 之间")
    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getAmountMaxBet() {
        return amountMaxBet;
    }

    public void setAmountMaxBet(String amountMaxBet) {
        this.amountMaxBet = amountMaxBet;
    }

    public List<LotteryTypeTime> getLotteryTypeTimeList() {
        return lotteryTypeTimeList;
    }

    public void setLotteryTypeTimeList(List<LotteryTypeTime> lotteryTypeTimeList) {
        this.lotteryTypeTimeList = lotteryTypeTimeList;
    }

}