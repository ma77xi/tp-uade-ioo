package view.vistas;

import util.Util;
import model.ClienteWeb;

public class ClienteWebView {

	private int numeroCliente;
	private String nombre;
	private String apellido;
	private String fechaNacimiento;
	private String domicilio;
	private String telefono;
	private Long dni;
	private boolean masculino;
	private String nacionalidad;
	private String user;
	private String password;

	public ClienteWebView(ClienteWeb cliente) {
		this.numeroCliente = cliente.getNumeroCliente();
		this.nombre = cliente.getNombre();
		this.apellido = cliente.getApellido();
		this.fechaNacimiento = Util.parseFecha(cliente.getFechaNacimiento());
		this.domicilio = cliente.getDomicilio();
		this.telefono = cliente.getTelefono();
		this.dni = cliente.getDni();
		this.masculino = cliente.getSexo().equals("M");
		this.nacionalidad = cliente.getNacionalidad();
		this.user = cliente.getUsuario();
		this.password = cliente.getPassword();
	}

	public int getNumeroCliente() {
		return numeroCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public Long getDni() {
		return dni;
	}

	public boolean isMasculino() {
		return masculino;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getPassword() {
		return password;
	}
}