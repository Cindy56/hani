/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.modules.order.service;

import java.util.List;

import com.game.common.persistence.Page;
import com.game.modules.order.entity.LotteryOrder;

/**
 * 订单明细Service
 * @author vinton
 * @version 2017-11-17
 */
public interface LotteryOrderService {

	public LotteryOrder get(String id);
	
	public List<LotteryOrder> findList(LotteryOrder lotteryOrder);
	
	public Page<LotteryOrder> findPage(Page<LotteryOrder> page, LotteryOrder lotteryOrder);
	
	public void save(LotteryOrder lotteryOrder);
	
	public void delete(LotteryOrder lotteryOrder);
	
}