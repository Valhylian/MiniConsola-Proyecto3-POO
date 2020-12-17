package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextField;

public class Hilo extends Thread {
	static ServerSocket ss;
	static Socket s;
	static ServerSocket segundo;
	static Socket pantalla;
	static DataInputStream dis;
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
				message = dis.readUTF();
				interfazServidor.recibidoServidor.setText(message);
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
