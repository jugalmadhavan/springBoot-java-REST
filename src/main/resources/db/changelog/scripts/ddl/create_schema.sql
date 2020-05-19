--liquibase formatted sql

--changeset jugal:1 logicalFilePath:/scripts/ddl/create_schema runOnChange:true
--validCheckSum: ANY
-- -----------------------------------------------------
-- Schema books
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `books` DEFAULT CHARACTER SET utf8;


-- -----------------------------------------------------
-- Table `books`.`t_books`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `books`.`t_books` (
  `book_id` INT(10) NOT NULL,
  `isbn` INT(15) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(1000) NOT NULL,
  `language` ENUM('GERMAN', 'ENGLISH') NOT NULL,
  `author` VARCHAR(30) NOT NULL,
  `price` INT(10) NOT NULL,
  `update_date` DATETIME NOT NULL,
  `update_user` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`book_id`),
  UNIQUE INDEX `isbn_UNIQUE` (`isbn` ASC));