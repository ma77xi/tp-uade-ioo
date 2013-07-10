package view.swing.auto;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import util.ErrorGui;
import util.RespuestaGui;
import util.Util;
import view.GuiEmpleado;
import view.swing.MenuPanel;
import controller.AlquilerAutos;

public class AltaModeloPanel extends MenuPanel implements ActionListener {

	private static final long serialVersionUID = -877305488631193827L;

	public DatosModeloPanel datosModeloPanel;
	private JButton guardarButton;

	public AltaModeloPanel(AlquilerAutos sistema, GuiEmpleado gui) {
		super(sistema, gui);
		this.init();
	}

	private void init() {

		this.setLayout(new GridBagLayout());
		GridBagConstraints gBC = new GridBagConstraints();

		int y = 0;

		gBC.gridy = y;
		gBC.gridx = 0;
		gBC.gridwidth = 6;
		this.datosModeloPanel = new DatosModeloPanel(sistema);
		this.add(this.datosModeloPanel, gBC);

		y++;

		gBC.gridy = y;
		gBC.gridx = 5;
		gBC.gridwidth = 1;
		this.guardarButton = new JButton("Guardar");
		this.guardarButton.addActionListener(this);
		this.add(guardarButton, gBC);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		RespuestaGui respuesta = this.datosModeloPanel.altaModelo();
		if (respuesta.getTipoRespuesta().equals(ErrorGui.OK)) {
			int seleccion = JOptionPane.showConfirmDialog(null, "Se dio de alta satisfactoriamente el modelo "
					+ respuesta.getMensaje() + "\nDesea dar de alta otro Modelo?", "Alta Modelo",
					JOptionPane.YES_NO_OPTION);
			if (seleccion == JOptionPane.YES_OPTION) {
				guiEmpleado.altaModeloReset();
			} else {
				guiEmpleado.reset();
			}
		} else {
			Util.mostrarError(this, respuesta.getMensaje());
		}

	}

}