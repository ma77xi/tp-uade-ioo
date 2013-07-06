package controller;

import model.TipoModelo;
import util.RespuestaTransaccion;
import util.Util;
import view.GuiClienteWeb;

public class MainClienteWeb {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AlquilerAutos controller = new AlquilerAutos();

		// Precarga de modelos
		controller.altaModelo("Datos del motor", "Datos de seguridad", "Ford", "Fiesta",
				TipoModelo.ECONOMICO.getCodigo(), 10.0f, 1.5f);
		controller.altaModelo("Datos del motor", "Datos de seguridad", "Ford", "Focus",
				TipoModelo.ECONOMICO.getCodigo(), 10.0f, 1.5f);
		controller.altaModelo("Datos del motor", "Datos de seguridad", "Audi", "A1", TipoModelo.ECONOMICO.getCodigo(),
				50.0f, 7.5f);
		controller.altaModelo("Datos del motor", "Datos de seguridad", "Audi", "A4", TipoModelo.ECONOMICO.getCodigo(),
				50.0f, 7.5f);

		// Precarga de autos
		controller.altaAutomovil(1, "AFC 432", 2010, 1000L, 50.0f);
		controller.altaAutomovil(1, "RGT 142", 2010, 1000L, 50.0f);
		controller.altaAutomovil(2, "WVT 254", 2011, 1000L, 50.0f);
		controller.altaAutomovil(2, "DGT 137", 2012, 1000L, 50.0f);
		controller.altaAutomovil(4, "WHT 118", 2013, 0L, 40.0f);
		controller.altaAutomovil(4, "TFV 946", 2013, 100L, 40.0f);

		// Precarga de clientes
		controller.altaClienteWeb("Intro", "O.O.", Util.parseFecha("01/03/2013"), "Lima 717", "(011)4555-9112",
				12345678L, "M", "Argentina", "ioo", "123");

		GuiClienteWeb g = new GuiClienteWeb(controller);
		g.setVisible(true);

	}

}
