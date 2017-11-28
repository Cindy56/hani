/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.trade.modules.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.common.persistence.Page;
import com.game.common.service.CrudService;
import com.game.modules.order.entity.LotteryOrder;
import com.game.modules.order.service.LotteryOrderService;
import com.game.trade.modules.order.dao.LotteryOrderDao;

/**
 * 订单明细Service
 * @author vinton
 * @version 2017-11-17
 */
@Service("lotteryOrderService")
@Transactional(readOnly = true)
public class LotteryOrderServiceImpl 
	extends CrudService<LotteryOrderDao, LotteryOrder> implements LotteryOrderService {

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
	public LotteryOrder save(LotteryOrder lotteryOrder) {
		return super.save(lotteryOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(LotteryOrder lotteryOrder) {
		super.delete(lotteryOrder);
	}
	
}