package io;

import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.Main;

public class CrearBBDD {
    private Connection conexionBD;
    private Logger logger;

    public CrearBBDD() {
        this.conexionBD = Main.getConexionBD();
        this.logger = Main.getLogger();

        // Verificar si la conexión es válida
        if (conexionBD == null) {
            System.err.println("La conexión a la base de datos no está disponible.");
            return;
        }

        // Script SQL dividido en comandos individuales
        String sql = """
        CREATE TABLE IF NOT EXISTS "Admin" (
            "dni" TEXT,
            PRIMARY KEY("dni"),
            FOREIGN KEY("dni") REFERENCES "Usuario"("dni") ON DELETE CASCADE
        );
        CREATE TABLE IF NOT EXISTS "AsistenciaEvento" (
            "id" INTEGER PRIMARY KEY AUTOINCREMENT,
            "dni_asistente" INTEGER NOT NULL,
            "id_evento" INTEGER NOT NULL,
            FOREIGN KEY("dni_asistente") REFERENCES "Usuario"("dni") ON DELETE CASCADE,
            FOREIGN KEY("id_evento") REFERENCES "Evento"("id") ON DELETE CASCADE
        );
        CREATE TABLE IF NOT EXISTS "Cliente" (
            "dni" TEXT,
            "amonestaciones" INTEGER,
            PRIMARY KEY("dni"),
            FOREIGN KEY("dni") REFERENCES "Usuario"("dni") ON DELETE CASCADE
        );
        CREATE TABLE IF NOT EXISTS "Evento" (
            "id" INTEGER PRIMARY KEY AUTOINCREMENT,
            "tItulo" TEXT,
            "id_tipo_evento" INTEGER,
            "id_sala" INTEGER,
            "fecha" TEXT,
            FOREIGN KEY("id_sala") REFERENCES "Sala"("id") ON DELETE CASCADE,
            FOREIGN KEY("id_tipo_evento") REFERENCES "TipoEvento"("id") ON DELETE CASCADE
        );
        CREATE TABLE IF NOT EXISTS "Libro" (
            "isbn" INTEGER NOT NULL UNIQUE,
            "titulo" TEXT NOT NULL,
            "autor" TEXT,
            "numPaginas" INTEGER,
            "sinopsis" TEXT,
            "genero" TEXT,
            "rating" INTEGER,
            "fecha_publicacion" INTEGER,
            PRIMARY KEY("isbn")
        );
        CREATE TABLE IF NOT EXISTS "LogAccion" (
            "id" INTEGER PRIMARY KEY AUTOINCREMENT,
            "fecha" TEXT NOT NULL,
            "descripcion" TEXT NOT NULL,
            "dni_admin" TEXT NOT NULL,
            FOREIGN KEY("dni_admin") REFERENCES "Admin"("dni") ON DELETE CASCADE
        );
        CREATE TABLE IF NOT EXISTS "Recurso" (
            "id" INTEGER PRIMARY KEY AUTOINCREMENT,
            "descripcion" TEXT NOT NULL UNIQUE
        );
        CREATE TABLE IF NOT EXISTS "ReservaLibro" (
            "id" INTEGER PRIMARY KEY AUTOINCREMENT,
            "fecha_inicio" TEXT,
            "fecha_fin" TEXT,
            "isbn_libro" INTEGER NOT NULL,
            "dni_cliente" TEXT NOT NULL,
            FOREIGN KEY("dni_cliente") REFERENCES "Cliente"("dni") ON DELETE CASCADE,
            FOREIGN KEY("isbn_libro") REFERENCES "Libro"("isbn") ON DELETE CASCADE
        );
        CREATE TABLE IF NOT EXISTS "ReservaSalaPrivada" (
            "id" INTEGER PRIMARY KEY AUTOINCREMENT,
            "fecha_entrada" TEXT,
            "fecha_salida" TEXT,
            "fecha_reserva" TEXT,
            "dni_cliente" TEXT,
            "id_sala" INTEGER,
            FOREIGN KEY("dni_cliente") REFERENCES "Cliente"("dni") ON DELETE CASCADE,
            FOREIGN KEY("id_sala") REFERENCES "Sala"("id") ON DELETE CASCADE
        );
        CREATE TABLE IF NOT EXISTS "ReservaSalaPublica" (
            "id" INTEGER PRIMARY KEY AUTOINCREMENT,
            "fecha_entrada" TEXT,
            "dni_cliente" TEXT,
            "numero_bloque" INTEGER,
            FOREIGN KEY("dni_cliente") REFERENCES "Cliente"("dni") ON DELETE CASCADE,
            CHECK("numero_bloque" < 250)
        );
        CREATE TABLE IF NOT EXISTS "Review" (
            "id" INTEGER PRIMARY KEY AUTOINCREMENT,
            "comentario" TEXT,
            "rating" INTEGER,
            "isbn_libro" INTEGER NOT NULL,
            "dni_cliente" TEXT NOT NULL,
            FOREIGN KEY("dni_cliente") REFERENCES "Cliente"("dni") ON DELETE CASCADE,
            FOREIGN KEY("isbn_libro") REFERENCES "Libro"("isbn") ON DELETE CASCADE
        );
        CREATE TABLE IF NOT EXISTS "Sala" (
            "id" INTEGER PRIMARY KEY AUTOINCREMENT,
            "piso" INTEGER,
            "capacidad" INTEGER,
            "tipo" INTEGER NOT NULL,
            FOREIGN KEY("tipo") REFERENCES "TipoSala"("id") ON DELETE CASCADE
        );
        CREATE TABLE IF NOT EXISTS "SalaPrivadaRecurso" (
            "id" INTEGER PRIMARY KEY AUTOINCREMENT,
            "id_sala" INTEGER,
            "id_recurso" INTEGER,
            FOREIGN KEY("id_recurso") REFERENCES "Recurso"("id") ON DELETE CASCADE,
            FOREIGN KEY("id_sala") REFERENCES "Sala"("id") ON DELETE CASCADE
        );
        CREATE TABLE IF NOT EXISTS "TipoEvento" (
            "id" INTEGER PRIMARY KEY AUTOINCREMENT,
            "descripcion" TEXT UNIQUE
        );
        CREATE TABLE IF NOT EXISTS "TipoSala" (
            "id" INTEGER PRIMARY KEY AUTOINCREMENT,
            "tipo" TEXT NOT NULL
        );
        CREATE TABLE IF NOT EXISTS "Usuario" (
            "dni" TEXT NOT NULL UNIQUE,
            "nombre" TEXT,
            "email" TEXT,
            "fechaCreacion" TEXT,
            "contrasena" TEXT,
            PRIMARY KEY("dni")
        );
        INSERT INTO "Usuario" ("dni","nombre","email","fechaCreacion","contrasena") 
        VALUES 
            ('00000000A','Sergio','sergio@si.es','2024-12-01','hola'),
            ('11111111B','Aroa','aroa@no.com','2024-12-01','aroa2003');
		INSERT INTO "Admin" ("dni") VALUES ('11111111B');
        INSERT INTO "Cliente" ("dni","amonestaciones") VALUES ('00000000A',0);
        """;

        // Ejecutar las consultas SQL
        String[] scripts = sql.split(";");
        try (Statement stmt = conexionBD.createStatement()) {
            for (String script : scripts) {
                if (!script.trim().isEmpty()) {
                    stmt.execute(script.trim());
                }
            }
            System.out.println("Tablas creadas exitosamente.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al crear las tablas: ", e);
        }
    }
}
