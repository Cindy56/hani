/**
 * 今日开奖
 */
package com.game.hall.modules.bet.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.game.hall.common.persistence.CrudDao;
import com.game.hall.common.persistence.annotation.MyBatisDao;
import com.game.manager.modules.order.entity.LotteryOrder;

/**
 * @author antonio
 *
 */
@MyBatisDao
public interface LotteryOrderDao extends CrudDao<LotteryOrder> {

	List<LotteryOrder> findListById(@Param("userid")String userId, @Param("lotterycode")String lotteryCode, @Param("num")int num );
	
	
}
