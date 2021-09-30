/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodegon;

/**
 *
 * @author Leonardo Morganti
 */
public class AcumuladorPlato  extends Plato{
	
	private int cantidad;
	
	public AcumuladorPlato() {
		super();
	}
	
	public AcumuladorPlato(Plato plato) {
		super(plato.getNombre(), plato.getDetalle(), String.valueOf(plato.getPrecio()));
		this.cantidad=1;
	}

	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public void sumarCantidad() {
		this.cantidad = this.cantidad +1;
	}
	
	
}
