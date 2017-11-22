/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.trade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.common.persistence.Page;
import com.game.common.service.CrudService;
import com.game.modules.trade.entity.FinanceTradeDetail;
import com.game.manager.modules.gen.entity.GenScheme;
import com.game.manager.modules.trade.dao.FinanceTradeDetailDao;

/**
 * 账变流水Service
 * @author jerry
 * @version 2017-11-09
 */
@Service
@Transactional(readOnly = true)
public class FinanceTradeDetailService extends CrudService<FinanceTradeDetailDao, FinanceTradeDetail> {
	
	@Autowired
	private FinanceTradeDetailDao financeTradeDetailDao;
	
	public FinanceTradeDetail get(String id) {
		return super.get(id);
	}
	
	public List<FinanceTradeDetail> findList(FinanceTradeDetail financeTradeDetail) {
		return super.findList(financeTradeDetail);
	}
	
	public Page<FinanceTradeDetail> findPage(Page<FinanceTradeDetail> page, FinanceTradeDetail financeTradeDetail) {
		return super.findPage(page, financeTradeDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(FinanceTradeDetail financeTradeDetail) {
		super.save(financeTradeDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(FinanceTradeDetail financeTradeDetail) {
		super.delete(financeTradeDetail);
	}
	
	/**
	 * 批量插入账变流水
	 * @param list
	 */
	@Transactional(readOnly = false)
	public void batchTrade(List<FinanceTradeDetail> list) {
		list.stream().forEach(c->{
			c.preInsert();
		});
		financeTradeDetailDao.batchTrade(list);
	}
	
}