-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema asset_management
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema asset_management
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `asset_management`;
CREATE SCHEMA IF NOT EXISTS `asset_management` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `asset_management` ;

-- -----------------------------------------------------
-- Table `asset_management`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`company` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`asset`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`asset` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_company_id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(970) NULL DEFAULT NULL,
  `model` VARCHAR(255) NULL DEFAULT NULL,
  `category` VARCHAR(255) NULL DEFAULT NULL,
  `area` VARCHAR(255) NULL DEFAULT NULL,
  `date_created` DATE NOT NULL,
  `creator_user_id` INT NOT NULL,
  `date_purchased` DATE NULL DEFAULT NULL,
  `barcode` BIGINT NULL DEFAULT NULL,
  `purchase_price` DECIMAL(10,0) NULL DEFAULT NULL,
  `residual_value` DECIMAL(10,0) NULL DEFAULT NULL,
  `useful_life_unit` VARCHAR(45) NULL DEFAULT NULL,
  `useful_life_quantity` INT NULL DEFAULT NULL,
  `date_placed_in_service` DATE NULL DEFAULT NULL,
  `date_warranty_ends` DATE NULL DEFAULT NULL,
  `additional_info` VARCHAR(255) NULL DEFAULT NULL,
  `track_and_log_usage` TINYINT NOT NULL DEFAULT '0',
  `check_in_procedure` VARCHAR(255) NULL DEFAULT NULL,
  `check_out_procedure` VARCHAR(255) NULL DEFAULT NULL,
  `primary_user_id` INT NULL DEFAULT NULL,
  `parent_asset_id` INT NULL DEFAULT NULL,
  `location_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `barcode_UNIQUE` (`barcode` ASC) VISIBLE,
  INDEX `fk_assets_users1_idx` (`primary_user_id` ASC) VISIBLE,
  INDEX `fk_assets_users3_idx` (`creator_user_id` ASC) VISIBLE,
  INDEX `fk_assets_assets1_idx` (`parent_asset_id` ASC) VISIBLE,
  INDEX `fk_asset_company1_idx` (`user_company_id` ASC) VISIBLE,
  INDEX `fk_asset_location1_idx` (`location_id` ASC) VISIBLE,
  CONSTRAINT `fk_asset_company1`
    FOREIGN KEY (`user_company_id`)
    REFERENCES `asset_management`.`company` (`id`),
  CONSTRAINT `fk_asset_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `asset_management`.`location` (`id`),
  CONSTRAINT `fk_assets_assets1`
    FOREIGN KEY (`parent_asset_id`)
    REFERENCES `asset_management`.`asset` (`id`),
  CONSTRAINT `fk_assets_users1`
    FOREIGN KEY (`primary_user_id`)
    REFERENCES `asset_management`.`user` (`id`),
  CONSTRAINT `fk_assets_users3`
    FOREIGN KEY (`creator_user_id`)
    REFERENCES `asset_management`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`activity_log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`activity_log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `asset_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `activity_type` VARCHAR(45) NOT NULL,
  `comment` VARCHAR(500) CHARACTER SET 'ascii' NULL DEFAULT NULL,
  `timestamp` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_activity_log_asset1_idx` (`asset_id` ASC) VISIBLE,
  INDEX `fk_activity_log_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_activity_log_asset1`
    FOREIGN KEY (`asset_id`)
    REFERENCES `asset_management`.`asset` (`id`),
  CONSTRAINT `fk_activity_log_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `asset_management`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`asset_customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`asset_customer` (
  `id` INT UNSIGNED NOT NULL,
  `asset_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_asset_vendor_assets1_idx` (`asset_id` ASC) VISIBLE,
  INDEX `fk_asset_customer_customers1_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `fk_asset_customer_customers1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `asset_management`.`customer` (`id`),
  CONSTRAINT `fk_asset_vendor_assets10`
    FOREIGN KEY (`asset_id`)
    REFERENCES `asset_management`.`asset` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`asset_in_location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`asset_in_location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `asset_id` INT NOT NULL,
  `location_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_assets_in_locations_assets1_idx` (`asset_id` ASC) VISIBLE,
  INDEX `fk_assets_in_locations_locations1_idx` (`location_id` ASC) VISIBLE,
  CONSTRAINT `fk_assets_in_locations_assets1`
    FOREIGN KEY (`asset_id`)
    REFERENCES `asset_management`.`asset` (`id`),
  CONSTRAINT `fk_assets_in_locations_locations1`
    FOREIGN KEY (`location_id`)
    REFERENCES `asset_management`.`location` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`part`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`part` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`asset_part`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`asset_part` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `asset_id` INT NOT NULL,
  `part_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_parts_in_assets_assets1_idx` (`asset_id` ASC) VISIBLE,
  INDEX `fk_parts_in_assets_parts1_idx` (`part_id` ASC) VISIBLE,
  CONSTRAINT `fk_parts_in_assets_assets1`
    FOREIGN KEY (`asset_id`)
    REFERENCES `asset_management`.`asset` (`id`),
  CONSTRAINT `fk_parts_in_assets_parts1`
    FOREIGN KEY (`part_id`)
    REFERENCES `asset_management`.`part` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`asset_secondary_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`asset_secondary_user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `asset_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_asset_secondary_user_asset1_idx` (`asset_id` ASC) VISIBLE,
  INDEX `fk_asset_secondary_user_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_asset_secondary_user_asset1`
    FOREIGN KEY (`asset_id`)
    REFERENCES `asset_management`.`asset` (`id`),
  CONSTRAINT `fk_asset_secondary_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `asset_management`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`team` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`asset_team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`asset_team` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `asset_id` INT NOT NULL,
  `team_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_asset_team_asset1_idx` (`asset_id` ASC) VISIBLE,
  INDEX `fk_asset_team_team1_idx` (`team_id` ASC) VISIBLE,
  CONSTRAINT `fk_asset_team_asset1`
    FOREIGN KEY (`asset_id`)
    REFERENCES `asset_management`.`asset` (`id`),
  CONSTRAINT `fk_asset_team_team1`
    FOREIGN KEY (`team_id`)
    REFERENCES `asset_management`.`team` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`asset_usage_log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`asset_usage_log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `is_checked_in` TINYINT NOT NULL DEFAULT '0',
  `comment` VARCHAR(255) NULL DEFAULT NULL,
  `activity_log_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_asset_usage_log_activity_log1_idx` (`activity_log_id` ASC) VISIBLE,
  CONSTRAINT `fk_asset_usage_log_activity_log1`
    FOREIGN KEY (`activity_log_id`)
    REFERENCES `asset_management`.`activity_log` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`vendor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`vendor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_company_id` INT NOT NULL,
  `company_name` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  `website` VARCHAR(255) NULL DEFAULT NULL,
  `contact_name` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `vendor_type` VARCHAR(255) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `rate` DECIMAL(13,2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idtable1_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `company_name_UNIQUE` (`company_name` ASC) VISIBLE,
  INDEX `fk_vendor_company1_idx` (`user_company_id` ASC) VISIBLE,
  CONSTRAINT `fk_vendor_company1`
    FOREIGN KEY (`user_company_id`)
    REFERENCES `asset_management`.`company` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`asset_vendor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`asset_vendor` (
  `id` INT UNSIGNED NOT NULL,
  `asset_id` INT NOT NULL,
  `vendor_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_asset_vendor_assets1_idx` (`asset_id` ASC) VISIBLE,
  INDEX `fk_asset_vendor_vendors1_idx` (`vendor_id` ASC) VISIBLE,
  CONSTRAINT `fk_asset_vendor_assets1`
    FOREIGN KEY (`asset_id`)
    REFERENCES `asset_management`.`asset` (`id`),
  CONSTRAINT `fk_asset_vendor_vendors1`
    FOREIGN KEY (`vendor_id`)
    REFERENCES `asset_management`.`vendor` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`custom_field`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`custom_field` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `value` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`operational_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`operational_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `company_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_asset_reliability_status_company1_idx` (`company_id` ASC) VISIBLE,
  CONSTRAINT `fk_asset_reliability_status_company1`
    FOREIGN KEY (`company_id`)
    REFERENCES `asset_management`.`company` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`reliability_log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`reliability_log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `operatioanl_status_id` INT NOT NULL DEFAULT '1',
  `activity_log_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reliability_asset_reliability_statuses1_idx` (`operatioanl_status_id` ASC) VISIBLE,
  INDEX `fk_reliability_log_activity_log1_idx` (`activity_log_id` ASC) VISIBLE,
  CONSTRAINT `fk_reliability_asset_reliability_statuses1`
    FOREIGN KEY (`operatioanl_status_id`)
    REFERENCES `asset_management`.`operational_status` (`id`),
  CONSTRAINT `fk_reliability_log_activity_log1`
    FOREIGN KEY (`activity_log_id`)
    REFERENCES `asset_management`.`activity_log` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`vendor_custom_field`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`vendor_custom_field` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `vendor_id` INT NOT NULL,
  `custom_field_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_vendor_custom_fields_vendors_idx` (`vendor_id` ASC) VISIBLE,
  INDEX `fk_vendor_custom_fields_custom_fields1_idx` (`custom_field_id` ASC) VISIBLE,
  CONSTRAINT `fk_vendor_custom_fields_custom_fields1`
    FOREIGN KEY (`custom_field_id`)
    REFERENCES `asset_management`.`custom_field` (`id`),
  CONSTRAINT `fk_vendor_custom_fields_vendors`
    FOREIGN KEY (`vendor_id`)
    REFERENCES `asset_management`.`vendor` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`zxcszfdczsdfcxz`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`zxcszfdczsdfcxz` (
  `idasdasd` INT NOT NULL,
  `asd` VARCHAR(45) NULL DEFAULT NULL,
  `a` VARCHAR(45) NULL DEFAULT NULL,
  `asdcol` VARCHAR(45) NULL DEFAULT NULL,
  `asdcol1` VARCHAR(45) NULL DEFAULT NULL,
  `asdcol2` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idasdasd`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
