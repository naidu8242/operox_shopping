ALTER TABLE `operox`.`LEAVE` 
ADD COLUMN `DESCRIPTION` TEXT(500) NULL AFTER `LEAVE_TYPE`;

ALTER TABLE `operox`.`HOLIDAY` 
ADD COLUMN `DESCRIPTION` TEXT(500) NULL AFTER `YEAR`;
