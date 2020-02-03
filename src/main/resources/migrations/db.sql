
 use operox;
 
 CREATE TABLE operox.USER(
          Id INT NOT NULL AUTO_INCREMENT,
          username VARCHAR(50) NOT NULL ,
		  password VARCHAR(250) NOT NULL ,
		  PRIMARY KEY (Id),
		  INDEX(Id)
  );
  
  CREATE TABLE operox.user_roles (
	  user_role_id INT NOT NULL AUTO_INCREMENT,
	  userId INT,
	  username varchar(50) NOT NULL,
	  role varchar(45) NOT NULL,
	  PRIMARY KEY (user_role_id),
	  FOREIGN KEY(userId) REFERENCES operox.USER(Id)
  );
  
  
# pwd : Pwd123
  
INSERT INTO operox.USER(username,password) VALUES ('operox','c9436c72cea8f1f90296f70c2acb902cq7bff165a0b9c02b4da1a6bc299652afffq73f719ffdc2520d71b0d78f871ac9f0ecq710277cf00cdc41a422eefee6c7a1de11q7');


INSERT INTO operox.user_roles (userId, username, role) VALUES (1, 'operox', 'ROLE_USER');
INSERT INTO operox.user_roles (userId, username, role) VALUES (1, 'operox', 'ROLE_ADMIN');

DROP TABLE IF EXISTS `operox`.`STUDENT`;
 CREATE TABLE operox.STUDENT(
      id INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	  studentName VARCHAR(50)  NULL ,
	  standard VARCHAR(20) NULL ,
	  subjectName VARCHAR(30) NULL ,
	  marks VARCHAR(30) NULL,
	  courseName VARCHAR(30) NULL ,
      duration VARCHAR(20) NULL ,
	  PRIMARY KEY (id),
	  INDEX(`id`) 
  );
