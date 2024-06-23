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
  `peso` VARCHAR(40) NULL,
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
  `pesorealizado` VARCHAR(40) NULL DEFAULT NULL,
  `repeticionesrealizadas` INT NULL DEFAULT NULL,
  `seriesrealizadas` INT NULL DEFAULT NULL,
  `observaciones` VARCHAR(200) NULL,
  `pesoobjetivo` VARCHAR(40) NULL DEFAULT NULL, 
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
  `tipo` ENUM('ENTRENADOR FUERZA', 'ENTRENADOR CROSSTRAINING', 'DIETISTA') NOT NULL,
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


SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE plato;
SET FOREIGN_KEY_CHECKS = 1;


-- Platos
INSERT INTO `taw12`.`Plato` (`nombre`, `descripcion`, `alergenos`) VALUES
('Paella Valenciana', 'Tradicional paella valenciana con arroz, pollo, conejo y verduras frescas de la huerta.', 'Crustáceos'),
('Tortilla Española', 'Tortilla de patatas jugosa, hecha con huevos frescos y patatas seleccionadas.', 'Huevo'),
('Gazpacho Andaluz', 'Sopa fría de tomate, pimiento, pepino y ajo, refrescante y nutritiva.', NULL),
('Pulpo a la Gallega', 'Pulpo tierno cocido, servido con pimentón, aceite de oliva y sal gruesa.', 'Moluscos'),
('Croquetas de Jamón', 'Deliciosas croquetas caseras de jamón serrano, crujientes por fuera y cremosas por dentro.', 'Gluten, Huevo'),
('Salmorejo Cordobés', 'Crema espesa de tomate y pan, acompañada de jamón serrano y huevo duro.', 'Gluten, Huevo'),
('Fabada Asturiana', 'Guiso tradicional de judías blancas con chorizo, morcilla y panceta.', 'Sulfitos'),
('Pimientos de Padrón', 'Pimientos de Padrón fritos, unos pican y otros no.', NULL),
('Cochinillo Asado', 'Cochinillo asado a baja temperatura, con piel crujiente y carne jugosa.', NULL),
('Churros con Chocolate', 'Churros crujientes servidos con una taza de chocolate caliente para mojar.', 'Gluten'),
('Calamares a la Romana', 'Calamares rebozados y fritos, servidos con limón y alioli.', 'Moluscos, Gluten'),
('Patatas Bravas', 'Patatas fritas acompañadas de una salsa brava picante y alioli.', NULL),
('Jamón Ibérico', 'Jamón ibérico de bellota, cortado en finas lonchas, servido con pan con tomate.', 'Gluten'),
('Huevos Rotos', 'Huevos fritos con yema líquida, servidos sobre una cama de patatas fritas y jamón.', 'Huevo'),
('Albóndigas en Salsa', 'Albóndigas de carne caseras, cocinadas en una rica salsa de tomate y vino.', 'Sulfitos'),
('Ensalada Mixta', 'Ensalada fresca con lechuga, tomate, cebolla, atún y huevo duro.', 'Huevo, Pescado'),
('Pulpo a la Brasa', 'Pulpo a la brasa, acompañado de patatas y pimentón.', 'Moluscos'),
('Arroz Negro', 'Arroz negro cocido con tinta de calamar, servido con alioli.', 'Pescado, Moluscos'),
('Cordero Asado', 'Cordero asado lentamente, acompañado de patatas y hierbas aromáticas.', NULL),
('Tarta de Santiago', 'Tarta de almendra tradicional gallega, sin gluten, espolvoreada con azúcar glas.', 'Frutos de cáscara'),
('Bacalao a la Vizcaína', 'Bacalao en salsa vizcaína, hecha con pimientos choriceros y cebolla.', 'Pescado'),
('Fideuá', 'Fideuá de mariscos, un plato similar a la paella pero hecho con fideos.', 'Crustáceos, Moluscos'),
('Cocido Madrileño', 'Guiso de garbanzos con chorizo, morcilla, carne de cerdo y verduras.', 'Sulfitos'),
('Gambas al Ajillo', 'Gambas salteadas con ajo y guindilla, servidas con pan.', 'Crustáceos, Gluten'),
('Empanada Gallega', 'Empanada rellena de atún, cebolla y pimiento, cocida al horno.', 'Gluten, Pescado'),
('Tortillitas de Camarones', 'Tortillitas crujientes hechas con camarones y harina de garbanzo.', 'Crustáceos'),
('Pisto Manchego', 'Guiso de verduras de temporada, como calabacín, berenjena, tomate y pimiento.', NULL),
('Callos a la Madrileña', 'Callos de ternera guisados con chorizo y morcilla en una salsa espesa.', 'Sulfitos'),
('Arroz con Leche', 'Postre cremoso de arroz cocido en leche, con canela y limón.', 'Leche'),
('Caracoles a la Llauna', 'Caracoles cocidos a la plancha con ajo y perejil.', 'Moluscos'),
('Leche Frita', 'Postre típico de leche frita, rebozado y espolvoreado con azúcar y canela.', 'Leche, Gluten, Huevo'),
('Torrijas', 'Pan empapado en leche y huevo, frito y espolvoreado con azúcar y canela.', 'Gluten, Huevo, Leche'),
('Migas', 'Pan frito con chorizo, pimientos y uvas, típico de Castilla-La Mancha.', 'Gluten'),
('Ensaimada', 'Dulce espiral mallorquín, hecho con masa hojaldrada y espolvoreado con azúcar glas.', 'Gluten'),
('Flan de Huevo', 'Postre de flan de huevo, cubierto con caramelo.', 'Huevo, Leche'),
('Queso Manchego', 'Queso curado de oveja, originario de La Mancha, con sabor intenso.', 'Leche'),
('Pan con Tomate', 'Rebanadas de pan con tomate rallado, aceite de oliva y sal.', 'Gluten'),
('Boquerones en Vinagre', 'Boquerones marinados en vinagre, ajo y perejil.', 'Pescado'),
('Caldereta de Cordero', 'Guiso de cordero con patatas, pimientos y tomate, cocido lentamente.', NULL),
('Gazpachuelo', 'Sopa caliente de pescado con mayonesa, típica de Málaga.', 'Pescado, Huevo'),
('Canelones', 'Canelones rellenos de carne, cubiertos con bechamel y gratinados.', 'Gluten, Leche, Huevo'),
('Pastel de Cabracho', 'Pastel de pescado de cabracho, servido frío con mayonesa.', 'Pescado, Huevo'),
('Fritura Malagueña', 'Surtido de pescados y mariscos fritos, típico de Málaga.', 'Pescado, Crustáceos, Moluscos'),
('Menestra de Verduras', 'Guiso de verduras variadas, como alcachofas, espárragos y zanahorias.', NULL),
('Sepia a la Plancha', 'Sepia a la plancha con ajo y perejil, servida con alioli.', 'Moluscos'),
('Lentejas con Chorizo', 'Guiso de lentejas con chorizo y verduras.', 'Sulfitos'),
('Arroz al Horno', 'Arroz al horno con costillas, morcilla, garbanzos y tomate.', 'Sulfitos'),
('Calamares en su Tinta', 'Calamares cocidos en su tinta, con cebolla y vino blanco.', 'Moluscos'),
('Sopa de Ajo', 'Sopa caliente de ajo con pan, pimentón y huevo escalfado.', 'Gluten, Huevo');

        
        USE taw12;

INSERT INTO `taw12`.`Ejercicio` (`nombre`, `descripcion`, `tipo`) VALUES
('Sentadilla', 'Ejercicio básico de fuerza para las piernas y glúteos', 'FUERZA'),
('Press de banca', 'Ejercicio de fuerza enfocado en el desarrollo del pecho', 'FUERZA'),
('Carrera continua', 'Ejercicio cardiovascular para mejorar la resistencia', 'RESISTENCIA'),
('Natación', 'Ejercicio de resistencia en el agua que involucra todo el cuerpo', 'RESISTENCIA'),
('Ciclismo', 'Actividad aeróbica que mejora la capacidad cardiovascular', 'CAPACIDAD AEROBICA'),
('Salto de cuerda', 'Ejercicio aeróbico para mejorar coordinación y resistencia', 'CAPACIDAD AEROBICA'),
('Sprints', 'Carreras cortas a máxima velocidad para desarrollar rapidez', 'VELOCIDAD'),
('Correr 100m', 'Carrera de velocidad pura en distancia corta de 100 metros', 'VELOCIDAD'),
('Levantamiento de pesas', 'Ejercicio de alta intensidad para aumentar potencia muscular', 'POTENCIA'),
('Clean and Jerk', 'Levantamiento olímpico para desarrollar potencia explosiva', 'POTENCIA'),
('Planchas', 'Ejercicio de estabilidad que fortalece el núcleo abdominal', 'ESTABILIDAD'),
('Superman', 'Ejercicio que mejora la estabilidad y fuerza de la espalda baja', 'ESTABILIDAD'),
('Estiramientos dinámicos', 'Ejercicios para mejorar la movilidad articular y flexibilidad', 'MOVILIDAD'),
('Yoga', 'Práctica de posturas para aumentar movilidad y flexibilidad', 'MOVILIDAD'),
('Remo', 'Ejercicio de fuerza y resistencia para la parte superior del cuerpo', 'FUERZA'),
('Dominadas', 'Ejercicio de fuerza enfocado en la espalda y brazos', 'FUERZA'),
('Flexiones', 'Ejercicio de fuerza para pecho y tríceps, sencillo y efectivo', 'FUERZA'),
('Abdominales', 'Ejercicio clásico para fortalecer los músculos abdominales', 'FUERZA'),
('Bicicleta estática', 'Ejercicio aeróbico que mejora la capacidad cardiovascular', 'CAPACIDAD AEROBICA'),
('Burpees', 'Ejercicio completo que desarrolla resistencia y potencia', 'POTENCIA'),
('Remo en máquina', 'Ejercicio de resistencia y fuerza para la parte superior del cuerpo', 'RESISTENCIA'),
('Trote suave', 'Ejercicio de resistencia cardiovascular a ritmo moderado', 'RESISTENCIA'),
('Saltos al cajón', 'Ejercicio de potencia y velocidad para piernas y glúteos', 'POTENCIA'),
('Skipping', 'Ejercicio que mejora velocidad y coordinación al trotar en el lugar', 'VELOCIDAD'),
('Zancadas', 'Ejercicio de fuerza enfocado en piernas y glúteos', 'FUERZA'),
('Peso muerto', 'Ejercicio de fuerza para la espalda, glúteos y piernas', 'FUERZA'),
('Circuito de alta intensidad', 'Entrenamiento que mejora resistencia y fuerza', 'RESISTENCIA'),
('Flexibilidad de piernas', 'Ejercicio que mejora la flexibilidad en piernas', 'MOVILIDAD'),
('Tai Chi', 'Práctica de movimientos lentos para mejorar movilidad y equilibrio', 'MOVILIDAD'),
('Estocadas', 'Ejercicio enfocado en fortalecer piernas y glúteos', 'FUERZA'),
('Nado mariposa', 'Estilo de natación que mejora fuerza y resistencia', 'RESISTENCIA'),
('Carrera de 5km', 'Carrera de distancia moderada para mejorar la resistencia', 'RESISTENCIA'),
('Sprint en bicicleta', 'Ejercicio de velocidad y potencia realizado en bicicleta', 'VELOCIDAD'),
('Carrera de obstáculos', 'Ejercicio que combina resistencia y agilidad', 'RESISTENCIA');



-- Para resetear los datos de clientes y trabajadores manteniendo sus ID
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE taw12.trabajador;
TRUNCATE TABLE taw12.cliente;
TRUNCATE TABLE taw12.cliente_trabajador;
SET FOREIGN_KEY_CHECKS = 1;

-- Añadir clientes
INSERT INTO taw12.Cliente (nombre, email, contrasenya, imagenPerfil, peso, altura, alergias) 
VALUES	('Juan Pérez', 'juan.perez@example.com', 'contraseña1', NULL, 70.5, 170.0, 'Ninguna'),
		('María García', 'maria.garcia@example.com', 'contraseña2', NULL, 65.0, 165.0, 'Pollen'),
		('Pedro Martínez', 'pedro.martinez@example.com', 'contraseña3', NULL, 75.8, 180.0, 'Gluten'),
		('Laura Rodríguez', 'laura.rodriguez@example.com', 'contraseña4', NULL, 80.0, 175.0, 'Lactosa'),
		('Ana López', 'ana.lopez@example.com', 'contraseña5', NULL, 62.3, 160.0, 'Ninguna'),
		('Carlos Fernández', 'carlos.fernandez@example.com', 'pass123', NULL, 70.5, 1.75, 'Ninguna'),
		('Ana Gómez', 'ana.gomez@example.com', 'pass123', NULL, 65.0, 1.68, 'Gluten'),
		('Luis Martínez', 'luis.martinez@example.com', 'pass123', NULL, 80.3, 1.82, 'Frutos de cáscara'),
		('María López', 'maria.lopez@example.com', 'pass123', NULL, 90.7, 1.90, 'Crustáceos'),
		('Elena Sánchez', 'elena.sanchez@example.com', 'pass123', NULL, 68.2, 1.72, 'Huevo'),
		('Diego Torres', 'diego.torres@example.com', 'pass123', NULL, 72.5, 1.80, 'Mostaza'),
		('Laura Díaz', 'laura.diaz@example.com', 'pass123', NULL, 85.0, 1.85, 'Pescado'),
		('Pablo Jiménez', 'pablo.jimenez@example.com', 'pass123', NULL, 60.0, 1.60, 'Sésamo'),
		('Marta Ramírez', 'marta.ramirez@example.com', 'pass123', NULL, 78.9, 1.78, 'Cacahuetes'),
		('Jorge Morales', 'jorge.morales@example.com', 'pass123', NULL, 82.0, 1.75, 'Ninguna'),
		('Sara Ruiz', 'sara.ruiz@example.com', 'pass123', NULL, 68.4, 1.65, 'Gluten'),
		('Alberto Navarro', 'alberto.navarro@example.com', 'pass123', NULL, 77.3, 1.79, 'Frutos de cáscara'),
		('Sofía Molina', 'sofia.molina@example.com', 'pass123', NULL, 72.1, 1.68, 'Crustáceos'),
		('Daniel Ortega', 'daniel.ortega@example.com', 'pass123', NULL, 65.8, 1.70, 'Apio'),
		('Paula Romero', 'paula.romero@example.com', 'pass123', NULL, 90.2, 1.85, 'Huevo'),
		('Hugo García', 'hugo.garcia@example.com', 'pass123', NULL, 62.4, 1.60, 'Mostaza'),
		('Irene Castro', 'irene.castro@example.com', 'pass123', NULL, 68.9, 1.78, 'Pescado'),
		('Manuel Rubio', 'manuel.rubio@example.com', 'pass123', NULL, 80.0, 1.82, 'Sésamo'),
		('Clara Serrano', 'clara.serrano@example.com', 'pass123', NULL, 75.5, 1.77, 'Cacahuetes');


-- Añadir trabajadores
-- Dietistas
INSERT INTO taw12.Trabajador (nombre, email, contrasenya, tipo, imagenPerfil) 
VALUES	('María González', 'maria.gonzalez@example.com', 'contraseña1', 'DIETISTA', NULL),
		('Pedro Sánchez', 'pedro.sanchez@example.com', 'contraseña2', 'DIETISTA', NULL),
		('Laura García', 'laura.garcia@example.com', 'contraseña3', 'DIETISTA', NULL);

-- Entrenadores de Fuerza
INSERT INTO taw12.Trabajador (nombre, email, contrasenya, tipo, imagenPerfil) 
VALUES 	('Juan López', 'juan.lopez@example.com', 'contraseña4', 'ENTRENADOR FUERZA', NULL),
		('Ana Rodríguez', 'ana.rodriguez@example.com', 'contraseña5', 'ENTRENADOR FUERZA', NULL),
		('Carlos Pérez', 'carlos.perez@example.com', 'contraseña6', 'ENTRENADOR FUERZA', NULL);

-- Entrenadores de Crosstraining
INSERT INTO taw12.Trabajador (nombre, email, contrasenya, tipo, imagenPerfil) 
VALUES 	('Elena Martínez', 'elena.martinez@example.com', 'contraseña7', 'ENTRENADOR CROSSTRAINING', NULL),
		('Mario Gómez', 'mario.gomez@example.com', 'contraseña8', 'ENTRENADOR CROSSTRAINING', NULL),
		('Sara Fernández', 'sara.fernandez@example.com', 'contraseña9', 'ENTRENADOR CROSSTRAINING', NULL);
-- Más trabjadores
INSERT INTO `taw12`.`Trabajador` (`nombre`, `email`, `contrasenya`, `tipo`) VALUES
('Pedro Álvarez', 'pedro.alvarez@example.com', 'pass123', 'ENTRENADOR FUERZA'),
('Lucía Torres', 'lucia.torres@example.com', 'pass123', 'ENTRENADOR FUERZA'),
('Roberto García', 'roberto.garcia@example.com', 'pass123', 'ENTRENADOR FUERZA'),
('Carmen Martín', 'carmen.martin@example.com', 'pass123', 'ENTRENADOR FUERZA'),
('Andrés Jiménez', 'andres.jimenez@example.com', 'pass123', 'ENTRENADOR FUERZA'),
('Patricia González', 'patricia.gonzalez@example.com', 'pass123', 'ENTRENADOR FUERZA'),
('Francisco López', 'francisco.lopez@example.com', 'pass123', 'ENTRENADOR FUERZA'),
('Laura Fernández', 'laura.fernandez@example.com', 'pass123', 'ENTRENADOR FUERZA'),
('David Rodríguez', 'david.rodriguez@example.com', 'pass123', 'ENTRENADOR FUERZA'),
('Marta Sánchez', 'marta.sanchez@example.com', 'pass123', 'ENTRENADOR FUERZA'),
('Raúl Pérez', 'raul.perez@example.com', 'pass123', 'ENTRENADOR CROSSTRAINING'),
('Carolina Morales', 'carolina.morales@example.com', 'pass123', 'ENTRENADOR CROSSTRAINING'),
('Fernando Díaz', 'fernando.diaz@example.com', 'pass123', 'ENTRENADOR CROSSTRAINING'),
('Sara Ruiz', 'sara.ruiz@example.com', 'pass123', 'ENTRENADOR CROSSTRAINING'),
('Luis Gómez', 'luis.gomez@example.com', 'pass123', 'ENTRENADOR CROSSTRAINING'),
('Natalia Romero', 'natalia.romero@example.com', 'pass123', 'ENTRENADOR CROSSTRAINING'),
('José Navarro', 'jose.navarro@example.com', 'pass123', 'ENTRENADOR CROSSTRAINING'),
('Elena Castro', 'elena.castro@example.com', 'pass123', 'ENTRENADOR CROSSTRAINING'),
('Víctor Serrano', 'victor.serrano@example.com', 'pass123', 'ENTRENADOR CROSSTRAINING'),
('Cristina Rubio', 'cristina.rubio@example.com', 'pass123', 'ENTRENADOR CROSSTRAINING'),
('Álvaro Fernández', 'alvaro.fernandez@example.com', 'pass123', 'DIETISTA'),
('Beatriz Torres', 'beatriz.torres@example.com', 'pass123', 'DIETISTA'),
('Gonzalo Jiménez', 'gonzalo.jimenez@example.com', 'pass123', 'DIETISTA'),
('Mónica García', 'monica.garcia@example.com', 'pass123', 'DIETISTA'),
('Diego Martín', 'diego.martin@example.com', 'pass123', 'DIETISTA'),
('Claudia Sánchez', 'claudia.sanchez@example.com', 'pass123', 'DIETISTA'),
('Adrián López', 'adrian.lopez@example.com', 'pass123', 'DIETISTA'),
('María Rodríguez', 'maria.rodriguez@example.com', 'pass123', 'DIETISTA'),
('Jorge Pérez', 'jorge.perez@example.com', 'pass123', 'DIETISTA'),
('Alicia Morales', 'alicia.morales@example.com', 'pass123', 'DIETISTA');

        
-- Relaciones Cliente-Entrenador
INSERT INTO taw12.cliente_trabajador (idcliente, idtrabajador)
VALUES
    -- Cliente Juan Pérez (idCliente = 1) con varios tipos de trabajadores
	(1, 1),  -- Juan Pérez con María González (Dietista)
    (1, 4),  -- Juan Pérez con Juan López (Entrenador de Fuerza)
    
    -- Cliente María García (idCliente = 2) con un trabajador
    (2, 4),  -- María García con Juan López (Entrenador de Fuerza)
    
    -- Cliente Pedro Martínez (idCliente = 3) con todos tipos de trabajadores
    (3, 2),  -- Pedro Martínez con Pedro Sánchez (Dietista)
    (3, 5),  -- Pedro Martínez con Ana Rodríguez (Entrenador de Fuerza)
    (3, 7),  -- Pedro Martínez con Elena Martínez (Entrenador Crosstraining)
    
    -- Cliente Laura Rodríguez (idCliente = 4) con un trabajador
    (4, 2),  -- Laura Rodríguez con Pedro Sánchez (Dietista)
    
    -- Cliente Ana López (idCliente = 5) con trabajadores
    (5, 6), -- Ana López con Carlos Pérez (Entrenador de Fuerza)
    (5, 7), -- Ana López con Elena Martínez (Entrenador Crosstraining)
    
    -- Cliente Carlos Fernández (idCliente = 6) con más entrenadores
    (6, 7),   -- Carlos Fernández con Elena Martínez (Entrenador Crosstraining)
    (6, 10),  -- Carlos Fernández con Marta Sánchez (Entrenador de Fuerza)
    
    -- Cliente Ana Gómez (idCliente = 7) con más entrenadores
    (7, 9),   -- Ana Gómez con Sara Fernández (Entrenador Crosstraining)
    (7, 10),  -- Ana Gómez con Marta Sánchez (Entrenador de Fuerza)
    
    -- Cliente Luis Martínez (idCliente = 8) con más entrenadores
    (8, 6),   -- Luis Martínez con Carlos Pérez (Entrenador de Fuerza)
    (8, 7),   -- Luis Martínez con Elena Martínez (Entrenador Crosstraining)
    
    -- Cliente María López (idCliente = 9) con más entrenadores
    (9, 8),   -- María López con Mario Gómez (Entrenador de Fuerza)
    (9, 9);   -- María López con Sara Fernández (Entrenador Crosstraining)

INSERT INTO taw12.rutina (idrutina, nombre, idcliente, idtrabajador) VALUES (1, 'nueva', 3, 7);
INSERT INTO taw12.rutina (idrutina, nombre, idcliente, idtrabajador) VALUES (5, 'AnaRutina', 5, 7);
INSERT INTO taw12.rutina (idrutina, nombre, idcliente, idtrabajador) VALUES (6, 'Nueva Rutina Ana', 5, 7);

INSERT INTO taw12.ejerciciorutina (idejerciciorutina, idrutina, idejercicio, peso, repeticiones, series, orden, diassemana) VALUES (2, 1, 18, '', 20, 2, 0, 'Lunes');
INSERT INTO taw12.ejerciciorutina (idejerciciorutina, idrutina, idejercicio, peso, repeticiones, series, orden, diassemana) VALUES (6, 5, 7, '100 m', 1, 4, 0, 'Lunes');
INSERT INTO taw12.ejerciciorutina (idejerciciorutina, idrutina, idejercicio, peso, repeticiones, series, orden, diassemana) VALUES (7, 5, 4, '10 largos', 2, 3, 1, 'Lunes');
INSERT INTO taw12.ejerciciorutina (idejerciciorutina, idrutina, idejercicio, peso, repeticiones, series, orden, diassemana) VALUES (8, 1, 8, '', 2, 2, 0, 'Martes');
INSERT INTO taw12.ejerciciorutina (idejerciciorutina, idrutina, idejercicio, peso, repeticiones, series, orden, diassemana) VALUES (9, 1, 32, '', 1, 2, 1, 'Miércoles');
INSERT INTO taw12.ejerciciorutina (idejerciciorutina, idrutina, idejercicio, peso, repeticiones, series, orden, diassemana) VALUES (11, 1, 4, '40 largos', 1, 2, 1, 'Lunes');
INSERT INTO taw12.ejerciciorutina (idejerciciorutina, idrutina, idejercicio, peso, repeticiones, series, orden, diassemana) VALUES (13, 6, 5, '50 km', 1, 1, 0, 'Lunes');
INSERT INTO taw12.ejerciciorutina (idejerciciorutina, idrutina, idejercicio, peso, repeticiones, series, orden, diassemana) VALUES (14, 1, 7, '30 m', 5, 2, 0, 'Miércoles');
INSERT INTO taw12.ejerciciorutina (idejerciciorutina, idrutina, idejercicio, peso, repeticiones, series, orden, diassemana) VALUES (15, 1, 17, '', 10, 4, 0, 'Jueves');
INSERT INTO taw12.ejerciciorutina (idejerciciorutina, idrutina, idejercicio, peso, repeticiones, series, orden, diassemana) VALUES (16, 1, 29, '30 min', 1, 1, 0, 'Viernes');

INSERT INTO taw12.seguimientoobjetivos (idseguimiento, idrutina, idcliente, fecha, realizado, pesorealizado, repeticionesrealizadas, seriesrealizadas, observaciones, pesoobjetivo, seriesobjetivo, repeticionesobjetivo, nombreejercicio) VALUES (1, 1, 3, '2024-06-19', 1, '30 metros', 2, 1, 'Me sentia muy cansado del dia anterior', '30 m', 2, 5, 'Sprints');
INSERT INTO taw12.seguimientoobjetivos (idseguimiento, idrutina, idcliente, fecha, realizado, pesorealizado, repeticionesrealizadas, seriesrealizadas, observaciones, pesoobjetivo, seriesobjetivo, repeticionesobjetivo, nombreejercicio) VALUES (2, 1, 3, '2024-06-18', 1, null, 1, 2, null, null, 2, 2, 'Correr 100m');

INSERT INTO taw12.administrador (email, contrasenya) VALUES ('admin@gmail.com', 'admin');

INSERT INTO taw12.dieta (iddieta,nombre,idcliente) VALUES (59,'Dieta para Juan',1);
INSERT INTO taw12.dieta (iddieta,nombre,idcliente) VALUES (60,'Dieta 2 para Juan',1);


INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (1,1,59,0,0,0,'Lunes','Desayuno');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (1,4,60,0,0,0,'Lunes','Desayuno');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (2,2,59,0,0,0,'Miercoles','Desayuno');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (2,5,60,0,0,0,'Miercoles','Desayuno');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (3,5,60,0,0,0,'Viernes','Desayuno');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (3,8,59,0,0,0,'Jueves','Desayuno');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (4,1,59,0,0,0,'Viernes','Desayuno');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (4,9,60,0,0,0,'Sabado','Desayuno');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (5,5,60,0,0,0,'Martes','Mediodia');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (5,11,59,0,0,0,'Sabado','Desayuno');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (6,5,60,0,0,0,'Miercoles','Mediodia');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (6,7,59,0,0,0,'Martes','Mediodia');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (7,6,59,0,0,0,'Viernes','Mediodia');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (7,8,60,0,0,0,'Viernes','Mediodia');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (8,6,60,0,0,0,'Domingo','Mediodia');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (8,7,59,0,0,0,'Lunes','Almuerzo');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (9,5,60,0,0,0,'Lunes','Almuerzo');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (9,9,59,0,0,0,'Martes','Almuerzo');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (10,7,60,0,0,0,'Martes','Almuerzo');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (10,10,59,0,0,0,'Miercoles','Almuerzo');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (11,5,59,0,0,0,'Jueves','Almuerzo');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (11,7,60,0,0,0,'Miercoles','Almuerzo');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (12,5,60,0,0,0,'Jueves','Almuerzo');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (12,6,59,0,0,0,'Viernes','Almuerzo');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (13,8,60,0,0,0,'Viernes','Almuerzo');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (13,9,59,0,0,0,'Sabado','Almuerzo');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (14,7,59,0,0,0,'Domingo','Almuerzo');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (14,8,60,0,0,0,'Domingo','Almuerzo');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (15,5,60,0,0,0,'Martes','Merienda');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (15,6,59,0,0,0,'Miercoles','Merienda');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (16,6,60,0,0,0,'Jueves','Merienda');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (16,8,59,0,0,0,'Domingo','Merienda');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (17,6,59,0,0,0,'Lunes','Cena');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (17,6,60,0,0,0,'Sabado','Merienda');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (18,7,60,0,0,0,'Lunes','Cena');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (18,10,59,0,0,0,'Martes','Cena');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (19,8,60,0,0,0,'Jueves','Cena');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (19,12,59,0,0,0,'Jueves','Cena');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (20,6,59,0,0,0,'Sabado','Cena');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (20,6,60,0,0,0,'Sabado','Cena');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (21,7,60,0,0,0,'Domingo','Cena');
INSERT INTO taw12.platodieta (`idplatodieta`,`idplato`,`iddieta`,`calorias`,`cantidad`,`orden`,`diassemana`,`franjahoraria`) VALUES (21,13,59,0,0,0,'Domingo','Cena');
