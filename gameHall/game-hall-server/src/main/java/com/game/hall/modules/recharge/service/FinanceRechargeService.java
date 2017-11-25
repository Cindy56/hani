package com.game.hall.modules.recharge.service;

import java.io.IOException;

/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */


import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.game.common.persistence.Page;
import com.game.common.service.CrudService;
import com.game.common.utils.StringUtils;
import com.game.hall.modules.member.service.PersonalDataService;
import com.game.hall.modules.recharge.dao.FinanceRechargeDao;
import com.game.hall.modules.recharge.entity.FinanceRecharge;
import com.game.hall.modules.utils.PassWordUtils;

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