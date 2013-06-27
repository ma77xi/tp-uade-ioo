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
import view.vistas.AutoView;
import controller.AlquilerAutos;

public class DatosAutoPanel extends JPanel {

	private static final long serialVersionUID = -2857459072910291205L;

	private AlquilerAutos sistema;

	private JPanel innerPanel;

	private JComboBox<ElementoCombo> modelo;
	private JTextField patente;
	private JTextField anio;
	private JTextField kilometraje;
	private JTextField combustible;

	private JLabel modeloLabel;
	private JLabel patenteLabel;
	private JLabel anioLabel;
	private JLabel kilometrajeLabel;
	private JLabel combustibleLabel;

	private String patenteAnterior;

	public DatosAutoPanel(AlquilerAutos sistema) {
		this.sistema = sistema;
		this.init();
	}

	private void init() {

		this.setLayout(new GridBagLayout());
		GridBagConstraints gBC = new GridBagConstraints();

		this.innerPanel = new JPanel(new GridBagLayout());
		this.innerPanel.setBorder(BorderFactory
				.createTitledBorder("Datos del Auto"));
		gBC.insets = new Insets(5, 5, 5, 5);
		gBC.gridwidth = 6;
		this.add(innerPanel, gBC);

		int y = 0;

		gBC.anchor = GridBagConstraints.NORTHEAST;
		gBC.insets = new Insets(2, 2, 2, 2);
		gBC.gridy = y;

		gBC.gridx = 0;
		gBC.gridwidth = 1;
		this.modeloLabel = new JLabel("Modelo:");
		innerPanel.add(this.modeloLabel, gBC);

		gBC.gridx = 1;
		this.modelo = new JComboBox<ElementoCombo>(sistema.listaModelos());
		innerPanel.add(this.modelo, gBC);

		y++;

		gBC.gridy = y;

		gBC.gridx = 0;
		this.patenteLabel = new JLabel("Patente:");
		innerPanel.add(this.patenteLabel, gBC);

		gBC.gridx = 1;
		this.patente = new JTextField(10);
		innerPanel.add(this.patente, gBC);

		gBC.gridx = 2;
		this.anioLabel = new JLabel("Año:");
		innerPanel.add(this.anioLabel, gBC);

		gBC.gridx = 3;
		this.anio = new JTextField(10);
		innerPanel.add(this.anio, gBC);

		y++;

		gBC.gridy = y;

		gBC.gridx = 0;
		this.kilometrajeLabel = new JLabel("Kilometraje:");
		innerPanel.add(this.kilometrajeLabel, gBC);

		gBC.gridx = 1;
		this.kilometraje = new JTextField(10);
		innerPanel.add(this.kilometraje, gBC);

		gBC.gridx = 2;
		this.combustibleLabel = new JLabel("Combustible:");
		innerPanel.add(this.combustibleLabel, gBC);

		gBC.gridx = 3;
		this.combustible = new JTextField(10);
		innerPanel.add(this.combustible, gBC);

	}

	public RespuestaGui altaAuto() {
		if (this.todosCamposValidos()) {
			ElementoCombo opcionSeleccionada = (ElementoCombo) this.modelo
					.getSelectedItem();
			RespuestaTransaccion respuesta = sistema.altaAutomovil(Integer
					.parseInt(opcionSeleccionada.getCodigo()), this.patente
					.getText(), Integer.parseInt(this.anio.getText()), Long
					.parseLong(this.kilometraje.getText()), Float
					.parseFloat(this.combustible.getText()));
			if (respuesta.getTipoRespuesta().equals(RespuestaSistema.OK)) {
				return new RespuestaGui(ErrorGui.OK, respuesta.getMensaje());
			} else {
				return new RespuestaGui(ErrorGui.ERROR_TRANSACCION, respuesta
						.getTipoRespuesta().getDescripcion());
			}
		} else {
			return new RespuestaGui(ErrorGui.ERROR_VALIDACION,
					ErrorGui.ERROR_VALIDACION.getDescripcion());
		}
	}

	public RespuestaGui modificarAuto() {
		if (this.todosCamposValidos()) {
			ElementoCombo opcionSeleccionada = (ElementoCombo) this.modelo
					.getSelectedItem();
			RespuestaTransaccion respuesta = sistema.modificarAutomovil(Integer
					.parseInt(opcionSeleccionada.getCodigo()),
					this.patenteAnterior,
					Integer.parseInt(this.anio.getText()), Long
							.parseLong(this.kilometraje.getText()), Float
							.parseFloat(this.combustible.getText()),
					this.patente.getText());
			if (respuesta.getTipoRespuesta().equals(RespuestaSistema.OK)) {
				return new RespuestaGui(ErrorGui.OK, respuesta.getMensaje());
			} else {
				return new RespuestaGui(ErrorGui.ERROR_TRANSACCION, respuesta
						.getTipoRespuesta().getDescripcion());
			}
		} else {
			return new RespuestaGui(ErrorGui.ERROR_VALIDACION,
					ErrorGui.ERROR_VALIDACION.getDescripcion());
		}
	}

	private boolean todosCamposValidos() {
		return (this.modelo.getSelectedItem() != null && this.patente.getText() != "")
				&& (Util.numeroValido(anio.getText()))
				&& (Util.numeroValido(this.kilometraje.getText()) && (Util
						.decimalValido(this.combustible.getText())));
	}

	public void enableAllComponents(boolean enable) {
		for (Component component : this.innerPanel.getComponents()) {
			component.setEnabled(enable);

		}
	}

	public void cargaAuto(AutoView autoView) {

		for (int i = 0; i < this.modelo.getItemCount(); i++) {
			if (((ElementoCombo) this.modelo.getItemAt(i)).getCodigo().equals(
					autoView.getModelo())) {
				this.modelo.setSelectedIndex(i);
			}
		}

		this.patente.setText(autoView.getPatente());
		this.anio.setText(autoView.getAnio());
		this.kilometraje.setText(String.valueOf(autoView.getKilometraje()));
		this.combustible.setText(String.valueOf(autoView.getCombustible()));

		this.patenteAnterior = autoView.getPatente();

	}

}