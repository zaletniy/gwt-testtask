create table NamedData (id int NOT NULL AUTO_INCREMENT, name varchar(20), primary key (id));
create table role (id int NOT NULL AUTO_INCREMENT, name varchar(20), primary key (id));
create table rule_type (id int NOT NULL AUTO_INCREMENT, name varchar(20), is_interval int, primary key (id));
create table substitution (id int NOT NULL AUTO_INCREMENT, substitutor_id int NOT NULL, rule_type_id int NOT NULL, role_id int NOT NULL, begin_date date, end_date date, primary key (id));
create table substitutor (id int NOT NULL AUTO_INCREMENT, name varchar(200), primary key (id));
/*The shit follows*/
create table hibernate_sequences (id int NOT NULL AUTO_INCREMENT,sequence_next_hi_value int, sequence_name varchar(200), primary key (id));

