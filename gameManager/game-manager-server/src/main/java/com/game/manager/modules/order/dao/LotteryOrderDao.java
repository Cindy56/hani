/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.order.dao;

import com.game.manager.common.persistence.CrudDao;
import com.game.manager.common.persistence.annotation.MyBatisDao;
import com.game.manager.modules.order.entity.LotteryOrder;

/**
 * 订单明细DAO接口
 * @author antonio
 * @version 2017-11-16
 */
@MyBatisDao
public interface LotteryOrderDao extends CrudDao<LotteryOrder> {
	
}