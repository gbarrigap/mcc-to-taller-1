-- Set foreign keys on the Sqlite DB.
--PRAGMA foreign_keys = ON;

-- 
DELETE FROM materiales_prestamos;
DELETE FROM prestamos;
DELETE FROM ejemplares;
DELETE FROM libros;
DELETE FROM cds;
DELETE FROM revistas;
DELETE FROM materiales;
DELETE FROM usuarios;

-- LIBROS
INSERT INTO materiales(titulo, editorial) VALUES ('Writing Effective Use Cases', 'Addyson-Wesley');
INSERT INTO libros(mid, isbn, autor) VALUES ((SELECT max(mid) FROM materiales), '9780201702255', 'Alistair Cockburn');
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 1);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 2);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 3);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 4);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 5);

INSERT INTO materiales(titulo, editorial) VALUES ('Physics of the Impossible', 'Anchor Books');
INSERT INTO libros(mid, isbn, autor) VALUES ((SELECT max(mid) FROM materiales), '9780307278821', 'Michio Kaku');
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 1);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 2);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 3);
