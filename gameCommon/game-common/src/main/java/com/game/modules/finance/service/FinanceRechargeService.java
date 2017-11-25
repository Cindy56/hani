package com.game.modules.finance.service;

import java.util.List;

import com.game.common.persistence.Page;
import com.game.modules.finance.entity.FinanceRecharge;

public interface FinanceRechargeService {

	public FinanceRecharge get(String id);
	
	public List<FinanceRecharge> findList(FinanceRecharge financeRecharge);
	
	public Page<FinanceRecharge> findPage(Page<FinanceRecharge> page, FinanceRecharge financeRecharge);
	
	public FinanceRecharge save(FinanceRecharge financeRecharge);
	
	public void delete(FinanceRecharge financeRecharge);
}
