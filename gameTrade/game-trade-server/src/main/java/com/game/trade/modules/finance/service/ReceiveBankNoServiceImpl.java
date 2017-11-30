/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.trade.modules.finance.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.common.persistence.Page;
import com.game.common.service.CrudService;
import com.game.modules.finance.entity.ReceiveBankNo;
import com.game.modules.finance.service.ReceiveBankNoService;
import com.game.trade.modules.finance.dao.ReceiveBankNoDao;

/**
 * 收款人银行卡管理Service
 * @author David
 * @version 2017-11-28
 */
@Service("receiveBankNoService")
@Transactional(readOnly = true)
public class ReceiveBankNoServiceImpl extends CrudService<ReceiveBankNoDao, ReceiveBankNo> implements ReceiveBankNoService{

	public ReceiveBankNo get(String id) {
		return super.get(id);
	}
	
	public List<ReceiveBankNo> findList(ReceiveBankNo receiveBankNo) {
		return super.findList(receiveBankNo);
	}
	
	public Page<ReceiveBankNo> findPage(Page<ReceiveBankNo> page, ReceiveBankNo receiveBankNo) {
		return super.findPage(page, receiveBankNo);
	}
	
	@Transactional(readOnly = false)
	public ReceiveBankNo save(ReceiveBankNo receiveBankNo) {
		return super.save(receiveBankNo);
	}
	
	@Transactional(readOnly = false)
	public void delete(ReceiveBankNo receiveBankNo) {
		super.delete(receiveBankNo);
	}
	
}