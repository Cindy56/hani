package com.game.trade.modules;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.game.modules.sys.entity.Office;
import com.game.modules.sys.service.OfficeServiceFacade;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class OfficeTest {

	@Autowired
	private OfficeServiceFacade officeServiceFacade;
	
	//公司模板id
	private final static String COMPANY_TEMPLET_ID="ce6f9bcd5512471cabd6a281d4f0f19c";
	
	@Test
	public void findaaa() {
		List<Office> list = officeServiceFacade.findOfficesByParentId(COMPANY_TEMPLET_ID);
		System.out.println(list);
	}
}
