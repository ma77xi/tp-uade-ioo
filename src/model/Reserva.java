package model;

import java.util.Date;

public class Reserva {

	private static int siguienteNumeroReserva;
	
	private int numeroReserva;
	private Date fechaInicio;
	private Date fechaFin;
	private Automovil automovil;
	private Cliente cliente;
	private float multaCancelacion;

	public int getNumeroReserva() {
		return numeroReserva;
	}

	public void setNumeroReserva(int numeroReserva) {
		this.numeroReserva = numeroReserva;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Automovil getAutomovil() {
		return automovil;
	}

	public void setAutomovil(Automovil automovil) {
		this.automovil = automovil;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public float getMultaCancelacion() {
		return multaCancelacion;
	}

	public void setMultaCancelacion(float multaCancelacion) {
		this.multaCancelacion = multaCancelacion;
	}

	public Reserva(Automovil automovil, Cliente cliente, Date fechaFin,
			Date fechaInicio, float multaCancelacion) {
		
		this.automovil = automovil;
		this.cliente = cliente;
		this.fechaFin = fechaFin;
		this.fechaInicio = fechaInicio;
		this.multaCancelacion = multaCancelacion;
		this.numeroReserva = Reserva.obtenerNumeroReserva();
	}

	private static int obtenerNumeroReserva() {
		return ++siguienteNumeroReserva;
	}

	public boolean sosReserva(int nroReserva) {
		return this.numeroReserva == nroReserva;
	}

	public boolean tenesAutomovil(Automovil a) {
		return this.automovil==a;
	}

	public boolean tenesCliente(int nroCliente) {
		return this.cliente.sosCliente(nroCliente);
	}
	
	
}
