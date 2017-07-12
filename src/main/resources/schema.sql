drop table user if exists;

CREATE TABLE user
(
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(100) NOT NULL,
	email varchar(100) NOT NULL,
	PRIMARY KEY (id)	 
);



drop table transaction if exists;
 
CREATE TABLE transction 
(
	user_id int(11) NOT NULL,
	transaction_id int(11) NOT NULL,
	type varchar(100) NOT NULL,
	side varchar(100) NOT NULL,
	size int(11) NOT NULL,
	time int(11) NOT NULL,
	price int(11) NOT NULL,
	currency_from varchar(100) NOT NULL,
	currency_to varchar(100) NOT NULL,
	transaction_state varchar(100) NOT NULL,
	limit_price int(11) NOT NULL,
 	PRIMARY KEY (transaction_id),
	FOREIGN KEY (user_id) references user(id)
); 
