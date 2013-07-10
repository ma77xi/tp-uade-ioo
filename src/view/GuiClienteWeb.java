package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import view.swing.login.Login;
import view.swing.reserva.AltaReservaPanel;
import view.swing.reserva.CancelarReservaPanel;
import view.swing.clienteweb.AltaClienteWebPanel;
import view.swing.clienteweb.ModificarClienteWebPanel;
import controller.AlquilerAutos;

public class GuiClienteWeb extends JFrame implements ActionListener, MenuListener {
	
	private static final long serialVersionUID = -6739731088517367469L;

	private AlquilerAutos sistema;
	private String user;

	private JMenuBar menuBar;
	private JMenu menuClienteWeb;
	private JMenuItem itemModificacionClienteWeb;
	private JMenu menuReserva;
	private JMenuItem itemAltaReserva;
	private JMenuItem itemBajaReserva;
	private JMenu menuLogout;
	private JMenu menuSalir;


	public GuiClienteWeb(AlquilerAutos sistema) {
		this.sistema = sistema;
		login();
	}

	public final void login() {
		// Definición del frame
		setTitle("Alquiler Autos");
		setResizable(false);
		setSize(600, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		getContentPane().add(new Login(this, sistema));
	}

	public final void init(String user) {
		this.user = user;
		this.init();
	}

	public final void init() {
		// Definición del frame
		setTitle("Alquiler Autos");
		setResizable(false);
		setSize(600, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Definición del menú
		this.menuBar = new JMenuBar();

		// Menú Clientes - INICIO
		this.menuClienteWeb = new JMenu("Clientes");

		this.itemModificacionClienteWeb = new JMenuItem("Actualizar Datos");
		this.itemModificacionClienteWeb.addActionListener(this);
		this.menuClienteWeb.add(itemModificacionClienteWeb);
		// Menú Clientes - FIN

		// Menú Reservas - INICIO
		this.menuReserva = new JMenu("Reservas");

		this.itemAltaReserva = new JMenuItem("Nueva Reserva");
		this.itemAltaReserva.addActionListener(this);
		this.menuReserva.add(itemAltaReserva);

		this.itemBajaReserva = new JMenuItem("Cancelar Reserva");
		this.itemBajaReserva.addActionListener(this);
		this.menuReserva.add(itemBajaReserva);
		// Menú Reservas - FIN

		// Menú Salir - INICIO
		this.menuLogout = new JMenu("Cerrar sesión");
		// this.menuLogout.addActionListener(this);
		this.menuLogout.addMenuListener(this);
		// Menú Salir - FIN

		// Menú Salir - INICIO
		this.menuSalir = new JMenu("Salir");
		// this.menuSalir.addActionListener(this);
		this.menuSalir.addMenuListener(this);
		// Menú Salir - FIN

		// Se agregan las opciones a la barra de menú.
		setJMenuBar(menuBar);
		menuBar.add(menuClienteWeb);
		menuBar.add(menuReserva);
		menuBar.add(menuLogout);
		menuBar.add(menuSalir);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.itemModificacionClienteWeb) 
		{
			getContentPane().removeAll();
			getContentPane().add(new ModificarClienteWebPanel(this.sistema, this, this.user));
		} 
		else if (event.getSource() == this.itemAltaReserva) 
		{
			getContentPane().removeAll();
			getContentPane().add(new AltaReservaPanel(sistema, this, this.user));
		} 
		else if (event.getSource() == this.itemBajaReserva) 
		{
			getContentPane().removeAll();
			getContentPane().add(new CancelarReservaPanel(this.sistema, this));
		}

		getContentPane().revalidate();
		getContentPane().repaint();
	}

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void menuSelected(MenuEvent e) {
		if (e.getSource() == this.menuSalir) 
		{
			int seleccion = JOptionPane.showConfirmDialog(null, "Está seguro que desea salir?", "Advertencia", JOptionPane.YES_NO_OPTION);
			
			if (seleccion == JOptionPane.YES_OPTION) 
			{
				System.exit(0);
			}
		} 
		else if (e.getSource() == this.menuLogout) 
		{
			int seleccion = JOptionPane.showConfirmDialog(null, "Está seguro que desea cerrar sesión?", "Advertencia", JOptionPane.YES_NO_OPTION);
			
			if (seleccion == JOptionPane.YES_OPTION) 
			{
				this.user = null;
				this.resetLogin();
			}
		}
	}

	public void altaClienteWebReset() {
		getContentPane().removeAll();
		getContentPane().add(new AltaClienteWebPanel(this.sistema, this));
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	public void modificarClienteWebReset() {
		getContentPane().removeAll();
		getContentPane().add(new ModificarClienteWebPanel(this.sistema, this, this.user));
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	public void resetLogin() {
		if (this.menuBar != null) 
		{
			int cantidadMenu = this.menuBar.getMenuCount();
		
			for (int i = 0; i < cantidadMenu; i++)
			{
				this.menuBar.remove(0);
			}
		}

		getContentPane().removeAll();
		this.login();
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	public void reset(String user) {
		getContentPane().removeAll();
		this.init(user);
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	public void reset() {
		getContentPane().removeAll();
		this.init();
		getContentPane().revalidate();
		getContentPane().repaint();
	}
}