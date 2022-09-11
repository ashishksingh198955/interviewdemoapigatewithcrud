CREATE TABLE `users` (
  `userid` INT(11) NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `middlename` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `division` VARCHAR(45) NOT NULL,
  `position` VARCHAR(45) NOT NULL,
  `contact` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `status` TINYINT(4) DEFAULT NULL,
  `createdby` INT(4) DEFAULT '0',
  `createdatetime` TIMESTAMP NULL DEFAULT NULL,
  `auditedby` INT(4) DEFAULT NULL,
  `auditeddatetime` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=INNODB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1

CREATE TABLE `roles` (
  `roleid` INT(11) NOT NULL AUTO_INCREMENT,
  `rolename` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`roleid`)
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1


CREATE TABLE `users_roles` (
  `userid` INT(11) NOT NULL,
  `roleid` INT(11) NOT NULL,
  `isactive` TINYINT(4) DEFAULT NULL,
  KEY `user_fk_idx` (`userid`),
  KEY `role_fk_idx` (`roleid`),
  CONSTRAINT `role_fk` FOREIGN KEY (`roleid`) REFERENCES `roles` (`roleid`),
  CONSTRAINT `user_fk` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`)
) ENGINE=INNODB DEFAULT CHARSET=latin1


CREATE TABLE `categoryinfo` (
  `categoryid` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `categoryname` VARCHAR(255) DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`categoryid`)
) ENGINE=INNODB DEFAULT CHARSET=latin1


CREATE TABLE `departmentinfo` (
  `departmentid` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `departmentcode` VARCHAR(255) DEFAULT NULL,
  `departmentname` VARCHAR(255) DEFAULT NULL,
  `organizationname` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`departmentid`)
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1

INSERT INTO `rolemaster` (`rolename`) VALUES ('ROLE_EMPLOYEE');
INSERT INTO `userroles` (`userroles`,`roleid`,`isactive`) VALUES (1,1,true),;






