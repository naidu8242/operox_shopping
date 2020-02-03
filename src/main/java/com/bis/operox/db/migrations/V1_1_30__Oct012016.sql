CALL operox.AlterTableAddColumn ('operox','USER','PAYSLIP_FROM_DATE','DATE NULL AFTER `USER_CODE`');

CALL operox.AlterTableAddColumn ('operox','USER','PAYSLIP_TO_DATE','DATE NULL AFTER `PAYSLIP_FROM_DATE`');

