package pantalla;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.*;
import java.io.*;
import java.lang.reflect.Array;

public class PantallaGUI {

	public static JButton matriz[][] = new JButton[50][50];
	public static int matrizLogica[][] = new int [50][50];
	static Socket segundo;
	static Socket pantalla;
	static DataInputStream dis;
	static DataOutputStream dout;
	
	static Color color1;
	static Color color2;
	static Color color3;
	static Color color4;
	static Color color5;

	

	private JFrame frame;
	
	
	public void cargarMatriz() {
		for (int i=0; i<50; i++) {
			for (int j=0; j<50; j++) {
				if (i==24 && j==24) {
					JButton btnNewButton = new JButton ();
					btnNewButton.setBackground(Color.red);
					btnNewButton.setOpaque(true);
					matriz[i][j] =  btnNewButton;
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
	public static int[][] retornaMatriz (JSONArray json){
		int newMatriz[][] = new int[50][50];
		for (int i=0; i<50; i++) {
			JSONArray array = (JSONArray) json.get(i);
			for (int j=0; j<50; j++) {
				JSONObject valor = (JSONObject) array.get(j);
				int numero = (Integer) valor.get("valor");
				newMatriz[i][j] = numero;
			}
		}
		return newMatriz;
	}
	
	public static void imprimeMatriz ( int[][]  matriz) {
		System.out.println();
		for (int i=0;i<50;i++) {
			for (int j=0;j<50;j++) {
				System.out.print(matriz[i][j]);
			}
			System.out.println();
	}}
	
	public void cargarMatrizLogica(int matrix[][]) {
		
	}
	
	//RETORNA EL COLOR
	public static Color selectColor (String color) {
		if (color.equals("red")) 
			return Color.red;
		if (color.equals("green")) 
			return Color.green;
		if (color.equals("blue")) 
			return Color.blue;
		if (color.equals("yellow")) 
			return Color.yellow;
		if (color.equals("pink")) 
			return Color.pink;
		return Color.BLACK;
		
	}
	
	//ASIGNAR COLORES
	public static void asignarColores(String mensajeJson) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject) parser.parse(mensajeJson);
			
			color1 = selectColor (json.get("color1").toString());
			color2 = selectColor (json.get("color2").toString());
			color3 = selectColor (json.get("color3").toString());
			color4 = selectColor (json.get("color4").toString());
			color5 = selectColor (json.get("color5").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void cambiosPantalla(int newX, int newY, int oldX, int oldY, int color) {
		
		matriz[newX][newY].setBackground(color1);
		
		if (color == 0) 
			matriz[oldX][oldY].setBackground(color2);
		
		else if (color == 1) 
			matriz[oldX][oldY].setBackground(color3);
		
		else if (color == 2) 
			matriz[oldX][oldY].setBackground(color4);
		
		else if (color == 3) 
			matriz[oldX][oldY].setBackground(color5);
		
	

	}

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
				
				JSONParser parser = new JSONParser();
				try {
					JSONObject json = (JSONObject) parser.parse(message);
					if (json.get("messageNum").toString().equals("0")) {
						asignarColores(message);
						System.out.println(color1);
						System.out.println(color2);
						
					}
					else {
						int nuevaX = Integer.parseInt(json.get("nuevaX").toString());
						int nuevaY = Integer.parseInt(json.get("nuevaY").toString());
						
						int anterioX = Integer.parseInt(json.get("anterioX").toString());
						int anterioY = Integer.parseInt(json.get("anterioY").toString());
						
						int color = Integer.parseInt(json.get("accion").toString());

						
						cambiosPantalla(nuevaX, nuevaY, anterioX, anterioY, color);
					}
					
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

	
	public PantallaGUI() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 701, 700);
		frame.getContentPane().setLayout(new GridLayout(50,50));

		cargarMatriz();
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	

}}
	
