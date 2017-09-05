# Inserting all roles which are necessary for the system
INSERT INTO roles(id, name) VALUES (1, "driver");
INSERT INTO roles(id, name) VALUES (2, "dispatcher");
INSERT INTO roles(id, name) VALUES (3, "admin");

# Insert Admin
INSERT INTO users(id, email, password, date_of_registration) VALUES (1, "artmalchik@gmail.com",
                            "$2a$10$ufibkIP46PDj0nV9JRCWSOsio0kaDhFycRLMPWmyUMQP.O5OyDdDW", NOW());
INSERT INTO user_roles (user_id, role_id) VALUES (1, 3);


# Insert Dispatcher
INSERT INTO users (id, email, password, date_of_registration) VALUES (2, "wx_lagger@mail.ru",
  "$2a$10$tC0tDTyqWgxGt6cxYMYOrOC7myfnL58Im4TkMiPb0D1VQaYJGgshi", NOW());
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);

# Insert car condition types
INSERT INTO car_condition_types (id, name) VALUES (1, "Broken");
INSERT INTO car_condition_types (id, name) VALUES (2, "Repairing");
INSERT INTO car_condition_types (id, name) VALUES (3, "Ready");

# Insert car types
INSERT INTO car_types (id, name) VALUES (1, "Van");
INSERT INTO car_types (id, name) VALUES (2, "Minivan");
INSERT INTO car_types (id, name) VALUES (3, "Campervan");
INSERT INTO car_types (id, name) VALUES (4, "Mini_truck");
INSERT INTO car_types (id, name) VALUES (5, "Truck");
INSERT INTO car_types (id, name) VALUES (6, "Big_truck");
INSERT INTO car_types (id, name) VALUES (7, "Micro");
INSERT INTO car_types (id, name) VALUES (8, "Sedan");
INSERT INTO car_types (id, name) VALUES (9, "CUV");
INSERT INTO car_types (id, name) VALUES (10, "SUV");
INSERT INTO car_types (id, name) VALUES (11, "Hatchback");
INSERT INTO car_types (id, name) VALUES (12, "Roadster");
INSERT INTO car_types (id, name) VALUES (13, "Pickup");
INSERT INTO car_types (id, name) VALUES (14, "Coupe");
INSERT INTO car_types (id, name) VALUES (15, "Supercar");
INSERT INTO car_types (id, name) VALUES (16, "Cabriolet");

# Insert trip statuses
INSERT INTO trip_statuses (id, name) VALUES (1, "Open");
INSERT INTO trip_statuses (id, name) VALUES (2, "In progress");
INSERT INTO trip_statuses (id, name) VALUES (3, "Closed");
INSERT INTO trip_statuses (id, name) VALUES (4, "Canceled");