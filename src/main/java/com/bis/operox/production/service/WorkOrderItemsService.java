package com.bis.operox.production.service;

import java.util.List;

import com.bis.operox.production.dao.entity.WorkOrderItems;

public interface WorkOrderItemsService {

	List<WorkOrderItems> getProductsByworkorderId(Long workorderId);

}
