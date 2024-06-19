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
