package model;

import java.util.Date;

public class Factura {

	private static int siguienteNumeroFactura;

	private int numeroFactura;
	private Date fecha;
	private String detalle;
	private Cliente cliente;
	private float monto;

	public int getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(int numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public static int obtenerNumeroFactura() {
		return ++siguienteNumeroFactura;
	}

	public Factura(String detalle, Date fecha, Cliente cliente, float monto) {

		this.detalle = detalle;
		this.fecha = fecha;
		this.cliente = cliente;
		this.monto = monto;
		this.numeroFactura = Factura.obtenerNumeroFactura();
	}

}
