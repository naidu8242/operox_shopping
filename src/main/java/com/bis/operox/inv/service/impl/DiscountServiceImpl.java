package com.bis.operox.inv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.DiscountDao;
import com.bis.operox.inv.dao.entity.Discount;
import com.bis.operox.inv.service.DiscountService;

/**
 * 
 * @author shivayogikadagad
 *
 */
@Service
public class DiscountServiceImpl implements DiscountService {

	@Autowired
	private DiscountDao discountDao;

	@Override
	public Discount addDiscount(Discount discount) {
		return discountDao.addDiscount(discount);
	}

	@Override
	public Discount getDiscountById(Long id) {
		return this.discountDao.getDiscountById(id);
	}

	@Override
	public List<Discount> getAll() {
		return this.discountDao.getAll();
	}

}
