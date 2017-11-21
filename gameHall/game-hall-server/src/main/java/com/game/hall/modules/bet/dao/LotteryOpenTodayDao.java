/**
 * 今日开奖
 */
package com.game.hall.modules.bet.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.game.hall.common.persistence.annotation.MyBatisDao;
import com.game.hall.modules.bet.entity.LotteryTimeNum;

/**
 * @author antonio
 *
 */

@MyBatisDao
public interface LotteryOpenTodayDao {

	public List<LotteryTimeNum> openToday(@Param("name")String lotteryName, @Param("num")Integer num/* Map<String, Object> params */);

	public List<LotteryTimeNum> currentIssue(String lotteryName, @Param("dt") Date dt);

}
