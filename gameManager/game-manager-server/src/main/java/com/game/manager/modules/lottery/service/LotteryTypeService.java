/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.lottery.service;

import java.util.List;

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

    public LotteryType get(String id) {
        return super.get(id);
    }

    public List<LotteryType> findList(LotteryType lotteryType) {
        return super.findList(lotteryType);
    }

    public Page<LotteryType> findPage(Page<LotteryType> page, LotteryType lotteryType) {
        return super.findPage(page, lotteryType);
    }

    @Transactional(readOnly = false)
    public void save(LotteryType lotteryType) {
        super.save(lotteryType);
    }

    @Transactional(readOnly = false)
    public void delete(LotteryType lotteryType) {
        super.delete(lotteryType);
    }
}