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
import com.game.manager.modules.lottery.dao.LotteryTypeDao;
import com.game.manager.modules.lottery.entity.LotteryType;

/**
 * 彩种基本信息管理Service
 * @author Terry
 * @version 2017-11-07
 */
@Service
@Transactional(readOnly = true)
public class LotteryTypeService extends CrudService<LotteryTypeDao, LotteryType> {
	
	@Autowired
    private LotteryTypeDao lotteryTypeDao;
	
    /**
     * 通过记录编号获取单行数据
     */
    public LotteryType get(String id) {
        return super.get(id);
    }

    /**
     * 查询所有指定删除状态的数据
     */
    public List<LotteryType> findList(LotteryType lotteryType) {
        return super.findList(lotteryType);
    }

    /**
     * 分页查询所有指定删除状态的数据
     */
    public Page<LotteryType> findPage(Page<LotteryType> page, LotteryType lotteryType) {
        return super.findPage(page, lotteryType);
    }

    /**
     * 更新信息
     */
    @Transactional(readOnly = false)
    public void save(LotteryType lotteryType) {
        super.save(lotteryType);
    }

    /**
     * 删除信息（更新数据删除标识为1）
     */
    @Transactional(readOnly = false)
    public void delete(LotteryType lotteryType) {
        super.delete(lotteryType);
    }
    
    /**
     * 根据彩种code查询彩种信息
     */
   
    public LotteryType queryLotteryType(String lotteryCode) {
        return lotteryTypeDao.queryLotteryType(lotteryCode);
    }
 
  
    
}