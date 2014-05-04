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

CREATE TABLE cds (
    cid             INTEGER PRIMARY KEY AUTOINCREMENT,
    mid             INTEGER REFERENCES material(mid)
);

CREATE TABLE libros (
    lid             INTEGER PRIMARY KEY AUTOINCREMENT,
    mid             INTEGER REFERENCES materiales(mid),
    cid             INTEGER REFERENCES cds(cid),
    isbn            INTEGER,
    autor           TEXT NOT NULL
);

CREATE TABLE revistas (
    rid             INTEGER PRIMARY KEY AUTOINCREMENT,
    mid             INTEGER REFERENCES materiales(mid),
    cid             INTEGER REFERENCES cds(cid),
    periodicidad    TEXT NOT NULL CHECK (periodicidad IN ('Quincenal', 'Mensual', 'Trimestral'))
    
);

CREATE TABLE ejemplares (
    eid             INTEGER PRIMARY KEY AUTOINCREMENT,
    mid             INTEGER REFERENCES materiales(mid),
    numero          INTEGER NOT NULL,
    
    UNIQUE (mid, numero)
);

CREATE TABLE usuarios (
    uid             INTEGER PRIMARY KEY AUTOINCREMENT,
    rut             INTEGER NOT NULL,
    nombre          TEXT NOT NULL
);

CREATE TABLE prestamos (
    pid             INTEGER PRIMARY KEY AUTOINCREMENT,
    uid             INTEGER REFERENCES usuarios(uid),
    vigente         INTEGER NOT NULL CHECK (vigente IN (0, 1)) -- 0 = FALSE; 1 = TRUE.
);

CREATE TABLE materiales_prestamos (
    pid             INTEGER REFERENCES prestamos(pid),
    eid             INTEGER REFERENCES ejemplares(eid),

    PRIMARY KEY (pid, eid)
);
