/**
 * 
 */
package com.game.hall.modules.bet.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.api.BetServiceApi;
import com.entity.ResultData;
import com.game.hall.common.utils.SpringContextHolder;
import com.game.hall.modules.bet.dao.LotteryOpenTodayDao;
import com.game.hall.modules.bet.entity.LotteryTimeNum;

/**
 * @author antonio
 *
 */
@Service
public class LotteryOpenTodayService {
//	@Autowired @Qualifier("myServiceClient")
//	// (name = "myServiceClient")
//	private BetServiceApi myServiceClient;
//	
//	private BetServiceApi getBetServiceApi() {
//		return (BetServiceApi) SpringContextHolder.getBean("myServiceClient");
//	}
//
//	@Autowired
//	private LotteryOpenTodayDao lotOpenToday;
//
//	public void bet() {
//		getBetServiceApi().test1();
//	}
//
//	public ResultData OpenToday() {
//		return getBetServiceApi().openToday("SSC", 5);
//	}
//
//	public List<LotteryTimeNum> Cur(Date dt) {
//		return lotOpenToday.currentIssue(dt);
//	}

}
