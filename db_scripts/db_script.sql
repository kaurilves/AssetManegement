

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
-- Table `asset_management`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`activity_log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`activity_log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_activity_log_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_activity_log_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `asset_management`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`customers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`teams`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`teams` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`assets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`assets` (
  `id` INT NOT NULL AUTO_INCREMENT,
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
  `check_in_procedure` VARCHAR(255) NULL DEFAULT NULL,
  `check_out_procedure` VARCHAR(255) NULL DEFAULT NULL,
  `teams_id` INT NULL DEFAULT NULL,
  `primary_user_id` INT NULL DEFAULT NULL,
  `secondary_user_id` INT NULL DEFAULT NULL,
  `parent_asset_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `barcode_UNIQUE` (`barcode` ASC) VISIBLE,
  INDEX `fk_assets_teams1_idx` (`teams_id` ASC) VISIBLE,
  INDEX `fk_assets_users1_idx` (`primary_user_id` ASC) VISIBLE,
  INDEX `fk_assets_users2_idx` (`secondary_user_id` ASC) VISIBLE,
  INDEX `fk_assets_users3_idx` (`creator_user_id` ASC) VISIBLE,
  INDEX `fk_assets_assets1_idx` (`parent_asset_id` ASC) VISIBLE,
  CONSTRAINT `fk_assets_teams1`
    FOREIGN KEY (`teams_id`)
    REFERENCES `asset_management`.`teams` (`id`),
  CONSTRAINT `fk_assets_users1`
    FOREIGN KEY (`primary_user_id`)
    REFERENCES `asset_management`.`users` (`id`),
  CONSTRAINT `fk_assets_users2`
    FOREIGN KEY (`secondary_user_id`)
    REFERENCES `asset_management`.`users` (`id`),
  CONSTRAINT `fk_assets_users3`
    FOREIGN KEY (`creator_user_id`)
    REFERENCES `asset_management`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_assets_assets1`
    FOREIGN KEY (`parent_asset_id`)
    REFERENCES `asset_management`.`assets` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`asset_customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`asset_customer` (
  `id` INT UNSIGNED NOT NULL,
  `assets_id` INT NOT NULL,
  `customers_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_asset_vendor_assets1_idx` (`assets_id` ASC) VISIBLE,
  INDEX `fk_asset_customer_customers1_idx` (`customers_id` ASC) VISIBLE,
  CONSTRAINT `fk_asset_customer_customers1`
    FOREIGN KEY (`customers_id`)
    REFERENCES `asset_management`.`customers` (`id`),
  CONSTRAINT `fk_asset_vendor_assets10`
    FOREIGN KEY (`assets_id`)
    REFERENCES `asset_management`.`assets` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`images` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `base64` BLOB NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`asset_image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`asset_image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `assets_id` INT NOT NULL,
  `images_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_asset_image_assets1_idx` (`assets_id` ASC) VISIBLE,
  INDEX `fk_asset_image_images1_idx` (`images_id` ASC) VISIBLE,
  CONSTRAINT `fk_asset_image_assets1`
    FOREIGN KEY (`assets_id`)
    REFERENCES `asset_management`.`assets` (`id`),
  CONSTRAINT `fk_asset_image_images1`
    FOREIGN KEY (`images_id`)
    REFERENCES `asset_management`.`images` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`asset_reliability_statuses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`asset_reliability_statuses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`vendors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`vendors` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `company_name` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  `website` VARCHAR(255) NULL DEFAULT NULL,
  `contact_name` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `vendor_type` VARCHAR(255) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `rate` DECIMAL(13,2) NULL DEFAULT NULL,
  `user_company_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idtable1_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `company_name_UNIQUE` (`company_name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`asset_vendor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`asset_vendor` (
  `id` INT UNSIGNED NOT NULL,
  `assets_id` INT NOT NULL,
  `vendors_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_asset_vendor_assets1_idx` (`assets_id` ASC) VISIBLE,
  INDEX `fk_asset_vendor_vendors1_idx` (`vendors_id` ASC) VISIBLE,
  CONSTRAINT `fk_asset_vendor_assets1`
    FOREIGN KEY (`assets_id`)
    REFERENCES `asset_management`.`assets` (`id`),
  CONSTRAINT `fk_asset_vendor_vendors1`
    FOREIGN KEY (`vendors_id`)
    REFERENCES `asset_management`.`vendors` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`locations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`locations` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`assets_in_locations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`assets_in_locations` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `assets_id` INT NOT NULL,
  `locations_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_assets_in_locations_assets1_idx` (`assets_id` ASC) VISIBLE,
  INDEX `fk_assets_in_locations_locations1_idx` (`locations_id` ASC) VISIBLE,
  CONSTRAINT `fk_assets_in_locations_assets1`
    FOREIGN KEY (`assets_id`)
    REFERENCES `asset_management`.`assets` (`id`),
  CONSTRAINT `fk_assets_in_locations_locations1`
    FOREIGN KEY (`locations_id`)
    REFERENCES `asset_management`.`locations` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`checkin_checkout`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`checkin_checkout` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `assets_id` INT NOT NULL,
  `timestamp` DATETIME NULL DEFAULT NULL,
  `status` VARCHAR(10) NULL DEFAULT NULL,
  `comment` VARCHAR(255) NULL DEFAULT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_checkin_checkout_assets1_idx` (`assets_id` ASC) VISIBLE,
  INDEX `fk_checkin_checkout_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_checkin_checkout_assets1`
    FOREIGN KEY (`assets_id`)
    REFERENCES `asset_management`.`assets` (`id`),
  CONSTRAINT `fk_checkin_checkout_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `asset_management`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`custom_fields`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`custom_fields` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `value` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`parts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`parts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`parts_in_assets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`parts_in_assets` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `assets_id` INT NOT NULL,
  `parts_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_parts_in_assets_assets1_idx` (`assets_id` ASC) VISIBLE,
  INDEX `fk_parts_in_assets_parts1_idx` (`parts_id` ASC) VISIBLE,
  CONSTRAINT `fk_parts_in_assets_assets1`
    FOREIGN KEY (`assets_id`)
    REFERENCES `asset_management`.`assets` (`id`),
  CONSTRAINT `fk_parts_in_assets_parts1`
    FOREIGN KEY (`parts_id`)
    REFERENCES `asset_management`.`parts` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `asset_management`.`reliability`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_management`.`reliability` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `assets_id` INT NOT NULL,
  `timestamp` DATETIME NULL DEFAULT NULL,
  `asset_reliability_statuses_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reliability_log_assets1_idx` (`assets_id` ASC) VISIBLE,
  INDEX `fk_reliability_asset_reliability_statuses1_idx` (`asset_reliability_statuses_id` ASC) VISIBLE,
  CONSTRAINT `fk_reliability_asset_reliability_statuses1`
    FOREIGN KEY (`asset_reliability_statuses_id`)
    REFERENCES `asset_management`.`asset_reliability_statuses` (`id`),
  CONSTRAINT `fk_reliability_log_assets1`
    FOREIGN KEY (`assets_id`)
    REFERENCES `asset_management`.`assets` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


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
  CONSTRAINT `fk_vendor_custom_fields_custom_fields1`
    FOREIGN KEY (`custom_fields_id`)
    REFERENCES `asset_management`.`custom_fields` (`id`),
  CONSTRAINT `fk_vendor_custom_fields_vendors`
    FOREIGN KEY (`vendors_id`)
    REFERENCES `asset_management`.`vendors` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
