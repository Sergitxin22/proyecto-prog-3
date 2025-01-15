BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Admin" (
	"dni"	TEXT,
	PRIMARY KEY("dni"),
	FOREIGN KEY("dni") REFERENCES "Usuario"("dni") ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS "AsistenciaEvento" (
	"id"	INTEGER,
	"dni_asistente"	INTEGER NOT NULL,
	"id_evento"	INTEGER NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("dni_asistente") REFERENCES "Usuario"("dni") ON DELETE CASCADE,
	FOREIGN KEY("id_evento") REFERENCES "Evento"("id") ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS "Cliente" (
	"dni"	TEXT,
	"amonestaciones"	INTEGER,
	PRIMARY KEY("dni"),
	FOREIGN KEY("dni") REFERENCES "Usuario"("dni") ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS "Evento" (
	"id"	INTEGER,
	"tItulo"	TEXT,
	"id_tipo_evento"	INTEGER,
	"id_sala"	INTEGER,
	"fecha"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("id_sala") REFERENCES "Sala"("id") ON DELETE CASCADE,
	FOREIGN KEY("id_tipo_evento") REFERENCES "TipoEvento"("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "Libro" (
	"isbn"	INTEGER NOT NULL UNIQUE,
	"titulo"	TEXT NOT NULL,
	"autor"	TEXT,
	"numPaginas"	INTEGER,
	"sinopsis"	TEXT,
	"genero"	TEXT,
	"rating"	INTEGER,
	"fecha_publicacion"	INTEGER,
	PRIMARY KEY("isbn")
);
CREATE TABLE IF NOT EXISTS "LogAccion" (
	"id"	INTEGER,
	"fecha"	TEXT NOT NULL,
	"descripcion"	TEXT NOT NULL,
	"dni_admin"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("dni_admin") REFERENCES "Admin"("dni") ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS "Recurso" (
	"id"	INTEGER,
	"descripcion"	TEXT NOT NULL UNIQUE,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "ReservaLibro" (
	"id"	INTEGER,
	"fecha_inicio"	TEXT,
	"fecha_fin"	TEXT,
	"isbn_libro"	INTEGER NOT NULL,
	"dni_cliente"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("dni_cliente") REFERENCES "Cliente"("dni") ON DELETE CASCADE,
	FOREIGN KEY("isbn_libro") REFERENCES "Libro"("isbn") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "ReservaSalaPrivada" (
	"id"	INTEGER,
	"fecha_entrada"	TEXT,
	"fecha_salida"	TEXT,
	"fecha_reserva"	TEXT,
	"dni_cliente"	TEXT,
	"id_sala"	INTEGER,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("dni_cliente") REFERENCES "Cliente"("dni") ON DELETE CASCADE,
	FOREIGN KEY("id_sala") REFERENCES "Sala"("id") ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS "ReservaSalaPublica" (
	"id"	INTEGER,
	"fecha_entrada"	TEXT,
	"dni_cliente"	TEXT,
	"numero_bloque"	INTEGER,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("dni_cliente") REFERENCES "Cliente"("dni") ON DELETE CASCADE,
	CHECK("numero_bloque" < 250)
);
CREATE TABLE IF NOT EXISTS "Review" (
	"id"	INTEGER,
	"comentario"	TEXT,
	"rating"	INTEGER,
	"isbn_libro"	INTEGER NOT NULL,
	"dni_cliente"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("dni_cliente") REFERENCES "Cliente"("dni") ON DELETE CASCADE,
	FOREIGN KEY("isbn_libro") REFERENCES "Libro"("isbn") ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS "Sala" (
	"id"	INTEGER,
	"piso"	INTEGER,
	"capacidad"	INTEGER,
	"tipo"	INTEGER NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("tipo") REFERENCES "TipoSala"("id") ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS "SalaPrivadaRecurso" (
	"id"	INTEGER,
	"id_sala"	INTEGER,
	"id_recurso"	INTEGER,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("id_recurso") REFERENCES "Recurso"("id") ON DELETE CASCADE,
	FOREIGN KEY("id_sala") REFERENCES "Sala"("id") ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS "TipoEvento" (
	"id"	INTEGER,
	"descripcion"	TEXT UNIQUE,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "TipoSala" (
	"id"	INTEGER,
	"tipo"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Usuario" (
	"dni"	TEXT NOT NULL UNIQUE,
	"nombre"	TEXT,
	"email"	TEXT,
	"fechaCreacion"	TEXT,
	"contrasena"	TEXT,
	PRIMARY KEY("dni")
);

INSERT INTO "Usuario" ("dni","nombre","email","fechaCreacion","contrasena") VALUES ('00000000A','Sergio','sergio@si.es','2024-12-01T22:52:05.597527100','hola'),
 ('11111111B','Aroa','aroa@no.com','2024-12-01T22:52:05.601522700','aroa2003');
 INSERT INTO "Admin" ("dni") VALUES ('11111111B');
INSERT INTO "Cliente" ("dni","amonestaciones") VALUES ('00000000A',0);
COMMIT;
