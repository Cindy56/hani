/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.memberadd.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.game.manager.common.persistence.CrudDao;
import com.game.manager.common.persistence.annotation.MyBatisDao;
import com.game.manager.modules.memberadd.entity.MemberAccount;

/**
 * 会员开户DAO接口
 * @author freeman
 * @version 2017-11-17
 */
@MyBatisDao
public interface MemberAccountDao extends CrudDao<MemberAccount> {
	
	List<Map<String,Object>> getLotteryPlayConfig();
	
	/**
	 * 根据userid查找会员，一对一
	 * @param userId
	 * @return
	 */
	public MemberAccount getByUserId(String userId);
	
	/**
	 * 增加会员账户金额
	 * @param memberAccount
	 * @param amount
	 */
	public void plusAmount(@Param("accountId")String accountId, @Param("amount")BigDecimal amount);
	/**
	 * 扣减会员账户金额
	 * @param memberAccount
	 * @param amount
	 */
	public void minusAmount(@Param("accountId")String accountId, @Param("amount")BigDecimal amount);
	
	/**
	 * 根据 多个 会员ID 查询 会员信息
	 * @param Ids id集合
	 * @return 会员信息
	 */
	public List<MemberAccount> findMemberIds(@Param("ids") List<String> ids);
	
	
}