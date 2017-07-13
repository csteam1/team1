drop table users if exists;

CREATE TABLE users
(
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(100) NOT NULL,
	email varchar(100) NOT NULL,
	PRIMARY KEY (id)	 
);

drop table transaction if exists;
 
CREATE TABLE transaction 
(
	u_id int(11) NOT NULL,
	t_id int(11) NOT NULL,
	typeOrder varchar(100) NOT NULL,
	side varchar(100) NOT NULL,
	lotSize int(11) NOT NULL,
	dateOfTransaction varchar(100) NOT NULL,
	price double(11) NOT NULL,
	currencyFrom varchar(100) NOT NULL,
	currencyTo varchar(100) NOT NULL,
	status varchar(100) NOT NULL,
	limitPrice double(11) NOT NULL,
 	PRIMARY KEY (t_id),
	FOREIGN KEY (u_id) references users(id)
); 



drop table historyTransaction if exists;

CREATE TABLE historyTransaction
(
	currencyFrom varchar(100) NOT NULL,
	currencyTo varchar(100) NOT NULL,
	price double(11) NOT NULL,
	lotSize int(11) NOT NULL,
	dateOfTransaction varchar(100) NOT NULL
);