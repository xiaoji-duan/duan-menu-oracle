CREATE TABLE `abc_shortapplication` (
  `NAME` varchar(64) NOT NULL,
  `DESCRIPTION` varchar(256) DEFAULT NULL,
  `PREFIX` varchar(3) NOT NULL,
  `LOGO` varchar(1024) DEFAULT NULL,
  `OWNER` varchar(200) NOT NULL,
  `AUTHORIZE` varchar(32) DEFAULT 'AUTHORIZED',
  `CREATE_TIME` date DEFAULT NULL,
  PRIMARY KEY (`PREFIX`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
