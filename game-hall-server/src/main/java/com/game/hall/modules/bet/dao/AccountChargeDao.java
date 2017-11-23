/**
 * 今日开奖
 */
package com.game.hall.modules.bet.dao;

import java.math.BigDecimal;

import com.game.hall.common.persistence.CrudDao;
import com.game.hall.common.persistence.annotation.MyBatisDao;
import com.game.manager.modules.order.entity.LotteryOrder;

/**
 * @author antonio
 *
 */
@MyBatisDao
public interface AccountChargeDao extends CrudDao<LotteryOrder> {

	/**
	 * 余额扣除
	 * 
	 * @param id
	 * @param blance
	 */
	public void AccountChargeAmount(String id, BigDecimal blance);

}
