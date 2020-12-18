package servidor;

import java.awt.Color;
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
	static int color = 0;
	
	
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
					
					if (accionStr.equals("arriba")) {
						if (interfazServidor.dentroMatriz(interfazServidor.posicionX -1, interfazServidor.posicionY)) {
							
							try {
								JSONObject jsonEnviado = new JSONObject();
								jsonEnviado.put("anterioX", interfazServidor.posicionX);
								jsonEnviado.put("anterioY", interfazServidor.posicionY);
								interfazServidor.posicionX--;
								
								jsonEnviado.put("nuevaX", interfazServidor.posicionX);
								jsonEnviado.put("nuevaY", interfazServidor.posicionY);
								
								jsonEnviado.put("accion", color);
								
								
								
								String mess = jsonEnviado.toString();
								HiloConsolaPantalla.outPantalla.writeUTF(mess);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					
					else if (accionStr.equals("abajo")) {
						if (interfazServidor.dentroMatriz(interfazServidor.posicionX +1, interfazServidor.posicionY)) {
							
							try {
								JSONObject jsonEnviado = new JSONObject();
								jsonEnviado.put("anterioX", interfazServidor.posicionX);
								jsonEnviado.put("anterioY", interfazServidor.posicionY);
								interfazServidor.posicionX++;
								
								jsonEnviado.put("nuevaX", interfazServidor.posicionX);
								jsonEnviado.put("nuevaY", interfazServidor.posicionY);
								
								jsonEnviado.put("accion", color);
								
								String mess = jsonEnviado.toString();
								HiloConsolaPantalla.outPantalla.writeUTF(mess);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					else if (accionStr.equals("derecha")) {
						if (interfazServidor.dentroMatriz(interfazServidor.posicionX, interfazServidor.posicionY + 1)) {
							
							try {
								JSONObject jsonEnviado = new JSONObject();
								jsonEnviado.put("anterioX", interfazServidor.posicionX);
								jsonEnviado.put("anterioY", interfazServidor.posicionY);
								interfazServidor.posicionY++;
								
								jsonEnviado.put("nuevaX", interfazServidor.posicionX);
								jsonEnviado.put("nuevaY", interfazServidor.posicionY);
						
								jsonEnviado.put("accion", color);
								String mess = jsonEnviado.toString();
								HiloConsolaPantalla.outPantalla.writeUTF(mess);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					else if (accionStr.equals("izquierda")) {
						if (interfazServidor.dentroMatriz(interfazServidor.posicionX, interfazServidor.posicionY -1)) {
							
							try {
								JSONObject jsonEnviado = new JSONObject();
								jsonEnviado.put("anterioX", interfazServidor.posicionX);
								jsonEnviado.put("anterioY", interfazServidor.posicionY);
								interfazServidor.posicionY -- ;
								
								jsonEnviado.put("nuevaX", interfazServidor.posicionX);
								jsonEnviado.put("nuevaY", interfazServidor.posicionY);
						
								jsonEnviado.put("accion", color);
								String mess = jsonEnviado.toString();
								HiloConsolaPantalla.outPantalla.writeUTF(mess);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					
					else if (accionStr.equals("accion")) {
						try {
							JSONObject jsonEnviado = new JSONObject();
							jsonEnviado.put("anterioX", interfazServidor.posicionX);
							jsonEnviado.put("anterioY", interfazServidor.posicionY);
							
							
							jsonEnviado.put("nuevaX", interfazServidor.posicionX);
							jsonEnviado.put("nuevaY", interfazServidor.posicionY);
							
							if (color >= 11) {
								color = 0;
								jsonEnviado.put("accion", color);
							}
							else {
								color ++;
								jsonEnviado.put("accion", color);
							}
							
							
							String mess = jsonEnviado.toString();
							HiloConsolaPantalla.outPantalla.writeUTF(mess);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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
