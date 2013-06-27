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

import view.swing.auto.AltaAutoPanel;
import view.swing.auto.AltaModeloPanel;
import view.swing.auto.ModificarAutoPanel;
import view.swing.auto.ModificarModeloPanel;
import view.swing.cliente.AltaClientePanel;
import view.swing.cliente.ModificarClientePanel;
import controller.AlquilerAutos;

public class Gui extends JFrame implements ActionListener, MenuListener {

	private static final long serialVersionUID = -6739731088517367469L;

	public static final int RESULTADO_OK = 1;
	public static final int ERROR_VALIDACION = -1;

	private AlquilerAutos sistema;

	private JMenuBar menuBar;
	private JMenu menuCliente;
	private JMenuItem itemAltaCliente;
	private JMenuItem itemBajaCliente;
	private JMenuItem itemModificacionCliente;
	private JMenu menuAuto;
	private JMenuItem itemAltaModelo;
	private JMenuItem itemModificarModelo;
	private JMenuItem itemAltaAuto;
	private JMenuItem itemModificarAuto;
	private JMenu menuReserva;
	private JMenuItem itemAltaReserva;
	private JMenu menuAlquiler;
	private JMenuItem itemAltaAlquiler;
	private JMenuItem itemDevolucion;
	private JMenu menuSalir;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Gui g = new Gui();
				g.setVisible(true);
			}
		});
	}

	public Gui() {
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

		this.itemBajaCliente = new JMenuItem("Baja Cliente");
		this.itemBajaCliente.addActionListener(this);
		this.menuCliente.add(itemBajaCliente);

		this.itemModificacionCliente = new JMenuItem("Modificación Cliente");
		this.itemModificacionCliente.addActionListener(this);
		this.menuCliente.add(itemModificacionCliente);
		// Menú Clientes - FIN

		// Menú Autos - INICIO
		this.menuAuto = new JMenu("Autos");

		this.itemAltaModelo = new JMenuItem("Alta Modelo");
		this.itemAltaModelo.addActionListener(this);
		this.menuAuto.add(itemAltaModelo);

		this.itemModificarModelo = new JMenuItem("Modificar Modelo");
		this.itemModificarModelo.addActionListener(this);
		this.menuAuto.add(itemModificarModelo);

		this.itemAltaAuto = new JMenuItem("Alta Auto");
		this.itemAltaAuto.addActionListener(this);
		this.menuAuto.add(itemAltaAuto);

		this.itemModificarAuto = new JMenuItem("Modificar Auto");
		this.itemModificarAuto.addActionListener(this);
		this.menuAuto.add(itemModificarAuto);
		// Menú Autos - FIN

		// Menú Reservas - INICIO
		this.menuReserva = new JMenu("Reservas");

		this.itemAltaReserva = new JMenuItem("Alta Reserva");
		this.itemAltaReserva.addActionListener(this);
		this.menuReserva.add(itemAltaReserva);
		// Menú Reservas - FIN

		// Menú Alquileres - INICIO
		this.menuAlquiler = new JMenu("Alquileres");

		this.itemAltaAlquiler = new JMenuItem("Registrar Alquiler");
		this.itemAltaAlquiler.addActionListener(this);
		this.menuAlquiler.add(itemAltaAlquiler);

		this.itemDevolucion = new JMenuItem("Registrar Devolución");
		this.itemDevolucion.addActionListener(this);
		this.menuAlquiler.add(itemDevolucion);
		// Menú Alquileres - FIN

		// Menú Salir - INICIO
		this.menuSalir = new JMenu("Salir");
		this.menuSalir.addActionListener(this);
		this.menuSalir.addMenuListener(this);
		// Menú Salir - FIN

		// Se agregan las opciones a la barra de menú.
		setJMenuBar(menuBar);
		menuBar.add(menuCliente);
		menuBar.add(menuAuto);
		menuBar.add(menuReserva);
		menuBar.add(menuAlquiler);
		menuBar.add(menuSalir);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == this.itemAltaCliente) {
			getContentPane().removeAll();
			getContentPane().add(new AltaClientePanel(this.sistema, this));
		} else if (event.getSource() == this.itemModificacionCliente) {
			getContentPane().removeAll();
			getContentPane().add(new ModificarClientePanel(this.sistema, this));
		} else if (event.getSource() == this.itemAltaModelo) {
			getContentPane().removeAll();
			getContentPane().add(new AltaModeloPanel(this.sistema, this));
		} else if (event.getSource() == this.itemModificarModelo) {
			getContentPane().removeAll();
			getContentPane().add(new ModificarModeloPanel(this.sistema, this));
		} else if (event.getSource() == this.itemAltaAuto) {
			getContentPane().removeAll();
			getContentPane().add(new AltaAutoPanel(this.sistema, this));
		} else if (event.getSource() == this.itemModificarAuto) {
			getContentPane().removeAll();
			getContentPane().add(new ModificarAutoPanel(this.sistema, this));
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
		getContentPane().add(new AltaModeloPanel(this.sistema, this));
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	public void modificarClienteReset() {
		getContentPane().removeAll();
		getContentPane().add(new ModificarClientePanel(this.sistema, this));
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	public void reset() {
		getContentPane().removeAll();
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	public void altaModeloReset() {
		getContentPane().removeAll();
		getContentPane().add(new AltaModeloPanel(this.sistema, this));
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	public void modificarModeloReset() {
		getContentPane().removeAll();
		getContentPane().add(new ModificarModeloPanel(this.sistema, this));
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	public void altaAutoReset() {
		getContentPane().removeAll();
		getContentPane().add(new AltaAutoPanel(this.sistema, this));
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	public void modificarAutoReset() {
		getContentPane().removeAll();
		getContentPane().add(new ModificarAutoPanel(this.sistema, this));
		getContentPane().revalidate();
		getContentPane().repaint();
	}

}
