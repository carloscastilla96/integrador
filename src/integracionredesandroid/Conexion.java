package integracionredesandroid;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Observable;
import comun.*;

import comun.Usuario;

public class Conexion extends Observable implements Runnable {

	private int port;
	private DatagramSocket socket;
	private InetAddress ip;
	private byte[] buffer;

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

	public byte[] serializar(Object o) {

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		try {
			ObjectOutputStream os = new ObjectOutputStream(bytes);
			os.writeObject(o);
			os.flush();
			os.close();
			return bytes.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public void sendMessage(byte[] data, InetAddress destAddress, int destPort) {
		DatagramPacket packet = new DatagramPacket(data, data.length, destAddress, destPort);
		try {
			System.out.println("Sending data to " + destAddress.getHostAddress() + " : " + destPort);
			socket.send(packet);
			System.out.println("Data was sent");
		} catch (IOException e) {
			// Error sending the packet
			e.printStackTrace();
		}
	}

	public void run() {

		// TODO Auto-generated method stub
		while (true) {

			try {
				DatagramPacket paqueteRecibido = recibir();
				Object objetoDeserializado = deserializar(paqueteRecibido.getData());
				Thread.sleep(100);
				setChanged();
				notifyObservers(objetoDeserializado);
				clearChanged();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public DatagramPacket recibir() {

		buffer = new byte[1024];

		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

		try {
			if (socket != null) {
				socket.receive(packet);

				System.out.println("Data received from" + packet.getAddress() + " : " + packet.getPort());

				return packet;
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
		return packet;

	}

	public Object deserializar(byte[] ba) {
		Object data = null;
		try {
			if (buffer != null) {
				ByteArrayInputStream byteArray = new ByteArrayInputStream(ba);
				ObjectInputStream is = new ObjectInputStream(byteArray);
				data = is.readObject();
				System.out.println("deserializado");
				is.close();
				return data;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;

	}

}
