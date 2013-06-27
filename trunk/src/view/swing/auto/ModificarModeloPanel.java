package view.swing.auto;

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
import view.Gui;
import view.swing.MenuPanel;
import view.vistas.ModeloView;
import controller.AlquilerAutos;

public class ModificarModeloPanel extends MenuPanel implements ActionListener {

	private static final long serialVersionUID = 3962037373678734873L;

	private JTextField busqueda;
	private JLabel busquedaLabel;
	private JButton buscarButton;

	private DatosModeloPanel datosModeloPanel;

	private JButton guardarButton;

	public ModificarModeloPanel(AlquilerAutos sistema, Gui gui) {
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
		this.busquedaLabel = new JLabel("Número modelo:");
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
		this.datosModeloPanel = new DatosModeloPanel(sistema);
		this.datosModeloPanel.enableAllComponents(false);
		this.add(this.datosModeloPanel, gBC);

		y++;

		gBC.gridy = y;
		gBC.gridx = 3;
		gBC.gridwidth = 1;
		this.guardarButton = new JButton("Guardar");
		this.guardarButton.addActionListener(this);
		this.add(guardarButton, gBC);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == this.buscarButton) {
			if (Util.numeroValido(this.busqueda.getText())) {
				ModeloView modeloView = this.sistema.buscarModeloView(Integer.parseInt(this.busqueda.getText()));
				if (modeloView != null) {
					this.datosModeloPanel.cargaModelo(modeloView);
					this.datosModeloPanel.enableAllComponents(true);
				} else {
					Util.mostrarError(this, "No se encontró el cliente número " + this.busqueda.getText());
				}
			} else {
				Util.mostrarError(this, "Número de cliente inválido");
			}
		} else if (event.getSource() == this.guardarButton) {
			RespuestaGui respuesta = this.datosModeloPanel.modificarModelo(Long.parseLong(this.busqueda.getText()));
			if (respuesta.getTipoRespuesta().equals(ErrorGui.OK)) {
				int seleccion = JOptionPane.showConfirmDialog(null, "Se actualizó correctamente el modelo."
						+ "\nDesea modificar otro Modelo?", "Modificación Modelo", JOptionPane.YES_NO_OPTION);
				if (seleccion == JOptionPane.YES_OPTION) {
					gui.modificarModeloReset();
				} else {
					gui.reset();
				}
			} else {
				Util.mostrarError(this, respuesta.getMensaje());
			}

		}
	}

}