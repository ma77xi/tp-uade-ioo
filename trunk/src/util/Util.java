package util;

import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class Util {

	public static boolean fechaValida(String fecha) {
		Date date = Util.parseFecha(fecha);
		return date != null;
	}

	public static Date parseFecha(String fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(fecha);
			return date;
		} catch (ParseException e) {
			return null;
		}
	}

	public static String parseFecha(Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(fecha);
	}

	public static void mostrarError(Component parent, String mensaje) {
		JOptionPane.showMessageDialog(parent, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public static boolean numeroValido(String numero) {
		try {
			Integer.parseInt(numero);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean decimalValido(String numero) {
		try {
			Float.parseFloat(numero);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
