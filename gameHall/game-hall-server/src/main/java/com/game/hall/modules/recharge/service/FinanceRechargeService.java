package com.game.hall.modules.recharge.service;

/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.common.persistence.Page;
import com.game.common.service.CrudService;
import com.game.hall.modules.member.service.PersonalDataService;
import com.game.hall.modules.recharge.dao.FinanceRechargeDao;
import com.game.modules.finance.entity.FinanceRecharge;

/**
 * 账户充值管理Service
 * @author David
 * @version 2017-11-24
 */
@Service
@Transactional(readOnly = true)
public class FinanceRechargeService extends CrudService<FinanceRechargeDao, FinanceRecharge> {

	@Autowired
	private PersonalDataService personalDataService;
	
	public FinanceRecharge get(String id) {
		return super.get(id);
	}
	
	public List<FinanceRecharge> findList(FinanceRecharge financeRecharge) {
		return super.findList(financeRecharge);
	}
	
	public Page<FinanceRecharge> findPage(Page<FinanceRecharge> page, FinanceRecharge financeRecharge) {
		return super.findPage(page, financeRecharge);
	}
	
//	@Transactional(readOnly = false)
//	public void save(FinanceRecharge financeRecharge) {
//		super.save(financeRecharge);
//	}
	
	@Transactional(readOnly = false)
	public void delete(FinanceRecharge financeRecharge) {
		super.delete(financeRecharge);
	}
	
	

	
}