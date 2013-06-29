package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Alquiler;
import model.Automovil;
import model.Cliente;
import model.ClienteWeb;
import model.Modelo;
import model.Reserva;
import model.TipoModelo;
import util.ElementoCombo;
import util.RespuestaSistema;
import util.RespuestaTransaccion;
import view.vistas.AutoView;
import view.vistas.ClienteView;
import view.vistas.ModeloView;

public class AlquilerAutos {

	private List<Cliente> clientes;
	private List<Modelo> modelos;
	private List<Alquiler> alquileres;
	private List<Reserva> reservas;

	public AlquilerAutos() {
		this.clientes = new ArrayList<Cliente>();
		this.modelos = new ArrayList<Modelo>();
		this.alquileres = new ArrayList<Alquiler>();
		this.reservas = new ArrayList<Reserva>();
	}

	// --- Administraci�n de clientes --- INICIO
	public RespuestaTransaccion altaCliente(String nombre, String apellido, Date fechaNacimiento, String domicilio,
			String telefono, Long dni, String sexo, String nacionalidad) {

		if (buscarCliente(dni) == null) {

			Cliente c = new Cliente(nombre, apellido, fechaNacimiento, domicilio, telefono, dni, sexo, nacionalidad);

			this.clientes.add(c);

			return new RespuestaTransaccion(RespuestaSistema.OK, String.valueOf(c.getNumeroCliente()));

		} else {
			return new RespuestaTransaccion(RespuestaSistema.CLIENTE_DUPLICADO);
		}

	}

	public int altaClienteWeb(String nombre, String apellido, Date fechaNacimiento, String domicilio, String telefono,
			Long dni, String sexo, String nacionalidad, String usuario, String password) {

		Cliente c = new ClienteWeb(nombre, apellido, fechaNacimiento, domicilio, telefono, dni, sexo, nacionalidad,
				usuario, password);

		this.clientes.add(c);

		return c.getNumeroCliente();
	}

	public RespuestaTransaccion modificarCliente(Long numeroCliente, String nombre, String apellido,
			Date fechaNacimiento, String domicilio, String telefono, Long dni, String sexo, String nacionalidad) {

		Cliente c = this.buscarCliente(numeroCliente);

		if (c != null) {
			c.actualizarDatos(nombre, apellido, fechaNacimiento, domicilio, dni, telefono, sexo, nacionalidad);
			return new RespuestaTransaccion(RespuestaSistema.OK);
		} else {
			return new RespuestaTransaccion(RespuestaSistema.CLIENTE_INEXISTENTE);
		}

	}

	public void modificarClienteWeb(String nombre, String apellido, Date fechaNacimiento, String domicilio,
			String telefono, Long dni, String sexo, String nacionalidad, String usuario, String password) {

		ClienteWeb c = (ClienteWeb) this.buscarCliente(dni);

		if (c != null) {
			c.actualizarDatos(nombre, apellido, fechaNacimiento, domicilio, dni, telefono, sexo, nacionalidad, usuario,
					password);
		}

	}

	// TODO : eliminarCliente

	public void bajaCliente(int nroCliente) {
		clientes.remove(buscarCliente(nroCliente));
	}

	private Cliente buscarCliente(Long dni) {

		Cliente c = null;

		for (Cliente cliente : this.clientes) {
			if (cliente.sosCliente(dni)) {
				c = cliente;
				break;
			}
		}

		return c;
	}

	private Cliente buscarCliente(int nroCliente) {

		Cliente c = null;

		for (Cliente cliente : this.clientes) {
			if (cliente.sosCliente(nroCliente)) {
				c = cliente;
				break;
			}
		}

		return c;
	}

	public ClienteView buscarClienteView(int nroCliente) {

		Cliente c = this.buscarCliente(nroCliente);

		if (c == null) {
			return null;
		} else {
			return new ClienteView(c);
		}

	}

	// --- Administraci�n de clientes --- FIN

	// --- Administraci�n de autom�viles --- INICIO
	public RespuestaTransaccion altaModelo(String datosMotor, String datosSeguridad, String marca, String modelo,
			String tipo, float costoDia, float costoKmExcedente) {

		Modelo m = this.buscarModelo(marca, modelo);
		if (m == null) {
			m = new Modelo(datosMotor, datosSeguridad, marca, modelo, tipo, costoDia, costoKmExcedente);
			this.modelos.add(m);
			return new RespuestaTransaccion(RespuestaSistema.OK, String.valueOf(m.getNumeroModelo()));
		}

		return new RespuestaTransaccion(RespuestaSistema.MODELO_DUPLICADO);

	}

	public RespuestaTransaccion modificarModelo(int nroModelo, String datosMotor, String datosSeguridad, String marca,
			String modelo, String tipo, float costoDia, float costoKmExcedente) {

		Modelo m = this.buscarModelo(nroModelo);
		if (m != null) {
			m.actualizarDatos(datosMotor, datosSeguridad, marca, modelo, tipo, costoDia, costoKmExcedente);
			return new RespuestaTransaccion(RespuestaSistema.OK);
		}

		return new RespuestaTransaccion(RespuestaSistema.MODELO_INEXISTENTE);
	}

	public RespuestaTransaccion altaAutomovil(int nroModelo, String patente, int anio, long kilometraje,
			float combustible) {

		Modelo m = this.buscarModelo(nroModelo);
		// TODO : cambiar la creaci�n de autom�vil, que no dependa de modelo.
		if (m != null) {
			m.agregarAutomovil(anio, combustible, kilometraje, patente);
			return new RespuestaTransaccion(RespuestaSistema.OK, String.valueOf(m.getNumeroModelo()));
		}

		return new RespuestaTransaccion(RespuestaSistema.MODELO_INEXISTENTE);

	}

	public RespuestaTransaccion modificarAutomovil(int nroModelo, String patente, int anio, long kilometraje,
			float combustible, String nuevaPatente) {

		Modelo m = this.buscarModelo(nroModelo);
		// TODO : cambiar la modificaci�n de autom�vil, que no dependa de
		// modelo.
		if (m != null) {
			int errorSistema = m.actualizarAutomovil(patente, anio, kilometraje, combustible, nuevaPatente);
			return new RespuestaTransaccion(RespuestaSistema.fromCode(errorSistema));
		}

		return new RespuestaTransaccion(RespuestaSistema.MODELO_INEXISTENTE);

	}

	public int bajaAutomovil(int nroModelo, String patente) {

		Modelo m = this.buscarModelo(nroModelo);

		if (m != null) {

			Automovil a = m.buscarAutomovil(patente);
			// TODO : terminar eliminar autom�vil cuando esten el manejo de
			// reservas.
			return RespuestaSistema.OK.getKey();
		}

		return RespuestaSistema.MODELO_INEXISTENTE.getKey();

	}

	private Modelo buscarModelo(String marca, String modelo) {

		Modelo m = null;

		for (Modelo mod : this.modelos) {
			if (mod.sosModelo(marca, modelo)) {
				m = mod;
				break;
			}
		}

		return m;

	}

	public ModeloView buscarModeloView(int nroModelo) {

		Modelo m = this.buscarModelo(nroModelo);

		if (m == null) {
			return null;
		} else {
			return new ModeloView(m);
		}

	}

	private Modelo buscarModelo(int numeroModelo) {

		Modelo m = null;

		for (Modelo mod : this.modelos) {
			if (mod.sosModelo(numeroModelo)) {
				m = mod;
				break;
			}
		}

		return m;

	}

	public AutoView buscarAutoView(String patente) {

		for (Modelo m : this.modelos) {
			Automovil a = m.buscarAutomovil(patente);
			if (a != null) {
				return new AutoView(m.getModelo(), a);
			}
		}

		return null;

	}

	// --- Administraci�n de autom�viles --- FIN

	// --- Administraci�n de reservas --- INICIO

	public int registrarReserva(int nroCliente, int nroModelo, String patente, Date fechaInicio, Date fechaFin,
			float multaCancelacion) {

		Cliente c = this.buscarCliente(nroCliente);
		if (c == null)
			return RespuestaSistema.CLIENTE_INEXISTENTE.getKey();

		Modelo m = this.buscarModelo(nroModelo);
		if (m == null)
			return RespuestaSistema.MODELO_INEXISTENTE.getKey();

		Automovil a = m.buscarAutomovil(patente);
		if (a == null)
			return RespuestaSistema.AUTOMOVIL_INEXISTENTE.getKey();

		Reserva r = new Reserva(a, c, fechaFin, fechaInicio, multaCancelacion);
		this.reservas.add(r);

		return RespuestaSistema.OK.getKey();
	}

	public float cancelarReserva(int nroReserva) {

		Reserva r = this.buscarReserva(nroReserva);
		this.reservas.remove(r);
		return r.getMultaCancelacion();

	}

	private Reserva buscarReserva(int nroReserva) {

		for (Reserva r : this.reservas) {
			if (r.sosReserva(nroReserva)) {
				return r;
			}
		}

		return null;

	}

	// --- Administraci�n de reservas --- FIN

	// --- Administraci�n de alquileres --- INICIO

	public int registrarAlquiler(int nroCliente, int nroModelo, String patente, Date fechaInicio, Date fechaFin,
			String descripcionInspeccion, boolean entregaDocumentacion, float porcentajeDescuento) {

		Cliente c = this.buscarCliente(nroCliente);
		if (c == null)
			return RespuestaSistema.CLIENTE_INEXISTENTE.getKey();

		Modelo m = this.buscarModelo(nroModelo);
		if (m == null)
			return RespuestaSistema.MODELO_INEXISTENTE.getKey();

		Automovil a = m.buscarAutomovil(patente);
		if (a == null)
			return RespuestaSistema.AUTOMOVIL_INEXISTENTE.getKey();

		Alquiler al = new Alquiler(a, c, fechaInicio, fechaFin, porcentajeDescuento, descripcionInspeccion,
				entregaDocumentacion);

		a.alquilado();

		this.alquileres.add(al);

		return RespuestaSistema.OK.getKey();

	}

	public int registrarDevolucionAutomovil(int nroAlquiler, Date fechaDevolucion, float cantidadCombustible,
			float kilometrosRecorridos, boolean obtuvoDanios, float cargosExtra) {

		Alquiler a = this.buscarAlquiler(nroAlquiler);
		a.registrarDevolucion(fechaDevolucion, cantidadCombustible, kilometrosRecorridos, obtuvoDanios, cargosExtra);
		a.facturar();

		return RespuestaSistema.OK.getKey();

	}

	private Alquiler buscarAlquiler(int nroAlquiler) {

		for (Alquiler a : this.alquileres) {
			if (a.sosAlquiler(nroAlquiler)) {
				return a;
			}
		}

		return null;

	}

	// --- Administraci�n de alquileres --- FIN

	public ElementoCombo[] listaTiposModelo() {
		List<ElementoCombo> lista = new ArrayList<ElementoCombo>();
		for (TipoModelo tipoModelo : TipoModelo.values()) {
			lista.add(new ElementoCombo(tipoModelo.getCodigo(), tipoModelo.getDescripcion()));
		}

		return lista.toArray(new ElementoCombo[lista.size()]);
	}

	public ElementoCombo[] listaModelos() {
		List<ElementoCombo> lista = new ArrayList<ElementoCombo>();
		for (Modelo modelo : this.modelos) {
			lista.add(new ElementoCombo(String.valueOf(modelo.getNumeroModelo()), modelo.getMarca() + " - "
					+ modelo.getModelo()));
		}
		return lista.toArray(new ElementoCombo[lista.size()]);
	}
}