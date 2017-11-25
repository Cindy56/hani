/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.trade.modules.finance.dao;

import com.game.common.persistence.CrudDao;
import com.game.common.persistence.annotation.MyBatisDao;
import com.game.modules.finance.entity.FinanceRecharge;

/**
 * 账户充值管理DAO接口
 * @author David
 * @version 2017-11-25
 */
@MyBatisDao
public interface FinanceRechargeDao extends CrudDao<FinanceRecharge> {
	
}