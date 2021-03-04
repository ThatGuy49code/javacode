

DROP TABLE bank_account;

--Begining of sql database for Banking postgres database
DROP TABLE bank_account;

DROP TABLE client;

DROP TABLE logs;

CREATE TABLE client(
client_id SERIAL UNIQUE PRIMARY KEY,
admin_id VARCHAR(20),
username VARCHAR(20),
pass VARCHAR(20),
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NOT NULL,
isCust BOOLEAN NOT NULL,
client_status VARCHAR(15)NOT NULL
);

CREATE TYPE client-status AS ENUM ('PROCESSING', 'ONLINE', 'DORMANT', 'ENDED')

INSERT INTO client(username, first_name, last_name, pass, isCust, client_status)
VALUES
('jbueller59', 'Ferris', 'Bueller','cola56','true', 'PROCESSING'),
('jcarlos47', 'Juan', 'Carlos','ripe24','true','ONLINE'),
('giglesias71', 'Gabriel', 'Iglesias', 'threat2','true','DORMANT'),
('ldicaprio23', 'Leo', 'DiCaprio', 'tric34','true','ENDED'),
('mstraus66', 'Monica', 'Straus', 'cross83','true','ONLINE'); 

INSERT INTO client(username, admin_id, first_name, last_name, pass, isCust, client_status )
VALUES
('abrick42','E3925','Brick','Anthony', 'random45','FALSE','ONLINE'),
('tryan31','E0741','Ryan','Trooly', 'tmerr12','FALSE','ONLINE'),  
('rburgandy27','E6359','Ron','Burgandy','gmsa21','FALSE','ONLINE');
--('jbueller59', 'Ferris', 'Bueller','cola56'),
--('jcarlos47', 'Juan', 'Carlos','ripe24'),
--('giglesias71', 'Gabriel', 'Iglesias', 'threat2'),
--('ldicaprio23', 'Leo', 'DiCaprio', 'tric34'),
--('mstraus66', 'Monica', 'Straus', 'cross83'); 
DROP TABLE bank_account;

CREATE TYPE bank-status AS ENUM('PROCESSING', 'ONLINE', 'ENDED')

CREATE TABLE bank_account(
bank_id SERIAL PRIMARY KEY,
user_id INT UNIQUE REFERENCES client(client_id),
mail_addrs VARCHAR(50)NOT NULL,
transaction_inprgrs BOOLEAN NOT NULL,
pass VARCHAR(45) NOT NULL,
bank_status VARCHAR (20) NOT NULL,
starting_dbtbal NUMERIC DEFAULT 0 CHECK(starting_dbtbal >=0)
);


INSERT INTO bank_account(username, pass, debit_balance,credit_balance)
VALUES ('abrick42', 'random45','17324.81','8002.42'),
       ('tryan31', 'tmerr12','12023.47', '7804.35'),
       ('rburgandy27','gmsa21','10618.32', '9482.84'),
       ('mstraus66', 'cross83', '15424.92', '2840.02'),
       ('ldicaprio23', 'tric34','43961.29','8325.24'),
       ('giglesias71', 'threat2','20447.73', '9364.73'),
       ('jcarlos47', 'ripe24','10709.91','2489.42'),
       ('jbueller59','cola56','80220.82', '4890.96');

DROP TABLE checking_account;
      
CREATE TABLE chequing_account(
ck_acctnum VARCHAR(30) NOT NULL,
bank_id INT UNIQUE REFERENCES bank_account(bank_id),
check_bal NUMERIC DEFAULT 0 CHECK(check_bal >=0),
CONSTRAINT ck_acct_pk PRIMARY KEY (bank_id, ck_acctnum)
);

DROP TABLE saving_account;

CREATE TABLE saving_account(
sav_acctnum VARCHAR (30) NOT NULL,
bank_id INT UNIQUE REFERENCES bank_account(bank_id),
sav_bal NUMERIC DEFAULT 0 CHECK(check_bal >=0),
CONSTRAINT sav_acct_pk PRIMARY KEY (bank_id, sav_acctnum)
);

CREATE TYPE trans-status AS ENUM('PROCESSING', 'ACCEPTED', 'DENIED','DROPPED' )
      
CREATE TABLE TRANSACTION(
trans_id SERIAL PRIMARY KEY,
recieving_id INT REFERENCES client(client_id),
sending_id INT REFERENCES client(client_id),
send_acctnum VARCHAR (30) NOT NULL,
trans_status NOT NULL
);

INSERT INTO client(username, pass, isCust, client_status)
VALUES(jbueller)


DROP TABLE logs;
CREATE TABLE logs (
	id SERIAL UNIQUE REFERENCES client(client_id),
	content TEXT NOT NULL,
	"user" VARCHAR(20) NOT NULL	
);


DROP TABLE banking_account;
DROP TABLE "user";
Drop TABLE


drop schema if exists banking101 cascade;
create schema banking101;
set schema 'banking101';


create type "user-status" as enum ('PENDING','ACTIVE','INACTIVE','CLOSED');

create table "user"(
	  "user_id" serial primary key,
	  "email" text unique not null,
	  "first_name" text,
	  "last_name" text,
	  "password" text not null,
	  "isCustomer" boolean not null,
	  "user_status" "user-status" not null 
);

create type "banking-status" as enum('PENDING','ACTIVE','CLOSED');

create table "banking_account"(
		"bank_id" serial primary key,
		"customer_id" int unique references "user"("user_id"),
		"mailing_address" text not null,
		"pending_transaction" boolean not null,
		"banking_status" "banking-status" not null,
		"initial_deposit" numeric(15,2) not null check(initial_deposit >=0)
);

create table "chequing_account"(
		"ca_account_number" TEXT NOT NULL,
		"bank_id" int UNIQUE REFERENCES "banking_account"("bank_id"),
		"ca_balance" numeric(15,2) NOT NULL CHECK(ca_balance >=0),
	    CONSTRAINT "chequing_account_pk" PRIMARY KEY ("bank_id","ca_account_number")
);

create table "saving_account"(
		"sa_account_number" text not null,
		"bank_id" INT UNIQUE REFERENCES "banking_account"("bank_id"),
		"sa_balance" numeric(15,2) NOT NULL CHECK(sa_balance >=0),
	    CONSTRAINT "saving_account_pk" PRIMARY KEY ("bank_id","sa_account_number")
);

create type "transaction-status" as enum ('PENDING','ACCEPTED','REJECTED','CANCELLED');

create table "transaction"(
		"transaction_id" SERIAL PRIMARY KEY,
		"repicient_id" INT REFERENCES "user"("user_id"),
		"sender_id" INT REFERENCES "user"("user_id"),
		"sender_account_number" TEXT NOT NULL,
		"transaction_amount" NUMERIC(15,2) not null check(transaction_amount >=0),
		"transaction_status" "transaction-status" not null		
         );
------------------------------------------------------------------------------------
begin;
insert into "user" ("email","password","isCustomer","user_status") 
values ('kevinspacey@gmail.com','12345',true,'PENDING');
insert into "user" ("email","password","isCustomer","user_status") 
values ('nickgrimes@gmail.com','12345',true,'ACTIVE');


--bank employee
insert into "user" ("email","password","isCustomer","user_status") 
values ('cbank122@bank101.ca','12345',false,'ACTIVE') returning "user_id";

insert into banking_account (customer_id,mailing_address,pending_transaction,banking_status,initial_deposit)
values(2,'17 Hope Dr',false,'ACTIVE',0.00) returning "bank_id";
insert into banking_account (customer_id,mailing_address,pending_transaction,banking_status,initial_deposit)
values(1,'40 Beach Rd',false,'PENDING',0.00) returning "bank_id";

insert into chequing_account (bank_id,ca_account_number,ca_balance) 
values(1,'654456',300.50);
insert into saving_account (bank_id,sa_account_number,sa_balance) 
values(1,'654457',2350.50);
update "user" set first_name = 'Nick', last_name = 'Strong' where email = 'nickgrimes@gmail.com';
update "user" set first_name = 'Ashley', last_name = 'Drummer' where email = 'cbank122@bank122.ca';
update "user" set first_name = 'Kevin', last_name = 'spacey' where email = 'kevinspacey@gmail.com';
insert into "user" ("email","password","first_name","last_name","isCustomer","user_status")
values('charlie@gmail.com','12345','Charlie','Strider',true,'PENDING') returning "user_id";

insert into "banking_account" (customer_id,pending_transaction,mailing_address,banking_status,initial_deposit)
values(4,false,'Not Available','PENDING',0.00);
commit;