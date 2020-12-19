package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HiloConsola2 extends Thread {
	public static String informacion;
	static ServerSocket ss;
	static Socket s;
	public static DataInputStream dis;
	static DataOutputStream dout;
	static int color = 1;
	
	
	public HiloConsola2 (String name) {
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
				IntefazServidor2.recibidoServidor.setText(message);
				
				JSONParser parser = new JSONParser();
				try {
					JSONObject json = (JSONObject) parser.parse(message);
					Object accion = json.get("presionado");
					String accionStr = accion.toString();
					
					if (accionStr.equals("arriba")) {
						if (IntefazServidor2.dentroMatriz(IntefazServidor2.posicionX -1, IntefazServidor2.posicionY)) {
							
							try {
								JSONObject jsonEnviado = new JSONObject();
								jsonEnviado.put("messageNum", "1");
								jsonEnviado.put("anterioX", IntefazServidor2.posicionX);
								jsonEnviado.put("anterioY", IntefazServidor2.posicionY);
								IntefazServidor2.posicionX--;
								
								jsonEnviado.put("nuevaX", IntefazServidor2.posicionX);
								jsonEnviado.put("nuevaY", IntefazServidor2.posicionY);
								
								jsonEnviado.put("accion", color);
								jsonEnviado.put("color", 1);
								
								
								
								String mess = jsonEnviado.toString();
								HiloConsolaPantalla2.outPantalla.writeUTF(mess);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					
					else if (accionStr.equals("abajo")) {
						if (IntefazServidor2.dentroMatriz(IntefazServidor2.posicionX +1, IntefazServidor2.posicionY)) {
							
							try {
								JSONObject jsonEnviado = new JSONObject();
								jsonEnviado.put("messageNum", "1");
								jsonEnviado.put("anterioX", IntefazServidor2.posicionX);
								jsonEnviado.put("anterioY", IntefazServidor2.posicionY);
								IntefazServidor2.posicionX++;
								
								jsonEnviado.put("nuevaX", IntefazServidor2.posicionX);
								jsonEnviado.put("nuevaY", IntefazServidor2.posicionY);
								
								jsonEnviado.put("accion", color);
								jsonEnviado.put("color", 1);
								
								String mess = jsonEnviado.toString();
								HiloConsolaPantalla2.outPantalla.writeUTF(mess);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					else if (accionStr.equals("derecha")) {
						if (IntefazServidor2.dentroMatriz(IntefazServidor2.posicionX, IntefazServidor2.posicionY + 1)) {
							
							try {
								JSONObject jsonEnviado = new JSONObject();
								jsonEnviado.put("messageNum", "1");
								jsonEnviado.put("anterioX", IntefazServidor2.posicionX);
								jsonEnviado.put("anterioY", IntefazServidor2.posicionY);
								IntefazServidor2.posicionY++;
								
								jsonEnviado.put("nuevaX", IntefazServidor2.posicionX);
								jsonEnviado.put("nuevaY", IntefazServidor2.posicionY);
						
								jsonEnviado.put("accion", color);
								jsonEnviado.put("color", 1);
								String mess = jsonEnviado.toString();
								HiloConsolaPantalla2.outPantalla.writeUTF(mess);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					else if (accionStr.equals("izquierda")) {
						if (IntefazServidor2.dentroMatriz(IntefazServidor2.posicionX, IntefazServidor2.posicionY -1)) {
							
							try {
								JSONObject jsonEnviado = new JSONObject();
								jsonEnviado.put("messageNum", "1");
								jsonEnviado.put("anterioX", IntefazServidor2.posicionX);
								jsonEnviado.put("anterioY", IntefazServidor2.posicionY);
								IntefazServidor2.posicionY -- ;
								
								jsonEnviado.put("nuevaX", IntefazServidor2.posicionX);
								jsonEnviado.put("nuevaY", IntefazServidor2.posicionY);
						
								jsonEnviado.put("accion", color);
								jsonEnviado.put("color", 1);
								String mess = jsonEnviado.toString();
								HiloConsolaPantalla2.outPantalla.writeUTF(mess);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					
					else if (accionStr.equals("accion")) {
						try {
							JSONObject jsonEnviado = new JSONObject();
							jsonEnviado.put("messageNum", "1");
							jsonEnviado.put("anterioX", IntefazServidor2.posicionX);
							jsonEnviado.put("anterioY", IntefazServidor2.posicionY);
							
							
							jsonEnviado.put("nuevaX", IntefazServidor2.posicionX);
							jsonEnviado.put("nuevaY", IntefazServidor2.posicionY);
							
							jsonEnviado.put("color", 1);
							
							
							if (color >= 7) {
								color = 1;
								jsonEnviado.put("accion", color);
							}
							else {
								color ++;
								jsonEnviado.put("accion", color);
							}
							
							
							String mess = jsonEnviado.toString();
							HiloConsolaPantalla2.outPantalla.writeUTF(mess);
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

