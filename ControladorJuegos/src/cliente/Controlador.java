package cliente;

import java.awt.EventQueue;

import java.net.*;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextPane;

import org.json.simple.JSONObject;

import servidor.Hilo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class Controlador {
	private static JTextPane textPane; 
	static Socket s;
	static DataInputStream dis;
	static DataOutputStream dout;
	static DataOutputStream outPantalla;
	static int i = 0;
	
	static boolean pressArriba = false; 
	static boolean pressAbajo = false; 
	static boolean pressIzquierda = false; 
	static boolean pressDerecha = false; 
	static boolean pressAccion = false; 
	

	private JFrame frame;

	//FORMATO DEL JSON CONTROLADOR-CONSOLA
	public static void enviarFormatoJSON(String contendio) {
		try {
			JSONObject jsonEnviado = new JSONObject();
			jsonEnviado.put("presionado", contendio);
			String mess = jsonEnviado.toString();
			Controlador.dout.writeUTF(mess);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	public static void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controlador window = new Controlador();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		try {

			String message = "";
			s = new Socket ("192.168.88.20",9000);
			dis = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream (s.getOutputStream());
			
			while (!message.equals("exit")) {
				message = dis.readUTF();
				System.out.println("auiiiiii");
				textPane.setText("\n Servidor: "+ message + i++);
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public Controlador() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 274, 351);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textPane = new JTextPane();
		textPane.setBounds(38, 237, 185, 43);
		frame.getContentPane().add(textPane);
		
		//BOTON ARRIBA
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(103, 27, 55, 52);
		frame.getContentPane().add(btnNewButton);
	
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				pressArriba = true;
				Thread hiloEje = new ThreadPress(" boton");
				hiloEje.start();
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				pressArriba = false;
			}
		});
		
		//BOTON ACCION
		JButton button = new JButton("");
		button.setBounds(103, 90, 55, 52);
		frame.getContentPane().add(button);
		button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				pressAccion = true;
				Thread hiloEje = new ThreadPress(" boton");
				hiloEje.start();

			}
		});
		button.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				pressAccion = false;
			}
			
		});
		
		
		//BOTON DERECHA
		JButton button_1 = new JButton("");
		button_1.setBounds(168, 90, 55, 52);
		frame.getContentPane().add(button_1);
		button_1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				pressDerecha = true;
				Thread hiloEje = new ThreadPress(" boton");
				hiloEje.start();

			}
		});
		button_1.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				pressDerecha = false;
			}
			
		});
		
		//BOTON IZQUIERDA
		JButton button_2 = new JButton("");
		button_2.setBounds(38, 90, 55, 52);
		frame.getContentPane().add(button_2);
		button_2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				pressIzquierda = true;
				Thread hiloEje = new ThreadPress(" boton");
				hiloEje.start();

			}
		});
		button_2.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				pressIzquierda = false;
			}
		});
		
		
		//BOTON ABAJO
		JButton button_3 = new JButton("");
		button_3.setBounds(103, 153, 55, 52);
		frame.getContentPane().add(button_3);
		button_3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				pressAbajo = true;
				Thread hiloEje = new ThreadPress(" boton");
				hiloEje.start();

			}
		});
		button_3.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				pressAbajo = false;
			}
		});
		
		
		
		
	}
	
	

}