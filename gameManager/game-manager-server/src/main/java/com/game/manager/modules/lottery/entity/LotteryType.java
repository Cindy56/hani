/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.lottery.entity;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.alibaba.fastjson.JSON;
import com.game.manager.common.persistence.DataEntity;
import com.game.manager.common.utils.StringUtils;
import com.game.manager.modules.lottery.constant.LotteryConstants;
import com.game.manager.modules.lottery.constant.RegexConstants;

/**
 * 彩种基本信息管理Entity
 * @author Terry
 * @version 2017-11-07
 */
public class LotteryType extends DataEntity<LotteryType> {

    private static final long serialVersionUID = 1L;

    /**
     * 彩种代码
     */
    private String code;

    /**
     * 彩种名称
     */
    private String name;

    /**
     * 彩种类型
     */
    private String parentCode;

    /**
     * 是否自动开奖
     */
    private String isAuto;

    /**
     * 是否有效
     */
    private String isEnable;

    /**
     * 每日开售时间
     */
    private String startDate;

    /**
     * 每日停售时间
     */
    private String endDate;

    /**
     * 每日期数
     */
    private String times;

    /**
     * 开奖周期
     */
    private String periodTotalTime;

    /**
     * 封单时间
     */
    private String periodHaltTime;

    /**
     * 每期投注最高金额
     */
    private String amountMaxBet;

    /**
     * 当前期号
     */
    private String currentIssueNo;

    /**
     * 下期期号
     */
    private String nextIssueNo;

    public LotteryType() {
        super();
    }

    public LotteryType(String id) {
        super(id);
    }

    @Pattern(regexp = RegexConstants.LETTER_AND_NUMBER_1_50, message = LotteryConstants.CODE_CHECK_MESSAGE)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Pattern(regexp = RegexConstants.LETTER_AND_NUMBER_1_50, message = LotteryConstants.PARENTCODE_CHECK_MESSAGE)
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    @Length(min = RegexConstants.LENGTH_1, max = RegexConstants.LENGTH_50, message = LotteryConstants.NAME_CHECK_MESSAGE)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = RegexConstants.LENGTH_1, max = RegexConstants.LENGTH_1, message = LotteryConstants.ISAUTO_CHECK_MESSAGE)
    public String getIsAuto() {
        return isAuto;
    }

    public void setIsAuto(String isAuto) {
        this.isAuto = isAuto;
    }

    @Length(min = RegexConstants.LENGTH_1, max = RegexConstants.LENGTH_1, message = LotteryConstants.ISENABLE_CHECK_MESSAGE)
    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    @Pattern(regexp = RegexConstants.TIME_HH_MM, message = LotteryConstants.STARTDATE_CHECK_MESSAGE)
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Pattern(regexp = RegexConstants.TIME_HH_MM, message = LotteryConstants.ENDDATE_CHECK_MESSAGE)
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Pattern(regexp = RegexConstants.NUM_1_6, message = LotteryConstants.TIMES_CHECK_MESSAGE)
    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    @Pattern(regexp = RegexConstants.NUM_1_255, message = LotteryConstants.PERIODTOTALTIME_CHECK_MESSAGE)
    public String getPeriodTotalTime() {
        return periodTotalTime;
    }

    public void setPeriodTotalTime(String periodTotalTime) {
        this.periodTotalTime = periodTotalTime;
    }

    @Pattern(regexp = RegexConstants.NUM_1_255, message = LotteryConstants.PERIODHALTTIMEE_CHECK_MESSAGE)
    public String getPeriodHaltTime() {
        return periodHaltTime;
    }

    public void setPeriodHaltTime(String periodHaltTime) {
        this.periodHaltTime = periodHaltTime;
    }

    public String getAmountMaxBet() {
        return amountMaxBet;
    }

    public void setAmountMaxBet(String amountMaxBet) {
        if (StringUtils.isBlank(amountMaxBet)) {
            amountMaxBet = "0";
        }
        this.amountMaxBet = amountMaxBet;
    }

    @Length(min = RegexConstants.LENGTH_0, max = RegexConstants.LENGTH_50, message = LotteryConstants.CURRENTISSUENO_CHECK_MESSAGE)
    public String getCurrentIssueNo() {
        return currentIssueNo;
    }

    public void setCurrentIssueNo(String currentIssueNo) {
        this.currentIssueNo = currentIssueNo;
    }

    @Length(min = RegexConstants.LENGTH_0, max = RegexConstants.LENGTH_50, message = LotteryConstants.NEXTISSUNO_CHECK_MESSAGE)
    public String getNextIssueNo() {
        return nextIssueNo;
    }

    public void setNextIssueNo(String nextIssueNo) {
        this.nextIssueNo = nextIssueNo;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}