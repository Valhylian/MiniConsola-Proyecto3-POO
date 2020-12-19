package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class HiloConsolaPantalla2 extends Thread {
	static ServerSocket segundo;
	static Socket pantalla;
	static DataInputStream dis;
	static DataOutputStream outPantalla;
	
	//matriz logica----------------------------------------
	public static int matriz[][] = new int[50][50];

	public static void llenarMatriz() {
		for (int i=0; i<50; i++) {
			for (int j=0; j<50; j++) {
				
				if (j == 3) {
					matriz[i][j] =  1;
					if (i ==49) {
						matriz[i][j] =  0;
					}
				}
				else if (j == 5) {
					matriz[i][j] =  1;
					if (i ==24) {
						matriz[i][j] =  0;
					}
				}
				else if (j == 7) {
					matriz[i][j] =  1;
					if (i ==1) {
						matriz[i][j] =  0;
					}
				}
				else if (j == 9) {
					matriz[i][j] =  1;
					if (i ==32) {
						matriz[i][j] =  0;
					}
				}
				else{
					matriz[i][j] =  0;
				}
			}
		}
	}
	//////////////////////////////////////////////////////////
	public static JSONArray matriz_Json () {
		JSONArray jsonarray = new JSONArray();
		for (int i=0; i<50; i++) {
			JSONArray jsonFila = new JSONArray();
			for (int j=0; j<50; j++) {
				JSONObject jsonValor = new JSONObject();
				jsonValor.put("valor", matriz[i][j]);
				jsonFila.add(jsonValor);
			}
			jsonarray.add(jsonFila);
		}
		System.out.println(jsonarray);
		return jsonarray;
	}
	//////////////////////////////////////////////////////////////
	
	
	public HiloConsolaPantalla2 (String name) {
		super(name);
	}
	
	public void run() {
		try {
			String message = "";
			segundo = new ServerSocket (5000);
			pantalla = segundo.accept();
			dis = new DataInputStream(pantalla.getInputStream());
			outPantalla = new DataOutputStream (pantalla.getOutputStream());
			
			//PRIMER MENSAJE A PANTALLA (ASIGNA LOS COLORES)
			JSONObject jsonEnviado = new JSONObject();
			//prueba.
			jsonEnviado.put("messageNum", "0");
			jsonEnviado.put("color1", "green");
			jsonEnviado.put("color2", "WHITE");
			jsonEnviado.put("color3", "grey");
			jsonEnviado.put("color4", "yellow");
			jsonEnviado.put("color5", "pink");
			jsonEnviado.put("color6", "blue");
			jsonEnviado.put("color7", "MAGENTA");
			jsonEnviado.put("color8", "red");
			llenarMatriz();
			jsonEnviado.put("matrizLogica", matriz_Json ()); //envio la matriz inicial
			String mensajeJson = jsonEnviado.toString();
			HiloConsolaPantalla2.outPantalla.writeUTF(mensajeJson);
			//////////////////////////////////////////////////////////
			
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
