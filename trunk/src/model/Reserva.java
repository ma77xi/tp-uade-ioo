package model;

import java.util.Calendar;
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
			Date fechaInicio) {
		
		long startTime = fechaInicio.getTime();
		long endTime = fechaFin.getTime();
		long diffTime = endTime - startTime;
		long diffDays = diffTime / (1000 * 60 * 60 * 24);
		
		this.automovil = automovil;
		this.cliente = cliente;
		this.fechaFin = fechaFin;
		this.fechaInicio = fechaInicio;
		float costoDia = automovil.getModelo().getCostoDia();
		this.multaCancelacion = (costoDia * diffDays * 10 / 100);
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

	public boolean haySolapamiento(Date fechaDesde, Date fechaHasta) {
		return this.fechaInicio.compareTo(fechaHasta) <= 0 && this.fechaFin.compareTo(fechaDesde) >= 0;
	}

	public boolean aplicaMulta(Date fechaCancelacion) {
		Calendar c = Calendar.getInstance();
		c.setTime(fechaCancelacion); 
		c.add(Calendar.DATE, 2);
		return (c.getTime().compareTo(this.fechaInicio) > 0 ); 
	}
	
}
