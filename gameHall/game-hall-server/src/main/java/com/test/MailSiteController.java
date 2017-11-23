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
import com.game.hall.modules.mailsite.dao.MailSiteDao;
import com.game.hall.modules.mailsite.entity.MailSite;
import com.game.hall.modules.sys.entity.Office;
import com.game.hall.modules.sys.entity.User;
import com.game.manager.modules.order.entity.LotteryOrder;

@RunWith(SpringJUnit4ClassRunner.class)
// @WebAppConfiguration
@ContextConfiguration("classpath*:spring*.xml")
public class MailSiteController extends AbstractJUnit4SpringContextTests {

	private MockMvc mockMvc;

	// @Autowired
	// private com.game.hall.modules.bet.web.LotteryBetController myBetController;

	@Mock
	private com.game.hall.modules.mailsite.service.MailSiteService myMailService;

	@Mock
	private MailSiteDao myMailSiteDao;

	@InjectMocks
	private com.game.hall.modules.mailsite.web.MailSiteController myMailController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(myMailController).build();
	}

	public static MailSite getMailSite() {

		MailSite mail = new MailSite();

		return mail;
	}

	@Test
	public void testMailSiteController() {

	

	
		MailSite bet1 = getMailSite();

	
	//	String jsonstr = JsonMapper.toJsonString(lsbet);

	//	System.out.println(jsonstr);

		
		// 测试，输入预想的输入in
		MailSite in = bet1;

//		ResultData rd1 = myMailController.addBet(in);

		// 验证，输出到目标object的出参out，是不是我们想要的

		List<LotteryOrder> out = new ArrayList<LotteryOrder>();
		LotteryOrder out2 = new LotteryOrder();
		out.add(out2);
		out.add(out2);

//		ResultData rd2 = verify(myAddBetService).addBet(out);
//
//		LotteryOrder entity = null;
//		verify(myMailSiteDao).insert(entity);

	}


}
