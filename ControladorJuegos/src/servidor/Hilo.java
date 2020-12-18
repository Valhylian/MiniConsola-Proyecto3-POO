package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextField;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Hilo extends Thread {
	public static String informacion;
	static ServerSocket ss;
	static Socket s;
	public static DataInputStream dis;
	static DataOutputStream dout;
	
	public Hilo (String name) {
		super(name);
	}
	
	public void run() {
		try {
			String message = "";
			ss = new ServerSocket (9000);
			s = ss.accept();
			dis = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream (s.getOutputStream());
			
			while (!message.equals("exit")) {
				System.out.println("entra");
				message = dis.readUTF();
				informacion = message;
				interfazServidor.recibidoServidor.setText(message);
				
				JSONParser parser = new JSONParser();
				try {
					JSONObject json = (JSONObject) parser.parse(message);
					Object accion = json.get("presionado");
					String accionStr = accion.toString();
					
					if (accionStr == "arriba") {
						//esta dentro de la matriz?
						//mande el mensaje
					}
					System.out.println(accion);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
