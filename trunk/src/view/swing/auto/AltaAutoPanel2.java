package view.swing.auto;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AltaAutoPanel2 extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField modelo;
	private JTextField patente;
	private JTextField anio;
	private JTextField kilometraje;
	private JTextField combustible;


	private JLabel clienteLabel;
	private JLabel modeloLabel;
	private JLabel patenteLabel;
	private JLabel anioLabel;
	private JLabel kilometrajeLabel;
	private JLabel combustibleLabel;

	public AltaAutoPanel2() {
		this.init();
		this.setLayout(null);
	}

	private void init() {

		this.clienteLabel = new JLabel();
		this.clienteLabel.setBounds(20, 10, 100, 20);
		this.clienteLabel.setText("Datos Auto");
		this.clienteLabel.setAlignmentX(RIGHT_ALIGNMENT);
		this.add(clienteLabel);

		this.modeloLabel = new JLabel();
		this.modeloLabel.setBounds(20, 30, 50, 20);
		this.modeloLabel.setText("Modelo:");
		this.modeloLabel.setAlignmentX(RIGHT_ALIGNMENT);
		this.add(modeloLabel);

		this.modelo = new JTextField();
		this.modelo.setBounds(70, 30, 80, 20);
		this.add(modelo);

		this.patenteLabel = new JLabel();
		this.patenteLabel.setBounds(150, 30, 50, 20);
		this.patenteLabel.setText("Patente:");
		this.patenteLabel.setAlignmentX(RIGHT_ALIGNMENT);
		this.add(patenteLabel);

		this.patente = new JTextField();
		this.patente.setBounds(200, 30, 80, 20);
		this.add(patente);

		this.kilometrajeLabel = new JLabel();
		this.kilometrajeLabel.setBounds(280, 30, 60, 20);
		this.kilometrajeLabel.setText("kilometraje:");
		this.kilometrajeLabel.setAlignmentX(RIGHT_ALIGNMENT);
		this.add(kilometrajeLabel);

		this.kilometraje = new JTextField();
		this.kilometraje.setBounds(340, 30, 80, 20);
		this.add(kilometraje);

		this.anioLabel = new JLabel();
		this.anioLabel.setBounds(20, 50, 125, 20);
		this.anioLabel.setText("Fecha de Nacimiento:");
		this.anioLabel.setAlignmentX(RIGHT_ALIGNMENT);
		this.add(anioLabel);

		this.anio = new JTextField();
		this.anio.setBounds(145, 50, 80, 20);
		this.add(anio);

		this.combustibleLabel = new JLabel();
		this.combustibleLabel.setBounds(20, 70, 60, 20);
		this.combustibleLabel.setText("combustible:");
		this.combustibleLabel.setAlignmentX(RIGHT_ALIGNMENT);
		this.add(combustibleLabel);

		this.combustible = new JTextField();
		this.combustible.setBounds(80, 70, 80, 20);
		this.add(combustible);


	}

}
