package cliente;

import java.io.IOException;

import org.json.simple.JSONObject;

public class ThreadPress extends Thread{

	public ThreadPress (String name) {
		super(name);
	}
	
	public void run() {
		int i= 0;
		while (Controlador.pressArriba || Controlador.pressAbajo || Controlador.pressDerecha) {
			System.out.println(i);
			i ++; 
			if (Controlador.pressArriba) {
				try {
					JSONObject jsonEnviado = new JSONObject();
					jsonEnviado.put("presionado", "arriba");
					String mess = jsonEnviado.toString();
					Controlador.dout.writeUTF(mess);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (Controlador.pressAbajo) {
				try {
					JSONObject jsonEnviado = new JSONObject();
					jsonEnviado.put("presionado", "arriba");
					String mess = jsonEnviado.toString();
					Controlador.dout.writeUTF(mess);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (Controlador.pressDerecha) {
				try {
					JSONObject jsonEnviado = new JSONObject();
					jsonEnviado.put("presionado", "derecha");
					String mess = jsonEnviado.toString();
					Controlador.dout.writeUTF(mess);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
