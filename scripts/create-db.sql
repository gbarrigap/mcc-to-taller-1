DROP TABLE IF EXISTS libro;

CREATE TABLE libro (
    mid             INTEGER PRIMARY KEY AUTOINCREMENT,
    numero_copia    INTEGER,
    titulo          TEXT,
    editorial       TEXT,
    prestado        TEXT,
    autor           TEXT,
    isbn            INTEGER
);
