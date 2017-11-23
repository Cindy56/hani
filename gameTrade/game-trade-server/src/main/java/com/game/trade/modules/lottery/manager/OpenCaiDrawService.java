package com.game.trade.modules.lottery.manager;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.game.common.mapper.JsonMapper;
import com.game.common.utils.StringUtils;
import com.game.modules.lottery.dto.OpenCaiResp;
import com.game.modules.lottery.dto.OpenCaiResult;
import com.game.modules.lottery.exception.LotteryNumDrawException;

@Service
public class OpenCaiDrawService implements  LotteryNumDrawService{
//	@Autowired @Qualifier("restTemplate")  
//	@Resource(name = "restTemplate")
//	private  RestTemplate restTemplate;
	
	@Override
	public OpenCaiResult drawLotteryNum(String lotteryCode, String issueNo) throws LotteryNumDrawException {
		String urlurl = "http://d.apiplus.net/newly.do?token=TC173E9F673C13AK&code=cqssc&format=json";
//		OpenCaiDTO.Req req = new OpenCaiDTO().new Req();
//		req.setUrl("http://d.apiplus.net/newly.do");
//		req.setToken("TC173E9F673C13AK");
//		req.setCode("cqssc");
//		req.setRows("20");
//		req.setFormat("json");
		
		RestTemplate restTemplate = new RestTemplate();
//		RestTemplate restTemplate =  SpringContextHolder.getBean("restTemplate");
		try {
			
			ResponseEntity<String> response = restTemplate.getForEntity(urlurl, String.class);
			
			if(StringUtils.isEmpty(response.getBody())) {
				throw new LotteryNumDrawException();
			}
			
			OpenCaiResp resp =	JsonMapper.getInstance().fromJson(response.getBody(), OpenCaiResp.class);
			for (OpenCaiResult lotteryNum : resp.getData()) {
				if(issueNo.equals(lotteryNum.getExpect())) {
					return lotteryNum;
				}
			}
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new LotteryNumDrawException(e.getCause());
		}
	}
}
