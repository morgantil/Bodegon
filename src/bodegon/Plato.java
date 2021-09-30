/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodegon;

import java.io.Serializable;

/**
 *
 * @author Leonardo Morganti
 */
public class Plato implements Serializable {

	private String nombre = "";
	private String detalle = "";
	private float precio = 0;


	
	public Plato(String nombre, String detalle, String precio) {
		this.nombre = nombre;
		this.detalle = detalle;
		if(!precio.equals("")) {
			this.precio = Float. parseFloat(precio.replaceAll(",", "."));
		}
	}
	
	public Plato() {
		this.nombre = "";
		this.detalle = "";
		this.precio = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = Float.parseFloat(precio);
	}
	
	public void setPrecio(float precio) {
		this.precio =precio;
	}

	@Override
	public String toString() {
		return "Nombre = " + nombre + ", Detalle = " + detalle + ", Precio = $" + precio;
	}
	
	


}

