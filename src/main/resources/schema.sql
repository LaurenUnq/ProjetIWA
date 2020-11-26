DROP TABLE IF EXISTS public.user_notifications;
DROP TABLE IF EXISTS public.user_locations;
DROP TABLE IF EXISTS public.notifications;
DROP TABLE IF EXISTS public.locations;
DROP TABLE IF EXISTS public.user_entity;

CREATE TABLE IF NOT EXISTS public.user_entity
(
    id character varying(36) NOT NULL PRIMARY KEY,
    email character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    username character varying(255) UNIQUE
);

CREATE TABLE IF NOT EXISTS locations(
    location_id serial PRIMARY KEY,
    latitude numeric NOT NULL,
    longitude numeric NOT NULL,
    location_date timestamp without time zone NOT NULL
);


CREATE TABLE IF NOT EXISTS notifications(
    notification_id serial PRIMARY KEY,
    description varchar(100) NOT NULL,
    viewed boolean not null default false,
    notification_date timestamp without time zone NOT NULL
);

CREATE TABLE IF NOT EXISTS user_locations
(
    user_id varchar(36) NOT NULL REFERENCES user_entity (id),
    location_id integer NOT NULL REFERENCES locations (location_id)
);


CREATE TABLE IF NOT EXISTS user_notifications(
    user_id varchar(36) NOT NULL REFERENCES user_entity (id),
    notification_id integer NOT NULL REFERENCES notifications (notification_id)
);
