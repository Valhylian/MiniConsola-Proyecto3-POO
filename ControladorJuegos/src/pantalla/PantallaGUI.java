package pantalla;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;


public class PantallaGUI {

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
		frame.setBounds(100, 100, 1066, 721);
		frame.setLayout(new GridLayout(50,50));
		for (int i=0;i<50;i++) {
			for (int j=0;j<50;j++) {
				JButton btnNewButton = new JButton ();
				btnNewButton.setBackground(new Color(0,0,0,0));
				btnNewButton.setOpaque(false);
				frame.getContentPane().add(btnNewButton); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}}}
	
