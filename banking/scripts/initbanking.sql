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
	bankid int references BankTest.Bank(id));
	
CREATE TABLE BankTest.Account(
	id SERIAL PRIMARY KEY, 
	bankid int references BankTest.Bank(id), 
	customerid int references BankTest.Customer(id), 
	accountNumber varchar, 
	balance int);

CREATE TABLE BankTest.Movement(
	id SERIAL PRIMARY KEY,
	timeOfTransfer date,
	amount int,
 	targetAccount int references BankTest.Account(id),
	sourceAccount int references BankTest.Account(id));