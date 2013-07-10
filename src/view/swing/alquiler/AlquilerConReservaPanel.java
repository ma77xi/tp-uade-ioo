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
import view.vistas.ReservaView;
import controller.AlquilerAutos;

public class AlquilerConReservaPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 7027429706359909333L;

	private GuiEmpleado gui;
	private AlquilerAutos sistema;

	private JTextField busqueda;
	private JLabel busquedaLabel;
	private JButton buscarButton;

	private DatosAlquilerPanel datosAlquilerPanel;

	private JButton generarAlquilerButton;

	public AlquilerConReservaPanel(AlquilerAutos sistema, GuiEmpleado gui) {
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
		this.busquedaLabel = new JLabel("Número Reserva:");
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
		this.datosAlquilerPanel = new DatosAlquilerPanel(sistema);
		// TODO : ver habilitación
		this.add(this.datosAlquilerPanel, gBC);

		y++;

		gBC.gridy = y;
		gBC.gridx = 3;
		gBC.gridwidth = 1;
		gBC.gridheight = 1;
		this.generarAlquilerButton = new JButton("Convertir");
		this.generarAlquilerButton.addActionListener(this);
		this.add(generarAlquilerButton, gBC);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == this.buscarButton) {
			if (Util.numeroValido(this.busqueda.getText())) {
				ReservaView reservaView = this.sistema.buscarReservaView(Integer.parseInt(this.busqueda.getText()));
				if (reservaView != null) {
					this.datosAlquilerPanel.cargaReserva(reservaView, Integer.parseInt(this.busqueda.getText()));
				} else {
					Util.mostrarError(this, "No se encontró la reseva número " + this.busqueda.getText());
				}
			} else {
				Util.mostrarError(this, "Número de reserva inválido");
			}
		} else if (event.getSource() == this.generarAlquilerButton) {
			RespuestaGui respuesta = this.datosAlquilerPanel.generarAlquilerConReserva();
			if (respuesta.getTipoRespuesta().equals(ErrorGui.OK)) {
				JOptionPane.showMessageDialog(this,
						"Se generó correctamente el alquiler número " + respuesta.getMensaje(), "Alquiler Exitoso",
						JOptionPane.INFORMATION_MESSAGE);
				this.gui.reset();
			} else {
				Util.mostrarError(this, respuesta);
			}
		}
	}

}