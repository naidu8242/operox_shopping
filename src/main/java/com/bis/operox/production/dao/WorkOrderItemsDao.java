package com.bis.operox.production.dao;

import java.util.List;

import com.bis.operox.production.dao.entity.WorkOrderItems;

public interface WorkOrderItemsDao {

	WorkOrderItems saveWorkOrderItems(WorkOrderItems workOrderItems);

	List<WorkOrderItems> getWorkOrderByWorkOrderId(Long workorderId);
}
