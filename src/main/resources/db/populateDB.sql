DELETE FROM voting;
DELETE FROM menuconsist;
DELETE FROM dishes;
DELETE FROM lunchmenus;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

-- users -- id 100000
INSERT INTO users (description, email, password)
VALUES ('User', 'user@yandex.ru', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni');

-- users -- id 100001
INSERT INTO users (description, email, password)
VALUES ('Admin', 'admin@gmail.com', '$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

-- id 100002
INSERT INTO restaurants (description)
  VALUES ('First Restaurant');

-- id 100003
INSERT INTO restaurants (description)
VALUES ('Second Restaurant');

-- id 100004
INSERT INTO lunchmenus (restaurant_id, description)
VALUES (100002, 'Menu of First restaurant');

-- id 100005
INSERT INTO lunchmenus (restaurant_id, description)
VALUES (100003, 'Menu of Second restaurant');

-- id 100006
INSERT INTO dishes (restaurant_id, description, price)
VALUES (100002, 'Dish 1 of First restaurant', 100);

-- id 100007
INSERT INTO dishes (restaurant_id, description, price)
VALUES (100002, 'Dish 2 of First restaurant', 200);

-- id 100008
INSERT INTO dishes (restaurant_id, description, price)
VALUES (100003, 'Dish 1 of Second restaurant', 300);

-- id 100009
INSERT INTO menuconsist (date, restaurant_id, menu_id, dish_id)
VALUES ('2017-10-16', 100002, 100004, 100006);

-- id 100010
INSERT INTO menuconsist (date, restaurant_id, menu_id, dish_id)
VALUES ('2017-10-16', 100002, 100004, 100007);

-- id 100011
INSERT INTO menuconsist (date, restaurant_id, menu_id, dish_id)
VALUES ('2017-10-16', 100003, 100005, 100008);

-- id 100012
INSERT INTO menuconsist (date, restaurant_id, menu_id, dish_id)
VALUES ('2017-10-17', 100002, 100004, 100006);

-- id 100013
INSERT INTO voting (date, user_id, restaurant_id)
VALUES ('2017-10-16', 100000, 100002);

-- id 100014
INSERT INTO voting (date, user_id, restaurant_id)
VALUES ('2017-10-17', 100000, 100003);

-- id 100015
INSERT INTO voting (date, user_id, restaurant_id)
VALUES ('2017-10-16', 100001, 100002);

-- users -- id 100016
INSERT INTO users (description, email, password)
VALUES ('User 2', 'user2@yandex.ru', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100016);
