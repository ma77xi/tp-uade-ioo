package view.swing.cliente;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import util.ErrorGui;
import util.RespuestaGui;
import util.Util;
import view.GuiEmpleado;
import view.swing.MenuPanel;
import view.vistas.ClienteView;
import controller.AlquilerAutos;

public class BajaClientePanel extends MenuPanel implements ActionListener {

	private static final long serialVersionUID = 3962037373678734873L;

	private JTextField busqueda;
	private JLabel busquedaLabel;
	private JButton buscarButton;

	private DatosClientePanel datosClientePanel;

	private JButton borrarButton;

	public BajaClientePanel (AlquilerAutos sistema, GuiEmpleado gui) {
		super(sistema, gui);
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
		this.busquedaLabel = new JLabel("N�mero Cliente:");
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
		this.datosClientePanel = new DatosClientePanel(sistema);
		this.datosClientePanel.enableAllComponents(false);
		this.add(this.datosClientePanel, gBC);

		y++;

		gBC.gridy = y;
		gBC.gridx = 3;
		gBC.gridwidth = 1;
		this.borrarButton = new JButton("Borrar");
		this.borrarButton.addActionListener(this);
		this.add(borrarButton, gBC);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == this.buscarButton) {
			if (Util.numeroValido(this.busqueda.getText())) {
				ClienteView clienteView = this.sistema.buscarClienteView(Integer.parseInt(this.busqueda.getText()));
				if (clienteView != null) {
					this.datosClientePanel.cargaCliente(clienteView);
					this.datosClientePanel.enableAllComponents(true);
				} else {
					Util.mostrarError(this, "No se encontr� el cliente n�mero " + this.busqueda.getText());
				}
			} else {
				Util.mostrarError(this, "N�mero de cliente inv�lido");
			}
		} else if (event.getSource() == this.borrarButton) {
			RespuestaGui respuesta = this.datosClientePanel.eliminarCliente(Long.parseLong(this.busqueda.getText()));
			if (respuesta.getTipoRespuesta().equals(ErrorGui.OK)) {
				int seleccion = JOptionPane.showConfirmDialog(null, respuesta.getMensaje()
						+ "\nDesea eliminar otro Cliente?", "Eliminacion Cliente", JOptionPane.YES_NO_OPTION);
				if (seleccion == JOptionPane.YES_OPTION) {
					guiEmpleado.eliminarClienteReset();
				} else {
					guiEmpleado.reset();
				}
			} else {
				Util.mostrarError(this, respuesta.getMensaje());
			}

		}
	}

}