CALL operox.AlterTableAddColumn ('operox','BILL','CURRENCY_ID','BIGINT NULL AFTER `ID`');
ALTER TABLE `operox`.`BILL` ADD FOREIGN KEY(CURRENCY_ID) REFERENCES operox.CURRENCY(ID);

CALL operox.AlterTableAddColumn ('operox','BILL','ACTUAL_BILL_AMOUNT','FLOAT NULL AFTER `BILL_AMOUNT`');