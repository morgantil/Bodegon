/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodegon;

import java.util.Scanner;

/**
 *
 * @author Leonardo Morganti
 */
public class Cocinero extends Persona {

	public Cocinero(String usuario, String password) {
		super(usuario, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean proceder() {
		Scanner scanner = new Scanner(System.in);
		boolean cerrar = false;
		while (!cerrar) {
			int op;
			do {
				System.out.println("MENU COCINERO");
				System.out.println("\n[0] Salir" + "\n" + "[1] Agregar Plato \n");
				op = scanner.nextInt();

			} while (op < 0 || op > 2);

			if (op == 1) {
				try {
					Menu.getInstance().agregar(crearPlato());
				}catch(Exception e) {
					System.out.println("ERROR AL INTENTAR AGREGAR PLATO : "+e.getMessage());	
				}
			} else {

				cerrar = true;
				System.out.println("Adios");
			}
		}
		return true;
	}
	
	public Plato crearPlato() {
		Scanner scanner = new Scanner(System.in);
		String nombre , detalle;
		System.out.println("INGRESE EL PLATO");
	    nombre = scanner.nextLine();
	    System.out.println("INGRESE DETALLES DEL PLATO");
	    detalle =scanner.nextLine();
	    return new Plato(nombre,detalle,"");
	}

}

