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

import died.ejemplos.gui.ayuda.PanelAyuda;
import died.ejemplos.view.PanelCamiones;

// extiende de una ventana en el S.O
public class App extends JFrame {

	
	JMenuBar menuBar;
	JMenu menuArchivo;
	JMenu menuEntidades;
	JMenu menuAyuda;
	JMenuItem menuItemAyuda;
	JMenuItem menuItemCamion;
	JMenuItem menuItemSalir;

	private App() {
	}

	private void armarApp() {
		this.menuBar = new JMenuBar();

		this.menuArchivo = new JMenu("Archivo");
		this.menuItemSalir = new JMenuItem("Salir");
		this.menuItemSalir.addActionListener( e -> System.exit(0));
		this.menuArchivo.add(menuItemSalir);
		
		this.menuEntidades = new JMenu("Entidades");
		this.menuItemCamion = new JMenuItem("Camion");
		this.menuItemCamion.addActionListener( e -> {
			System.out.println("LISTENER 1");
			this.setContentPane(new PanelCamiones());
			//this.pack();
			this.revalidate();
			this.repaint();
		});
		this.menuItemCamion.addActionListener( e -> {
			System.out.println("LISTENER 2");
		});
		/*
		 * e -> {
			this.setContentPane(new PanelCamiones());
			this.pack();
		}
		 */
		this.menuEntidades.add(menuItemCamion);

		this.menuAyuda = new JMenu("Ayuda");
		this.menuItemAyuda = new JMenuItem("Manual");
		this.menuItemAyuda.setMnemonic(KeyEvent.VK_F1);
		this.menuItemAyuda.addActionListener( e -> {
			PanelAyuda panel = new PanelAyuda();
			panel.armarPanel();
			this.setContentPane(panel);	
			this.revalidate();
			this.repaint();
		}); 
		this.menuAyuda.add(menuItemAyuda);

		menuBar.add(this.menuArchivo);
		menuBar.add(this.menuEntidades );
		menuBar.add(this.menuAyuda);
		this.setJMenuBar(menuBar);
		this.addWindowListener( new WindowAdapter() {
			public void windowIconified(WindowEvent e) {
				System.out.println("AHORA SI");
			};
		});
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("mouseReleased en "+e.getPoint());

			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("mousePressed en "+e.getPoint());

			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Click en "+e.getPoint());
			}
		});
//		JLabel etiquetaBievneido = new JLabel(" EJEMPLO DIED");
//		etiquetaBievneido.setSize(200, 100);
//		this.add(etiquetaBievneido);
		JTextField texto = new JTextField(500);
		texto.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				System.out.println("estoy perdiendo el foco");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				System.out.println("estoy ganando el foco");
				
			}
		});
		this.add(texto);
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
				  app.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				  app.armarApp();
				  // seteo el tamaño fijo
				  // app.setSize(1020, 750);
				  // para que aparezca maximizado
				  app.setExtendedState(app.getExtendedState() | JFrame.MAXIMIZED_BOTH);
				  app.setVisible(true);
				  System.out.println("app creada");
			}
		});
	}

}
