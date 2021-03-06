
DROP TABLE IF EXISTS `operox`.`HOLIDAY`;
CREATE TABLE `operox`.`HOLIDAY` (
  ID BIGINT NOT NULL AUTO_INCREMENT,
  USER_ID BIGINT,
  HOLIDAY_NAME VARCHAR(45),
  HOLIDAY_DATE DATE,
  YEAR VARCHAR(45),
  CREATED_DATE DATE,
  CREATED_BY VARCHAR(45),
  UPDATED_DATE DATE,
  UPDATED_BY VARCHAR(45),
  FOREIGN KEY(USER_ID) REFERENCES `operox`.`USER`(ID),
  PRIMARY KEY (`ID`) 
); 


DROP TABLE IF EXISTS `operox`.`LEAVE`;
CREATE TABLE `operox`.`LEAVE` (
  ID BIGINT NOT NULL AUTO_INCREMENT,
  LEAVE_NAME VARCHAR(45),
  BALANCE_LEAVES VARCHAR(45),
  LEAVE_TYPE VARCHAR(45),
  CREATED_BY VARCHAR(45),
  CREATED_DATE DATE,
  UPDATED_DATE DATE,
  UPDATED_BY VARCHAR(45),
  PRIMARY KEY (`ID`) 
); 

DROP TABLE IF EXISTS `operox`.`PAYROLL`;
CREATE TABLE `operox`.`PAYROLL` (
  ID BIGINT NOT NULL AUTO_INCREMENT,
  USER_ID BIGINT,
  ATTENDANCE VARCHAR(45),
  TOTAL_LEAVES int,
  HOLIDAY_ID BIGINT,
  TAX_ID BIGINT,
  LOP VARCHAR(45),
  CREATED_DATE DATE,
  CREATED_BY VARCHAR(45),
  UPDATED_DATE DATE,
  UPDATED_BY VARCHAR(45),
  FOREIGN KEY(USER_ID) REFERENCES `operox`.`USER`(ID),
  FOREIGN KEY(TAX_ID) REFERENCES `operox`.`TAX`(ID),
   FOREIGN KEY(HOLIDAY_ID) REFERENCES `operox`.`HOLIDAY`(ID),
  PRIMARY KEY (`ID`) 
); 


ALTER TABLE `operox`.`OFFER_CUSTOMER_LEVEL` CHANGE COLUMN `CUSTOMER_LEVEL` `RETAIL_CUSTOMER` VARCHAR(2) NULL DEFAULT NULL ;
CALL operox.AlterTableAddColumn ('operox','OFFER_CUSTOMER_LEVEL','WHOLE_SALE_CUSTOMER','VARCHAR(2) NULL AFTER `RETAIL_CUSTOMER`');


CALL operox.AlterTableAddColumn ('operox','OFFER_BUYXGETY','BUY_ITEM_PRODUCT_ID','BIGINT NULL AFTER `OFFER_ID`');
ALTER TABLE `operox`.`OFFER_BUYXGETY`  ADD FOREIGN KEY(BUY_ITEM_PRODUCT_ID) REFERENCES PRODUCT(ID);

CALL operox.AlterTableAddColumn ('operox','OFFER_BUYXGETY','FREE_ITEM_PRODUCT_ID','BIGINT NULL AFTER `BUY_ITEM_QUANTITY`');
ALTER TABLE `operox`.`OFFER_BUYXGETY`  ADD FOREIGN KEY(FREE_ITEM_PRODUCT_ID) REFERENCES PRODUCT(ID);

CALL operox.AlterTableAddColumn ('operox','OFFER_FREE_ITEM_ON_INVOICE','FREE_ITEM_PRODUCT_ID','BIGINT NULL AFTER `TOTAL_INVOICE_AMOUNT`');
ALTER TABLE `operox`.`OFFER_FREE_ITEM_ON_INVOICE`  ADD FOREIGN KEY(FREE_ITEM_PRODUCT_ID) REFERENCES PRODUCT(ID);

