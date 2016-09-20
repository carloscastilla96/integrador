import java.util.Observable;
import java.util.Observer;

import javax.swing.MenuSelectionManager;

public class Logica implements Observer {

	private Conexion con;

	public Logica() {
		con = new Conexion();
		con.addObserver(this);
		Thread hilo = new Thread(con);
		hilo.start();


	}

	@Override
	public void update(Observable o, Object arg) {

		if (arg instanceof String) {
	
		} else if (arg instanceof Usuario) {
		Usuario receivedUser= (Usuario) arg; 
		String contraseña=receivedUser.getContraseña(); 
		String nombre=receivedUser.getNombre(); 
		System.out.println(contraseña);
		System.out.println(nombre);
		}

	}

}
