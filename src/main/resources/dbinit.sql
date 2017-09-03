# Inserting all roles which are necessary for the system
INSERT INTO roles(id, name) VALUES (1, "driver");
INSERT INTO roles(id, name) VALUES (2, "dispatcher");
INSERT INTO roles(id, name) VALUES (3, "admin");

# Insert admin
INSERT INTO users(id, email, password, date_of_registration) VALUES (1, "artmalchik@gmail.com",
                            "$2a$10$ufibkIP46PDj0nV9JRCWSOsio0kaDhFycRLMPWmyUMQP.O5OyDdDW", NOW());
INSERT INTO user_roles (user_id, role_id) VALUES (1, 3);


# Insert dispatcher
INSERT INTO users (id, email, password, date_of_registration) VALUES (2, "wx_lagger@mail.ru",
  "$2a$10$tC0tDTyqWgxGt6cxYMYOrOC7myfnL58Im4TkMiPb0D1VQaYJGgshi", NOW());
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);

# Insert some cars
INSERT INTO cars (registration_number, car_type, manufacturer, model, production_year, number_of_seats, car_color,
                  mileage, car_condition)
VALUES ("AK 9595 MI", "minivan", "Hyundai", "H1", 2007, 12, "grey", 100, "ready");