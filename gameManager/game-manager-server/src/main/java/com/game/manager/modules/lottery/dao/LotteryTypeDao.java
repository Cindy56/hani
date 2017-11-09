/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.lottery.dao;

import com.game.manager.common.persistence.CrudDao;
import com.game.manager.common.persistence.annotation.MyBatisDao;
import com.game.manager.modules.lottery.entity.LotteryType;

/**
 * 彩种基本信息管理DAO接口
 * @author Terry
 * @version 2017-11-07
 */
@MyBatisDao
public interface LotteryTypeDao extends CrudDao<LotteryType> {

}