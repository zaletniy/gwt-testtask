INSERT INTO `role` (`id`,`name`) VALUES (1,'partTimeRole');
INSERT INTO `role` (`id`,`name`) VALUES (2,'fullTimeRole');

INSERT INTO `rule_type`(`id`,`name`,`is_interval`) VALUES(3,'intervalRuleType',1);
INSERT INTO `rule_type`(`id`,`name`,`is_interval`) VALUES(4,'alwaysRuleType',0);
INSERT INTO `rule_type`(`id`,`name`,`is_interval`) VALUES(5,'inactiveRuleType',0);

INSERT INTO `substitutor` (`id`,`name`) VALUES (6,'Ivan');
INSERT INTO `substitutor` (`id`,`name`) VALUES (7,'Bob');
INSERT INTO `substitutor` (`id`,`name`) VALUES (8,'Joe');

INSERT INTO `substitution` (`id`, `substitutor_id`, `rule_type_id`,`role_id`,`begin_date`,`end_date`) 
	VALUES( 9, 6, 3, 1,'2012-03-22', '2012-03-22');

INSERT INTO `hibernate_sequences` (`sequence_next_hi_value`, `sequence_name`) VALUES (10,'DataObject');






