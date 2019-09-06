INSERT INTO videojuegos(nombre, consola, precio, disponible, cantidad) VALUES ('Gears Of war 4', 'play station 4 y Xbox one', 350, TRUE, 200);
INSERT INTO videojuegos(nombre, consola, precio, disponible, cantidad) VALUES ('Call of duty', 'play station 4 y Xbox one', 800, TRUE, 500);
INSERT INTO videojuegos(nombre, consola, precio, disponible, cantidad) VALUES ('Destiny', 'play station 4 y Xbox one', 0, TRUE, 200);
INSERT INTO videojuegos(nombre, consola, precio, disponible, cantidad) VALUES ('Rainbow six', 'play station 4 y Xbox one', 500, TRUE, 100);
INSERT INTO videojuegos(nombre, consola, precio, disponible, cantidad) VALUES ('Crash', 'play station 4 y Xbox one', 1000, TRUE, 50);
INSERT INTO videojuegos(nombre, consola, precio, disponible, cantidad) VALUES ('Gears Of war 5', 'Xbox one', 1750, TRUE, 50);
INSERT INTO videojuegos(nombre, consola, precio, disponible, cantidad) VALUES ('Destiny2', 'play station 4 y Xbox one', 120, TRUE, 10);
INSERT INTO videojuegos(nombre, consola, precio, disponible, cantidad) VALUES ('Gears3', 'Xbox 360', 150, TRUE, 20);
INSERT INTO videojuegos(nombre, consola, precio, disponible, cantidad) VALUES ('Fallout 4', 'play station 4 y Xbox one', 700, TRUE, 20);
INSERT INTO videojuegos(nombre, consola, precio, disponible, cantidad) VALUES ('Metro last light', 'play station 4 y Xbox one', 500, TRUE, 24);



INSERT INTO users(username, password, enable, nombre, email, apellido, foto) VALUES ('Brayan', '$2a$10$yYBPJGLtlCUu.cfPZdhf.uaEccLR7b70NO/mGmk1FcXtjMcGTPy5W', 1, 'Brayan', 'brayan07@gmail.com', 'Mora', null);
INSERT INTO users(username, password, enable, nombre, email, apellido, foto) VALUES ('Jose', '$2a$10$UHr2bZpOCROuDddIJ9KfK.DuhsWIHgYSvolJiTHnXbVnJhjl5SQ5m', 1, 'Jose', 'jose@gmail.com', 'Cruz', null);

INSERT INTO roles(nombre) VALUES ('ROLE_ADMIN');
INSERT INTO roles(nombre) VALUES ('ROLE_USER');

INSERT INTO usuarios_roles(usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles(usuario_id, role_id) VALUES (1, 2);
INSERT INTO usuarios_roles(usuario_id, role_id) VALUES (2, 2);

INSERT INTO orders(direccion, user_id, create_at) VALUES ('Calle la carrera col la malinche', 1, NOW());

INSERT INTO orden_producto(videojuego_id, orden_id, cantidad) VALUES (1, 1, 1);
INSERT INTO orden_producto(videojuego_id, orden_id, cantidad) VALUES (3, 1, 1);