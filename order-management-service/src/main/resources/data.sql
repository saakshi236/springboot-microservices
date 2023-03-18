drop table ORDER_DETAILS_ORDERITEMS;
create table ORDER_DETAILS_ORDERITEMS(id int auto_increment primary key, order_id int, orderitems_id int);
insert into restaurant(id, name, budget, location) values (1, 'Holiday Inn', 2000, 'Nadiad'), (2, 'Sunflower', 1000, 'Surat');
insert into cuisine(id, name, price, rid) values (1, 'Thepla', 25, 1), (2, 'Pizza', 150, 2), (3, 'Tea', 10, 1), (4, 'Noodles', 100, 2);