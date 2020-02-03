package com.bis.operox.inv.service;

import java.util.List;

import com.bis.operox.inv.dao.entity.Discount;

/**
 * 
 * @author shivayogikadagad
 *
 */
public interface DiscountService {

	public Discount addDiscount(Discount discount);

	public Discount getDiscountById(Long id);

	public List<Discount> getAll();

}
