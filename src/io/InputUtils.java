package io;

import domain.Cliente;
import domain.Evento;
import domain.Libro;
import domain.Recurso;
import domain.Review;
import domain.Sala;
import domain.SalaEventos;
import domain.SalaPrivada;
import domain.SalaPublica;
import domain.TipoEvento;
import domain.Usuario;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;

import dbmejorada.UsuarioDTO;
import utils.Utils;

public class InputUtils {
    	public static ArrayList<Libro> cargarLibros() {
		ArrayList<Libro> listaLibros = new ArrayList<Libro>();
		File f = new File("resources/data/books.csv");
		
		try {
			Scanner sc = new Scanner(f);
			
			int contador = 0;
			while (sc.hasNextLine() && contador<35) {
				String linea = sc.nextLine();
				String[] datos = linea.split(";");
				
				try {
					long isbn = Long.parseLong(datos[0]);
					String titulo = datos[2];
	                String autor = datos[4];
	                String genero = datos[5];
	                String sinopsis = datos[7];
	                int fecha_publicacion = Integer.parseInt(datos[8]);
	                int rating = (Integer.parseInt(datos[9]) * 2) / 100;
	                int numeroDePaginas = Integer.parseInt(datos[10]);

	                ImageIcon foto = Utils.loadImage("books/" + isbn + ".jpg", 98, 151);
	                ArrayList<Review> reviews = new ArrayList<>();

	                Libro libro = new Libro(isbn, titulo, autor, numeroDePaginas, sinopsis, genero, rating, fecha_publicacion, foto, reviews);
	                listaLibros.add(libro);
	                contador++;

				} catch (Exception e) {
				    System.err.println("Error al convertir un número en la línea: " + linea);
				    e.printStackTrace();
				}
				
//				String titulo, String autor, int numeroDePaginas, Image foto, int id, String sinopsis,
//				ArrayList<Review> reviews, Genero genero, int rating
				
				
			}

			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: Archivo no encontrado");
		}

	
		
		return listaLibros;
	}	

	public static ArrayList<Sala> cargarSalas() {
		ArrayList<Sala> result = new ArrayList<>();

		File file = new File("resources/data/salas.csv");
		try {
			Scanner scanner = new Scanner(file);

			scanner.nextLine();
			while(scanner.hasNextLine()) {
				Sala sala = null;
				String line = scanner.nextLine();
				String[] fields = line.split(";");

				int id = Integer.parseInt(fields[0]);
				int piso = Integer.parseInt(fields[1]);
				int capacidad = Integer.parseInt(fields[2]);
				
					
				if (fields[3].equals("PUBLICA")) {
					sala = new SalaPublica(capacidad, id, piso);
				} 

				if (fields[3].equals("PRIVADA")) {
					ArrayList<Recurso> recursos = new ArrayList<>();
					String[] recursosArray = fields[4].split(",");

					for (int i = 0; i < recursosArray.length; i++) {
						switch (recursosArray[i]) {
							case "PIZARRA":
							recursos.add(Recurso.PIZARRA);
							break;

							case "PROYECTOR":
							recursos.add(Recurso.PROYECTOR);
							break;

							case "ORDENADORES":
							recursos.add(Recurso.ORDENADORES);
							break;
						}
					}

					sala = new SalaPrivada(capacidad, id, piso, recursos, new ArrayList<>());
				}

				if (fields[3].equals("EVENTOS")) {
					sala = new SalaEventos(capacidad, id, piso, new Evento());
				}
				
				result.add(sala);
				
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: No se ha encontrado el archivo");
		}

		return result;
	}

	public static ArrayList<Evento> cargarEventos(ArrayList<SalaEventos> salasEventos) {
		ArrayList<Evento> result = new ArrayList<>();

		File file = new File("resources/data/eventos.csv");
		try {
			Scanner scanner = new Scanner(file);

			System.out.println(scanner.nextLine());
			while(scanner.hasNextLine()) {
				Evento evento;
				String line = scanner.nextLine();
				String[] fields = line.split(";");

				int idEvento = Integer.parseInt(fields[0]);
				String tipoString = fields[1];
				String titulo = fields[2];
				LocalDate fecha = LocalDate.parse(fields[3], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
				int hora = Integer.parseInt(fields[4]);
				int idSala = Integer.parseInt(fields[5]);

				TipoEvento tipoEvento = null;
				switch (tipoString) {
					case "CHARLA":
						tipoEvento = TipoEvento.CHARLA;
						break;

					case "DEBATE":
						tipoEvento = TipoEvento.DEBATE;
						break;

					case "SEMINARIO":
						tipoEvento = TipoEvento.SEMINARIO;
						break;

					case "TALLER":
						tipoEvento = TipoEvento.TALLER;
						break;
				}

				SalaEventos salaEvento = null;
				for (SalaEventos sala : salasEventos) {
					if (sala.getId() == idSala) {
						salaEvento = sala;
						break;
					}
				}
				evento = new Evento(idEvento, titulo, tipoEvento, new ArrayList<>(), salaEvento, fecha, hora);

				result.add(evento);
				
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: No se ha encontrado el archivo");
		}

		return result;
	}
	public static void cargarReviews(ArrayList<Libro> libros, ArrayList<Usuario> usuarios) {
        File file = new File("resources/data/reviews.csv");
        
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(";");
                
                try {
                    String isbn = datos[0];  
                    String dni = datos[1];
                    String comentario = datos[2]; 
                    int rating = Integer.parseInt(datos[3]);
                   
                    Libro libro = buscarLibroPorIsbn(libros, isbn);
                    
                    if (libro != null) {
                       
                        Usuario usuario = buscarUsuarioPorDni(usuarios, dni);
                        
                        if (usuario != null && usuario instanceof Cliente) {
                           
                        	UsuarioDTO usuarioDTO = new UsuarioDTO();
                        	
                        	usuarioDTO.setAdmin(false);
                        	usuarioDTO.setAmonestaciones(((Cliente) usuario).getAmonestaciones());
                        	usuarioDTO.setDni(((Cliente) usuario).getDni());
                        	usuarioDTO.setNombre(((Cliente) usuario).getNombre());
                        	
                            Review review = new Review(libro, usuarioDTO, comentario, rating);
                            
                            // Añadir la review al libro
                            libro.agregarReview(review);
                        } else {
                            System.err.println("Usuario no encontrado o no es un cliente para DNI: " + dni);
                        }
                    } else {
                        System.err.println("Libro no encontrado con ISBN: " + isbn);
                    }
                    
                } catch (Exception e) {
                    System.err.println("Error al procesar la línea: " + linea);
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: Archivo de reviews no encontrado");
        }
    }

    // Método para buscar un libro por su ISBN
	public static Libro buscarLibroPorIsbn(ArrayList<Libro> libros, String isbn) {
	    for (Libro libro : libros) {
	        if (String.valueOf(libro.getIsbn()).equals(isbn)) {
	            return libro;
	        }
	    }
	    return null; // Si no encuentra el libro
	}
    // Método para buscar un usuario por su DNI
    public static Usuario buscarUsuarioPorDni(ArrayList<Usuario> usuarios, String dni) {
        for (Usuario usuario : usuarios) {
            if (usuario.getDni().equals(dni)) {
                return usuario;
            }
        }
        return null; // Si no encuentra el usuario
    }

	
    public static ArrayList<Usuario> cargarUsuarios() {
        ArrayList<Usuario> result = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        
        File file = new File("resources/data/usuarios.csv");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(";");
                
                try {
                    String dni = datos[0];
                    String nombre = datos[1];
                    String email = datos[2];
                    LocalDateTime fechaCreacion = LocalDateTime.parse(datos[3], formatter);
                    String contrasena = datos[4];
                    
                    //Inicializo con valor por defecto
                    ArrayList<Libro> historial = new ArrayList<>(); 
                    ArrayList<Review> listaReviews = new ArrayList<>(); 
                    int amonestaciones = 0; 

                    
                    Usuario usuario = new Cliente(dni, nombre, email, fechaCreacion, contrasena, historial, listaReviews, amonestaciones);
                    result.add(usuario);
                    
                } catch (Exception e) {
                    System.err.println("Error al procesar la línea: " + linea);
                    e.printStackTrace();
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: Archivo no encontrado");
        }
        
        return result;
    }
}
