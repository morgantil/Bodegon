/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodegon;

import java.util.Scanner;
import bodegon.Persona;

/**
 *
 * @author Leonardo Morganti
 */
public class Administrador extends Persona {

	public Administrador(String usuario, String password) {
		super(usuario, password);
	}

	@Override
	public boolean proceder() {
		Scanner scanner = new Scanner(System.in);
		boolean cerrar = false;
		int opcionSubmenu;
		String precioPlato;

		while (!cerrar) {
			int op;
			do {
				System.out.println("MENU ADMINISTRADOR");
				System.out.println("\n[0] Salir" + "\n" + "[1] Agregar Bebida \n"
						+ "[2] Asignar precio \n[3] Eliminar Plato" + "\n[4] Añadir empleado");
				op = scanner.nextInt();

			} while (op < 0 || op > 5);

			if (op == 1) {
				try {
					Menu.getInstance().agregar(agregarBebida());
				} catch (Exception e) {
					System.out.println("ERROR AL INTENTAR AGREGAR BEBIDA : " + e.getMessage());
				}
			} else if (op == 2) {
				System.out.println(Menu.getInstance().obtenerListadoPlatos());

				System.out.println("Seleccione el plato a modificar el precio o 0 para salir");
				opcionSubmenu = scanner.nextInt();
				if (opcionSubmenu > 0) {
					System.out.println("Ingrese el precio del plato");
					precioPlato = scanner.next();
					Menu.getInstance().modificarPrecioPorId(opcionSubmenu, precioPlato);
					System.out.println("Se asigno el precio");
				}

			} else if (op == 3) {
				System.out.println(Menu.getInstance().obtenerListadoPlatos());
				System.out.println("Seleccione el plato a eliminar");
				opcionSubmenu = scanner.nextInt();
				if (opcionSubmenu > 0) {
					//corroborar 
					Menu.getInstance().eliminarPlato(opcionSubmenu-1);
					System.out.println("Se asigno el precio");
				}
			}else if (op == 4) {
				System.out.println("Ingrese el id del puesto del empleado");				
				agregarEmpleado();
			}

			else {
				cerrar = true;
				System.out.println("Adios\n");
			}
		}
		return true;
	}

	public Plato agregarBebida() {
		Scanner scanner = new Scanner(System.in);
		String nombre, detalle, precio;
		System.out.println("INGRESE LA BEBIDA");
		nombre = scanner.nextLine();
		detalle = "bebida";
		System.out.println("INGRESE PRECIO DE LA BEBIDA");
		precio = scanner.nextLine();
		return new Plato(nombre, detalle, precio);
	}
	
	public void agregarEmpleado() {
		Scanner scanner = new Scanner(System.in);
		int opcion;
		System.out.println("[1] Camarero" + "\n" + "[2] Cocinero");
		opcion = scanner.nextInt();
		if(opcion == 1 || opcion == 2) {
			String user, pass;
			System.out.println("INGRESE EL USUARIO:");
			user = scanner.next();
			System.out.println("INGRESE EL PASSWORD:");
			pass = scanner.next();
			if(opcion == 1) {
				Sistema.getInstance().agregarEmpleado(new Camarero(user,pass));
			}else{
				Sistema.getInstance().agregarCocinero(new Cocinero(user,pass));
			}	
		}else {
			System.out.print("OPCION INVALIDA");
		}
	}

}
