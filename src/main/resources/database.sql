CREATE DATABASE motor_depot;

USE motor_depot;

-- Types  of Users -----------------------------------------------------------------------------------------------------
CREATE TABLE users (
  id                    INT          AUTO_INCREMENT PRIMARY KEY,
  email                 VARCHAR(100) NOT NULL,
  password              VARCHAR(255) NOT NULL,
  date_of_registration  DATETIME     NOT NULL,
  reg_admin_id          INT
)
  ENGINE = InnoDB;

CREATE TABLE administrators(
  id                        INT          AUTO_INCREMENT PRIMARY KEY,
  name                      VARCHAR(225) NOT NULL,
  passport_serial_numbers   VARCHAR(30)  NOT NULL,
  phone_number              VARCHAR(30)  NOT NULL,
  user_id                   INT          NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users(id)
)
  ENGINE = InnoDB;

CREATE TABLE dispatchers(
  id                        INT AUTO_INCREMENT PRIMARY KEY,
  name                      VARCHAR(225) NOT NULL,
  passport_serial_numbers   VARCHAR(30),
  phone_number              VARCHAR(30),
  salary_in_dollars         INT,
  user_id                   INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users(id)
)
  ENGINE = InnoDB;

CREATE TABLE drivers(
  id                        INT AUTO_INCREMENT PRIMARY KEY,
  name                      VARCHAR(225) NOT NULL,
  passport_serial_numbers   VARCHAR(30),
  phone_number              VARCHAR(30),
  age                       INT,
  user_id                   INT          NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users(id)
)
  ENGINE = InnoDB;
-- ---------------------------------------------------------------------------------------------------------------------

-- User roles ----------------------------------------------------------------------------------------------------------
CREATE TABLE roles (
  id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
)
  ENGINE = InnoDB;

CREATE TABLE user_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),

  UNIQUE (user_id, role_id)
)
  ENGINE = InnoDB;
-- ---------------------------------------------------------------------------------------------------------------------

-- Cars ----------------------------------------------------------------------------------------------------------------
CREATE TABLE car_types(
  id    INT AUTO_INCREMENT PRIMARY KEY,
  name  VARCHAR(30) NOT NULL
)
  ENGINE = InnoDB;

CREATE TABLE car_condition_types(
  id    INT AUTO_INCREMENT PRIMARY KEY,
  name  VARCHAR(30)
)
  ENGINE = InnoDB;

-- Table for cars
CREATE TABLE cars (
  id                  INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  registration_number VARCHAR(50)  NOT NULL,
  type_id             INT          NOT NULL,
  condition_type_id   INT          NOT NULL,
  model               VARCHAR(100) NOT NULL,
  number_of_seats     INT          NOT NULL,
  color           VARCHAR(50) NOT NULL,
  owner_id            INT          NOT NULL,

  FOREIGN KEY (type_id) REFERENCES car_types(id),
  FOREIGN KEY (condition_type_id) REFERENCES car_condition_types(id),
  FOREIGN KEY (owner_id) REFERENCES drivers(id)
)
  ENGINE = InnoDB;
-- ---------------------------------------------------------------------------------------------------------------------
CREATE TABLE trip_statuses(
  id    INT AUTO_INCREMENT PRIMARY KEY,
  name  VARCHAR(30) NOT NULL
)
  ENGINE = InnoDB;

CREATE TABLE trips(
  id                    INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  date_of_creation      DATETIME NOT NULL,
  status_id             INT NOT NULL,
  car_type_id_required  INT NOT NULL,
  car_id                INT,

  town_from             VARCHAR(100) NOT NULL,
  town_to               VARCHAR(100) NOT NULL,
  time_out              DATETIME,
  time_in               DATETIME,

  payment_in_dollars    INT,
  dispatcher_id         INT,

  FOREIGN KEY (status_id) REFERENCES trip_statuses(id),
  FOREIGN KEY (car_type_id_required) REFERENCES car_types(id),
  FOREIGN KEY (car_id) REFERENCES cars(id),
  FOREIGN KEY (dispatcher_id) REFERENCES dispatchers(id)
)
  ENGINE = InnoDB;

CREATE TABLE requests(
  id      INT AUTO_INCREMENT PRIMARY KEY,
  trip_id INT NOT NULL,
  car_id  INT NOT NULL,

  FOREIGN KEY (trip_id) REFERENCES trips(id),
  FOREIGN KEY (car_id) REFERENCES cars(id)
)
  ENGINE = InnoDB;