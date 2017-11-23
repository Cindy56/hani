/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.trade.modules.lottery.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.game.common.persistence.CrudDao;
import com.game.common.persistence.annotation.MyBatisDao;
import com.game.modules.lottery.entity.LotteryTimeNum;

/**
 * 开奖时刻和开奖结果DAO接口
 * @author jerry
 * @version 2017-11-15
 */
@MyBatisDao
public interface LotteryTimeNumDao extends CrudDao<LotteryTimeNum> {
	/**
	 * 更新当期开奖号码
	 * @param lotteryCode 彩票代码
	 * @param lotteryIssueNo 开奖期号
	 */
	public void updateLotteryNum(@Param("lotteryNum") String lotteryNum,
			@Param("lotteryCode") String lotteryCode,@Param("lotteryIssueNo") String lotteryIssueNo,
			@Param("status") String status,@Param("openDate") String openDate);

	/**
	 * 批量生成时刻明细
	 * @param lotteryTimeNums
	 */
	public void batchTimeNum(List<LotteryTimeNum> lotteryTimeNums);
	
	/**
	 * 根据时间获取当前时间的期号信息  
	 * @param lotteryCode 彩种code
	 * @param date 时间
	 */
	public LotteryTimeNum queryCurrentIssueNo(@Param("lotteryCode") String lotteryCode,@Param("date") Date date);
	
	/**
	 * 根据 彩种  查询明细 
	 * @param lotteryCode 彩种code
	 *  @param row 行数
	 */
	public List<LotteryTimeNum> queryHisLotteryTimeNum(@Param("lotteryCode") String lotteryCode,@Param("row") int row,@Param("haltDate") Date haltDate);
	
	
	/**
	 * 暂时一条
	 * 批量修改封单时间
	 * @param list 批量更新对象
	 */
	public void batchUpdateHaltDate(LotteryTimeNum lotteryTimeNums);
	
	/**
	 * 根据主键ID  查询明细 
	 * @param ids 多个主键
	 */
	public List<LotteryTimeNum> queryByIdList(@Param("ids") String[] ids);
	
	/**
	 *  根据期号查询 查询时刻明细 
	 * @param lotteryIssueNo 期号
	 * @return
	 */
	public LotteryTimeNum queryByLotteryIssueNo(@Param("lotteryIssueNo") String lotteryIssueNo);
	
	
	/**
	 *  根据彩种期号查询 查询时刻明细 
	 *   * @param lotteryCode 彩种
	 * @param lotteryIssueNo 期号
	 * @return
	 */
	public LotteryTimeNum queryByLotteryCodeIssueNo(@Param("lotteryCode") String lotteryCode,@Param("lotteryIssueNo") String lotteryIssueNo);
	
	
	
}