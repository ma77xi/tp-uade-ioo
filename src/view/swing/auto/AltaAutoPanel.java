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

public class AltaAutoPanel extends MenuPanel implements ActionListener {

	private static final long serialVersionUID = 3467955081357806828L;

	public DatosAutoPanel datosAutoPanel;
	private JButton guardarButton;

	public AltaAutoPanel(AlquilerAutos sistema, GuiEmpleado gui) {
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
		this.datosAutoPanel = new DatosAutoPanel(sistema);
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
	public void actionPerformed(ActionEvent e) {
		RespuestaGui respuesta = this.datosAutoPanel.altaAuto();
		if (respuesta.getTipoRespuesta().equals(ErrorGui.OK)) {
			int seleccion = JOptionPane
					.showConfirmDialog(null, "Se dio de alta satisfactoriamente el auto " + respuesta.getMensaje()
							+ "\nDesea dar de alta otro Auto?", "Alta Auto", JOptionPane.YES_NO_OPTION);
			if (seleccion == JOptionPane.YES_OPTION) {
				guiEmpleado.altaAutoReset();
			} else {
				guiEmpleado.reset();
			}
		} else {
			Util.mostrarError(this, respuesta.getMensaje());
		}

	}

}