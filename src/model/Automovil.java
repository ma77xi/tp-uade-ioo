package model;

public class Automovil {

	private String patente;
	private int anio;
	private long kilometraje;
	private float combustible;
	private Boolean disponible;

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public long getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(long kilometraje) {
		this.kilometraje = kilometraje;
	}

	public float getCombustible() {
		return combustible;
	}

	public void setCombustible(float combustible) {
		this.combustible = combustible;
	}

	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public Automovil(int anio, float combustible, Boolean disponible,
			long kilometraje, String patente) {

		this.anio = anio;
		this.combustible = combustible;
		this.disponible = disponible;
		this.kilometraje = kilometraje;
		this.patente = patente;
	}

	public boolean sosAutomovil(String patente) {
		return this.patente.equals(patente);
	}

	public void actualizarDatos(String nuevaPatente, int anio,
			long kilometraje, float combustible) {

		this.patente = nuevaPatente;
		this.anio = anio;
		this.kilometraje = kilometraje;
		this.combustible = combustible;

	}

	public void alquilado() {
		this.disponible = Boolean.FALSE;
	}

	public void registrarDevolucion(float kilometrajeRecorrido,
			float cantidadCombustible) {
		this.kilometraje += kilometrajeRecorrido;
		this.combustible = cantidadCombustible;
		this.disponible = Boolean.TRUE;
		
	}

}
