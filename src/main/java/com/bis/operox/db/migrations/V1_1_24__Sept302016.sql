
INSERT INTO `operox`.`ENTITY_TYPE` (`ID`, `ENTITY_NAME`, `ENTITY_TYPE`) VALUES ('2', 'Ticket By Customer', 'Email');

INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('9', '2', 'administratorFirstName', '{{Ticket.AdministratorFirstName}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('10', '2', 'administratorLastName', '{{Ticket.AdministratorLastName}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('11', '2', 'ticketNo', '{{Ticket.TicketNo}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('12', '2', 'ticketStatus', '{{Ticket.TicketStatus}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('13', '2', 'ticketSubject', '{{Ticket.TicketSubject}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('14', '2', 'ticketIssueDate', '{{Ticket.TicketIssueDate}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('15', '2', 'ticketDueDate', '{{Ticket.TicketDueDate}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('16', '2', 'ticketDescription', '{{Ticket.TicketDescription}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('17', '2', 'ticketSourceType', '{{Ticket.TicketSourceType}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('18', '2', 'customerName', '{{Ticket.CustomerName}}');

INSERT INTO `operox`.`EMAIL_SCENARIO` (`ID`, `SCENARIO_NAME`, `ENTITY_TYPE`, `UNIQUE_SCENARIO_NAME`, `DESCRIPTION`, `SUBJECT`, `TEMPLATE_TYPE`, `SIGNATURE`, `BODY`, `IS_TEMPLATE_MODIFIED`, `CREATED_BY`, `ORG_CODE`, `LOCATION_CODE`) VALUES ('2', 'Ticket By Customer', 'Email', 'Ticket By Customer', 'Ticket By Customer', 'TicketId : {{Ticket.TicketNo}} Status: {{Ticket.TicketStatus}} Subject : {{Ticket.TicketSubject}}', 'org', 'Best Regards,\n{{Ticket.AdministratorFirstName}} {{Ticket.AdministratorLastName}}', 'Hi {{Ticket.CustomerName}},\nWe have recived your complaint we will looking into it soon.And we will send you a personal response.\n\n Ticket No : {{Ticket.TicketNo}} \n Issue Date : {{Ticket.TicketIssueDate}} \n Due Date : {{Ticket.TicketDueDate}} \n Status : {{Ticket.TicketStatus}} \n Subject : {{Ticket.TicketSubject}}\n Source Type : {{Ticket.TicketSourceType}}\n Message : {{Ticket.TicketDescription}}\n\n Thanks for using the Helpdesk. \n If you have any questions or concerens about your request please reach out to \nOperox - Helpdesk Lead', 'N', 'OPADMIN', 'operox', 'operoxIN');



INSERT INTO `operox`.`ENTITY_TYPE` (`ID`, `ENTITY_NAME`, `ENTITY_TYPE`) VALUES ('3', 'Ticket Assigned User', 'Email');

INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('19', '3', 'administratorFirstName', '{{User.AdministratorFirstName}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('20', '3', 'administratorLastName', '{{User.AdministratorLastName}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('21', '3', 'firstName', '{{User.FirstName}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('22', '3', 'lastName', '{{User.LastName}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('23', '3', 'ticketNo', '{{User.TicketNo}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('24', '3', 'loginURL', '{{User.LoginURL}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('25', '3', 'ticketStatus', '{{User.TicketStatus}}');
INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('26', '3', 'ticketSubject', '{{User.TicketSubject}}');


INSERT INTO `operox`.`EMAIL_SCENARIO` (`ID`, `SCENARIO_NAME`, `ENTITY_TYPE`, `UNIQUE_SCENARIO_NAME`, `DESCRIPTION`, `SUBJECT`, `TEMPLATE_TYPE`, `SIGNATURE`, `BODY`, `IS_TEMPLATE_MODIFIED`, `CREATED_BY`, `ORG_CODE`, `LOCATION_CODE`) VALUES ('3', 'Ticket Assigned User', 'Email', 'Ticket Assigned User', 'Ticket Assigned User', 'TicketId {{Ticket.TicketNo}}  {{Ticket.TicketStatus}} {{Ticket.TicketSubject}}', 'org', 'Best Regards,\n{{User.AdministratorFirstName}} {{User.AdministratorLastName}}', 'Hi {{User.FirstName}} {{User.LastName}},\n The support Ticket No : {{Ticket.TicketNo}} has been assigned to you . To review the status of the ticket Please click here.\n {{User.LoginURL}}', 'N', 'OPADMIN', 'operox', 'operoxIN');


CALL operox.AlterTableAddColumn ('operox','USER','COMPENSATATION_TYPE','VARCHAR(20) NULL AFTER `COMPENSATION`');

UPDATE `operox`.`USER` SET `SHIFT_ID`='1', `FIRST_NAME`='Operox', `LAST_NAME`='Admin' WHERE `ID`='1';

INSERT INTO `operox`.`ENTITY_TYPE` (`ID`, `ENTITY_NAME`, `ENTITY_TYPE`) VALUES ('4', 'AssignedUser', 'Notification');
INSERT INTO `operox`.`CLASS_FIELD` (`ID`, `ENTITY_TYPE_ID`, `FIELD_NAME`, `DELIM_STR`) VALUES ('27', '4', 'ticketId', '{{AssignedUser.ticketId}}');
INSERT INTO `operox`.`EMAIL_SCENARIO` (`ID`, `SCENARIO_NAME`, `ENTITY_TYPE`, `UNIQUE_SCENARIO_NAME`, `DESCRIPTION`, `SUBJECT`, `TEMPLATE_TYPE`, `BODY`, `IS_TEMPLATE_MODIFIED`, `CREATED_BY`, `ORG_CODE`, `LOCATION_CODE`) VALUES ('4', 'Assigned User', 'Notification', 'Assigned User', 'Assigned User', 'Ticket {{AssignedUser.ticketId}}  assigned to you', 'org', 'Hi  Ticket {{AssignedUser.ticketId}} is assigned to you', 'N', 'OPADMIN', 'operox', 'operoxIN');

CALL operox.AlterTableAddColumn ('operox','USER','LEAVES','INT NULL AFTER `STATUS`');
