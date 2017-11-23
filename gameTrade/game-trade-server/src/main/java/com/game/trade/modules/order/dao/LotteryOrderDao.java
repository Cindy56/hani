/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.trade.modules.order.dao;

import com.game.common.persistence.CrudDao;
import com.game.common.persistence.annotation.MyBatisDao;
import com.game.modules.order.entity.LotteryOrder;

/**
 * 订单明细DAO接口
 * @author vinton
 * @version 2017-11-17
 */
@MyBatisDao
public interface LotteryOrderDao extends CrudDao<LotteryOrder> {
	
}