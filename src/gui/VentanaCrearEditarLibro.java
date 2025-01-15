package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import dbmejorada.LibroDTO;
import domain.Libro;
import domain.LogAccion;
import domain.Usuario;
import main.Main;

public class VentanaCrearEditarLibro extends JFrame {

	private static final long serialVersionUID = 1L;
	private Libro libro = new Libro();
	
	private Usuario usuario = Main.getUsuario();
	
	private long isbn;
	private final long isbnAntiguo;
	private String titulo;
	private String autor;
	private int numeroDePaginas;
	private String sinopsis;
	private String genero;
	private int rating;
	private int añoPublicacion;
	private ImageIcon foto;
	
	private JTextField tfISBN = new JTextField();
	private JTextField tfTitulo = new JTextField();
	private JTextField tfAutor = new JTextField();
	private JTextField tfNumeroPaginas = new JTextField();
	private JTextArea taSinopsis = new JTextArea();
	private JTextField tfGenero = new JTextField();
	private JTextField tfRating = new JTextField();
	private JTextField tfAñoPublicacion = new JTextField();
	
	private JLabel imageLabel = new JLabel();
	private ImageIcon imagen;
	private File ficheroImagen;

	public VentanaCrearEditarLibro(JFrame previousWindow, Libro libroAEditar) {
		
		isbnAntiguo = libroAEditar.getIsbn();
		
		String titleString = "";
		if (previousWindow instanceof VentanaBiblioteca) {
			titleString = "Crear";
			
		} else {
			
			titleString = "Editar";
			tfISBN.setText(Long.toString(libroAEditar.getIsbn()));
			tfTitulo.setText(libroAEditar.getTitulo());
			tfAutor.setText(libroAEditar.getAutor());
			tfNumeroPaginas.setText(Integer.toString(libroAEditar.getNumeroDePaginas()));
			taSinopsis.setText(libroAEditar.getSinopsis());
			tfGenero.setText(libroAEditar.getGenero());
			tfRating.setText(Integer.toString(libroAEditar.getRating()));
			tfAñoPublicacion.setText(Integer.toString(libroAEditar.getFechaPublicacion()));
			imageLabel.setIcon(libroAEditar.getFoto());
		}
		
		setTitle(titleString + " libro");
		setSize(1280, 720);
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
        	if (previousWindow instanceof VentanaBiblioteca) {
        		new VentanaBiblioteca();
        	} else {
        		new VentanaInformacionRecurso(libroAEditar);
        	}
        	dispose();
        	}
		});
		
		// Texto superior
		JLabel topText = new JLabel(titleString + " libro", SwingConstants.CENTER); // Label con texto centrado
		topText.setFont(new Font("Verdana", Font.BOLD, 32));

		
		// Cuerpo de la ventana
		JPanel body = new JPanel();
		
		JPanel leftBody = new JPanel();
		JPanel rightBody = new JPanel();
		
		rightBody.setLayout(new BoxLayout(rightBody, BoxLayout.Y_AXIS));
		leftBody.setLayout(new BoxLayout(leftBody, BoxLayout.Y_AXIS));
		
		JLabel textISBN = new JLabel("ISBN");
		textISBN.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		JLabel textTitulo = new JLabel("Título");
		textTitulo.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		JLabel textAutor = new JLabel("Autor(a)");
		textAutor.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		JLabel textNumeroPaginas = new JLabel("Número de páginas");
		textNumeroPaginas.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		JLabel textSinopsis = new JLabel("Sinopsis");
		textSinopsis.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		JLabel textGenero = new JLabel("Género");
		textGenero.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		JLabel textRating = new JLabel("Rating");
		textRating.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		JLabel textAñoPublicacion = new JLabel("Año de publicación");
		textAñoPublicacion.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		
		tfISBN.setPreferredSize(new Dimension(125, 25));
		tfTitulo.setPreferredSize(new Dimension(125, 25));
		tfAutor.setPreferredSize(new Dimension(125, 25));
		tfNumeroPaginas.setPreferredSize(new Dimension(125, 25));
		tfGenero.setPreferredSize(new Dimension(125, 25));
		tfRating.setPreferredSize(new Dimension(125, 25));
		tfAñoPublicacion.setPreferredSize(new Dimension(125, 25));
		
		JPanel tfISBNPanel = new JPanel();
		JPanel tfTituloPanel = new JPanel();
		JPanel tfAutorPanel = new JPanel();
		JPanel tfNumeroPaginasPanel = new JPanel();
		JPanel taSinopsisPanel = new JPanel();
		JPanel tfGeneroPanel = new JPanel();
		JPanel tfRatingPanel = new JPanel();
		JPanel tfFechaPanel = new JPanel();
		
		tfISBNPanel.add(tfISBN);
		tfTituloPanel.add(tfTitulo);
		tfAutorPanel.add(tfAutor);
		tfNumeroPaginasPanel.add(tfNumeroPaginas);
		taSinopsisPanel.add(taSinopsis);
		tfGeneroPanel.add(tfGenero);
		tfRatingPanel.add(tfRating);
		tfFechaPanel.add(tfAñoPublicacion);
		
		textISBN.setAlignmentX(CENTER_ALIGNMENT);
		textTitulo.setAlignmentX(CENTER_ALIGNMENT);
		tfISBN.setAlignmentX(CENTER_ALIGNMENT);
		tfTitulo.setAlignmentX(CENTER_ALIGNMENT);
		textAutor.setAlignmentX(CENTER_ALIGNMENT);
		textNumeroPaginas.setAlignmentX(CENTER_ALIGNMENT);
		tfAutor.setAlignmentX(CENTER_ALIGNMENT);
		tfNumeroPaginas.setAlignmentX(CENTER_ALIGNMENT);
		textSinopsis.setAlignmentX(CENTER_ALIGNMENT);
		textGenero.setAlignmentX(CENTER_ALIGNMENT);
		taSinopsis.setAlignmentX(CENTER_ALIGNMENT);
		tfGenero.setAlignmentX(CENTER_ALIGNMENT);
		textRating.setAlignmentX(CENTER_ALIGNMENT);
		textAñoPublicacion.setAlignmentX(CENTER_ALIGNMENT);
		tfRating.setAlignmentX(CENTER_ALIGNMENT);
		tfAñoPublicacion.setAlignmentX(CENTER_ALIGNMENT);
		
		leftBody.add(textISBN);
		leftBody.add(tfISBNPanel);
		leftBody.add(textTitulo);
		leftBody.add(tfTituloPanel);
		leftBody.add(textAutor);
		leftBody.add(tfAutorPanel);
		leftBody.add(textNumeroPaginas);
		leftBody.add(tfNumeroPaginasPanel);
		rightBody.add(textGenero);
		rightBody.add(tfGeneroPanel);
		rightBody.add(textRating);
		rightBody.add(tfRatingPanel);
		rightBody.add(textAñoPublicacion);
		rightBody.add(tfAñoPublicacion);
		
		body.add(leftBody);
		body.add(rightBody);
		
		JPanel west = new JPanel();
		west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
		
		JLabel textImagen = new JLabel("Imagen del libro");
		textImagen.setAlignmentX(CENTER_ALIGNMENT);

		imageLabel.setAlignmentX(CENTER_ALIGNMENT);
		JButton añadirImagenButton = new JButton("Añadir imagen");
		añadirImagenButton.setAlignmentX(CENTER_ALIGNMENT);
		
		añadirImagenButton.addActionListener(e -> {
		    JFileChooser fileChooser = new JFileChooser();

		    FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Imagen .jpg", "jpg");
		    fileChooser.setFileFilter(imageFilter);
		    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

		    int opcion = fileChooser.showOpenDialog(this);

		    if (opcion == JFileChooser.APPROVE_OPTION) {
		        ficheroImagen = fileChooser.getSelectedFile();

		        Long isbn = Long.parseLong(tfISBN.getText());
		        Path destino = Paths.get("resources/images/books/", isbn + ".jpg");
		        try {
		            Files.copy(ficheroImagen.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
		        } catch (IOException e1) {
		           e1.printStackTrace();
		        }

		        BufferedImage bi = null;
		        try {
		            bi = ImageIO.read(ficheroImagen);
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        }

		        if (bi != null) {
		            imagen = new ImageIcon(bi.getScaledInstance(128, 200, Image.SCALE_DEFAULT));
		            foto = imagen;
		            imageLabel.setIcon(imagen);
		        }
		    }
		});

		
		west.add(textImagen);
		west.add(imageLabel);
		west.add(añadirImagenButton);
		
		JPanel east = new JPanel();
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		
		taSinopsis.setFont(new Font("Arial", Font.PLAIN, 12));
		taSinopsis.setEditable(true);
		taSinopsis.setLineWrap(true);
		taSinopsis.setBorder(null);
		taSinopsis.setBorder(BorderFactory.createEmptyBorder());
		taSinopsis.setWrapStyleWord(true);
		taSinopsis.setBackground(Color.WHITE);
		taSinopsis.setColumns(30);
		taSinopsis.setRows(30);
		
		east.add(textSinopsis);
		east.add(taSinopsisPanel);
		
		rightBody.setBorder(new EmptyBorder(0, 60, 0, 0));
		leftBody.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		// Parte baja de la pantalla
		JButton crearLibroButton = new JButton(titleString + " libro");
		crearLibroButton.addActionListener(e -> {
			try {
				
				isbn = Long.parseLong(tfISBN.getText());
				titulo = tfTitulo.getText();
				autor = tfAutor.getText();
				numeroDePaginas = Integer.parseInt(tfNumeroPaginas.getText());
				sinopsis = taSinopsis.getText();
				genero = tfGenero.getText();
				rating = Integer.parseInt(tfRating.getText());
				añoPublicacion = Integer.parseInt(tfAñoPublicacion.getText());
				
				
				libro = new Libro(isbn, titulo, autor, numeroDePaginas, sinopsis, genero, rating, añoPublicacion, foto, new ArrayList<>());
				
				if (previousWindow instanceof VentanaInformacionRecurso) {
					LibroDTO libroEditado = new LibroDTO();
					libroEditado.setIsbn(isbn);
					libroEditado.setTitulo(titulo);
					libroEditado.setAutor(autor);
					libroEditado.setNumeroDePaginas(numeroDePaginas);
					libroEditado.setSinopsis(sinopsis);
					libroEditado.setGenero(genero);
					libroEditado.setRating(rating);
					libroEditado.setFechaPublicacion(añoPublicacion);

					Main.getLibroDAO().updateLibro(libroEditado, isbnAntiguo);
					Main.getUsuarioDAO().addLogAccion(new LogAccion(0, LocalDateTime.now(), "Editar libro " + libro.getIsbn(), usuario.getDni()));
					
					if (ficheroImagen != null) {
						
						try {
							Path deletePath = Paths.get(new File("resources/images/books/" + isbnAntiguo + ".jpg").getAbsolutePath());
							Files.delete(deletePath);
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(this, "Error al eliminar la imagen antigua del libro.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						
						Path origen = ficheroImagen.toPath();
						Path destino = Paths.get(new File("resources/images/books").getAbsolutePath(), Long.toString(isbn) + ".jpg");
						libro.setFoto(imagen);
						try {
							Files.copy(origen, destino);
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(this, "Error al guardar la imagen de libro. Comprueba que la imagen no esté ya creada.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						libro.setFoto(libroAEditar.getFoto());
						if (isbnAntiguo != isbn) {
							System.out.println("Epa");
							Path deletePath = Paths.get(new File("resources/images/books/" + Long.toString(isbnAntiguo) + ".jpg").getAbsolutePath());
							Path origen = deletePath;
							Path destino = Paths.get(new File("resources/images/books").getAbsolutePath(), Long.toString(isbn) + ".jpg");
							
							try {
								Files.copy(origen, destino);
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(this, "Error al guardar la imagen de libro. Comprueba que la imagen no esté ya creada.", "Error", JOptionPane.ERROR_MESSAGE);
							}
							
							try {
								Files.delete(deletePath);
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(this, "Error al eliminar la imagen antigua del libro.", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					
					JOptionPane.showMessageDialog(this, "Libro editado correctamente.", "Libro editado", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new VentanaInformacionRecurso(libro);
				} else {
					
					if (!Main.getLibroDAO().addLibro(libro)) {
						JOptionPane.showMessageDialog(this, "Error al insertar el libro en la BD. Comprueba los datos.", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						Main.getUsuarioDAO().addLogAccion(new LogAccion(0, LocalDateTime.now(), "Añadir libro " + libro.getIsbn(), usuario.getDni()));
						JOptionPane.showMessageDialog(this, "El libro se ha añadido correctamente.", "Libro añadido correctamente", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new VentanaInformacionRecurso(libro);
					}
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Error en el formato de algún campo. Comprueba los datos introducidos.", "Error", JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}
			
			
			
		});
		
		JPanel tail = new JPanel(new GridLayout(2, 1, 0, 0));
		
		JPanel añadirLibroButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		if (previousWindow instanceof VentanaInformacionRecurso) {
			JButton eliminarLibroButton = new JButton("Eliminar libro");
			eliminarLibroButton.addActionListener(e -> {
				if (Main.getLibroDAO().deleteLibroByIsbn(isbnAntiguo)) {
					Main.getUsuarioDAO().addLogAccion(new LogAccion(0, LocalDateTime.now(), "Eliminar libro " + isbnAntiguo, usuario.getDni()));
					JOptionPane.showMessageDialog(this, "Libro eliminado correctamente", "Libro eliminado", JOptionPane.INFORMATION_MESSAGE);
					File imagenLibro = new File("resources/images/books/" + isbnAntiguo + ".jpg");
					imagenLibro.delete();
					dispose();
					new VentanaBiblioteca();
				} else {
					JOptionPane.showMessageDialog(this, "Ha habido un problema eliminando el libro.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			});
			
			añadirLibroButtonPanel.add(eliminarLibroButton);
		}
		añadirLibroButtonPanel.add(crearLibroButton);
		
		tail.add(añadirLibroButtonPanel);
		
		topText.setBorder(new EmptyBorder(20, 0, 20, 0));
		body.setBorder(new EmptyBorder(50, 100, 0, 100));
		tail.setBorder(new EmptyBorder(50, 0, 0, 0));
		
		add(topText, BorderLayout.NORTH);
		add(body, BorderLayout.CENTER);
		add(east, BorderLayout.EAST);
		add(west, BorderLayout.WEST);
		add(tail, BorderLayout.SOUTH);
		
		west.setBorder(new EmptyBorder(50, 50, 0, 0));
		east.setBorder(new EmptyBorder(50, 0, 0, 50));
		
		body.setBorder(new EmptyBorder(100, 30, 0, 0));
		
		setVisible(true);
	}
}
