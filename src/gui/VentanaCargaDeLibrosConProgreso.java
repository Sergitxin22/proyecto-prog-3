package gui;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class VentanaCargaDeLibrosConProgreso extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JProgressBar progressBar;
    private JButton cargarButton;
    private JTextArea outputArea;
    
    public VentanaCargaDeLibrosConProgreso() {
    	
    	setTitle("Carga de Libros con Progreso");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        progressBar = new JProgressBar (0,100);
        cargarButton = new JButton("Cargar Libros");
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        
    
        add(progressBar, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
        add(cargarButton, BorderLayout.SOUTH);
        
        cargarButton.addActionListener(e -> cargarLibros());

        setVisible(true);
    }
    
    private void cargarLibros() {
    	cargarButton.setEnabled(false);
        outputArea.setText("Cargando libros...\n");
        
        SwingWorker<List<String>, Integer> worker = new SwingWorker<>() {

			@Override
			protected List<String> doInBackground() throws Exception {
				List<String> libros = new ArrayList<>();
				String dbPath = "resources/db/bibliotech.db";
				
                File dbFile = new File(dbPath);

                if (!dbFile.exists()) {
                    throw new Exception("El archivo de base de datos no existe: " + dbPath);
                }

                String url = "jdbc:sqlite:" + dbPath;
                
                try (Connection connection = DriverManager.getConnection(url);
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT nombre FROM libros")) {
                	resultSet.last(); // Lo movemos al último registro para contar
                    int totalLibros = resultSet.getRow();
                    resultSet.beforeFirst();
                    
                    int progreso = 0;
                    while (resultSet.next()) {
                       
                        Thread.sleep(100);
                        
                        libros.add(resultSet.getString("nombre"));

                        // Aquí se actualiza el progreso
                        progreso++;
                        int porcentaje = (int) ((progreso / (double) totalLibros) * 100);
                        publish(porcentaje);
                    }     
                }
                return libros;
			}
			
        	@Override
        	protected void process (List<Integer>chunks) {
        		int ultimoProgreso = chunks.get(chunks.size()-1);
        		progressBar.setValue(ultimoProgreso);
        	}
        	
        	@Override
        	protected void done() {
        		try {
        			List<String>libros = get();
        			outputArea.append("Libros cargados:\n");
        			for (String libro: libros) {
        				outputArea.append(libro + "\n");
        			}
        		}catch(Exception e) {
        			outputArea.append("Error al cargar los libros: " + e.getMessage());
        			
        		}finally {
        			cargarButton.setEnabled(true);
        			progressBar.setValue(0);
        		}
        	}
        };
        worker.execute();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(VentanaCargaDeLibrosConProgreso::new);
    }
}
