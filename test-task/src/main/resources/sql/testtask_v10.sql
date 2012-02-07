create table NamedData (id integer not null, name varchar(255) not null unique, primary key (id), unique (id, name)) ENGINE=InnoDB
create table rule (id integer not null, name varchar(255) not null unique, primary key (id), unique (id, name)) ENGINE=InnoDB
create table rule_type (id integer not null, name varchar(255) not null unique, interval boolean not null, primary key (id), unique (id, name)) ENGINE=InnoDB
create table substitutor (id integer not null, name varchar(255) not null unique, primary key (id), unique (id, name)) ENGINE=InnoDB
create table database_version (id integer not null, name varchar(255) not null unique, primary key (id), unique (id, name)) ENGINE=InnoDB
create table hibernate_sequences ( sequence_name varchar(255),  sequence_next_hi_value integer ) 
