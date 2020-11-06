CREATE TABLE users(
    username varchar(50) NOT NULL PRIMARY KEY,
    first_name varchar(30),
    last_name varchar(30),
    email varchar(80),
    password varchar(100) NOT NULL
);

CREATE TABLE locations(
    location_id serial PRIMARY KEY,
    latitude numeric(18, 16) NOT NULL,
    longitude numeric(18, 16) NOT NULL,
    location_date time without time zone NOT NULL
);

CREATE TABLE verif_tokens(
  token varchar(50) primary key,
  username varchar(50) 
           REFERENCES users (username),
  expiryDate DATE
);

CREATE TABLE notifications(
    notification_id serial PRIMARY KEY,
    description varchar(100) NOT NULL,
    viewed boolean not null default false,
    notification_date timestamp without time zone NOT NULL
);

CREATE TABLE user_locations
(
    username varchar(50) NOT NULL REFERENCES users (username),
    location_id integer NOT NULL REFERENCES locations (location_id)
);


CREATE TABLE user_notifications(
    username varchar(50) NOT NULL REFERENCES users (username),
    notification_id integer NOT NULL REFERENCES locations (location_id)
);
