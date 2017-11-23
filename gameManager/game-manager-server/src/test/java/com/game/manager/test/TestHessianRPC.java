package com.game.manager.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.game.modules.lottery.entity.LotteryPlayConfig;
import com.game.modules.lottery.service.LotteryPlayConfigService;

@RunWith(SpringJUnit4ClassRunner.class)    
@ContextConfiguration(locations = {"classpath*:/spring-context*.xml", "classpath*:/spring-hessian-client.xml"})  
public class TestHessianRPC {
	@Autowired
	private LotteryPlayConfigService lotteryPlayConfigService;

	@Test
	public void testLotteryPlayConfigService() {
		LotteryPlayConfig test = lotteryPlayConfigService.get("asdfasf");
		System.out.println(test);
	}
}
