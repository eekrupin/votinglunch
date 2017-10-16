DELETE FROM lunchmenus;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

-- admin
INSERT INTO users (description, email, password)
VALUES ('User', 'user@yandex.ru', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni');

-- password
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

-- id 100004
INSERT INTO lunchmenus (restaurant_id, description)
VALUES (100003, 'Menu of Second restaurant');