package servidor;

import java.awt.Color;
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
	static DataOutputStream outPantalla;
	
	public HiloConsolaPantalla (String name) {
		super(name);
	}
	
	public void run() {
		try {
			String message = "";
			segundo = new ServerSocket (5000);
			pantalla = segundo.accept();
			dis = new DataInputStream(pantalla.getInputStream());
			outPantalla = new DataOutputStream (pantalla.getOutputStream());
			
			while (!message.equals("exit")) {
				message = dis.readUTF();
				
				System.out.println(message);
				message = dis.readUTF();
				interfazServidor.recibidoServidor.setText(message);
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}