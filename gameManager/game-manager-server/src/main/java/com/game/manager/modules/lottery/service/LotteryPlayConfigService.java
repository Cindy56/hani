/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.lottery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.manager.common.persistence.Page;
import com.game.manager.common.service.CrudService;
import com.game.manager.modules.lottery.dao.LotteryPlayConfigDao;
import com.game.manager.modules.lottery.entity.LotteryPlayConfig;

/**
 * 彩票玩法管理Service
 * @author Terry
 * @version 2017-11-10
 */
@Service
@Transactional(readOnly = true)
public class LotteryPlayConfigService extends CrudService<LotteryPlayConfigDao, LotteryPlayConfig> {

    /**
     * 根据记录ID查询单条数据
     * @param id 数据记录ID
     * @return 查询的数据实体对象
     * @author terry
     */
    public LotteryPlayConfig get(String id) {
        return super.get(id);
    }

    /**
     * 根据传入属性搜索符合数据记录
     * @param lotteryPlayConfig 查询条件属性实体对象
     * @return 查询的数据List 
     * @author terry
     */
    public List<LotteryPlayConfig> findList(LotteryPlayConfig lotteryPlayConfig) {
        return super.findList(lotteryPlayConfig);
    }

    /**
     * 根据传入属性搜索符合数据记录
     * @param page 分页查询对象
     * @param lotteryPlayConfig 查询条件属性实体对象
     * @return 查询的当前页码数据
     * @author terry
     */
    public Page<LotteryPlayConfig> findPage(Page<LotteryPlayConfig> page, LotteryPlayConfig lotteryPlayConfig) {
        return super.findPage(page, lotteryPlayConfig);
    }

    /**
     * 更新数据到数据库，存在则更新，不存在则插入
     * @param lotteryPlayConfig 要更新到数据库的数据实体对象
     * @author terry
     */
    @Transactional(readOnly = false)
    public void save(LotteryPlayConfig lotteryPlayConfig) {
        super.save(lotteryPlayConfig);
    }

    /**
     * 删除数据（修改数据库数据删除标识为1）
     * @param lotteryPlayConfig 要更新到数据库的数据实体对象
     * @author terry
     */
    @Transactional(readOnly = false)
    public void delete(LotteryPlayConfig lotteryPlayConfig) {
        super.delete(lotteryPlayConfig);
    }
}