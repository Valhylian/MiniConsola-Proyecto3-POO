package servidor;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class IntefazServidor2 {
	public static int posicionX = 0;
	public static int posicionY = 0;
	private JFrame frame;
	public static JTextField recibidoServidor;
	public JTextField enviarServidor;
	
	
	public static boolean dentroMatriz(int x, int y) {
		if (y==3 & x!=49) {
			return false;
		}
		if (y==7 & x!=24) {
			return false;
		}
		
		if (y==11 & x!=1) {
			return false;
		}
		if (y==15 & x!=32) {
			return false;
		}
		if (y==19 & x!=10) {
			return false;
		}
		if (y==23 & x!=40) {
			return false;
		}
		if (y==27 & x!=20) {
			return false;
		}
		if (y==31 & x!=0) {
			return false;
		}
		if (y==35 & x!=32) {
			return false;
		}
		if (y==39 & x!=45) {
			return false;
		}
		if (y==43 & x!=17) {
			return false;
		}
		if (y==47 & x!=20) {
			return false;
		}
		if (x>=0 && x<50 && y>=0 && y<50) {
			return true;
		}
		return false;
	}
	public static void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IntefazServidor2 window = new IntefazServidor2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread hilo1 = new HiloConsola2("conexion 1");
		hilo1.start();
		Thread hilo2 = new HiloConsolaPantalla2 ("conexion 2");
		hilo2.start();
	}

	public IntefazServidor2 () {
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


}

