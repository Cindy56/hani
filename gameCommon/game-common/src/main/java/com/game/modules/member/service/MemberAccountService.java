/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.modules.member.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.game.common.persistence.Page;
import com.game.modules.member.entity.MemberAccount;

/**
 * 会员开户Service
 * @author freeman
 * @version 2017-11-17
 */
public interface MemberAccountService {
	

	public MemberAccount get(String id);
	public MemberAccount getByUserId(String userId);
	
	public List<MemberAccount> findList(MemberAccount memberAccount);
	
	public Page<MemberAccount> findPage(Page<MemberAccount> page, MemberAccount memberAccount);
	
	public void save(MemberAccount memberAccount);
	
	public void delete(MemberAccount memberAccount);
	
	public List<Map<String, Object>> getLotteryPlayConfig();
	
	/**
	 * 增加会员账户金额
	 * @param memberAccount
	 * @param amount
	 */
	public void plusAmount(String accountId, BigDecimal amount);
	
	/**
	 * 扣减会员账户金额
	 * @param memberAccount
	 * @param amount
	 */
	public void minusAmount(String accountId, BigDecimal amount);
	
	/**
	 * 根据 多个 会员ID 查询 会员信息
	 * @param memberAccount
	 * @param amount
	 */
	public List<MemberAccount>  findMemberId(String  id);
	
}