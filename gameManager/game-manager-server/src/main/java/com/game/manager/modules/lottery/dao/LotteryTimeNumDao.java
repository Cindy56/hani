/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.lottery.dao;

import java.util.List;

import com.game.manager.common.persistence.CrudDao;
import com.game.manager.common.persistence.annotation.MyBatisDao;
import com.game.manager.modules.lottery.entity.LotteryTimeNum;
import com.game.manager.modules.trade.entity.FinanceTradeDetail;

/**
 * 开奖时刻和开奖结果DAO接口
 * @author jerry
 * @version 2017-11-10
 */
@MyBatisDao
public interface LotteryTimeNumDao extends CrudDao<LotteryTimeNum> {
	public void batchTimeNum(List<LotteryTimeNum> lotteryTimeNums);
}