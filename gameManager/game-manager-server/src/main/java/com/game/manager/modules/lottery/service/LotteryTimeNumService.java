/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.lottery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.manager.common.persistence.Page;
import com.game.manager.common.service.CrudService;
import com.game.manager.common.utils.StringUtils;
import com.game.manager.modules.lottery.entity.LotteryTimeNum;
import com.game.manager.modules.lottery.dao.LotteryTimeNumDao;

/**
 * 开奖时刻和开奖结果Service
 * @author jerry
 * @version 2017-11-10
 */
@Service
@Transactional(readOnly = true)
public class LotteryTimeNumService extends CrudService<LotteryTimeNumDao, LotteryTimeNum> {

	
	public LotteryTimeNum get(String id) {
		LotteryTimeNum lotteryTimeNum = super.get(id);
		return lotteryTimeNum;
	}
	
	public List<LotteryTimeNum> findList(LotteryTimeNum lotteryTimeNum) {
		return super.findList(lotteryTimeNum);
	}
	
	public Page<LotteryTimeNum> findPage(Page<LotteryTimeNum> page, LotteryTimeNum lotteryTimeNum) {
		return super.findPage(page, lotteryTimeNum);
	}
	
	@Transactional(readOnly = false)
	public void save(LotteryTimeNum lotteryTimeNum) {
		super.save(lotteryTimeNum);
	}
	
	@Transactional(readOnly = false)
	public void delete(LotteryTimeNum lotteryTimeNum) {
		super.delete(lotteryTimeNum);
	}
	
}