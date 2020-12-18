package servidor;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.net.*;

import cliente.Controlador;


public class interfazServidor {
	
	public static int posicionX = 24;
	public static int posicionY = 24;
	private JFrame frame;
	public static JTextField recibidoServidor;
	public JTextField enviarServidor;
	
	/*
	static ServerSocket ss;
	static Socket s;
	static ServerSocket segundo;
	static Socket pantalla;
	static DataInputStream dis;
	static DataOutputStream dout;*/

	/**
	 * Launch the application.
	 */
	
	public static boolean dentroMatriz(int x, int y) {
		if (x>=0 && x<50 && y>=0 && y<50) {
			return true;
		}
		return false;
	}
	public static void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfazServidor window = new interfazServidor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		Thread hilo1 = new Hilo("conexion 1");
		hilo1.start();
		Thread hilo2 = new HiloConsolaPantalla ("conexion 2");
		hilo2.start();
		/*
		try {
			String message = "";
			ss = new ServerSocket (9000);
			s = ss.accept();
			dis = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream (s.getOutputStream());
			
			while (!message.equals("exit")) {
				message = dis.readUTF();
				recibidoServidor.setText(message);
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	/**
	 * Create the application.
	 */
	public interfazServidor () {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton iniciarServidor = new JButton("INICIAR SERVIDOR");
		iniciarServidor.setBounds(286, 102, 138, 23);
		frame.getContentPane().add(iniciarServidor);
		iniciarServidor.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				try {
					String mess = enviarServidor.getText();
					Hilo.dout.writeUTF(mess);
					enviarServidor.setText("");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		recibidoServidor = new JTextField();
		recibidoServidor.setBounds(10, 32, 235, 63);
		frame.getContentPane().add(recibidoServidor);
		recibidoServidor.setColumns(10);
		
		enviarServidor = new JTextField();
		enviarServidor.setBounds(10, 137, 235, 72);
		frame.getContentPane().add(enviarServidor);
		enviarServidor.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("RECIBIDO:");
		lblNewLabel.setBounds(10, 11, 95, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblEnviarRespuesta = new JLabel("ENVIAR RESPUESTA:");
		lblEnviarRespuesta.setBounds(10, 111, 177, 14);
		frame.getContentPane().add(lblEnviarRespuesta);
	}


	private void presionaFlechaArriba(java.awt.event.ActionEvent evt) {
		
}

}
