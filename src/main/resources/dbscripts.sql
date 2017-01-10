CREATE TABLE history (
    idhistory bigserial NOT NULL PRIMARY KEY,
    nr1 character(20),
    nr2 character(20),
    op character(20),
    result character(40)
);

INSERT INTO history (nr1, nr2, op, result) VALUES (4,5,'+',9)