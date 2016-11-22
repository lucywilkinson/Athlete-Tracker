DROP DATABASE IF EXISTS athlete_tracker;
CREATE DATABASE IF NOT EXISTS athlete_tracker;
USE athlete_tracker;

SELECT 'CREATING DATABASE STRUCTURE' as 'INFO';

DROP TABLE IF EXISTS products,
                     users,
                     users_addresses,
                     countries,
                     cities,
                     shipments,
                     addresses;

set default_storage_engine = InnoDB;

CREATE TABLE products (
  product_id         INT         NOT NULL AUTO_INCREMENT,
  product_name       VARCHAR(50) NOT NULL,
  product_cost       FLOAT       NOT NULL,
  product_quantity   INT         NOT NULL,
  PRIMARY KEY (product_id)
);

CREATE TABLE users (
  user_id      INT         NOT NULL AUTO_INCREMENT,
  first_name   VARCHAR(50) NOT NULL,
  last_name    VARCHAR(50) NOT NULL,
  username     VARCHAR(25) NOT NULL,
  password     VARCHAR(50) NOT NULL,
  active       BOOLEAN     DEFAULT true,
  user_type    ENUM('admin', 'athlete', 'worker'),
  PRIMARY KEY (user_id),
  UNIQUE  KEY (username)
);

CREATE TABLE countries (
  country_id     INT          NOT NULL AUTO_INCREMENT,
  country_name   VARCHAR(50)  NOT NULL,
  PRIMARY KEY (country_id)
);

CREATE TABLE cities (
  city_id      INT          NOT NULL AUTO_INCREMENT,
  country_id   INT          NOT NULL,
  city_name    VARCHAR(50)  NOT NULL,
  state_name   VARCHAR(50),
  FOREIGN KEY (country_id) REFERENCES countries (country_id) ON DELETE CASCADE,
  PRIMARY KEY (city_id)
);

CREATE TABLE addresses (
  address_id    INT         NOT NULL AUTO_INCREMENT,
  city_id       INT         NOT NULL,
  address       VARCHAR(50) NOT NULL,
  postal_code   VARCHAR(10) NOT NULL,
  FOREIGN KEY (city_id) REFERENCES cities (city_id) ON DELETE CASCADE,
  PRIMARY KEY (address_id)
);

CREATE TABLE users_addresses (
  user_id      INT NOT NULL,
  address_id   INT NOT NULL,
  FOREIGN KEY (user_id)    REFERENCES users (user_id) ON DELETE CASCADE,
  FOREIGN KEY (address_id) REFERENCES addresses (address_id) ON DELETE CASCADE,
  PRIMARY KEY (user_id, address_id)
);

CREATE TABLE shipments (
  shipment_id          INT  NOT NULL  AUTO_INCREMENT,
  shipment_product     INT  NOT NULL,
  shipment_creator     INT  NOT NULL,
  shipment_reciever    INT  NOT NULL,
  shipment_address     INT  NOT NULL,
  shipment_fulfilled   BOOL NOT NULL DEFAULT FALSE,
  FOREIGN KEY (shipment_creator)  REFERENCES users (user_id) ON DELETE CASCADE,
  FOREIGN KEY (shipment_product)  REFERENCES products (product_id) ON DELETE CASCADE,
  FOREIGN KEY (shipment_reciever) REFERENCES users (user_id) ON DELETE CASCADE,
  FOREIGN KEY (shipment_address)  REFERENCES addresses (address_id) ON DELETE CASCADE,
  PRIMARY KEY (shipment_id)
);
