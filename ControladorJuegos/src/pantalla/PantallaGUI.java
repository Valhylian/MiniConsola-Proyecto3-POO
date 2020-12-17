package pantalla;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.net.*;
import java.io.*;


public class PantallaGUI {
	static int posicionX = 0;
	static int posicionY = 0;
	
	static Socket pantalla;
	static DataInputStream dis;
	static DataOutputStream dout;
	

	private JFrame frame;

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
		for (int i=0;i<50;i++) {
			for (int j=0;j<50;j++) {
				if (i==posicionX && j==posicionY) {
					JButton btnNewButton = new JButton ();
					btnNewButton.setBackground(Color.red);
					btnNewButton.setOpaque(true);
					frame.getContentPane().add(btnNewButton); 
				}
				else {
					JButton btnNewButton = new JButton ();
					btnNewButton.setBackground(Color.gray);
					btnNewButton.setOpaque(true);
					frame.getContentPane().add(btnNewButton); 
				}
				
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}}}
	
