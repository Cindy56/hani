/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.trade.modules.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.common.persistence.Page;
import com.game.common.service.CrudService;
import com.game.modules.bank.entity.CompanyCard;
import com.game.modules.bank.service.CompanyCardService;
import com.game.trade.modules.bank.dao.CompanyCardDao;

/**
 * 银行卡管理Service
 * @author David
 * @version 2017-11-29
 */
@Service("companyCardService")
@Transactional(readOnly = true)
public class CompanyCardServiceImpl extends CrudService<CompanyCardDao, CompanyCard> implements CompanyCardService{
	@Autowired
	private CompanyCardDao companyCardDao;
	public CompanyCard get(String id) {
		return super.get(id);
	}
	
	public List<CompanyCard> findList(CompanyCard companyCard) {
		return super.findList(companyCard);
	}
	
	public Page<CompanyCard> findPage(Page<CompanyCard> page, CompanyCard companyCard) {
		return super.findPage(page, companyCard);
	}
	
	@Transactional(readOnly = false)
	public CompanyCard save(CompanyCard companyCard) {
		return super.save(companyCard);
	}
	
	@Transactional(readOnly = false)
	public void delete(CompanyCard companyCard) {
		super.delete(companyCard);
	}

	@Override
	public List<CompanyCard> findListByBankCode(CompanyCard companyCard) {
		return companyCardDao.findListByBankCode(companyCard);

	}
	
}