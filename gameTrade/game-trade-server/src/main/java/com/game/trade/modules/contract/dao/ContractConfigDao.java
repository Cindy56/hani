/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.trade.modules.contract.dao;

import com.game.common.persistence.CrudDao;
import com.game.common.persistence.annotation.MyBatisDao;
import com.game.modules.contract.entity.ContractConfig;

/**
 * 开设分公司DAO接口
 * @author freeman
 * @version 2017-11-22
 */
@MyBatisDao
public interface ContractConfigDao extends CrudDao<ContractConfig> {
	
}