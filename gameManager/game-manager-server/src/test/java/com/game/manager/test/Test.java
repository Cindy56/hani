/*package com.game.manager.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//import com.alibaba.fastjson.JSON;
import com.game.common.mapper.JsonMapper;
import com.game.modules.lottery.entity.LotteryPlayConfig;
import com.game.modules.lottery.entity.LotteryType;
import com.game.modules.sys.entity.User;

public class Test {
	
	public static void main(String[] args) {
//演示玩法json和java bean的互转
		List<LotteryPlayConfig> configList = new ArrayList<LotteryPlayConfig>();
		
		User xxxx = new User();
		
		LotteryPlayConfig test1 = new LotteryPlayConfig();
		LotteryType type1 = new LotteryType();
		type1.setCode("SSC_CQ");
		type1.setName("重庆时时彩");
		test1.setLotteryCode(type1);
		test1.setPlayCode("SSC_DAN1_ZHIXUAN");
		test1.setName("haha");
		test1.setPlayType("x");
		test1.setWinningProbability("0.001");
		test1.setCommissionRateMax(new BigDecimal("1.5"));
		test1.setCommissionRateMin(new BigDecimal("1.1"));
		test1.setBetRateLimit(10000);
		test1.setIsEnable("1");
		test1.setCurrentUser(xxxx);
		configList.add(test1);
		
		LotteryPlayConfig test2 = new LotteryPlayConfig();
		LotteryType type2 = new LotteryType();
		type2.setCode("SSC_XJ");
		type2.setName("新疆时时彩");
		test2.setLotteryCode(type2);
		test2.setPlayCode("SSC_DAN1_ZHIXUAN");
		test2.setName("看看");
		test2.setPlayType("x");
		test2.setWinningProbability("0.001");
		test2.setCommissionRateMax(new BigDecimal("1.5"));
		test2.setCommissionRateMin(new BigDecimal("0.9"));
		test2.setBetRateLimit(10000);
		test2.setIsEnable("1");
		test2.setCurrentUser(xxxx);
		configList.add(test2);
		
		LotteryPlayConfig test3 = new LotteryPlayConfig();
		LotteryType type3 = new LotteryType();
		type3.setCode("SSC_HLJ");
		type3.setName("HLJ时时彩");
		test3.setLotteryCode(type3);
		test3.setPlayCode("SSC_DAN1_ZHIXUAN");
		test3.setName("尽快看");
		test3.setPlayType("x");
		test3.setWinningProbability("0.001");
		test3.setCommissionRateMax(new BigDecimal("1.5"));
		test3.setCommissionRateMin(new BigDecimal("0.8"));
		test3.setBetRateLimit(10000);
		test3.setIsEnable("1");
		test3.setCurrentUser(xxxx);
		configList.add(test3);
		
		LotteryPlayConfig test4 = new LotteryPlayConfig();
		LotteryType type4 = new LotteryType();
		type4.setCode("SSC_FLB");
		type4.setName("菲律宾时时彩");
		test4.setLotteryCode(type4);
		test4.setPlayCode("SSC_DAN1_ZHIXUAN");
		test4.setName("啊啊啊");
		test4.setPlayType("x");
		test4.setWinningProbability("0.001");
		test4.setCommissionRateMax(new BigDecimal("1.5"));
		test4.setCommissionRateMin(new BigDecimal("0.8"));
		test4.setBetRateLimit(10000);
		test4.setIsEnable("1");
		test4.setCurrentUser(xxxx);
		configList.add(test4);
		
		LotteryPlayConfig test5 = new LotteryPlayConfig();
		LotteryType type5 = new LotteryType();
		type5.setCode("SSC_CQ");
		type5.setName("重庆时时彩");
		test5.setLotteryCode(type5);
		test5.setPlayCode("SSC_DAN1_ZHIXUAN");
		test5.setName("林俊杰");
		test5.setPlayType("x");
		test5.setWinningProbability("0.001");
		test5.setCommissionRateMax(new BigDecimal("1.5"));
		test5.setCommissionRateMin(new BigDecimal("0.9"));
		test5.setBetRateLimit(10000);
		test5.setIsEnable("1");
		test5.setCurrentUser(xxxx);
		configList.add(test5);
		
//		String jsonstr = JSONObject.toJSONString(configList);
		String jsonstr = JsonMapper.toJsonString(configList);
		
		
//		String jsonstr = JsonMapper.toJsonString(configList);
		
		
		System.out.println(jsonstr);
		
		System.out.println(jsonstr);
		String s=JSON.toJSONString(configList);
		System.out.println(s);
		
		List<LotteryPlayConfig> l=JSON.parseArray(jsonstr, LotteryPlayConfig.class);
		
		
		
		System.out.println(l);
		
		JsonMapper jsonMapper = JsonMapper.getInstance();
		List<LotteryPlayConfig> xxxxxx  =  jsonMapper.fromJson(jsonstr, jsonMapper.createCollectionType(List.class, LotteryPlayConfig.class));
		System.out.println(jsonstr);
		
//		System.out.println(xxxxxx);
		for (LotteryPlayConfig lotteryPlayConfig : xxxxxx) {
			System.out.println(lotteryPlayConfig);
			System.out.println(lotteryPlayConfig.getMap());
		}
	
	}
}*/