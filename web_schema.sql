USE ubenu_schema;

CREATE TABLE PharmaCompany (
	id varchar(36) not null unique,
    title varchar(50) not null,
    country varchar(3) not null,
    PRIMARY KEY (id)
);
-- INSERT INTO PharmaCompany (id, title, country) VALUES ('223e4267-e89b-12d3-a456-426614174000','Hemofarm', 'SRB');
-- INSERT INTO PharmaCompany (id, title, country) VALUES ('123e4367-e89b-12d3-a456-426614174020','Primer 2', 'HUN');
-- INSERT INTO PharmaCompany (id, title, country) VALUES ('123e4467-e89b-12d3-a456-426614174100','Primer 3', 'DEU'); 

INSERT INTO PharmaCompany (id, title, country) VALUES ('pc1','Hemofarm', 'SRB');
INSERT INTO PharmaCompany (id, title, country) VALUES ('pc2','Primer 2', 'HUN');
INSERT INTO PharmaCompany (id, title, country) VALUES ('pc3','Primer 3', 'DEU');



CREATE TABLE DrugCategory (
	id varchar(36) not null unique,
    title varchar(50) not null unique,
    useCase varchar(200) not null,
    descript varchar(500) not null,
	primary key(id)
);
-- insert into DrugCategory (id, title, useCase, descript) VALUES ('123e4567-e89b-12s3-a456-426614174108', 'analgetik', 'kod bolova i upala', 'lekovi protiv bolova');
-- insert into DrugCategory (id, title, useCase, descript) VALUES ('123e4567-e89b-12d3-a426-426614174100', 'steroid', 'kod upala', 'lekovi protiv upala');

insert into DrugCategory (id, title, useCase, descript) VALUES ('dc1', 'analgetik', 'kod bolova i upala', 'lekovi protiv bolova');
insert into DrugCategory (id, title, useCase, descript) VALUES ('dc2', 'steroid', 'kod upala', 'lekovi protiv upala');



CREATE TABLE Drug (
	id varchar(36) not null unique,
    title varchar(50) not null,
    drugCode varchar(14) not null,
    descript varchar(500) not null,
    contra varchar(200) not null,
    form varchar(20) not null,
    image varchar(100) not null,
    inventory int not null,
    price float not null,
    company_id varchar(36) not null,
    category_id varchar(36) not null,
    approved boolean not null,
    PRIMARY KEY (id),
    FOREIGN KEY (company_id) REFERENCES PharmaCompany(id),
    FOREIGN KEY (category_id) REFERENCES DrugCategory(id)  
);
-- INSERT INTO Drug (id, title, drugCode, descript, contra, form, image, inventory, price, company_id, category_id, approved)
--  values ('423e4567-e89b-12d3-a456-426614174000', 'title', 'asdfghytrewqui', 'opis', 'contra', 'SYRUP', 'https://www.drugs.com/images/pills/fio/ACC03010/trazodone-hydrochloride.JPG',
--  20, 320,'223e4267-e89b-12d3-a456-426614174000' , '123e4567-e89b-12s3-a456-426614174108', true);
--  INSERT INTO Drug (id, title, drugCode, descript, contra, form, image, inventory, price, company_id, category_id, approved)
--  values ('523e4567-e89b-12d3-a456-4266f4174000', 'title 2', 'asdfghytrewqui', 'opis', 'contra', 'SYRUP', 'https://www.drugs.com/images/pills/fio/ACC03010/trazodone-hydrochloride.JPG',
--  20, 320,'223e4267-e89b-12d3-a456-426614174000' , '123e4567-e89b-12s3-a456-426614174108', true);



  INSERT INTO Drug (id, title, drugCode, descript, contra, form, image, inventory, price, company_id, category_id, approved)
 values ('d1', 'title 2', 'asdfghytrewqui', 'opis', 'contra', 'SYRUP', 'https://www.drugs.com/images/pills/fio/ACC03010/trazodone-hydrochloride.JPG',
 20, 320,'pc1' , 'dc1', true);
   INSERT INTO Drug (id, title, drugCode, descript, contra, form, image, inventory, price, company_id, category_id, approved)
 values ('d2', 'title 2', 'asdfghytrewqui', 'opis', 'contra', 'SYRUP', 'https://www.drugs.com/images/pills/fio/ACC03010/trazodone-hydrochloride.JPG',
 20, 320,'pc2' , 'dc1', true);



CREATE TABLE ShoppingItem(
	id varchar(36) not null unique,
    drug_id varchar(36) not null,
    amount int not null,
    PRIMARY KEY (id),
    FOREIGN KEY (drug_id) REFERENCES Drug(id)
	);
-- INSERT INTO ShoppingItem (id, drug_id, amount) VALUES ('a23e4567-e89b-12d3-a456-426614174000','523e4567-e89b-12d3-a456-4266f4174000', 5); 
-- INSERT INTO ShoppingItem (id, drug_id, amount) VALUES ('b23e4567-e89b-12d3-a456-426614174000','523e4567-e89b-12d3-a456-4266f4174000', 2); 

INSERT INTO ShoppingItem (id, drug_id, amount) VALUES ('si1','d1', 5); 
INSERT INTO ShoppingItem (id, drug_id, amount) VALUES ('si2','d1', 2); 



CREATE TABLE UserTable (
	id varchar(36) not null unique,
    username varchar(20) not null,
    password varchar(20) not null,
    email varchar(50) not null,
    firstName varchar(20) not null,
    lastName varchar(20) not null,
    dateOfBirth date not null,
    address varchar(50) not null,
    phoneNumber varchar(20) not null,
    registrationDateTime DateTime not null,
    role varchar(20) not null,
    active boolean not null,
    PRIMARY KEY (id)
    );
    
INSERT INTO UserTable (id, username, password, email, firstName, lastName, dateOfBirth, address, phoneNumber,registrationDateTime, role, active)
VALUES ('u2', 'cust', 'cust', 'email2', 'name2','last name2','1994-06-11','address2','123', '2023-01-05 15:15:00', 'CUSTOMER', true);
INSERT INTO UserTable (id, username, password, email, firstName, lastName, dateOfBirth, address, phoneNumber,registrationDateTime, role, active)
VALUES ('u3', 'admin', 'admin', 'email2', 'name2','last name2','1994-06-11','address2','123', '2023-01-05 15:15:00', 'ADMIN', true);
INSERT INTO UserTable (id, username, password, email, firstName, lastName, dateOfBirth, address, phoneNumber,registrationDateTime, role, active)
VALUES ('u4', 'merch', 'merch', 'email2', 'name2','last name2','1994-06-11','address2','123', '2023-01-05 15:15:00', 'MERCHANT', true);

    

CREATE TABLE CustomerOrder (
	id varchar(36) not null unique,
    date_of date not null,
    user_id varchar(36) not null,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES UserTable(id)
	);
    
SELECT id, title, drugCode, descript, contra, form, image, inventory, price, company_id, category_id, approved FROM Drug WHERE id in (SELECT drug_id FROM ShoppingItem WHERE id IN (SELECT item_id FROM CustomerOrderItems WHERE order_id IN (SELECT id FROM CustomerOrder WHERE user_id='u2')));

select * from shoppingitem;
select * from customerorderitems;


Select d.id, SUM(si.amount) amount, d.price from customerOrder co
JOIN customerorderitems coi on co.id=coi.order_id
JOIN ShoppingItem si on coi.item_id=si.id
JOIN Drug d on d.id=si.drug_id
WHERE date_of BETWEEN DATE_SUB(NOW(), INTERVAL 30 DAY) AND NOW()
GROUP BY d.id;
    
    drop table customerorderitems;
    drop table customerorder;
    drop table shoppingItem;
    
CREATE TABLE CustomerOrderItems (
    order_id varchar(36) not null,
	item_id varchar(36) not null,
    FOREIGN KEY (order_id) REFERENCES CustomerOrder(id),
    FOREIGN KEY (item_id) REFERENCES ShoppingItem(id)
    );

    

CREATE TABLE Comment (
	id varchar(36) unique not null,
	txt varchar(500) not null,
    rating int not null,
    date_of date not null,
	user_id varchar(36) not null,
    anon bool,
    FOREIGN KEY (user_id) REFERENCES UserTable(id)
    );

CREATE TABLE RatingTable (
	comm_id varchar(36) not null,
	drug_id varchar(36) not null,
    FOREIGN KEY (comm_id) REFERENCES Comment(id),
    FOREIGN KEY (drug_id) REFERENCES Drug(id)
    );
  
INSERT INTO Comment (id, txt, rating, date_of, user_id, anon) VALUES ('c1', 'text', 3, '2023-01-01', 'u2', 1);
INSERT INTO ratingtable (comm_id, drug_id) VALUES ('c1', 'd1');

INSERT INTO Comment (id, txt, rating, date_of, user_id, anon) VALUES ('c2', 'text 2', 2, '2023-01-01', 'u2', 0);
INSERT INTO ratingtable (comm_id, drug_id) VALUES ('c2', 'd1');
    
  
drop table ratingtable;
update usertable set username='admin', password = 'admin' where id='u3';

    
CREATE TABLE Wishlist (
	user_id varchar(36) not null,
    drug_id varchar(36) not null,
    FOREIGN KEY (user_id) REFERENCES usertable(id),
    FOREIGN KEY (drug_id) REFERENCES drug(id)
    );
    
CREATE TABLE zaBrisanje
(
id varchar(10)
);

Insert into zabrisanje (id) values ('yy');
    

    
CREATE TABLE LoyaltyCard (
	user_id varchar(36) not null unique,
	points int,
    discount float,
    approved boolean,
    FOREIGN KEY (user_id) REFERENCES UserTable(id)
    );
    
Select * From drug;

select * from usertable;

DROP TABLE CustomerOrder;
Drop Table customerorderitems;
drop table shoppingitem;
drop table loyaltycard;
drop table drug;

DROP TABLE drug;
DROP TABLE drugcategory;
DROP TABLE pharmacompany;
DROP TABLE usertable;
DROP TABLE wishlist;