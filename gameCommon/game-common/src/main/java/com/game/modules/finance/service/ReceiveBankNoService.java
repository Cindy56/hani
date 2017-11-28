package com.game.modules.finance.service;

import java.util.List;

import com.game.common.persistence.Page;
import com.game.modules.finance.entity.FinanceRecharge;
import com.game.modules.finance.entity.ReceiveBankNo;

public interface ReceiveBankNoService  {

	public ReceiveBankNo get(String id);
	
	public List<ReceiveBankNo> findList(ReceiveBankNo receiveBankNo);
	
	public Page<ReceiveBankNo> findPage(Page<ReceiveBankNo> page, ReceiveBankNo receiveBankNo);
	
	public ReceiveBankNo save(ReceiveBankNo receiveBankNo);
	
	public void delete(ReceiveBankNo receiveBankNo);
	

}