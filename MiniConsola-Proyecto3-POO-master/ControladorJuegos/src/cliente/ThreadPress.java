package cliente;

import java.io.IOException;

import org.json.simple.JSONObject;

public class ThreadPress extends Thread{

	public ThreadPress (String name) {
		super(name);
	}
	
	public void run() {
		
		while (Controlador.pressArriba || Controlador.pressAbajo || Controlador.pressDerecha || Controlador.pressIzquierda || Controlador.pressAccion) {
			
			if (Controlador.pressArriba) {
				Controlador.enviarFormatoJSON("arriba");
			}
			if (Controlador.pressAbajo) {
				Controlador.enviarFormatoJSON("abajo");
			}
			if (Controlador.pressDerecha) {
				Controlador.enviarFormatoJSON("derecha");
			}
			if (Controlador.pressIzquierda) {
				Controlador.enviarFormatoJSON("izquierda");
			}
			if (Controlador.pressAccion) {
				Controlador.enviarFormatoJSON("accion");
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
