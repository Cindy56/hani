/**
 * 今日开奖
 */
package com.game.hall.modules.bet.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.game.hall.modules.bet.entity.LotteryTimeNum;
import com.game.manager.common.persistence.annotation.MyBatisDao;

/**
 * @author antonio
 *
 */

@MyBatisDao
public interface LotteryOpenTodayDao {

	public List<LotteryTimeNum> openToday(/* Map<String, Object> params */);

	public List<LotteryTimeNum> currentIssue(@Param("dt") Date dt);

}
