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

import cliente.interfazCliente;


public class interfazServidor {

	private JFrame frame;
	private static JTextField recibidoServidor;
	private JTextField enviarServidor;
	
	static ServerSocket ss;
	static Socket s;
	static DataInputStream dis;
	static DataOutputStream dout;

	/**
	 * Launch the application.
	 */
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
		
		try {
			String message = "";
			ss = new ServerSocket (5000);
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
		}
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
				/*
				try {
			
					String Texto2 = enviarServidor.getText().toString();
					ServerSocket servidor = new ServerSocket(5000);
					Socket clienteNuevo = servidor.accept();
					ObjectInputStream entrada = new ObjectInputStream(clienteNuevo.getInputStream());
					
					String Mensaje = (String) entrada.readObject();
					recibidoServidor.setText(Mensaje);
					
					ObjectOutputStream respuesta = new ObjectOutputStream(clienteNuevo.getOutputStream());
					respuesta.writeObject(Texto2);
					servidor.close();
					clienteNuevo.close();
					
				} catch (IOException ex) {
					Logger.getLogger(interfazCliente.class.getName()).log(Level.SEVERE, null, ex);
				} catch (ClassNotFoundException ex) {
				
					Logger.getLogger(interfazCliente.class.getName()).log(Level.SEVERE, null, ex);
				
				
			}*/
				
				try {
					String mess = enviarServidor.getText();
					dout.writeUTF(mess);
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
