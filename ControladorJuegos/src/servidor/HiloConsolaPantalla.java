package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.simple.JSONObject;

public class HiloConsolaPantalla extends Thread {

	static ServerSocket segundo;
	static Socket pantalla;
	static DataInputStream dis;
	static DataOutputStream dout;
	
	public HiloConsolaPantalla (String name) {
		super(name);
	}
	
	public void run() {
		try {
			String message = "";
			segundo = new ServerSocket (5000);
			pantalla = segundo.accept();
			dis = new DataInputStream(pantalla.getInputStream());
			dout = new DataOutputStream (pantalla.getOutputStream());
			
			while (!message.equals("exit")) {
				System.out.println("entra");
				message = dis.readUTF();
				interfazServidor.recibidoServidor.setText(message);
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
