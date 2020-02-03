package com.bis.operox.inv.service;

import java.util.List;

import org.json.JSONObject;

import com.bis.operox.inv.dao.entity.MeasuringUnit;
import com.bis.operox.inv.dao.entity.User;

public interface MeasuringUnitService {

	
	MeasuringUnit addMeasuringUnit(MeasuringUnit measuringUnit);
	
	MeasuringUnit getMeasuringUnitById(Long id);

	List<MeasuringUnit> listMeasuringUnit();

	void addMeasuringUnitDetails(JSONObject jsonObj, User user)throws Exception ;

	Boolean validateMeasuringUnit(String measuringUnit);

}
