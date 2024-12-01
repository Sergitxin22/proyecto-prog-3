package main;

import BiblioTech.SalaPublica;
import gui.Portada;
import java.util.ArrayList;

public class Main {

    private static SalaPublica salaPublica = new SalaPublica(250, 0, 1, new ArrayList<>());

    public static SalaPublica getSalaPublica() {
        return salaPublica;
    }

    public static void main(String[] args) {
        new Portada(null);
    }
}
