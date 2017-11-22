package com.game.manager.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

import com.game.common.test.BaseMvcJunitTest;


public class MockTestController extends BaseMvcJunitTest {
	   @Test
	   public void getAllCategoryTest() throws Exception {
/*		   String responseString = mockMvc.perform(
				   get("/user/showUser1")
	               .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
	               .param("id","1")
	           ).andExpect(status().isOk())    //返回的状态是200
		   		.andDo(print())         //打印出请求和相应的内容
		   		.andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
		   
		   System.out.println("-----返回的json = " + responseString);*/
	   }
}
