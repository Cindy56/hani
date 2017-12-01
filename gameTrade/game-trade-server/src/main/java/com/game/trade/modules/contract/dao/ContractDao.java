/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.trade.modules.contract.dao;

import org.apache.ibatis.annotations.Param;

import com.game.common.persistence.CrudDao;
import com.game.common.persistence.annotation.MyBatisDao;
import com.game.modules.contract.entity.Contract;

/**
 * 开设分公司DAO接口
 * @author freeman
 * @version 2017-11-22
 */
@MyBatisDao
public interface ContractDao extends CrudDao<Contract> {
	public Contract getContractByUserId(@Param("userId")String userId,@Param("companyId")String companyId);
}