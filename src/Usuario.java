
public class Usuario {

	private String nombre, contrase�a;
	private int ID;

	public Usuario(String nombre, String contrase�a, int iD) {

		this.nombre = nombre;
		this.contrase�a = contrase�a;
		this.ID = iD;
	}

	public String getNombre() {
		return nombre;
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public int getID() {
		return ID;
	}
	


}
