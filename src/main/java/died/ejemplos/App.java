package died.ejemplos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import died.ejemplos.gestor.GestorPlanta;
import died.ejemplos.gui.ayuda.GrafoPlanta;
import died.ejemplos.gui.ayuda.PanelAyuda;
import died.ejemplos.view.ViewActualizarStock;
import died.ejemplos.view.ViewAgregarRuta;
import died.ejemplos.view.ViewAltaCamion;
import died.ejemplos.view.ViewAltaInsumo;
import died.ejemplos.view.ViewAltaOrdenPedido;
import died.ejemplos.view.ViewAltaPlanta;
import died.ejemplos.view.ViewAnalisisFlujoMax;
import died.ejemplos.view.ViewBuscarCamion;
import died.ejemplos.view.ViewBuscarOrdenPedido;
import died.ejemplos.view.ViewBuscarPuntoPedido;
import died.ejemplos.view.ViewInfo;
import died.ejemplos.view.ViewVisualizarInsumo;
import died.ejemplos.view.viewBuscarOrdenPedidoProcesada;


// extiende de una ventana en el S.O
public class App extends JFrame {
	
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
			//this.pack();
			this.revalidate();
			this.repaint();
		});
		this.menuItemAltaCamion = new JMenuItem("Registrar");
		this.menuItemAltaCamion.addActionListener( e -> {

			this.setContentPane(new ViewAltaCamion());
			//this.pack();
			this.revalidate();
			this.repaint();
		});
		this.menuItemAltaPlanta = new JMenuItem("Registrar");
		this.menuItemAltaPlanta.addActionListener( e -> {
			this.setContentPane(new ViewAltaPlanta());
			//this.pack();
			this.revalidate();
			this.repaint();
		});
		this.menuItemAgregarRuta = new JMenuItem("Agregar ruta");
		this.menuItemAgregarRuta.addActionListener( e -> {
			this.setContentPane(new ViewAgregarRuta(p));
			//this.pack();
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
			//this.pack();
			this.revalidate();
			this.repaint();
		});
		this.menuItemVisualizarInsumo = new JMenuItem("Visualizar");
		this.menuItemVisualizarInsumo.addActionListener(e -> {
			this.setContentPane(new ViewVisualizarInsumo(this));
			//this.pack();
			this.revalidate();
			this.repaint();
		});
		
		this.menuEntidades.add(menuInsumo);
		this.menuInsumo.add(menuItemAltaInsumo);
		this.menuInsumo.add(menuItemVisualizarInsumo);
		
		this.menuItemActualizarStock = new JMenuItem("Actualizar stock");
		this.menuItemActualizarStock.addActionListener(e -> {
			this.setContentPane(new ViewActualizarStock(this));
			//this.pack();
			this.revalidate();
			this.repaint();
		});
		this.menuPlanta.add(menuItemActualizarStock);
		
		this.menuItemBuscarPuntoPedido = new JMenuItem("Buscar punto de pedido");
		this.menuItemBuscarPuntoPedido.addActionListener(e -> {
			this.setContentPane(new ViewBuscarPuntoPedido());
			//this.pack();
			this.revalidate();
			this.repaint();
		});
		this.menuPlanta.add(menuItemBuscarPuntoPedido);
		
		this.menuOrdenPedio = new JMenu("Orden Pedido");
		this.menuItemAltaOrdenPedido = new JMenuItem("Registrar");
		this.menuItemAltaOrdenPedido.addActionListener(e -> {
			this.setContentPane(new ViewAltaOrdenPedido(this));
			//this.pack();
			this.revalidate();
			this.repaint();
		});
		this.menuEntidades.add(menuOrdenPedio);
		this.menuOrdenPedio.add(menuItemAltaOrdenPedido);
		this.menuItemBuscarOrdenPedido = new JMenuItem("Ordenes CREADAS");
		this.menuItemBuscarOrdenPedido.addActionListener(e -> {
			this.setContentPane(new ViewBuscarOrdenPedido(this,p));
			//this.pack();
			this.revalidate();
			this.repaint();
		});
		this.menuOrdenPedio.add(menuItemBuscarOrdenPedido);
		
		this.menuItemBuscarOrdenPedidoProcesada = new JMenuItem("Ordenes PROCESADAS");
		this.menuItemBuscarOrdenPedidoProcesada.addActionListener(e -> {
			this.setContentPane(new viewBuscarOrdenPedidoProcesada(this,p));
			//this.pack();
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
		
		menuBar.add(this.menuArchivo);
		menuBar.add(this.menuEntidades );
		menuBar.add(menuAnalisis);
		menuBar.add(this.menuInfo);
		this.setJMenuBar(menuBar);
		this.addWindowListener( new WindowAdapter() {
			public void windowIconified(WindowEvent e) {
				//System.out.println("AHORA SI");
			};
		});

	}

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				  try {
			            // Set System L&F
//			          UIManager.setLookAndFeel(
//			          UIManager.getSystemLookAndFeelClassName());
//					  UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");			    }
					  UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");			    }
					  
			    catch (UnsupportedLookAndFeelException e) {
			       // handle exception
			    }
			    catch (ClassNotFoundException e) {
			       // handle exception
			    }
			    catch (InstantiationException e) {
			       // handle exception
			    }
			    catch (IllegalAccessException e) {
			       // handle exception
			    }
				  App app = new App();
				  app.setTitle("Sistema de gestion logística - TP DIED 2020 ");
				  // no hacer nada cuando presiona la cruz para cerrar
			//	  app.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				  app.armarApp();
				  // seteo el tamaño fijo
				  // app.setSize(1020, 750);
				  // para que aparezca maximizado
				  app.setExtendedState(app.getExtendedState() | JFrame.MAXIMIZED_BOTH);
				  app.setVisible(true);
			}
		});
	}

}
