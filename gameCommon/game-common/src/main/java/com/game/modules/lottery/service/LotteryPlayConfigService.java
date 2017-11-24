/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.modules.lottery.service;

import java.util.List;

import com.game.common.persistence.Page;
import com.game.modules.lottery.entity.LotteryPlayConfig;

/**
 * 彩票玩法管理Service
 * @author Terry
 * @version 2017-11-10
 */
public  interface LotteryPlayConfigService{
    /**
     * 根据记录ID查询单条数据
     * @param id 数据记录ID
     * @return 查询的数据实体对象
     * @author terry
     */
    public LotteryPlayConfig get(String id);

    /**
     * 通过玩法代码查询单条数据
     * @param code 玩法代码
     * @return 查询的数据实体对象
     * @author Terry
     */
    public LotteryPlayConfig getByCode(String code);

    /**
     * 根据传入属性搜索符合数据记录
     * @param lotteryPlayConfig 查询条件属性实体对象
     * @return 查询的数据List 
     * @author terry
     */
    public List<LotteryPlayConfig> findList(LotteryPlayConfig lotteryPlayConfig);

    /**
     * 根据传入属性搜索符合数据记录
     * @param page 分页查询对象
     * @param lotteryPlayConfig 查询条件属性实体对象
     * @return 查询的当前页码数据
     * @author terry
     */
    public Page<LotteryPlayConfig> findPage(Page<LotteryPlayConfig> page, LotteryPlayConfig lotteryPlayConfig);

    /**
     * 更新数据到数据库，存在则更新，不存在则插入
     * @param lotteryPlayConfig 要更新到数据库的数据实体对象
     * @author terry
     */
    public LotteryPlayConfig save(LotteryPlayConfig lotteryPlayConfig);

    /**
     * 删除数据（修改数据库数据删除标识为1）
     * @param lotteryPlayConfig 要更新到数据库的数据实体对象
     * @author terry
     */
    public void delete(LotteryPlayConfig lotteryPlayConfig);
}