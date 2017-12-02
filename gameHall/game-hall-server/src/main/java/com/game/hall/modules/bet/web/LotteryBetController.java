/**
 * 
 */
package com.game.hall.modules.bet.web;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.ResultData;
import com.game.common.utils.IdGen;
import com.game.hall.modules.bet.service.LotteryAddBetService;
import com.game.hall.modules.bet.service.LotteryOrderManagerService;
import com.game.hall.modules.bet.service.OrderUtils;
import com.game.hall.modules.sys.utils.UserUtils;
import com.game.modules.finance.service.FinanceTradeDetailService;
import com.game.modules.lottery.entity.GameError;
import com.game.modules.lottery.entity.ResponseMsgData;
import com.game.modules.lottery.service.LotteryCalculateService;
import com.game.modules.lottery.service.LotteryPlayConfigService;
import com.game.modules.lottery.service.LotteryTimeNumService;
import com.game.modules.member.entity.MemberAccount;
import com.game.modules.member.service.MemberAccountService;
import com.game.modules.member.service.MemberPlayConfigService;
import com.game.modules.order.entity.LotteryOrder;
import com.game.modules.order.service.LotteryOrderService;
import com.game.modules.sys.entity.Office;
import com.game.modules.sys.entity.User;
import com.game.modules.sys.service.SystemServiceFacade;
import com.google.common.collect.Lists;
import com.test.testLotteryBetController;

/**
 * 投注
 * 
 * @author antonio
 */
@Controller
@RequestMapping("/bet/bet")
public class LotteryBetController {

	private static final Logger LOG = LoggerFactory.getLogger(LotteryBetController.class);
	
	private static final List<String> list = Lists.newArrayList();
	
	static {
		list.add("84581,20683,14211,68328,89281,24408,82222,23232,91427,05653");
		list.add("15523,45592,17826,96529,10710,88201,92304,09934,31027,42326,89068,50967,43890,04428,85621,18271,42630,41653,07681,86265,94493,10207,38912,98865,41697,07860,62948,40657,57143,97588,79200,80377,34481,22871,97042,25761,37246,95049,35340,62352,18264,55421,75443,04713,91691,55819,22438,32985,69874,85859,03007,89824,00176,09614,87305,94469,38567,32167,02680,37469,33435,62440,03648,22209,65374,19392,54594,98305,90688,27131,04868,06329,09441,21130,02065,74433,28833,62130,08746,13622,68124,72382,80651,63433,78670,27234,56723,32277,01059,08880,90277,73971,33735,90204,33281,74694,79206,03146,32615,40569");
		list.add("84763,21345,07545,56002,79798,18653,94924,43008,12212,38743,66255,22208,41754,78703,88573,78291,22261,44504,42790,84198,94751,03001,77218,24398,43683,84325,72908,04865,72166,04316,25941,19439,69442,44631,99107,05710,01101,73431,26015,52688,73812,24364,38531,50858,44721,03922,31232,31529,86162,92402,32388,01807,59410,10292,43133,30014,97100,27065,70714,97543,17294,50909,05197,73587,74259,86717,81367,84509,12331,96035,06877,78654,43631,10301,84527,93776,77751,48426,92044,77643,17796,08677,70313,42289,15706,92281,15621,56406,95396,83659,15421,38207,50938,24849,00685,66319,45830,10309,85383,28183,10185,57130,01147,41940,36073,92410,54436,14113,15689,12283,84117,77363,13151,54186,03484,45650,25216,32211,57320,40531,59370,70103,52506,41375,72611,08595,86396,63116,17629,17574,55212,77795,84305,04671,49149,65030,30217,88305,48577,91099,46264,24028,24219,75405,36095,23609,97188,71811,06118,12222,35411,95282,61384,81268,91947,23361,88707,26090,29117,72360,34014,13030,93379,43773,50460,00044,01511,42407,21511,49905,67251,08606,49692,71828,63582,48669,08815,42790,78068,85822,23210,30755,10019,41400,17357,90896,31448,69278,41945,84581,89368,08019,22507,09015,54275,15199,09817,34998,96058,28716");
		list.add("46501,34432,60663,73835,56103,66835,71136,83152,56151,93007,48477,89121,05686,01457,06835,55535,68184,58316,42540,46794,78784,31738,66470,74693,23908,55226,62517,44238,79936,49871,34049,20654,89078,98028,81737,44206,96781,06563,14280,34878,18640,07510,75834,48182,94319,31523,69409,45166,32243,78821,63423,37598,53732,72423,12253,75366,35419,96239,09981,48174,66879,63108,28779,36112,36652,74576,33672,56428,55141,77259,79158,09323,15732,15651,12185,79451,18332,56555,36627,93267,41822,69106,94996,25517,89130,96119,56968,04474,03780,59368,39793,01057,61982,51260,28187,34474,59161,09756,40605,68841");
		list.add("37980,77504,21854,26555,94282,89031,54607,08139,74222,09241,29530,14889,99924,19740,53157,82271,54679,89319,20080,22115,36504,33141,50081,77785,28310,53194,11634,95648,16970,61049,17234,27332,21613,11584,38543,92281,11492,59110,23010,37944,47039,81708,85208,52487,57151,85623,83849,11361,82542,12073,08012,96036,38331,94050,18961,69878,13574,71716,61055,44694,77919,05396,67514,86284,15042,76382,60637,02722,39481,56778,77694,59187,01316,15201,94172,56233,16849,70253,32782,12048,54584,23268,35503,00020,26896,59813,44883,42037,63269,06262,57296,72240,38491,79892,17031,75839,56655,98363,71147,42822,94615,68190,39667,71745,81928,21243,68670,64351,99230,96269,81564,90577,72371,65820,02339,82099,80619,18234,64618,42180,09111,97092,75933,65320,78809,09323,82482,87288,37978,52691,57691,57270,10410,96397,92047,03181,03990,61625,67162,81930,37833,95319,08964,97410,13081,50534,17145,00086,66565,48344,99589,05914,64385,74497,45138,27787,28545,49347,39792,98714,52946,76445,14995,00879,51920,72789,20625,58933,98760,34513,05828,58094,66455,98323,06566,13286,11758,14611,58220,48829,44219,69080,26982,08141,28703,44752,52865,76859,86226,28263,64558,67100,76334,95378,86385,28063,65741,66458,08602,92192,41004,50944,93646,39191,26054,32948,86211,37118,23288,79587,25818,54551,49161,12325,95952,77105,38263,29612,46664,30562,18417,24185,24105,73006,08536,09116,93623,51732,37669,77642,71840,21492,57121,44575,63140,04961,49877,66572,99823,56510,44195,79576,15377,05164,97500,00172,85459,57126,61560,74053,42779,38014,38220,28965,50246,68697,98450,62264,14457,45627,64473,70975,11931,90337,28327,21829,01928,50778,41064,41795,82253,04922,24906,08991,87755,78261,76212,42604,13441,09924,99065,21100,15980,01149,73141,54199,17607,80268,11791,78753,93668,97598,20136,00556,79262,26239,64748,85917,05052,26118");
		list.add("04853,71062,00639,81685,94153,45916,28022,08618,48161,07690,83181,29467,91073,63361,22024,63315,76872,61175,06243,71769,89395,42863,07111,91044,92211,79065,17118,97374,89007,36673,39077,18447,89011,22224,52603,18185,35062,19149,62667,87257,30269,74027,64639,73920,13474,28994,54896,84009,65683,81930,28089,97251,86936,01868,73659,87071,83117,87035,40608,75366,17563,31563,37211,43114,54352,64634,05177,69853,44375,59335,51748,79316,21041,45982,29673,15150,48197,22483,42092,39758,68862,86242,47542,13115,22965,28520,53296,02338,27397,88015,60005,41702,61307,62832,03506,77280,71885,30234,99624,24876,17921,62284,63963,26440,71898,75149,07166,74422,75275,79704,03082,99572,72448,46059,88569,47138,13720,49583,73746,47545,24493,18774,05501,94550,01964,32239,23685,73336,05521,48608,89501,31620,34883,65748,23496,43399,70681,74259,85661,60076,69656,81196,04481,41080,25305,42077,66241,26553,56147,00864");
	}
	
	// @Autowired
	// private LotteryOpenTodayService lotteryTimeNumService;

	@Autowired
	private LotteryAddBetService lotteryAddBetService;
	@Autowired
	private LotteryCalculateService lotteryCalculateService;

	// ---------------------test

	@Autowired
	LotteryPlayConfigService mylotteryPlayConfigService;

	@Autowired
	MemberPlayConfigService myMemberPlayConfigService;

	@Autowired
	MemberAccountService memberAccountService;

	@Autowired
	SystemServiceFacade systemServiceFacade;

	@Autowired
	private LotteryTimeNumService lotteryTimeNumService;
	@Autowired
	private LotteryOrderService lotteryOrderService;
	@Autowired
	private FinanceTradeDetailService financeTradeDetailService;
	@Autowired
	private LotteryOrderManagerService lotteryOrderManagerService;

	public String genOrderNo() {
		return String.valueOf(IdGen.randomLong());
	}

	public static void main(String[] args) {
		/*Random rand = new Random();
		// StringBuilder betNumber = new StringBuilder();
		// betNumber.append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10));

		SecureRandom random = new SecureRandom();

		for (int i = 0; i < 10000; i++) {
			int j = random.nextInt(2);

			if (j < 0)
				System.out.println(j);
		}

		System.out.println("end");*/
		
		 Random rand = new Random();

		/* StringBuilder betNumber = new StringBuilder();
		 for (int i = 0; i < 150; i++) {
			 betNumber.append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10));
			 betNumber.append(",");
		}
		 System.out.println(betNumber.substring(0,betNumber.length()-1));*/
		 for (int i = 0; i < 20000; i++) {
			System.out.println(rand.nextInt(list.size()));
		} 
		
	}

	// @RequiresPermissions("finance:financeRecharge:view")
	@RequestMapping(value = { "testAddBet" })
	@ResponseBody
	public ResultData testAddBet(LotteryOrder lotteryOrder, Model model) throws Exception {
		long startTime = System.currentTimeMillis();
		// =================模拟生成order
		LotteryOrder testOrder = new LotteryOrder();
		User currentUser = UserUtils.getUser();
		testOrder.setCurrentUser(currentUser);
		testOrder.setOrderNo(genOrderNo());// 订单编号
		testOrder.setUser(currentUser);
		
		testOrder.setCompanyId(currentUser.getCompany().getId());
		testOrder.setOfficeId(currentUser.getOffice().getId());
		testOrder.setLotteryCode("SSC_CQ");
		testOrder.setBetIssueNo(lotteryTimeNumService.findCurrentIssueNo("SSC_CQ").getLotteryIssueNo());
		MemberAccount currentAccount = memberAccountService.getByUserId(currentUser.getId());
		testOrder.setAccountId(currentAccount.getId());

		 Random rand = new Random();

		 StringBuilder betNumber = new StringBuilder();
		// betNumber.append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10));

		int randNum = testLotteryBetController.getInstance().getRandmonNum();

		String playCode = testLotteryBetController.getInstance().getRandmonPlayCode(randNum);

		//String betDetail = testLotteryBetController.getInstance().getRandmonBetDetail(randNum);

		
		 
		testOrder.setBetType("SSC_5XING_ZHIXUANDAN");

		testOrder.setBetDetail(list.get(rand.nextInt(list.size())));

		testOrder.setBetAmount(new BigDecimal(4));
		testOrder.setBetRate(2);
		testOrder.setPlayModeMoney(190000);
		testOrder.setPlayModeCommissionRate(new BigDecimal(0.03));
		testOrder.setPlayModeMoneyType("0");
		testOrder.setOrderSource("1");
		testOrder.setOrderType("1");
		testOrder.setSchemaId("xxxxxxxxxx");
		testOrder.setWinAmount(new BigDecimal(0));
		testOrder.setWithdrawAmount(new BigDecimal(0));
		testOrder.setStatus("0");

		// =================调用check
		ResponseMsgData result = this.lotteryCalculateService.checkOrder(testOrder);
		
		 if(!result.getIsSucceed()) {
			 return ResultData.error(result.getMsg());
		 }

		// =================入库
//		this.lotteryOrderService.save(testOrder);
		// =================扣钱
	/*//	boolean minusAmountResult = this.memberAccountService.minusAmount(currentAccount.getId(), testOrder.getBetAmount());
		if (BooleanUtils.isFalse(minusAmountResult)) {
			// TODO:扣款失败，返回异常提示

		}
		// =================生成流水,挪到返水服务里
		this.financeTradeDetailService.batchGenFinanceTradeDetail(Collections.singletonList(testOrder), FinanceTradeDetailType.BET_DEDUCTIONS);
*/
		this.lotteryOrderManagerService.testInOrder(testOrder);
		System.out.println("====================bet order lost time:" + (System.currentTimeMillis() - startTime));
		return ResultData.ok();
	}

	/*@ResponseBody
	@RequestMapping(value = "/addbet", method = RequestMethod.GET)
	public ResultData addBet(String jsbetData) {

		// =================模拟生成order
		String betIssueNo = lotteryTimeNumService.findCurrentIssueNo("SSC_CQ").getLotteryIssueNo();

		LotteryOrder testOrder = testLotteryBetController.getInstance().getRandmonOrder(betIssueNo);

		// ================设置用户，生成订单号
		testOrder.preInsert();
		User currentUser = UserUtils.getUser();
		//testOrder.setId(id);
		testOrder.setCurrentUser(currentUser);
		testOrder.setOrderNo(genOrderNo());//
		testOrder.setUser(currentUser);
		testOrder.setCompanyId(currentUser.getCompany().getId());
		testOrder.setOfficeId(currentUser.getOffice().getId());

		MemberAccount currentAccount = memberAccountService.getByUserId(currentUser.getId());
		testOrder.setAccountId(currentAccount.getId());

		List<LotteryOrder> lsOrders = new ArrayList<>();
		lsOrders.add(testOrder);

		System.out.println("1");


		ResultData rd = ResultData.ResultDataOK();

		for (int i = 0; i < lsOrders.size(); i++) {
			LotteryOrder lotOrder = lsOrders.get(i);
			lotOrder.setAccountId(memberAccountService.getByUserId(currentUser.getId()).getId());
			lotOrder.setOrderNo(OrderUtils.getOrderNo());

			// =============================前置校验 List<LotteryOrder> lsOrders
			ResponseMsgData responseMsgData = this.lotteryCalculateService.checkOrder(lotOrder);
			if (!responseMsgData.getIsSucceed()) {
				rd.setMessage(responseMsgData.getMsg());
				return rd;
			}

			// =============================save and deduct and Statement detail
			// List<LotteryOrder> lsOrders
			int bRet = lotteryAddBetService.addBet(lotOrder);

			if (bRet != 0) {
				rd.setErrorCode(bRet);
				rd.setMessage(GameError.getInstance().findErrorString(bRet));
				return rd;
			}

		}

		System.out.println("2");

		// BetData betData;

		if (ret != 0)
			return ResultData.ResultDataFail();

		// ---------------------

		// List<LotteryOrder> lsBetData = lsBetData;

		rd = ResultData.ResultDataOK();

		return rd;

	}*/

	@ResponseBody
	@RequestMapping(value = "/cancelorder", method = RequestMethod.POST)
	public ResultData cancelOrder(@RequestParam("orderIds") List<String> orderIds) {

		// List<LotteryOrder> betData = null;
		System.out.println("1");

		try {
			// 前置校验
			boolean ret = false;
		//	ResultData rd = ResultData.ResultDataOK();

		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

		System.out.println("2");

		// BetData betData;
		User user = UserUtils.getUser();
		user.preInsert();
		return lotteryAddBetService.cancelOrder(user, orderIds);
		// System.out.println();

	}

	// @ResponseBody
	// @RequestMapping(value = "/getopentoday", method = RequestMethod.GET)
	// public List<LotteryTimeNum> getOpenToday(String name) {
	//
	// ResultData rd = lotteryTimeNumService.OpenToday();
	// System.out.println();
	//
	// return null;
	// }
	//
	// @ResponseBody
	// @RequestMapping(value = "/getcur", method = RequestMethod.GET)
	// public List<LotteryTimeNum> getCur() {
	//
	// Date dt = new Date();
	//
	// List<LotteryTimeNum> openToday = lotteryTimeNumService.Cur(dt);
	//
	// return openToday;
	// }

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

		User user = new User();
		user.setId("00user");// 用户ID
		user.setName("00username");// 用户名
		Office company = new Office();
		company.setCode("code");// 组织编号
		user.setCompany(company);

		LotteryOrder bet1 = new LotteryOrder();
		bet1.setCurrentUser(user);
		// AddBetFormInput bet1 = new AddBetFormInput();
		bet1.preInsert();
		// bet1.setUserId("userId");
		// bet1.setUserName("userName");
		bet1.setCompanyId("orgId");
		bet1.setOfficeId("orgId");

		bet1.setAccountId("034f37416db44fa4a8ab05d98da6fa7d");
		String lotteryCode = "SSC_CQ";
		bet1.setLotteryCode(lotteryCode);
		BigDecimal betAmount = new BigDecimal(100);
		bet1.setBetAmount(betAmount);
		String betIssueNo = "20171125083";
		bet1.setBetIssueNo(betIssueNo);
		String betType = "SSC_5_ZHIXUANFUSHI";
		bet1.setBetType(betType);
		// User currentUser = null;
		// bet1.setCurrentUser(currentUser);

		String orderSource = "0";
		bet1.setOrderSource(orderSource);
		String orderType = "2";
		bet1.setOrderType(orderType);
		BigDecimal playModeCommissionRate = new BigDecimal(0);
		bet1.setPlayModeCommissionRate(playModeCommissionRate);
		Integer playModeMoney = 1960;
		bet1.setPlayModeMoney(playModeMoney);
		String playModeMoneyType = "0";
		bet1.setPlayModeMoneyType(playModeMoneyType);

		// bet1.setOrderNum("10000001");
		bet1.setBetDetail("detail");
		bet1.setBetRate(1);
		bet1.setStatus("0");
		// Date createDate = new Date();
		// bet1.setCreateDate(createDate);
		// bet1.setCreateBy(user);
		// bet1.setUpdateBy(user);

		return bet1;
	}

}
