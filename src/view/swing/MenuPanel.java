package view.swing;

import javax.swing.JPanel;

import view.GuiEmpleado;
import controller.AlquilerAutos;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = -5177257654710893919L;

	protected AlquilerAutos sistema;
	protected GuiEmpleado gui;

	public MenuPanel(AlquilerAutos sistema, GuiEmpleado gui) {
		this.sistema = sistema;
		this.gui = gui;
	}

}
