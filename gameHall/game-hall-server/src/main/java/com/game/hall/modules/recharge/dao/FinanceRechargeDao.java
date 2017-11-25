package com.game.hall.modules.recharge.dao;

/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */


import com.game.common.persistence.CrudDao;
import com.game.common.persistence.annotation.MyBatisDao;
import com.game.hall.modules.recharge.entity.FinanceRecharge;

/**
 * 账户充值管理DAO接口
 * @author David
 * @version 2017-11-24
 */
@MyBatisDao
public interface FinanceRechargeDao extends CrudDao<FinanceRecharge> {
	
}