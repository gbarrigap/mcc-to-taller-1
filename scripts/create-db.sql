DROP TABLE IF EXISTS detalles_prestamos;
DROP TABLE IF EXISTS prestamos;
DROP TABLE IF EXISTS copias;
DROP TABLE IF EXISTS libros;
DROP TABLE IF EXISTS revistas;
DROP TABLE IF EXISTS cds;
DROP TABLE IF EXISTS materiales;
DROP TABLE IF EXISTS usuarios;

CREATE TABLE materiales (
    mid             INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo          TEXT NOT NULL,
    editorial       TEXT NOT NULL
);

CREATE TABLE cds (
    cid             INTEGER PRIMARY KEY AUTOINCREMENT,
    mid             INTEGER NOT NULL REFERENCES materiales(mid) ON DELETE CASCADE
);

CREATE TABLE libros (
    lid             INTEGER PRIMARY KEY AUTOINCREMENT,
    mid             INTEGER NOT NULL REFERENCES materiales(mid) ON DELETE CASCADE,
    cid             INTEGER NOT NULL REFERENCES cds(cid)        ON DELETE SET NULL,
    isbn            TEXT NOT NULL UNIQUE,
    autor           TEXT NOT NULL
);

CREATE TABLE revistas (
    rid             INTEGER PRIMARY KEY AUTOINCREMENT,
    mid             INTEGER NOT NULL REFERENCES materiales(mid) ON DELETE CASCADE,
    cid             INTEGER NOT NULL REFERENCES cds(cid)        ON DELETE SET NULL,
    periodicidad    TEXT NOT NULL CHECK (periodicidad IN ('Quincenal', 'Mensual', 'Trimestral', 'Semanal'))
    
);

CREATE TABLE copias (
    mid             INTEGER NOT NULL REFERENCES materiales(mid) ON DELETE CASCADE,
    numero          INTEGER NOT NULL,
    
    PRIMARY KEY (mid, numero)
);

CREATE TABLE usuarios (
    uid             INTEGER PRIMARY KEY AUTOINCREMENT,
    rut             INTEGER NOT NULL,
    nombre          TEXT NOT NULL
);

CREATE TABLE prestamos (
    pid             INTEGER PRIMARY KEY AUTOINCREMENT,
    uid             INTEGER NOT NULL REFERENCES usuarios(uid) ON DELETE CASCADE,
    vigente         INTEGER NOT NULL CHECK (vigente IN (0, 1)) -- 0 = FALSE; 1 = TRUE.
);

CREATE TABLE detalles_prestamos (
    pid             INTEGER NOT NULL REFERENCES prestamos(pid) ON DELETE CASCADE,
    mid             INTEGER NOT NULL,
    numero          INTEGER NOT NULL,

    PRIMARY KEY (pid, mid, numero),
    FOREIGN KEY (mid, numero) REFERENCES copias(mid, numero) ON DELETE CASCADE
);
