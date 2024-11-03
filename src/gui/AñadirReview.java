package gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import BiblioTech.Genero;
import BiblioTech.Libro;
import BiblioTech.LibroLectura;
import utils.Utils;

public class AñadirReview extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AñadirReview(Libro libro) {
		
		setTitle("Anadir review");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 400);
		setLocationRelativeTo(null);
		
		JLabel titleLabel = new JLabel("Añadir review", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Verdana", Font.BOLD, 32));
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		JLabel bookIcon = new JLabel();
		bookIcon.setIcon(new ImageIcon(libro.getFoto()));
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
		
		for (int i = 0; i < 5; i++) {
			JLabel label = new JLabel();
			label.setIcon(Utils.loadImage("estrellaBlanca.png", 24, 24));
			
			starPanel.add(label);
		}
		
		JButton publicarButton = new JButton("Publicar review");
		
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
	
	public static void main(String[] args) {
		Libro libro = new LibroLectura("Harry Potter I", "J.K. Rowling", 443, Utils.loadImage("ejemploLibro.jpg", 112, 182).getImage(), 1, "Harry va a Hogwarts y tal",
				null, Genero.FANTASIA, 2);

		new AñadirReview(libro);
	}

}
