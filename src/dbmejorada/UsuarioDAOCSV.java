package dbmejorada;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.Cliente;
import domain.Usuario;

public class UsuarioDAOCSV implements UsuarioDAOInterface {

    private File archivoCSV;
    private Logger logger = null;

    public UsuarioDAOCSV(String nombreArchivo) {
    	nombreArchivo = "resources/data/" + nombreArchivo + ".csv";
        archivoCSV = new File(nombreArchivo);
        logger = Logger.getLogger("GestorPersistencia-" + archivoCSV.getName());
    }
    
	@Override
    public boolean guardarCliente(Usuario usuario) {
        try {
            // Abrimos el archivo CSV en modo de añadir (append)
            FileWriter fw = new FileWriter(archivoCSV, true);
            BufferedWriter bw = new BufferedWriter(fw);
            // Escribir los datos del usuario en formato CSV
            if (usuario instanceof Cliente) {
                Cliente cliente = (Cliente) usuario;
                bw.write(cliente.getDni() + "," + "cliente," + cliente.getNombre() + "," + cliente.getEmail() + ","
                        + cliente.getFechaCreacion() + "," + cliente.getContrasena() + ","
                        + cliente.getAmonestaciones() + "\n");
            } else {
                bw.write(usuario.getDni() + "," + "admin," + usuario.getNombre() + "," + usuario.getEmail() + ","
                        + usuario.getFechaCreacion() + "," + usuario.getContrasena() + "\n");
            }
            bw.close();
            System.out.println("Cliente guardado correctamente.");
            return true;
        } catch (IOException e) {
            if (logger != null)
                logger.log(Level.SEVERE, "Error al guardar cliente en CSV: ", e);
            return false;
        }
    }


	@Override
	public void borrarRegistros() {
		// Este método puede ser opcional, dependiendo de si deseas borrar los datos en el archivo CSV.
        try {
            if (archivoCSV.exists()) {
                archivoCSV.delete();
                System.out.println("Registros borrados correctamente.");
            }
        } catch (Exception e) {
            if (logger != null)
                logger.log(Level.SEVERE, "Error al borrar los registros: ", e);
        }		
	}

	

}
