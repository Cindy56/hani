/**
 * 
 */
package com.game.manager.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.game.manager.modules.sys.service.OfficeService;
import com.game.modules.sys.entity.Office;
import com.game.modules.sys.service.OfficeServiceFacade;
import com.game.modules.sys.service.SystemServiceFacade;

/**
 * 测试
 * @author freemam
 * 2017年11月24日 下午3:32:20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class OfficeTest {

	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private SystemServiceFacade systemServiceFacade;
	
	
	
	/*@Before
	public void init() {
		officeService = SpringContextHolder.getBean("officeService");
	}*/
	
	//公司模板id
	private final static String COMPANY_TEMPLET_ID="ce6f9bcd5512471cabd6a281d4f0f19c";
	
	@Test
	public void officeA() {
		Office off=officeService.get(COMPANY_TEMPLET_ID);
		System.out.println(off.getId());
	}
	
	@Test
	public void findRoleByOfficeId() {
		String officeId="ce6f9bcd5512471cabd6a281d4f0f19c";
		systemServiceFacade.findRoleByOfficeId(officeId);
	}
	
	@Test
	public void addMember() {
		
	}
	
}
