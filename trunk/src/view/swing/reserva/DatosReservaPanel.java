package view.swing.reserva;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
import view.vistas.ClienteView;
import view.vistas.ReservaView;
import controller.AlquilerAutos;

public class DatosReservaPanel extends JPanel implements FocusListener, ActionListener {

	private static final long serialVersionUID = -2183603889250268077L;

	private AlquilerAutos sistema;
	private ClienteView clienteView;

	private JPanel innerPanel;

	private JTextField cliente;
	private JTextField dni;
	private JTextField fechaInicio;
	private JTextField fechaFin;
	private JButton disponibilidadButton;
	private JComboBox<ElementoCombo> modelos;
	private JComboBox<ElementoCombo> autos;

	private JLabel clienteLabel;
	private JLabel dniLabel;
	private JLabel fechaInicioLabel;
	private JLabel fechaFinLabel;
	private JLabel modelosLabel;
	private JLabel autosLabel;

	private String oldFechaInicio;
	private String oldFechaFin;

	public DatosReservaPanel(AlquilerAutos sistema, ClienteView clienteView) {
		this.sistema = sistema;
		this.clienteView = clienteView;
		this.init();
	}

	public DatosReservaPanel(AlquilerAutos sistema) {
		this.sistema = sistema;
		this.clienteView = null;
		this.init();
	}

	private void init() {

		this.setLayout(new GridBagLayout());
		GridBagConstraints gBC = new GridBagConstraints();

		this.innerPanel = new JPanel(new GridBagLayout());
		this.innerPanel.setBorder(BorderFactory.createTitledBorder("Datos de la Reserva"));
		gBC.insets = new Insets(5, 5, 5, 5);
		gBC.gridwidth = 4;
		this.add(innerPanel, gBC);

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
		if (this.clienteView != null) {
			this.cliente.setText(this.clienteView.getNombre() + " " + this.clienteView.getApellido());
		}
		this.innerPanel.add(this.cliente, gBC);

		gBC.gridx = 2;
		this.dniLabel = new JLabel("DNI:");
		this.innerPanel.add(this.dniLabel, gBC);

		gBC.gridx = 3;
		this.dni = new JTextField(15);
		this.dni.setEditable(false);
		if (this.clienteView != null) {
			this.dni.setText(this.clienteView.getDni().toString());
		}
		this.innerPanel.add(this.dni, gBC);

		y++;

		gBC.gridy = y;

		gBC.gridx = 0;
		this.fechaInicioLabel = new JLabel("Desde:");
		this.innerPanel.add(this.fechaInicioLabel, gBC);

		gBC.gridx = 1;
		MaskFormatter fechaFormatter = null;
		try {
			fechaFormatter = new MaskFormatter("##/##/####");
			fechaFormatter.setPlaceholderCharacter('_');
		} catch (ParseException e) {
		}
		if (fechaFormatter != null) {
			this.fechaInicio = new JFormattedTextField(fechaFormatter);
		} else {
			this.fechaInicio = new JTextField(8);
		}
		this.fechaInicio.addFocusListener(this);
		innerPanel.add(fechaInicio, gBC);

		gBC.gridx = 2;
		this.fechaFinLabel = new JLabel("Hasta:");
		this.innerPanel.add(this.fechaFinLabel, gBC);

		gBC.gridx = 3;
		if (fechaFormatter != null) {
			this.fechaFin = new JFormattedTextField(fechaFormatter);
		} else {
			this.fechaFin = new JTextField(8);
		}
		this.fechaFin.addFocusListener(this);
		innerPanel.add(this.fechaFin, gBC);

		y++;

		gBC.gridy = y;

		gBC.gridx = 0;
		gBC.gridwidth = 2;
		this.disponibilidadButton = new JButton("Consultar Disponibilidad");
		this.disponibilidadButton.addActionListener(this);
		this.disponibilidadButton.setEnabled(false);
		this.innerPanel.add(this.disponibilidadButton, gBC);

		gBC.gridx = 2;
		gBC.gridwidth = 1;
		this.modelosLabel = new JLabel("Modelos:");
		this.innerPanel.add(this.modelosLabel, gBC);

		gBC.gridx = 3;
		this.modelos = new JComboBox<ElementoCombo>();
		this.modelos.setEnabled(false);
		this.innerPanel.add(this.modelos, gBC);

	}

	private boolean todosCamposValidos() {
		return Util.fechaValida(this.fechaInicio.getText()) && Util.fechaValida(this.fechaFin.getText())
				&& this.modelos.getSelectedItem() != null;
	}

	private boolean fechaInicioAnterioFechaFin() {
		return Util.parseFecha(this.fechaInicio.getText()).before(Util.parseFecha(this.fechaFin.getText()));
	}

	// Focus Listener
	@Override
	public void focusGained(FocusEvent fe) {
		if (fe.getSource() == this.fechaInicio) {
			this.oldFechaInicio = this.fechaInicio.getText();
		} else if (fe.getSource() == this.fechaFin) {
			this.oldFechaFin = this.fechaFin.getText();
		}
	}

	@Override
	public void focusLost(FocusEvent fe) {
		if (fe.getSource() == this.fechaInicio) {
			if (!Util.fechaValida(this.fechaInicio.getText())) {
				Util.mostrarError(this, "Fecha Desde inválida");
			} else if (!this.fechaInicio.getText().equals(this.oldFechaInicio)) {
				this.limpiaModelos();
				if (Util.fechaValida(this.fechaFin.getText())) {
					this.disponibilidadButton.setEnabled(true);
				}
			}
		} else if (fe.getSource() == this.fechaFin) {
			if (!Util.fechaValida(this.fechaFin.getText())) {
				Util.mostrarError(this, "Fecha Fin inválida");
			} else if (!this.fechaFin.getText().equals(this.oldFechaFin)) {
				this.limpiaModelos();
				if (Util.fechaValida(this.fechaInicio.getText())) {
					this.disponibilidadButton.setEnabled(true);
				}
			}
		}
	}

	// Action Listener
	@Override
	public void actionPerformed(ActionEvent e) {
		this.modelos.removeAllItems();
		ElementoCombo[] listaModelos = sistema.listarModelosConDisponibilidad(
				Util.parseFecha(this.fechaInicio.getText()), Util.parseFecha(this.fechaFin.getText()));
		this.modelos.setEnabled(true);
		for (ElementoCombo elementoCombo : listaModelos) {
			this.modelos.addItem(elementoCombo);
		}

	}

	public RespuestaGui generarReserva() {
		if (this.todosCamposValidos()) {
			if (this.fechaInicioAnterioFechaFin()) {
				ElementoCombo opcionSeleccionada = (ElementoCombo) this.modelos.getSelectedItem();
				RespuestaTransaccion respuesta = this.sistema.registrarReserva(this.clienteView.getNumeroCliente(),
						Integer.parseInt(opcionSeleccionada.getCodigo()), "",
						Util.parseFecha(this.fechaInicio.getText()), Util.parseFecha(this.fechaFin.getText()), 50f);
				if (respuesta.getTipoRespuesta().equals(RespuestaSistema.OK)) {
					return new RespuestaGui(ErrorGui.OK, respuesta.getMensaje());
				} else {
					return new RespuestaGui(ErrorGui.ERROR_TRANSACCION, respuesta.getTipoRespuesta().getDescripcion());
				}
			} else {
				return new RespuestaGui(ErrorGui.ERROR_RANGO_FECHA);
			}
		} else {
			return new RespuestaGui(ErrorGui.ERROR_VALIDACION);
		}
	}

	public RespuestaGui cancelarReserva(Long numeroReserva) {
		RespuestaTransaccion respuesta = sistema.cancelarReserva(numeroReserva.intValue());
		if (respuesta.getTipoRespuesta().equals(RespuestaSistema.OK)) {
			return new RespuestaGui(ErrorGui.OK);
		} else if (respuesta.getTipoRespuesta().equals(RespuestaSistema.APLICA_MULTA)) {
			return new RespuestaGui(ErrorGui.MUESTRA_MENSAJE, respuesta.getMensaje());
		} else {
			return new RespuestaGui(ErrorGui.ERROR_TRANSACCION, respuesta.getMensaje());
		}
	}

	private void limpiaModelos() {
		this.modelos.removeAllItems();
		this.modelos.setEnabled(false);
	}

	public void cargaReserva(ReservaView reservaView) {

		this.cliente.setText(reservaView.getNombre() + " " + reservaView.getApellido());
		this.dni.setText(reservaView.getDni().toString());
		this.fechaInicio.setText(reservaView.getFechaInicio());
		this.fechaInicio.setEditable(false);
		this.fechaFin.setText(reservaView.getFechaFin());
		this.fechaFin.setEditable(false);
		this.disponibilidadButton.setVisible(false);
		this.modelos.removeAllItems();
		this.modelos.addItem(new ElementoCombo("", reservaView.getMarca() + " - " + reservaView.getModelo()));
		this.modelos.setEnabled(false);

	}

}