package pantalla;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.json.simple.JSONObject;

import servidor.interfazServidor;

import java.net.*;
import java.io.*;


public class PantallaGUI {
	
	
	static Socket segundo;
	static Socket pantalla;
	static DataInputStream dis;
	static DataOutputStream dout;
	

	private JFrame frame;
	
	
	public void cargarMatriz() {
		for (int i=0; i<50; i++) {
			for (int j=0; j<50; j++) {
				JButton btnNewButton = new JButton ();
				btnNewButton.setBackground(Color.red);
				btnNewButton.setOpaque(true);
				interfazServidor.matriz[i][j] =  btnNewButton;
				frame.getContentPane().add(btnNewButton); 
			}
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaGUI window = new PantallaGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		try {

			String message = "";
			pantalla = new Socket ("192.168.88.20",5000);
			dis = new DataInputStream(pantalla.getInputStream());
			dout = new DataOutputStream (pantalla.getOutputStream());
			
			while (!message.equals("exit")) {
				message = dis.readUTF();
				System.out.println("entraAqui");
				System.out.println(message);
				
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Create the application.
	 */
	public PantallaGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 701, 700);
		frame.getContentPane().setLayout(new GridLayout(50,50));
		/*
		for (int i=0;i<50;i++) {
			for (int j=0;j<50;j++) {
				if (i==0 && j==0) {
					JButton btnNewButton = new JButton ();
					btnNewButton.setBackground(Color.red);
					btnNewButton.setOpaque(true);
					btnNewButton.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							
						try {
							JSONObject jsonEnviado = new JSONObject();
							jsonEnviado.put("prueba", "pantalla");
							String mess = jsonEnviado.toString();
							dout.writeUTF(mess);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						}
					});
					frame.getContentPane().add(btnNewButton); 
				}
				else {
					JButton btnNewButton = new JButton ();
					btnNewButton.setBackground(Color.gray);
					btnNewButton.setOpaque(true);
					frame.getContentPane().add(btnNewButton); 
				}
			}
		}*/
		cargarMatriz();
	
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	

}}
	
