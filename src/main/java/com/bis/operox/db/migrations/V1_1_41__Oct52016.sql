CALL operox.AlterTableAddColumn ('operox','BILL_ITEMS','TAX','VARCHAR(50) NULL AFTER `QUANTITY`');
CALL operox.AlterTableAddColumn ('operox','BILL_ITEMS','DISCOUNT','VARCHAR(50) NULL AFTER `TAX`');

CALL operox.AlterTableAddColumn ('operox','BILL','DISCOUNT_ON_INVOICE','FLOAT NULL AFTER `GROSS_AMOUNT`');
CALL operox.AlterTableAddColumn ('operox','BILL','BILL_DATE','DATETIME NULL AFTER `DISCOUNT_ON_INVOICE`');

CALL operox.AlterTableAddColumn ('operox','PRODUCT_STOCK','DISCOUNT','FLOAT NULL AFTER `PRODUCT_ID`');
