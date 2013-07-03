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
import view.GuiEmpleado;
import view.swing.MenuPanel;
import view.vistas.AutoView;
import controller.AlquilerAutos;

public class ModificarAutoPanel extends MenuPanel implements ActionListener {

	private static final long serialVersionUID = -1780388022499999592L;

	private JTextField busqueda;
	private JLabel busquedaLabel;
	private JButton buscarButton;

	private DatosAutoPanel datosAutoPanel;

	private JButton guardarButton;

	public ModificarAutoPanel(AlquilerAutos sistema, GuiEmpleado gui) {
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
		this.busquedaLabel = new JLabel("Patente:");
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
		this.datosAutoPanel = new DatosAutoPanel(sistema);
		this.datosAutoPanel.enableAllComponents(false);
		this.add(this.datosAutoPanel, gBC);

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
				AutoView autoView = this.sistema.buscarAutoView(this.busqueda.getText());
				if (autoView != null) {
					this.datosAutoPanel.cargaAuto(autoView);
					this.datosAutoPanel.enableAllComponents(true);
				} else {
					Util.mostrarError(this, "No se encontró automóvil patente" + this.busqueda.getText());
				}
			} else {
				Util.mostrarError(this, "Patente inválida");
			}
		} else if (event.getSource() == this.guardarButton) {
			RespuestaGui respuesta = this.datosAutoPanel.modificarAuto();
			if (respuesta.getTipoRespuesta().equals(ErrorGui.OK)) {
				int seleccion = JOptionPane.showConfirmDialog(null, "Se actualizó correctamente el automóvil."
						+ "\nDesea modificar otro Automóvil?", "Modificación Automóvil", JOptionPane.YES_NO_OPTION);
				if (seleccion == JOptionPane.YES_OPTION) {
					gui.modificarAutoReset();
				} else {
					gui.reset();
				}
			} else {
				Util.mostrarError(this, respuesta.getMensaje());
			}

		}
	}

}