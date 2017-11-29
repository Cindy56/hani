package com.game.modules.bank.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.game.common.persistence.Page;
import com.game.modules.bank.entity.CompanyCard;

public interface CompanyCardService {
	public CompanyCard get(String id);
	
	public List<CompanyCard> findList(CompanyCard companyCard);
	
	public Page<CompanyCard> findPage(Page<CompanyCard> page, CompanyCard companyCard);
	
	public CompanyCard save(CompanyCard companyCard);
	
	public void delete(CompanyCard companyCard);
	
}
