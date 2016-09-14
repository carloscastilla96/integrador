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
		System.out.println("sizas");

	}

	@Override
	public void update(Observable o, Object arg) {

		if (arg instanceof String) {
			String mensaje = (String) arg;
			System.out.println(mensaje);
		} else if (arg instanceof Usuario) {
			// validar(arg);
		}

	}

}
