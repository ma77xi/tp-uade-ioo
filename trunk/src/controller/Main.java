package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.ElementoCombo;
import util.Util;


public class Main {
	
	public final static Date fechaDesde = Util.parseFecha("10/10/2013");
	public final static Date fechaHasta = Util.parseFecha("20/10/2013");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		List<ElementoCombo> lista = new ArrayList<ElementoCombo>();
//		lista.add(new ElementoCombo("1", "a"));
//		lista.add(new ElementoCombo("2", "a"));
//		lista.add(new ElementoCombo("3", "a"));
//		lista.add(new ElementoCombo("2", "b"));
//		lista.add(new ElementoCombo("3", "b"));
//		lista.add(new ElementoCombo("1", "a"));
//		lista.add(new ElementoCombo("2", "c"));
//		
//		Map<Long, List<String>> mapa = new HashMap<Long, List<String>>();
//		for (ElementoCombo e : lista) {
//			if (null != mapa.get(new Long(e.getCodigo()))) {
//				List<String> l = mapa.get(new Long(e.getCodigo()));
//				if (!l.contains(e.getDescripcion())) {
//					l.add(e.getDescripcion());
//				}
//			} else {
//				List<String> l = new ArrayList<String>();
//				l.add(e.getDescripcion());
//				mapa.put(new Long(e.getCodigo()), l);
//			}
//		}
//		
//		System.out.println(mapa);
		
		Date fecha1 = Util.parseFecha("01/10/2013");
		Date fecha2 = Util.parseFecha("10/10/2013");
		Date fecha3 = Util.parseFecha("12/10/2013");
		Date fecha4 = Util.parseFecha("18/10/2013");
		Date fecha5 = Util.parseFecha("20/10/2013");
		Date fecha6 = Util.parseFecha("30/10/2013");
		
		System.out.println("false");
		System.out.println(fecha1 + " " + fecha6 + "    --- " + Main.estasEnRango(fecha1, fecha6));
		System.out.println(fecha1 + " " + fecha2 + "    --- " + Main.estasEnRango(fecha1, fecha2));
		System.out.println(fecha1 + " " + fecha3 + "    --- " + Main.estasEnRango(fecha1, fecha3));
		System.out.println(fecha2 + " " + fecha6 + "    --- " + Main.estasEnRango(fecha2, fecha6));
		System.out.println(fecha5 + " " + fecha6 + "    --- " + Main.estasEnRango(fecha5, fecha6));
		
		System.out.println();
		System.out.println("true");
		System.out.println(fecha2 + " " + fecha5 + "    --- " + Main.estasEnRango(fecha2, fecha5));
		System.out.println(fecha2 + " " + fecha3 + "    --- " + Main.estasEnRango(fecha2, fecha3));
		System.out.println(fecha3 + " " + fecha4 + "    --- " + Main.estasEnRango(fecha3, fecha4));
		System.out.println(fecha4 + " " + fecha5 + "    --- " + Main.estasEnRango(fecha4, fecha5));
		
	}
	
	public static boolean estasEnRango(Date fechaDesde, Date fechaHasta) {
		return Main.fechaDesde.compareTo(fechaDesde) <= 0 && Main.fechaHasta.compareTo(fechaHasta) >= 0;
	}
	

}
