package view.swing.alquiler;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import util.ElementoCombo;
import util.ErrorGui;
import util.RespuestaGui;
import util.RespuestaSistema;
import util.RespuestaTransaccion;
import util.Util;
import view.vistas.AlquilerView;
import view.vistas.AutoView;
import view.vistas.ModeloView;
import controller.AlquilerAutos;

public class DatosDevolucionPanel extends JPanel implements FocusListener, ActionListener {

	private static final long serialVersionUID = -6697214022528265655L;

	private AlquilerAutos sistema;

	private JPanel innerPanel;

	// Datos del alquiler
	private JTextField cliente;
	private JTextField dni;
	private JTextField fechaInicio;
	private JTextField fechaFin;
	private JComboBox<ElementoCombo> modelos;
	private JComboBox<ElementoCombo> autos;

	// Datos devolución
	private JTextField fechaDevolucion;
	private JCheckBox obtuvoDanios;
	private JTextField combustible;
	private JTextField kilometraje;
	private JTextField cargosExtra;

	private JLabel clienteLabel;
	private JLabel dniLabel;
	private JLabel fechaInicioLabel;
	private JLabel fechaFinLabel;
	private JLabel modelosLabel;
	private JLabel autosLabel;

	private JLabel fechaDevolucionLabel;
	private JLabel obtuvoDaniosLabel;
	private JLabel combustibleLabel;
	private JLabel kilometrajeLabel;
	private JLabel cargosExtraLabel;

	private int numeroAlquiler;
	private Map<ModeloView, List<AutoView>> mapaModelosAutos;

	public DatosDevolucionPanel(AlquilerAutos sistema) {
		this.sistema = sistema;
		this.mapaModelosAutos = new HashMap<ModeloView, List<AutoView>>();
		this.numeroAlquiler = -1;
		this.init();
	}

	private void init() {

		this.setLayout(new GridBagLayout());
		GridBagConstraints gBC = new GridBagConstraints();

		this.innerPanel = new JPanel(new GridBagLayout());
		this.innerPanel.setBorder(BorderFactory.createTitledBorder("Datos del Alquiler"));
		gBC.insets = new Insets(5, 5, 5, 5);
		gBC.gridwidth = 4;
		this.add(this.innerPanel, gBC);

		int y = 0;

		gBC.anchor = GridBagConstraints.NORTHEAST;
		gBC.insets = new Insets(2, 2, 2, 2);
		gBC.gridy = y;

		gBC.gridx = 0;
		gBC.gridwidth = 1;
		this.clienteLabel = new JLabel("Cliente:");
		this.innerPanel.add(this.clienteLabel, gBC);

		gBC.gridx = 1;
		this.cliente = new JTextField(15);
		this.cliente.setEditable(false);
		this.innerPanel.add(this.cliente, gBC);

		gBC.gridx = 2;
		this.dniLabel = new JLabel("DNI:");
		this.innerPanel.add(this.dniLabel, gBC);

		gBC.gridx = 3;
		this.dni = new JTextField(15);
		this.dni.setEditable(false);
		this.innerPanel.add(this.dni, gBC);

		y++;

		gBC.gridy = y;

		gBC.gridx = 0;
		this.fechaInicioLabel = new JLabel("Desde:");
		this.innerPanel.add(this.fechaInicioLabel, gBC);

		gBC.gridx = 1;
		gBC.anchor = GridBagConstraints.NORTHWEST;
		this.fechaInicio = new JTextField(8);
		this.fechaInicio.setEditable(false);
		innerPanel.add(this.fechaInicio, gBC);

		gBC.gridx = 2;
		gBC.anchor = GridBagConstraints.NORTHEAST;
		this.fechaFinLabel = new JLabel("Hasta:");
		this.innerPanel.add(this.fechaFinLabel, gBC);

		gBC.gridx = 3;
		gBC.anchor = GridBagConstraints.NORTHWEST;
		this.fechaFin = new JTextField(8);
		this.fechaFin.setEditable(false);
		innerPanel.add(this.fechaFin, gBC);

		y++;

		gBC.gridy = y;

		gBC.gridx = 0;
		gBC.gridwidth = 1;
		this.modelosLabel = new JLabel("Modelos:");
		this.innerPanel.add(this.modelosLabel, gBC);

		gBC.gridx = 1;
		gBC.anchor = GridBagConstraints.NORTHWEST;
		this.modelos = new JComboBox<ElementoCombo>();
		this.modelos.setEditable(false);
		this.innerPanel.add(this.modelos, gBC);

		gBC.gridx = 2;
		gBC.anchor = GridBagConstraints.NORTHEAST;
		this.autosLabel = new JLabel("Autos:");
		this.innerPanel.add(this.autosLabel, gBC);

		gBC.gridx = 3;
		gBC.anchor = GridBagConstraints.NORTHWEST;
		this.autos = new JComboBox<ElementoCombo>();
		this.autos.setEditable(false);
		this.innerPanel.add(this.autos, gBC);

		y++;

		gBC.gridy = y;

		gBC.gridx = 0;
		this.fechaDevolucionLabel = new JLabel("Fecha Devolución:");
		this.innerPanel.add(this.fechaDevolucionLabel, gBC);

		gBC.gridx = 1;
		gBC.anchor = GridBagConstraints.NORTHWEST;
		MaskFormatter fechaFormatter = null;
		try {
			fechaFormatter = new MaskFormatter("##/##/####");
			fechaFormatter.setPlaceholderCharacter('_');
		} catch (ParseException e) {
		}
		if (fechaFormatter != null) {
			this.fechaDevolucion = new JFormattedTextField(fechaFormatter);
		} else {
			this.fechaDevolucion = new JTextField(8);
		}
		this.fechaDevolucion.addFocusListener(this);
		innerPanel.add(this.fechaDevolucion, gBC);

		gBC.gridx = 2;
		this.obtuvoDanios = new JCheckBox();
		this.innerPanel.add(this.obtuvoDanios, gBC);

		gBC.gridx = 3;
		this.obtuvoDaniosLabel = new JLabel("Obtuvo Daños");
		this.innerPanel.add(this.obtuvoDaniosLabel, gBC);

	}

	private boolean todosCamposValidos() {
		return Util.fechaValida(this.fechaInicio.getText()) && Util.fechaValida(this.fechaFin.getText())
				&& this.modelos.getSelectedItem() != null && this.autos.getSelectedItem() != null;
	}

	// Focus Listener
	@Override
	public void focusGained(FocusEvent fe) {

	}

	@Override
	public void focusLost(FocusEvent fe) {
		if (fe.getSource() == this.fechaDevolucion) {
			if (!Util.fechaValida(this.fechaDevolucion.getText())) {
				Util.mostrarError(this, "Fecha Devolución inválida");
			}
		}
	}

	// Action Listener
	@Override
	public void actionPerformed(ActionEvent e) {
		// if (e.getSource() == this.disponibilidadButton) {
		// this.mapaModelosAutos = sistema.listarModelosAutosDisponibilidad(
		// Util.parseFecha(this.fechaInicio.getText()),
		// Util.parseFecha(this.fechaFin.getText()));
		// this.modelos.removeAllItems();
		// for (ModeloView modelo : mapaModelosAutos.keySet()) {
		// if (!mapaModelosAutos.get(modelo).isEmpty()) {
		// this.modelos.addItem(new
		// ElementoCombo(String.valueOf(modelo.getNumeroModelo()),
		// modelo.getMarca()
		// + " - " + modelo.getModelo()));
		// }
		// }
		// this.modelos.setEnabled(true);
		// } else if (e.getSource() == this.modelos) {
		// this.autos.removeAllItems();
		// ElementoCombo modeloSeleccionado = (ElementoCombo)
		// this.modelos.getSelectedItem();
		// for (ModeloView modelo : mapaModelosAutos.keySet()) {
		// if (!mapaModelosAutos.get(modelo).isEmpty()
		// && modelo.getNumeroModelo() ==
		// Integer.parseInt(modeloSeleccionado.getCodigo())) {
		// for (AutoView auto : mapaModelosAutos.get(modelo)) {
		// this.autos.addItem(new ElementoCombo(auto.getPatente(),
		// auto.getAnio() + " - "
		// + auto.getPatente()));
		// }
		// }
		// }
		// this.autos.setEnabled(true);
		// }
	}

	public RespuestaGui registrarDevolucion() {
		if (this.todosCamposValidos()) {
			// ElementoCombo modelo = (ElementoCombo)
			// this.modelos.getSelectedItem();
			// ElementoCombo auto = (ElementoCombo)
			// this.autos.getSelectedItem();
			// RespuestaTransaccion respuesta =
			// this.sistema.registrarAlquiler(this.numeroCliente,
			// Integer.parseInt(modelo.getCodigo()), auto.getCodigo(),
			// Util.parseFecha(this.fechaInicio.getText()),
			// Util.parseFecha(this.fechaFin.getText()),
			// this.inspeccion.getText(), this.documentacion.isSelected(),
			// (this.descuento != null) ?
			// Float.parseFloat(this.descuento.getText()) : 0);
			// if (respuesta.getTipoRespuesta().equals(RespuestaSistema.OK)) {
			// return new RespuestaGui(ErrorGui.OK, respuesta.getMensaje());
			// } else {
			// return new RespuestaGui(ErrorGui.ERROR_TRANSACCION,
			// respuesta.getTipoRespuesta().getDescripcion());
			// }
		} else {
			return new RespuestaGui(ErrorGui.ERROR_VALIDACION);
		}
		return null;
	}

	// public RespuestaGui generarAlquilerConReserva() {
	// // Los campos obligatorios están precargados; no se valida.
	// RespuestaTransaccion respuesta =
	// this.sistema.registrarAlquiler(this.numeroAlquiler,
	// this.inspeccion.getText(),
	// this.documentacion.isSelected(), (this.descuento != null) ?
	// Float.parseFloat(this.descuento.getText())
	// : 0);
	// if (respuesta.getTipoRespuesta().equals(RespuestaSistema.OK)) {
	// return new RespuestaGui(ErrorGui.OK, respuesta.getMensaje());
	// } else {
	// return new RespuestaGui(ErrorGui.ERROR_TRANSACCION,
	// respuesta.getTipoRespuesta().getDescripcion());
	// }
	// }

	// private void limpiaModelos() {
	// this.modelos.removeAllItems();
	// this.modelos.setEnabled(false);
	// this.autos.removeAllItems();
	// this.autos.setEnabled(false);
	// }

	public void cargaAlquiler(AlquilerView alquilerView, int numeroAlquiler) {
		this.numeroAlquiler = numeroAlquiler;
		this.cliente.setText(alquilerView.getNombre() + " " + alquilerView.getApellido());
		this.dni.setText(alquilerView.getDni().toString());
		this.fechaInicio.setText(alquilerView.getFechaInicio());
		this.fechaFin.setText(alquilerView.getFechaFin());
		this.modelos.removeAllItems();
		this.modelos.addItem(new ElementoCombo("", alquilerView.getMarca() + " - " + alquilerView.getModelo()));
		this.autos.removeAllItems();
		this.autos.addItem(new ElementoCombo("", alquilerView.getAnio() + " - " + alquilerView.getPatente()));
	}

	// public void cargaCliente(ClienteView clienteView) {
	// this.cliente.setText(clienteView.getNombre() + " " +
	// clienteView.getApellido());
	// this.dni.setText(clienteView.getDni().toString());
	// this.numeroCliente = clienteView.getNumeroCliente();
	// }

}