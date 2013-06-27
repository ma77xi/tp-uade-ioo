package view.swing;

import javax.swing.JPanel;

import view.Gui;
import controller.AlquilerAutos;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = -5177257654710893919L;

	protected AlquilerAutos sistema;
	protected Gui gui;

	public MenuPanel(AlquilerAutos sistema, Gui gui) {
		this.sistema = sistema;
		this.gui = gui;
	}

}
