
import java.io.Serializable;

/**
 * Created by 1144090943 on 19/09/2016.
 */

public class Usuario implements Serializable {

    private String nombre, contrase�a;


    public Usuario(String nombre, String contrase�a) {

        this.nombre = nombre;
        this.contrase�a = contrase�a;

    }

    public String getNombre() {
        return nombre;
    }

    public String getContrase�a() {
        return contrase�a;
    }


}
