package controller;

import model.TipoModelo;
import util.Util;
import view.GuiClienteWeb;
import view.GuiEmpleado;

public class Main {
	
	public static void main(String[] args) {		
	
		AlquilerAutos sistema = new AlquilerAutos();
		
		// Precarga de modelos
		sistema.altaModelo("Datos del motor", "Datos de seguridad", "Ford", "Fiesta",
				TipoModelo.ECONOMICO.getCodigo(), 10.0f, 1.5f);
		sistema.altaModelo("Datos del motor", "Datos de seguridad", "Ford", "Focus",
				TipoModelo.ECONOMICO.getCodigo(), 10.0f, 1.5f);
		sistema.altaModelo("Datos del motor", "Datos de seguridad", "Audi", "A1", TipoModelo.ECONOMICO.getCodigo(),
				50.0f, 7.5f);
		sistema.altaModelo("Datos del motor", "Datos de seguridad", "Audi", "A4", TipoModelo.ECONOMICO.getCodigo(),
				50.0f, 7.5f);

		// Precarga de autos
		sistema.altaAutomovil(1, "AFC 432", 2010, 1000L, 50.0f);
		sistema.altaAutomovil(1, "RGT 142", 2010, 1000L, 50.0f);
		sistema.altaAutomovil(2, "WVT 254", 2011, 1000L, 50.0f);
		sistema.altaAutomovil(2, "DGT 137", 2012, 1000L, 50.0f);
		sistema.altaAutomovil(4, "WHT 118", 2013, 0L, 40.0f);
		sistema.altaAutomovil(4, "TFV 946", 2013, 100L, 40.0f);

		// Precarga de clientes
		sistema.altaClienteWeb("Intro", "O.O.", Util.parseFecha("01/03/2013"), "Lima 717", "(011)4555-9112",
				12345678L, "M", "Argentina", "ioo", "123");
		sistema.altaClienteWeb("1", "1", null, "1", "1", 11111L, "1", "1", "1", "1");
		
		GuiEmpleado gui = new GuiEmpleado(sistema);
		gui.setVisible(true);
		
		GuiClienteWeb guiEw = new GuiClienteWeb(sistema);
		guiEw.setVisible(true);	
	}
}