--insert into employees ( manager_id, my_user_id, accesses, salary) values(2,3,123,1000);
--select * from employees
--CREATE DATABASE my_db;
--DROP database OnlineBank
DROP TABLE IF EXISTS employees
DROP TABLE IF EXISTS actionhistory
DROP TABLE IF EXISTS openkeys
DROP TABLE IF EXISTS accounts
DROP TABLE IF EXISTS users
CREATE TABLE `onlinebank`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NULL,
  `user_type` INT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
CREATE TABLE `onlinebank`.`employees` (
  `employee_id` INT NOT NULL AUTO_INCREMENT,
  `manager_id` INT NULL,
  `user_id` INT NOT NULL,
  `accesses` INT NULL,
  `salary` DOUBLE NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE INDEX `employee_id_UNIQUE` (`employee_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
CREATE TABLE `onlinebank`.`accounts` (
  `account_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL,
  `name` VARCHAR(45) NULL,
  `money` DOUBLE NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE INDEX `account_id_UNIQUE` (`account_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
CREATE TABLE `onlinebank`.`openkeys` (
  `open_key_id` INT NOT NULL AUTO_INCREMENT,
  `open_key` VARCHAR(100) NULL,
  `macadress` VARCHAR(45) NULL,
  `account_id` INT NOT NULL,
  UNIQUE INDEX `account_id_UNIQUE` (`open_key_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
CREATE TABLE `onlinebank`.`actionhistory` (
  `date` DATE NOT NULL,
  `user_id` INT NULL,
  `action` VARCHAR(256) NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


INSERT INTO users ( login, password, user_type) VALUE ('gregorysobol', 'malinamalina', 0);
INSERT INTO users ( login, password, user_type) VALUE ('dmitryparshin','decorator', 1);
INSERT INTO users ( login, password, user_type) VALUE ('olegladin', 'nigers', 1);
INSERT INTO users ( login, password, user_type) VALUE ('alexeysidorov', 'supovar', 2);

INSERT INTO employees (manager_id, user_id, accesses, salary) VALUE (NULL, 1, 0, 100000);
INSERT INTO employees (manager_id, user_id, accesses, salary) VALUE (1, 2, 1, 10000);
INSERT INTO employees (manager_id, user_id, accesses, salary) VALUE (2, 3, 3, 1200);

INSERT INTO accounts (user_id, `name`, money) VALUE (1, 'Gregory', 0);
INSERT INTO accounts (user_id, `name`, money) VALUE (2, 'Dmitry', 0);
INSERT INTO accounts (user_id, `name`, money) VALUE (3, 'Oleg', 0);
INSERT INTO accounts (user_id, `name`, money) VALUE (4, 'Leha', 0);

