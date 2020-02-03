CALL operox.AlterTableAddColumn ('operox','DEPARTMENT','DEPARTMENT_ID','VARCHAR(250) NULL AFTER `DEPARTMENT_NAME`');
CALL operox.AlterTableAddColumn ('operox','DEPARTMENT','DESCRIPTION','TEXT NULL AFTER `DEPARTMENT_ID`');


CALL operox.AlterTableAddColumn ('operox','STORE','EMAIL','VARCHAR(250) NULL AFTER `STATUS`');
CALL operox.AlterTableAddColumn ('operox','STORE','PHONE_NUMBER','VARCHAR(50) NULL AFTER `EMAIL`');

