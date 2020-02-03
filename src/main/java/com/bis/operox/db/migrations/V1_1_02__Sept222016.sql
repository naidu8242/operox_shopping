INSERT INTO `operox`.`ROLE` (`ID`, `ROLE_NAME`, `DISPLAY_NAME`) VALUES ('1', 'ROLE_ADMIN', 'ADMIN');
INSERT INTO `operox`.`ROLE` (`ID`, `ROLE_NAME`, `DISPLAY_NAME`) VALUES ('2', 'ROLE_STORE_MANAGER', 'STORE MANAGER');
INSERT INTO `operox`.`ROLE` (`ID`, `ROLE_NAME`, `DISPLAY_NAME`) VALUES ('3', 'ROLE_OPERATION_MANAGER', 'OPERATION MANAGER');
INSERT INTO `operox`.`ROLE` (`ID`, `ROLE_NAME`, `DISPLAY_NAME`) VALUES ('4', 'ROLE_CASHIER', 'CASHIER');
INSERT INTO `operox`.`ROLE` (`ID`, `ROLE_NAME`, `DISPLAY_NAME`) VALUES ('5', 'ROLE_EMPLOYEE', 'EMPLOYEE');


INSERT INTO `operox`.`ADDRESS` (`ID`, `COUNTRY`, `STATE`, `CITY`, `ZIPCODE`, `ADDRESS`, `STATUS`) VALUES ('1', 'India', 'Telangana', 'Hyderabad', '500081', 'Hitex Kaman,Madhapur', '1');

INSERT INTO `operox`.`COMPANY` (`ID`, `COMPANY_NAME`, `ADDRESS_ID`, `STATUS`) VALUES ('1', 'Business Intelli', '1', '1');

INSERT INTO `operox`.`STORE` (`ID`, `STORE_NAME`, `STORE_TYPE`, `COMPANY_ID`, `ADDRESS_ID`, `STATUS`) VALUES ('1', 'Business Intelli Stroe', 'Store', '1', '1', '1');

INSERT INTO `operox`.`DEPARTMENT` (`ID`, `DEPARTMENT_NAME`) VALUES ('1', 'Marketing');

INSERT INTO `operox`.`USER` (`ID`, `EMPLOYEE_ID`, `STORE_ID`, `ADDRESS_ID`, `ROLE_ID`, `DEPARTMENT_ID`, `FIRST_NAME`, `EMAIL`, `USERNAME`, `PASSWORD`, `STATUS`) VALUES ('1', 'Emp1', '1', '1', '1', '1', 'Operox Admin', 'operoxadmin@operox.com', 'operoxadmin@operox.com', 'c9436c72cea8f1f90296f70c2acb902cq7bff165a0b9c02b4da1a6bc299652afffq73f719ffdc2520d71b0d78f871ac9f0ecq710277cf00cdc41a422eefee6c7a1de11q7', '1');

