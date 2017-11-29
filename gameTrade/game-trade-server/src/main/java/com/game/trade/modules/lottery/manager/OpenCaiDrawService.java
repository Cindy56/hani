package com.game.trade.modules.lottery.manager;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.game.common.config.Global;
import com.game.common.mapper.JsonMapper;
import com.game.common.utils.StringUtils;
import com.game.modules.lottery.dto.OpenCaiResp;
import com.game.modules.lottery.dto.OpenCaiResult;
import com.game.modules.lottery.exception.LotteryNumDrawException;

@Service
public class OpenCaiDrawService implements  LotteryNumDrawService{
	private static final Logger logger = LoggerFactory.getLogger(TimeTaskService.class);

//	@Autowired @Qualifier("restTemplate")  
//	@Autowired
//	private  RestTemplate restTemplate;
	
	@Override
	public OpenCaiResult drawLotteryNum(String lotteryCode, String issueNo) throws LotteryNumDrawException {
		
//		OpenCaiDTO.Req req = new OpenCaiDTO().new Req();
//		req.setUrl("http://d.apiplus.net/newly.do");
//		req.setToken("TC173E9F673C13AK");
//		req.setCode("cqssc");
//		req.setRows("20");
//		req.setFormat("json");
		String url = null;
		if(LotteryCodeConstants.SSC_CQ.equals(lotteryCode)){
			 url = Global.getConfig("open.ssccq.url");
		}else if(LotteryCodeConstants.SSC_TJ.equals(lotteryCode)){
			 url = Global.getConfig("open.ssctj.url");
		}else if(LotteryCodeConstants.SSC_XJ.equals(lotteryCode)) {
			 url = Global.getConfig("open.sscxj.url");
		}else if(LotteryCodeConstants.PK10_BJ.equals(lotteryCode)) {
			 url = Global.getConfig("open.sscbjpk.url");
		}
		RestTemplate restTemplate = new RestTemplate();
//		RestTemplate restTemplate =  SpringContextHolder.getBean("restTemplate");
		try {
			
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			
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
			logger.error("[拉奖接口异常] url： {},错误信息：{}",url,e.getMessage());
			throw new LotteryNumDrawException(e.getCause());
		}
	}

	@Override
	public OpenCaiResult drawLotteryNum(String lotteryCode) throws LotteryNumDrawException {
		String url = null;
		RestTemplate restTemplate = new RestTemplate();
		if(LotteryCodeConstants.SSC_CQ.equals(lotteryCode)){
			 url = Global.getConfig("open.ssccq.url");
		}else if(LotteryCodeConstants.SSC_TJ.equals(lotteryCode)){
			 url = Global.getConfig("open.ssctj.url");
		}else if(LotteryCodeConstants.SSC_XJ.equals(lotteryCode)) {
			 url = Global.getConfig("open.sscxj.url");
		}else if(LotteryCodeConstants.PK10_BJ.equals(lotteryCode)) {
			 url = Global.getConfig("open.sscbjpk.url");
		}
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			if(StringUtils.isEmpty(response.getBody())) {
				throw new LotteryNumDrawException();
			}
			OpenCaiResp resp =	JsonMapper.getInstance().fromJson(response.getBody(), OpenCaiResp.class);
			if(null != resp) {
				if(CollectionUtils.isNotEmpty(resp.getData())) {
					return resp.getData().get(0);
				}
			}
			return null;
		} catch (Exception e) {
			logger.error("[拉奖接口异常] url： {},错误信息：{}",url,e.getMessage());
			throw new LotteryNumDrawException(e.getCause());
		}
	}
}
