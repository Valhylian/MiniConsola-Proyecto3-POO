package pantalla;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.*;
import java.io.*;


public class PantallaGUI {
	int color = 0;
	public static JButton matriz[][] = new JButton[50][50];
	static Socket segundo;
	static Socket pantalla;
	static DataInputStream dis;
	static DataOutputStream dout;
	

	private JFrame frame;
	
	
	public void cargarMatriz() {
		for (int i=0; i<50; i++) {
			for (int j=0; j<50; j++) {
				if (i==24 && j==24) {
					JButton btnNewButton = new JButton ();
					btnNewButton.setBackground(Color.red);
					btnNewButton.setOpaque(true);
					matriz[i][j] =  btnNewButton;
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
					matriz[i][j] =  btnNewButton;
					frame.getContentPane().add(btnNewButton); 
				}
				
			}
		}
	}
	
	public static void cambiosPantalla(int newX, int newY, int oldX, int oldY, int color) {
		matriz[newX][newY].setBackground(Color.RED);
		if (color == 0) 
			matriz[oldX][oldY].setBackground(Color.orange);
		
		else if (color == 1) 
			matriz[oldX][oldY].setBackground(Color.blue);
		
		else if (color == 2) 
			matriz[oldX][oldY].setBackground(Color.pink);
		
		else if (color == 3) 
			matriz[oldX][oldY].setBackground(Color.MAGENTA);
		
		else if (color == 4) 
			matriz[oldX][oldY].setBackground(Color.white);
		
		else if (color == 5) 
			matriz[oldX][oldY].setBackground(Color.yellow);
		
		else if (color == 6) 
			matriz[oldX][oldY].setBackground(Color.CYAN);
		
		else if (color == 7) 
			matriz[oldX][oldY].setBackground(Color.black);
		
		else if (color == 8) 
			matriz[oldX][oldY].setBackground(Color.DARK_GRAY);
		
		else if (color == 9) 
			matriz[oldX][oldY].setBackground(Color.LIGHT_GRAY);
		
		else if (color == 10) 
			matriz[oldX][oldY].setBackground(Color.red);
		
		else if (color == 11) 
			matriz[oldX][oldY].setBackground(Color.gray);

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
				JSONParser parser = new JSONParser();
				try {
					JSONObject json = (JSONObject) parser.parse(message);
					
					int nuevaX = Integer.parseInt(json.get("nuevaX").toString());
					int nuevaY = Integer.parseInt(json.get("nuevaY").toString());
					
					int anterioX = Integer.parseInt(json.get("anterioX").toString());
					int anterioY = Integer.parseInt(json.get("anterioY").toString());
					
					int color = Integer.parseInt(json.get("accion").toString());

					
					cambiosPantalla(nuevaX, nuevaY, anterioX, anterioY, color);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
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
	
