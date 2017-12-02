/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.companylottery.entity;

import org.hibernate.validator.constraints.Length;

import com.game.common.persistence.DataEntity;

/**
 * 公司彩票配置Entity
 * @author Terry
 * @version 2017-12-02
 */
public class CompanyLottery extends DataEntity<CompanyLottery> {
	
	private static final long serialVersionUID = 1L;
	private String companyId;		// 公司id
	private String lotteryCode;		// 彩票代码
	private String lotteryPlay;		// 彩票玩法
	
	public CompanyLottery() {
		super();
	}

	public CompanyLottery(String id){
		super(id);
	}

	@Length(min=1, max=50, message="公司id长度必须介于 1 和 50 之间")
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	@Length(min=1, max=50, message="彩票代码长度必须介于 1 和 50 之间")
	public String getLotteryCode() {
		return lotteryCode;
	}

	public void setLotteryCode(String lotteryCode) {
		this.lotteryCode = lotteryCode;
	}
	
	public String getLotteryPlay() {
		return lotteryPlay;
	}

	public void setLotteryPlay(String lotteryPlay) {
		this.lotteryPlay = lotteryPlay;
	}
	
}