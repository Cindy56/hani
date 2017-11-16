/**
 * 
 */
package com.game.hall.modules.bet.service;


import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.hall.modules.bet.dao.LotteryOpenTodayDao;
import com.game.hall.modules.bet.entity.LotteryTimeNum;

/**
 * @author antonio
 *
 */
@Service
public class LotteryOpenTodayService {

	@Autowired
	private LotteryOpenTodayDao lotOpenToday;

	public List<LotteryTimeNum> OpenToday() {
		return lotOpenToday.OpenToday();
	}

	public List<LotteryTimeNum> Cur(Date dt) {
		return lotOpenToday.CurrentIssue(dt);
	}

}
