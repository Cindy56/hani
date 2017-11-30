/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.trade.modules.bank.dao;

import java.util.List;

import com.game.common.persistence.CrudDao;
import com.game.common.persistence.annotation.MyBatisDao;
import com.game.modules.bank.entity.CompanyCard;

/**
 * 银行卡管理DAO接口
 * @author David
 * @version 2017-11-29
 */
@MyBatisDao
public interface CompanyCardDao extends CrudDao<CompanyCard> {
	
	public List<CompanyCard> findListByBankCode(CompanyCard companyCard);
}