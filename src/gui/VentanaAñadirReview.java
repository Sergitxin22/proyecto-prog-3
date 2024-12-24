package gui;

import domain.Cliente;
import domain.Libro;
import domain.Review;
import main.Main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import utils.Utils;

public class VentanaAñadirReview extends JFrame {

	private static final long serialVersionUID = 1L;
	private Cliente cliente = (Cliente) Main.getUsuario();
	private int rating = 0;
	private String comment = "";

	public VentanaAñadirReview(Libro libro) {
		
		setTitle("Anadir review");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 400);
		setLocationRelativeTo(null);
		
		JLabel titleLabel = new JLabel("Añadir review", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Verdana", Font.BOLD, 32));
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		JLabel bookIcon = new JLabel();
		bookIcon.setIcon(libro.getFoto());
		JLabel bookTitle = new JLabel(libro.getTitulo());	
		
		bookIcon.setAlignmentX(CENTER_ALIGNMENT);
		bookTitle.setAlignmentX(CENTER_ALIGNMENT);
		
		leftPanel.add(bookIcon);
		leftPanel.add(bookTitle);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		JLabel comentarioLabel = new JLabel("Comentario");
		
		JTextArea comentarioTextArea = new JTextArea();
		
		JLabel valoracionLabel = new JLabel("Valoración");
		
		JPanel starPanel = new JPanel();
		List<JLabel> starList = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			JLabel label = new JLabel();

			final int j = i;
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					starAction(j, starList);
					rating = (j + 1) * 2;
				}
			});

			label.setIcon(Utils.loadImage("estrellaBlanca.png", 24, 24));
			
			starList.add(label);
		}

		for (int i = 0; i < 5; i++) {
			starPanel.add(starList.get(i));
		}
		
		JButton publicarButton = new JButton("Publicar review");

		publicarButton.addActionListener(e -> {
			comment = comentarioTextArea.getText();
			Review review = new Review(libro, cliente, comment, rating);
			libro.getReviews().add(review);
			// TODO: Añadir función para añadir review a la BD.

			dispose();
			VentanaInformacionRecurso redirectWindow = new VentanaInformacionRecurso(libro);
			JOptionPane.showMessageDialog(redirectWindow, "Gracias por tu review!", "Review publicada correctamente", JOptionPane.INFORMATION_MESSAGE);
		});
		
		comentarioLabel.setAlignmentX(CENTER_ALIGNMENT);
		comentarioTextArea.setAlignmentX(CENTER_ALIGNMENT);
		valoracionLabel.setAlignmentX(CENTER_ALIGNMENT);
		starPanel.setAlignmentX(CENTER_ALIGNMENT);
		publicarButton.setAlignmentX(CENTER_ALIGNMENT);		
				
		rightPanel.add(comentarioLabel);
		rightPanel.add(comentarioTextArea);
		rightPanel.add(valoracionLabel);
		rightPanel.add(starPanel);
		rightPanel.add(publicarButton);
		
		comentarioLabel.setBorder(new EmptyBorder(0, 0, 5, 0));
		valoracionLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
		leftPanel.setBorder(new EmptyBorder(50, 70, 0, 0));
		rightPanel.setBorder(new EmptyBorder(0, 0, 20, 70));
		starPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
		
		add(titleLabel, BorderLayout.NORTH);
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.EAST);

		setVisible(true);
	}

	public static void starAction(int starIndex, List<JLabel> starList) {
		// Pinta las estrellas en función de la estrella de review seleccionada

		for (int i = 0; i < 5; i++) {
			starList.get(i).setIcon(Utils.loadImage("estrellaBlanca.png", 24, 24));
		}

		for (int i = 0; i <= starIndex; i++) {
			starList.get(i).setIcon(Utils.loadImage("estrellaNegra.png", 24, 24));
		}
	}
	
	public static void main(String[] args) {
		ImageIcon foto = Utils.loadImage("books/9780006514855" + ".jpg", 128, 200);
		Libro libro = new Libro(0000000000000, "Libro 1", "Autor 1", 300, "Sinopsis", "Genero 1", 30, 2003, foto, new ArrayList<Review>());
		new VentanaAñadirReview(libro);
	}

}
