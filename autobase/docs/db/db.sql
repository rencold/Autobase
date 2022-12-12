CREATE TABLE car (
	id integer PRIMARY KEY AUTOINCREMENT,
	number varchar  NOT NULL,
	brand varchar  NOT NULL,
	model varchar  NOT NULL,
	driveUnit varchar  NOT NULL,
	engine varchar  NOT NULL,
	transmission varchar  NOT NULL,
	driverId integer  NOT NULL REFERENCES driver(id)
);

CREATE TABLE driver (
	id integer PRIMARY KEY AUTOINCREMENT,
	name varchar NOT NULL,
	phoneNumber varchar NOT NULL,
	exp varchar NOT NULL,
	statement string NOT NULL
);

CREATE TABLE run (
	id integer PRIMARY KEY AUTOINCREMENT,
	locationFrom varchar NOT NULL,
	locationTo varchar NOT NULL,
	distance double NOT NULL
);

CREATE TABLE request (
	id integer PRIMARY KEY AUTOINCREMENT,
	custName varchar NOT NULL,
	phoneNumber varchar NOT NULL,
	runId integer NOT NULL REFERENCES run(id),
	carId integer NOT NULL REFERENCES car(id),
	statement string NOT NULL
);




