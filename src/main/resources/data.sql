-- Insertar datos en la tabla 'coordenadas'
INSERT INTO coordenada (id, latitud, longitud) VALUES (1, 40.7128, -74.0060);
INSERT INTO coordenada (id, latitud, longitud) VALUES (2, 34.0522, -118.2437);
INSERT INTO coordenada (id, latitud, longitud) VALUES (3, 41.8781, -87.6298);
INSERT INTO coordenada (id, latitud, longitud) VALUES (4, 29.7604, -95.3698);
INSERT INTO coordenada (id, latitud, longitud) VALUES (5, 39.7392, -104.9903);
INSERT INTO coordenada (id, latitud, longitud) VALUES (6, 37.7749, -122.4194);
INSERT INTO coordenada (id, latitud, longitud) VALUES (7, 33.4484, -112.0740);

-- Insertar datos en la tabla 'categorias'
INSERT INTO categoria (id, nombre, descripcion, tipo_categoria) VALUES (1, 'Bebida Con Alcohol', 'Esta bebida es con alcohol, prohibido su consumo en menores', 'BEBIDA');
INSERT INTO categoria (id, nombre, descripcion, tipo_categoria) VALUES (2, 'Bebida Sin Alcohol', 'Esta bebida es sin alcohol', 'BEBIDA');
INSERT INTO categoria (id, nombre, descripcion, tipo_categoria) VALUES (3, 'Comida Vegana', 'Esta comida es vegana', 'COMIDA');
INSERT INTO categoria (id, nombre, descripcion, tipo_categoria) VALUES (4, 'Comida Vegetariana', 'Esta comida es vegetariana, no apta para veganos', 'COMIDA');
INSERT INTO categoria (id, nombre, descripcion, tipo_categoria) VALUES (5, 'Comida Clasica', 'Esta comida no es apta para vegetarianos, ni apta para veganos', 'COMIDA');


-- Insertar datos en la tabla 'vendedores'
INSERT INTO vendedor (id, activo, coordenada_Id, fecha_eliminacion, fecha_registro, direccion, nombre) VALUES (1, 1, 1, CURDATE(), CURDATE(), 'Avenida Siempre Viva 742', 'Gaia');
INSERT INTO vendedor (id, activo, coordenada_Id, fecha_eliminacion, fecha_registro, direccion, nombre) VALUES (2, 1, 2, CURDATE(), CURDATE(), 'Calle Principal 123', 'La Buena Pizza');
INSERT INTO vendedor (id, activo, coordenada_Id, fecha_eliminacion, fecha_registro, direccion, nombre) VALUES (3, 1, 3, CURDATE(), CURDATE(), 'Boulevard Central 456', 'Kiosko Pancho');
INSERT INTO vendedor (id, activo, coordenada_Id, fecha_eliminacion, fecha_registro, direccion, nombre) VALUES (4, 1, 4, CURDATE(), CURDATE(), 'Avenida del Sol 789', 'Cafe Amanecer');
INSERT INTO vendedor (id, activo, coordenada_Id, fecha_eliminacion, fecha_registro, direccion, nombre) VALUES (5, 1, 5, CURDATE(), CURDATE(), 'Plaza Mayor 101', 'Sabor y Tradicion');


-- Insertar datos en la tabla 'item_menu'
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 1, 2, NULL, 150.00, 'Refresco de cola', 'Coca Cola', 1);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (2, 1, 2, NULL, 150.00, 'Refresco sabor lima', 'Sprite', 1);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (3, 1, 2, NULL, 150.00, 'Refresco sabor naranja', 'Fanta', 1);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (4, 1, 2, NULL, 25.00, 'Refresco sabor uva', 'Manaos de Uva', 1);
--5
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (5, 1, 1, NULL, 300.00, 'Fernet con Coca-Cola', 'Fernet con Coca', 2);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (6, 1, 1, NULL, 210.00, 'Campari', 'Camari', 2);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (7, 1, 1, NULL, 220.00, 'VodKa con bebida energizante', 'Vodka con Speed', 2);
--9
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (8, 1, 2, NULL, 150.00, 'Bebida energizante', 'Speed', 3);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (9, 1, 2, NULL, 180.00, 'Bebida energizante Monster sabor comun', 'Monster', 3);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (10, 1, 2, NULL, 180.00, 'Bebida energizante Monster sin azucar', 'Monster Blanca', 3);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (11, 1, 2, NULL, 200.00, 'Bebida energizante Monster sabor mango', 'Monster Mango', 3);
--13
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (12, 1, 2, NULL, 140.00, 'Cafe negro', 'Cafe Cortado', 4);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (13, 1, 2, NULL, 160.00, 'Cafe Latte', 'Latte', 4);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (14, 1, 2, NULL, 200.00, 'Cafe de especialidad', 'Cafe Especial', 4);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (15, 1, 2, NULL, 110.00, 'Jugo de naranja exprimido a mano', 'Jugo de naranja', 4);
--17
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (16, 1, 2, NULL, 150.00, 'Refresco de cola', 'Pepsi', 5);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (17, 1, 2, NULL, 150.00, 'Refresco sabor lima', 'Seven-Up', 5);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (18, 1, 2, NULL, 150.00, 'Refresco sabor naranja', 'Mirinda', 5);
--20//platos
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (19, 1, 5, NULL, 300.00, 'Empanadas de carne de res', 'Empanadas de carne', 1);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (20, 1, 5, NULL, 300.00, 'Empanadas de jamon y queso', 'Empanadas de jamon y queso', 1);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (21, 1, 5, NULL, 300.00, 'Sanguche de milanesa con tomate, lechuga y huevo', 'Sanguche de milanesa', 1);
--23
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (22, 1, 4, NULL, 400.00, 'Pizza Muzzarella', 'Pizza Muzzarella', 2);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (23, 1, 4, NULL, 450.00, 'Pizza con tomate y albahaca', 'Pizza Napolitana', 2);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (24, 1, 5, NULL, 500.00, 'Pizza jamon, queso y morron', 'Pizza Especial', 2);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (25, 1, 5, NULL, 400.00, 'Hamburguesa simple', 'Hamburguesa Simple', 2);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (26, 1, 5, NULL, 600.00, 'Hamburguesa con tomate, lechuga, cebolla crispy, huevo y panceta', 'Hamburguesa Completa', 2);
--28
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (27, 1, 4, NULL, 80.00, 'Alfajor triple de la marca oreo', 'Alfajor Oreo triple', 3);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (28, 1, 4, NULL, 80.00, 'Alfajor triple de la marca terrabusi', 'Alfajor Terrabusi triple', 3);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (29, 1, 5, NULL, 70.00, 'Alfajor simple de la marca Rasta', 'Alfajor Rasta', 3);
--31
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (30, 1, 4, NULL, 110.00, 'Tostado de jamon y queso', 'Tostado Simple', 4);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (31, 1, 4, NULL, 100.00, 'Alfajor triple de la marca Havana', 'Alfajor Havana', 4);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (32, 1, 4, NULL, 50.00, 'Medialuna', 'Medialuna Salada', 4);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (33, 1, 3, NULL, 300.00, 'Cookies sin ningun alimento origen animal', 'Cookies Veganas', 4);
--35
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (34, 1, 4, NULL, 300.00, 'Empanadas de queso y choclo', 'Empanada de humita', 5);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (35, 1, 5, NULL, 400.00, 'Sanguche de lomo', 'Sanguche de lomo', 5);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (36, 1, 3, NULL, 600.00, 'Hamburguesa de lenteja', 'Hamburguesa Vegana', 5);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (37, 1, 5, NULL, 350.00, 'Papas fritas con cheddar y panceta', 'Papas con cheddar y panceta', 5);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (38, 1, 3, NULL, 200.00, 'Ensalada de quinoa, lentejas, tomate, pepino, pimiento, apio, cebolla morada y vinagreta', 'Ensalada Especial', 5);
INSERT INTO item_menu (id, activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (39, 1, 4, NULL, 300.00, 'Papas fritas con cheddar', 'Papas con cheddar', 5);

-- Insertar datos en la tabla 'Bebidas' con id
INSERT INTO bebida (id, graduacion_alcoholica, tamano, volumen) VALUES (1, 0.0, 500, 500);
INSERT INTO bebida (id, graduacion_alcoholica, tamano, volumen) VALUES (2, 0.0, 500, 500);
INSERT INTO bebida (id, graduacion_alcoholica, tamano, volumen) VALUES (3, 0.0, 500, 500);
INSERT INTO bebida (id, graduacion_alcoholica, tamano, volumen) VALUES (4, 0.0, 1500, 1500);

INSERT INTO bebida (id, graduacion_alcoholica, tamano, volumen) VALUES (5, 30.0, 500, 500);
INSERT INTO bebida (id, graduacion_alcoholica, tamano, volumen) VALUES (6, 15.0, 500, 500);
INSERT INTO bebida (id, graduacion_alcoholica, tamano, volumen) VALUES (7, 20.0, 500, 500);

INSERT INTO bebida (id, graduacion_alcoholica, tamano, volumen) VALUES (8, 0.0, 500, 500);
INSERT INTO bebida (id, graduacion_alcoholica, tamano, volumen) VALUES (9, 0.0, 473, 473);
INSERT INTO bebida (id, graduacion_alcoholica, tamano, volumen) VALUES (10, 0.0, 473, 473);
INSERT INTO bebida (id, graduacion_alcoholica, tamano, volumen) VALUES (11, 0.0, 473, 473);

INSERT INTO bebida (id, graduacion_alcoholica, tamano, volumen) VALUES (12, 0.0, 300, 300);
INSERT INTO bebida (id, graduacion_alcoholica, tamano, volumen) VALUES (13, 0.0, 300, 300);
INSERT INTO bebida (id, graduacion_alcoholica, tamano, volumen) VALUES (14, 0.0, 300, 300);
INSERT INTO bebida (id, graduacion_alcoholica, tamano, volumen) VALUES (15, 0.0, 500, 500);

INSERT INTO bebida (id, graduacion_alcoholica, tamano, volumen) VALUES (16, 0.0, 1000, 1000);
INSERT INTO bebida (id, graduacion_alcoholica, tamano, volumen) VALUES (17, 0.0, 1000, 1000);
INSERT INTO bebida (id, graduacion_alcoholica, tamano, volumen) VALUES (18, 0.0, 1000, 1000);

-- Insertar datos en la tabla 'Platos' (comenzando desde id 19)
INSERT INTO plato (id, apto_celiaco, apto_vegano, apto_vegetariano, calorias, peso) VALUES (19, 0, 0, 0, 250.0, 200);
INSERT INTO plato (id, apto_celiaco, apto_vegano, apto_vegetariano, calorias, peso) VALUES (20, 0, 0, 0, 200.0, 200);
INSERT INTO plato (id, apto_celiaco, apto_vegano, apto_vegetariano, calorias, peso) VALUES (21, 0, 0, 0, 300.0, 500);

INSERT INTO plato (id, apto_celiaco, apto_vegano, apto_vegetariano, calorias, peso) VALUES (22, 0, 0, 1, 300.0, 300);
INSERT INTO plato (id, apto_celiaco, apto_vegano, apto_vegetariano, calorias, peso) VALUES (23, 0, 0, 1, 300.0, 300);
INSERT INTO plato (id, apto_celiaco, apto_vegano, apto_vegetariano, calorias, peso) VALUES (24, 0, 0, 0, 370.0, 350);
INSERT INTO plato (id, apto_celiaco, apto_vegano, apto_vegetariano, calorias, peso) VALUES (25, 0, 0, 0, 350.0, 250);
INSERT INTO plato (id, apto_celiaco, apto_vegano, apto_vegetariano, calorias, peso) VALUES (26, 0, 0, 0, 360.0, 260);

INSERT INTO plato (id, apto_celiaco, apto_vegano, apto_vegetariano, calorias, peso) VALUES (27, 0, 0, 1, 100.0, 100);
INSERT INTO plato (id, apto_celiaco, apto_vegano, apto_vegetariano, calorias, peso) VALUES (28, 0, 0, 1, 100.0, 100);
INSERT INTO plato (id, apto_celiaco, apto_vegano, apto_vegetariano, calorias, peso) VALUES (29, 0, 0, 1, 80.0, 80);

INSERT INTO plato (id, apto_celiaco, apto_vegano, apto_vegetariano, calorias, peso) VALUES (30, 0, 0, 0, 100.0, 200);
INSERT INTO plato (id, apto_celiaco, apto_vegano, apto_vegetariano, calorias, peso) VALUES (31, 0, 0, 1, 100.0, 80);
INSERT INTO plato (id, apto_celiaco, apto_vegano, apto_vegetariano, calorias, peso) VALUES (32, 0, 1, 0, 80.0, 50);
INSERT INTO plato (id, apto_celiaco, apto_vegano, apto_vegetariano, calorias, peso) VALUES (33, 0, 1, 0, 350.0, 250);

INSERT INTO plato (id, apto_celiaco, apto_vegano, apto_vegetariano, calorias, peso) VALUES (34, 0, 0, 1, 200.0, 200);
INSERT INTO plato (id, apto_celiaco, apto_vegano, apto_vegetariano, calorias, peso) VALUES (35, 0, 0, 1, 300.0, 290);
INSERT INTO plato (id, apto_celiaco, apto_vegano, apto_vegetariano, calorias, peso) VALUES (36, 0, 1, 0, 120.0, 250);
INSERT INTO plato (id, apto_celiaco, apto_vegano, apto_vegetariano, calorias, peso) VALUES (37, 1, 0, 0, 250.0, 200);
INSERT INTO plato (id, apto_celiaco, apto_vegano, apto_vegetariano, calorias, peso) VALUES (38, 1, 1, 0, 80.0, 200);
INSERT INTO plato (id, apto_celiaco, apto_vegano, apto_vegetariano, calorias, peso) VALUES (39, 1, 0, 1, 230.0, 200);


-- Insertar datos en la tabla 'cliente'
INSERT INTO cliente (id, activo, coordenada_Id, fecha_eliminacion, fecha_registro, direccion, nombre, cuit, email) VALUES (1, 1, 7, NULL, CURDATE(), 'Calle Falsa 123', 'Homero Simpson', '20-12345678-9', 'homero@example.com');

-- Insertar datos en la tabla 'pago'
INSERT INTO Pago (id, fecha, monto, tipo_pago, alias, cbu, cuit) VALUES (1, CURDATE(), 300.00, 'Transferencia', 'Alias1', '1234567890', '20-12345678-9');