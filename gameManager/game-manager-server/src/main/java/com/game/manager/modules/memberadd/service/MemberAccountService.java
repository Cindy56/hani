/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.memberadd.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.manager.common.persistence.Page;
import com.game.manager.common.service.CrudService;
import com.game.manager.modules.memberadd.entity.MemberAccount;
import com.game.manager.modules.memberadd.dao.MemberAccountDao;

/**
 * 会员开户Service
 * @author freeman
 * @version 2017-11-17
 */
@Service
@Transactional(readOnly = true)
public class MemberAccountService extends CrudService<MemberAccountDao, MemberAccount> {
	
	@Autowired
	private MemberAccountDao memberAccountDao;

	public MemberAccount get(String id) {
		return super.get(id);
	}
	public MemberAccount getByUserId(String userId) {
		return this.memberAccountDao.getByUserId(userId);
	}
	
	public List<MemberAccount> findList(MemberAccount memberAccount) {
		return super.findList(memberAccount);
	}
	
	public Page<MemberAccount> findPage(Page<MemberAccount> page, MemberAccount memberAccount) {
		return super.findPage(page, memberAccount);
	}
	
	@Transactional(readOnly = false)
	public void save(MemberAccount memberAccount) {
		super.save(memberAccount);
	}
	
	@Transactional(readOnly = false)
	public void delete(MemberAccount memberAccount) {
		super.delete(memberAccount);
	}
	
	public List<Map<String, Object>> getLotteryPlayConfig(){
		return memberAccountDao.getLotteryPlayConfig();
	}
	
	/**
	 * 增加会员账户金额
	 * @param memberAccount
	 * @param amount
	 */
	public void plusAmount(String accountId, BigDecimal amount) {
		 this.memberAccountDao.plusAmount(accountId, amount);
	}
	
	/**
	 * 扣减会员账户金额
	 * @param memberAccount
	 * @param amount
	 */
	public void minusAmount(String accountId, BigDecimal amount) {
		this.memberAccountDao.minusAmount(accountId, amount);
	}
	
}