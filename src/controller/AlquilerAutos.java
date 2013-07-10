package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import view.vistas.ClienteWebView;
import view.vistas.ModeloView;
import view.vistas.ReservaView;

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

	// --- Administración de clientes --- INICIO
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

	public RespuestaTransaccion altaClienteWeb(String nombre, String apellido, Date fechaNacimiento, String domicilio,
			String telefono, Long dni, String sexo, String nacionalidad, String usuario, String password) {

		if (buscarCliente(usuario) == null) {
			Cliente c = new ClienteWeb(nombre, apellido, fechaNacimiento, domicilio, telefono, dni, sexo, nacionalidad,
					usuario, password);
			this.clientes.add(c);
			return new RespuestaTransaccion(RespuestaSistema.OK, String.valueOf(c.getNumeroCliente()));
		} else {
			return new RespuestaTransaccion(RespuestaSistema.USUARIO_DUPLICADO);
		}
	}

	public RespuestaTransaccion modificarCliente(Long numeroCliente, String nombre, String apellido,
			Date fechaNacimiento, String domicilio, String telefono, Long dni, String sexo, String nacionalidad) {
		Cliente c = this.buscarCliente(numeroCliente);

		if (c != null) 
		{
			c.actualizarDatos(nombre, apellido, fechaNacimiento, domicilio, dni, telefono, sexo, nacionalidad);
			return new RespuestaTransaccion(RespuestaSistema.OK);
		} 
		else 
		{
			return new RespuestaTransaccion(RespuestaSistema.CLIENTE_INEXISTENTE);
		}
	}

	public RespuestaTransaccion modificarClienteWeb(String nombre, String apellido, Date fechaNacimiento,
			String domicilio, String telefono, Long dni, String sexo, String nacionalidad, String usuario,
			String password) {
		ClienteWeb c = (ClienteWeb) this.buscarCliente(usuario);

		if (c != null) 
		{
			c.actualizarDatos(nombre, apellido, fechaNacimiento, domicilio, dni, telefono, sexo, nacionalidad, usuario, password);
			return new RespuestaTransaccion(RespuestaSistema.OK);
		} 
		else 
		{
			return new RespuestaTransaccion(RespuestaSistema.CLIENTE_INEXISTENTE);
		}
	}

	public RespuestaTransaccion bajaCliente(int nroCliente) {

		Cliente c = buscarCliente(nroCliente);
		if (c == null) {
			return new RespuestaTransaccion(RespuestaSistema.CLIENTE_INEXISTENTE);
		}

		for (Reserva reserva : this.reservas) {
			if (reserva.tenesCliente(nroCliente)) {
				// c.setBajaLogica(true); no deberiamos desactivarlo si tiene
				// reservas, tiene q cancelar reservas primero
				return new RespuestaTransaccion(RespuestaSistema.CLIENTE_CON_RESERVA);
			}
		}
		for (Alquiler alquiler : this.alquileres) {
			if (alquiler.tenesCliente(nroCliente)) {
				c.setBajaLogica(true);
				return new RespuestaTransaccion(RespuestaSistema.OK, "Ciente desactivado");
			}
		}

		clientes.remove(c);
		return new RespuestaTransaccion(RespuestaSistema.OK, "Cliente eliminado");
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

	private ClienteWeb buscarCliente(String user) {
		for (int i = 0; i < this.clientes.size(); i++) 
		{
			try 
			{
				ClienteWeb c = (ClienteWeb) this.clientes.get(i);
				if (c.sosCliente(user)) 
				{
					return c;
				}
			} 
			catch (ClassCastException cce) 
			{
				// No es cliente web; no me interesa
			}
		}

		return null;
	}

	public ClienteView buscarClienteView(int nroCliente) {
		Cliente c = this.buscarCliente(nroCliente);

		if (c == null) 
		{
			return null;
		} 
		else 
		{
			return new ClienteView(c);
		}
	}

	// --- Administración de clientes --- FIN

	// --- Administración de automóviles --- INICIO
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
		if (m != null) {
			m.agregarAutomovil(anio, combustible, kilometraje, patente);
			return new RespuestaTransaccion(RespuestaSistema.OK, String.valueOf(m.getNumeroModelo()));
		}

		return new RespuestaTransaccion(RespuestaSistema.MODELO_INEXISTENTE);

	}

	public RespuestaTransaccion modificarAutomovil(int nroModelo, String patente, int anio, long kilometraje,
			float combustible, String nuevaPatente) {

		Modelo m = this.buscarModelo(nroModelo);
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

			for (Reserva reserva : this.reservas) {
				if (reserva.tenesAutomovil(a)) {
					return RespuestaSistema.AUTOMOVIL_RESERVADO.getKey();
				}
			}

			m.quitarAutomovil(a);
			// TODO:No se si esto deberia quedar o no, para eliminar el modelo
			// cuando se queda sin autos
			if (!m.tenesAutos()) {
				this.modelos.remove(m);
			}
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

	private Modelo buscarModelo(Automovil auto) {

		Modelo m = null;

		for (Modelo mod : this.modelos) {
			if (mod.tenesAuto(auto)) {
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

	// --- Administración de automóviles --- FIN

	// --- Administración de reservas --- INICIO

	public RespuestaTransaccion registrarReserva(int nroCliente, int nroModelo, String patente, Date fechaInicio,
			Date fechaFin) {

		Cliente c = this.buscarCliente(nroCliente);
		if (c == null)
			return new RespuestaTransaccion(RespuestaSistema.CLIENTE_INEXISTENTE);

		Modelo m = this.buscarModelo(nroModelo);
		if (m == null)
			return new RespuestaTransaccion(RespuestaSistema.MODELO_INEXISTENTE);

		Automovil a = this.buscarAutomovilDisponible(m, fechaInicio, fechaFin);

		Reserva r = new Reserva(a, c, fechaFin, fechaInicio);
		this.reservas.add(r);

		return new RespuestaTransaccion(RespuestaSistema.OK, String.valueOf(r.getNumeroReserva()));
	}

	// PRE: el modelo tiene un automóvil disponible
	private Automovil buscarAutomovilDisponible(Modelo m, Date fechaDesde, Date fechaHasta) {

		List<Automovil> listaAutos = new ArrayList<Automovil>();
		listaAutos.addAll(m.getAutomoviles());

		// Se quitan aquéllos autos que estén alquilados para esas fechas.
		for (Alquiler alquiler : this.alquileres) {
			if (alquiler.haySolapamiento(fechaDesde, fechaHasta)) {
				listaAutos.remove(alquiler.getAutomovil());
			}
		}

		// se quitan aquéllos autos que estén reservados para esas fechas.
		for (Reserva reserva : this.reservas) {
			if (reserva.haySolapamiento(fechaDesde, fechaHasta)) {
				listaAutos.remove(reserva.getAutomovil());
			}
		}

		return listaAutos.get(0);

	}

	public RespuestaTransaccion cancelarReserva(int nroReserva) {

		Reserva r = this.buscarReserva(nroReserva);

		if (r == null) {
			return new RespuestaTransaccion(RespuestaSistema.NRO_RESERVA_INEXISTENTE);
		}

		this.reservas.remove(r);

		if (r.aplicaMulta(new Date())) {
			return new RespuestaTransaccion(RespuestaSistema.APLICA_MULTA, String.valueOf(r.getMultaCancelacion()));
		} else {
			return new RespuestaTransaccion(RespuestaSistema.OK);
		}

	}

	private Reserva buscarReserva(int nroReserva) {

		for (Reserva r : this.reservas) {
			if (r.sosReserva(nroReserva)) {
				return r;
			}
		}

		return null;

	}

	public ReservaView buscarReservaView(int nroReserva) {

		for (Reserva r : this.reservas) {
			if (r.sosReserva(nroReserva)) {
				Modelo m = this.buscarModelo(r.getAutomovil());
				return new ReservaView(r, m);
			}
		}

		return null;
	}

	// --- Administración de reservas --- FIN

	// --- Administración de alquileres --- INICIO

	public RespuestaTransaccion registrarAlquiler(int nroCliente, int nroModelo, String patente, Date fechaInicio,
			Date fechaFin, String descripcionInspeccion, boolean entregaDocumentacion, float porcentajeDescuento) {

		Cliente c = this.buscarCliente(nroCliente);
		if (c == null)
			return new RespuestaTransaccion(RespuestaSistema.CLIENTE_INEXISTENTE);

		Modelo m = this.buscarModelo(nroModelo);
		if (m == null)
			return new RespuestaTransaccion(RespuestaSistema.MODELO_INEXISTENTE);

		Automovil a = m.buscarAutomovil(patente);
		if (a == null)
			return new RespuestaTransaccion(RespuestaSistema.AUTOMOVIL_INEXISTENTE);

		Alquiler al = new Alquiler(a, c, fechaInicio, fechaFin, porcentajeDescuento, descripcionInspeccion,
				entregaDocumentacion);

		a.alquilado();

		this.alquileres.add(al);

		return new RespuestaTransaccion(RespuestaSistema.OK, String.valueOf(al.getNumeroAlquiler()));

	}

	public RespuestaTransaccion registrarAlquiler(int numeroReserva, String descripcionInspeccion,
			boolean entregaDocumentacion, float porcentajeDescuento) {

		Reserva r = this.buscarReserva(numeroReserva);

		if (r == null) {
			return new RespuestaTransaccion(RespuestaSistema.NRO_RESERVA_INEXISTENTE);
		}

		Alquiler al = new Alquiler(r, porcentajeDescuento, descripcionInspeccion, entregaDocumentacion);

		this.alquileres.add(al);

		return new RespuestaTransaccion(RespuestaSistema.OK, String.valueOf(al.getNumeroAlquiler()));
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

	// --- Administración de alquileres --- FIN

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

	public ElementoCombo[] listaModelosConAutos() {
		List<ElementoCombo> lista = new ArrayList<ElementoCombo>();
		for (Modelo modelo : this.modelos) {
			if (modelo.tenesAutos()) {
				lista.add(new ElementoCombo(String.valueOf(modelo.getNumeroModelo()), modelo.getMarca() + " - "
						+ modelo.getModelo()));
			}
		}
		return lista.toArray(new ElementoCombo[lista.size()]);
	}

	public ElementoCombo[] listarModelosConDisponibilidad(Date fechaDesde, Date fechaHasta) {

		// Se crea un mapa con todos los autos de todos los modelos
		Map<Modelo, List<Automovil>> mapaModelosAutos = new HashMap<Modelo, List<Automovil>>();
		for (Modelo modelo : this.modelos) {
			List<Automovil> autos = new ArrayList<Automovil>();
			autos.addAll(modelo.getAutomoviles());
			mapaModelosAutos.put(modelo, autos);
		}

		// Se quitan aquéllos autos que estén alquilados para esas fechas.
		for (Alquiler alquiler : this.alquileres) {
			if (alquiler.haySolapamiento(fechaDesde, fechaHasta)) {
				this.remueveAutoAMapa(mapaModelosAutos, alquiler.getAutomovil());
			}
		}

		// se quitan aquéllos autos que estén reservados para esas fechas.
		for (Reserva reserva : this.reservas) {
			if (reserva.haySolapamiento(fechaDesde, fechaHasta)) {
				this.remueveAutoAMapa(mapaModelosAutos, reserva.getAutomovil());
			}
		}

		List<ElementoCombo> lista = new ArrayList<ElementoCombo>();
		for (Modelo modelo : mapaModelosAutos.keySet()) {
			if (!mapaModelosAutos.get(modelo).isEmpty()) {
				lista.add(new ElementoCombo(String.valueOf(modelo.getNumeroModelo()), modelo.getMarca() + " - "
						+ modelo.getModelo()));
			}
		}

		return lista.toArray(new ElementoCombo[lista.size()]);
	}

	private void remueveAutoAMapa(Map<Modelo, List<Automovil>> mapa, Automovil auto) {
		for (Modelo modelo : mapa.keySet()) {
			if (mapa.get(modelo).contains(auto)) {
				mapa.get(modelo).remove(auto);
				break;
			}
		}
	}

	// --- Login ---
	public RespuestaTransaccion login(String user, String password) {

		for (int i = 0; i < this.clientes.size(); i++) {
			try {
				ClienteWeb c = (ClienteWeb) this.clientes.get(i);
				if (c.sosCliente(user, password)) {
					return new RespuestaTransaccion(RespuestaSistema.OK);
				}
			} catch (ClassCastException cce) {
				// No es cliente web; no me interesa.
			}
		}

		return new RespuestaTransaccion(RespuestaSistema.USUARIO_CONTRASEÑA_INVALIDO);

	}

	public ClienteWebView buscarClienteWebView(String user) {
		ClienteWeb c = this.buscarCliente(user);

		if (c == null) 
		{
			return null;
		} 
		else 
		{
			return new ClienteWebView(c);
		}
	}
	
	public ClienteView buscarClienteView(String user) {
		Cliente c = this.buscarCliente(user);

		if (c == null) 
		{
			return null;
		} 
		else 
		{
			return new ClienteView(c);
		}
	}

	public Map<ModeloView, List<AutoView>> listarModelosAutosDisponibilidad(Date fechaDesde, Date fechaHasta) {

		// Se crea un mapa con todos los autos de todos los modelos
		Map<Modelo, List<Automovil>> mapaModelosAutos = new HashMap<Modelo, List<Automovil>>();
		for (Modelo modelo : this.modelos) {
			List<Automovil> autos = new ArrayList<Automovil>();
			autos.addAll(modelo.getAutomoviles());
			mapaModelosAutos.put(modelo, autos);
		}

		// Se quitan aquéllos autos que estén alquilados para esas fechas.
		for (Alquiler alquiler : this.alquileres) {
			if (alquiler.haySolapamiento(fechaDesde, fechaHasta)) {
				this.remueveAutoAMapa(mapaModelosAutos, alquiler.getAutomovil());
			}
		}

		// se quitan aquéllos autos que estén reservados para esas fechas.
		for (Reserva reserva : this.reservas) {
			if (reserva.haySolapamiento(fechaDesde, fechaHasta)) {
				this.remueveAutoAMapa(mapaModelosAutos, reserva.getAutomovil());
			}
		}

		// Se pasa a vistas.
		Map<ModeloView, List<AutoView>> mapa = new HashMap<ModeloView, List<AutoView>>();
		for (Modelo modelo : this.modelos) {
			List<AutoView> autos = new ArrayList<AutoView>();
			if (modelo.getAutomoviles() != null) {
				for (Automovil auto : modelo.getAutomoviles()) {
					autos.add(new AutoView(modelo.getModelo(), auto));
				}
			}
			mapa.put(new ModeloView(modelo), autos);
		}

		return mapa;
	}

}
