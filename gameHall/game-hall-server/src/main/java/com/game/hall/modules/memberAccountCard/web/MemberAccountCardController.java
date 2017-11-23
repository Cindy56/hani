package com.game.hall.modules.memberAccountCard.web;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drew.lang.StringUtil;
import com.game.hall.common.utils.StringUtils;
import com.game.hall.modules.memberAccountCard.entity.MemberAccountCard;
import com.game.hall.modules.memberAccountCard.service.MemberAccountCardService;
import com.game.hall.modules.sys.entity.User;
import com.game.hall.utils.PassWordUtils;






@Controller
@RequestMapping(value = "/memberAccount")
public class MemberAccountCardController  {

	
	//银行卡状态
	private static final String NORMAL = "1";//1正常
	private static final String FREEZE = "2";//2冻结
	
	
	
	@Autowired
	private MemberAccountCardService memberAccountCardService;
	//根据账户id查询此账户下所有绑定的银行卡信息 查看list列表显示该用户所有的银行卡信息
	@ResponseBody
	@RequestMapping(value = "/accountCardInfo", method = RequestMethod.GET)
	public List<MemberAccountCard> accountCardInfo(String id) {
		List<MemberAccountCard> memberAccountCard = memberAccountCardService.get(id);	
		return memberAccountCard;
	}
}
	//验证安全码 如果验证通过就可以增加绑定银行卡
	//如果没有通过 刷新页面给出提示信息
	
	
/*	//增加银行卡信息
	@ResponseBody
	@RequestMapping(value = "/addAccountCard", method = RequestMethod.GET)
	public String addAccountCard(MemberAccountCard memberAccountCard) {
		memberAccountCard.setId(UUID.randomUUID().toString());
		User user = new User();
		user.setId(UUID.randomUUID().toString());

		memberAccountCard.setBankBranchCity("湖北");
		memberAccountCard.setBankBranchCity("武汉");
		memberAccountCard.setQqNo("346204131");
		memberAccountCard.setBeginCreateDate(new Date());
		memberAccountCard.setUpdateBy("cmh");
		memberAccountCard.setCreateBy("cmh");
		memberAccountCard.setDelFlag("1");
		memberAccountCard.setEndCreateDate(new Date());
		return "成功插入"+String.valueOf(memberAccountCardService.addAccountCard(memberAccountCard))+"条记录";
	}*/
	
	

/*	//验证用户安全码
	@ResponseBody
	@RequestMapping(value = "/verSecPwd", method = RequestMethod.GET)
	public String verSecPwd(String id,String secPassWord) {
			//根据id查询该用户的安全密码
	      String str = 	memberAccountCardService.getSec(id);
	      if(StringUtils.isNotBlank(secPassWord)) {
	    	 if(PassWordUtils.validatePassword(secPassWord, str)) {
	    		 //输入的安全密码正确
	    		 return "输入的安全码正确";
	    	 }else {
	    		 return "输入的安全码错误";
	    	 }
	      }else {
	    	  return "安全码不能为空";
	      }
		
	}*/
	
/*	//验证用户安全码
	public boolean verSecPassWord(String id,String secPassWord) {
			//根据id查询该用户的安全密码
	      String str = 	memberAccountCardService.getSec(id);
	      if(StringUtils.isNotBlank(secPassWord)) {
	    	 if(PassWordUtils.validatePassword(secPassWord, str)) {
	    		 //输入的安全密码正确
	    		 return true;
	    	 }else {
	    		 return false;
	    	 }
	      }else {
	    	  return false;
	      }
		
	}*/
	
	
/*	//修改用户安全码
	@ResponseBody
	@RequestMapping(value = "/modifySecPwd", method = RequestMethod.GET)
	public String modifySecPwd(String id,String secPassWord,String newPassWord) {
			//验证该用户输入的安全码是否正确
		   if(verSecPassWord(id,secPassWord)) {
			//验证通过  更新安全密码
			   
			  return "安全码更新成功";
		   }else {
		    //验证不通过
			   
			return "输入的安全码错误";
		   }
		
	}
	}*/
	
	
	
/*	@Autowired
	private MemberAccountCardService memberAccountCardService;
	
	@ModelAttribute
	public MemberAccountCard get(@RequestParam(required=false) String id) {
		MemberAccountCard entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = memberAccountCardService.get(id);
		}
		if (entity == null){
			entity = new MemberAccountCard();
		}
		return entity;
	}
	
	@RequiresPermissions("memberbank:memberAccountCard:view")
	@RequestMapping(value = {"list", ""})
	public String list(MemberAccountCard memberAccountCard, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MemberAccountCard> page = memberAccountCardService.findPage(new Page<MemberAccountCard>(request, response), memberAccountCard); 
		model.addAttribute("page", page);
		return "modules/memberbank/memberAccountCardList";
	}*/

	/*@RequiresPermissions("memberbank:memberAccountCard:view")
	@RequestMapping(value = "form")
	public String form(MemberAccountCard memberAccountCard, Model model) {
		model.addAttribute("memberAccountCard", memberAccountCard);
		return "modules/memberbank/memberAccountCardForm";
	}*/

	/*@RequiresPermissions("memberbank:memberAccountCard:edit")
	@RequestMapping(value = "save")
	public String save(MemberAccountCard memberAccountCard, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, memberAccountCard)){
			return form(memberAccountCard, model);
		}
		memberAccountCardService.save(memberAccountCard);
		addMessage(redirectAttributes, "保存会员银行卡成功");
		return "redirect:"+Global.getAdminPath()+"/memberbank/memberAccountCard/?repage";
	}*/
	
	/*@RequiresPermissions("memberbank:memberAccountCard:edit")
	@RequestMapping(value = "delete")
	public String delete(MemberAccountCard memberAccountCard, RedirectAttributes redirectAttributes) {
		memberAccountCardService.delete(memberAccountCard);
		addMessage(redirectAttributes, "删除会员银行卡成功");
		return "redirect:"+Global.getAdminPath()+"/memberbank/memberAccountCard/?repage";
	}*/
	
/*	@RequiresPermissions("memberbank:memberAccountCard:edit")
	@RequestMapping(value = "changeStatus")
	public String changeStatus(MemberAccountCard memberAccountCard, RedirectAttributes redirectAttributes) {
		String status=memberAccountCard.getStatus();
		String message="会员银行卡审核中，暂时不能操作";
		if(NORMAL.equals(status)) {
			memberAccountCard.setStatus(FREEZE);
			message="会员银行卡冻结成功";
		}else if(FREEZE.equals(status)) {
			memberAccountCard.setStatus(NORMAL);
			message="会员银行卡启用成功";
		}
		memberAccountCardService.save(memberAccountCard);
		addMessage(redirectAttributes,message);
		return "redirect:"+Global.getAdminPath()+"/memberbank/memberAccountCard/?repage";
	}*/

