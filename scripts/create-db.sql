DROP TABLE IF EXISTS materiales_prestamos;
DROP TABLE IF EXISTS prestamos;
DROP TABLE IF EXISTS ejemplares;
DROP TABLE IF EXISTS libros;
DROP TABLE IF EXISTS cds;
DROP TABLE IF EXISTS revistas;
DROP TABLE IF EXISTS materiales;
DROP TABLE IF EXISTS usuarios;

CREATE TABLE materiales (
    mid             INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo          TEXT NOT NULL,
    editorial       TEXT
);

CREATE TABLE libros (
    mid             INTEGER REFERENCES material(mid),
    isbn            INTEGER,
    autor           TEXT NOT NULL
);

CREATE TABLE cds (
    mid             INTEGER REFERENCES material(mid)
);

CREATE TABLE revistas (
    mid             INTEGER REFERENCES material(mid),
    periodicidad    INTEGER NOT NULL
);

CREATE TABLE ejemplares (
    eid             INTEGER PRIMARY KEY AUTOINCREMENT,
    mid             INTEGER REFERENCES material(mid),
    numero          INTEGER NOT NULL
);

CREATE TABLE usuarios (
    uid             INTEGER PRIMARY KEY AUTOINCREMENT,
    rut             INTEGER NOT NULL,
    nombre          TEXT NOT NULL
);

CREATE TABLE prestamos (
    pid             INTEGER PRIMARY KEY AUTOINCREMENT,
    uid             INTEGER REFERENCES usuarios(uid),
    vigente         INTEGER NOT NULL -- 0 = FALSE; 1 = TRUE.
);

CREATE TABLE materiales_prestamos (
    pid             INTEGER REFERENCES prestamos(pid),
    eid             INTEGER REFERENCES ejemplares(eid)
);
