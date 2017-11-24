package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.game.hall.modules.mailsite.entity.MailSite;
import com.game.modules.sys.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
// @WebAppConfiguration
@ContextConfiguration("classpath*:spring*.xml")
public class MailSiteService extends AbstractJUnit4SpringContextTests {

	@Autowired
	com.game.hall.modules.mailsite.service.MailSiteService myMailService;

	private com.game.hall.modules.mailsite.web.MailSiteController myMailController;

	@Test
	public void testMailSiteService() {

		MailSite bet1 = getMailSite();

		// String jsonstr = JsonMapper.toJsonString(lsbet);

		// System.out.println(jsonstr);

		// 测试，输入预想的输入in
		MailSite in = bet1;

		myMailController = new com.game.hall.modules.mailsite.web.MailSiteController();
		myMailService.save(in);

		// ResultData rd1 = myMailController.getMailContent(mailId);
		// ResultData rd1 = myMailController.getMailContent(mailId);

		// 验证，输出到目标object的出参out，是不是我们想要的

		// verify(myMailSiteDao).insert(entity);

	}

	private MailSite getMailSite() {
		// TODO Auto-generated method stub

		MailSite mail = new MailSite();

		mail.setUserId("userID");
		mail.preInsert();

		User user = new User();
		user.setId("userid");
		mail.setCreateBy(user);
		mail.setUpdateBy(user);

		mail.setContext("context");
		mail.setSender("sender");
		mail.setReceiver("to");
		mail.setId("id");
		mail.setLabel("label");
		mail.setTitle("title");

		mail.setDelFlag("0");
		mail.setReadFlag("0");

		mail.setIsNewRecord(true);
		return mail;
	}

}
