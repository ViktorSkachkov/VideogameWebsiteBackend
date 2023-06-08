CREATE TABLE `addition` (
  `id` int NOT NULL AUTO_INCREMENT,
  `game_id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `description` varchar(1000) NOT NULL,
  `image` mediumblob NOT NULL,
  `time` datetime NOT NULL,
  `deleted` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `addition_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `addition_id` int NOT NULL,
  `user_id` int NOT NULL,
  `units` int NOT NULL,
  `time` datetime NOT NULL,
  `approved` tinyint NOT NULL,
  `total_price` double NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `game_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `game_id` int NOT NULL,
  `user_id` int NOT NULL,
  `units` int NOT NULL,
  `time` datetime NOT NULL,
  `approved` tinyint NOT NULL,
  `total_price` double NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `game_id` int NOT NULL,
  `title` varchar(250) NOT NULL,
  `text` varchar(1000) NOT NULL,
  `image` mediumblob NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `reviewed_item_id` int NOT NULL,
  `user_id` int NOT NULL,
  `text` varchar(1000) NOT NULL,
  `time` datetime NOT NULL,
  `type_of_reviewed_item` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(250) NOT NULL,
  `email` varchar(100) NOT NULL,
  `bank_account` varchar(250) NOT NULL,
  `pwd` varchar(200) NOT NULL,
  `deleted` tinyint NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(100) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `videogame` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `price` double NOT NULL,
  `description` varchar(1000) NOT NULL,
  `image` mediumblob NOT NULL,
  `featured` tinyint NOT NULL,
  `time` datetime NOT NULL,
  `deleted` tinyint NOT NULL,
  PRIMARY KEY (`id`)
);