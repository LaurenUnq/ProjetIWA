
CREATE TABLE locations(
    location_id serial PRIMARY KEY,
    latitude numeric NOT NULL,
    longitude numeric NOT NULL,
    location_date timestamp without time zone NOT NULL
);


CREATE TABLE notifications(
    notification_id serial PRIMARY KEY,
    description varchar(100) NOT NULL,
    viewed boolean not null default false,
    notification_date timestamp without time zone NOT NULL
);

CREATE TABLE user_locations
(
    user_id varchar(36) NOT NULL REFERENCES user_entity (id),
    location_id integer NOT NULL REFERENCES locations (location_id)
);


CREATE TABLE user_notifications(
    user_id varchar(36) NOT NULL REFERENCES user_entity (id),
    notification_id integer NOT NULL REFERENCES notifications (notification_id)
);
