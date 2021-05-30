DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS channels;
DROP TABLE IF EXISTS companies;
DROP TABLE IF EXISTS creditrole;
DROP TABLE IF EXISTS people;
DROP TABLE IF EXISTS people_contact_details;
DROP TABLE IF EXISTS programme;
DROP TABLE IF EXISTS credits;
DROP TABLE IF EXISTS programme_categories;
DROP TABLE IF EXISTS programme_epg;
DROP TABLE IF EXISTS programme_epg_entries;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS reviews;

-- Creation of tables

CREATE TABLE categories
(
    id            SERIAL NOT NULL PRIMARY KEY,
    categorytitle VARCHAR(255) UNIQUE
);

CREATE TABLE channels
(
    id     SERIAL       NOT NULL PRIMARY KEY,
    epg_id INT UNIQUE,
    logo   VARCHAR(255) NOT NULL,
    name   VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE companies
(
    id                SERIAL NOT NULL PRIMARY KEY,
    address           VARCHAR(255),
    lei               VARCHAR(255),
    nbr               VARCHAR(255),
    logo              VARCHAR(255),
    name              VARCHAR(255),
    parent_company_id INT REFERENCES companies
);

CREATE TABLE creditrole
(
    id    INTEGER NOT NULL PRIMARY KEY,
    title VARCHAR(255) UNIQUE
);

CREATE TABLE people
(
    id    SERIAL NOT NULL PRIMARY KEY,
    dob   VARCHAR(255),
    image VARCHAR(255),
    name  VARCHAR(255)
);

-- Mapping a Map-object to SQL
CREATE TABLE people_contact_details
(
    person_id INT          NOT NULL REFERENCES people,
    value     VARCHAR(255),
    key       VARCHAR(255) NOT NULL,
    PRIMARY KEY (person_id, key)
);

CREATE TABLE programme
(
    id         SERIAL  NOT NULL PRIMARY KEY,
    prodyear   INTEGER NOT NULL,
    title      VARCHAR(255),
    channel_id INT REFERENCES channels,
    company_id INT REFERENCES companies
);

CREATE TABLE credits
(
    id           SERIAL NOT NULL PRIMARY KEY,
    person_id    INT REFERENCES people,
    programme_id INT REFERENCES programme,
    role_id      INTEGER REFERENCES creditrole
);

-- Many to Many (Composite Key)
CREATE TABLE programme_categories
(
    programme_id  INT NOT NULL REFERENCES programme,
    categories_id INT NOT NULL REFERENCES categories,
    PRIMARY KEY (programme_id, categories_id)
);

CREATE TABLE programme_epg
(
    id            SERIAL NOT NULL PRIMARY KEY,
    epgchannelid  INT,
    epgidentifier VARCHAR(255),
    start         VARCHAR(255),
    stop          VARCHAR(255),
    title         VARCHAR(255)
);

-- Many to Many (Composite Key)
CREATE TABLE programme_epg_entries
(
    programme_id     INT NOT NULL REFERENCES programme,
    epgprogrammes_id INT NOT NULL UNIQUE REFERENCES programme_epg,
    PRIMARY KEY (programme_id, epgprogrammes_id)
);

CREATE TABLE users
(
    id           SERIAL      NOT NULL PRIMARY KEY,
    email        VARCHAR(255),
    name         VARCHAR(255),
    passwordhash VARCHAR(255),
    username     VARCHAR(255) UNIQUE,
    company_id   INT REFERENCES companies,
    usertype     VARCHAR(31) NOT NULL -- Flag column for inheritance mapping
);

CREATE TABLE reviews
(
    id              INTEGER NOT NULL PRIMARY KEY,
    original        VARCHAR(255),
    state           INTEGER,
    submission_time TIMESTAMP,
    updated         VARCHAR(255),
    programme_id    INT REFERENCES programme,
    user_id         INT REFERENCES users
);

-- Data Population Examples

INSERT INTO creditrole(id, title)
VALUES (1, "Stylist");

INSERT INTO people(id, dob, image, name)
VALUES (1, '1947-06-24T01:29:40.408Z', 'https://randomuser.me/api/portraits/women/79.jpg', "Wilman Kala");

INSERT INTO companies(id, address, lei, nbr, logo, name, parent_company_id)
VALUES (1, 'Example Address 12', '1234421', '', 'TV 2', null);

-- --
-- We don't cover creating an user in the example due to the complexity of hashing the user's password.
-- However it could be done by using the pgcrypto-plugin for Postgres, we decided it was out of scope, and a low
-- priority task to accomplish, and therefore it's done in Java.
--
-- INSERT INTO users(id, email, name, passwordhash, username, company_id, usertype)
-- VALUES (1, 'user@example.com', 'Some Name', '!!!!!!', 'user', 1, 'user');
-- --

INSERT INTO categories(id, categorytitle)
VALUES (1, 'Drama');

INSERT INTO channels(id, epg_id, logo, name)
VALUES (1, 3, 'https://digitalt.tv/pics/kanalloger/tv2.png', 'TV 2');

INSERT INTO programme_categories(programme_id, categories_id)
VALUES (1, 1);

INSERT INTO programme(id, prodyear, title, channel_id, company_id)
VALUES (1, 2001, 'Some Programme Name', 1, 1);

INSERT INTO credits(id, person_id, programme_id, role_id)
VALUES (1, 1, 1, 1);
