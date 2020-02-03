package com.bis.operox.payroll.dao;

import java.util.List;

import com.bis.operox.payroll.entity.Leaves;

public interface LeavesDao {
   
	Leaves addLeaves(Leaves leaves);
	
	Leaves getLeavesById(Long leaveId);

	List<Leaves> listLeaves();

	Leaves deleteLeaveById(Leaves leaves);
}
