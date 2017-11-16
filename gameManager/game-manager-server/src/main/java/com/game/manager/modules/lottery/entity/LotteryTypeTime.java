/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.lottery.entity;

import org.hibernate.validator.constraints.Length;

import com.game.manager.common.persistence.DataEntity;

/**
 * 彩种基本信息管理Entity
 * @author Terry
 * @version 2017-11-15
 */
public class LotteryTypeTime extends DataEntity<LotteryTypeTime> {

    private static final long serialVersionUID = 1L;
    private LotteryType lotteryTypeId; // 彩票主键id 父类
    private String lotteryCode; // 彩票代码
    private String startTime; // 开始时间
    private String endTime; // 截止时间
    private String periodTotalTime; // 开奖周期时间
    private String periodHaltTime; // 每期封单时间

    public LotteryTypeTime() {
        super();
    }

    public LotteryTypeTime(String id) {
        super(id);
    }

    public LotteryTypeTime(LotteryType lotteryTypeId) {
        this.lotteryTypeId = lotteryTypeId;
    }

    public LotteryType getLotteryTypeId() {
        return lotteryTypeId;
    }

    public void setLotteryTypeId(LotteryType lotteryTypeId) {
        this.lotteryTypeId = lotteryTypeId;
    }

    @Length(min = 1, max = 50, message = "彩票代码长度必须介于 1 和 50 之间")
    public String getLotteryCode() {
        return lotteryCode;
    }

    public void setLotteryCode(String lotteryCode) {
        this.lotteryCode = lotteryCode;
    }

    @Length(min = 1, max = 10, message = "开始时间长度必须介于 1 和 10 之间")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Length(min = 1, max = 10, message = "截止时间长度必须介于 1 和 10 之间")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Length(min = 1, max = 6, message = "开奖周期时间长度必须介于 1 和 6 之间")
    public String getPeriodTotalTime() {
        return periodTotalTime;
    }

    public void setPeriodTotalTime(String periodTotalTime) {
        this.periodTotalTime = periodTotalTime;
    }

    @Length(min = 1, max = 6, message = "每期封单时间长度必须介于 1 和 6 之间")
    public String getPeriodHaltTime() {
        return periodHaltTime;
    }

    public void setPeriodHaltTime(String periodHaltTime) {
        this.periodHaltTime = periodHaltTime;
    }

}