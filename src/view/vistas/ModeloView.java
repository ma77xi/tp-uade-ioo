package view.vistas;

import model.Modelo;

public class ModeloView {

	private int numeroModelo;
	private String datosMotor;
	private String datosSeguridad;
	private String marca;
	private String modelo;
	private String tipo;
	private float costoDia;
	private float costoKmExcedente;

	public ModeloView(Modelo cliente) {
		this.numeroModelo = cliente.getNumeroModelo();
		this.datosMotor = cliente.getDatosMotor();
		this.datosSeguridad = cliente.getDatosSeguridad();
		this.marca = cliente.getMarca();
		this.modelo = cliente.getModelo();
		this.tipo = cliente.getTipo();
		this.costoDia = cliente.getCostoDia();
		this.costoKmExcedente = cliente.getCostoKmExcedente();
	}

	public int getNumeroModelo() {
		return numeroModelo;
	}

	public String getDatosMotor() {
		return datosMotor;
	}

	public String getDatosSeguridad() {
		return datosSeguridad;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public String getTipo() {
		return tipo;
	}

	public float getCostoDia() {
		return costoDia;
	}

	public float getCostoKmExcedente() {
		return costoKmExcedente;
	}

}
