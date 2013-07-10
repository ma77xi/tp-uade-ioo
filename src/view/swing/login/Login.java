package view.swing.login;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import util.ErrorGui;
import util.RespuestaSistema;
import util.RespuestaTransaccion;
import util.Util;
import view.GuiClienteWeb;
import controller.AlquilerAutos;

public class Login extends JPanel implements ActionListener {

	private static final long serialVersionUID = 3242115935368098899L;
	
	private GuiClienteWeb gui;
	private AlquilerAutos sistema;

	private JPanel innerPanel;

	private JLabel userLabel;
	private JTextField user;
	private JLabel passwordLabel;
	private JPasswordField password;
	private JButton loginButton;
	private JButton nuevoButton;

	public Login(GuiClienteWeb gui, AlquilerAutos sistema) {
		this.gui = gui;
		this.sistema = sistema;
		this.init();
	}

	private void init() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gBC = new GridBagConstraints();

		this.innerPanel = new JPanel(new GridBagLayout());
		this.innerPanel.setBorder(BorderFactory.createTitledBorder("Login"));
		gBC.insets = new Insets(5, 5, 5, 5);
		gBC.gridwidth = 2;
		this.add(this.innerPanel, gBC);

		int y = 0;

		gBC.anchor = GridBagConstraints.NORTHEAST;
		gBC.insets = new Insets(2, 2, 2, 2);
		gBC.gridy = y;

		gBC.gridx = 0;
		gBC.gridwidth = 1;
		this.userLabel = new JLabel("Usuario:");
		this.innerPanel.add(this.userLabel, gBC);

		gBC.gridx = 1;
		this.user = new JTextField(10);
		this.innerPanel.add(this.user, gBC);

		y++;

		gBC.gridy = y;

		gBC.gridx = 0;
		this.passwordLabel = new JLabel("Contraseña:");
		this.innerPanel.add(this.passwordLabel, gBC);

		gBC.gridx = 1;
		this.password = new JPasswordField(10);
		innerPanel.add(this.password, gBC);

		y = 1;

		gBC.gridy = y;
		gBC.gridx = 0;
		this.loginButton = new JButton("Iniciar Sesión");
		this.loginButton.addActionListener(this);
		this.add(this.loginButton, gBC);

		gBC.gridy = y;
		gBC.gridx = 1;
		this.nuevoButton = new JButton("Soy nuevo");
		this.nuevoButton.addActionListener(this);
		this.add(this.nuevoButton, gBC);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.loginButton) 
		{
			if (todosCamposValidos()) 
			{
				RespuestaTransaccion respuesta = this.sistema.login(this.user.getText(), new String(this.password.getPassword()));
				
				if (respuesta.getTipoRespuesta().equals(RespuestaSistema.OK)) 
				{
					this.gui.reset(this.user.getText());
				} 
				else 
				{
					Util.mostrarError(this, respuesta.getTipoRespuesta().getDescripcion());
				}
			} 
			else 
			{
				Util.mostrarError(this, ErrorGui.ERROR_VALIDACION.getDescripcion());
			}
		} 
		else if (e.getSource() == this.nuevoButton) 
		{
			gui.altaClienteWebReset();
		}
	}

	private boolean todosCamposValidos() {
		return this.user.getText() != "" && this.password.getPassword() != null;
	}
}