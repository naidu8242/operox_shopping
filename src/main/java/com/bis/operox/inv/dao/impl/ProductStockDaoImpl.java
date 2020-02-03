  package com.bis.operox.inv.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.inv.dao.ProductStockDao;
import com.bis.operox.inv.dao.entity.ProductStock;

@Repository
public  class ProductStockDaoImpl implements ProductStockDao{
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductStockDaoImpl.class);

	@Override
	@Transactional
	public ProductStock addProductStock(ProductStock ps) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(ps);
		logger.info("ProductStock saved successfully, ProductStock Details="+ps);
		return ps;
		
	}
	
	@Override
	@Transactional
	public List<ProductStock> listProductStock() {
		Session session = this.sessionFactory.getCurrentSession();
		List<ProductStock> productStock = session.createQuery("from ProductStock prostock  left join fetch prostock.productId proid GROUP BY proid.id ").list();
		return productStock;
	  }
	
	
	@Override
	@Transactional
	public List<ProductStock> recentProductStockList(int count) {
		Session session = this.sessionFactory.getCurrentSession();
		List<ProductStock> productStock = session.createQuery("from ProductStock ps ORDER BY ps.updatedDate DESC").setMaxResults(count).list();
		return productStock;
	  }
	
	
	@Override
	@Transactional
	public List<ProductStock> listProductStockBySearchKeys(Long storeId, Long categoryId, String searchKey,List<Long> brandIds,String minPrice,String maxPrice) {
		
		Session session = this.sessionFactory.getCurrentSession();
		List<ProductStock> searchedLists = new ArrayList<ProductStock>();
		Map<String, Object> params = new HashMap<String,Object>();
		
		String from = "from ProductStock ps left join fetch ps.productId";             
		String subQuery = "";
		String subQuery1 = "";
		String subQuery2 = "";
		String subQuery3 = "";
		String subQuery4 = "";
		
		if(storeId != null){
			subQuery1 =  "ps.storeId.id = :storeId and ";
			params.put( "storeId", storeId );
		}
		if(categoryId != null){
			subQuery2 =  subQuery2 + " ps.productId.category.id = :categoryId and";
			params.put( "categoryId", categoryId );
		}
		if(searchKey != null && searchKey != "undefined" && searchKey != ""){
			subQuery3 =  subQuery3 + " (ps.productId.productName like :searchKey OR ps.productId.productCode like :searchKey) and";
			params.put( "searchKey", searchKey );
		}
		if(brandIds != null  && !brandIds.isEmpty()&& brandIds.get(0) !=null ){
			subQuery4 =  subQuery4 + " ps.productId.brand.id IN (:brandIds) and";
			params.put( "brandIds", brandIds );
		}
		if(subQuery1 != "" || subQuery2 != "" || subQuery3 != "" || subQuery4 != ""  ){
			subQuery = "where "+subQuery1+subQuery2+subQuery3+subQuery4;
		}
		String whereCondition = "";
		if(subQuery != ""){
			whereCondition = subQuery.substring(0, subQuery.length() - 4); 
		}
		
		Query query = session.createQuery(from+" "+whereCondition);
        for (String str : query.getNamedParameters())
        {
        	if(str.equalsIgnoreCase("brandIds")){
        		 query.setParameterList( str, (Collection) params.get( str ) );
        	}else{
        		 query.setParameter( str, params.get( str ) );
        	}
           
        }
        searchedLists = (List<ProductStock>) query.list();
	        
		return searchedLists;
		 
	  }
	
	
	@Override
	@Transactional
	public ProductStock getProductStockById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();	
		String query = "from ProductStock productStock  where productStock.id = :id";  
	    List<ProductStock> productStockList = session.createQuery(query).setLong("id", id).setCacheable(true).list();
	    ProductStock productStock = null;
	    if(productStockList != null && !productStockList.isEmpty()){
	    	productStock = productStockList.iterator().next();
	    }
	    return productStock;
	}

	@Override
	@Transactional
	public ProductStock getProductStockByProductBarCodeAndStoreId(String barcode, Long storeId) {
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<ProductStock> productStockList = 	session.createQuery(" from ProductStock ps left join fetch ps.productId left join fetch ps.taxId left join fetch ps.discountId left join fetch ps.storeId where ps.currentQuantity >0 and ps.storeId.id = :storeId and "
				            + " ps.barCode = :barcode ").setParameter("barcode", barcode).setLong("storeId", storeId).setCacheable(true).list();
		
		 ProductStock productStock = null;
	    if(productStockList != null && !productStockList.isEmpty()){
	    	productStock = productStockList.iterator().next();
	    }
	    return productStock;
	}

	@Override
	@Transactional
	public ProductStock getProductStockByProductSearchCodeAndStoreId(String searhcode, Long storeId) {
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<ProductStock> productStockList = 	session.createQuery(" from ProductStock ps left join fetch ps.productId left join fetch ps.taxId left join fetch ps.discountId left join fetch ps.currentQuantity >0 and ps.storeId where ps.storeId.id = :storeId and "
				            + " ps.productId.productCode = :productCode ").setParameter("productCode", searhcode).setLong("storeId", storeId).setCacheable(true).list();
		
		 ProductStock productStock = null;
	    if(productStockList != null && !productStockList.isEmpty()){
	    	productStock = productStockList.iterator().next();
	    }
	    return productStock;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ProductStock> getProductStockByProductNameAndStoreId(String productName, Long storeId) {
		Session session = this.sessionFactory.getCurrentSession();
		return session
				.createQuery(" from ProductStock ps left join fetch ps.productId left join fetch ps.taxId left join fetch ps.discountId left join fetch ps.storeId where ps.currentQuantity >0 and ps.storeId.id = :storeId and "
						+ "ps.productId.productName LIKE '" + productName + "%'").setLong("storeId", storeId).setCacheable(true).list();
				
	}

	@Override
	@Transactional
	public List<ProductStock> getEcommersProductsStockListByIds(List<Long> projectStockIds) {
		
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from ProductStock ps where ps.id IN (:projectStockIds) ";
		List<ProductStock> productStockList = session.createQuery(query).setParameterList("projectStockIds", projectStockIds).setCacheable(true).list();
		return productStockList;
	
	}
	
}










