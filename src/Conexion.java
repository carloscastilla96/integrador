import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Observable;

public class Conexion extends Observable implements Runnable {

	private int port;
	private DatagramSocket socket;
	private InetAddress ip;

	public Conexion() {
		port = 5000;
		try {
			ip = InetAddress.getLocalHost();
			System.out.println("La IP del servidor local es:" + ip);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			socket = new DatagramSocket(port);
			if (socket != null) {
				System.out.println("Se inicializo el socket en el puerto" + port);
			}
	
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}

	public void enviar(Object ob) {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ObjectOutputStream obs = new ObjectOutputStream(out);
			obs.writeObject(ob);
			obs.flush();

			obs.close();
			byte hola[] = out.toByteArray();
			DatagramPacket packet = new DatagramPacket(hola, hola.length);
			socket.send(packet);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void run() {

		// TODO Auto-generated method stub
		while (true) {

			try {
				recibir();
				Thread.sleep(100);

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public void recibir() throws IOException, ClassNotFoundException {
		byte[] buffer;
		DatagramPacket packet;
		buffer = new byte[1024];
		packet = new DatagramPacket(buffer, buffer.length);
		System.out.println("recibi de: "+packet.getAddress()+" en el puerto"+packet.getPort());
		socket.receive(packet);
		deserializar(packet);
	}

	private void deserializar(DatagramPacket packet) throws IOException, ClassNotFoundException {

		ByteArrayInputStream by = new ByteArrayInputStream(packet.getData());
		ObjectInputStream obs = new ObjectInputStream(by);
	
		System.out.println("deserializo");
		Object ob = obs.readObject();
		setChanged();
		notifyObservers(ob);
		clearChanged();
		
	}


	
}
