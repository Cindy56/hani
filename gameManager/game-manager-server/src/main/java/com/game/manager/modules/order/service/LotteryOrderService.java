/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.manager.common.persistence.Page;
import com.game.manager.common.service.CrudService;
import com.game.manager.modules.order.entity.LotteryOrder;
import com.game.manager.modules.order.dao.LotteryOrderDao;

/**
 * 订单明细Service
 * @author antonio
 * @version 2017-11-16
 */
@Service
@Transactional(readOnly = true)
public class LotteryOrderService extends CrudService<LotteryOrderDao, LotteryOrder> {

	public LotteryOrder get(String id) {
		return super.get(id);
	}
	
	public List<LotteryOrder> findList(LotteryOrder lotteryOrder) {
		return super.findList(lotteryOrder);
	}
	
	public Page<LotteryOrder> findPage(Page<LotteryOrder> page, LotteryOrder lotteryOrder) {
		return super.findPage(page, lotteryOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(LotteryOrder lotteryOrder) {
		super.save(lotteryOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(LotteryOrder lotteryOrder) {
		super.delete(lotteryOrder);
	}
	
}