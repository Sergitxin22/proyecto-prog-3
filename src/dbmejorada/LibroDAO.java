package dbmejorada;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.Cliente;
import domain.Libro;
import main.Main;

public class LibroDAO implements LibroDAOInterface{
	
	private Connection conexionBD;
	private Logger logger;
	
	public LibroDAO() {
		this.conexionBD = Main.getConexionBD();
		this.logger = Main.getLogger();
	}
	

	@Override
	public boolean addLibro(Libro libro) {
	    String insertSQLLibro = "INSERT INTO Libro (isbn, titulo, autor, numero_de_paginas, sinopsis, genero, rating, fecha_publicacion) VALUES (?,?,?,?,?,?,?,?)";

	    try (PreparedStatement preparedStmtLibro = conexionBD.prepareStatement(insertSQLLibro)) {
	        preparedStmtLibro.setLong(1, libro.getIsbn());
	        preparedStmtLibro.setString(2, libro.getTitulo());
	        preparedStmtLibro.setString(3, libro.getAutor());
	        preparedStmtLibro.setInt(4, libro.getNumeroDePaginas());
	        preparedStmtLibro.setString(5, libro.getSinopsis());
	        preparedStmtLibro.setString(6, libro.getGenero());
	        preparedStmtLibro.setInt(7, libro.getRating());

	        if (libro.getFechaPublicacion() != 0) {
	            // Crear un LocalDate con el año de fechaPublicacion (usando 1 de enero como día predeterminado)
	            LocalDate fecha = LocalDate.of(libro.getFechaPublicacion(), 1, 1);
	            preparedStmtLibro.setDate(8, java.sql.Date.valueOf(fecha));
	        } else {
	            preparedStmtLibro.setDate(8, null);
	        }

	        preparedStmtLibro.executeUpdate();
	        return true;

	    } catch (SQLException e) {
	        if (logger != null) {
	            logger.log(Level.SEVERE, "Error al añadir el libro: ", e);
	        }
	        return false;
	    }
	}

	
	@Override
	public LibroDTO getLibro(long isbn) {
	    LibroDTO libro = null;
	    
	    String selectSQL = "SELECT * FROM Libro WHERE isbn = ?";
	    PreparedStatement preparedStmt;
	    try {
	        preparedStmt = conexionBD.prepareStatement(selectSQL);
	        preparedStmt.setLong(1, isbn);
	        try (ResultSet rs = preparedStmt.executeQuery()) {
	            while (rs.next()) {
	                libro = new LibroDTO();
	                libro.setIsbn(rs.getLong("isbn"));
	                libro.setTitulo(rs.getString("titulo"));
	                libro.setAutor(rs.getString("autor"));
	                libro.setNumeroDePaginas(rs.getInt("número de páginas"));
	                libro.setSinopsis(rs.getString("sinopsis"));
	                libro.setGenero(rs.getString("género"));
	                libro.setRating(rs.getInt("rating"));

	                Date fechaPublicacionDate = rs.getDate("fecha publicación");
	                if (fechaPublicacionDate != null) {
	                    libro.setFechaPublicacion(fechaPublicacionDate.toLocalDate());
	                } else {
	                    libro.setFechaPublicacion(null); // Fechas nulas
	                }

	                System.out.println("Libro recuperado correctamente");
	                preparedStmt.close();
	            }
	        }
	    } catch (SQLException e) {
	        if (logger != null) {
	            logger.log(Level.SEVERE, "Error al recuperar el libro: ", e);
	            return libro;
	        }
	    }
	    return libro;
	}

	@Override
	public void añadirReserva(long isbn, int diasDevolucion, Cliente cliente) {
		String insertSQL = "INSERT INTO ReservaLibro(fecha_inicio, fecha_fin, isbn, dni_cliente";
		  PreparedStatement preparedStmt;
			try {
				preparedStmt = conexionBD.prepareStatement(insertSQL);
				preparedStmt.setString(1, LocalDateTime.now().toString());
				preparedStmt.setString(1, LocalDateTime.now().plusDays(diasDevolucion).toString());
				preparedStmt.setLong(1, isbn);
				preparedStmt.setString(1, cliente.getDni());
				 		
				preparedStmt.executeUpdate();
				preparedStmt.close();
			} catch(SQLException e) {
				if (logger != null) {
					logger.log(Level.SEVERE, "Error al realizar la reserva: ", e);
				}
			}

		
			
	}
	
	@Override
	public ArrayList<LibroDTO>getHistorialByCliente(String dniCliente){
		ArrayList<LibroDTO> historialCliente = new ArrayList<LibroDTO>();
		
		String insertSQL = "SELECT * FROM ReservaLibro WHERE dni_cliente=?;";
        PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setString(1, dniCliente);
			
	        try (ResultSet rs = preparedStmt.executeQuery()) {
                
                while(rs.next()) {
                	LibroDTO libro = null;
                	long isbn = rs.getLong("isbn");
                	String titulo = rs.getString ("titulo");
                	String autor = rs.getString("autor");
                	int numeroDePaginas = rs.getInt("numero_paginas");
                	String sinopsis = rs.getString("sinopsis");
                	String genero = rs.getString("genero");
                	int rating = rs.getInt("rating");
                	String fechaPublicacion = rs.getString("fecha_publicacion");
                	
                	libro = new LibroDTO(isbn, titulo, autor, numeroDePaginas, sinopsis, genero, rating, LocalDate.parse(fechaPublicacion));
                	historialCliente.add(libro);
                }
                System.out.println("Historial cliente sin fallos");
                return historialCliente;
	        }
		
		}catch(SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar el bhistorial del cliente: ", e);
			}
			return historialCliente;
				
		}
	}
	
	
	@Override
	public boolean updateLibro(LibroDTO libro) {
		try {
            String insertSQL = "UPDATE Libro SET titulo=?,autor=?,numero_de_paginas=?,sinopsis=?, genero=?, rating=?, fecha_publicacion=? WHERE isbn=?;";
            PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
            preparedStmt.setLong(1, libro.getIsbn());
            preparedStmt.setString(2, libro.getTitulo());
            preparedStmt.setString(3, libro.getAutor());
            preparedStmt.setInt(4, libro.getNumeroDePaginas());
            preparedStmt.setString(5, libro.getSinopsis());
            preparedStmt.setString(6, libro.getGenero());
            preparedStmt.setInt(7, libro.getRating());
            preparedStmt.setString(8, libro.getFechaPublicacion().toString());
            
            int filas = preparedStmt.executeUpdate();
            System.out.println("Filas modificadas: " + filas);

            return (filas > 0) ? true : false;
            
		}catch(SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al actualizar la reserva de la sala privada: ", e);
            return false;
		}
	}
	
	@Override
	public boolean deleteLibroByIsbn(long isbnLibro) {
		try {
            String insertSQL = "DELETE FROM Libro where isbn = ?;";
            PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
            preparedStmt.setLong(1,isbnLibro);
            int filas = preparedStmt.executeUpdate();
            System.out.println("Filas modificadas: " + filas);

            return (filas > 0) ? true : false;
            
		}catch(SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al eliminar la reserva de la sala privada: ", e);
            return false;
			
		}
	}


	@Override
	public boolean libroLeidoByDniCliente(String dniCliente, Long isbn) {
	    String query = "SELECT count(*) AS veces_leido FROM ReservaLibro WHERE dni_cliente = ? AND isbn = ?";

	    try (PreparedStatement preparedStmt = conexionBD.prepareStatement(query)) {
	       
	        preparedStmt.setString(1, dniCliente);
	        preparedStmt.setLong(2, isbn);

	        try (ResultSet rs = preparedStmt.executeQuery()) {
	            if (rs.next()) {
	                // Obtener el número de veces que el cliente ha leído el libro
	                int vecesLeido = rs.getInt("veces_leido");

	                // Si el libro ha sido leído al menos una vez, retornar true, de lo contrario false
	                return vecesLeido > 0;
	            }
	        }
	    } catch (SQLException e) {
	        if (logger != null) {
	            logger.log(Level.SEVERE, "Error al verificar si el cliente ha leído el libro: ", e);
	        }
	    }
	    return false;
	}
	
}
