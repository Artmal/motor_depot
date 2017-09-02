CREATE DATABASE motor_depot;

USE motor_depot;

-- Types  of Users -----------------------------------------------------------------------------------------------------
CREATE TABLE users (
  id                    INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  email                 VARCHAR(100) NOT NULL,
  password              VARCHAR(255) NOT NULL,
  date_of_registration  DATETIME     NOT NULL,
  reg_admin_id          INT
)
  ENGINE = InnoDB;

CREATE TABLE persons(
  id                      INT NOT NULL  AUTO_INCREMENT PRIMARY KEY,
  name                    VARCHAR(255) NOT NULL,
  passport_serial_numbers VARCHAR(255),
  phone_number            VARCHAR(50),
  date_of_birth           DATE,
  user_id                 INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users(id)
)
  ENGINE = InnoDB;

CREATE TABLE dispatchers(
  id                      INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  salary_in_dollars       INT NOT NULL,
  person_id               INT NOT NULL,

  FOREIGN KEY (person_id) REFERENCES persons(id)
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

-- Table for cars
CREATE TABLE cars (
  id                  INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  registration_number VARCHAR(50)  NOT NULL,
  car_type            VARCHAR(100) NOT NULL,
  manufacturer        VARCHAR(100) NOT NULL,
  model               VARCHAR(100) NOT NULL,
  production_year     INT,
  number_of_seats     INT          NOT NULL,
  car_color           VARCHAR(100) NOT NULL,
  mileage             INT,
  car_condition       VARCHAR(50)  NOT NULL,
  owner_id            INT,

  FOREIGN KEY (owner_id) REFERENCES persons(id)
)
  ENGINE = InnoDB;

CREATE TABLE trips(
  id                  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  date_of_creation    DATETIME NOT NULL,
  trip_type           VARCHAR(50),
  status              VARCHAR(50),
  town_from           VARCHAR(100),
  town_to             VARCHAR(100),
  time_out            DATETIME,
  time_in             DATETIME,
  payment_in_dollars  INT,
  dispatcher_id       INT
)
  ENGINE = InnoDB;

CREATE TABLE trip_cars(
  trip_id INT NOT NULL,
  car_id  INT NOT NULL,

  FOREIGN KEY (trip_id) REFERENCES trips(id),
  FOREIGN KEY (car_id)  REFERENCES cars(id)
)
  ENGINE = InnoDB;

CREATE TABLE requests(
  id        INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  trip_id   INT NOT NULL,
  car_id    INT NOT NULL,
  datetime  DATETIME,

  FOREIGN KEY (trip_id) REFERENCES trips(id),
  FOREIGN KEY (car_id)  REFERENCES cars(id)
)
  ENGINE = InnoDB;