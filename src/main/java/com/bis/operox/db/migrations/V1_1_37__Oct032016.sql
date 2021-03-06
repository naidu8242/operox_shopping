
DROP TABLE IF EXISTS `operox`.`QC_EXECUTION`;
CREATE TABLE `operox`.`QC_EXECUTION` (
  ID BIGINT NOT NULL AUTO_INCREMENT,
  RAW_MATERIAL_ID BIGINT,
  PRODUCT_ID BIGINT,
  QC_CHECK_LIST_ID BIGINT,
  START_DATE DATE,
  END_DATE DATE,
  TOTAL_QUANTITY INT,
  NO_OF_PASSED_UNITS INT,
  NO_OF_FAILED_UNITS INT,
  STATUS INT,
  FILE_CONTENT_TYPE VARCHAR(50),
  FILE_NAME TEXT,
  DOC_FILE MEDIUMBLOB,
  COMMENT TEXT,
  CREATED_DATE DATE,
  CREATED_BY VARCHAR(10),
  UPDATED_DATE DATE,
  UPDATED_BY VARCHAR(10),
  FOREIGN KEY(RAW_MATERIAL_ID) REFERENCES `operox`.`RAW_MATERIAL`(ID),
  FOREIGN KEY(PRODUCT_ID) REFERENCES `operox`.`PRODUCT`(ID),
  FOREIGN KEY(QC_CHECK_LIST_ID) REFERENCES `operox`.`QC_CHECK_LIST`(ID),
  PRIMARY KEY (`ID`) 
); 



DROP TABLE IF EXISTS `operox`.`QC_EXECUTION_RESULTS`;
CREATE TABLE `operox`.`QC_EXECUTION_RESULTS` (
  ID BIGINT NOT NULL AUTO_INCREMENT,
  QC_CHECK_LIST_REPORT_ID BIGINT,
  ACTUAL_VALUE VARCHAR(50),
  RESULT VARCHAR(45),
  REMARKS TEXT,
  STATUS INT,
  CREATED_DATE DATE,
  CREATED_BY VARCHAR(10),
  UPDATED_DATE DATE,
  UPDATED_BY VARCHAR(10),
  FOREIGN KEY(QC_CHECK_LIST_REPORT_ID) REFERENCES `operox`.`QC_CHECK_LIST_REPORT`(ID),
  PRIMARY KEY (`ID`) 
); 


CALL operox.AlterTableAddColumn ('operox','PURCHASE_ORDER','WORK_ORDER_ID','BIGINT NULL AFTER `STORE_ID`');
ALTER TABLE `operox`.`PURCHASE_ORDER`  ADD FOREIGN KEY(WORK_ORDER_ID) REFERENCES `WORK_ORDER`(ID);

CALL operox.AlterTableAddColumn ('operox','PURCHASE_ORDER_ITEMS','RAW_MATERIAL_ID','BIGINT NULL AFTER `PRODUCT_ID`');
ALTER TABLE `operox`.`PURCHASE_ORDER_ITEMS`  ADD FOREIGN KEY(RAW_MATERIAL_ID) REFERENCES `RAW_MATERIAL`(ID);

