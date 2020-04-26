DROP TABLE IF EXISTS banktest.bank CASCADE;
DROP TABLE IF EXISTS banktest.account CASCADE;
DROP TABLE IF EXISTS banktest.movement CASCADE;
DROP TABLE IF EXISTS banktest.customer CASCADE;
DROP SCHEMA IF EXISTS banktest CASCADE;

SET search_path to banktest;

CREATE SCHEMA BankTest;
SET search_path to "BankTest";

CREATE TABLE BankTest.Bank(
 	id SERIAL PRIMARY KEY,
	cvr varchar, 
 	name varchar);
	
CREATE TABLE BankTest.Customer(
	id SERIAL PRIMARY KEY,
	cpr varchar,
	name varchar,
	bankid int references BankTest.Bank(id)ON DELETE CASCADE);
	
CREATE TABLE BankTest.Account(
	id SERIAL PRIMARY KEY, 
	bankid int references BankTest.Bank(id) ON DELETE CASCADE, 
	customerid int references BankTest.Customer(id) ON DELETE CASCADE, 
	accountNumber varchar, 
	balance int);

CREATE TABLE BankTest.Movement(
	id SERIAL PRIMARY KEY,
	timeOfTransfer date,
	amount int,
 	targetAccount int references BankTest.Account(id) ON DELETE CASCADE,
	sourceAccount int references BankTest.Account(id) ON DELETE CASCADE);

INSERT INTO BankTest.Bank (cvr,name) VALUES ('1234','Jyske Bank');
INSERT INTO BankTest.Bank (cvr,name) VALUES ('4321','Danske Bank');

INSERT INTO BankTest.Customer (cpr,name,bankid) VALUES ('1010102010','Mogens',1);
INSERT INTO BankTest.Customer (cpr,name,bankid) VALUES ('2020201010','Jesper',2);

INSERT INTO BankTest.Account (bankid,customerid,accountnumber,balance) VALUES (1,1,'ABC123',100);
INSERT INTO BankTest.Account (bankid,customerid,accountnumber,balance) VALUES (2,2,'123ABC',200);

INSERT INTO BankTest.Movement(timeoftransfer,amount,targetaccount,sourceaccount) VALUES (now(),100,1,2);