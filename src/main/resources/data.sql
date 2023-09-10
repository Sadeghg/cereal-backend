drop database cereal;
create database cereal;
use cereal;
create table counters
(
    id char(8) primary key
);

create table seed
(
    id int primary key
);
insert into seed
values (0),
       (1),
       (2),
       (3),
       (4),
       (5),
       (6),
       (7),
       (8),
       (9);

insert into counters
select concat(s1.id, s2.id, s3.id) as n
from seed as s1,
     seed as s2,
     seed as s3;

insert into category(title, parent_id)
select concat('category-parent-', counter_id), null
from (select id as counter_id
      from counters limit 12) t;

insert into category(title, parent_id)
select concat('subcategory-level-one-', category_id, counter_id), category_id
from (select categories.id as category_id, counters.id as counter_id
      from (
                   (select id from category) categories,
                       (select id from counters limit 27) counters
               )) t;

insert into category(title, parent_id)
select concat('subcategory-level-two-', category_id, counter_id), category_id
from (select categories.id as category_id, counters.id as counter_id
      from (
                   (select id from category where parent_id is not null) categories,
                       (select id from counters limit 51) counters
               )) t;

insert into company(company_name)
select concat('company-', counter_id)
from (select id as counter_id
      from counters limit 37) t;

insert into categories_for_company(company_id, category_id)
select company_id, category_id
from (select companies.id as company_id, categories.id as category_id
      from (
                   (select id from company) companies,
                       (select * from (  select * from category limit 12) as subquery order by rand() limit 3) categories
               )) t;

insert into shop(name)
select concat('shop-', counter_id)
from (select id as counter_id
      from counters limit 400) t;


insert into categories_for_shop(shop_id, category_id)
select shop_id, category_id
from (select shops.id as shop_id, categories.id as category_id
      from (
                   (select id from shop) shops,
                   (select * from (  select * from category limit 270 offset  12) as subquery order by rand() limit 17) categories
               )) t;

insert into product(product_name, company_id)
select concat('product-', company_id, counter_id), company_id
from (select companies.id as company_id, counters.id as counter_id
      from (
                   (select id from company) companies,
                       (select id from counters limit 120) counters
               )) t;

insert into categories_for_product(product_id, category_id)
select product_id, category_id
from (select products.id as product_id, categories.id as category_id
      from (
               (select id from product) products,
                   (select * from (  select * from category limit 17000 offset  300) as subquery order by rand() limit 11) categories
               )) t;

insert into categories_for_product(product_id, category_id)
select product_id, category_id
from (select products.id as product_id, categories.id as category_id
      from (
               (select id from product) products,
                   (select * from (  select * from category limit 17000 offset  300) as subquery order by rand() limit 7) categories
               )) t;

insert into shop_item(product_price, product_quantity, product_id, shop_id)
select floor(rand() * (10000 - 3 + 1) + 3), floor(rand() * (1000 - 20 + 1) + 20), product_id, shop_id
from (select products.id as product_id, shops.id as shop_id
      from (
               (select id from shop order by rand() limit 57) shops,
               (select id from product order by rand() limit 700) products
               )) t;

insert into shop_item(product_price, product_quantity, product_id, shop_id)
select floor(rand() * (10000 - 3 + 1) + 3), floor(rand() * (100 - 20 + 1) + 20), product_id, shop_id
from (select products.id as product_id, shops.id as shop_id
      from (
               (select id from shop order by rand() limit 143) shops,
                   (select id from product order by rand() limit 100) products
               )) t;

insert into shop_item(product_price, product_quantity, product_id, shop_id)
select floor(rand() * (10000 - 3 + 1) + 3), floor(rand() * (70 - 20 + 1) + 20), product_id, shop_id
from (select products.id as product_id, shops.id as shop_id
      from (
               (select id from shop order by rand() limit 157) shops,
                   (select id from product order by rand() limit 20) products
               )) t;

insert into categories_for_shop_item(shop_item_id, category_id)
select shop_id, category_id
from (select categories.id as category_id, shop_items.id as shop_id
      from (
               (select id from shop_item) shop_items,
                   (select * from (  select * from category limit 17000 offset  300) as subquery order by rand() limit 7) categories
               )) t;
