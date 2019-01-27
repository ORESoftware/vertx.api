DROP TABLE IF EXISTS USER;

CREATE TABLE USER(
  id INTEGER,
  description VARCHAR2(1000)
);

INSERT INTO USER (id, description) VALUES (1, 'product 1');
INSERT INTO USER (id, description) VALUES (2, 'product 2');
INSERT INTO USER (id, description) VALUES (1, 'product 3');
INSERT INTO USER (id, description) VALUES (4, 'product 4');
