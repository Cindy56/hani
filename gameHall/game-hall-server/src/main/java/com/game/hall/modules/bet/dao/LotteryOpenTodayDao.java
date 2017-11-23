/**
 * 今日开奖
 */
package com.game.hall.modules.bet.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.game.common.persistence.annotation.MyBatisDao;
import com.game.hall.modules.bet.entity.LotteryTimeNumbak;

/**
 * @author antonio
 *
 */

@MyBatisDao
public interface LotteryOpenTodayDao {

	public List<LotteryTimeNumbak> openToday(/* Map<String, Object> params */);

	public List<LotteryTimeNumbak> currentIssue(@Param("dt") Date dt);

}
