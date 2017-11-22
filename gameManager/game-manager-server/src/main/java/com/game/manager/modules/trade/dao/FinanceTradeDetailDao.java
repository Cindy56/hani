/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.trade.dao;

import java.util.List;

import com.game.common.persistence.CrudDao;
import com.game.common.persistence.annotation.MyBatisDao;
import com.game.modules.trade.entity.FinanceTradeDetail;

/**
 * 账变流水DAO接口
 * @author jerry
 * @version 2017-11-09
 */
@MyBatisDao
public interface FinanceTradeDetailDao extends CrudDao<FinanceTradeDetail> {
	public void batchTrade(List<FinanceTradeDetail> financeTradeDetail);
}