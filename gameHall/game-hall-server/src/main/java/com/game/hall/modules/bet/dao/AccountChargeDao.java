/**
 * 今日开奖
 */
package com.game.hall.modules.bet.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.game.hall.common.persistence.CrudDao;
import com.game.hall.common.persistence.annotation.MyBatisDao;
import com.game.manager.modules.order.entity.LotteryOrder;

/**
 * @author antonio
 *
 */
@MyBatisDao
public interface AccountChargeDao extends CrudDao<AccountChargeDao> {

	/**
	 * 余额扣除
	 * 
	 * @param id
	 * @param blance
	 */
	public void AccountChargeAmount(@Param("accountId")String id, @Param("blance")BigDecimal blance);

}
