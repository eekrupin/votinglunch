DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

-- admin
INSERT INTO users (description, email, password)
VALUES ('User', 'user@yandex.ru', '12345');

-- password
INSERT INTO users (description, email, password)
VALUES ('Admin', 'admin@gmail.com', '12345');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);
