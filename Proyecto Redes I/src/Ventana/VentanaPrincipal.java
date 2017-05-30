package Ventana;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Logica.Redes;

public class VentanaPrincipal extends JFrame {

	/*
	 * Declaracion de las funciones de interfaz.
	 */
	public JPanel contentPane;
	public JTextArea txtVisor;
	public JButton btnResolver;

	// vacia

	public static void main(String[] args) 
	{
		VentanaPrincipal vent = new VentanaPrincipal();

		vent.setTitle("Proyecto Redes I");
		vent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vent.setSize(800, 650);
		vent.setLocationRelativeTo(null);
		vent.repaint();
		vent.setVisible(true);
		vent.setResizable(false);

	}

	/**
	 * Creacion de la ventana principal.
	 */
	public VentanaPrincipal() {

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);

		setContentPane(contentPane);

		txtVisor = new JTextArea();
		// setBounds(x, y, width, height);
		txtVisor.setBounds(19, 200, 758, 400);
		txtVisor.setFont(new Font("Arial", Font.BOLD, 18));
		txtVisor.setBackground(new Color(224, 255, 255));

		JScrollPane scrollPane = new JScrollPane(txtVisor);
		scrollPane.setBounds(719, 623, 852, 23);

		txtVisor.setColumns(10);

		JLabel lblFormula = new JLabel("Consola");
		lblFormula.setBounds(20, 130, 495, 114);
		lblFormula.setFont(new Font("Arial", Font.BOLD, 20));

		JButton btnHost = new JButton("Reconocer Host");
		btnHost.setBounds(200, 45, 160, 41);
		btnHost.setFont(new Font("Arial", Font.BOLD, 12));
		btnHost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Redes red = new Redes(0, 0);

				String t = "";

				try {
					t=red.reconociendoHost();
					txtVisor.setText(t);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}

				repaint();

			}
		});
		/*
		 * Boton encargado de llamar el metodo de generar puertos y mostrarlo en interfaz.
		 */
		JButton btnPuerto = new JButton("Generar Puertos");
		btnPuerto.setBounds(380, 45, 160, 41);
		btnPuerto.setFont(new Font("Arial", Font.BOLD, 12));
		btnPuerto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String t="";
				Redes red = new Redes(0,0);
				
				red.generarHilo();
				t=red.p;
				txtVisor.setText(t);

				
				repaint();

			}
		});

		/*
		 * Boton encargado de llamar el metodo de generar interface y mostrarlo en interfaz.
		 */
		JButton btnInterface = new JButton("Generar Interface");
		btnInterface.setBounds(560, 45, 160, 41);
		btnInterface.setFont(new Font("Arial", Font.BOLD, 12));
		btnInterface.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Redes red = new Redes(0,0);		
				try {
				String t = red.generarInterface();				
				txtVisor.setText(t);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		/*
		 * Boton encargado cerrar la aplicacion.
		 */
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(670, 150, 90, 32);
		btnSalir.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{				
				System.exit(0);
			}
		});
		/*
		 * Boton encargado de limpiar la ventana.
		 */
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(560, 150, 90, 32);
		btnLimpiar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				txtVisor.setText("");
				if(txtVisor.getText().equals("")){
					JOptionPane.showMessageDialog(null,
							"El panel de informacion ya esta vacio",
							"Alerta !!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		contentPane.add(btnSalir);
		contentPane.add(btnPuerto);
		contentPane.add(scrollPane);
		contentPane.add(btnHost);
		contentPane.add(btnLimpiar);
		contentPane.add(btnInterface);
		contentPane.add(lblFormula);
		contentPane.add(txtVisor);

	}
}
