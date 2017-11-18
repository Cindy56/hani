/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.member.web;

import java.math.BigDecimal;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.game.manager.common.config.Global;
import com.game.manager.common.utils.StringUtils;
import com.game.manager.common.web.BaseController;
import com.game.manager.modules.member.entity.MemberAccount;
import com.game.manager.modules.member.service.MemberAccountService;
import com.game.manager.modules.sys.entity.Office;
import com.game.manager.modules.sys.entity.User;
import com.game.manager.modules.sys.service.SystemService;
import com.game.manager.modules.sys.utils.UserUtils;

/**
 * 会员开户Controller
 * @author freeman
 * @version 2017-11-17
 */
@Controller
@RequestMapping(value = "${adminPath}/memberadd/memberAccount")
public class MemberAccountAddController extends BaseController {

	@Autowired
	private MemberAccountService memberAccountService;
	
	@Autowired
	private SystemService systemService;
	
	@ModelAttribute
	public MemberAccount get(@RequestParam(required=false) String id) {
		MemberAccount entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = memberAccountService.get(id);
		}
		if (entity == null){
			entity = new MemberAccount();
		}
		return entity;
	}
	
	@RequiresPermissions("memberadd:memberAccount:view")
	@RequestMapping(value = {"list", ""})
	public String list(MemberAccount memberAccount, HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<MemberAccount> page = memberAccountService.findPage(new Page<MemberAccount>(request, response), memberAccount); 
//		model.addAttribute("page", page);
		//查询返点信息
		List<Map<String, Object>> list=memberAccountService.getLotteryPlayConfig();
		
//		List<Map> resetList=new ArrayList<Map>();//放所有彩种
		
		//找出所有彩种
		Map<String, List<Map<String, Object>>> repeatMap=new HashMap<>();
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
		}
		//奖金组=2÷单注中奖概率×（1-平台利润）
		model.addAttribute("repeatMap",repeatMap);
		return "modules/member/memberAccountBack";
	}

	@RequiresPermissions("memberadd:memberAccount:view")
	@RequestMapping(value = "form")
	public String form(MemberAccount memberAccount, Model model) {
		model.addAttribute("memberAccount", memberAccount);
		return "modules/member/memberAccountForm";
	}

	@RequiresPermissions("memberadd:memberAccount:edit")
	@RequestMapping(value = "save")
	public String save(MemberAccount memberAccount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, memberAccount)){
			return form(memberAccount, model);
		}
		//获取当前用户信息 从seesion取出
		User seesionUser = UserUtils.getUser();
		User user=memberAccount.getUser();
		//新开户用户设置和开户者同一个公司
		user.setCompany(seesionUser.getCompany());
		//新开户用户设置和开户者同一个机构
		user.setOffice(seesionUser.getOffice());
		
		if (!beanValidator(model, user)){
			return form(memberAccount, model);
		}
		/*id, 
		companyId, 
		office_id, 
		login_name, 
		password, 
		no, 
		name, 
		email, 
		phone, 
		mobile, 
		user_type, 
		create_by, 
		create_date, 
		update_by, 
		update_date, 
		remarks, 
		login_flag, 
		photo, 
		del_flag*/
		
		user.setCompany(seesionUser.getCompany());
		user.setOffice(seesionUser.getOffice());
		//user.setLoginName(user.getName());
		user.setPassword(SystemService.entryptPassword(user.getPassword()));
		user.setNo("");
		user.setName(user.getLoginName());
		user.setEmail("");
		user.setPhone("");
		user.setMobile(memberAccount.getMobileNo());
		user.setUserType("3");
		user.setRemarks("");
		
		/*List<Role> li=user.getRoleList();
		li.add(new Role());
		
		user.setRoleList(li);*/
		
		user.setRoleList(seesionUser.getRoleList());
		
		user.setLoginFlag(seesionUser.getLoginFlag());// 是否允许登陆	
		user.setDelFlag("0");//删除标志
		
		//保存会员信息
		systemService.saveUser(user);
		
		memberAccount.setParentAgentId("223456432132146546");
		memberAccount.setOrgId(new Office());
		memberAccount.setBlance("0");
		memberAccount.setBlanceFrozen("0");
		memberAccount.setStatus("0");
		
		memberAccount.setParentAgentIds(memberAccount.getParentAgentIds() + "," +memberAccount.getId());
		
		memberAccountService.save(memberAccount);
		addMessage(redirectAttributes, "保存会员信息成功");
		return "redirect:"+Global.getAdminPath()+"/member/memberAccount/?repage";
	}
	
	@RequiresPermissions("memberadd:memberAccount:edit")
	@RequestMapping(value = "delete")
	public String delete(MemberAccount memberAccount, RedirectAttributes redirectAttributes) {
		memberAccountService.delete(memberAccount);
		addMessage(redirectAttributes, "删除会员信息成功");
		return "redirect:"+Global.getAdminPath()+"/member/memberAccount/?repage";
	}

}