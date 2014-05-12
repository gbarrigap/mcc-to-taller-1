DROP TABLE IF EXISTS materiales_prestamos;
DROP TABLE IF EXISTS prestamos;
DROP TABLE IF EXISTS ejemplares;
DROP TABLE IF EXISTS libros;
DROP TABLE IF EXISTS revistas;
DROP TABLE IF EXISTS cds;
DROP TABLE IF EXISTS materiales;
DROP TABLE IF EXISTS usuarios;

CREATE TABLE materiales (
    mid             INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo          TEXT NOT NULL,
    editorial       TEXT
);

CREATE TABLE cds (
    cid             INTEGER PRIMARY KEY AUTOINCREMENT,
    mid             INTEGER REFERENCES materiales(mid) ON DELETE CASCADE
);

CREATE TABLE libros (
    lid             INTEGER PRIMARY KEY AUTOINCREMENT,
    mid             INTEGER REFERENCES materiales(mid) ON DELETE CASCADE,
    cid             INTEGER REFERENCES cds(cid) ON DELETE SET NULL,
    isbn            TEXT UNIQUE,
    autor           TEXT NOT NULL
);

CREATE TABLE revistas (
    rid             INTEGER PRIMARY KEY AUTOINCREMENT,
    mid             INTEGER REFERENCES materiales(mid) ON DELETE CASCADE,
    cid             INTEGER REFERENCES cds(cid) ON DELETE SET NULL,
    periodicidad    TEXT NOT NULL CHECK (periodicidad IN ('Quincenal', 'Mensual', 'Trimestral', 'Semanal'))
    
);

CREATE TABLE ejemplares (
    eid             INTEGER PRIMARY KEY AUTOINCREMENT,
    mid             INTEGER REFERENCES materiales(mid) ON DELETE CASCADE,
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
    uid             INTEGER REFERENCES usuarios(uid) ON DELETE CASCADE,
    vigente         INTEGER NOT NULL CHECK (vigente IN (0, 1)) -- 0 = FALSE; 1 = TRUE.
);

CREATE TABLE materiales_prestamos (
    pid             INTEGER REFERENCES prestamos(pid) ON DELETE CASCADE,
    eid             INTEGER REFERENCES ejemplares(eid) ON DELETE CASCADE,

    PRIMARY KEY (pid, eid)
);
