package view.vistas;

import model.Alquiler;
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

	public AlquilerView(Alquiler alquiler) {
		this.nombre = alquiler.getCliente().getNombre();
		this.apellido = alquiler.getCliente().getApellido();
		this.dni = alquiler.getCliente().getDni();
		this.modelo = alquiler.getAutomovil().getModelo().getModelo();
		this.marca = alquiler.getAutomovil().getModelo().getMarca();
		this.anio = String.valueOf(alquiler.getAutomovil().getAnio());
		this.patente = alquiler.getAutomovil().getPatente();
		this.fechaInicio = Util.parseFecha(alquiler.getFechaInicio());
		this.fechaFin = Util.parseFecha(alquiler.getFechaFin());
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

}
