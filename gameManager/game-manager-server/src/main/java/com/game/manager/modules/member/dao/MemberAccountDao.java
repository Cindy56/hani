/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.member.dao;

import com.game.manager.common.persistence.CrudDao;
import com.game.manager.common.persistence.annotation.MyBatisDao;
import com.game.manager.modules.member.entity.MemberAccount;

/**
 * 会员管理DAO接口
 * @author David
 * @version 2017-11-08
 */
@MyBatisDao
public interface MemberAccountDao extends CrudDao<MemberAccount> {
	
}