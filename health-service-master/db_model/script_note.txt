-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema dev
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dev
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dev` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `dev` ;

-- -----------------------------------------------------
-- Table `dev`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dev`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(15) NOT NULL,
  `password` VARCHAR(15) NOT NULL,
  `category` VARCHAR(10) NULL,
  `last_login_date` DATETIME NULL,
  `created_date` DATETIME NULL,
  `created_by` VARCHAR(45) NULL,
  `last_modified_date` DATETIME NULL,
  `last_modified_by` VARCHAR(45) NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dev`.`user_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dev`.`user_info` (
  `user_info_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `first_name` VARCHAR(20) NULL,
  `middle_name` VARCHAR(20) NULL,
  `last_name` VARCHAR(20) NOT NULL,
  `email` VARCHAR(256) NULL,
  `mobile` BIGINT(10) NULL,
  `gender` VARCHAR(10) NULL,
  `address` VARCHAR(256) NULL,
  `created_date` DATETIME NULL,
  `created_by` VARCHAR(45) NULL,
  `last_modified_date` DATETIME NULL,
  `last_modified_by` VARCHAR(45) NULL,
  PRIMARY KEY (`user_info_id`),
  UNIQUE INDEX `user_info_id_UNIQUE` (`user_info_id` ASC) VISIBLE,
  INDEX `fk_user_id_idx` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `mobile_UNIQUE` (`mobile` ASC) VISIBLE,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `dev`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dev`.`doctor_specialization`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dev`.`doctor_specialization` (
  `doctor_specialization_id` INT NOT NULL AUTO_INCREMENT,
  `specialization_name` VARCHAR(45) NOT NULL,
  `specialization_descrption` VARCHAR(45) NULL,
  `created_date` DATETIME NULL,
  `created_by` VARCHAR(45) NULL,
  `last_modified_date` DATETIME NULL,
  `last_modified_by` VARCHAR(45) NULL,
  PRIMARY KEY (`doctor_specialization_id`),
  UNIQUE INDEX `doctor_specialisation_id_UNIQUE` (`doctor_specialization_id` ASC) VISIBLE,
  UNIQUE INDEX `specialization_name_UNIQUE` (`specialization_name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dev`.`doctor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dev`.`doctor` (
  `doctor_id` INT NOT NULL AUTO_INCREMENT,
  `doctor_specialization_id` INT NOT NULL,
  `full_name` VARCHAR(60) NOT NULL,
  `registration_number` VARCHAR(45) NOT NULL,
  `mobile` INT NULL,
  `email` VARCHAR(256) NULL,
  `gender` VARCHAR(45) NULL,
  `rating` INT NULL,
  `created_date` DATETIME NULL,
  `created_by` VARCHAR(45) NULL,
  `last_modified_date` DATETIME NULL,
  `last_modified_by` VARCHAR(45) NULL,
  PRIMARY KEY (`doctor_id`),
  UNIQUE INDEX `doctor_info_id_UNIQUE` (`doctor_id` ASC) VISIBLE,
  INDEX `fk_doctor_specialization_id_idx` (`doctor_specialization_id` ASC) VISIBLE,
  CONSTRAINT `fk_doctor_specialization_id`
    FOREIGN KEY (`doctor_specialization_id`)
    REFERENCES `dev`.`doctor_specialization` (`doctor_specialization_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dev`.`doctor_schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dev`.`doctor_schedule` (
  `doctor_schedule_id` INT NOT NULL AUTO_INCREMENT,
  `doctor_id` INT NOT NULL,
  `start_time` DATETIME NOT NULL,
  `end_time` DATETIME NOT NULL,
  `capacity` INT NOT NULL,
  `created_date` DATETIME NULL,
  `created_by` VARCHAR(45) NULL,
  `last_modified_date` DATETIME NULL,
  `last_modifed_by` VARCHAR(45) NULL,
  PRIMARY KEY (`doctor_schedule_id`),
  UNIQUE INDEX `doctor_schedule_id_UNIQUE` (`doctor_schedule_id` ASC) VISIBLE,
  INDEX `fk_doctor_id_idx` (`doctor_id` ASC) VISIBLE,
  CONSTRAINT `fk_doctor_id`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `dev`.`doctor` (`doctor_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dev`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dev`.`payment` (
  `payment_id` INT NOT NULL AUTO_INCREMENT,
  `amount` DECIMAL NOT NULL,
  `status` VARCHAR(15) NOT NULL,
  `mode` VARCHAR(15) NULL,
  `reference_id` VARCHAR(45) NULL,
  `created_date` DATETIME GENERATED ALWAYS AS () VIRTUAL,
  `created_by` VARCHAR(45) NULL,
  `last_modified_date` DATETIME NULL,
  `last_modified_by` VARCHAR(45) NULL,
  PRIMARY KEY (`payment_id`),
  UNIQUE INDEX `payment_id_UNIQUE` (`payment_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dev`.`appointment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dev`.`appointment` (
  `appointment_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `doctor_id` INT NOT NULL,
  `payment_id` INT NOT NULL,
  `status` VARCHAR(15) NULL,
  `doctor_schedule_id` INT NOT NULL,
  `created_date` DATETIME NULL,
  `created_by` VARCHAR(45) NULL,
  `last_modified_date` DATETIME NULL,
  `last_modified_by` VARCHAR(45) NULL,
  PRIMARY KEY (`appointment_id`),
  UNIQUE INDEX `appointment_id_UNIQUE` (`appointment_id` ASC) VISIBLE,
  INDEX `fk_doctor_schedule_id_idx` (`doctor_schedule_id` ASC) VISIBLE,
  INDEX `fk_user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_doctor_id_idx` (`doctor_id` ASC) VISIBLE,
  INDEX `fk_payment_id_idx` (`payment_id` ASC) VISIBLE,
  CONSTRAINT `fk_doctor_schedule_id`
    FOREIGN KEY (`doctor_schedule_id`)
    REFERENCES `dev`.`doctor_schedule` (`doctor_schedule_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `dev`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_doctor_id`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `dev`.`doctor` (`doctor_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_id`
    FOREIGN KEY (`payment_id`)
    REFERENCES `dev`.`payment` (`payment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
