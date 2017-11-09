/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.lottery.entity;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.alibaba.fastjson.JSON;
import com.game.manager.common.persistence.DataEntity;

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

    @Pattern(regexp = "\\w{1, 50}", message = "彩种代码长度必须介于 1 和 50 之间，且必须是数字或字母")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Pattern(regexp = "\\w{1, 50}", message = "彩种类型长度必须介于 1 和 50 之间，且必须是数字或字母")
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    @Length(min = 1, max = 50, message = "彩种名称长度必须介于 1 和 50 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 1, max = 1, message = "是否自动开奖长度必须为 1")
    public String getIsAuto() {
        return isAuto;
    }

    public void setIsAuto(String isAuto) {
        this.isAuto = isAuto;
    }

    @Length(min = 1, max = 1, message = "是否有效长度必须为 1")
    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    @Pattern(regexp = "[0-1]?[0-9]{1}:{1}[0-5]{1}[0-9]{1}", message = "每日开售时刻格式有误，正确格式：HH:mm")
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Pattern(regexp = "[0-1]?[0-9]{1}:{1}[0-5]{1}[0-9]{1}", message = "每日停售时刻格式有误，正确格式：HH:mm")
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Pattern(regexp = "\\d{1, 6}", message = "每日期数长度必须介于 1 和 6 之间，且必须为数字")
    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    @Pattern(regexp = "\\d{1, 255}", message = "开奖周期长度必须介于 1 和 6 之间，且必须为数字")
    public String getPeriodTotalTime() {
        return periodTotalTime;
    }

    public void setPeriodTotalTime(String periodTotalTime) {
        this.periodTotalTime = periodTotalTime;
    }

    @Pattern(regexp = "\\d{1, 255}", message = "封单时间长度必须介于 1 和 255 之间，且必须为数字")
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
        this.amountMaxBet = amountMaxBet;
    }

    @Length(min = 0, max = 50, message = "当前期号长度必须介于 0 和 50 之间")
    public String getCurrentIssueNo() {
        return currentIssueNo;
    }

    public void setCurrentIssueNo(String currentIssueNo) {
        this.currentIssueNo = currentIssueNo;
    }

    @Length(min = 0, max = 50, message = "下期期号长度必须介于 0 和 50 之间")
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