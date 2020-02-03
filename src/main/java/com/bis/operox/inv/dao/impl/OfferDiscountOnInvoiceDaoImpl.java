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

import com.bis.operox.inv.dao.OfferDiscountOnInvoiceDao;
import com.bis.operox.inv.dao.entity.Offer;
import com.bis.operox.inv.dao.entity.OfferDiscountOnInvoice;

@Repository
public class OfferDiscountOnInvoiceDaoImpl implements OfferDiscountOnInvoiceDao{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(OfferDiscountOnInvoiceDaoImpl.class);
	
	@Override
	@Transactional
	public OfferDiscountOnInvoice storeOfferDiscountOnInvoice(OfferDiscountOnInvoice offerDiscountOnInvoice)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(offerDiscountOnInvoice);
		logger.info("OfferDiscountOnInvoice saved successfully, Offer Details="+offerDiscountOnInvoice);
		return offerDiscountOnInvoice;
	}

	@Override
	@Transactional
	public OfferDiscountOnInvoice getOfferDiscountOnInvoiceByOfferId(Long offerId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<OfferDiscountOnInvoice> offerDiscountOnInvoice = new ArrayList<OfferDiscountOnInvoice>();
		logger.debug("getOfferDiscountOnInvoiceByOfferId user code start");
		offerDiscountOnInvoice = session.createQuery("from OfferDiscountOnInvoice offerDiscountOnInvoice where offerDiscountOnInvoice.offer.id =:offerId" ).setCacheable(true).setLong( "offerId", offerId ).setCacheable(true).list();
		logger.debug("getOfferDiscountOnInvoiceByOfferId user code end");
		if (offerDiscountOnInvoice.size() > 0) {
			return offerDiscountOnInvoice.get(0);
		} else {
			return null;
		}
}

	@Override
	@Transactional
	public OfferDiscountOnInvoice getOfferDiscountOnInvoiceById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		OfferDiscountOnInvoice offerDiscountOnInvoice = (OfferDiscountOnInvoice) session.load(OfferDiscountOnInvoice.class, new Long(id));
		logger.info("OfferDiscountOnInvoice loaded successfully, Offer details="+offerDiscountOnInvoice);
		return offerDiscountOnInvoice;
	}

	@Override
	@Transactional
	public OfferDiscountOnInvoice getOfferDiscountOnInvoice(Offer offer) throws Exception {
		
		
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
		List<OfferDiscountOnInvoice> offerDiscountOnInvoice = new ArrayList<OfferDiscountOnInvoice>();
		logger.debug("getOfferDiscountOnInvoiceByOfferId user code start");
		
		if(currentDate.compareTo(offer.getOfferStartDate()) == 0 && isDayApplicable){
			if((presentTime.after(startTime) && presentTime.before(endTime))){
				offerDiscountOnInvoice = session.createQuery("from OfferDiscountOnInvoice offerDiscountOnInvoice where offerDiscountOnInvoice.offer.id = :offerId" ).setLong("offerId", offer.getId()).setCacheable(true).list();	
			}
		}
		
		else if((currentDate.after(offer.getOfferStartDate())) && (null == offer.getOfferEndDate() || currentDate.before(offer.getOfferEndDate())) && isDayApplicable){
			if(presentTime.after(startTime) && presentTime.before(endTime)){
				offerDiscountOnInvoice = session.createQuery("from OfferDiscountOnInvoice offerDiscountOnInvoice where offerDiscountOnInvoice.offer.id = :offerId" ).setLong("offerId", offer.getId()).setCacheable(true).list();	
			}
		}
		
		logger.debug("getOfferDiscountOnInvoiceByOfferId user code end");
		if (offerDiscountOnInvoice.size() > 0) {
			return offerDiscountOnInvoice.get(0);
		} else {
			return null;
		}
}

	@Override
	@Transactional
	public List<Float> getStoreInvoiceAmounts(Long storeId) {
		Session session = this.sessionFactory.getCurrentSession();
		
		String query = "select odi.invoiceAmount from OfferDiscountOnInvoice odi where " +
				" odi.offer.id IN (select offerStores.offer.id from OfferStores offerStores where offerStores.store.id = :storeId)";
		
		List<Float> invoiceAmountsList = session.createQuery(query.toString()).setLong("storeId", storeId).setCacheable(true).list();
		return invoiceAmountsList;
	}

	@Override
	@Transactional
	public OfferDiscountOnInvoice getDiscountOnInvoiceAmountByCompanyId(Float invoiceAmount, Long companyId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<OfferDiscountOnInvoice> offerDiscountOnInvoice = new ArrayList<OfferDiscountOnInvoice>();
		logger.debug("getDiscountOnInvoiceAmountByCompanyId user code start");
		offerDiscountOnInvoice = session.createQuery("from OfferDiscountOnInvoice offerDiscountOnInvoice where "
				+ "offerDiscountOnInvoice.offer.company.id =:companyId and offerDiscountOnInvoice.invoiceAmount = :invoiceAmount "
				+ "and offerDiscountOnInvoice.offer.status = '1' " ).setCacheable(true).setLong( "companyId", companyId ).setFloat("invoiceAmount", invoiceAmount).setCacheable(true).list();
		logger.debug("getDiscountOnInvoiceAmountByCompanyId user code end");
		if (offerDiscountOnInvoice.size() > 0) {
			return offerDiscountOnInvoice.get(0);
		} else {
			return null;
		}
}

}
