package com.bis.operox.payroll.service;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.payroll.entity.Leaves;

public interface LeavesService {
	
    Leaves addLeaves(JSONObject jsonObject,User user) throws JSONException;
	
	Leaves getLeavesById(Long leaveId);

	List<Leaves> listLeaves();

	Leaves deleteLeaveById(Leaves leaves);
}
