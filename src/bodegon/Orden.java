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
public class Orden implements Serializable {

	private ArrayList<Plato> pedidos;
	private Integer mesa;
	
	public Orden() {
		pedidos = new ArrayList<>();
		mesa = 0;
	}
	
	
	public ArrayList<Plato> getPedidos() {
		return pedidos;
	}
	
	public void agregarPedido(Plato plato) {
		pedidos.add(plato);
	}
	
	public void setPedidos(ArrayList<Plato> pedidos) {
		this.pedidos = pedidos;
	}
	public Integer getMesa() {
		return mesa;
	}
	public void setMesa(Integer mesa) {
		this.mesa = mesa;
	}
	
	
	
}

