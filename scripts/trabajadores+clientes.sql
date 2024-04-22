-- Para resetear los datos de clientes y trabajadores manteniendo sus ID
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE trabajador;
TRUNCATE TABLE cliente;
SET FOREIGN_KEY_CHECKS = 1;
--

-- Añadir clientes
INSERT INTO `mydb`.`Cliente` (`nombre`, `email`, `contrasenya`, `imagenPerfil`, `peso`, `altura`, `alergias`, `idRutina`, `idEntrenador`, `idDieta`, `idDietista`) 
VALUES	('Juan Pérez', 'juan.perez@example.com', 'contraseña1', NULL, 70.5, 170.0, 'Ninguna', NULL, 4, NULL, 1),
		('María García', 'maria.garcia@example.com', 'contraseña2', NULL, 65.0, 165.0, 'Pollen', NULL, 6, NULL, 2),
		('Pedro Martínez', 'pedro.martinez@example.com', 'contraseña3', NULL, 75.8, 180.0, 'Gluten', NULL, 9, NULL, 3),
		('Laura Rodríguez', 'laura.rodriguez@example.com', 'contraseña4', NULL, 80.0, 175.0, 'Lactosa', NULL, 8, NULL, NULL),
		('Ana López', 'ana.lopez@example.com', 'contraseña5', NULL, 62.3, 160.0, 'Ninguna', NULL, NULL, NULL, 2);

-- Añadir trabajadores
-- Dietistas
INSERT INTO `mydb`.`Trabajador` (`nombre`, `email`, `contrasenya`, `tipo`, `imagenPerfil`) 
VALUES	('María González', 'maria.gonzalez@example.com', 'contraseña1', 'DIETISTA', NULL),
		('Pedro Sánchez', 'pedro.sanchez@example.com', 'contraseña2', 'DIETISTA', NULL),
		('Laura García', 'laura.garcia@example.com', 'contraseña3', 'DIETISTA', NULL);

-- Entrenadores de Fuerza
INSERT INTO `mydb`.`Trabajador` (`nombre`, `email`, `contrasenya`, `tipo`, `imagenPerfil`) 
VALUES 	('Juan López', 'juan.lopez@example.com', 'contraseña4', 'ENTRENADOR FUERZA', NULL),
		('Ana Rodríguez', 'ana.rodriguez@example.com', 'contraseña5', 'ENTRENADOR FUERZA', NULL),
		('Carlos Pérez', 'carlos.perez@example.com', 'contraseña6', 'ENTRENADOR FUERZA', NULL);

-- Entrenadores de Crosstraining
INSERT INTO `mydb`.`Trabajador` (`nombre`, `email`, `contrasenya`, `tipo`, `imagenPerfil`) 
VALUES 	('Elena Martínez', 'elena.martinez@example.com', 'contraseña7', 'ENTRENADOR CROSSTRAINNING', NULL),
		('Mario Gómez', 'mario.gomez@example.com', 'contraseña8', 'ENTRENADOR CROSSTRAINNING', NULL),
		('Sara Fernández', 'sara.fernandez@example.com', 'contraseña9', 'ENTRENADOR CROSSTRAINNING', NULL);

