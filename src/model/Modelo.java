package model;

import java.util.ArrayList;
import java.util.List;

import util.RespuestaSistema;

public class Modelo {

	private static int siguienteNumeroModelo;

	private int numeroModelo;
	private String datosMotor;
	private String datosSeguridad;
	private String marca;
	private String modelo;
	private String tipo;
	private float costoDia;
	private float costoKmExcedente;
	private List<Automovil> automoviles;

	public int getNumeroModelo() {
		return numeroModelo;
	}

	public void setNumeroModelo(int numeroModelo) {
		this.numeroModelo = numeroModelo;
	}

	public String getDatosMotor() {
		return datosMotor;
	}

	public void setDatosMotor(String datosMotor) {
		this.datosMotor = datosMotor;
	}

	public String getMarca() {
		return marca;
	}

	public String getDatosSeguridad() {
		return datosSeguridad;
	}

	public void setDatosSeguridad(String datosSeguridad) {
		this.datosSeguridad = datosSeguridad;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getCostoDia() {
		return costoDia;
	}

	public void setCostoDia(float costoDia) {
		this.costoDia = costoDia;
	}

	public float getCostoKmExcedente() {
		return costoKmExcedente;
	}

	public void setCostoKmExcedente(float costoKmExcedente) {
		this.costoKmExcedente = costoKmExcedente;
	}

	public List<Automovil> getAutomoviles() {
		return automoviles;
	}

	public void setAutomoviles(List<Automovil> automoviles) {
		this.automoviles = automoviles;
	}

	public static int obtenerNumeroModelo() {
		return ++siguienteNumeroModelo;
	}

	public Modelo(String datosMotor, String datosSeguridad, String marca, String modelo, String tipo, float costoDia,
			float costoKmExcedente) {

		this.numeroModelo = Modelo.obtenerNumeroModelo();
		this.automoviles = new ArrayList<Automovil>();
		this.costoDia = costoDia;
		this.costoKmExcedente = costoKmExcedente;
		this.datosMotor = datosMotor;
		this.datosSeguridad = datosSeguridad;
		this.marca = marca;
		this.modelo = modelo;
		this.tipo = tipo;
	}

	public void agregarAutomovil(int anio, float combustible, long kilometraje, String patente) {

		Automovil nuevoAutomovil = new Automovil(anio, combustible, true, kilometraje, patente, this);
		this.automoviles.add(nuevoAutomovil);

	}

	public boolean sosModelo(String marca, String modelo) {
		return (this.marca.equals(marca) && this.modelo.equals(modelo));
	}

	public boolean sosModelo(int numeroModelo) {
		return this.numeroModelo == numeroModelo;
	}

	public void actualizarDatos(String datosMotor, String datosSeguridad, String marca, String modelo, String tipo,
			float costoDia, float costoKmExcedente) {

		this.datosMotor = datosMotor;
		this.datosSeguridad = datosSeguridad;
		this.marca = marca;
		this.modelo = modelo;
		this.tipo = tipo;
		this.costoDia = costoDia;
		this.costoKmExcedente = costoKmExcedente;

	}

	public int actualizarAutomovil(String patente, int anio, long kilometraje, float combustible, String nuevaPatente) {

		Automovil a = this.buscarAutomovil(patente);

		if (a != null) {
			a.actualizarDatos(nuevaPatente, anio, kilometraje, combustible);
			return RespuestaSistema.OK.getKey();
		}

		return RespuestaSistema.AUTOMOVIL_INEXISTENTE.getKey();

	}

	public Automovil buscarAutomovil(String patente) {

		Automovil a = null;

		for (Automovil aut : this.automoviles) {
			if (aut.sosAutomovil(patente)) {
				return aut;
			}
		}

		return a;

	}

	public void quitarAutomovil(Automovil a) {
		this.automoviles.remove(a);
	}

	public boolean tenesAutos() {
		return (this.automoviles != null && this.automoviles.size() != 0);
	}

	public boolean tenesAuto(Automovil auto) {
		if (this.automoviles != null) {
			return this.automoviles.contains(auto);
		}
		return false;
	}

}
