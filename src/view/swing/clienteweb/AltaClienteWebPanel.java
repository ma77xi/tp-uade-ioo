package view.swing.clienteweb;

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
import controller.AlquilerAutos;

public class AltaClienteWebPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 5985194730526057115L;

	private GuiClienteWeb gui;
	private AlquilerAutos sistema;

	public DatosClienteWebPanel datosClienteWebPanel;

	private JButton guardarButton;
	private JButton volverButton;

	public AltaClienteWebPanel(AlquilerAutos sistema, GuiClienteWeb gui) {
		this.sistema = sistema;
		this.gui = gui;
		this.init();
	}

	private void init() {

		this.setLayout(new GridBagLayout());
		GridBagConstraints gBC = new GridBagConstraints();

		int y = 0;

		gBC.gridy = y;
		gBC.gridx = 0;
		gBC.gridwidth = 4;
		this.datosClienteWebPanel = new DatosClienteWebPanel(sistema);
		this.add(this.datosClienteWebPanel, gBC);

		y++;

		gBC.gridy = y;

		JPanel botonera = new JPanel(new GridBagLayout());
		GridBagConstraints gBC2 = new GridBagConstraints();

		gBC2.gridy = 0;

		gBC2.gridx = 0;
		this.volverButton = new JButton("Volver");
		this.volverButton.addActionListener(this);
		botonera.add(volverButton, gBC2);

		gBC2.gridx = 1;
		this.guardarButton = new JButton("Guardar");
		this.guardarButton.addActionListener(this);
		botonera.add(guardarButton, gBC2);

		gBC.anchor = GridBagConstraints.CENTER;
		this.add(botonera, gBC);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.guardarButton) {
			RespuestaGui respuesta = this.datosClienteWebPanel.altaCliente();
			if (respuesta.getTipoRespuesta().equals(ErrorGui.OK)) {
				JOptionPane.showMessageDialog(this, "Se dio creo correctamente su usuario.\nSu número de cliente es "
						+ respuesta.getMensaje(), "Alta Usuario", JOptionPane.INFORMATION_MESSAGE);
				this.gui.resetLogin();
			} else {
				Util.mostrarError(this, respuesta.getMensaje());
			}
		} else if (e.getSource() == this.volverButton) {
			this.gui.resetLogin();
		}
	}

}