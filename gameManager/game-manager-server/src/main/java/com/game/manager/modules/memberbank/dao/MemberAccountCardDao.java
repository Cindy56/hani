/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.memberbank.dao;

import com.game.manager.common.persistence.CrudDao;
import com.game.manager.common.persistence.annotation.MyBatisDao;
import com.game.manager.modules.memberbank.entity.MemberAccountCard;

/**
 * 会员银行卡管理DAO接口
 * @author freeman
 * @version 2017-11-14
 */
@MyBatisDao
public interface MemberAccountCardDao extends CrudDao<MemberAccountCard> {
	
}