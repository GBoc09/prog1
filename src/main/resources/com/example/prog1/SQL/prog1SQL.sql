-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema prog
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `prog` ;

-- -----------------------------------------------------
-- Schema prog
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `prog` DEFAULT CHARACTER SET utf8 ;
USE `prog` ;

-- -----------------------------------------------------
-- Table `prog`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prog`.`User` (
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`email`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `prog`.`Scuba`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prog`.`Scuba` (
  `license` VARCHAR(9) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`license`),
  INDEX `scubaMail_idx` (`email` ASC) VISIBLE,
  CONSTRAINT `scubaMail`
    FOREIGN KEY (`email`)
    REFERENCES `prog`.`User` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `prog`.`Free`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prog`.`Free` (
  `license` VARCHAR(9) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`license`),
  INDEX `freeMail_idx` (`email` ASC) VISIBLE,
  CONSTRAINT `freeMail`
    FOREIGN KEY (`email`)
    REFERENCES `prog`.`User` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `prog`.`Manager`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prog`.`Manager` (
  `license` VARCHAR(9) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`license`),
  INDEX `manMail_idx` (`email` ASC) VISIBLE,
  CONSTRAINT `manMail`
    FOREIGN KEY (`email`)
    REFERENCES `prog`.`User` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `prog`.`Diving`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prog`.`Diving` (
  `idDiving` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `location` VARCHAR(45) NOT NULL,
  `telephone` VARCHAR(45) NOT NULL,
  `divingManager` VARCHAR(9) NOT NULL,
  PRIMARY KEY (`idDiving`, `name`),
  INDEX `manager_idx` (`divingManager` ASC) VISIBLE,
  CONSTRAINT `manager`
    FOREIGN KEY (`divingManager`)
    REFERENCES `prog`.`Manager` (`license`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `prog`.`Equipment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prog`.`Equipment` (
  `idEquipment` INT NOT NULL AUTO_INCREMENT,
  `equipType` VARCHAR(45) NOT NULL,
  `size` VARCHAR(4) NOT NULL,
  `availability` INT NOT NULL,
  `price` DOUBLE NOT NULL,
  `DivingName` VARCHAR(45) NOT NULL,
  `manager` VARCHAR(9) NOT NULL,
  PRIMARY KEY (`idEquipment`),
  INDEX `diving_idx` (`DivingName` ASC) VISIBLE,
  INDEX `idMan_idx` (`manager` ASC) VISIBLE,
  CONSTRAINT `diving`
    FOREIGN KEY (`DivingName`)
    REFERENCES `prog`.`Diving` (`idDiving`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idMan`
    FOREIGN KEY (`manager`)
    REFERENCES `prog`.`Manager` (`license`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `prog`.`Rental`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prog`.`Rental` (
  `idRental` INT NOT NULL AUTO_INCREMENT,
  `idEquip` INT NOT NULL,
  `equipType` VARCHAR(45) NOT NULL,
  `scuba` VARCHAR(9) NOT NULL,
  `diving` VARCHAR(45) NOT NULL,
  `total` DOUBLE NOT NULL,
  PRIMARY KEY (`idRental`),
  INDEX `equip_idx` (`idEquip` ASC) VISIBLE,
  INDEX `scuba_idx` (`scuba` ASC) VISIBLE,
  INDEX `diving_idx` (`diving` ASC) VISIBLE,
  CONSTRAINT `equip`
    FOREIGN KEY (`idEquip`)
    REFERENCES `prog`.`Equipment` (`idEquipment`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `scuba`
    FOREIGN KEY (`scuba`)
    REFERENCES `prog`.`Scuba` (`license`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `divingRent`
    FOREIGN KEY (`diving`)
    REFERENCES `prog`.`Diving` (`idDiving`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `prog`.`Cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prog`.`Cart` (
  `idCart` INT NOT NULL AUTO_INCREMENT,
  `equipType` VARCHAR(45) NOT NULL,
  `equipSize` VARCHAR(4) NOT NULL,
  `equipPrice` DOUBLE NOT NULL,
  `equipQuantity` INT NOT NULL,
  `scuba` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idCart`),
  INDEX `scubaCart_idx` (`scuba` ASC) VISIBLE,
  CONSTRAINT `scubaCart`
    FOREIGN KEY (`scuba`)
    REFERENCES `prog`.`Scuba` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `prog`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `prog`;
INSERT INTO `prog`.`User` (`email`, `password`, `type`) VALUES ('giuboc@gmail.com', 'root1', 'scuba');
INSERT INTO `prog`.`User` (`email`, `password`, `type`) VALUES ('harrypotter@gmail.com', 'root2', 'manager');

COMMIT;


-- -----------------------------------------------------
-- Data for table `prog`.`Scuba`
-- -----------------------------------------------------
START TRANSACTION;
USE `prog`;
INSERT INTO `prog`.`Scuba` (`license`, `name`, `lastname`, `email`) VALUES ('123456789', 'giulia', 'boc', 'giuboc@gmail.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `prog`.`Manager`
-- -----------------------------------------------------
START TRANSACTION;
USE `prog`;
INSERT INTO `prog`.`Manager` (`license`, `name`, `lastname`, `email`) VALUES ('369258147', 'harry', 'potter', 'harrypotter@gmail.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `prog`.`Diving`
-- -----------------------------------------------------
START TRANSACTION;
USE `prog`;
INSERT INTO `prog`.`Diving` (`idDiving`, `name`, `location`, `telephone`, `divingManager`) VALUES (111, 'HappyBubbles', 'Giannutri', '347854662', '369258147');

COMMIT;


-- -----------------------------------------------------
-- Data for table `prog`.`Equipment`
-- -----------------------------------------------------
START TRANSACTION;
USE `prog`;
INSERT INTO `prog`.`Equipment` (`idEquipment`, `equipType`, `size`, `availability`, `price`, `DivingName`, `manager`) VALUES (1, 'finns', 'XS', 3, 15.50, '111', '369258147');
INSERT INTO `prog`.`Equipment` (`idEquipment`, `equipType`, `size`, `availability`, `price`, `DivingName`, `manager`) VALUES (2, 'finns', 'S', 5, 15.50, '111', '369258147');
INSERT INTO `prog`.`Equipment` (`idEquipment`, `equipType`, `size`, `availability`, `price`, `DivingName`, `manager`) VALUES (3, 'finns', 'M', 7, 15.50, '111', '369258147');
INSERT INTO `prog`.`Equipment` (`idEquipment`, `equipType`, `size`, `availability`, `price`, `DivingName`, `manager`) VALUES (4, 'finns', 'L', 10, 15.50, '111', '369258147');
INSERT INTO `prog`.`Equipment` (`idEquipment`, `equipType`, `size`, `availability`, `price`, `DivingName`, `manager`) VALUES (5, 'finns', 'XL', 2, 15.50, '111', '369258147');
INSERT INTO `prog`.`Equipment` (`idEquipment`, `equipType`, `size`, `availability`, `price`, `DivingName`, `manager`) VALUES (6, 'jacket', 'XS', 2, 45.0, '111', '369258147');
INSERT INTO `prog`.`Equipment` (`idEquipment`, `equipType`, `size`, `availability`, `price`, `DivingName`, `manager`) VALUES (7, 'jacket', 'S', 4, 45, '111', '369258147');
INSERT INTO `prog`.`Equipment` (`idEquipment`, `equipType`, `size`, `availability`, `price`, `DivingName`, `manager`) VALUES (8, 'jacket', 'M', 3, 45, '111', '369258147');
INSERT INTO `prog`.`Equipment` (`idEquipment`, `equipType`, `size`, `availability`, `price`, `DivingName`, `manager`) VALUES (9, 'jacket', 'L', 5, 45, '111', '369258147');
INSERT INTO `prog`.`Equipment` (`idEquipment`, `equipType`, `size`, `availability`, `price`, `DivingName`, `manager`) VALUES (10, 'jacket', 'XL', 2, 45, '111', '369258147');
INSERT INTO `prog`.`Equipment` (`idEquipment`, `equipType`, `size`, `availability`, `price`, `DivingName`, `manager`) VALUES (11, 'mask', 'none', 12, 25, '111', '369258147');
INSERT INTO `prog`.`Equipment` (`idEquipment`, `equipType`, `size`, `availability`, `price`, `DivingName`, `manager`) VALUES (12, 'regulator', 'none', 5, 30, '111', '369258147');
INSERT INTO `prog`.`Equipment` (`idEquipment`, `equipType`, `size`, `availability`, `price`, `DivingName`, `manager`) VALUES (13, 'suit', 'XS', 2, 25, '111', '369258147');

COMMIT;

