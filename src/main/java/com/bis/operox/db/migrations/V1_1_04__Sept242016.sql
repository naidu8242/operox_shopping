CALL operox.AlterTableAddColumn ('operox','USER','USER_CODE','VARCHAR(10) NULL AFTER `STATUS`');

UPDATE `operox`.`USER` SET `USER_CODE`='opradmin' WHERE `ID`='1';
