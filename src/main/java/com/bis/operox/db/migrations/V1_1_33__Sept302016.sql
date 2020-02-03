#Purchase Order Email
INSERT INTO `operox`.`ENTITY_TYPE` (`ENTITY_NAME`,`ENTITY_TYPE`) VALUES ('PurchaseOrder','Email');
INSERT INTO `operox`.`CLASS_FIELD` (`ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ((SELECT MAX( ID ) FROM `operox`.`ENTITY_TYPE`), 'supplierName', '{{PurchaseOrder.supplierName}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ((SELECT MAX( ID ) FROM `operox`.`ENTITY_TYPE`), 'storeName', '{{PurchaseOrder.storeName}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ((SELECT MAX( ID ) FROM `operox`.`ENTITY_TYPE`), 'location', '{{PurchaseOrder.location}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ((SELECT MAX( ID ) FROM `operox`.`ENTITY_TYPE`), 'productsCount', '{{PurchaseOrder.productsCount}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ((SELECT MAX( ID ) FROM `operox`.`ENTITY_TYPE`), 'orderDate', '{{PurchaseOrder.orderDate}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ((SELECT MAX( ID ) FROM `operox`.`ENTITY_TYPE`), 'loginUserName', '{{PurchaseOrder.loginUserName}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ((SELECT MAX( ID ) FROM `operox`.`ENTITY_TYPE`), 'link', '{{PurchaseOrder.link}}');

INSERT INTO `operox`.`EMAIL_SCENARIO` (`SCENARIO_NAME`, `ENTITY_TYPE`, `UNIQUE_SCENARIO_NAME`, `DESCRIPTION`, `SUBJECT`, `TEMPLATE_TYPE`, `SIGNATURE`, `BODY`,`IS_TEMPLATE_MODIFIED`, `CREATED_BY`, `ORG_CODE`,`LOCATION_CODE`) VALUES ('Purchase Order','Email', 'Purchase Order', 'Used Email To Supplier for Purchase Order', 'Purchase Order by {{PurchaseOrder.storeName}}','org', 'Best Regards,\n{{PurchaseOrder.loginUserName}}', 'Dear {{PurchaseOrder.supplierName}},\n\n Purchase order by {{PurchaseOrder.storeName}} for the {{PurchaseOrder.location}} location.\n\n  Order by : {{PurchaseOrder.storeName}}\n  Number of Products : {{PurchaseOrder.productsCount}}\n  Order Date : {{PurchaseOrder.orderDate}}\n\nFor details click on the following link\n{{PurchaseOrder.link}}\n','N', 'OPADMIN', 'operox', 'operoxIN');

#Purchase Order Notification
INSERT INTO `operox`.`ENTITY_TYPE` (`ENTITY_NAME`, `ENTITY_TYPE`) VALUES ('PurchaseOrder', 'Notification');

INSERT INTO `operox`.`EMAIL_SCENARIO` (`SCENARIO_NAME`, `ENTITY_TYPE`, `UNIQUE_SCENARIO_NAME`, `DESCRIPTION`, `SUBJECT`, `TEMPLATE_TYPE`, `SIGNATURE`, `BODY`,`IS_TEMPLATE_MODIFIED`,`CREATED_BY`, `ORG_CODE`,`LOCATION_CODE`) VALUES ('Purchase Order','Notification','Purchase Order', 'Used for Notification To Supplier for Purchase Order', 'Purchase Order by {{PurchaseOrder.storeName}}', 'operox', '', 'Purchase Order by {{PurchaseOrder.storeName}}','N','OPADMIN', 'operox', 'operoxIN');


#Transfer Stock Email
INSERT INTO `operox`.`ENTITY_TYPE` (`ENTITY_NAME`,`ENTITY_TYPE`) VALUES ('TransferStock','Email');
INSERT INTO `operox`.`CLASS_FIELD` (`ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ((SELECT MAX( ID ) FROM `operox`.`ENTITY_TYPE`), 'fromStoreName', '{{TransferStock.fromStoreName}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ((SELECT MAX( ID ) FROM `operox`.`ENTITY_TYPE`), 'toStoreName', '{{TransferStock.toStoreName}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ((SELECT MAX( ID ) FROM `operox`.`ENTITY_TYPE`), 'transferDate', '{{TransferStock.transferDate}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ((SELECT MAX( ID ) FROM `operox`.`ENTITY_TYPE`), 'receivedDate', '{{TransferStock.receivedDate}}');

INSERT INTO `operox`.`EMAIL_SCENARIO` (`SCENARIO_NAME`, `ENTITY_TYPE`, `UNIQUE_SCENARIO_NAME`, `DESCRIPTION`, `SUBJECT`, `TEMPLATE_TYPE`, `SIGNATURE`, `BODY`,`IS_TEMPLATE_MODIFIED`, `CREATED_BY`, `ORG_CODE`,`LOCATION_CODE`) VALUES ('Transfer Stock','Email', 'Transfer Stock', 'Used Email for Transfer Stock', 'Stock send by {{TransferStock.fromStoreName}}','org', 'Best Regards,\n{{PurchaseOrder.loginUserName}}', 'Dear {{TransferStock.toStoreName}},\n\n  Stock transferred by the {{TransferStock.fromStoreName}}.\n\n  Transferer : {{TransferStock.fromStoreName}}\n  Receiver : {{TransferStock.toStoreName}}\n  Transfer Date : {{TransferStock.transferDate}}\n  Receive Date : {{TransferStock.receivedDate}}\n  Number of Products : {{PurchaseOrder.productsCount}}\n\nFor details click on the following link\n{{PurchaseOrder.link}}\n','N', 'OPADMIN', 'operox', 'operoxIN');


#Transfer Stock Notification
INSERT INTO `operox`.`ENTITY_TYPE` (`ENTITY_NAME`, `ENTITY_TYPE`) VALUES ('TransferStock', 'Notification');

INSERT INTO `operox`.`EMAIL_SCENARIO` (`SCENARIO_NAME`, `ENTITY_TYPE`, `UNIQUE_SCENARIO_NAME`, `DESCRIPTION`, `SUBJECT`, `TEMPLATE_TYPE`, `SIGNATURE`, `BODY`,`IS_TEMPLATE_MODIFIED`,`CREATED_BY`, `ORG_CODE`,`LOCATION_CODE`) VALUES ('Transfer Stock','Notification','Transfer Stock', 'Used for Notification for Transfer Stock', 'Stock send by {{TransferStock.fromStoreName}}', 'operox', '', 'Stock send by {{TransferStock.fromStoreName}}','N','OPADMIN', 'operox', 'operoxIN');




#Received Stock Email
INSERT INTO `operox`.`ENTITY_TYPE` (`ENTITY_NAME`,`ENTITY_TYPE`) VALUES ('ReceivedStock','Email');
INSERT INTO `operox`.`CLASS_FIELD` (`ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ((SELECT MAX( ID ) FROM `operox`.`ENTITY_TYPE`), 'totalAmount', '{{ReceivedStock.totalAmount}');

INSERT INTO `operox`.`EMAIL_SCENARIO` (`SCENARIO_NAME`, `ENTITY_TYPE`, `UNIQUE_SCENARIO_NAME`, `DESCRIPTION`, `SUBJECT`, `TEMPLATE_TYPE`, `SIGNATURE`, `BODY`,`IS_TEMPLATE_MODIFIED`, `CREATED_BY`, `ORG_CODE`,`LOCATION_CODE`) VALUES ('Received Stock','Email', 'Received Stock', 'Used Email for Received Stock', 'Stock received for {{TransferStock.fromStoreName}} on {{TransferStock.receivedDate}}','org', 'Best Regards,\n{{PurchaseOrder.loginUserName}}', 'Dear {{TransferStock.toStoreName}},\n\n  Stock received by the {{TransferStock.fromStoreName}}.\n\n  Stock Sender : {{TransferStock.fromStoreName}}\n  Stock Receiver : {{TransferStock.toStoreName}}\n  Receive Date : {{TransferStock.receivedDate}}\n  Number of Products : {{PurchaseOrder.productsCount}}\n  Total Amount : {{ReceivedStock.totalAmount}\n\nFor details click on the following link\n{{PurchaseOrder.link}}\n','N', 'OPADMIN', 'operox', 'operoxIN');


#Received Stock Notification
INSERT INTO `operox`.`ENTITY_TYPE` (`ENTITY_NAME`, `ENTITY_TYPE`) VALUES ('ReceivedStock', 'Notification');

INSERT INTO `operox`.`EMAIL_SCENARIO` (`SCENARIO_NAME`, `ENTITY_TYPE`, `UNIQUE_SCENARIO_NAME`, `DESCRIPTION`, `SUBJECT`, `TEMPLATE_TYPE`, `SIGNATURE`, `BODY`,`IS_TEMPLATE_MODIFIED`,`CREATED_BY`, `ORG_CODE`,`LOCATION_CODE`) VALUES ('Received Stock','Notification','Received Stock', 'Used for Notification for Received Stock', 'Stock received for {{TransferStock.fromStoreName}} on {{TransferStock.receivedDate}}', 'operox', '', 'Stock received for {{TransferStock.fromStoreName}} on {{TransferStock.receivedDate}}','N','OPADMIN', 'operox', 'operoxIN');
