/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.lottery.dao;

import com.game.manager.common.persistence.CrudDao;
import com.game.manager.common.persistence.annotation.MyBatisDao;
import com.game.manager.modules.lottery.entity.LotteryTypeTime;

/**
 * 彩种基本信息管理DAO接口
 * @author Terry
 * @version 2017-11-15
 */
@MyBatisDao
public interface LotteryTypeTimeDao extends CrudDao<LotteryTypeTime> {
	
}