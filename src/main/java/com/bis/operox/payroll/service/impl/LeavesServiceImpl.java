package com.bis.operox.payroll.service.impl;

import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.payroll.dao.LeavesDao;
import com.bis.operox.payroll.entity.Leaves;
import com.bis.operox.payroll.service.LeavesService;

@Service
public class LeavesServiceImpl implements LeavesService{

	private static final Logger logger = LoggerFactory.getLogger(LeavesServiceImpl.class);
	
	@Autowired
	private LeavesDao leavesDao;
	
	public Leaves addLeaves(JSONObject jsonObject,User user) throws JSONException {
		Leaves leave =null;
		if(jsonObject.has("leaveId") && jsonObject.getString("leaveId") != null && !"".equals(jsonObject.getString("leaveId")))
		{
			leave = leavesDao.getLeavesById(Long.parseLong(jsonObject.getString("leaveId")));
			leave.setUpdatedBy(user.getUserCode());
			leave.setUpdatedDate(new Date());
		}else{
			leave= new Leaves();
			leave.setCreatedBy(user.getUserCode());
			leave.setCreatedDate(new Date());
			leave.setUpdatedBy(user.getUserCode());
			leave.setUpdatedDate(new Date());
		}
		if (jsonObject.has("leaveName") && !jsonObject.getString("leaveName").isEmpty()) {
			leave.setLeaveName(jsonObject.getString("leaveName"));
		}else{
			leave.setLeaveName("");
		}
		
		if (  jsonObject.has("description") && !jsonObject.getString("description").isEmpty()) {
			leave.setDescription(jsonObject.getString("description"));
		}else{
			leave.setDescription("");
		}
			leave.setStatus(1);
			
		leavesDao.addLeaves(leave);
		logger.info("leaves Sent to Leavedao");
		return leave;
				
	}

	@Override
	public Leaves getLeavesById(Long leaveId) {
		return leavesDao.getLeavesById(leaveId);
	}

	@Override
	public List<Leaves> listLeaves() {
		return leavesDao.listLeaves();
	}
	@Override
	public Leaves deleteLeaveById(Leaves leaves) {
		return leavesDao.deleteLeaveById(leaves);
	}


}
