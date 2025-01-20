CREATE TABLE IF NOT EXISTS goods (
    id INT PRIMARY KEY,
    name VARCHAR(25),
    price FLOAT
);

INSERT INTO goods (id, name, price) VALUES(
    1, 'bread', 25.0
);
INSERT INTO goods (id, name, price) VALUES(
    2, 'milk', 54.0
);
INSERT INTO goods (id, name, price) VALUES(
    3, 'soap', 99.9
);
INSERT INTO goods (id, name, price) VALUES(
    4, 'towel', 100
);
INSERT INTO goods (id, name, price) VALUES(
    5, 'test', 999
);