package view.swing.alquiler;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.ErrorGui;
import util.RespuestaGui;
import util.Util;
import view.GuiEmpleado;
import view.vistas.AlquilerView;
import controller.AlquilerAutos;

public class RegistrarDevolucionPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 7027429706359909333L;

	private GuiEmpleado gui;
	private AlquilerAutos sistema;

	private JTextField busqueda;
	private JLabel busquedaLabel;
	private JButton buscarButton;

	private DatosDevolucionPanel datosDevolucionPanel;

	private JButton registrarDevolucionButton;

	public RegistrarDevolucionPanel(AlquilerAutos sistema, GuiEmpleado gui) {
		this.sistema = sistema;
		this.gui = gui;
		this.init();
	}

	private void init() {

		this.setLayout(new GridBagLayout());
		GridBagConstraints gBC = new GridBagConstraints();

		int y = 0;

		gBC.gridy = y;

		gBC.insets = new Insets(2, 2, 2, 2);
		gBC.anchor = GridBagConstraints.NORTHEAST;
		gBC.gridx = 0;
		this.busquedaLabel = new JLabel("Número Alquiler:");
		this.add(busquedaLabel, gBC);

		gBC.gridx = 1;
		this.busqueda = new JTextField(15);
		this.add(busqueda, gBC);

		gBC.gridx = 3;
		this.buscarButton = new JButton("Buscar");
		this.buscarButton.addActionListener(this);
		this.add(buscarButton, gBC);

		y++;

		gBC.gridy = y;
		gBC.gridx = 0;
		gBC.gridwidth = 4;
		this.datosDevolucionPanel = new DatosDevolucionPanel(sistema);
		// TODO : ver habilitación
		this.add(this.datosDevolucionPanel, gBC);

		y++;

		gBC.gridy = y;
		gBC.gridx = 3;
		gBC.gridwidth = 1;
		gBC.gridheight = 1;
		this.registrarDevolucionButton = new JButton("Registrar");
		this.registrarDevolucionButton.addActionListener(this);
		this.add(registrarDevolucionButton, gBC);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == this.buscarButton) {
			if (Util.numeroValido(this.busqueda.getText())) {
				AlquilerView alquilerView = this.sistema.buscarAlquilerView(Integer.parseInt(this.busqueda.getText()));
				if (alquilerView != null) {
					this.datosDevolucionPanel.cargaAlquiler(alquilerView, Integer.parseInt(this.busqueda.getText()));
				} else {
					Util.mostrarError(this, "No se encontró el alquiler número " + this.busqueda.getText());
				}
			} else {
				Util.mostrarError(this, "Número de alquiler inválido");
			}
		} 
		else if (event.getSource() == this.registrarDevolucionButton) {
			RespuestaGui respuesta = this.datosDevolucionPanel.registrarDevolucion();
			if (respuesta.getTipoRespuesta().equals(ErrorGui.OK)) {
				JOptionPane.showMessageDialog(this,
						"El costo del alquiler es de " + respuesta.getMensaje(), "Devolución Exitosa",
						JOptionPane.INFORMATION_MESSAGE);
				this.gui.reset();
			} else {
				Util.mostrarError(this, respuesta);
			}
		}
	}

}