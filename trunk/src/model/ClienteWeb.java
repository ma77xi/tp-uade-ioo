package model;

import java.util.Date;

public class ClienteWeb extends Cliente {

	private String usuario;
	private String password;

	public ClienteWeb(String nombre, String apellido, Date fechaNacimiento, String domicilio, String telefono,
			Long dni, String sexo, String nacionalidad, String usuario, String password) {

		super(nombre, apellido, fechaNacimiento, domicilio, telefono, dni, sexo, nacionalidad);
		this.usuario = usuario;
		this.password = password;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void actualizarDatos(String nombre, String apellido, Date fechaNacimiento, String domicilio, Long dni,
			String telefono, String sexo, String nacionalidad, String usuario, String password) {
		super.actualizarDatos(nombre, apellido, fechaNacimiento, domicilio, dni, telefono, sexo, nacionalidad);
		this.usuario = usuario;
		this.password = password;

	}

	public boolean sosCliente(String user, String password) {
		return this.usuario.equals(user) && this.password.equals(password);
	}

	public boolean sosCliente(String user) {
		return this.usuario.equals(user);
	}
}
