USE cereal;
#Main Categories
INSERT INTO category (id, name)
VALUES (1, 'mobile and electronic'), (2, 'house and furniture'),
       (3, 'fashion and clothing'), (4, 'grocery and supermarket'),
       (5, 'art and stationery'), (6, 'sports and travel'),
       (7, 'native and local products'), (8, 'industrial construction tools'),
       (9, 'cars and motorcycles'), (10, 'beauty and health');

#First Layer - Sub categories (electronics)
INSERT INTO category (id, name)
VALUES (11, 'mobile'), (12, 'tablet'), (13, 'laptop'), (14, 'pc'),
       (15, 'phone and tablet accessories'), (16,  'mouse'),
       (17, 'keyboard'), (18, 'speaker'), (19, 'cable');


# electronics relation
INSERT INTO categories_prent_child (parent_id, child_id)
VALUES (1, 11), (1, 12), (1, 13), (1, 14);

INSERT INTO categories_prent_child (parent_id, child_id)
VALUES (14, 15), (14, 16), (14, 17), (14, 18), (14, 19);

INSERT INTO categories_prent_child (parent_id, child_id)
VALUES (11, 15), (11, 18), (11, 19);

INSERT INTO categories_prent_child (parent_id, child_id)
VALUES (12, 15), (12, 18), (12, 17);

INSERT INTO categories_prent_child (parent_id, child_id)
VALUES (13, 15), (13, 16), (13, 17), (13, 18), (13, 19);

INSERT INTO shop (id, description,  name)
VALUES (20, 'iran biggest online shop', 'digikala'),
       (21, 'all hail to the one', 'amazon'),
       (22, 'nice mid average clothes shop', 'jeanwest'),
       (23, 'great shop that provides all types of clothes', 'banimode');

INSERT INTO company (id, company_name)
VALUES (24, 'LG'), (25, 'Jeanwest'), (26, 'SONY'), (27, 'Apple'), (28, 'Gucci');

INSERT INTO product (id, product_name, company_id)
VALUES (29, 'Playstation 5', 26), (30, 'Gaming Tv', 24), (31, 'Iphone 12 Pro Max', 27), (32, 'Shirt', 25);

UPDATE cute_sequence SET next_val = 33;

 DROP DATABASE cereal;
 CREATE DATABASE cereal;