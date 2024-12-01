package main;

import BiblioTech.SalaPublica;
import gui.Portada;

import java.util.ArrayList;

public class Main {
    // private static GestorDB bd = new GestorDB();
    private static SalaPublica salaPublica = new SalaPublica(250, 0, 1, new ArrayList<>());

    public static SalaPublica getSalaPublica() {
    return salaPublica;
    }

    public static void main(String[] args) {

        try {
        Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
        System.out.println("No se ha podido cargar el driver de la base de datos");
        }

        
        // Para probar cada vista en diferentes tipos de usuario, descomenta una de las tres líneas       
        new Portada(null); // NO LOGUEADO
//      new Portada(new Cliente()); // CLIENTE
//      new Portada(new Admin()); // ADMIN
        
        
// Pruebas de consultas
        
        
        
//        bd.init("bibliotech");
//        bd.deleteDatosTablas();
//        System.out.println(bd.existeBD("bibliotech"));
//        
//        Cliente u = new Cliente("00000000A", "Sergio", "sergio@si.es", LocalDateTime.now(), "hola", new ArrayList<>(),
//                new ArrayList<>(), 0);
//        Admin a = new Admin("11111111B", "Aroa", "aroa@no.com", LocalDateTime.now(), "aroa2003", new ArrayList<>());
//        Libro l = new Libro(1111111111111l, "libro1", "autor1", 500, "sinopsis1", "Fantasia", 7, 2005, null, new ArrayList<>());
//        SalaPublica sp = new SalaPublica(20, 1, 5, new ArrayList<>());
//        SalaPrivada spr = new SalaPrivada(15, 2, 2, new ArrayList<>(), new ArrayList<>());
//        SalaEventos se = new SalaEventos(10, 3, 4, new ArrayList<>(), null);
//        Review rw = new Review(l, u, "c1", 5);
//        ArrayList<Cliente> cl = new ArrayList<Cliente>();
//        cl.add(u);
//        Reserva r = new Reserva(spr, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(),cl);
//        
//        bd.addUsuario(u);
//        bd.addUsuario(a);
//        bd.addLibro(l);
//        bd.addTipoEvento();
//        bd.addTipoSala();
//        bd.Recurso();
//        bd.getIdTipoSala("PRIVADA");
//        bd.addSala(sp);
//        bd.addSala(spr);
//        bd.addSala(se);
//        bd.addReview(rw);
//        bd.addReservaSala(r);
//        
//        try {
//            bd.getConnection().close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
