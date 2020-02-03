package com.bis.operox.production.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.operox.production.dao.QCCheckListDao;
import com.bis.operox.production.dao.entity.QCCheckList;

@Repository
public class QCCheckListDaoImpl implements QCCheckListDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory.getLogger(QCCheckListDaoImpl.class);

	@Override
    @Transactional
    public QCCheckList addQcCheckList(QCCheckList qcCheckList) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(qcCheckList);
        logger.info("QcCheckList saved successfully, QcCheckList Details=" + qcCheckList);
        return qcCheckList;
    }

    @Override
    @Transactional
    public QCCheckList getQcCheckListById(Long id) {
       
        Session session = this.sessionFactory.getCurrentSession();   
        String query = "from QCCheckList qccl  where qccl.id = :id"; 
        List<QCCheckList> qcCheckList = session.createQuery(query).setLong("id", new Long(id)).setCacheable(true).list();
        QCCheckList qccheckList = null;
        if(qcCheckList != null && !qcCheckList.isEmpty()){
            qccheckList = qcCheckList.iterator().next();
        }
        return qccheckList;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<QCCheckList> listQcCheckList() {
        Session session = this.sessionFactory.getCurrentSession();
        Integer status = 1;
        String query = "from QCCheckList qccl where qccl.status = :status";
        List<QCCheckList> listQcCheckList = session.createQuery(query).setInteger("status", status).setCacheable(true).list();
        return listQcCheckList;
    }

	@Override
	 @Transactional
	public List<QCCheckList> getQcCheckListByRawMaterialId(Long materialId) {
		Session session = this.sessionFactory.getCurrentSession();   
        String query = "from QCCheckList qccl  where qccl.rawMaterial.id = :materialId"; 
        List<QCCheckList> qcCheckList = session.createQuery(query).setLong("materialId", materialId).setCacheable(true).list();
		return qcCheckList;
	}

	@Override
	 @Transactional
	public List<QCCheckList> getQcCheckListByProductId(Long productId) {
		Session session = this.sessionFactory.getCurrentSession();   
        String query = "from QCCheckList qccl  where qccl.productid.id = :productId"; 
        List<QCCheckList> qcCheckList = session.createQuery(query).setLong("productId", productId).setCacheable(true).list();
		return qcCheckList;
	}

}