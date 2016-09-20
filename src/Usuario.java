
public class Usuario {

	private String nombre, contraseña;
	private int ID;

	public Usuario(String nombre, String contraseña, int iD) {

		this.nombre = nombre;
		this.contraseña = contraseña;
		this.ID = iD;
	}

	public String getNombre() {
		return nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public int getID() {
		return ID;
	}
	


}
