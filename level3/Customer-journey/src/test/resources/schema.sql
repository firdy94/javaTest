DROP TABLE IF EXISTS customers;

CREATE TABLE IF NOT EXISTS customers (
	id BIGINT primary key auto_increment not null,
	firstname varchar(256) not null,
	lastname varchar(256) not null
	);