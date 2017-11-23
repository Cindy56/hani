/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.modules.lottery.service;

import java.util.List;

import org.quartz.SchedulerException;

import com.game.common.persistence.Page;
import com.game.modules.lottery.dto.TimeTask;
import com.game.modules.lottery.entity.LotteryTimeNum;
import com.game.modules.sys.entity.Dict;

/**
 * 开奖时刻和开奖结果Service
 * @author jerry
 * @version 2017-11-10
 */
public interface LotteryTimeNumService{
	
	public LotteryTimeNum get(String id);
	
	public List<LotteryTimeNum> findList(LotteryTimeNum lotteryTimeNum);
	
	public Page<LotteryTimeNum> findPage(Page<LotteryTimeNum> page, LotteryTimeNum lotteryTimeNum);
	
	public void save(LotteryTimeNum lotteryTimeNum);
	
	public void delete(LotteryTimeNum lotteryTimeNum);
	
	public List<Dict> getLotteryDict();
	
	public void updateLotteryNum(String lotteryNum,String lotteryCode,String lotteryIssueNo,String status,String openDate);
	
    public  void  generatePlanTime (TimeTask timeTask) throws SchedulerException;
  
	/**
	 * 获取当前期号
	 * @param lotteryCode 彩种code
	 * @return 
	 */
	public LotteryTimeNum findCurrentIssueNo(String lotteryCode);
	/**
	 * 根据彩种和期号 查询 
	 * @param lotteryCode 彩种code
	 * @return 
	 */
	public LotteryTimeNum findByLotteryCodeIssueNo(String lotteryCode,String lotteryIssueNo);
	
	
	/**
	 * 获取历史期号
	 * @param lotteryCode 彩种code
	 * @param rows 行数
	 * @return 
	 */
	public List<LotteryTimeNum> findLotteryTimeNum(String lotteryCode,int rows);
	
	public String batchUpdateHalt(String[] ids,Integer betHaltDate);
}