/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.companylottery.dao;

import com.game.common.persistence.CrudDao;
import com.game.common.persistence.annotation.MyBatisDao;
import com.game.manager.modules.companylottery.entity.CompanyLottery;

/**
 * 公司彩票配置DAO接口
 * @author Terry
 * @version 2017-12-02
 */
@MyBatisDao
public interface CompanyLotteryDao extends CrudDao<CompanyLottery> {
	
}