-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `asset_management` ;
CREATE SCHEMA IF NOT EXISTS `asset_management` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `asset_management` ;

-- -----------------------------------------------------
-- Table `asset_management`.`vendors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`vendors` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `company_name` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NULL,
  `website` VARCHAR(255) NULL,
  `contact_name` VARCHAR(255) NULL,
  `email` VARCHAR(255) NULL,
  `vendor_type` VARCHAR(255) NULL,
  `description` VARCHAR(255) NULL,
  `rate` DECIMAL(13,2) NULL,
  `date_created` DATE NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idtable1_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `company_name_UNIQUE` (`company_name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `asset_management`.`custom_fields`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`custom_fields` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `value` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `asset_management`.`vendor_custom_fields`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`vendor_custom_fields` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `vendors_id` INT NOT NULL,
  `custom_fields_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_vendor_custom_fields_vendors_idx` (`vendors_id` ASC) VISIBLE,
  INDEX `fk_vendor_custom_fields_custom_fields1_idx` (`custom_fields_id` ASC) VISIBLE,
  CONSTRAINT `fk_vendor_custom_fields_vendors`
    FOREIGN KEY (`vendors_id`)
    REFERENCES `asset_management`.`vendors` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vendor_custom_fields_custom_fields1`
    FOREIGN KEY (`custom_fields_id`)
    REFERENCES `asset_management`.`custom_fields` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
