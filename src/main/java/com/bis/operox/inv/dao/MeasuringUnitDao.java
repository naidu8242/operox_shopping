package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.MeasuringUnit;

public interface MeasuringUnitDao {
	
	public MeasuringUnit addMeasuringUnit(MeasuringUnit measuringUnit);
	
	public MeasuringUnit getMeasuringUnitById(Long id);

	public List<MeasuringUnit> listMeasuringUnit();

	public Boolean validateMeasuringUnit(String measuringUnit);
}