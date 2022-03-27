# simplemath



## Exercise 1
Having the following scenario:

```
create database test;

create table products ( productID serial primary key, name varchar(30) not null);

create table customers ( customerID serial primary key, name varchar(30) not null);

create table productprices ( productID int4 not null, validfrom timestamp not null, price int4 not null, primary key (productID, validfrom), foreign key (productID) references products (productID) );

create table sales (tranID serial primary key, customerID int4 not null, datetime timestamp not null, foreign key (customerID) references customers (customerID));

create table salesitem (tranID int4 not null, productID int4 not null, amount int4 not null, primary key (tranID, productID), foreign key (tranID) references sales (tranID), foreign key (productID) references products (productID));


insert into products (productID, name) values (1, 'pencil');
insert into products (productID, name) values (2, 'paper');
insert into products (productID, name) values (3, 'backpack');
insert into products (productID, name) values (4, 'black pen');
insert into products (productID, name) values (5, 'red pen');
insert into products (productID, name) values (6, 'eraser');

insert into customers (customerID, name) values (1, 'jim');
insert into customers (customerID, name) values (2, 'ron');
insert into customers (customerID, name) values (3, 'dan');
insert into customers (customerID, name) values (4, 'bowen');

insert into sales (tranID, customerID, datetime) values (1, 1, '2015-01-01 00:00:00');
insert into sales (tranID, customerID, datetime) values (2, 2, '2015-01-03 00:00:00');
insert into sales (tranID, customerID, datetime) values (3, 2, '2015-01-02 00:00:00');
insert into sales (tranID, customerID, datetime) values (4, 3, '2015-01-01 00:00:00');
insert into sales (tranID, customerID, datetime) values (5, 4, '2015-01-01 00:00:00');
insert into sales (tranID, customerID, datetime) values (6, 4, '2017-01-01 00:00:00');
insert into sales (tranID, customerID, datetime) values (7, 1, '2015-01-03 00:00:00');

insert into salesitem (tranID, productID, amount) values (1, 1, 8);
insert into salesitem (tranID, productID, amount) values (1, 2, 8);
insert into salesitem (tranID, productID, amount) values (2, 1, 5);
insert into salesitem (tranID, productID, amount) values (3, 1, 5);
insert into salesitem (tranID, productID, amount) values (4, 3, 11);
insert into salesitem (tranID, productID, amount) values (5, 1, 9);
insert into salesitem (tranID, productID, amount) values (5, 5, 8);
insert into salesitem (tranID, productID, amount) values (6, 5, 10);
insert into salesitem (tranID, productID, amount) values (7, 1, 8);

insert into productprices (productID , validfrom, price) values (1, '2015-01-01 00:00:00', 1);
insert into productprices (productID , validfrom, price) values (2, '2015-01-01 00:00:00', 1);
insert into productprices (productID , validfrom, price) values (3, '2015-01-01 00:00:00', 1);
insert into productprices (productID , validfrom, price) values (4, '2015-01-01 00:00:00', 1);
insert into productprices (productID , validfrom, price) values (5, '2015-01-01 00:00:00', 1);
insert into productprices (productID , validfrom, price) values (6, '2015-01-01 00:00:00', 1);
insert into productprices (productID , validfrom, price) values (2, '2015-01-02 00:00:00', 100);
insert into productprices (productID , validfrom, price) values (6, '2016-01-01 00:00:00', 100);
```

###ANSW A
```
select c.name, si.productid, sum(si.amount) from customers c join sales s on c.customerID = s.customerID join salesitem si on s.tranID = si.tranID where s.datetime between to_date('2015-01-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and to_date('2015-12-31-23:59:59', 'yyyy-MM-dd hh24:mi:ss') group by c.name, si.productid having sum(si.amount) > 9;
```

###ANSW B
```
select p.name, sum(si.amount) from products p join salesitem si on p.productID = si.productID join sales s on si.tranID = s.tranID where s.datetime between to_date('2010-01-01 00:00:00', 'yyyy-MM-dd hh24:mi:ss') and to_date('2015-12-31 23:59:59', 'yyyy-MM-dd hh24:mi:ss') group by p.name order by sum(si.amount), p.name desc;
```

###ANSW C
Answer C is still working in progress, following query does not work properly.

```
select c.customerID, c.name, sum(si.amount * pp.price) from customers c join sales s on c.customerID = s.customerID join salesitem si on s.tranID = si.tranID join productprices pp on si.productID = pp.productID where s.datetime >= pp.validfrom group by c.customerID, c.name order by c.name;
```

## Exercise 2
The present project implements a simple solution about representing aritmetic expressions. 

###Is it possible that in some cases calculation is not finite?
Yes it is, having an `ExpressionMap` with circular dependencies triggers the case. 

### If so, how would you adjust your code to avoid such a situation?
I have implemented a component that check if circular dependencies exist in map to prevent the issue, component implements Khan's algoritm. Class `CheckCircularDependenciesByKhanAlgorithm` is the component.