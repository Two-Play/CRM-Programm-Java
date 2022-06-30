-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema crm
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema crm
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `crm` DEFAULT CHARACTER SET utf8 ;
USE `crm` ;

-- -----------------------------------------------------
-- Table `crm`.`ort`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm`.`ort` (
  `ortNr` INT NOT NULL,
  `ortName` VARCHAR(45) NULL,
  `plz` VARCHAR(6) NULL,
  PRIMARY KEY (`ortNr`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crm`.`kunden`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm`.`kunden` (
  `kundenNr` INT NOT NULL,
  `vorname` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `tel` VARCHAR(45) NULL,
  `firma` VARCHAR(45) NULL,
  `ortNr` INT NOT NULL,
  `strasse` VARCHAR(45) NULL,
  `bemerkungen` VARCHAR(45) NULL,
  `interessen` VARCHAR(45) NULL,
  `geburtsdatum` DATE NULL,
  `geschlecht` VARCHAR(45) NULL,
  PRIMARY KEY (`kundenNr`),
  INDEX `fk_kunden_idx` (`ortNr` ASC) VISIBLE,
  CONSTRAINT `fk_kunden`
    FOREIGN KEY (`ortNr`)
    REFERENCES `crm`.`ort` (`ortNr`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crm`.`auftraege`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm`.`auftraege` (
  `auftraegeNr` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `start` DATE NULL,
  `ende` DATE NULL,
  `notiz` VARCHAR(45) NULL,
  `status` VARCHAR(45) NULL,
  `kundenNr` INT NOT NULL,
  PRIMARY KEY (`auftraegeNr`),
  INDEX `fk_auftraege1_idx` (`kundenNr` ASC) VISIBLE,
  CONSTRAINT `fk_auftraege1`
    FOREIGN KEY (`kundenNr`)
    REFERENCES `crm`.`kunden` (`kundenNr`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crm`.`termine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crm`.`termine` (
  `termineNr` INT NOT NULL,
  `datum` DATE NULL,
  `uhrzeit` TIME NULL,
  `bemerkung` INT NULL,
  `kundenNr` INT NOT NULL,
  PRIMARY KEY (`termineNr`),
  INDEX `fk_termine1_idx` (`kundenNr` ASC) VISIBLE,
  CONSTRAINT `fk_termine1`
    FOREIGN KEY (`kundenNr`)
    REFERENCES `crm`.`kunden` (`kundenNr`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
