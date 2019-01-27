DROP TABLE IF EXISTS HURU_USER;

CREATE TABLE HURU_USER(
  id INTEGER,
  description VARCHAR(1000)
);

INSERT INTO HURU_USER (id, description) VALUES (1, 'product 1');
INSERT INTO HURU_USER (id, description) VALUES (2, 'product 2');
INSERT INTO HURU_USER (id, description) VALUES (1, 'product 3');
INSERT INTO HURU_USER (id, description) VALUES (4, 'product 4');
