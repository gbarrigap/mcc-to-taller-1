-- Set foreign keys on the Sqlite DB.
-- PRAGMA foreign_keys = ON;
PRAGMA foreign_keys = OFF;

-- 
DELETE FROM materiales_prestamos;
DELETE FROM prestamos;
DELETE FROM ejemplares;
DELETE FROM libros;
DELETE FROM revistas;
DELETE FROM cds;
DELETE FROM materiales;
DELETE FROM usuarios;

-- CDS
INSERT INTO materiales(titulo, editorial) VALUES ('CD1', 'Disquera 1');
INSERT INTO cds(mid) VALUES ((SELECT max(mid) FROM materiales));
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 1);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 2);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 3);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 4);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 5);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 6);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 7);

INSERT INTO materiales(titulo, editorial) VALUES ('CD2', 'Disquera 1');
INSERT INTO cds(mid) VALUES ((SELECT max(mid) FROM materiales));
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 1);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 2);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 3);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 4);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 5);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 6);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 7);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 8);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 9);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 10);

INSERT INTO materiales(titulo, editorial) VALUES ('CD3', 'Disquera 2');
INSERT INTO cds(mid) VALUES ((SELECT max(mid) FROM materiales));
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 1);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 2);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 3);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 4);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 5);

INSERT INTO materiales(titulo, editorial) VALUES ('CD4', 'Disquera 2');
INSERT INTO cds(mid) VALUES ((SELECT max(mid) FROM materiales));
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 1);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 2);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 3);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 4);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 5);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 6);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 7);

-- LIBROS
INSERT INTO materiales(titulo, editorial) VALUES ('Writing Effective Use Cases', 'Addyson-Wesley');
INSERT INTO libros(mid, isbn, autor) VALUES ((SELECT max(mid) FROM materiales), '978-0-201-70225-5', 'Alistair Cockburn');
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 1);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 2);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 3);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 4);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 5);

INSERT INTO materiales(titulo, editorial) VALUES ('Physics of the Impossible', 'Anchor Books');
INSERT INTO libros(mid, isbn, autor, cid) VALUES ((SELECT max(mid) FROM materiales), '978-0-307-27882-1', 'Michio Kaku', (SELECT cid FROM cds JOIN materiales USING (mid) WHERE titulo = 'CD1'));
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 1);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 2);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 3);

-- REVISTAS
INSERT INTO materiales(titulo, editorial) VALUES ('Newsweek', 'Newsweek Inc.');
INSERT INTO revistas(mid, periodicidad, cid) VALUES ((SELECT max(mid) FROM materiales), 'Quincenal', (SELECT cid FROM cds JOIN materiales USING (mid) WHERE titulo = 'CD5'));
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 1);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 2);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 3);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 4);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 5);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 6);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 7);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 8);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 9);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 10);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 11);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 12);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 13);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 14);
INSERT INTO ejemplares(mid, numero) VALUES ((SELECT max(mid) FROM materiales), 15);
