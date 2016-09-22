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

		if (arg instanceof Usuario) {
			System.out.println("holi");
			Usuario receivedUser = (Usuario) arg;
			String contrase�a = receivedUser.getContrase�a();
			String nombre = receivedUser.getNombre();
			System.out.println(contrase�a);
			System.out.println(nombre);
		}

	}

}
