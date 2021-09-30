/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodegon;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Leonardo Morganti
 */
public class Camarero extends Persona {

	private ArrayList<Orden> pedidos;

	public Camarero(String usuario, String password) {
		super(usuario, password);
		this.pedidos = new ArrayList<>();
	}

	public ArrayList<Orden> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<Orden> pedidos) {
		this.pedidos = pedidos;
	}

	public void agregarPedido(Orden orden) {
		this.pedidos.add(orden);
	}
	
	public void vaciarPedidos() {
		this.pedidos.clear();
	}

	@Override
	public boolean proceder() {
		Scanner scanner = new Scanner(System.in);
		boolean cerrar = false;
		boolean sumarPlato = true;
		int opcionMesa,idAgregar;
		while (!cerrar) {
			int op;
			do {
				System.out.println("MENU CAMARERO");
				System.out.println("\n[0] Salir" + "\n[1] Tomar pedido");
				op = scanner.nextInt();
				

				if (op != 0) {
					
					System.out.println("\nIngrese el numero de mesa o 0 para salir");
					opcionMesa = scanner.nextInt();
					if(opcionMesa != 0) {
						Orden orden = new Orden();
						orden.setMesa(opcionMesa);
						while(sumarPlato) {
							System.out.println("\n[0] Finalizar Orden" + "\n" + Menu.getInstance().obtenerListadoPlatosConPrecio());
							idAgregar= scanner.nextInt();
							if(idAgregar > 0 && idAgregar <= Menu.getInstance().obtenerCantidadPlatos()) {
								orden.agregarPedido(Menu.getInstance().getPlatos().get(idAgregar-1));
								System.out.println("\nPlato agregado");
							}else if(idAgregar == 0) {
								this.pedidos.add(orden);
								System.out.println("\nOrden finalizada");
								op=0;
								sumarPlato = false;
							}else {
								System.out.println("\nOpcion invalida");
							}
						}
					}
					
				}

			} while (op < 0 || op > Menu.getInstance().obtenerCantidadPlatos());

			cerrar = true;
			System.out.println("Adios");
		}
		return true;
	}
	

}
