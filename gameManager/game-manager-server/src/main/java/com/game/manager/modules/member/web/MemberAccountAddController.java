/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.member.web;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.game.common.config.Global;
import com.game.common.mapper.JsonMapper;
import com.game.common.utils.StringUtils;
import com.game.common.web.BaseController;
import com.game.manager.modules.sys.service.SystemService;
import com.game.manager.modules.sys.utils.UserUtils;
import com.game.modules.lottery.entity.LotteryPlayConfig;
import com.game.modules.member.entity.MemberAccount;
import com.game.modules.member.entity.MemberAccountOpenDto;
import com.game.modules.member.entity.MemberPlayConfig;
import com.game.modules.member.service.MemberAccountService;
import com.game.modules.member.service.MemberPlayConfigService;
import com.game.modules.sys.entity.Office;
import com.game.modules.sys.entity.Role;
import com.game.modules.sys.entity.User;

/**
 * 会员开户Controller
 * @author freeman
 * @version 2017-11-17
 */
@Controller
@RequestMapping(value = "${adminPath}/memberadd/memberAccount")
public class MemberAccountAddController extends BaseController {
	
	//代理
	private static final String AGENCY = "agency";
	//玩家
	private static final String PLAYER = "gameplayer";

	@Autowired
	private MemberAccountService memberAccountService;
	
	@Autowired
	private MemberPlayConfigService memberPlayConfigService;
	
	@Autowired
	private SystemService systemService;
	
	@ModelAttribute
	public MemberAccountOpenDto get(@RequestParam(required=false) String id) {
		MemberAccountOpenDto formObj = new MemberAccountOpenDto();
		MemberAccount account = null;
		if (StringUtils.isNotBlank(id)){
			account = memberAccountService.get(id);
		}
		if (account == null){
			account = new MemberAccount();
		}
		
		formObj.setAccount(account);
		formObj.setUser(null);
		formObj.setPlayList(null);
		
		return formObj;
	}
	
	/**
	 * 返回用户可选返点信息
	 * @param memberAccountOpenDto
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("memberadd:memberAccount:view")
	@RequestMapping(value = {"list", ""})
	public String list(MemberAccountOpenDto memberAccountOpenDto, HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<MemberAccount> page = memberAccountService.findPage(new Page<MemberAccount>(request, response), memberAccount); 
//		model.addAttribute("page", page);
		
		//获取当前用户信息 从seesion取出
		User seesionUser = UserUtils.getUser();
		//当前登录用户id
		String seesionUserId=seesionUser.getId();
		//查询当前登录用户的玩法配置
		MemberPlayConfig memberPlayConfig=memberPlayConfigService.getMemberPlayConfigByUserId(seesionUserId);
		//找出所有彩种
		Map<String, List<LotteryPlayConfig>> repeatMap=new HashMap<>();
		
		if(null!=memberPlayConfig&&StringUtils.isNotBlank(memberPlayConfig.getPlayConfig())) {
			
			String playConfig=memberPlayConfig.getPlayConfig();
			//包含当前登录用户的玩法配置
			JsonMapper jsonMapper = JsonMapper.getInstance();
			List<LotteryPlayConfig> playConfigList  =  jsonMapper.fromJson(playConfig, jsonMapper.createCollectionType(List.class, LotteryPlayConfig.class));
			for (LotteryPlayConfig lottery : playConfigList) {
				if(repeatMap.containsKey(lottery.getLotteryCode().getName())) {
					List repList = repeatMap.get(lottery.getLotteryCode().getName());
					repList.add(lottery);
				}else {
					List<LotteryPlayConfig> list1 = new ArrayList<>();
					list1.add(lottery);
					repeatMap.put(lottery.getLotteryCode().getName(), list1);
				}
			}
			Set<Entry<String, List<LotteryPlayConfig>>> setEntry = repeatMap.entrySet();
			for (Entry<String, List<LotteryPlayConfig>> entry : setEntry) {
				List<LotteryPlayConfig> repeatList=entry.getValue();
				
				for (LotteryPlayConfig lottery : repeatList) {
					//循环每种玩法
					//中奖几率
					BigDecimal winningProbability=new BigDecimal(lottery.getWinningProbability());
					//最大返水
					BigDecimal commissionRateMax=lottery.getCommissionRateMax();
					//最小返水
					BigDecimal commissionRateMin=lottery.getCommissionRateMin();
					//每种玩法的list
					List<Map> groupList=new ArrayList<Map>();
					
					while (commissionRateMax.compareTo(commissionRateMin)>=0) {
						//循环算出玩法的奖金与返点
						BigDecimal awardMoney=new BigDecimal(2).divide(winningProbability,3).multiply(new BigDecimal(1).subtract(commissionRateMin));
						DecimalFormat dfFormat = new DecimalFormat("0.000"); 
						String Money=dfFormat.format(awardMoney);
						
						Map<String, Object> awardMap=new HashMap<String, Object>();
						//奖金
						//awardMap.put("awardMoney", awardMoney);
						awardMap.put("awardMoney", Money);
						//返点百分比
						awardMap.put("commissionRate", commissionRateMin);
						//.multiply(new BigDecimal(100))
						//把每种返点添加到groupList中
						groupList.add(awardMap);
						commissionRateMin=commissionRateMin.add(new BigDecimal("0.001"));
					}
					if(null!=lottery.getMap()) {
						lottery.getMap().put("awardList", groupList);
					}else {
						lottery.setMap(new HashMap());
						lottery.getMap().put("awardList", groupList);
					}
					
				}
			}
		}
		////////////
		
		
		//查询返点信息
//		List<Map<String, Object>> list=memberAccountService.getLotteryPlayConfig();
		
//		String repeaList=JSON.toJSONString(list);
		
//		List<Map> resetList=new ArrayList<Map>();//放所有彩种
		
		//找出所有彩种
		/*Map<String, List<Map<String, Object>>> repeatMap=new HashMap<>();
		for (Map map : list) {
			if(repeatMap.containsKey(map.get("parentName"))) {
				List repList = repeatMap.get(map.get("parentName"));
				repList.add(map);
			}else {
				List<Map<String, Object>> list1 = new ArrayList<>();
				list1.add(map);
				repeatMap.put(map.get("parentName").toString(), list1);
			}
		}
		Set<Entry<String, List<Map<String, Object>>>> setEntry = repeatMap.entrySet();
		for (Entry<String, List<Map<String, Object>>> entry : setEntry) {
			List<Map<String, Object>> repeatList=entry.getValue();
			
			for (Map<String, Object> map : repeatList) {
				//循环每种玩法
//				List<Map> playList=new ArrayList<Map>();
//				Map<String, Object> resetMap=new HashMap<String, Object>();
				map.get("lotteryCode");//彩票代码
				String parentName=map.get("parentName").toString();//彩票名称
				map.get("playCode");//玩法代码
				map.get("lotteryName");//玩法名称
				map.get("playType");//玩法模式:0 直选返点	1 不定位返点 2 所有返点 3 趣味型返点 	
				BigDecimal winningProbability=new BigDecimal(map.get("winningProbability").toString());//中奖几率
				BigDecimal commissionRateMax=new BigDecimal(map.get("commissionRateMax").toString());//最大返水
				BigDecimal commissionRateMin=new BigDecimal(map.get("commissionRateMin").toString());//最小返水
				
				int cha=commissionRateMax.subtract(commissionRateMin).multiply(new BigDecimal(10)).intValue();
				
				List<Map> groupList=new ArrayList<Map>();//每种玩法的list
				
//				Map<String, Object> groupMap=new HashMap<String, Object>();
				while (commissionRateMax.compareTo(commissionRateMin)>=0) {
					//循环算出玩法的奖金与返点
					BigDecimal awardMoney=new BigDecimal(2).divide(winningProbability,3).multiply(new BigDecimal(1).subtract(commissionRateMin.divide(new BigDecimal(100))));
					System.out.println(awardMoney);
					Map<String, Object> awardMap=new HashMap<String, Object>();
					awardMap.put("awardMoney", awardMoney);//奖金
					awardMap.put("commissionRate", commissionRateMin);//返点百分比
					groupList.add(awardMap);//把每种返点添加到groupList中
					commissionRateMin=commissionRateMin.add(new BigDecimal("0.1"));
				}
				map.put("awardList", groupList);
//				groupMap.put("groupList", groupList);
//				groupMap.put("playName", map.get("name"));//玩法名称
//				groupMap.put("playCode", map.get("playCode"));//玩法代码
//				
//				playList.add(groupMap);//一种玩法
//				
//				resetMap.put("groupList", playList);//
//				resetMap.put("parentName",parentName);//彩票名称
//				resetMap.put("lotteryCode", map.get("lotteryCode"));
//				resetList.add(resetMap);//将彩种添加到resetList中
			}
		}*/
		//奖金组=2÷单注中奖概率×（1-平台利润）
//		memberAccountOpenDto.setPlayList(playList);
		model.addAttribute("repeatMap",repeatMap);
		return "modules/member/memberAccountBack";
	}

	@RequiresPermissions("memberadd:memberAccount:view")
	@RequestMapping(value = "form")
	public String form(MemberAccount memberAccount, Model model) {
		model.addAttribute("memberAccount", memberAccount);
		return "modules/member/memberAccountForm";
	}

	/**
	 * 保存用户开户信息
	 * @param memberAccount
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("memberadd:memberAccount:edit")
	@RequestMapping(value = "save")
	@Transactional(readOnly=false)
	public String save(MemberAccountOpenDto memberAccountOpenDto, Model model, RedirectAttributes redirectAttributes) {//,MemberAccount memberAccount
		MemberAccount memberAccount=memberAccountOpenDto.getAccount();
		if (!beanValidator(model, memberAccount)){
			model.addAttribute("memberAccount", memberAccount);
			return "modules/member/memberAccountBack";
		}
		
		//获取当前用户信息 从seesion取出
		User seesionUser = UserUtils.getUser();
		User user=memberAccountOpenDto.getUser();
		//数据库查找登录名称是否存在
		String userLoginName=user.getLoginName();
		if(null!=systemService.getUserByLoginName(userLoginName)) {
			addMessage(redirectAttributes, "登录名称已存在！");
			return "modules/member/memberAccountBack";
		}
		//新开户用户设置和开户者同一个公司
		user.setCompany(seesionUser.getCompany());
		//新开户用户设置和开户者同一个机构
		user.setOffice(seesionUser.getOffice());
		
		if (!beanValidator(model, user)){
			model.addAttribute("memberAccount", memberAccount);
			return "redirect:"+Global.getAdminPath()+"/memberadd/memberAccount/";
		}
		user.setPassword(SystemService.entryptPassword(user.getPassword()));
		
		user.setMobile(memberAccount.getMobileNo());
		//用户类型为普通用户
		user.setUserType("3");
		//删除标志
		user.setDelFlag("0");
		
		//用户角色根据会员类型取值
		//会员类型2代理3玩家
		String accountType=memberAccount.getAccountType();
		if("2".equals(accountType)) {
			//查询代理角色
			Role agency=systemService.getRoleByEnname(AGENCY);
			user.getRoleList().add(agency);
		}else {
			//查询玩家角色
			Role gameplayer=systemService.getRoleByEnname(PLAYER);
			user.getRoleList().add(gameplayer);
		}
		
		//保存用户信息
		user.setCurrentUser(UserUtils.getUser());
		systemService.saveUser(user);
		
		memberAccount.setParentAgentId(seesionUser.getId());
		memberAccount.setParentAgentIds(seesionUser.getId()+","+user.getId());
		memberAccount.setOrgId(new Office());
		memberAccount.setBlance("0");
		memberAccount.setBlanceFrozen("0");
		memberAccount.setStatus("0");
		memberAccount.setOrgId(user.getOffice());
		memberAccount.setUser(user);
		memberAccount.setSecPassword(SystemService.entryptPassword(memberAccount.getSecPassword()));
		
		//保存会员信息
		memberAccount.setCurrentUser(UserUtils.getUser());
		memberAccount=memberAccountService.save(memberAccount);
		
		//保存用户的返点信息
		List<LotteryPlayConfig> playConfigList=memberAccountOpenDto.getPlayList();
		
		MemberPlayConfig memberPlayConfig=new MemberPlayConfig();
		memberPlayConfig.setUser(user);
		memberPlayConfig.setAccountId(memberAccount.getId());
		memberPlayConfig.setPlayConfig(JsonMapper.toJsonString(playConfigList));
		memberPlayConfig.setUserName(user.getName());
		memberPlayConfig.setAccountId(memberAccount.getId());
		memberPlayConfig.setCurrentUser(UserUtils.getUser());
		memberPlayConfigService.save(memberPlayConfig);
		addMessage(redirectAttributes, "保存会员信息成功");
		return "redirect:"+Global.getAdminPath()+"/memberadd/memberAccount/";
	}
	
	@RequiresPermissions("memberadd:memberAccount:edit")
	@RequestMapping(value = "delete")
	public String delete(MemberAccount memberAccount, RedirectAttributes redirectAttributes) {
		memberAccountService.delete(memberAccount);
		addMessage(redirectAttributes, "删除会员信息成功");
		return "redirect:"+Global.getAdminPath()+"/member/memberAccount/?repage";
	}

}