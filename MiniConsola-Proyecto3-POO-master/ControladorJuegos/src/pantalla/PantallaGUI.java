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

import servidor.interfazServidor;

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
	static Color color6;
	static Color color7;
	static Color color8;

	

	private static JFrame frame = new JFrame();
	
	
	public static void cargarMatriz() {
		for (int i=0; i<50; i++) {
			for (int j=0; j<50; j++) {
				JButton btnNewButton = new JButton ();
				btnNewButton.setBackground(Color.gray);
				btnNewButton.setOpaque(true);
				matriz[i][j] =  btnNewButton;
				frame.getContentPane().add(btnNewButton);
				/*
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
				}*/
			}
		}
	}
	public static int[][] retornaMatriz (JSONArray json){
		
		int newMatriz[][] = new int[50][50];
		for (int i=0; i<50; i++) {
			JSONArray array = (JSONArray) json.get(i);
			for (int j=0; j<50; j++) {
				JSONObject valor = (JSONObject) array.get(j);
				int numero = Integer.parseInt(valor.get("valor").toString());
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
	
	public static void cargarMatrizLogica() {
	
		//cargarMatriz();
		for (int i=0; i<50; i++) {
			for (int j=0; j<50; j++) {
				System.out.println("i"+i);
				System.out.println("j"+j);
				if (matrizLogica[i][j] == 0) {
					matriz[i][j].setBackground(color2);
				}
				if (matrizLogica[i][j] == 1) {
					matriz[i][j].setBackground(Color.BLACK);
				}
				
				
			}
		}
		//matriz[interfazServidor.posicionX][interfazServidor.posicionY].setBackground(color1);
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
		if (color.equals("gray")) 
			return Color.gray;
		if (color.equals("MAGENTA")) 
			return Color.MAGENTA;
		if (color.equals("WHITE")) 
			return Color.white;
		if (color.equals("CYAN")) 
			return Color.CYAN;
		if (color.equals("grey")) 
			return Color.gray;
	
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
			color6 = selectColor (json.get("color6").toString());
			color7 = selectColor (json.get("color7").toString());
			color8 = selectColor (json.get("color8").toString());
			
			JSONArray matrizJson = (JSONArray)json.get("matrizLogica");
			matrizLogica = retornaMatriz(matrizJson);
			imprimeMatriz (matrizLogica);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void cambiosPantalla(int newX, int newY, int oldX, int oldY, int colorNew, int colorOld) {
		
		if (colorOld == 0) 
			matriz[newX][newY].setBackground(color1);
		else if (colorOld == 1) 
			matriz[newX][newY].setBackground(color2);
		
		else if (colorOld == 2) 
			matriz[newX][newY].setBackground(color3);
		
		else if (colorOld == 3) 
			matriz[newX][newY].setBackground(color4);
		
		else if (colorOld == 4) 
			matriz[newX][newY].setBackground(color5);
		
		else if (colorOld == 5) 
			matriz[newX][newY].setBackground(color6);
		
		else if (colorOld == 6) 
			matriz[newX][newY].setBackground(color7);
		
		else if (colorOld == 7) 
			matriz[newX][newY].setBackground(color8);
		////////////////
		if (colorNew == 1) 
			matriz[oldX][oldY].setBackground(color2);
		
		else if (colorNew == 2) 
			matriz[oldX][oldY].setBackground(color3);
		
		else if (colorNew == 3) 
			matriz[oldX][oldY].setBackground(color4);
		
		else if (colorNew == 4) 
			matriz[oldX][oldY].setBackground(color5);
		
		else if (colorNew == 5) 
			matriz[oldX][oldY].setBackground(color6);
		
		else if (colorNew == 6) 
			matriz[oldX][oldY].setBackground(color7);
		
		else if (colorNew == 7) 
			matriz[oldX][oldY].setBackground(color8);
		
		
		
	

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
						cargarMatrizLogica();
						
					}
					
					if (json.get("messageNum").toString().equals("1")) {
						//cargarMatrizLogica();
						int nuevaX = Integer.parseInt(json.get("nuevaX").toString());
						int nuevaY = Integer.parseInt(json.get("nuevaY").toString());
						
						int anterioX = Integer.parseInt(json.get("anterioX").toString());
						int anterioY = Integer.parseInt(json.get("anterioY").toString());
						
						int color = Integer.parseInt(json.get("accion").toString());
						int colorNew = Integer.parseInt(json.get("color").toString());

						
						cambiosPantalla(nuevaX, nuevaY, anterioX, anterioY, color, colorNew);
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
		
		frame.setBounds(100, 100, 701, 700);
		frame.getContentPane().setLayout(new GridLayout(50,50));

		cargarMatriz();
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	

}}
	
