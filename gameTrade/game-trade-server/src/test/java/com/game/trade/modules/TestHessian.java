package com.game.trade.modules;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.game.common.test.BaseJunitTest;
import com.game.modules.lottery.entity.LotteryPlayConfig;
import com.game.modules.lottery.service.LotteryPlayConfigService;

public class TestHessian extends BaseJunitTest {
	@Autowired
	private LotteryPlayConfigService lotteryPlayConfigService;
	
	@Test
	public void test001() {
		LotteryPlayConfig xxx = this.lotteryPlayConfigService.get("xxxxxxx");
		Object aaa = this.lotteryPlayConfigService.get("/lotteryPlayConfigService");
		System.out.println(xxx);
	}

}
