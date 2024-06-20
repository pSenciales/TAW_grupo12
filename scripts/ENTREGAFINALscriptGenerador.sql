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
INSERT INTO `taw12`.`plato` (`nombre`, `alergenos`, `video`, `descripcion`) 
VALUES	('Salmón al Horno', 'Pescado', NULL, 'Filete de salmón fresco horneado con una mezcla de hierbas aromáticas. Acompañado de verduras al vapor.'),
		('Ensalada César', 'Lácteos, Gluten', NULL, 'Mezcla de lechugas frescas, pollo a la parrilla, crutones de pan y aderezo César. Contiene queso parmesano rallado.'),
		('Risotto de Champiñones', 'Lácteos', NULL, 'Arroz cremoso cocido a fuego lento con champiñones frescos, caldo de vegetales y un toque de queso parmesano.'),
        ('Tacos de Camarones', 'Crustáceos', NULL, 'Tortillas de maíz rellenas de camarones salteados con cebolla, pimientos y especias. Acompañados de guacamole y salsa.'),
        ('Pizza Margarita', 'Lácteos, Gluten', NULL, 'Pizza con salsa de tomate, mozzarella fresca y albahaca.'),
        ('Sopa de Tomate', 'Apio', NULL, 'Sopa caliente de tomate, cocida con cebolla, ajo, zanahorias y apio, sazonada con hierbas mediterráneas.'),
        ('Pollo a la Parrilla', NULL, NULL, 'Pechuga de pollo marinada y grillada a la perfección. Servida con una guarnición de vegetales asados.'),
        ('Paella de Mariscos', 'Crustáceos', NULL, 'Plato tradicional español con arroz, mejillones, camarones, calamares y langostinos cocidos en un caldo de pescado.'),
        ('Filete Mignon', NULL, NULL, 'Jugoso filete de ternera asado a la parrilla y acompañado de una salsa de champiñones.'),
        ('Pasta Carbonara', 'Lácteos, Gluten, Huevos', NULL, 'Pasta italiana con salsa de huevo, queso parmesano, panceta y pimienta negra.'),
        ('Ensalada de Frutas', NULL, NULL, 'Mezcla de frutas frescas de temporada con un toque de miel y menta fresca.'),
        ('Curry de Verduras', NULL, NULL, 'Verduras mixtas cocidas en una sabrosa salsa de curry con leche de coco, servidas con arroz basmati.'),
        ('Hamburguesa Vegana', 'Gluten', NULL, 'Hamburguesa de lentejas y vegetales, servida en un pan integral con lechuga, tomate y salsa de aguacate.'),
        ('Sushi Variado', 'Pescado, Gluten', NULL, 'Variedad de rollos de sushi con ingredientes como salmón, atún, aguacate y pepino.'),
		('Lasaña de Carne', 'Lácteos, Gluten', NULL, 'Capas de pasta intercaladas con una mezcla de carne molida, salsa de tomate y queso ricotta, cubiertas con mozzarella.'),
        ('Sopa de Lentejas', NULL, NULL, 'Sopa reconfortante preparada con lentejas, zanahorias, apio, cebolla y tomate, sazonada con hierbas aromáticas.'),
        ('Tarta de Manzana', 'Gluten', NULL, 'Deliciosa tarta de manzana casera con una base crujiente de masa quebrada y un relleno de manzanas caramelizadas.'),
        ('Tacos de Pollo', NULL, NULL, 'Tortillas de maíz rellenas de pollo desmenuzado, cilantro, cebolla, tomate y salsa de pico de gallo.'),
        ('Ensalada Griega', 'Lácteos', NULL, 'Ensalada fresca con pepino, tomate, cebolla roja, aceitunas kalamata, queso feta y aderezo de limón y aceite de oliva.'),
        ('Pad Thai', 'Cacahuetes, Gluten', NULL, 'Plato tailandés de fideos de arroz salteados con camarones, tofu, brotes de soja, cacahuetes y salsa de tamarindo.'),
        ('Pollo al Curry', NULL, NULL, 'Trozos de pollo tierno cocidos en una salsa de curry con leche de coco, servidos con arroz jazmín y naan.'),
        ('Estofado de Ternera', NULL, NULL, 'Guiso de ternera cocido a fuego lento con verduras de temporada como zanahorias, papas y guisantes.'),
        ('Ensalada de Quinoa', NULL, NULL, 'Ensalada fresca con quinoa cocida, pepino, tomate cherry, aguacate, y aderezo de limón y cilantro.'),
        ('Tiramisú', 'Lácteos, Huevos', NULL, 'Postre italiano compuesto por capas de bizcochos empapados en café, crema de mascarpone y cacao en polvo.'),
        ('Rollitos de Primavera', 'Gluten', NULL, 'Rollitos crujientes rellenos de vegetales frescos como zanahorias, repollo, champiñones y fideos de arroz, servidos con salsa agridulce.'),
        ('Filete de Salmón a la Parrilla', 'Pescado', NULL, 'Filete de salmón marinado y grillado a la perfección, acompañado de espárragos a la parrilla y puré de papas.'),
		('Sopa de Fideos', 'Gluten', NULL, 'Sopa reconfortante con fideos finos en un caldo de pollo casero, con zanahorias, apio y cebolla.'),
        ('Bistec a la Parrilla', NULL, NULL, 'Jugoso bistec de res asado a la parrilla, sazonado con sal marina y pimienta negra, acompañado de papas al horno y vegetales asados.'),
        ('Espaguetis a la Boloñesa', 'Lácteos, Gluten', NULL, 'Pasta italiana servida con una salsa de carne de res cocinada a fuego lento con tomate, cebolla, zanahoria y apio.'),
        ('Ensalada de Atún', 'Pescado', NULL, 'Ensalada fresca con hojas verdes, tomate, huevo duro, aceitunas, atún en lata y aderezo de limón y aceite de oliva.'),
        ('Sushi de Salmón', 'Pescado, Gluten', NULL, 'Variedad de rollos de sushi con salmón fresco, aguacate, pepino y queso crema, servidos con salsa de soja y wasabi.'),
        ('Chili con Carne', NULL, NULL, 'Guiso picante de carne de res molida cocida con frijoles, tomates, cebolla, pimientos y especias, servido con arroz.'),
        ('Tarta de Chocolate', 'Lácteos, Gluten, Huevos', NULL, 'Deliciosa tarta de chocolate con una base de galleta triturada, relleno de crema de chocolate y decorada con virutas de chocolate.'),
        ('Pollo al Limón', NULL, NULL, 'Trozos de pollo marinados en una mezcla de limón, ajo y hierbas, luego cocidos a la parrilla y servidos con arroz al limón.'),
        ('Enchiladas de Pollo', 'Lácteos', NULL, 'Tortillas de maíz rellenas de pollo desmenuzado y salsa roja picante, gratinadas con queso cheddar y crema agria.'),
        ('Paella Vegetariana', NULL, NULL, 'Paella española sin carne, preparada con arroz, verduras mixtas como pimientos, guisantes, alcachofas y espárragos.'),
        ('Tarta de Limón', 'Lácteos, Gluten', NULL, 'Postre refrescante con una base de galleta de limón, relleno de crema de limón y cobertura de merengue tostado.'),
        ('Wraps de Pollo', NULL, NULL, 'Tortillas de trigo rellenas de pollo a la parrilla, lechuga, tomate, aguacate, y aderezo de mayonesa y mostaza.'),
		('Ceviche de Camarón', 'Crustáceos, Pescado', NULL, 'Plato peruano de camarones crudos marinados en jugo de limón con cebolla morada, ají limo, cilantro y maíz choclo.'),
        ('Tacos de Carne Asada', NULL, NULL, 'Tortillas de maíz rellenas de carne de res asada a la parrilla, cebolla caramelizada, cilantro y salsa de tomate picante.');
        
        USE taw12;

INSERT INTO ejercicio (nombre, descripcion, tipo) VALUES 
('Sentadilla', 'Ejercicio básico de fuerza para las piernas', 'FUERZA'),
('Press de banca', 'Ejercicio de fuerza para el pecho', 'FUERZA'),
('Carrera continua', 'Ejercicio de resistencia cardiovascular', 'RESISTENCIA'),
('Natación', 'Ejercicio de resistencia en el agua', 'RESISTENCIA'),
('Ciclismo', 'Actividad aeróbica que mejora la capacidad cardiovascular', 'CAPACIDAD AEROBICA'),
('Salto de cuerda', 'Ejercicio aeróbico para mejorar la coordinación y resistencia', 'CAPACIDAD AEROBICA'),
('Sprints', 'Carreras cortas a máxima velocidad', 'VELOCIDAD'),
('Correr 100m', 'Carrera de velocidad pura en distancia corta', 'VELOCIDAD'),
('Levantamiento de pesas', 'Ejercicio de alta intensidad para aumentar la potencia muscular', 'POTENCIA'),
('Clean and Jerk', 'Levantamiento de pesas olímpico para potencia explosiva', 'POTENCIA'),
('Planchas', 'Ejercicio de estabilidad para el núcleo abdominal', 'ESTABILIDAD'),
('Superman', 'Ejercicio para mejorar la estabilidad y la fuerza de la espalda baja', 'ESTABILIDAD'),
('Estiramientos dinámicos', 'Ejercicios para mejorar la movilidad articular', 'MOVILIDAD'),
('Yoga', 'Práctica de posturas para aumentar la movilidad y flexibilidad', 'MOVILIDAD'),
('Remo', 'Ejercicio de fuerza y resistencia para la parte superior del cuerpo', 'FUERZA'),
('Dominadas', 'Ejercicio de fuerza para la espalda y los brazos', 'FUERZA'),
('Flexiones', 'Ejercicio de fuerza para el pecho y los tríceps', 'FUERZA'),
('Abdominales', 'Ejercicio para fortalecer los músculos abdominales', 'FUERZA'),
('Bicicleta estática', 'Ejercicio aeróbico para mejorar la capacidad cardiovascular', 'CAPACIDAD AEROBICA'),
('Burpees', 'Ejercicio de cuerpo completo para resistencia y potencia', 'POTENCIA'),
('Remo en máquina', 'Ejercicio de resistencia y fuerza para la parte superior del cuerpo', 'RESISTENCIA'),
('Trote suave', 'Ejercicio de resistencia cardiovascular a un ritmo moderado', 'RESISTENCIA'),
('Saltos al cajón', 'Ejercicio de potencia y velocidad para las piernas', 'POTENCIA'),
('Skipping', 'Ejercicio de velocidad y coordinación', 'VELOCIDAD'),
('Zancadas', 'Ejercicio de fuerza para las piernas y glúteos', 'FUERZA'),
('Peso muerto', 'Ejercicio de fuerza para la espalda y piernas', 'FUERZA'),
('Circuito de alta intensidad', 'Entrenamiento para mejorar la resistencia y fuerza', 'RESISTENCIA'),
('Flexibilidad de piernas', 'Ejercicio para mejorar la flexibilidad de las piernas', 'MOVILIDAD'),
('Tai Chi', 'Práctica de movimientos lentos para mejorar la movilidad y equilibrio', 'MOVILIDAD'),
('Estocadas', 'Ejercicio para fortalecer las piernas y glúteos', 'FUERZA'),
('Nado mariposa', 'Estilo de natación para mejorar la fuerza y resistencia', 'RESISTENCIA'),
('Carrera de 5km', 'Carrera de distancia moderada para resistencia', 'RESISTENCIA'),
('Sprint en bicicleta', 'Ejercicio de velocidad y potencia en bicicleta', 'VELOCIDAD'),
('Carrera de obstáculos', 'Ejercicio de resistencia y agilidad', 'RESISTENCIA');

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
		('Ana López', 'ana.lopez@example.com', 'contraseña5', NULL, 62.3, 160.0, 'Ninguna');

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
    (5, 7); -- Ana López con Elena Martínez (Entrenador Crosstraining)

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

