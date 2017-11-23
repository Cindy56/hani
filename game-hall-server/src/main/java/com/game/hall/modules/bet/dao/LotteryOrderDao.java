/**
 * 今日开奖
 */
package com.game.hall.modules.bet.dao;

import com.game.hall.common.persistence.CrudDao;
import com.game.hall.common.persistence.annotation.MyBatisDao;
import com.game.manager.modules.order.entity.LotteryOrder;

/**
 * @author antonio
 *
 */
@MyBatisDao
public interface LotteryOrderDao extends CrudDao<LotteryOrder> {

}
