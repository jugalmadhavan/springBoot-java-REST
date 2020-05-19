--liquibase formatted sql

--changeset jugal:2 logicalFilePath:/scripts/ddl/alter_table runOnChange:true endDelimiter:#
--validCheckSum: ANY
-- -----------------------------------------------------
-- Schema books
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS alter_tables;
#
CREATE PROCEDURE alter_tables ()
BEGIN
    DECLARE CONTINUE HANDLER FOR 1060 BEGIN END; -- Duplicate column name
    DECLARE CONTINUE HANDLER FOR 1054 BEGIN END; -- Unknown column
    DECLARE CONTINUE HANDLER FOR 1091 BEGIN END; -- check that column/key exists
    DECLARE CONTINUE HANDLER FOR 1146 BEGIN END; -- Table doesn't exist
    DECLARE CONTINUE HANDLER FOR 1022 BEGIN END; -- Can't write, duplicate key in table

    ALTER TABLE `books`.`t_books`
        CHANGE COLUMN `book_id` `book_id` INT(10) NOT NULL AUTO_INCREMENT ;

    ALTER TABLE `books`.`t_books`
        CHANGE COLUMN `update_date` `update_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
        CHANGE COLUMN `update_user` `update_user` VARCHAR(30) NOT NULL DEFAULT 'USER' ;

END
#
call alter_tables();
#
DROP PROCEDURE IF EXISTS alter_tables;
#