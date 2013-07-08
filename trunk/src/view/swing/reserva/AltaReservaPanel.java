package view.swing.reserva;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import util.ErrorGui;
import util.RespuestaGui;
import util.Util;
import view.GuiClienteWeb;
import view.GuiEmpleado;
import view.swing.MenuPanel;
import controller.AlquilerAutos;

public class AltaReservaPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = -6594792435424550536L;

	private GuiClienteWeb gui;
	private AlquilerAutos sistema;
	private String user;

	public DatosReservaPanel datosReservaPanel;
	private JButton generarResevaButton;

	public AltaReservaPanel(AlquilerAutos sistema, GuiClienteWeb gui, String user) {
		this.sistema = sistema;
		this.gui = gui;
		this.user = user;
		this.init();
	}

	private void init() {

		this.setLayout(new GridBagLayout());
		GridBagConstraints gBC = new GridBagConstraints();

		int y = 0;

		gBC.gridy = y;
		gBC.gridx = 0;
		gBC.gridwidth = 4;
		this.datosReservaPanel = new DatosReservaPanel(sistema, this.sistema.buscarClienteView(user));
		this.add(this.datosReservaPanel, gBC);

		y++;

		gBC.gridy = y;
		gBC.gridx = 3;
		gBC.gridwidth = 1;
		this.generarResevaButton = new JButton("Generar Reserva");
		this.generarResevaButton.addActionListener(this);
		this.add(generarResevaButton, gBC);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		RespuestaGui respuesta = this.datosReservaPanel.generarReserva();
		if (respuesta.getTipoRespuesta().equals(ErrorGui.OK)) {
			JOptionPane.showMessageDialog(this, "Se generó correctamente la reserva número " + respuesta.getMensaje(),
					"Reseva Exitosa", JOptionPane.INFORMATION_MESSAGE);
			this.gui.reset();
		} else {
			Util.mostrarError(this, respuesta);
		}

	}

}