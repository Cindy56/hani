/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.hall.modules.finance.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.common.config.Global;
import com.game.common.persistence.Page;
import com.game.common.utils.StringUtils;
import com.game.common.web.BaseController;
import com.game.modules.bank.entity.CompanyCard;
import com.game.modules.bank.service.CompanyCardService;
import com.game.modules.finance.entity.FinanceRecharge;
import com.game.modules.finance.service.FinanceRechargeService;
import com.game.modules.sys.entity.User;

/**
 * 账户充值管理Controller
 * 
 * @author David
 * @version 2017-11-25
 */
@Controller
@RequestMapping(value = "${adminPath}/finance/financeRecharge")
public class FinanceRechargeController extends BaseController {

	@Autowired
	private FinanceRechargeService financeRechargeService;
	@Autowired
	CompanyCardService companyCardService;
	@ModelAttribute
	public FinanceRecharge get(@RequestParam(required=false) String id) {
		FinanceRecharge entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = financeRechargeService.get(id);
		}
		if (entity == null){
			entity = new FinanceRecharge();
		}
		return entity;
	}

	/**
	 * 数据权限测试：查看本部门，查看下级，查看本人
	 * @param financeRecharge
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("finance:financeRecharge:view")
	@RequestMapping(value = {"list", ""})
	public String list(FinanceRecharge financeRecharge, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FinanceRecharge> page = financeRechargeService.findPage(new Page<FinanceRecharge>(request, response), financeRecharge); 
		model.addAttribute("page", page);
		return "modules/finance/financeRechargeList";
	}

	@RequiresPermissions("finance:financeRecharge:edit")
	@RequestMapping(value = "form")
	public String form(FinanceRecharge financeRecharge, Model model) {
		//TODO:相关验证		
		
		//充值表单提交 产生一条充值记录 状态支付中
		User user = new User();
		user.setId("cb587fdc02ba4395aaa653b03ded304c");
		financeRecharge.setUser(user);
		financeRecharge.setOfficeId("机构名字");
		financeRecharge.setRechargeNo("充值单编号");
		financeRecharge.setRechargeDate(new Date());
		financeRecharge.setPaymentChannelId("工商银行");
		financeRecharge.setStatus("0");
	
		financeRechargeService.save(financeRecharge);
		
		model.addAttribute("financeRecharge", financeRecharge);
		//return "modules/finance/financeRechargeForm";
		return "redirect:"+Global.getAdminPath()+"/finance/financeRecharge/list"; 
	}
	
	
	@RequiresPermissions("finance:financeRecharge:edit")
	@RequestMapping(value = "confirmForm")
	public String confirmForm(FinanceRecharge financeRecharge, Model model,HttpServletRequest req) {
		//随机产生6位数附言
		financeRecharge.setValidateCode(String.valueOf((int)((Math.random()*9+1)*100000)));
		CompanyCard companyCard =new CompanyCard();
		int i = 0;
		i = Integer.parseInt(req.getParameter("bankSelect"));
		if(i == 1) {
			companyCard.setBankCode("001");
		}else if(i == 2) {
			companyCard.setBankCode("002");
		}else if(i == 3) {
			companyCard.setBankCode("003");
		}else if(i == 4) {
			companyCard.setBankCode("004");
		}
		
		List<CompanyCard> list = companyCardService.findListByBankCode(companyCard);
		CompanyCard card = list.get((int)(Math.random()*list.size()));
		model.addAttribute("card",card);
		return "modules/finance/financeRechargeForm";
	}
	
	
	/**
	 * 
	 * 银行卡充值页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "rechargeForm")
	public String rechargeForm(Model model) {
		//弹出银行卡选择页面
/*		List<CompanyCard> list = companyCardService.findList(new CompanyCard());
		model.addAttribute("list", list);*/
		
		//return "modules/finance/financeRechargeForm";
		return "modules/finance/selectBank";
	}
	
	
/*	@RequiresPermissions("trade:financeRecharge:edit")
	@RequestMapping(value = "save")
	public String save(FinanceRecharge financeRecharge, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, financeRecharge)){
			
			
			return form(financeRecharge, model);
		}
		financeRechargeService.save(financeRecharge);
		addMessage(redirectAttributes, "保存账户充值成功");
		//TODO:重定向到第三方充值平台
		return "redirect:"+Global.getAdminPath()+"/trade/financeRecharge/?repage";
	}*/
	
/*	@RequiresPermissions("trade:financeRecharge:edit")*/
	@RequestMapping(value = "delete")
	public String delete(FinanceRecharge financeRecharge, RedirectAttributes redirectAttributes) {

		if("0".equals(financeRecharge.getStatus()) || "1".equals(financeRecharge.getStatus())) {
			financeRechargeService.delete(financeRecharge);
			addMessage(redirectAttributes, "撤销充值成功");
		}else {
			addMessage(redirectAttributes, "打款已经到账,撤销失败");
		}
		
		return "redirect:"+Global.getAdminPath()+"/finance/financeRecharge/list"; 
	}	
	
	
	@ResponseBody
	@RequestMapping(value = "bankSelect")
	public String bankSelect(HttpServletRequest res,HttpServletResponse re) throws IOException {
		String bankSelect = res.getParameter("bankSelect");
		if("1".equals(bankSelect)) {
			//工商银行 代码为001
		//	List<CompanyCard> list = companyCardService.findList(new CompanyCard());
			
		//	List<CompanyCard> list = companyCardService.findListByBankCode(new CompanyCard());
		//	CompanyCard companyCard =new CompanyCard();
		//	companyCard.setBankCode("001");
		//	List<CompanyCard> list = companyCardService.findList(companyCard);
			
			
			CompanyCard companyCard =new CompanyCard();
			companyCard.setBankCode("001");
			List<CompanyCard> list = companyCardService.findListByBankCode(companyCard);			
			ObjectMapper objectMapper = new ObjectMapper();  
			String s = objectMapper.writeValueAsString(list);
			re.getWriter().write(s);
			
		}else if("2".equals(bankSelect)) {
			
		}else if("3".equals(bankSelect)) {
			
		}else {
			
		}
		return null;
	}	
	
}