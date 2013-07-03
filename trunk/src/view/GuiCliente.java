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

import controller.AlquilerAutos;

public class GuiCliente extends JFrame implements ActionListener, MenuListener {

	private static final long serialVersionUID = -6739731088517367469L;

	private AlquilerAutos sistema;

	private JMenuBar menuBar;
	private JMenu menuCliente;
	private JMenuItem itemAltaCliente;
	private JMenuItem itemModificacionCliente;
	private JMenu menuReserva;
	private JMenuItem itemAltaReserva;
	private JMenuItem itemBajaReserva;
	private JMenu menuSalir;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GuiCliente g = new GuiCliente();
				g.setVisible(true);
			}
		});
	}

	public GuiCliente() {
		sistema = new AlquilerAutos();
		init();
	}

	public final void init() {

		// Definición del frame
		setTitle("Alquiler Autos");
		setResizable(false);
		setSize(600, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Definición del menú
		this.menuBar = new JMenuBar();

		// Menú Clientes - INICIO
		this.menuCliente = new JMenu("Clientes");

		this.itemAltaCliente = new JMenuItem("Alta Cliente");
		this.itemAltaCliente.addActionListener(this);
		this.menuCliente.add(itemAltaCliente);

		this.itemModificacionCliente = new JMenuItem("Modificación Cliente");
		this.itemModificacionCliente.addActionListener(this);
		this.menuCliente.add(itemModificacionCliente);
		// Menú Clientes - FIN

		// Menú Reservas - INICIO
		this.menuReserva = new JMenu("Reservas");

		this.itemAltaReserva = new JMenuItem("Nueva");
		this.itemAltaReserva.addActionListener(this);
		this.menuReserva.add(itemAltaReserva);
		
		this.itemBajaReserva = new JMenuItem("Cancelar");
		this.itemBajaReserva.addActionListener(this);
		this.menuReserva.add(itemBajaReserva);
		// Menú Reservas - FIN

		// Menú Salir - INICIO
		this.menuSalir = new JMenu("Salir");
		this.menuSalir.addActionListener(this);
		this.menuSalir.addMenuListener(this);
		// Menú Salir - FIN

		// Se agregan las opciones a la barra de menú.
		setJMenuBar(menuBar);
		menuBar.add(menuCliente);
		menuBar.add(menuReserva);
		menuBar.add(menuSalir);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == this.itemAltaCliente) {
			getContentPane().removeAll();
//			getContentPane().add(new AltaClientePanel(this.sistema, this));
		} else if (event.getSource() == this.itemModificacionCliente) {
			getContentPane().removeAll();
//			getContentPane().add(new ModificarClientePanel(this.sistema, this));
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
		int seleccion = JOptionPane.showConfirmDialog(null, "Está seguro que desea salir?", "Advertencia",
				JOptionPane.YES_NO_OPTION);
		if (seleccion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}

	}

	public void altaClienteReset() {
		getContentPane().removeAll();
//		getContentPane().add(new AltaModeloPanel(this.sistema, this));
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	public void modificarClienteReset() {
		getContentPane().removeAll();
//		getContentPane().add(new ModificarClientePanel(this.sistema, this));
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	public void reset() {
		getContentPane().removeAll();
		getContentPane().revalidate();
		getContentPane().repaint();
	}

}
