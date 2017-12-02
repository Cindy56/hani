/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.companylottery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.common.persistence.Page;
import com.game.common.service.CrudService;
import com.game.manager.modules.companylottery.dao.CompanyLotteryDao;
import com.game.manager.modules.companylottery.entity.CompanyLottery;

/**
 * 公司彩票配置Service
 * @author Terry
 * @version 2017-12-02
 */
@Service
@Transactional(readOnly = true)
public class CompanyLotteryService extends CrudService<CompanyLotteryDao, CompanyLottery> {

	public CompanyLottery get(String id) {
		return super.get(id);
	}

	public List<CompanyLottery> findList(CompanyLottery companyLottery) {
		return super.findList(companyLottery);
	}

	public Page<CompanyLottery> findPage(Page<CompanyLottery> page, CompanyLottery companyLottery) {
		return super.findPage(page, companyLottery);
	}

	@Transactional(readOnly = false)
	public CompanyLottery save(CompanyLottery companyLottery) {
		return super.save(companyLottery);
	}

	@Transactional(readOnly = false)
	public void delete(CompanyLottery companyLottery) {
		super.delete(companyLottery);
	}

}