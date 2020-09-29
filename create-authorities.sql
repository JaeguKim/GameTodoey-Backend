--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `admins` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('admin1','ROLE_GAME_ADMIN'),
('admin2','ROLE_USER_ADMIN'),
('admin3','ROLE_SUPER_ADMIN');


