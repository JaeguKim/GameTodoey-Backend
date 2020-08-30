DROP SCHEMA IF EXISTS `gameTodoey-db-schema`;

CREATE SCHEMA `gameTodoey-db-schema`;

use `gameTodoey-db-schema`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `game`;

CREATE TABLE `game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `popularity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  
  UNIQUE KEY `TITLE_UNIQUE` (`title`)
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rating` FLOAT DEFAULT NULL,
  `comment` varchar(256) DEFAULT NULL,
  `game_id` int(11) DEFAULT NULL,

constraint `rating_min` check((`rating` > 0)),
constraint `rating_max` check((`rating` < 1)),

  PRIMARY KEY (`id`),

  KEY `FK_GAME_ID_idx` (`game_id`),

  CONSTRAINT `FK_GAME` 
  FOREIGN KEY (`game_id`) 
  REFERENCES `game` (`id`) 

  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `game_user`;

CREATE TABLE `game_user` (
  `game_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  
  PRIMARY KEY (`game_id`,`user_id`),
  
  KEY `FK_user_idx` (`user_id`),
  
  CONSTRAINT `FK_GAME` FOREIGN KEY (`game_id`) 
  REFERENCES `game` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
