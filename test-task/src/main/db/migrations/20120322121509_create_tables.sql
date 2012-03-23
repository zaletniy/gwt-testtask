delimiter $$

CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE `nameddata` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `id` (`id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `id` (`id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE `rule_type` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `is_interval` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `id` (`id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `substitutor` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `id` (`id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `substitution` (
  `id` int(11) NOT NULL,
  `begin_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `rule_type_id` int(11) DEFAULT NULL,
  `substitutor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK313E02FD4B81BFC5` (`role_id`),
  KEY `FK313E02FD9892B2CF` (`substitutor_id`),
  KEY `FK313E02FDB3BFEA9E` (`rule_type_id`),
  CONSTRAINT `FK313E02FDB3BFEA9E` FOREIGN KEY (`rule_type_id`) REFERENCES `rule_type` (`id`),
  CONSTRAINT `FK313E02FD4B81BFC5` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK313E02FD9892B2CF` FOREIGN KEY (`substitutor_id`) REFERENCES `substitutor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$
