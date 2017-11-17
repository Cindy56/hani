/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.memberadd.dao;

import java.util.List;
import java.util.Map;

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
	
}