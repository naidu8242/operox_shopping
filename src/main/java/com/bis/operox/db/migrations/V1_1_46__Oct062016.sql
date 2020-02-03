CALL AlterTableRemoveColumn('operox','TICKET','TICKET_CREATED_BY');

CALL operox.AlterTableAddColumn ('operox','TICKET','TICKET_CREATED_BY','BIGINT NULL AFTER `MESSAGE`');
ALTER TABLE `operox`.`TICKET`  ADD FOREIGN KEY(TICKET_CREATED_BY) REFERENCES `operox`.`USER`(ID);




INSERT INTO `operox`.`CURRENCY` (`COMPANY_ID`, `CURRENCY`, `SYMBOL`, `EXCHANGE_VALUE`, `IS_DEFAULT`, `DESCRIPTION`, `STATUS`) VALUES (1, 'dollar', 'USD', '1', 'Y', 'In US Dollars', 1);


ALTER TABLE `operox`.`TICKET`
	CHANGE COLUMN `FILE_CONTENT_TYPE` `FILE_CONTENT_TYPE` VARCHAR(500) NULL DEFAULT NULL AFTER `CUSTOMER_ID`;
	
ALTER TABLE `operox`.`QC_CHECK_LIST`
	CHANGE COLUMN `FILE_CONTENT_TYPE` `FILE_CONTENT_TYPE` VARCHAR(500) NULL DEFAULT NULL AFTER `UPDATED_BY`;
	
ALTER TABLE `operox`.`QC_EXECUTION`
	CHANGE COLUMN `FILE_CONTENT_TYPE` `FILE_CONTENT_TYPE` VARCHAR(500) NULL DEFAULT NULL AFTER `QC_EXECUTION_TYPE`;
	
ALTER TABLE `operox`.`CURRENCY`
	CHANGE COLUMN `EXCHANGE_VALUE` `EXCHANGE_VALUE` FLOAT NULL DEFAULT NULL AFTER `SYMBOL`;
