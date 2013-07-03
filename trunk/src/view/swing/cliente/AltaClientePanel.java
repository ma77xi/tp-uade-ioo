package view.swing.cliente;

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

public class AltaClientePanel extends MenuPanel implements ActionListener {

	private static final long serialVersionUID = -6594792435424550536L;

	public DatosClientePanel datosClientePanel;
	private JButton guardarButton;

	public AltaClientePanel(AlquilerAutos sistema, GuiEmpleado gui) {
		super(sistema, gui);
		this.init();
	}

	private void init() {

		this.setLayout(new GridBagLayout());
		GridBagConstraints gBC = new GridBagConstraints();

		int y = 0;

		gBC.gridy = y;
		gBC.gridx = 0;
		gBC.gridwidth = 4;
		this.datosClientePanel = new DatosClientePanel(sistema);
		this.add(this.datosClientePanel, gBC);

		y++;

		gBC.gridy = y;
		gBC.gridx = 3;
		gBC.gridwidth = 1;
		this.guardarButton = new JButton("Guardar");
		this.guardarButton.addActionListener(this);
		this.add(guardarButton, gBC);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		RespuestaGui respuesta = this.datosClientePanel.altaCliente();
		if (respuesta.getTipoRespuesta().equals(ErrorGui.OK)) {
			int seleccion = JOptionPane.showConfirmDialog(null,
					"Se dio de alta al cliente Nro " + respuesta.getMensaje() + "\nDesea dar de alta otro Cliente?",
					"Alta Cliente", JOptionPane.YES_NO_OPTION);
			if (seleccion == JOptionPane.YES_OPTION) {
				gui.altaClienteReset();
			} else {
				gui.reset();
			}
		} else {
			Util.mostrarError(this, respuesta.getMensaje());
		}

	}

}