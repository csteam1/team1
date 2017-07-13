insert into users(id, name, email) values(1,'User1', 'user1@gmail.com');

insert into transaction(U_ID, T_ID, TYPEORDER, SIDE, LOTSIZE, DATEOFTRANSACTION, PRICE, CURRENCYFROM, CURRENCYTO,STATUS, LIMITPRICE) 
values(1,5,'MO','BUY',400,'25',1.303,'USD','JPY','COMPLETED',3.4);

insert into historyTransaction(currencyFrom, currencyTo, price, lotSize, dateOfTransaction) 
	values('USD', 'JPY', 1.50, 100, '2017'); 

insert into historyTransaction(currencyFrom, currencyTo, price, lotSize, dateOfTransaction) 
	values('USD', 'EUR', 1.6, 100, '2017');

insert into historyTransaction(currencyFrom, currencyTo, price, lotSize, dateOfTransaction) 
	values('JPY', 'USD', 60, 100, '2017');

insert into historyTransaction(currencyFrom, currencyTo, price, lotSize, dateOfTransaction) 
	values('EUR', 'USD', .625, 100, '2017'); 