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
public abstract class Persona implements Serializable {

    private final String usuario;
    private String password;

    public Persona(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public abstract boolean proceder();

}
