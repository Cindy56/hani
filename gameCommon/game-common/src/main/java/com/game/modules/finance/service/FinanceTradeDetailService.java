/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.modules.finance.service;

import java.util.List;

import com.game.common.persistence.Page;
import com.game.modules.finance.entity.FinanceTradeDetail;

/**
 * 账变流水Service
 * @author jerry
 * @version 2017-11-09
 */
public interface FinanceTradeDetailService {
	public FinanceTradeDetail get(String id);
	
	public List<FinanceTradeDetail> findList(FinanceTradeDetail financeTradeDetail);
	
	public Page<FinanceTradeDetail> findPage(Page<FinanceTradeDetail> page, FinanceTradeDetail financeTradeDetail);
	
	public void save(FinanceTradeDetail financeTradeDetail);
	
	public void delete(FinanceTradeDetail financeTradeDetail);
	
	/**
	 * 批量插入账变流水
	 * @param list
	 */
	public void batchTrade(List<FinanceTradeDetail> list);
}