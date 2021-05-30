CREATE TABLE categories (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    categorytitle VARCHAR(255) UNIQUE
);

CREATE TABLE channels (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    epg_id BIGINT UNIQUE,
    logo VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL UNIQUE
);

create table companies (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    address VARCHAR(255),
    lei VARCHAR(255),
    nbr VARCHAR(255),
    logo VARCHAR(255),
    name VARCHAR(255),
    parent_company_id BIGINT REFERENCES companies
);

CREATE TABLE creditrole (
    id INTEGER NOT NULL PRIMARY KEY,
    title VARCHAR(255) UNIQUE
);

CREATE TABLE people (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    dob VARCHAR(255),
    image VARCHAR(255),
    name VARCHAR(255)
);

-- Mapping a Map-object to SQL
CREATE TABLE people_contact_details (
    person_id bigint NOT NULL REFERENCES people,
    value VARCHAR(255),
    key VARCHAR(255) NOT NULL,
    PRIMARY KEY (person_id, key)
);

CREATE TABLE programme (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    prodyear INTEGER NOT NULL,
    title VARCHAR(255),
    channel_id BIGINT REFERENCES channels,
    company_id BIGINT REFERENCES companies
);

CREATE TABLE credits (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    person_id BIGINT REFERENCES people,
    programme_id BIGINT REFERENCES programme,
    role_id INTEGER REFERENCES creditrole
);

CREATE TABLE programme_categories (
    programme_id  BIGINT NOT NULL REFERENCES programme,
    categories_id BIGINT NOT NULL REFERENCES categories,
    PRIMARY KEY(programme_id, categories_id)
);

CREATE TABLE programme_epg (
    id BIGSERIAL NOT NULL PRIMARY KEY ,
    epgchannelid BIGINT,
    epgidentifier VARCHAR(255),
    start VARCHAR(255),
    stop VARCHAR(255),
    title VARCHAR(255)
);

CREATE TABLE programme_epg_entries (
    programme_id BIGINT NOT NULL REFERENCES programme,
    epgprogrammes_id BIGINT NOT NULL UNIQUE REFERENCES programme_epg
);

CREATE TABLE users (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    email VARCHAR(255),
    name VARCHAR(255),
    passwordhash VARCHAR(255),
    username VARCHAR(255) UNIQUE,
    company_id BIGINT REFERENCES companies,
    usertype VARCHAR(31) NOT NULL -- Flag column for inheritance mapping
);

CREATE TABLE reviews (
    id INTEGER NOT NULL PRIMARY KEY,
    original VARCHAR(255),
    state INTEGER,
    submission_time TIMESTAMP,
    updated VARCHAR(255),
    programme_id BIGINT REFERENCES programme,
    user_id BIGINT REFERENCES users
);
