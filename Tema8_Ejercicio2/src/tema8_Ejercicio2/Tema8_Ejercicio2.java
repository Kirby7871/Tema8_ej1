package tema8_Ejercicio2;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Tema8_Ejercicio2 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tema8_Ejercicio2 window = new Tema8_Ejercicio2();
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
	public Tema8_Ejercicio2() {
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
		
		JComboBox provinciacomboBox = new JComboBox();
		provinciacomboBox.setBounds(269, 31, 116, 24);
		frame.getContentPane().add(provinciacomboBox);
		
		JComboBox comunidadcomboBox = new JComboBox();
		
		comunidadcomboBox.setBounds(38, 31, 171, 24);
		frame.getContentPane().add(comunidadcomboBox);
		
		JLabel lblComunidad = new JLabel("Comunidad:");
		lblComunidad.setBounds(38, 4, 116, 15);
		frame.getContentPane().add(lblComunidad);
		
		JLabel lblProvincia = new JLabel("Provincia:");
		lblProvincia.setBounds(269, 4, 89, 15);
		frame.getContentPane().add(lblProvincia);
		
		String url = "jdbc:mysql://127.0.0.1:3307/Provincias";
		String user = "alumno";
		String password = "alumno";
		
		int idcomunidad;
		int idprovincia;
		String nombrecomunidad;
		String nombreprovincia;
		
		comunidadcomboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					Connection con = DriverManager.getConnection(url, user, password);
					
					provinciacomboBox.removeAllItems();
					
					PreparedStatement stmt = con.prepareStatement("SELECT * FROM Provincia where codComunidad = ?");
					stmt.setInt(1, comunidadcomboBox.getSelectedIndex() + 1);
					ResultSet rs = stmt.executeQuery();
					
					
					
					while(rs.next()) {
						provinciacomboBox.addItem(rs.getString("Nombre"));
					}
					rs.close();
					stmt.close();
					con.close();
					
					
					
				} catch(SQLException e) {
					System.err.println(e.getMessage());
					 e.getErrorCode();
					 e.printStackTrace();
				}
			}
		});
		
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Comunidad");
			while(rs.next()) {
				comunidadcomboBox.addItem(rs.getString("Nombre"));
			}
			rs.close();
			stmt.close();
			con.close();
			
			
		} catch(SQLException e) {
			System.err.println(e.getMessage());
			 e.getErrorCode();
			 e.printStackTrace();
		}
		
	}
}
