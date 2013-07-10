package model;

import java.util.Date;

public class Cliente {

	private static int siguienteNumeroCliente;

	private int numeroCliente;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String domicilio;
	private String telefono;
	private Long dni;
	private String sexo;
	private String nacionalidad;
	private Boolean bajaLogica;

	public int getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(int numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Boolean getBajaLogica() {
		return bajaLogica;
	}

	public void setBajaLogica(Boolean bajaLogica) {
		this.bajaLogica = bajaLogica;
	}

	public static int obtenerNumeroCiente() {
		return ++siguienteNumeroCliente;
	}

	public Cliente(String nombre, String apellido, Date fechaNacimiento, String domicilio, String telefono, Long dni,
			String sexo, String nacionalidad) {

		this.apellido = apellido;
		this.bajaLogica = Boolean.FALSE;
		this.dni = dni;
		this.domicilio = domicilio;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.nombre = nombre;
		this.numeroCliente = Cliente.obtenerNumeroCiente();
		this.sexo = sexo;
		this.telefono = telefono;
	}

	public boolean sosCliente(Long dni) {
		return this.dni.equals(dni);
	}

	public boolean sosCliente(int nroCliente) {
		return this.numeroCliente == nroCliente;
	}

	public void actualizarDatos(String nombre, String apellido, Date fechaNacimiento, String domicilio, Long dni,
			String telefono, String sexo, String nacionalidad) {

		this.apellido = apellido;
		this.domicilio = domicilio;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.nombre = nombre;
		this.sexo = sexo;
		this.telefono = telefono;
		this.dni = dni;
	}
}