package cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextPane;

import org.json.simple.JSONObject;  

import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class interfazCliente {
	private static JTextPane textPane; 

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfazCliente window = new interfazCliente();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public interfazCliente() {
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
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(38, 237, 185, 43);
		frame.getContentPane().add(textPane);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(103, 27, 55, 52);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					
					
					JSONObject jsonEnviado = new JSONObject();
					jsonEnviado.put("presionado", "arriba");
					String Texto = jsonEnviado.toString();
					Socket cliente = new Socket("192.168.88.20",5000);
					ObjectOutputStream mensaje = new ObjectOutputStream(cliente.getOutputStream());
					mensaje.writeObject(Texto);
					
					ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
					String mensaje2 = (String) entrada.readObject();
					textPane.setText(mensaje2);
					cliente.close();
					
				} catch (IOException ex) {
					Logger.getLogger(interfazCliente.class.getName()).log(Level.SEVERE, null, ex);
				} catch (ClassNotFoundException ex) {
				
					Logger.getLogger(interfazCliente.class.getName()).log(Level.SEVERE, null, ex);
				
				
			}
			}
		});
		
		JButton button = new JButton("");
		button.setBounds(103, 90, 55, 52);
		frame.getContentPane().add(button);
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					
					
					
					String Texto = "EJECUTA ACCION";
					String texto2 = textPane.getText().toString();
					Socket cliente = new Socket("192.168.88.20",5000);
					ObjectOutputStream mensaje = new ObjectOutputStream(cliente.getOutputStream());
					mensaje.writeObject(Texto);
					
					ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
					String mensaje2 = (String) entrada.readObject();
					textPane.setText(mensaje2);
					cliente.close();
					
				} catch (IOException ex) {
					Logger.getLogger(interfazCliente.class.getName()).log(Level.SEVERE, null, ex);
				} catch (ClassNotFoundException ex) {
				
					Logger.getLogger(interfazCliente.class.getName()).log(Level.SEVERE, null, ex);
				
				
			}
			}
		});
		
		
		JButton button_1 = new JButton("");
		button_1.setBounds(168, 90, 55, 52);
		frame.getContentPane().add(button_1);
		button_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					
					
					
					String Texto = "PRESIONA FLECHA DERECHA";
					String texto2 = textPane.getText().toString();
					Socket cliente = new Socket("192.168.88.20",5000);
					ObjectOutputStream mensaje = new ObjectOutputStream(cliente.getOutputStream());
					mensaje.writeObject(Texto);
					
					ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
					String mensaje2 = (String) entrada.readObject();
					textPane.setText(mensaje2);
					cliente.close();
					
				} catch (IOException ex) {
					Logger.getLogger(interfazCliente.class.getName()).log(Level.SEVERE, null, ex);
				} catch (ClassNotFoundException ex) {
				
					Logger.getLogger(interfazCliente.class.getName()).log(Level.SEVERE, null, ex);
				
				
			}
			}
		});
		
		
		JButton button_2 = new JButton("");
		button_2.setBounds(38, 90, 55, 52);
		frame.getContentPane().add(button_2);
		button_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					
					
					
					String Texto = "PRESIONA FLECHA IZQUIERDA";
					String texto2 = textPane.getText().toString();
					Socket cliente = new Socket("192.168.88.20",5000);
					ObjectOutputStream mensaje = new ObjectOutputStream(cliente.getOutputStream());
					mensaje.writeObject(Texto);
					
					ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
					String mensaje2 = (String) entrada.readObject();
					textPane.setText(mensaje2);
					cliente.close();
					
				} catch (IOException ex) {
					Logger.getLogger(interfazCliente.class.getName()).log(Level.SEVERE, null, ex);
				} catch (ClassNotFoundException ex) {
				
					Logger.getLogger(interfazCliente.class.getName()).log(Level.SEVERE, null, ex);
				
				
			}
			}
		});
		
		JButton button_3 = new JButton("");
		button_3.setBounds(103, 153, 55, 52);
		frame.getContentPane().add(button_3);
		button_3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					
					
					
					String Texto = "PRESIONA FLECHA ABAJO";
					String texto2 = textPane.getText().toString();
					Socket cliente = new Socket("192.168.88.20",5000);
					ObjectOutputStream mensaje = new ObjectOutputStream(cliente.getOutputStream());
					mensaje.writeObject(Texto);
					
					ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
					String mensaje2 = (String) entrada.readObject();
					textPane.setText(mensaje2);
					cliente.close();
					
				} catch (IOException ex) {
					Logger.getLogger(interfazCliente.class.getName()).log(Level.SEVERE, null, ex);
				} catch (ClassNotFoundException ex) {
				
					Logger.getLogger(interfazCliente.class.getName()).log(Level.SEVERE, null, ex);
				
				
			}
			}
		});
		
		
		
		
	}
	
	

}