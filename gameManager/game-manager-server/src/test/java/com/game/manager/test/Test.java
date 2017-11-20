package com.game.manager.test;

import java.util.ArrayList;
import java.util.List;

import com.game.manager.common.mapper.JsonMapper;
import com.game.manager.modules.lottery.entity.LotteryPlayConfig;
import com.game.manager.modules.lottery.entity.LotteryType;

public class Test {
	
	public static void main(String[] args) {
//演示玩法json和java bean的互转
		List<LotteryPlayConfig> configList = new ArrayList<LotteryPlayConfig>();
		
		LotteryPlayConfig test1 = new LotteryPlayConfig();
		LotteryType type1 = new LotteryType();
		type1.setCode("SSC_CQ");
		test1.setLotteryCode(type1);
		test1.setPlayCode("SSC_DAN1_ZHIXUAN");
		test1.setPlayType("x");
		test1.setWinningProbability("0.1");
		test1.setCommissionRateMax("0.12");
		test1.setCommissionRateMin("0.6");
		test1.setBetRateLimit("10000");
		test1.setIsEnable("1");
		configList.add(test1);
		
		LotteryPlayConfig test2 = new LotteryPlayConfig();
		LotteryType type2 = new LotteryType();
		type2.setCode("SSC_XJ");
		test2.setLotteryCode(type2);
		test2.setPlayCode("SSC_DAN1_ZHIXUAN");
		test2.setPlayType("x");
		test2.setWinningProbability("0.1");
		test2.setCommissionRateMax("0.12");
		test2.setCommissionRateMin("0.6");
		test2.setBetRateLimit("10000");
		test2.setIsEnable("1");
		configList.add(test2);
		
		LotteryPlayConfig test3 = new LotteryPlayConfig();
		LotteryType type3 = new LotteryType();
		type3.setCode("SSC_HLJ");
		test3.setLotteryCode(type3);
		test3.setPlayCode("SSC_DAN1_ZHIXUAN");
		test3.setPlayType("x");
		test3.setWinningProbability("0.1");
		test3.setCommissionRateMax("0.12");
		test3.setCommissionRateMin("0.6");
		test3.setBetRateLimit("10000");
		test3.setIsEnable("1");
		configList.add(test3);
		
		LotteryPlayConfig test4 = new LotteryPlayConfig();
		LotteryType type4 = new LotteryType();
		type4.setCode("SSC_FLB");
		test4.setLotteryCode(type4);
		test4.setPlayCode("SSC_DAN1_ZHIXUAN");
		test4.setPlayType("x");
		test4.setWinningProbability("0.1");
		test4.setCommissionRateMax("0.12");
		test4.setCommissionRateMin("0.6");
		test4.setBetRateLimit("10000");
		test4.setIsEnable("1");
		configList.add(test4);
		
		LotteryPlayConfig test5 = new LotteryPlayConfig();
		LotteryType type5 = new LotteryType();
		type5.setCode("SSC_CQ");
		test5.setLotteryCode(type5);
		test5.setPlayCode("SSC_DAN1_ZHIXUAN");
		test5.setPlayType("x");
		test5.setWinningProbability("0.1");
		test5.setCommissionRateMax("0.12");
		test5.setCommissionRateMin("0.6");
		test5.setBetRateLimit("10000");
		test5.setIsEnable("1");
		configList.add(test5);
		
//		String jsonstr = JSONObject.toJSONString(configList);
		String jsonstr = JsonMapper.toJsonString(configList);
		
		JsonMapper jsonMapper = JsonMapper.getInstance();
		List<LotteryPlayConfig> xxxxxx  =  jsonMapper.fromJson(jsonstr, jsonMapper.createCollectionType(List.class, LotteryPlayConfig.class));
		System.out.println(jsonstr);
	
	}
}
