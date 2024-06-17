-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema taw12
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema taw12
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `taw12` DEFAULT CHARACTER SET utf8mb4 ;
USE `taw12` ;

-- -----------------------------------------------------
-- Table `taw12`.`Cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taw12`.`Cliente` ;

CREATE TABLE IF NOT EXISTS `taw12`.`Cliente` (
  `idcliente` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `contrasenya` VARCHAR(45) NOT NULL,
  `imagenperfil` LONGBLOB NULL DEFAULT NULL,
  `peso` DOUBLE NULL DEFAULT NULL,
  `altura` DOUBLE NULL DEFAULT NULL,
  `alergias` VARCHAR(150) NULL DEFAULT NULL,
  PRIMARY KEY (`idcliente`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- -----------------------------------------------------
-- Table `taw12`.`Dieta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taw12`.`Dieta` ;

CREATE TABLE IF NOT EXISTS `taw12`.`Dieta` (
  `iddieta` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `idcliente` INT NOT NULL,
  PRIMARY KEY (`iddieta`),
  INDEX `fk_dieta_Cliente1_idx` (`idcliente` ASC) VISIBLE,
  CONSTRAINT `fk_dieta_Cliente1`
    FOREIGN KEY (`idcliente`)
    REFERENCES `taw12`.`Cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- -----------------------------------------------------
-- Table `taw12`.`Ejercicio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taw12`.`Ejercicio` ;

CREATE TABLE IF NOT EXISTS `taw12`.`Ejercicio` (
  `idejercicio` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `tipo` ENUM('FUERZA', 'RESISTENCIA', 'CAPACIDAD AEROBICA', 'VELOCIDAD', 'POTENCIA', 'ESTABILIDAD', 'MOVILIDAD') NOT NULL,
  `video` LONGBLOB NULL DEFAULT NULL,
  `descripcion` VARCHAR(150) NULL DEFAULT NULL,
  PRIMARY KEY (`idejercicio`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `taw12`.`Plato`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taw12`.`Plato` ;

CREATE TABLE IF NOT EXISTS `taw12`.`Plato` (
  `idplato` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `alergenos` VARCHAR(150) NULL DEFAULT NULL,
  `video` LONGBLOB NULL DEFAULT NULL,
  `descripcion` VARCHAR(150) NULL DEFAULT NULL,
  PRIMARY KEY (`idplato`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `taw12`.`Rutina`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taw12`.`Rutina` ;

CREATE TABLE IF NOT EXISTS `taw12`.`Rutina` (
  `idrutina` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `idcliente` INT NOT NULL,
  `idtrabajador` INT NOT NULL, -- Nueva columna para la clave foránea
  PRIMARY KEY (`idrutina`),
  INDEX `fk_rutina_cliente1_idx` (`idcliente` ASC) VISIBLE,
  INDEX `fk_rutina_trabajador1_idx` (`idtrabajador` ASC) VISIBLE, -- Índice para la nueva clave foránea
  CONSTRAINT `fk_rutina_cliente1`
    FOREIGN KEY (`idcliente`)
    REFERENCES `taw12`.`Cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rutina_trabajador1`
    FOREIGN KEY (`idtrabajador`)
    REFERENCES `taw12`.`Trabajador` (`idtrabajador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `taw12`.`PlatoDieta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taw12`.`PlatoDieta` ;

CREATE TABLE IF NOT EXISTS `taw12`.`PlatoDieta` (
  `idplatodieta` INT NOT NULL,
  `idplato` INT NOT NULL,
  `iddieta` INT NOT NULL,
  `calorias` INT NULL,
  `cantidad` INT NULL,
  `orden` INT NULL,
  `diassemana` ENUM('Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo') NULL,
  `franjahoraria` ENUM('Desayuno', 'Mediodía', 'Almuerzo', 'Merienda', 'Cena') NULL,
  PRIMARY KEY (`idplatodieta`, `idplato`, `iddieta`),
  INDEX `fk_plato_has_dieta_dieta1_idx` (`iddieta` ASC) VISIBLE,
  INDEX `fk_plato_has_dieta_plato1_idx` (`idplato` ASC) VISIBLE,
  CONSTRAINT `fk_plato_has_dieta_plato1`
    FOREIGN KEY (`idplato`)
    REFERENCES `taw12`.`Plato` (`idplato`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plato_has_dieta_dieta1`
    FOREIGN KEY (`iddieta`)
    REFERENCES `taw12`.`Dieta` (`iddieta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `taw12`.`SeguimientoDieta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taw12`.`SeguimientoDieta` ;

CREATE TABLE IF NOT EXISTS `taw12`.`SeguimientoDieta` (
  `idseguimientodieta` INT NOT NULL AUTO_INCREMENT, 
  `iddieta` INT NOT NULL, 
  `idcliente` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `comido` TINYINT NULL DEFAULT NULL,
  `cantidad` INT NULL DEFAULT NULL,
  `observaciones` VARCHAR(250) NULL DEFAULT NULL,
   `cantidadobjetivo` INT NULL DEFAULT NULL,  -- Nueva columna para cantidad objetivo
  `nombreplato` VARCHAR(100) NOT NULL,  -- Nueva columna para nombre del plato
  PRIMARY KEY (`idseguimientodieta`),
  INDEX `fk_SeguimientoDieta_Dieta1_idx` (`iddieta`),  -- Índice para la nueva clave foránea
  INDEX `fk_SeguimientoDieta_Cliente1_idx` (`idcliente`),  -- Índice para la nueva clave foránea
  CONSTRAINT `fk_SeguimientoDieta_Dieta1`
    FOREIGN KEY (`iddieta`)
    REFERENCES `taw12`.`Dieta` (`iddieta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SeguimientoCliente_Dieta1`
    FOREIGN KEY (`idcliente`)
    REFERENCES `taw12`.`Cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `taw12`.`EjercicioRutina`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taw12`.`EjercicioRutina` ;

CREATE TABLE IF NOT EXISTS `taw12`.`EjercicioRutina` (
  `idejerciciorutina` INT NOT NULL AUTO_INCREMENT,
  `idrutina` INT NOT NULL,
  `idejercicio` INT NOT NULL,
  `peso` FLOAT NULL,
  `repeticiones` INT NULL,
  `series` INT NULL,
  `orden` INT NULL,
  `diassemana` ENUM('Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo') NULL,
  PRIMARY KEY (`idejerciciorutina`),
  INDEX `fk_EjercicioRutina_Rutina1_idx` (`idrutina`),
  INDEX `fk_EjercicioRutina_ejercicio1_idx` (`idejercicio`),
  CONSTRAINT `fk_EjercicioRutina_Rutina1`
    FOREIGN KEY (`idrutina`)
    REFERENCES `taw12`.`Rutina` (`idrutina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_EjercicioRutina_Ejercicio1`
    FOREIGN KEY (`idejercicio`)
    REFERENCES `taw12`.`Ejercicio` (`idejercicio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;



-- -----------------------------------------------------
-- Table `taw12`.`SeguimientoObjetivos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taw12`.`SeguimientoObjetivos` ;

CREATE TABLE IF NOT EXISTS `taw12`.`SeguimientoObjetivos` (
  `idseguimiento` INT NOT NULL AUTO_INCREMENT,
  `idrutina` INT NOT NULL,
  `idcliente` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `realizado` TINYINT NOT NULL,
  `pesorealizado` FLOAT NULL DEFAULT NULL,
  `repeticionesrealizadas` INT NULL DEFAULT NULL,
  `seriesrealizadas` INT NULL DEFAULT NULL,
  `observaciones` VARCHAR(200) NULL,
  `pesoobjetivo` FLOAT NULL DEFAULT NULL, 
  `seriesobjetivo` INT NULL DEFAULT NULL,  
  `repeticionesobjetivo` INT NULL DEFAULT NULL,
  `nombreejercicio` VARCHAR(100) NOT NULL,  
  PRIMARY KEY (`idseguimiento`),
  INDEX `fk_SeguimientoObjetivos_Rutina1_idx` (`idrutina`),  
  INDEX `fk_SeguimientoObjetivos_Cliente1_idx` (`idcliente`),  
  CONSTRAINT `fk_SeguimientoObjetivos_Rutina1`
    FOREIGN KEY (`idrutina`)
    REFERENCES `taw12`.`Rutina` (`idrutina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SeguimientoObjetivos_Cliente1`
    FOREIGN KEY (`idcliente`)
    REFERENCES `taw12`.`Cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;



-- -----------------------------------------------------
-- Table `taw12`.`Trabajador`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taw12`.`Trabajador` ;

CREATE TABLE IF NOT EXISTS `taw12`.`Trabajador` (
  `idtrabajador` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `contrasenya` VARCHAR(45) NOT NULL,
  `tipo` ENUM('ENTRENADOR FUERZA', 'ENTRENADOR CROSSTRAINNING', 'DIETISTA') NOT NULL,
  `imagenperfil` LONGBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`idtrabajador`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;






-- -----------------------------------------------------
-- Table `taw12`.`cliente_trabajador`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taw12`.`cliente_trabajador` ;

CREATE TABLE IF NOT EXISTS `taw12`.`cliente_trabajador` (
  `idcliente` INT NOT NULL,
  `idtrabajador` INT NOT NULL,
  PRIMARY KEY (`idcliente`, `idtrabajador`),
  INDEX `fk_cliente_has_trabajador_trabajador1_idx` (`idtrabajador` ASC) VISIBLE,
  INDEX `fk_cliente_has_trabajador_cliente1_idx` (`idcliente` ASC) VISIBLE,
  CONSTRAINT `fk_cliente_has_trabajador_cliente1`
    FOREIGN KEY (`idcliente`)
    REFERENCES `taw12`.`Cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_has_trabajador_trabajador1`
    FOREIGN KEY (`idtrabajador`)
    REFERENCES `taw12`.`Trabajador` (`idtrabajador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- -----------------------------------------------------
-- Table `taw12`.`administrador`
-- -----------------------------------------------------
create table if not exists administrador
(
    idadministrador int auto_increment
        primary key,
    email           varchar(45) not null,
    contrasenya     varchar(45) not null
);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
