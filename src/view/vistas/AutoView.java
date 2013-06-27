package view.vistas;

import model.Automovil;

public class AutoView {

	private String modelo;
	private String patente;
	private String anio;
	private long kilometraje;
	private String combustible;

	public AutoView(String modelo, Automovil auto) {
		this.modelo = modelo;
		this.patente = auto.getPatente();
		this.anio = String.valueOf(auto.getAnio());
		this.kilometraje = auto.getKilometraje();
		this.combustible = String.valueOf(auto.getCombustible());
	}

	public String getModelo() {
		return modelo;
	}

	public String getPatente() {
		return patente;
	}

	public String getAnio() {
		return anio;
	}

	public long getKilometraje() {
		return kilometraje;
	}

	public String getCombustible() {
		return combustible;
	}

}
