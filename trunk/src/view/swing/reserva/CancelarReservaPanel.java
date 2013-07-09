package view.swing.reserva;

import java.awt.Color;
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
import view.GuiClienteWeb;
import view.vistas.ReservaView;
import controller.AlquilerAutos;

public class CancelarReservaPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1401079430111645826L;

	private GuiClienteWeb gui;
	private AlquilerAutos sistema;

	private JTextField busqueda;
	private JLabel busquedaLabel;
	private JButton buscarButton;

	private DatosReservaPanel datosReservaPanel;

	private JButton cancelarButton;

	public CancelarReservaPanel(AlquilerAutos sistema, GuiClienteWeb gui) {
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
		this.datosReservaPanel = new DatosReservaPanel(sistema);
		// this.datosReservaPanel.enableAllComponents(false);
		// TODO : ver habilitación
		this.add(this.datosReservaPanel, gBC);

		y++;

		gBC.gridy = y;
		gBC.gridx = 0;
		gBC.gridwidth = 4;
		gBC.gridheight = 2;
		JLabel warning = new JLabel(
				"<html>Las cancelaciones posteriores a las 48 hs anteriores del inicio de la reserva <br />tendrán una multa.</html>");
		warning.setForeground(Color.RED);
		this.add(warning, gBC);

		y++;
		y++;

		gBC.gridy = y;
		gBC.gridx = 3;
		gBC.gridwidth = 1;
		gBC.gridheight = 1;
		this.cancelarButton = new JButton("Cancelar");
		this.cancelarButton.addActionListener(this);
		this.add(cancelarButton, gBC);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == this.buscarButton) {
			if (Util.numeroValido(this.busqueda.getText())) {
				ReservaView reservaView = this.sistema.buscarReservaView(Integer.parseInt(this.busqueda.getText()));
				if (reservaView != null) {
					this.datosReservaPanel.cargaReserva(reservaView);
					// this.datosReservaPanel.enableAllComponents(true);
				} else {
					Util.mostrarError(this, "No se encontró la reseva número " + this.busqueda.getText());
				}
			} else {
				Util.mostrarError(this, "Número de reserva inválido");
			}
		} else if (event.getSource() == this.cancelarButton) {
			RespuestaGui respuesta = this.datosReservaPanel.cancelarReserva(Long.parseLong(this.busqueda.getText()));
			if (respuesta.getTipoRespuesta().equals(ErrorGui.OK)) {
				JOptionPane.showMessageDialog(this, "Se canceló correctamente la reserva.", "Cancelación Exitosa",
						JOptionPane.INFORMATION_MESSAGE);
				this.gui.reset();
			} else if (respuesta.getTipoRespuesta().equals(ErrorGui.MUESTRA_MENSAJE)) {
				JOptionPane.showMessageDialog(
						this,
						"Se canceló correctamente la reserva pero deberá \nabonar una multa de "
								+ respuesta.getMensaje(), "Cancelación Exitosa", JOptionPane.INFORMATION_MESSAGE);
				this.gui.reset();
			} else {
				Util.mostrarError(this, respuesta.getMensaje());
			}

		}
	}

}