/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.lottery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.manager.common.persistence.Page;
import com.game.manager.common.service.CrudService;
import com.game.manager.modules.lottery.entity.LotteryPlayConfig;
import com.game.manager.modules.lottery.dao.LotteryPlayConfigDao;

/**
 * 彩票玩法管理Service
 * @author Terry
 * @version 2017-11-10
 */
@Service
@Transactional(readOnly = true)
public class LotteryPlayConfigService extends CrudService<LotteryPlayConfigDao, LotteryPlayConfig> {

	public LotteryPlayConfig get(String id) {
		return super.get(id);
	}
	
	public List<LotteryPlayConfig> findList(LotteryPlayConfig lotteryPlayConfig) {
		return super.findList(lotteryPlayConfig);
	}
	
	public Page<LotteryPlayConfig> findPage(Page<LotteryPlayConfig> page, LotteryPlayConfig lotteryPlayConfig) {
		return super.findPage(page, lotteryPlayConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(LotteryPlayConfig lotteryPlayConfig) {
		super.save(lotteryPlayConfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(LotteryPlayConfig lotteryPlayConfig) {
		super.delete(lotteryPlayConfig);
	}
	
}