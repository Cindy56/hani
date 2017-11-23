/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.hall.modules.bet.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.game.common.persistence.CrudDao;
import com.game.common.persistence.annotation.MyBatisDao;
import com.game.modules.lottery.entity.LotteryPlayConfig;

/**
 * 彩票玩法管理DAO接口
 * @author Terry
 * @version 2017-11-10
 */
@MyBatisDao
public interface LotteryPlayConfigDao extends CrudDao<LotteryPlayConfig> {

    /**
     * 通过玩法代码查询单条数据
     * @param code 玩法代码
     * @return 返回查询的实体对象
     * @author Terry
     */
    LotteryPlayConfig getByCode(String code);
    
    
    /**
     * 查询彩种玩法配置
     * @param lotteryName
     * @return
     */
    List<LotteryPlayConfig>  findAllConfigByName(@Param("name")String lotteryName);
    
    
}