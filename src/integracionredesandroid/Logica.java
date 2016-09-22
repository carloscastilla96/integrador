package integracionredesandroid;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.MenuSelectionManager;

import comun.Usuario;

public class Logica implements Observer {

	private Conexion con;
	private ArrayList<Usuario> usuarios; 

	public Logica() {
		con = new Conexion();
		con.addObserver(this);
		Thread hilo = new Thread(con);
		hilo.start();

	}

	@Override
	public void update(Observable o, Object arg) {

		if (arg instanceof Usuario) {
			Usuario receivedUser = (Usuario) arg;
			String contraseña= receivedUser.getContraseña();
			String nombre = receivedUser.getNombre();
			System.out.println(nombre);
			System.out.println(contraseña);
		}

	}
	

}
