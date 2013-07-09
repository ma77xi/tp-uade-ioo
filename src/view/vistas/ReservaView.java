package view.vistas;

import model.Modelo;
import model.Reserva;
import util.Util;

public class ReservaView {

	private String nombre;
	private String apellido;
	private Long dni;
	private String modelo;
	private String marca;
	private String fechaInicio;
	private String fechaFin;
	private String multaCancelacion;

	public ReservaView(Reserva reserva, Modelo modelo) {
		this.nombre = reserva.getCliente().getNombre();
		this.apellido = reserva.getCliente().getNombre();
		this.dni = reserva.getCliente().getDni();
		this.modelo = modelo.getModelo();
		this.marca = modelo.getMarca();
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
