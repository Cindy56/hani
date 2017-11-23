/**
 * 今日开奖
 */
package com.game.hall.modules.bet.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.game.hall.common.persistence.CrudDao;
import com.game.hall.common.persistence.annotation.MyBatisDao;
import com.game.hall.modules.sys.entity.User;
import com.game.manager.modules.order.entity.LotteryOrder;

/**
 * @author antonio
 *
 */
@MyBatisDao
public interface LotteryOrderDao extends CrudDao<LotteryOrder> {

	public List<LotteryOrder> findListById(@Param("userid")String userId, @Param("lotterycode")String lotteryCode, @Param("num")int num );
	
	/**
	 * 撤销订单
	 * @param user
	 * @param orderNo
	 * @return
	 */
	public int cancelByOrderNo(@Param("user")User user,@Param("updateDate")Date updateDate,  @Param("orderNo")String orderNo );


}
