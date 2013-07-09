package model;


import java.util.Date;
import java.util.GregorianCalendar;

public class Alquiler {

	private static int siguienteNumeroAlquiler;

	private int numeroAlquiler;
	private Cliente cliente;
	private Date fechaInicio;
	private Date fechaFin;
	private Automovil automovil;
	private String descripcionInspeccion;
	private Date fechaDevolucion;
	private float porcentajeDescuento;
	private float cantidadCombustible;
	private float kilometrajeRecorrido;
	private Boolean obtuvoDanios;
	private Boolean entregoDocumentacion;
	private float cargosExtra;
	private Factura factura;

	public int getNumeroAlquiler() {
		return numeroAlquiler;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public String getDescripcionInspeccion() {
		return descripcionInspeccion;
	}

	public void setDescripcionInspeccion(String descripcionInspeccion) {
		this.descripcionInspeccion = descripcionInspeccion;
	}

	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public float getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public void setPorcentajeDescuento(float porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public float getCantidadCombustible() {
		return cantidadCombustible;
	}

	public void setCantidadCombustible(float cantidadCombustible) {
		this.cantidadCombustible = cantidadCombustible;
	}

	public float getKilometrajeRecorrido() {
		return kilometrajeRecorrido;
	}

	public void setKilometrajeRecorrido(float kilometrajeRecorrido) {
		this.kilometrajeRecorrido = kilometrajeRecorrido;
	}

	public Boolean getObtuvoDanios() {
		return obtuvoDanios;
	}

	public void setObtuvoDanios(Boolean obtuvoDanios) {
		this.obtuvoDanios = obtuvoDanios;
	}

	public Boolean getEntregoDocumentacion() {
		return entregoDocumentacion;
	}

	public void setEntregoDocumentacion(Boolean entregoDocumentacion) {
		this.entregoDocumentacion = entregoDocumentacion;
	}

	public float getCargosExtra() {
		return cargosExtra;
	}

	public void setCargosExtra(float cargosExtra) {
		this.cargosExtra = cargosExtra;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Alquiler(Automovil automovil, Cliente cliente, Date fechaInicio,
			Date fechaFin, float porcentajeDescuento,
			String descripcionInspeccion, boolean entregoDocumentacion) {

		this.automovil = automovil;
		this.cliente = cliente;
		this.fechaFin = fechaFin;
		this.fechaInicio = fechaInicio;
		this.porcentajeDescuento = porcentajeDescuento;
		this.descripcionInspeccion = descripcionInspeccion;
		this.entregoDocumentacion = entregoDocumentacion;
		this.numeroAlquiler = Alquiler.obtenerNumeroAlquiler();
		this.factura = null;
	}

	private static int obtenerNumeroAlquiler() {
		return ++siguienteNumeroAlquiler;
	}

	public boolean sosAlquiler(int nroAlquiler) {
		return this.numeroAlquiler == nroAlquiler;
	}

	public void registrarDevolucion(Date fechaDevolucion,
			float cantidadCombustible, float kilometrosRecorridos,
			boolean obtuvoDanios, float cargosExtra) {

		this.fechaDevolucion = fechaDevolucion;
		this.cantidadCombustible = cantidadCombustible;
		this.kilometrajeRecorrido = kilometrosRecorridos;
		this.obtuvoDanios = obtuvoDanios;
		this.cargosExtra = cargosExtra;

	}

	public void facturar() {
		
		Date fechaActual = new GregorianCalendar().getTime();
		
		// Le doy que son 100 km por dia, mas de eso es exedente
		
		float kmExtra = (this.kilometrajeRecorrido - Float.intBitsToFloat(fechaInicio.compareTo(fechaFin) * 100))
				* this.automovil.getModelo().getCostoKmExcedente();	
		
		float costoDia = this.automovil.getModelo().getCostoDia()
				* Float.intBitsToFloat(fechaInicio.compareTo(fechaFin));
		
		
		float monto = this.cargosExtra + kmExtra + costoDia;

		String detalles = "Costos: /n" + "Cargos Extra: " + String.valueOf(this.cargosExtra) + "/n"
				+ "Kilometraje Extra: " + String.valueOf(kmExtra) + "/n" + "Cargos por dia: "
				+ String.valueOf(costoDia) + "/n";
	
		Factura f = new Factura(detalles, fechaActual, this.cliente, monto);
		this.automovil.registrarDevolucion(this.kilometrajeRecorrido,
				this.cantidadCombustible);
		this.factura = f;

	}

	public boolean tenesCliente(int nroCliente) {
		return this.cliente.sosCliente(nroCliente);
	}

	public boolean haySolapamiento(Date fechaDesde, Date fechaHasta) {
		Date fechaFinal = (null != this.fechaDevolucion) ? this.fechaDevolucion : this.fechaFin;
		return this.fechaInicio.compareTo(fechaHasta) <= 0 && fechaFinal.compareTo(fechaDesde) >= 0;
	}

}
