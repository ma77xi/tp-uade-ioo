package view.swing.reserva;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
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
import controller.AlquilerAutos;

public class DatosReservaPanel extends JPanel implements FocusListener {

	private static final long serialVersionUID = -2183603889250268077L;

	private AlquilerAutos sistema;
	private ClienteView clienteView;

	private JPanel innerPanel;

	private JTextField cliente;
	private JTextField dni;
	private JTextField fechaInicio;
	private JTextField fechaFin;
	private JComboBox<ElementoCombo> modelos;
	private JComboBox<ElementoCombo> autos;

	private JLabel clienteLabel;
	private JLabel dniLabel;
	private JLabel fechaInicioLabel;
	private JLabel fechaFinLabel;
	private JLabel modelosLabel;
	private JLabel autosLabel;

	public DatosReservaPanel(AlquilerAutos sistema, ClienteView clienteView) {
		this.sistema = sistema;
		this.clienteView = clienteView;
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
		this.cliente.setEnabled(false);
		this.cliente.setText(this.clienteView.getNombre() + " " + this.clienteView.getApellido());
		this.innerPanel.add(this.cliente, gBC);

		gBC.gridx = 2;
		this.dniLabel = new JLabel("DNI:");
		this.innerPanel.add(this.dniLabel, gBC);

		gBC.gridx = 3;
		this.dni = new JTextField(15);
		this.dni.setEnabled(false);
		this.dni.setText(this.clienteView.getDni().toString());
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
		this.modelosLabel = new JLabel("Modelos:");
		this.innerPanel.add(this.modelosLabel, gBC);

		gBC.gridx = 1;
		this.modelos = new JComboBox<ElementoCombo>(sistema.listaModelosConAutos());
		this.innerPanel.add(this.modelos, gBC);

	}

	private boolean todosCamposValidos() {
		return Util.fechaValida(this.fechaInicio.getText()) && Util.fechaValida(this.fechaFin.getText());
	}

	@Override
	public void focusGained(FocusEvent fe) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent fe) {
		if (fe.getSource() == this.fechaInicio) {
			if (!Util.fechaValida(this.fechaInicio.getText())) {
				Util.mostrarError(this, "Fecha Desde inválida");
			}
		} else if (fe.getSource() == this.fechaFin) {
			if (!Util.fechaValida(this.fechaFin.getText())) {
				Util.mostrarError(this, "Fecha Fin inválida");
			}
		}

	}

	// public void enableAllComponents(boolean enable) {
	// for (Component component : this.innerPanel.getComponents()) {
	// if (component == this.sexoPanel) {
	// for (Component innerComponent : this.sexoPanel.getComponents()) {
	// innerComponent.setEnabled(enable);
	// }
	// } else {
	// component.setEnabled(enable);
	// }
	// }
	// }

	public RespuestaGui generarReserva() {
		if (this.todosCamposValidos()) {
			ElementoCombo opcionSeleccionada = (ElementoCombo) this.modelos.getSelectedItem();
			RespuestaTransaccion respuesta = this.sistema.registrarReserva(this.clienteView.getNumeroCliente(),
					Integer.parseInt(opcionSeleccionada.getCodigo()), "", Util.parseFecha(this.fechaInicio.getText()),
					Util.parseFecha(this.fechaFin.getText()), 50f);
			if (respuesta.getTipoRespuesta().equals(RespuestaSistema.OK)) {
				return new RespuestaGui(ErrorGui.OK, respuesta.getMensaje());
			} else {
				return new RespuestaGui(ErrorGui.ERROR_TRANSACCION, respuesta.getTipoRespuesta().getDescripcion());
			}
		} else {
			return new RespuestaGui(ErrorGui.ERROR_VALIDACION);
		}
	}

	public RespuestaGui cancelarReserva(Long numeroReserva) {
		// if (this.todosCamposValidos()) {
		// String sexoSeleccionado = (this.sexoM.isSelected()) ?
		// this.sexoM.getText() : this.sexoF.getText();
		// RespuestaTransaccion respuesta =
		// sistema.modificarCliente(numeroCliente, this.nombre.getText(),
		// this.apellido.getText(),
		// Util.parseFecha(this.fechaNacimiento.getText()), domicilio.getText(),
		// telefono.getText(), Long.parseLong(dni.getText()), sexoSeleccionado,
		// nacionalidad.getText());
		// if (respuesta.getTipoRespuesta().equals(RespuestaSistema.OK)) {
		// return new RespuestaGui(ErrorGui.OK, respuesta.getMensaje());
		// } else {
		// return new RespuestaGui(ErrorGui.ERROR_TRANSACCION,
		// respuesta.getTipoRespuesta().getDescripcion());
		// }
		// } else {
		// return new RespuestaGui(ErrorGui.ERROR_VALIDACION);
		// }
		return null;
	}

}