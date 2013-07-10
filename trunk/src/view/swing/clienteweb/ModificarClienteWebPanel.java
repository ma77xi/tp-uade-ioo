package view.swing.clienteweb;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import util.ErrorGui;
import util.RespuestaGui;
import util.Util;
import view.GuiClienteWeb;
import view.GuiEmpleado;
import view.swing.MenuPanel;
import view.vistas.ClienteWebView;
import controller.AlquilerAutos;

public class ModificarClienteWebPanel extends MenuPanel implements ActionListener {
	
	private static final long serialVersionUID = 3962037373678734873L;

	private DatosClienteWebPanel datosClienteWebPanel;
	
	private JButton guardarButton;

	public ModificarClienteWebPanel(AlquilerAutos sistema, GuiEmpleado gui) {
		super(sistema, gui);
		this.init();
	}

	public ModificarClienteWebPanel(AlquilerAutos sistema, GuiClienteWeb gui, String user) {
		super(sistema, gui);
		
		ClienteWebView clienteView = sistema.buscarClienteWebView(user);
		
		this.datosClienteWebPanel = new DatosClienteWebPanel(sistema);
		if (clienteView != null)
		{
			this.datosClienteWebPanel.cargaCliente(clienteView);
			this.datosClienteWebPanel.enableAllComponents(true);
		}
		else 
		{
			Util.mostrarError(this, "No se encontró al usuario " + user);
		}
		
		this.init();
	}

	private void init() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gBC = new GridBagConstraints();

		int y = 0;

		gBC.insets = new Insets(2, 2, 2, 2);
		gBC.anchor = GridBagConstraints.NORTHEAST;

		gBC.gridy = y;
		gBC.gridx = 0;
		gBC.gridwidth = 4;
		this.add(this.datosClienteWebPanel, gBC);

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
		if (event.getSource() == this.guardarButton) 
		{
			RespuestaGui respuesta = this.datosClienteWebPanel.modificarCliente();
			if (respuesta.getTipoRespuesta().equals(ErrorGui.OK)) 
			{
				JOptionPane.showMessageDialog(this, "Se actualizó correctamente al cliente",
							"Modificación Exitosa", JOptionPane.INFORMATION_MESSAGE);
				guiClienteWeb.reset();
			} 
			else 
			{
				Util.mostrarError(this, respuesta.getMensaje());
			}
		}
	}
}