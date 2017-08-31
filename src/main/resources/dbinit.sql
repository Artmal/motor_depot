# Inserting all rolles which are necessary for the system
INSERT INTO roles(id, name) VALUES (1, "driver");
INSERT INTO roles(id, name) VALUES (2, "dispatcher");
INSERT INTO roles(id, name) VALUES (3, "admin");

# Inserting admin in to the system
INSERT INTO users(id, username, password, email) VALUES (1, "admin", "pass", "artmalchik@gmail.com");
INSERT INTO user_roles(user_id, role_id) VALUES (1, 3);

# Insert some cars
INSERT INTO cars (registration_number, car_type, manufacturer, model, production_year, number_of_seats, car_color,
                  mileage, car_condition)
VALUES ("AK 9595 MI", "minivan", "Hyundai", "H1", 2007, 12, "grey", 100, "ready");