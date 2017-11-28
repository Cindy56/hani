/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.trade.modules.finance.dao;

import com.game.common.persistence.CrudDao;
import com.game.common.persistence.annotation.MyBatisDao;
import com.game.modules.finance.entity.ReceiveBankNo;

/**
 * 收款人银行卡管理DAO接口
 * @author David
 * @version 2017-11-28
 */
@MyBatisDao
public interface ReceiveBankNoDao extends CrudDao<ReceiveBankNo> {
	
}