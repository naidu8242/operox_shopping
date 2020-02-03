package com.bis.operox.inv.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.CustomerDao;
import com.bis.operox.inv.dao.entity.Customer;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.ProductStock;

@Repository
public class CustomerDaoImpl implements CustomerDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerDaoImpl.class);

	@Override
	@Transactional
	public Customer getCustomerById(int id) {
		Session session = this.sessionFactory.getCurrentSession();	
		String query = "from Customer customer  where customer.id = :id";  
	    List<Customer> customerList = session.createQuery(query).setLong("id", new Long(id)).setCacheable(true).list();
	    Customer customer = null;
	    if(customerList != null && !customerList.isEmpty()){
	    	customer = customerList.iterator().next();
	    }
	    return customer;
	}
	
	@Transactional
	public Customer addCustomer(Customer customer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(customer);
		logger.info("Customer saved successfully, Customer Details="+customer);
		return customer;
	}

	@SuppressWarnings("unchecked")
    @Transactional
    public List<Customer> listCustomer(Long companyId) {
        Session session = this.sessionFactory.getCurrentSession();
        Integer status = 1;
        String query = "from Customer cust where cust.companyId = :companyID and cust.status = :status";
        List<Customer> customerList = session.createQuery(query).setLong("companyID", companyId).setInteger("status", status).setCacheable(true).list();
        return customerList;
    }

	@Transactional
	public Customer getCustomerById(Long customerId) {
		Session session = this.sessionFactory.getCurrentSession();	
		 String query = "from Customer cust where cust.id = :customerId";
	     List<Customer> customerList = session.createQuery(query).setLong("customerId", customerId).setCacheable(true).list();
	    return customerList.get(0);
	}

	@Transactional
	public Customer deleteCustomerById(Customer customer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(customer);
		logger.info("Customer saved successfully, Customer Details="+customer);
		return customer;
	}

	 @Transactional
	public Customer getCustomerByPhoneNumber(String phone) {
		Session session = this.sessionFactory.getCurrentSession();
		 List<Customer> customer = new ArrayList<Customer>();
            customer = session.createQuery("from Customer cust where cust.phone =:phone").setCacheable(true).setString("phone", phone).list();
            if (customer.size() > 0) {
                return customer.get(0);
            } else {
                return null;
            }
	}
	 


	@Override
	@Transactional
	public Boolean getCustomerByPhone(String phone) {
		boolean isValidProduct = true;
		Integer status = 1;
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from Customer cust where cust.phone = :phone and cust.status = :status";
		List<Customer> customersList = session.createQuery(query).setString("phone", phone).setInteger("status", status).setCacheable(true).list();
		if(customersList != null && customersList.size() > 0){
			isValidProduct = false;
		}
		return isValidProduct;
	}

	@Override
	@Transactional
	public List<Customer> listCustomerForWorkOrder(Long companyId) {
        Session session = this.sessionFactory.getCurrentSession();
        String query = "from Customer cust where cust.companyId = :companyID and cust.customerType = 'Whole Sale' and cust.status = '1' ";
        List<Customer> customerList = session.createQuery(query).setLong("companyID", companyId).setCacheable(true).list();
        return customerList;
    }

	

}