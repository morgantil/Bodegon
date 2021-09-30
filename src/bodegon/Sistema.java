/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodegon;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Leonardo Morganti
 */

public class Sistema implements Serializable {
	private static Sistema sistema;
	private Menu menu;
	private List<Persona> personas;

	private Sistema() {
		menu = Menu.getInstance();
		personas = new ArrayList<>();
	}
// SINGLETON
	public static Sistema getInstance() {
		if (sistema == null) {
			sistema = new Sistema();
		}
		return sistema;
	}

	public void deSerializar(String archivo) throws IOException, ClassNotFoundException {
		FileInputStream f = new FileInputStream(archivo);
		ObjectInputStream o = new ObjectInputStream(f);
		this.sistema = (Sistema) o.readObject();

	}

	public void serializar(String archivo) throws IOException {
		FileOutputStream f = new FileOutputStream(archivo);
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(this);
		o.close();
	}

	public Menu getSb() {
		return menu;
	}

	public void setMenu(Menu sb) {
		this.menu = sb;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public void arrancar() {
		Scanner scanner = new Scanner(System.in);
		boolean corriendo = true;

		while (corriendo) {
			Persona u = null;

			System.out.println("Ingrese su usuario o 0 para salir");
			String usuario = scanner.next();
			if (!usuario.equals("0")) {
				System.out.println("Ingrese su password");
				String password = scanner.next();

				for (Persona p : personas) {
					if (p.getUsuario().equals(usuario) && p.getPassword().equals(password)) {
						u = p;
					}
				}

				if (u == null) {
					System.out.println("El usuario o el password es inexistente");
				} else {
					corriendo = u.proceder();
				}
			} else {
				corriendo = false;
			}

		}
		System.out.println("Finalizando el dia...");
		mostrarInformarcionAlFinalizar();
	}

	public void inicializacion() {
		Scanner scanner = new Scanner(System.in);
		String usuario, password;

		System.out.println("PRIMER INICIO\n\n" + "Debe generar los usuarios del sistema.");

		System.out.println("\nIngrese el nombre de usuario del administrador:");
		usuario = scanner.next();
		System.out.println("Ingrese el pasword del administrador:");
		password = scanner.next();
		Administrador administrador = new Administrador(usuario, password);

		System.out.println("\nIngrese el nombre de usuario del cocinero:");
		usuario = scanner.next();
		System.out.println("Ingrese el pasword del cocinero:");
		password = scanner.next();
		Cocinero cocinero = new Cocinero(usuario, password);

		System.out.println("\nIngrese el nombre de usuario del camarero:");
		usuario = scanner.next();
		System.out.println("Ingrese el pasword del camarero:");
		password = scanner.next();

		Camarero camarero = new Camarero(usuario, password);

		this.personas.add(administrador);
		this.personas.add(cocinero);
		this.personas.add(camarero);

		System.out.println("USUARIOS CARGADOS\n\n" + "Ya puede ingresar al sistema.");
	}

	public void agregarCamarero(Camarero camarero) {
		try {
			System.out.println(this.personas.size());
			this.personas.add(camarero);
			System.out.println(this.personas.size());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void agregarEmpleado(Persona p) {
		personas.add(p);
	}

	public void agregarCocinero(Cocinero cocinero) {
		this.personas.add(cocinero);
	}

	public void mostrarInformarcionAlFinalizar() {

		float totalRecaudado = 0;
		String nombreCamareroMasVentas="";
		int cantidadCamareroMasventas = 0;
		boolean platoNuevo;
		int cantidadVentasMaximasPorCamarero = 0;
		ArrayList<AcumuladorPlato> acumuladorPlatos = new ArrayList<>();
		for (Persona p : personas) {
			if (p instanceof Camarero) {
				ArrayList<Orden> ordenesDelUsuario = ((Camarero) p).getPedidos();
				for (Orden orden : ordenesDelUsuario) {
					for (Plato plato : orden.getPedidos()) {
						totalRecaudado = totalRecaudado + plato.getPrecio();
						platoNuevo = true;
						for (AcumuladorPlato acum : acumuladorPlatos) {
							if (acum.getNombre().equals(plato.getNombre())) {
								platoNuevo = false;
								acum.sumarCantidad();
							}
						}
						if(platoNuevo) {
							acumuladorPlatos.add(new AcumuladorPlato(plato));
						}
					}
					if (ordenesDelUsuario.size() > cantidadCamareroMasventas) {
						nombreCamareroMasVentas = p.getUsuario();
					}
				}
				if (ordenesDelUsuario.size() > cantidadVentasMaximasPorCamarero) {
					nombreCamareroMasVentas = p.getUsuario();
				}
			}
		}
		
		if(totalRecaudado>0) {
			System.out.println("La recaudacion de la fecha fue: $"+totalRecaudado);
			System.out.println("El camarero que mas vendió fue: "+nombreCamareroMasVentas);
			mostrarPlatoYBebidaMasPedidos(acumuladorPlatos);	
		}
		else {
			System.out.println("No se registraron ventas.");
		}
	}
	
	public void mostrarPlatoYBebidaMasPedidos(ArrayList<AcumuladorPlato> acumuladorPlatos) {
		int idPLatoMax=0,idBebidaMax=0,maximoPlato=0,maximaBebida=0;
		for(int i=0; i<acumuladorPlatos.size();i++) {
			if(acumuladorPlatos.get(i).getDetalle().equals("bebida")) {
				if(acumuladorPlatos.get(i).getCantidad()>maximaBebida) {
					maximaBebida = acumuladorPlatos.get(i).getCantidad();
					idBebidaMax = i;
				}
			}else {
				if(acumuladorPlatos.get(i).getCantidad()>maximoPlato) {
					maximoPlato = acumuladorPlatos.get(i).getCantidad();
					idPLatoMax = i;
				}
			}
		}
		System.out.println("La bebida mas pedida fue : "+acumuladorPlatos.get(idBebidaMax).getNombre()+
				" . Fue pedido "+maximaBebida+" veces.");
		System.out.println("El plato mas pedida fue : "+acumuladorPlatos.get(idPLatoMax).getNombre()+
				" . Fue pedido "+maximoPlato+" veces.");
		
	}
	
	public void vaciarVentasDelDia() {
		for (Persona p : personas) {
			if (p instanceof Camarero) {
				((Camarero) p).vaciarPedidos();
			}
		}
	}

}