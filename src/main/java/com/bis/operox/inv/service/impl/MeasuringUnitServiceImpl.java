package com.bis.operox.inv.service.impl;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.MeasuringUnitDao;
import com.bis.operox.inv.dao.entity.MeasuringUnit;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.MeasuringUnitService;

@Service
@Repository
public class MeasuringUnitServiceImpl implements MeasuringUnitService{

	@Autowired
	private MeasuringUnitDao measuringUnitDao;

	@Override
	public MeasuringUnit addMeasuringUnit(MeasuringUnit measuringUnit) {
		return measuringUnitDao.addMeasuringUnit(measuringUnit);
	}

	@Override
	public MeasuringUnit getMeasuringUnitById(Long id) {
		return measuringUnitDao.getMeasuringUnitById(id);
	}
	@Override
	public List<MeasuringUnit> listMeasuringUnit() {
		return measuringUnitDao.listMeasuringUnit();
	}


	@Override
	public void addMeasuringUnitDetails(JSONObject jsonObj, User user)throws Exception {
		MeasuringUnit measuringUnit = null;
			if(jsonObj.has("measuringUnitId") && jsonObj.getString("measuringUnitId") != null && !"".equals(jsonObj.getString("measuringUnitId"))){
				measuringUnit = measuringUnitDao.getMeasuringUnitById(Long.parseLong(jsonObj.getString("measuringUnitId")));
				measuringUnit.setUpdatedBy(user.getUserCode());
				measuringUnit.setUpdatedDate(new Date());
			}else{
				measuringUnit = new MeasuringUnit();
				measuringUnit.setCreatedBy(user.getUserCode());
				measuringUnit.setCreatedDate(new Date());
				measuringUnit.setUpdatedBy(user.getUserCode());
				measuringUnit.setUpdatedDate(new Date());
				measuringUnit.setStatus(1);
			}
			if(jsonObj.getString("measuringUnit") != null && !"".equals(jsonObj.getString("measuringUnit"))){
				measuringUnit.setMeasuringUnit(jsonObj.getString("measuringUnit"));	
			}
			else{
				measuringUnit.setMeasuringUnit("");
			}
			if(jsonObj.getString("description") != null && !"".equals(jsonObj.getString("description"))){
				measuringUnit.setDescription(jsonObj.getString("description"));	
			}
			else{
				measuringUnit.setDescription("");
			}
			measuringUnitDao.addMeasuringUnit(measuringUnit);
	}

	@Override
	public Boolean validateMeasuringUnit(String measuringUnit) {
		return measuringUnitDao.validateMeasuringUnit(measuringUnit);
	}
}
