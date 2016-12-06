USE athlete_tracker;

/* DELETE ALL PREVIOUS RECORDS */
DELETE FROM addresses;
DELETE FROM cities;
DELETE FROM countries;
DELETE FROM products;
DELETE FROM shipments;
DELETE FROM users;
DELETE FROM users_addresses;

/* RESET ALL PRIMARY KEYS */
ALTER TABLE addresses        AUTO_INCREMENT = 1;
ALTER TABLE cities           AUTO_INCREMENT = 1;
ALTER TABLE countries        AUTO_INCREMENT = 1;
ALTER TABLE products         AUTO_INCREMENT = 1;
ALTER TABLE shipments        AUTO_INCREMENT = 1;
ALTER TABLE users            AUTO_INCREMENT = 1;

/* CREATE USERS */
INSERT INTO users (first_name, last_name, username, email, password, user_type)
VALUES (
  "Matt",
  "Uhlar",
  "Clacious",
  "Clacious@colorado.edu",
  "Clacious-1",
  "Athlete"
);

INSERT INTO users (first_name, last_name, username, email, password, active, user_type)
VALUES (
  "Mark",
  "Uhlar",
  "DogBone",
  "Clacious@colorado.edu",
  "Clacious-1",
  false,
  "Athlete"
);

INSERT INTO users (first_name, last_name, username, email, password, active, user_type)
VALUES (
  "Malk",
  "Uhlar",
  "FishBone",
  "Clacious@colorado.edu",
  "Clacious-1",
  false,
  "Athlete"
);

INSERT INTO users (first_name, last_name, username, email, password, user_type)
VALUES (
  "Alex",
  "Goodwin",
  "Dragon",
  "Dragon@colorado.edu",
  "Dragon-1",
  "Admin"
);

INSERT INTO users (first_name, last_name, username, email, password, user_type)
VALUES (
  "Lucy",
  "Wilkinson",
  "Bird",
  "Bird@colorado.edu",
  "Bird-1",
  "Athlete"
);

INSERT INTO users (first_name, last_name, username, email, password, user_type)
VALUES (
  "Dymtro",
  "Ryzhkov",
  "Fish",
  "Fish@colorado.edu",
  "Fish-1",
  "Worker"
);

/* CREATE COUNTRIES */
INSERT INTO countries (country_name) VALUES ("United States");
INSERT INTO countries (country_name) VALUES ("Canada");
INSERT INTO countries (country_name) VALUES ("Mexico");

/* CREATE CITIES */
INSERT INTO cities (city_name, state_name) VALUES ("Boulder", "CO");
INSERT INTO cities (city_name, state_name) VALUES ("Seattle", "WA"); 
INSERT INTO cities (city_name) VALUES ("Vancouver");
INSERT INTO cities (city_name) VALUES ("Mexico City");

/* CREATE ADDRESSES */
INSERT INTO addresses (city_id, country_id, address, postal_code)
VALUES (
  (SELECT city_id    FROM cities    WHERE city_name = "Boulder"),
  (SELECT country_id FROM countries WHERE country_name = "United States"),
  "1600 Pennsylvania Ave",
  "80302"
);

INSERT INTO addresses (city_id, country_id, address, postal_code)
VALUES (
  (SELECT city_id    FROM cities    WHERE city_name = "Seattle"),
  (SELECT country_id FROM countries WHERE country_name = "United States"),
  "910 Lenora",
  "98074"
);

INSERT INTO addresses (city_id, country_id, address, postal_code)
VALUES (
  (SELECT city_id    FROM cities    WHERE city_name = "Vancouver"),
  (SELECT country_id FROM countries WHERE country_name = "Canada"),
  "1234 Granville St",
  "12345"
);

INSERT INTO addresses (city_id, country_id, address, postal_code)
VALUES (
  (SELECT city_id    FROM cities    WHERE city_name = "Mexico City"),
  (SELECT country_id FROM countries WHERE country_name = "Mexico"),
  "Sicario Dr",
  "66666"
);

/* CREATE USERS_ADDRESSES JUNCTION TABLE */
INSERT INTO users_addresses (user_id, address_id)
VALUES (
  (SELECT user_id    FROM users WHERE username = "Clacious"),
  (SELECT address_id FROM addresses 
    WHERE city_id      = (SELECT city_id FROM cities WHERE city_name = "Seattle" AND state_name = "WA")
    AND   country_id   = (SELECT country_id FROM countries WHERE country_name = "United States")
    AND   address      = "910 Lenora"
    AND   postal_code  = "98074"
  )
);

INSERT INTO users_addresses (user_id, address_id)
VALUES (
  (SELECT user_id    FROM users WHERE username = "Dragon"),
  (SELECT address_id FROM addresses 
    WHERE city_id      = (SELECT city_id FROM cities WHERE city_name = "Boulder" AND state_name = "CO")
    AND   country_id   = (SELECT country_id FROM countries WHERE country_name = "United States")
    AND   address      = "1600 Pennsylvania Ave"
    AND   postal_code  = "80302"
  )
);

INSERT INTO users_addresses (user_id, address_id)
VALUES (
  (SELECT user_id    FROM users WHERE username = "Bird"),
  (SELECT address_id FROM addresses 
    WHERE city_id      = (SELECT city_id FROM cities WHERE city_name = "Vancouver")
    AND   country_id   = (SELECT country_id FROM countries WHERE country_name = "Canada")
    AND   address      = "1234 Granville St"
    AND   postal_code  = "12345"
  )
);

INSERT INTO users_addresses (user_id, address_id)
VALUES (
  (SELECT user_id    FROM users WHERE username = "Fish"),
  (SELECT address_id FROM addresses 
    WHERE city_id      = (SELECT city_id FROM cities WHERE city_name = "Mexico City")
    AND   country_id   = (SELECT country_id FROM countries WHERE country_name = "Mexico")
    AND   address      = "Sicario Dr"
    AND   postal_code  = "66666"
  )
);

/* CREATE PRODUCTS */
INSERT INTO products (product_name, product_cost, product_quantity)
VALUES (
  "Sticker",
  0.50,
  10000
);

INSERT INTO products (product_name, product_cost, product_quantity)
VALUES (
  "Poster",
  5.00,
  100
);

INSERT INTO products (product_name, product_cost, product_quantity)
VALUES (
  "Jersey",
  50.00,
  750
);

/* CREATE SHIPMENTS */
INSERT INTO shipments (shipment_product, shipment_creator, shipment_worker, shipment_reciever, shipment_quantity, shipment_address)
VALUES (
  (SELECT product_id FROM products WHERE product_name = "Jersey"),
  (SELECT user_id FROM users WHERE username = "Dragon" AND user_type = "Admin"),
  (SELECT user_id FROM users WHERE username = "Fish" AND user_type = "Worker"),
  (SELECT user_id FROM users WHERE username = "Clacious" AND user_type = "Athlete"),
  10,
  (Select address_id FROM users_addresses WHERE user_id = (
    SELECT user_id FROM users WHERE username = "Clacious" AND user_type = "Athlete"
  ))
);

INSERT INTO shipments (shipment_product, shipment_creator, shipment_worker, shipment_reciever, shipment_quantity, shipment_address)
VALUES (
  (SELECT product_id FROM products WHERE product_name = "Sticker"),
  (SELECT user_id FROM users WHERE username = "Dragon" AND user_type = "Admin"),
  (SELECT user_id FROM users WHERE username = "Fish" AND user_type = "Worker"),
  (SELECT user_id FROM users WHERE username = "Bird" AND user_type = "Athlete"),
  150,
  (Select address_id FROM users_addresses WHERE user_id = (
    SELECT user_id FROM users WHERE username = "Bird" AND user_type = "Athlete"
  ))
);
