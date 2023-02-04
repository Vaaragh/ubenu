USE ubenu_schema;

CREATE TABLE PharmaCompany (
	id varchar(36) not null unique,
    title varchar(50) not null,
    country varchar(3) not null,
    PRIMARY KEY (id)
);


CREATE TABLE DrugCategory (
	id varchar(36) not null unique,
    title varchar(50) not null unique,
    useCase varchar(200) not null,
    descript varchar(500) not null,
	primary key(id)
);

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
    

CREATE TABLE Comment (
	txt varchar(500) not null,
    rating int not null,
    date_of date not null,
	user_id varchar(36) not null,
    drug_id varchar(36) not null,
    anon bool,
    FOREIGN KEY (user_id) REFERENCES UserTable(id),
    FOREIGN KEY (drug_id) REFERENCES Drug(id)
    );

    
CREATE TABLE Wishlist (
	user_id varchar(36) not null,
    drug_id varchar(36) not null,
    FOREIGN KEY (user_id) REFERENCES usertable(id),
    FOREIGN KEY (drug_id) REFERENCES drug(id)
    );
    
CREATE TABLE LoyaltyCard (
	user_id varchar(36) not null,
	points int,
    discount int
    );
    

INSERT INTO UserTable (id, username, password, email, firstName, lastName, dateOfBirth, address, phoneNumber,registrationDateTime, role, active)
VALUES ('111e4567-e89b-12d3-a456-4266f4111000', 'username', 'password', 'email', 'name','last name','1994-06-11','address','123', '2023-01-05 15:15:00', 'CUSTOMER', true);
INSERT INTO UserTable (id, username, password, email, firstName, lastName, dateOfBirth, address, phoneNumber,registrationDateTime, role, active)
VALUES ('111e4567-e89b-12d3-a456-4266f4222000', 'username2', 'password2', 'email2', 'name2','last name2','1994-06-11','address2','123', '2023-01-05 15:15:00', 'CUSTOMER', true);


INSERT INTO Drug (id, title, drugCode, descript, contra, form, image, inventory, price, company_id, category_id, approved)
 values ('423e4567-e89b-12d3-a456-426614174000', 'title', 'asdfghytrewqui', 'opis', 'contra', 'SYRUP', 'https://www.drugs.com/images/pills/fio/ACC03010/trazodone-hydrochloride.JPG',
 20, 320,'223e4267-e89b-12d3-a456-426614174000' , '123e4567-e89b-12s3-a456-426614174108', true);
 
 INSERT INTO Drug (id, title, drugCode, descript, contra, form, image, inventory, price, company_id, category_id, approved)
 values ('523e4567-e89b-12d3-a456-4266f4174000', 'title 2', 'asdfghytrewqui', 'opis', 'contra', 'SYRUP', 'https://www.drugs.com/images/pills/fio/ACC03010/trazodone-hydrochloride.JPG',
 20, 320,'223e4267-e89b-12d3-a456-426614174000' , '123e4567-e89b-12s3-a456-426614174108', true);


INSERT INTO PharmaCompany (id, title, country) VALUES ('223e4267-e89b-12d3-a456-426614174000','Hemofarm', 'SRB');
INSERT INTO PharmaCompany (id, title, country) VALUES ('123e4367-e89b-12d3-a456-426614174020','Primer 2', 'HUN');
INSERT INTO PharmaCompany (id, title, country) VALUES ('123e4467-e89b-12d3-a456-426614174100','Primer 3', 'DEU');


insert into DrugCategory (id, title, useCase, descript) VALUES ('123e4567-e89b-12s3-a456-426614174108', 'analgetik', 'kod bolova i upala', 'lekovi protiv bolova');
insert into DrugCategory (id, title, useCase, descript) VALUES ('123e4567-e89b-12d3-a426-426614174100', 'steroid', 'kod upala', 'lekovi protiv upala');


INSERT INTO wishlist(user_id, drug_id) VALUES ('111e4567-e89b-12d3-a456-4266f4111000','423e4567-e89b-12d3-a456-426614174000');
INSERT INTO wishlist(user_id, drug_id) VALUES ('111e4567-e89b-12d3-a456-4266f4111000','523e4567-e89b-12d3-a456-4266f4174000');


SELECT id, title, drugCode, descript, contra, form, rating, image, inventory, price, company_id, category_id, approved FROM Drug WHERE id in (Select drug_id From wishlist where user_id='111e4567-e89b-12d3-a456-4266f4111000');

SELECT * from drug;

select * from wishlist;


DROP TABLE drug;
DROP TABLE drugcategory;
DROP TABLE pharmacompany;
DROP TABLE usertable;
DROP TABLE wishlist;