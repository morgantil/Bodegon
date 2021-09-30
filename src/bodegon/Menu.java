/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodegon;


import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Leonardo Morganti
 */
public class Menu implements Serializable {
	private static Menu menu; 
	private ArrayList<Plato> platos;

	private Menu() { 
		platos = new ArrayList<>();
	}
        
        // SINGLETON
	public static Menu getInstance() { 
		if (menu == null) {
			menu = new Menu();
		}
		return menu;
	}

	public String obtenerListadoPlatos() {
		String lista = "";
		for (int i = 0; i < platos.size(); i++) {
			lista += "[" + (i + 1) + "] " + platos.get(i) + "\n";
		}
		return lista;
	}

	public String obtenerListadoPlatosConPrecio() {
		String lista = "";	
		for (int i = 0; i < platos.size(); i++) {
			if(platos.get(i).getPrecio()>0) {
				lista += "[" + (i + 1) + "] " + platos.get(i) + "\n";
			}
		}
		return lista;
	}

	public void setPlato(ArrayList<Plato> platos) {
		this.platos = platos;
	}
	
	public ArrayList<Plato> getPlatos() {
		return this.platos;
	}

	public int obtenerCantidadPlatos() {
		return platos.size();
	}

	public void eliminarPlato(int i) {
		platos.remove(i);
	}

	void agregar(Plato p) {
		platos.add(p);
	}

	public static Menu getMenu() {
		return menu;
	}

	public static void setMenu(Menu menu) {
		Menu.menu = menu;
	}

	public void modificarPrecioPorId(int posicion, String precio) {
		ArrayList<Plato> platosAux = new ArrayList<>();
		
		for (int i = 0; i < platos.size(); i++) {
			if (i == posicion - 1) {
				platosAux.add(new Plato(platos.get(i).getNombre(), platos.get(i).getDetalle(), precio));
			} else {
				platosAux.add(platos.get(i));
			}
		}
		setPlato(platosAux);
	}
	
	
	
}
