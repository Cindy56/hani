package com.test;

import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.entity.ResultData;
import com.game.hall.common.mapper.JsonMapper;
import com.game.hall.modules.bet.dao.LotteryOrderDao;
import com.game.hall.modules.sys.entity.Office;
import com.game.hall.modules.sys.entity.User;
import com.game.manager.modules.order.entity.LotteryOrder;

@RunWith(SpringJUnit4ClassRunner.class)
// @WebAppConfiguration
@ContextConfiguration("classpath*:spring*.xml")
public class LotteryBetController extends AbstractJUnit4SpringContextTests {

	private MockMvc mockMvc;

	// @Autowired
	// private com.game.hall.modules.bet.web.LotteryBetController myBetController;

	@Mock
	private com.game.hall.modules.bet.service.LotteryAddBetService myAddBetService;

	@Mock
	private LotteryOrderDao myOrder;

	@InjectMocks
	private com.game.hall.modules.bet.web.LotteryBetController myBetController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(myBetController).build();
	}

	public static LotteryOrder getOrder() {

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

		LotteryOrder bet1 = new LotteryOrder();
		//AddBetFormInput bet1 = new AddBetFormInput();
		bet1.preInsert();
		User user = new User();
		user.setId("00user");// 用户ID
		user.setName("00username");// 用户名
		Office company = new Office();
		company.setCode("code");// 组织编号
		user.setCompany(company);
		
//		bet1.setUserId("userId");
//		bet1.setUserName("userName");
		bet1.setOrgId("orgId");
		

		bet1.setAccountId("034f37416db44fa4a8ab05d98da6fa7d");
		String lotteryCode = "SSC_CQ";
		bet1.setLotteryCode(lotteryCode);
		BigDecimal betAmount = new BigDecimal(100);
		bet1.setBetAmount(betAmount);
		String betIssueNo = "20171117";
		bet1.setBetIssueNo(betIssueNo);
		String betType = "";
		bet1.setBetType(betType);
//		User currentUser = null;
//		bet1.setCurrentUser(currentUser);

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

		bet1.setOrderNum("10000001");
		bet1.setBetDetail("detail");
		bet1.setBetRate(1);
		bet1.setStatus("0");
		// Date createDate = new Date();
		// bet1.setCreateDate(createDate);
		// bet1.setCreateBy(user);
		// bet1.setUpdateBy(user);

		return bet1;
	}

	@Test
	public void testLotteryBetControllerMethodaddBet() {

		// try {
		// mockMvc.perform(get("/bet/bet/addbet").content("{\"title\":\"test blog
		// entry\"}")
		// .contentType(MediaType.APPLICATION_JSON))
		// .andExpect(status().isOk()) // 返回的状态是200
		// .andDo(print()) // 打印出请求和相应的内容
		// .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		//
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		List<LotteryOrder> lsbet = new ArrayList<LotteryOrder>();

		LotteryOrder bet1 = getOrder();

	//	List<AddBetFormInput> lsbet = new  ArrayList<AddBetFormInput>();
//		lsbet.add(bet1);
		
		lsbet.add(bet1);
		
		//String jsonStrng = JSON.toJSONString(betData);
		
		String jsonstr = JsonMapper.toJsonString(lsbet);
		
		System.out.println(jsonstr);

		LotteryOrder bet2 = new LotteryOrder();

		lsbet.add(bet2);
		// betData.add(bet2);

		// 测试，输入预想的输入in
		List<LotteryOrder> in = lsbet;

		ResultData rd1 = myBetController.addBet(in);

		// 验证，输出到目标object的出参out，是不是我们想要的

		List<LotteryOrder> out = new ArrayList<LotteryOrder>();
		LotteryOrder out2 = new LotteryOrder();
		out.add(out2);
		out.add(out2);

		ResultData rd2 = verify(myAddBetService).addBet(out);

		LotteryOrder entity = null;
		verify(myOrder).insert(entity);

	}

	@Test
	public void testLotteryAddBetServiceMethodaddBet() {

		List<LotteryOrder> lsbet = new ArrayList<LotteryOrder>();

		
		LotteryOrder bet1 = null;// getOrder();

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

		LotteryOrder bet2 = new LotteryOrder();

		lsbet.add(bet1);
		// betData.add(bet2);

		// myAddBetService.addBet(betData);

		// myAddBetService = new
		// com.game.hall.modules.bet.service.LotteryAddBetService();

		
		
		myBetController.addBet(lsbet);

		verify(myAddBetService).addBet(lsbet);
		verify(myAddBetService).addBet(lsbet);
		verify(myAddBetService).addBet(lsbet);
		verify(myAddBetService).addBet(lsbet);
		verify(myAddBetService).addBet(lsbet);
	}

}
