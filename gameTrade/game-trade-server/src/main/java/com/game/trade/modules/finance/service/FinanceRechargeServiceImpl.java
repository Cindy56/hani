/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.trade.modules.finance.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.common.persistence.Page;
import com.game.common.service.CrudService;
import com.game.modules.finance.entity.FinanceRecharge;
import com.game.modules.finance.service.FinanceRechargeService;
import com.game.trade.modules.finance.dao.FinanceRechargeDao;

/**
 * 账户充值管理Service
 * @author David
 * @version 2017-11-25
 */
@Service("financeRechargeService")
@Transactional(readOnly = true)
public class FinanceRechargeServiceImpl 
	extends CrudService<FinanceRechargeDao, FinanceRecharge> implements FinanceRechargeService {

	public FinanceRecharge get(String id) {
		return super.get(id);
	}
	
	public List<FinanceRecharge> findList(FinanceRecharge financeRecharge) {
		return super.findList(financeRecharge);
	}
	
	public Page<FinanceRecharge> findPage(Page<FinanceRecharge> page, FinanceRecharge financeRecharge) {
		return super.findPage(page, financeRecharge);
	}
	
	@Transactional(readOnly = false)
	public FinanceRecharge save(FinanceRecharge financeRecharge) {
		return super.save(financeRecharge);
	}
	
	@Transactional(readOnly = false)
	public void delete(FinanceRecharge financeRecharge) {
		super.delete(financeRecharge);
	}
	
}