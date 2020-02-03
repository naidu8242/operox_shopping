package com.bis.operox.inv.dao.impl;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.OfferCouponsDao;
import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferBuyxGetY;
import com.bis.operox.inv.dao.entity.OfferCoupons;
import com.bis.operox.inv.dao.entity.OfferDiscountOnInvoice;

@Repository
public class OfferCouponsDaoImpl  implements OfferCouponsDao{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(OfferCouponsDaoImpl.class);
	
	
	@Override
	@Transactional
	public OfferCoupons storeOfferCoupons(OfferCoupons offerCoupons) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(offerCoupons);
		logger.info("OfferCoupons saved successfully, Offer Details="+offerCoupons);
		return offerCoupons;
	}


	@Override
	@Transactional
	public OfferCoupons getOfferCouponsByOfferId(Long offerId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<OfferCoupons> offerCoupons = new ArrayList<OfferCoupons>();
		logger.debug("getOfferCouponsByOfferId user code start");
		offerCoupons = session.createQuery("from OfferCoupons offerCoupons where offerCoupons.offer.id =:offerId" ).setCacheable(true).setLong( "offerId", offerId ).setCacheable(true).list();
		logger.debug("getOfferCouponsByOfferId user code end");
		if (offerCoupons.size() > 0) {
			return offerCoupons.get(0);
		} else {
			return null;
		}
}


	@Override
	@Transactional
	public OfferCoupons getOfferCouponsById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		OfferCoupons offerCoupons = (OfferCoupons) session.load(OfferCoupons.class, new Long(id));
		logger.info("OfferCoupons loaded successfully, Offer details="+offerCoupons);
		return offerCoupons;
	}


	@Override
	@Transactional
	public OfferCoupons getOfferCouponsByOffer(Offer offer) throws Exception {
		
		
        java.sql.Date currentDate = java.sql.Date.valueOf(LocalDate.now());
		
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        String currentTime = parseFormat.format(cal.getTime());
        Date presentTime = parseFormat.parse(currentTime);
        
        Date startTime = parseFormat.parse(offer.getOfferStartTime());
        Date endTime = parseFormat.parse(offer.getOfferEndTime());
        
        
        LocalDate date = LocalDate.now();
        DayOfWeek dow = date.getDayOfWeek();
        String dayName = dow.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        boolean isDayApplicable = false;
        
        if(dayName.equalsIgnoreCase("Monday") && offer.getMonday().equalsIgnoreCase("Y")){
        	isDayApplicable = true;
        }
        else if(dayName.equalsIgnoreCase("Tuesday") && offer.getTuesday().equalsIgnoreCase("Y")){
        	isDayApplicable = true;
        }
        else if(dayName.equalsIgnoreCase("Wednesday") && offer.getWednesday().equalsIgnoreCase("Y")){
        	isDayApplicable = true;
        }
        else if(dayName.equalsIgnoreCase("Thursday") && offer.getThursday().equalsIgnoreCase("Y")){
        	isDayApplicable = true;
        }
        else if(dayName.equalsIgnoreCase("Friday") && offer.getFriday().equalsIgnoreCase("Y")){
        	isDayApplicable = true;
        }
        else if(dayName.equalsIgnoreCase("Saturday") && offer.getSaturday().equalsIgnoreCase("Y")){
        	isDayApplicable = true;
        }
        else if(dayName.equalsIgnoreCase("Sunday") && offer.getSunday().equalsIgnoreCase("Y")){
        	isDayApplicable = true;
        }

		
		
		Session session = this.sessionFactory.getCurrentSession();
		List<OfferCoupons> offerCoupons = new ArrayList<OfferCoupons>();
		logger.debug("getOfferCouponsByOffer user code start");
		
		if(currentDate.compareTo(offer.getOfferStartDate()) == 0 && isDayApplicable){
			if((presentTime.after(startTime) && presentTime.before(endTime))){
				offerCoupons = session.createQuery("from OfferCoupons oc where oc.offer.id = :offerId" ).setLong("offerId", offer.getId()).setCacheable(true).list();	
			}
		}
		
		else if((currentDate.after(offer.getOfferStartDate())) && (null == offer.getOfferEndDate() || currentDate.before(offer.getOfferEndDate())) && isDayApplicable){
			if(presentTime.after(startTime) && presentTime.before(endTime)){
				offerCoupons = session.createQuery("from OfferCoupons oc where oc.offer.id = :offerId" ).setLong("offerId", offer.getId()).setCacheable(true).list();	
			}
		}
		
		logger.debug("getOfferCouponsByOffer user code end");
		if (offerCoupons.size() > 0) {
			return offerCoupons.get(0);
		} else {
			return null;
		}
}


	@Override
	@Transactional
	public OfferCoupons getOfferCouponsBycouponName(String couponName, Long companyId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<OfferCoupons> offerCoupons = new ArrayList<OfferCoupons>();
		logger.debug("getOfferCouponsBycouponName user code start");
		offerCoupons = session.createQuery("from OfferCoupons offerCoupons where offerCoupons.offer.company.id =:companyId and offerCoupons.couponName = :couponName "
				+ "and offerCoupons.offer.status = '1' " ).setCacheable(true).setLong( "companyId", companyId ).setString("couponName", couponName).setCacheable(true).list();
		logger.debug("getOfferCouponsBycouponName user code end");
		if (offerCoupons.size() > 0) {
			return offerCoupons.get(0);
		} else {
			return null;
		}
	}

}
