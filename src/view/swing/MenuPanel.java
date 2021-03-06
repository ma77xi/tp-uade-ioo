package view.swing;

import javax.swing.JPanel;

import view.GuiClienteWeb;
import view.GuiEmpleado;
import controller.AlquilerAutos;

public class MenuPanel extends JPanel {
	private static final long serialVersionUID = -5177257654710893919L;

	protected AlquilerAutos sistema;
	protected GuiEmpleado guiEmpleado;
	protected GuiClienteWeb guiClienteWeb;

	public MenuPanel(AlquilerAutos sistema, GuiEmpleado guiEmpleado) {
		this.sistema = sistema;
		this.guiEmpleado = guiEmpleado;
	}

	public MenuPanel(AlquilerAutos sistema, GuiClienteWeb guiClienteWeb) {
		this.sistema = sistema;
		this.guiClienteWeb = guiClienteWeb;
	}
}