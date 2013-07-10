package view.vistas;

import model.Modelo;
import model.Reserva;
import util.Util;

public class AlquilerView {

	private String nombre;
	private String apellido;
	private Long dni;
	private String modelo;
	private String marca;
	private String anio;
	private String patente;
	private String fechaInicio;
	private String fechaFin;
	private String multaCancelacion;

	public AlquilerView(Reserva reserva, Modelo modelo) {
		this.nombre = reserva.getCliente().getNombre();
		this.apellido = reserva.getCliente().getApellido();
		this.dni = reserva.getCliente().getDni();
		this.modelo = modelo.getModelo();
		this.marca = modelo.getMarca();
		this.anio = String.valueOf(reserva.getAutomovil().getAnio());
		this.patente = reserva.getAutomovil().getPatente();
		this.fechaInicio = Util.parseFecha(reserva.getFechaInicio());
		this.fechaFin = Util.parseFecha(reserva.getFechaFin());
		this.multaCancelacion = String.valueOf(reserva.getMultaCancelacion());
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public Long getDni() {
		return dni;
	}

	public String getModelo() {
		return modelo;
	}

	public String getMarca() {
		return marca;
	}

	public String getAnio() {
		return anio;
	}

	public String getPatente() {
		return patente;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public String getMultaCancelacion() {
		return multaCancelacion;
	}

}
