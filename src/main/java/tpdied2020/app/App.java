package tpdied2020.app;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import tpdied2020.gestor.GestorPlanta;
import tpdied2020.gui.auxiliar.GrafoPlanta;
import tpdied2020.view.ViewActualizarStock;
import tpdied2020.view.ViewAgregarRuta;
import tpdied2020.view.ViewAltaCamion;
import tpdied2020.view.ViewAltaInsumo;
import tpdied2020.view.ViewAltaOrdenPedido;
import tpdied2020.view.ViewAltaPlanta;
import tpdied2020.view.ViewAnalisisFlujoMax;
import tpdied2020.view.ViewBuscarCamion;
import tpdied2020.view.ViewBuscarOrdenPedido;
import tpdied2020.view.ViewBuscarPuntoPedido;
import tpdied2020.view.ViewInfo;
import tpdied2020.view.ViewVisualizarInsumo;
import tpdied2020.view.viewBuscarOrdenPedidoProcesada;


public class App extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	JMenuBar menuBar;
	JMenu menuArchivo;
	JMenu menuEntidades;
	JMenu menuInfo;
	JMenuItem menuItemInfo;
	JMenu menuCamion;
	JMenu menuPlanta;
	JMenuItem menuItemBuscarCamion;
	JMenuItem menuItemAltaPlanta;
	JMenuItem menuItemAgregarRuta;
	JMenuItem menuItemAltaCamion;
	JMenuItem menuItemSalir;
	JMenu menuInsumo;
	JMenuItem menuItemAltaInsumo;
	JMenuItem menuItemVisualizarInsumo;
	JMenuItem menuItemActualizarStock;
	JMenuItem menuItemBuscarPuntoPedido;
	JMenu menuOrdenPedio;
	JMenuItem menuItemAltaOrdenPedido;
	JMenuItem menuItemBuscarOrdenPedido;
	JMenuItem menuItemBuscarOrdenPedidoProcesada;
	JMenu menuAnalisis;
	JMenuItem menuItemFlujoMaximo;
	
	private GrafoPlanta p;
	private GestorPlanta gestor;
	
	private App() {
	}

	private void armarApp() {
		this.menuBar = new JMenuBar();
		gestor = new GestorPlanta();
		p = gestor.armarGrafo();
		
		this.menuArchivo = new JMenu("Archivo");
		this.menuItemSalir = new JMenuItem("Salir");
		this.menuItemSalir.addActionListener( e -> System.exit(0));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				
			}
		});
		this.menuArchivo.add(menuItemSalir);
		this.menuPlanta = new JMenu("Planta");
		this.menuEntidades = new JMenu("Entidades");
		this.menuCamion = new JMenu("Camion");
		this.menuItemBuscarCamion = new JMenuItem("Buscar");
		this.menuItemBuscarCamion.addActionListener( e -> {
			this.setContentPane(new ViewBuscarCamion(this));
			this.revalidate();
			this.repaint();
		});
		this.menuItemAltaCamion = new JMenuItem("Registrar");
		this.menuItemAltaCamion.addActionListener( e -> {

			this.setContentPane(new ViewAltaCamion());
			this.revalidate();
			this.repaint();
		});
		this.menuItemAltaPlanta = new JMenuItem("Registrar");
		this.menuItemAltaPlanta.addActionListener( e -> {
			this.setContentPane(new ViewAltaPlanta());
			this.revalidate();
			this.repaint();
		});
		this.menuItemAgregarRuta = new JMenuItem("Agregar ruta");
		this.menuItemAgregarRuta.addActionListener( e -> {
			this.setContentPane(new ViewAgregarRuta(p));
			this.revalidate();
			this.repaint();
		});
		this.menuPlanta.add(menuItemAgregarRuta);
		this.menuEntidades.add(menuCamion);
		this.menuEntidades.add(menuPlanta);
		this.menuPlanta.add(menuItemAltaPlanta);
		this.menuCamion.add(menuItemAltaCamion);
		this.menuCamion.add(menuItemBuscarCamion);
		
		this.menuInsumo = new JMenu("Insumo");
		this.menuItemAltaInsumo = new JMenuItem("Registrar");
		this.menuItemAltaInsumo.addActionListener(e -> {
			this.setContentPane(new ViewAltaInsumo(this));
			this.revalidate();
			this.repaint();
		});
		this.menuItemVisualizarInsumo = new JMenuItem("Visualizar");
		this.menuItemVisualizarInsumo.addActionListener(e -> {
			this.setContentPane(new ViewVisualizarInsumo(this));
			this.revalidate();
			this.repaint();
		});
		
		this.menuEntidades.add(menuInsumo);
		this.menuInsumo.add(menuItemAltaInsumo);
		this.menuInsumo.add(menuItemVisualizarInsumo);
		
		this.menuItemActualizarStock = new JMenuItem("Actualizar stock");
		this.menuItemActualizarStock.addActionListener(e -> {
			this.setContentPane(new ViewActualizarStock(this));
			this.revalidate();
			this.repaint();
		});
		this.menuPlanta.add(menuItemActualizarStock);
		
		this.menuItemBuscarPuntoPedido = new JMenuItem("Buscar punto de pedido");
		this.menuItemBuscarPuntoPedido.addActionListener(e -> {
			this.setContentPane(new ViewBuscarPuntoPedido());
			this.revalidate();
			this.repaint();
		});
		this.menuPlanta.add(menuItemBuscarPuntoPedido);
		
		this.menuOrdenPedio = new JMenu("Orden Pedido");
		this.menuItemAltaOrdenPedido = new JMenuItem("Registrar");
		this.menuItemAltaOrdenPedido.addActionListener(e -> {
			this.setContentPane(new ViewAltaOrdenPedido(this));
			this.revalidate();
			this.repaint();
		});
		this.menuEntidades.add(menuOrdenPedio);
		this.menuOrdenPedio.add(menuItemAltaOrdenPedido);
		this.menuItemBuscarOrdenPedido = new JMenuItem("Ordenes CREADAS");
		this.menuItemBuscarOrdenPedido.addActionListener(e -> {
			this.setContentPane(new ViewBuscarOrdenPedido(this,p));
			this.revalidate();
			this.repaint();
		});
		this.menuOrdenPedio.add(menuItemBuscarOrdenPedido);
		
		this.menuItemBuscarOrdenPedidoProcesada = new JMenuItem("Ordenes PROCESADAS");
		this.menuItemBuscarOrdenPedidoProcesada.addActionListener(e -> {
			this.setContentPane(new viewBuscarOrdenPedidoProcesada(this,p));
			this.revalidate();
			this.repaint();
		});
		this.menuOrdenPedio.add(menuItemBuscarOrdenPedidoProcesada);
		
		this.menuAnalisis = new JMenu("Analisis");
		this.menuItemFlujoMaximo = new JMenuItem("Flujo maximo");
		this.menuItemFlujoMaximo.addActionListener( e -> {
			this.setContentPane(new ViewAnalisisFlujoMax(p));
			this.revalidate();
			this.repaint();
		});
		
		this.menuAnalisis.add(menuItemFlujoMaximo);
		
		this.menuInfo = new JMenu("INFO");
		this.menuItemInfo = new JMenuItem("Integrantes");
		this.menuItemInfo.addActionListener( e -> {
			ViewInfo panel = new ViewInfo();
			panel.armarPanel();
			this.setContentPane(panel);	
			this.revalidate();
			this.repaint();
		}); 
		this.menuInfo.add(menuItemInfo);
		
		menuBar.add(this.menuArchivo);
		menuBar.add(this.menuEntidades );
		menuBar.add(menuAnalisis);
		menuBar.add(this.menuInfo);
		this.setJMenuBar(menuBar);
		this.addWindowListener( new WindowAdapter() {
			public void windowIconified(WindowEvent e) {
			};
		});

	}

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				  try {
					  
					  UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");			    }
					  
			    catch (UnsupportedLookAndFeelException e) {
			    	
			    }
			    catch (ClassNotFoundException e) {
			    	
			    }
			    catch (InstantiationException e) {
			    	
			    }
			    catch (IllegalAccessException e) {
			    	
			    }
				  App app = new App();
				  app.setTitle("Sistema de gestion logística - TP DIED 2020 ");
				  app.armarApp();
				  app.setExtendedState(app.getExtendedState() | JFrame.MAXIMIZED_BOTH);
				  app.setVisible(true);
			}
		});
	}

}
