package view.swing.auto;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.ElementoCombo;
import util.ErrorGui;
import util.RespuestaSistema;
import util.RespuestaGui;
import util.RespuestaTransaccion;
import util.Util;
import view.vistas.ModeloView;
import controller.AlquilerAutos;

public class DatosModeloPanel extends JPanel {

	private static final long serialVersionUID = 8013386807279882666L;

	private AlquilerAutos sistema;

	private JPanel innerPanel;

	private JTextField motor;
	private JTextField seguridad;
	private JTextField marca;
	private JTextField modelo;
	private JComboBox<ElementoCombo> tipo;
	private JTextField costoDia;
	private JTextField excedente;

	private JLabel motorLabel;
	private JLabel seguridadLabel;
	private JLabel marcaLabel;
	private JLabel modeloLabel;
	private JLabel tipoLabel;
	private JLabel costoDiaLabel;
	private JLabel excedenteLabel;

	public DatosModeloPanel(AlquilerAutos sistema) {
		this.sistema = sistema;
		this.init();
	}

	private void init() {

		this.setLayout(new GridBagLayout());
		GridBagConstraints gBC = new GridBagConstraints();

		this.innerPanel = new JPanel(new GridBagLayout());
		this.innerPanel.setBorder(BorderFactory.createTitledBorder("Datos del Modelo"));
		gBC.insets = new Insets(5, 5, 5, 5);
		gBC.gridwidth = 6;
		this.add(innerPanel, gBC);

		int y = 0;

		gBC.anchor = GridBagConstraints.NORTHEAST;
		gBC.insets = new Insets(2, 2, 2, 2);
		gBC.gridy = y;

		gBC.gridx = 0;
		gBC.gridwidth = 1;
		this.motorLabel = new JLabel("Datos Motor:");
		innerPanel.add(this.motorLabel, gBC);

		gBC.gridx = 1;
		gBC.gridwidth = 5;
		gBC.fill = GridBagConstraints.HORIZONTAL;
		this.motor = new JTextField();
		innerPanel.add(this.motor, gBC);

		y++;

		gBC.gridy = y;

		gBC.gridx = 0;
		gBC.gridwidth = 1;
		gBC.fill = GridBagConstraints.NONE;
		this.seguridadLabel = new JLabel("Datos Seguridad:");
		innerPanel.add(this.seguridadLabel, gBC);

		gBC.gridx = 1;
		gBC.gridwidth = 5;
		gBC.fill = GridBagConstraints.HORIZONTAL;
		this.seguridad = new JTextField();
		innerPanel.add(this.seguridad, gBC);

		y++;

		gBC.gridy = y;

		gBC.gridx = 0;
		gBC.gridwidth = 1;
		gBC.fill = GridBagConstraints.NONE;
		this.marcaLabel = new JLabel("Marca:");
		innerPanel.add(this.marcaLabel, gBC);

		gBC.gridx = 1;
		this.marca = new JTextField(10);
		innerPanel.add(this.marca, gBC);

		gBC.gridx = 2;
		this.modeloLabel = new JLabel("Modelo:");
		innerPanel.add(this.modeloLabel, gBC);

		gBC.gridx = 3;
		this.modelo = new JTextField(10);
		innerPanel.add(this.modelo, gBC);

		gBC.gridx = 4;
		this.tipoLabel = new JLabel("Tipo:");
		innerPanel.add(this.tipoLabel, gBC);

		gBC.gridx = 5;
		this.tipo = new JComboBox<ElementoCombo>(sistema.listaTiposModelo());
		innerPanel.add(this.tipo, gBC);

		y++;

		// Fila 3
		gBC.gridy = y;

		gBC.gridx = 0;
		this.costoDiaLabel = new JLabel("Costo Diario:");
		innerPanel.add(this.costoDiaLabel, gBC);

		gBC.gridx = 1;
		this.costoDia = new JTextField(10);
		innerPanel.add(this.costoDia, gBC);

		gBC.gridx = 2;
		this.excedenteLabel = new JLabel("Excedente Km:");
		innerPanel.add(this.excedenteLabel, gBC);

		gBC.gridx = 3;
		this.excedente = new JTextField(10);
		innerPanel.add(this.excedente, gBC);

	}

	public RespuestaGui altaModelo() {
		if (this.todosCamposValidos()) {
			ElementoCombo opcionSeleccionada = (ElementoCombo) this.tipo.getSelectedItem();
			RespuestaTransaccion respuesta = sistema.altaModelo(this.motor.getText(), this.seguridad.getText(),
					this.marca.getText(), this.modelo.getText(), opcionSeleccionada.getCodigo(),
					Float.parseFloat(this.costoDia.getText()), Float.parseFloat(this.excedente.getText()));
			if (respuesta.getTipoRespuesta().equals(RespuestaSistema.OK)) {
				return new RespuestaGui(ErrorGui.OK, respuesta.getMensaje());
			} else {
				return new RespuestaGui(ErrorGui.ERROR_TRANSACCION, respuesta.getTipoRespuesta().getDescripcion());
			}
		} else {
			return new RespuestaGui(ErrorGui.ERROR_VALIDACION, ErrorGui.ERROR_VALIDACION.getDescripcion());
		}
	}

	public RespuestaGui modificarModelo(Long numeroModelo) {
		if (this.todosCamposValidos()) {
			ElementoCombo opcionSeleccionada = (ElementoCombo) this.tipo.getSelectedItem();
			RespuestaTransaccion respuesta = sistema.modificarModelo(numeroModelo.intValue(), this.motor.getText(),
					this.seguridad.getText(), this.marca.getText(), this.modelo.getText(),
					opcionSeleccionada.getCodigo(), Float.parseFloat(this.costoDia.getText()),
					Float.parseFloat(this.excedente.getText()));
			if (respuesta.getTipoRespuesta().equals(RespuestaSistema.OK)) {
				return new RespuestaGui(ErrorGui.OK, respuesta.getMensaje());
			} else {
				return new RespuestaGui(ErrorGui.ERROR_TRANSACCION, respuesta.getTipoRespuesta().getDescripcion());
			}
		} else {
			return new RespuestaGui(ErrorGui.ERROR_VALIDACION, ErrorGui.ERROR_VALIDACION.getDescripcion());
		}
	}

	private boolean todosCamposValidos() {
		return (this.motor.getText() != "") && (this.seguridad.getText() != "") && (this.marca.getText() != "")
				&& (this.modelo.getText() != "")
				&& (Util.decimalValido(this.costoDia.getText()) && (Util.decimalValido(this.excedente.getText())));
	}

	public void enableAllComponents(boolean enable) {
		for (Component component : this.innerPanel.getComponents()) {
			component.setEnabled(enable);

		}
	}

	public void cargaModelo(ModeloView modeloView) {
		this.motor.setText(modeloView.getDatosMotor());
		this.seguridad.setText(modeloView.getDatosSeguridad());
		this.marca.setText(modeloView.getMarca());
		this.modelo.setText(modeloView.getModelo());

		for (int i = 0; i < this.tipo.getItemCount(); i++) {
			if (((ElementoCombo) this.tipo.getItemAt(i)).getCodigo().equals(modeloView.getTipo())) {
				this.tipo.setSelectedIndex(i);
			}
		}

		this.tipo.setSelectedItem(modeloView.getTipo());
		this.costoDia.setText(String.valueOf(modeloView.getCostoDia()));
		this.excedente.setText(String.valueOf(modeloView.getCostoKmExcedente()));
	}

}