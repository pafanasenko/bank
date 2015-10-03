
CREATE TABLE Person
(
     id INT NOT NULL,
     firstName VARCHAR(100) NOT NULL,
     lastName VARCHAR(100) NOT NULL,
     role INT NOT NULL,
     isDeleted BOOLEAN NOT NULL,
     CONSTRAINT pk_Person PRIMARY KEY(id)
);

CREATE TABLE Account
(
     id INT NOT NULL,
     id_owner INT NOT NULL,
     value MONEY,
     currency VARCHAR(3),
     isDeleted BOOLEAN NOT NULL,
     CONSTRAINT pk_Account PRIMARY KEY(id)
);