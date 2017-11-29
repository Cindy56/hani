/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.modules.finance.service;

import java.util.List;

import com.game.common.persistence.Page;
import com.game.modules.finance.entity.FinanceTradeDetail;
import com.game.modules.order.entity.LotteryOrder;

/**
 * 账变流水Service
 * @author jerry
 * @version 2017-11-09
 */
public interface FinanceTradeDetailService {
	enum FinanceTradeDetailType {
		xxxxxx("0", "xxxx");
		
		private String code;
		private String name;
		private FinanceTradeDetailType(String code, String name) {
			this.code = code;
			this.name = name;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	
	public FinanceTradeDetail get(String id);
	
	public List<FinanceTradeDetail> findList(FinanceTradeDetail financeTradeDetail);
	
	public Page<FinanceTradeDetail> findPage(Page<FinanceTradeDetail> page, FinanceTradeDetail financeTradeDetail);
	
	public FinanceTradeDetail save(FinanceTradeDetail financeTradeDetail);
	
	public void delete(FinanceTradeDetail financeTradeDetail);
	
	/**
	 * 批量插入账变流水
	 * @param list
	 */
	public void batchTrade(List<FinanceTradeDetail> list);
	
	public void batchGenFinanceTradeDetail(List<LotteryOrder> lotteryOrderList, FinanceTradeDetailType type);
}