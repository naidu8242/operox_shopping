CALL operox.AlterTableAddColumn ('operox','OFFER','COMPANY_ID','BIGINT NULL AFTER `ID`');
ALTER TABLE `operox`.`OFFER` ADD FOREIGN KEY(COMPANY_ID) REFERENCES COMPANY(ID);
