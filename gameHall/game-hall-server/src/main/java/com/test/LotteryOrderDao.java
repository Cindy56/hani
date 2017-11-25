package com.test;

import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.game.modules.order.entity.LotteryOrder;
import com.game.modules.sys.entity.Office;
import com.game.modules.sys.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
// @WebAppConfiguration
@ContextConfiguration("classpath*:spring*.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class LotteryOrderDao extends AbstractJUnit4SpringContextTests {

	private MockMvc mockMvc;

	@Mock
	private com.game.hall.modules.bet.dao.LotteryOrderDao myOrderDao;

	@InjectMocks
	private com.game.hall.modules.bet.service.LotteryAddBetService myAddBetService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	
	
	public static LotteryOrder getOrder()
	{
		LotteryOrder bet1 = new LotteryOrder();
		bet1.preInsert();
		User user = new User();
		user.setId("00user");
		user.setName("00username");
		
		bet1.setAccountId("accountId");
		
		Office company = new Office();
		company.setCode("code");
		user.setCompany(company );
		bet1.setUser(user);
		String lotteryCode = "CQSSC";
		bet1.setLotteryCode(lotteryCode);
		BigDecimal betAmount = new BigDecimal(100);
		bet1.setBetAmount(betAmount);
		String betIssueNo = "20171117";
		bet1.setBetIssueNo(betIssueNo);
		String betType = "";
		bet1.setBetType(betType);
		User currentUser = null;
		bet1.setCurrentUser(currentUser);
		
		String orderSource = "0";
		bet1.setOrderSource(orderSource);
		String orderType = "2";
		bet1.setOrderType(orderType);
		String playModeCommissionRate = "0";
		bet1.setPlayModeCommissionRate(playModeCommissionRate);
		String playModeMoney = "1960";
		bet1.setPlayModeMoney(playModeMoney);
		String playModeMoneyType = "0";
		bet1.setPlayModeMoneyType(playModeMoneyType);
		
		//bet1.setOrderNum("10000001");
		
				
		bet1.setBetDetail("detail");
		bet1.setBetRate(1);
		bet1.setStatus("0");
		
		Date createDate = new Date();
		bet1.setCreateDate(createDate);
		bet1.setCreateBy(user);
		bet1.setUpdateBy(user);
		
		return bet1;
	}

	@Test
	public void testLotteryOrderDaoMethodinsert() {

		List<LotteryOrder> betData = new ArrayList<LotteryOrder>();

		

		/*
		 * bet_issue_no varchar(50) NOT NULL投注期号
		 * 
		 * bet_type varchar(50) NOT NULL投注类型： 比如重庆时时彩后三直选，不同的类型对应到不同的算奖模式
		 * 
		 * bet_detail varchar(4500) NOT NULL投注内容
		 * 
		 * bet_amount decimal(14,4) NOT NULL投注金额，单位为元
		 * 
		 * bet_rate smallint(6) NOT NULL投注倍数
		 * 
		 * play_mode_money smallint(6) NOT NULL奖金模式：1800,1700,1960， 需要在服务端做校验
		 * play_mode_commission_rate
		 * 
		 * smallint(6) NOT NULL佣金比例，返点比例，返水比例 需要在应用服务器做校验，看看奖金模式和返佣比率是否符合公式
		 * 
		 * play_mode_money_type char(1) NOT NULL玩法模式： 0元 1角2分
		 * 
		 * order_source char(1) NOT NULL注单来源： 1浏览器 2移动app
		 * 
		 * order_type char(1) NOT NULL注单类型： 1正常投注 2追号注单 2合买注单
		 * 
		 * schema_idvarchar(50) NULL方案外键： 如果是追号、合买，查看注单详情的时候链接到对应的方案
		 * 
		 * win_amountdecimal(14,4) NULL中奖金额，单位为元
		 * 
		 * withdraw_amount decimal(14,4) NULL撤单金额
		 * 
		 * statuschar(1) NOT NULL注单状态： 0等待开奖 1已中奖 2未中奖 3已撤单
		 */
		
		 LotteryOrder bet1 = getOrder();

		LotteryOrder bet2 = new LotteryOrder();

		betData.add(bet1);
		// betData.add(bet2);

		//myAddBetService.addBet(betData);

		verify(myOrderDao).insert(bet1);
		
		//myOrderDao.insert(bet1);
		
		
	}

}
