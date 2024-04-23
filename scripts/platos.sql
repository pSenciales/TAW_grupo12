
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE plato;
SET FOREIGN_KEY_CHECKS = 1;


-- Platos
INSERT INTO `mydb`.`plato` (`nombre`, `alergenos`, `video`, `descripcion`) 
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