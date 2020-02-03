package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.Discount;

/**
 * 
 * @author shivayogikadagad
 *
 */

public interface DiscountDao {

	public Discount addDiscount(Discount discount);

	public Discount getDiscountById(Long id);

	public List<Discount> getAll();

}