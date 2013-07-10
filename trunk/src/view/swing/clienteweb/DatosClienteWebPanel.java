package view.swing.clienteweb;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import util.ErrorGui;
import util.RespuestaSistema;
import util.RespuestaGui;
import util.RespuestaTransaccion;
import util.Util;
import view.vistas.ClienteView;
import controller.AlquilerAutos;

public class DatosClienteWebPanel extends JPanel implements FocusListener {

	private static final long serialVersionUID = 3525819765915085124L;

	private AlquilerAutos sistema;

	private JPanel innerPanel;

	private JTextField nombre;
	private JTextField apellido;
	private JTextField fechaNacimiento;
	private JTextField domicilio;
	private JFormattedTextField telefono;
	private JTextField dni;
	private JPanel sexoPanel;
	private JRadioButton sexoM;
	private JRadioButton sexoF;
	private ButtonGroup sexo;
	private JTextField nacionalidad;
	private JTextField user;
	private JPasswordField password;
	private JPasswordField passwordConfirmation;

	private JLabel nombreLabel;
	private JLabel apellidoLabel;
	private JLabel fechaNacimientoLabel;
	private JLabel domicilioLabel;
	private JLabel telefonoLabel;
	private JLabel dniLabel;
	private JLabel sexoLabel;
	private JLabel nacionalidadLabel;
	private JLabel userLabel;
	private JLabel passwordLabel;
	private JLabel passwordConfirmationLabel;

	public DatosClienteWebPanel(AlquilerAutos sistema) {
		this.sistema = sistema;
		this.init();
	}

	private void init() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gBC = new GridBagConstraints();

		this.innerPanel = new JPanel(new GridBagLayout());
		this.innerPanel.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));
		gBC.insets = new Insets(5, 5, 5, 5);
		gBC.gridwidth = 4;
		this.add(innerPanel, gBC);

		int y = 0;

		// Fila 0
		gBC.anchor = GridBagConstraints.NORTHEAST;
		gBC.insets = new Insets(2, 2, 2, 2);
		gBC.gridy = y;

		gBC.gridx = 0;
		gBC.gridwidth = 1;
		this.nombreLabel = new JLabel("Nombre:");
		innerPanel.add(nombreLabel, gBC);

		gBC.gridx = 1;
		this.nombre = new JTextField(15);
		innerPanel.add(nombre, gBC);

		gBC.gridx = 2;
		this.apellidoLabel = new JLabel("Apellido:");
		innerPanel.add(apellidoLabel, gBC);

		gBC.gridx = 3;
		this.apellido = new JTextField(15);
		innerPanel.add(apellido, gBC);

		y++;

		// Fila 1
		gBC.gridy = y;

		gBC.gridx = 0;
		this.domicilioLabel = new JLabel("Domicilio:");
		innerPanel.add(domicilioLabel, gBC);

		gBC.gridx = 1;
		gBC.gridwidth = 3;
		gBC.fill = GridBagConstraints.HORIZONTAL;
		this.domicilio = new JTextField(15);
		innerPanel.add(domicilio, gBC);

		y++;

		// Fila 2
		gBC.gridy = y;

		gBC.gridx = 0;
		gBC.gridwidth = 1;
		gBC.fill = GridBagConstraints.NONE;
		this.fechaNacimientoLabel = new JLabel("Fecha de Nacimiento:");
		innerPanel.add(fechaNacimientoLabel, gBC);

		gBC.gridx = 1;
		gBC.anchor = GridBagConstraints.NORTHWEST;
		MaskFormatter fechaFormatter;
		try {
			fechaFormatter = new MaskFormatter("##/##/####");
			fechaFormatter.setPlaceholderCharacter('_');
			this.fechaNacimiento = new JFormattedTextField(fechaFormatter);
			this.fechaNacimiento.addFocusListener(this);
		} catch (ParseException e) {
			this.fechaNacimiento = new JTextField(8);
		}
		innerPanel.add(fechaNacimiento, gBC);

		gBC.gridx = 2;
		gBC.anchor = GridBagConstraints.NORTHEAST;
		this.telefonoLabel = new JLabel("Telefono:");
		innerPanel.add(telefonoLabel, gBC);

		gBC.gridx = 3;
		MaskFormatter telefonoFormatter;
		try {
			telefonoFormatter = new MaskFormatter("(###) ####-####");
			telefonoFormatter.setPlaceholderCharacter('_');
			this.telefono = new JFormattedTextField(telefonoFormatter);
			this.telefono.setColumns(15);
		} catch (ParseException e) {
			this.telefono = new JFormattedTextField(15);
		}
		innerPanel.add(telefono, gBC);

		y++;

		// Fila 3
		gBC.gridy = y;

		gBC.gridx = 0;
		this.dniLabel = new JLabel("DNI:");
		innerPanel.add(dniLabel, gBC);

		gBC.gridx = 1;
		gBC.anchor = GridBagConstraints.NORTHWEST;
		this.dni = new JTextField(8);
		innerPanel.add(dni, gBC);

		gBC.gridx = 2;
		gBC.anchor = GridBagConstraints.NORTHEAST;
		this.sexoLabel = new JLabel("Sexo:");
		innerPanel.add(sexoLabel, gBC);

		gBC.gridx = 3;
		gBC.anchor = GridBagConstraints.NORTHWEST;
		this.sexoPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gBC2 = new GridBagConstraints();

		gBC2.gridy = 0;
		gBC2.gridx = 0;
		this.sexoM = new JRadioButton("M");
		this.sexoM.setMnemonic(KeyEvent.VK_M);
		this.sexoM.setSelected(true);
		sexoPanel.add(this.sexoM, gBC2);

		gBC2.gridx = 1;
		this.sexoF = new JRadioButton("F");
		this.sexoF.setMnemonic(KeyEvent.VK_F);
		sexoPanel.add(this.sexoF, gBC2);

		this.sexo = new ButtonGroup();
		sexo.add(this.sexoM);
		sexo.add(this.sexoF);

		innerPanel.add(sexoPanel, gBC);

		y++;

		// Fila 4
		gBC.gridy = y;

		gBC.gridx = 0;
		gBC.anchor = GridBagConstraints.NORTHEAST;
		this.nacionalidadLabel = new JLabel("Nacionalidad:");
		innerPanel.add(nacionalidadLabel, gBC);

		gBC.gridx = 1;
		this.nacionalidad = new JTextField(15);
		innerPanel.add(nacionalidad, gBC);

		y++;

		// Fila 5
		gBC.gridy = y;

		gBC.gridx = 0;
		gBC.gridwidth = 1;
		this.userLabel = new JLabel("Usuario:");
		this.innerPanel.add(this.userLabel, gBC);

		gBC.gridx = 1;
		this.user = new JTextField(15);
		this.innerPanel.add(this.user, gBC);

		gBC.gridx = 2;
		this.passwordLabel = new JLabel("Contraseña:");
		this.innerPanel.add(this.passwordLabel, gBC);

		gBC.gridx = 3;
		this.password = new JPasswordField(15);
		this.innerPanel.add(this.password, gBC);

		y++;

		// Fila 6
		gBC.gridy = y;

		gBC.gridx = 1;
		gBC.gridwidth = 2;
		this.passwordConfirmationLabel = new JLabel("Reescriba Contraseña:");
		this.innerPanel.add(this.passwordConfirmationLabel, gBC);

		gBC.gridx = 3;
		gBC.gridwidth = 1;
		this.passwordConfirmation = new JPasswordField(15);
		this.innerPanel.add(this.passwordConfirmation, gBC);
	}

	private boolean todosCamposValidos() {
		return 
				this.nombre.getText() != "" && 
				this.apellido.getText() != "" && 
				Util.fechaValida(this.fechaNacimiento.getText()) && 
				this.domicilio.getText() != "" && 
				null != this.telefono.getValue() && 
				Util.numeroValido(this.dni.getText()) && 
				this.nacionalidad.getText() != "" && 
				this.user.getText() != "" && 
				this.password.getPassword() != null && 
				this.passwordConfirmation.getPassword() != null;
	}

	@Override
	public void focusGained(FocusEvent fe) {
		// TODO Auto-generated method stub
	}

	@Override
	public void focusLost(FocusEvent fe) {
		if (!Util.fechaValida(this.fechaNacimiento.getText())) 
		{
			Util.mostrarError(this, "Fecha inválida");
		}
	}

	public void enableAllComponents(boolean enable) {
		for (Component component : this.innerPanel.getComponents()) 
		{
			if (component == this.sexoPanel) 
			{
				for (Component innerComponent : this.sexoPanel.getComponents()) 
				{
					innerComponent.setEnabled(enable);
				}
			} 
			else 
			{
				component.setEnabled(enable);
			}
		}
	}

	public void cargaCliente(ClienteView clienteView) {
		this.nombre.setText(clienteView.getNombre());
		this.apellido.setText(clienteView.getApellido());
		this.domicilio.setText(clienteView.getDomicilio());
		this.fechaNacimiento.setText(clienteView.getFechaNacimiento());
		this.telefono.setValue(clienteView.getTelefono());
		this.dni.setText(clienteView.getDni().toString());
		if (clienteView.isMasculino()) 
		{
			this.sexoM.setSelected(true);
		} 
		else 
		{
			this.sexoF.setSelected(true);
		}
		this.nacionalidad.setText(clienteView.getNacionalidad());
	}

	public RespuestaGui altaCliente() {
		if (this.todosCamposValidos()) 
		{
			String password = new String(this.password.getPassword());
			String confrimacionPassword = new String(this.passwordConfirmation.getPassword());
			
			if (password.equals(confrimacionPassword)) 
			{
				String sexoSeleccionado = (this.sexoM.isSelected()) ? this.sexoM.getText() : this.sexoF.getText();
				RespuestaTransaccion respuesta = 
						sistema.altaClienteWeb(
								this.nombre.getText(), 
								this.apellido.getText(),
								Util.parseFecha(this.fechaNacimiento.getText()), 
								this.domicilio.getText(),
								this.telefono.getText(),
								Long.parseLong(dni.getText()), 
								sexoSeleccionado,
								this.nacionalidad.getText(), 
								this.user.getText(),
								password);
				if (respuesta.getTipoRespuesta().equals(RespuestaSistema.OK)) 
				{
					return new RespuestaGui(ErrorGui.OK, respuesta.getMensaje());
				} 
				else 
				{
					return new RespuestaGui(ErrorGui.ERROR_TRANSACCION, respuesta.getTipoRespuesta().getDescripcion());
				}
			} 
			else 
			{
				return new RespuestaGui(ErrorGui.ERROR_PASSWORD, ErrorGui.ERROR_PASSWORD.getDescripcion());
			}
		} 
		else 
		{
			return new RespuestaGui(ErrorGui.ERROR_VALIDACION, ErrorGui.ERROR_VALIDACION.getDescripcion());
		}
	}

	public RespuestaGui modificarCliente() {
		if (this.todosCamposValidos()) 
		{
			String sexoSeleccionado = (this.sexoM.isSelected()) ? this.sexoM.getText() : this.sexoF.getText();
			
			RespuestaTransaccion respuesta = 
					sistema.modificarClienteWeb(
							this.nombre.getText(),
							this.apellido.getText(), 
							Util.parseFecha(this.fechaNacimiento.getText()), 
							this.domicilio.getText(),
							this.telefono.getText(),
							Long.parseLong(dni.getText()), 
							sexoSeleccionado,
							this.nacionalidad.getText(),
							this.user.getText(),
							this.password.toString());
			if (respuesta.getTipoRespuesta().equals(RespuestaSistema.OK))
			{
				return new RespuestaGui(ErrorGui.OK, respuesta.getMensaje());
			} 
			else 
			{
				return new RespuestaGui(ErrorGui.ERROR_TRANSACCION, respuesta.getTipoRespuesta().getDescripcion());
			}
		} 
		else 
		{
			return new RespuestaGui(ErrorGui.ERROR_VALIDACION, ErrorGui.ERROR_VALIDACION.getDescripcion());
		}
	}
}