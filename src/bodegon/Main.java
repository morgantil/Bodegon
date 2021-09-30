/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodegon;

import java.io.IOException;

/**
 *
 * @author Leonardo Morganti
 */
public class Main {

    public static void main(String[] args) {
        String archivoDatos = "bodegondata.bin";


        try {
        	Sistema.getInstance().deSerializar(archivoDatos);
        } catch (IOException | ClassNotFoundException ex) {
        	Sistema.getInstance().inicializacion();
        } finally {
        	Sistema.getInstance().arrancar();
        }

        try {
        	Sistema.getInstance().serializar(archivoDatos);
        } catch (IOException ex) {
        	System.out.println("ERROR: "+ex.getMessage());
        }
    }

}
