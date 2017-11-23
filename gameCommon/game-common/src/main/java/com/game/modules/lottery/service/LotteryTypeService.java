/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.modules.lottery.service;

import java.util.List;

import com.game.common.persistence.Page;
import com.game.modules.lottery.entity.LotteryType;

/**
 * 彩种基本信息管理Service
 * @author Terry
 * @version 2017-11-15
 */
public interface LotteryTypeService {

    /**
     * 根据记录ID获取单条数据
     */
    public LotteryType get(String id);

    /**
     * 通过彩种编号获取单条数据
     * @param code 彩种编号
     * @return 查询的数据实体对象
     * @author Terry
     */
    public LotteryType getByCode(String code);

    /**
     * 查询所有数据
     */
    public List<LotteryType> findList(LotteryType lotteryType);

    /**
     * 分页查询
     */
    public Page<LotteryType> findPage(Page<LotteryType> page, LotteryType lotteryType);

    /**
     * 保存表单提交数据
     */
    public void save(LotteryType lotteryType);

    /**
     * 删除一条数据（更新删除标识）
     */
    public void delete(LotteryType lotteryType);

    
    /**
     * 根据彩种code查询彩种信息
     */
   
    public LotteryType queryLotteryType(String lotteryCode);
}