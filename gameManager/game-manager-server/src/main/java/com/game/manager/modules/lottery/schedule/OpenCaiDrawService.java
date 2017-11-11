package com.game.manager.modules.lottery.schedule;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.manager.common.utils.StringUtils;
import com.game.manager.modules.lottery.dto.OpenCaiDTO;
import com.game.manager.modules.lottery.dto.OpenCaiResp;
import com.game.manager.modules.lottery.dto.OpenCaiResult;
import com.game.manager.modules.lottery.exception.LotteryNumDrawException;

@Service
public class OpenCaiDrawService implements  LotteryNumDrawService{
//	@Autowired @Qualifier("restTemplate")  
//	@Resource(name = "restTemplate")
//	private  RestTemplate restTemplate;
	
	@Override
	public String drawLotteryNum(String lotteryCode, String issueNo) throws LotteryNumDrawException {
		String urlurl = "http://d.apiplus.net/newly.do?token=TC173E9F673C13AK&code=cqssc&format=json";
		OpenCaiDTO.Req req = new OpenCaiDTO().new Req();
		req.setUrl("http://d.apiplus.net/newly.do");
		req.setToken("TC173E9F673C13AK");
		req.setCode("cqssc");
		req.setRows("20");
		req.setFormat("json");
		
		RestTemplate restTemplate = new RestTemplate();
//		RestTemplate restTemplate =  SpringContextHolder.getBean("restTemplate");
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			
//			ResponseEntity<String> xxxxx = restTemplate.postForEntity(req.getUrl(), objectMapper.writeValueAsString(req), String.class);
//			ResponseEntity<String> xxxxx = restTemplate.postForEntity(req.getUrl(), objectMapper.writeValueAsString(req), String.class);
			ResponseEntity<String> xxxxx = restTemplate.getForEntity(urlurl, String.class);
			
			if(StringUtils.isEmpty(xxxxx.getBody())) {
				throw new LotteryNumDrawException();
			}
			
			OpenCaiResp resp =	JSON.parseObject(xxxxx.getBody(),OpenCaiResp.class);
			//OpenCaiDTO.Resp resp = objectMapper.convertValue(xxxxx.getBody(), OpenCaiDTO.Resp.class);
			
//			ResponseEntity<OpenCaiDTO.Resp> resp = restTemplate.postForEntity(req.getUrl(), objectMapper.writeValueAsString(req), OpenCaiDTO.Resp.class);
//			if(null == resp || CollectionUtils.isEmpty(resp.getBody().getData())) {
//				throw new LotteryNumDrawException();
//			}
//			for (OpenCaiDTO.LotteryNum lotteryNum : resp.getBody().getData()) {
			for (OpenCaiResult lotteryNum : resp.getData()) {
				if(issueNo.equals(lotteryNum.getExpect())) {
					return lotteryNum.getOpencode();
				}
			}
			throw new LotteryNumDrawException();
		} catch (Exception e) {
			e.printStackTrace();
			throw new LotteryNumDrawException(e.getCause());
		}
	}
	
	public static void main(String[] args) {
		OpenCaiResp resp = new OpenCaiResp();
		resp.setCode("xxxxxx");
//		resp.setData(Collections.singleton(new OpenCaiDTO().new LotteryNum()));
		String xxxx = JSON.toJSONString(resp);
		System.out.println(xxxx);
		
		OpenCaiResp resp02 = JSON.parseObject(xxxx,OpenCaiResp.class);
		System.out.println(resp02);
		
		
	}
}
