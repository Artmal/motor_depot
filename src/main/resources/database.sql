CREATE DATABASE motor_depot;

USE motor_depot;

CREATE TABLE users (
  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password     VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB;

CREATE TABLE roles (
  id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
)
  ENGINE = InnoDB;

-- Table for mapping user and roles: user_roles
CREATE TABLE user_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),

  UNIQUE (user_id, role_id)
)
  ENGINE = InnoDB;

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
  car_condition       VARCHAR(50)  NOT NULL
)
  ENGINE = InnoDB;

/* TODO ----------------------------------------------------------------------------------------------*/
CREATE TABLE drivers(
  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name     VARCHAR(255) NOT NULL,


  user_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users(id)
)
  ENGINE = InnoDB;

