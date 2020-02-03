CALL operox.AlterTableAddColumn ('operox','ADDRESS','NAME','VARCHAR(100) NULL AFTER `ADDRESS`');

CALL operox.AlterTableAddColumn ('operox','ADDRESS','LANDMARK','VARCHAR(500) NULL AFTER `NAME`');

CALL operox.AlterTableAddColumn ('operox','ADDRESS','PHONE','VARCHAR(20) NULL AFTER `LANDMARK`');

CALL operox.AlterTableAddColumn ('operox','PAYMENT_DETAILS','CARD_HOLDER','VARCHAR(100) NULL AFTER `CARD_NUMBER`');

ALTER TABLE `operox`.`PAYMENT_DETAILS` 
CHANGE COLUMN `CARD_NUMBER` `CARD_NUMBER` VARCHAR(40) NULL DEFAULT NULL ,
CHANGE COLUMN `GIFT_CARD_NUMBER` `GIFT_CARD_NUMBER` VARCHAR(40) NULL DEFAULT NULL ,
CHANGE COLUMN `COUPON1_AMOUNT` `COUPON1_AMOUNT` VARCHAR(40) NULL DEFAULT NULL ;

ALTER TABLE `operox`.`USER` ADD COLUMN `VERIFICATION_CODE` VARCHAR(45) NULL AFTER `GENDER`;

INSERT INTO `operox`.`ENTITY_TYPE` (`ID`, `ENTITY_NAME`, `ENTITY_TYPE`) VALUES ('11', 'Forgot Password', 'Email');

INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('40', '11', 'firstName', '{{ForgotPassword.FirstName}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('41', '11', 'lastName', '{{ForgotPassword.LastName}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('42', '11', 'verificationCode', '{{ForgotPassword.verificationCode}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('43', '11', 'resetPasswordURL', '{{ForgotPassword.ResetPasswordURL}}');

INSERT INTO `operox`.`EMAIL_SCENARIO` (`ID`, `SCENARIO_NAME`, `ENTITY_TYPE`, `UNIQUE_SCENARIO_NAME`, `DESCRIPTION`, `SUBJECT`, `TEMPLATE_TYPE`, `SIGNATURE`, `BODY`, `IS_TEMPLATE_MODIFIED`, `CREATED_BY`, `ORG_CODE`, `LOCATION_CODE`) VALUES ('11', 'Forgot Password', 'Email', 'Forgot Password', 'Used for Forgot password', 'Request to Reset your Operox Password', 'operox', 'Best Regards,\nOperox.', 'Dear {{ForgotPassword.FirstName}} {{ForgotPassword.LastName}},\n\nWe received a request to reset your password for your Operox account.\n\nVerification Code : {{ForgotPassword.verificationCode}}\n\nTo reset your password process simply click the link below\n\n{{ForgotPassword.ResetPasswordURL}}\n\nIf you didn\'t ask to change your password ,don\'t worry! Your password is still safe and access the operox with existing credentials.', 'N', 'OPADMIN', 'operox', 'operolxIN');

