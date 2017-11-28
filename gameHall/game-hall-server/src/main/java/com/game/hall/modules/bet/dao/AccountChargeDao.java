/**
 * 今日开奖
 */
package com.game.hall.modules.bet.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.game.common.persistence.CrudDao;
import com.game.common.persistence.annotation.MyBatisDao;

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
	public void accountChargeAmount(@Param("accountId")String id, @Param("blance")BigDecimal blance);

}
