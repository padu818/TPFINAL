package died.ejemplos.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import died.ejemplos.controller.AltaOrdenPedidoController;
import died.ejemplos.controller.BuscarOrdenPedidoController;
import died.ejemplos.dominio.Insumo;
import died.ejemplos.dominio.Planta;
import died.ejemplos.gui.ayuda.GrafoPlanta;

public class ViewBuscarOrdenPedido extends JPanel {
	
	private JLabel lblPlantasDisponibles = new JLabel("Seleccionar planta:");
	private JComboBox<String> plantaDisponible = new JComboBox<String>();
	private JComboBox<String> SeleccionHsKm = new JComboBox<String>();
	private JLabel lblSeleccion = new JLabel("Seleccionar preferencia de camino: ");

	private JTable tablaInsumos = new JTable();
	private JScrollPane tablaInsumosScroll = new JScrollPane(tablaInsumos);
	private Object[][] datosTablaInsumos = {{""},{""},{""}};
	
	private JTable tablaPedidos = new JTable();
	private JScrollPane tablaPedidosScroll = new JScrollPane(tablaPedidos);
	private Object[][] datosTablaPedidos = {{""},{""},{""},{""}};
	
	private JTable tablaRuta = new JTable();
	private JScrollPane tablaRutaScroll = new JScrollPane(tablaRuta);
	private Object[][] datosTablaRuta = {{""}};
	
	private JButton btnGuardar = new JButton("GUARDAR");
	private JButton btnCancelar = new JButton("CANCELAR");
	

	
	private DefaultTableModel model;
	
	private BuscarOrdenPedidoController controller;
	
	public ViewBuscarOrdenPedido(JFrame v, GrafoPlanta p) {
		
		super();
		this.controller= new BuscarOrdenPedidoController(this, v,p);
		inicializarComponentes();
		ubicarComponentes();
		//addTablaInsumos(0);
		
	}
	
	private void inicializarComponentes() {		
		btnCancelar.setPreferredSize(new Dimension(160, 25));
//		btnEliminar.setPreferredSize(new Dimension(160, 25));
		btnGuardar.setPreferredSize(new Dimension(160, 25));
//		btnAgregar.setPreferredSize(new Dimension(160, 25));
		btnGuardar.setVisible(true);
		btnCancelar.setVisible(true);
		tablaPedidos.setEnabled(true);
		tablaPedidos.setVisible(true);
		tablaInsumos.setEnabled(false);
		tablaInsumos.setVisible(true);
		tablaRuta.setEnabled(false);
		tablaRuta.setVisible(true);
		plantaDisponible.setEnabled(false);
		SeleccionHsKm.setEnabled(false);
		addCampos();
		btnCancelar.setEnabled(true);
//		btnEliminar.setEnabled(false);
		btnGuardar.setEnabled(false);
//		btnAgregar.setEnabled(false);
//		campoTotalFilas.setEnabled(false);
		tablaPedidosScroll.setPreferredSize(new Dimension(400, 200));
		tablaPedidosScroll.setVisible(true);
		tablaInsumosScroll.setPreferredSize(new Dimension(400, 200));
		tablaInsumosScroll.setVisible(true);
		tablaRutaScroll.setPreferredSize(new Dimension(400, 200));
		tablaRutaScroll.setVisible(true);
	}
	
	private void ubicarComponentes() {
		setLayout (new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
			
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridheight = 8;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(0,0, 0, 0);
		add(tablaPedidosScroll, constraints);
			
		constraints.gridx = 6;
//		constraints.gridheight = 8;
//		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(0, 50, 0, 0);
		add(tablaInsumosScroll, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 9;
		constraints.insets.set(20, 0, 0, 0);
		add(lblPlantasDisponibles, constraints);
		constraints.insets.set(20, 230, 0, 0);
		plantaDisponible.setPreferredSize(new Dimension(180,20));
		add(plantaDisponible, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 10;
		constraints.insets.set(80, 0, 0, 0);
		add(lblSeleccion, constraints);
		constraints.insets.set(80, 230, 0, 0);
		SeleccionHsKm.setPreferredSize(new Dimension(180,20));
		add(SeleccionHsKm, constraints);
		
		constraints.gridx = 6;
		constraints.gridy = 10;
		constraints.gridheight = 3;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(0,50, 0, 0);
		add(tablaRutaScroll, constraints);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridy = 14;
		constraints.insets.set(20, 0, 0, 0);
		add(btnGuardar, constraints);
		constraints.gridy = 14;
		constraints.insets.set(20, 300, 0, 0);
		add(btnCancelar,constraints);
//			
//		constraints.gridx = 0;	
//		constraints.gridy = 9;
//		constraints.anchor = GridBagConstraints.WEST;
//		constraints.insets.set(20, 0, 0, 0);
//		add(btnAgregar, constraints);
//		constraints.insets.set(20, 200, 0, 0);
//		add(btnEliminar,constraints);
		
	}
	
	public void addListenerBtnGuardar(ActionListener listener) {
		btnGuardar.addActionListener(listener);
	}
	
	public void addListenerBtnCancelar(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}
	
	public void addListenerTablaPedidos(MouseListener listener) {
		tablaPedidos.addMouseListener(listener);
	}
	
	public void addListenerTablaRuta(MouseListener listener) {
		tablaPedidos.addMouseListener(listener);
	}
	
	public void addListenerTablaInsumos(MouseListener listener) {
		tablaInsumos.addMouseListener(listener);
	}
	
	
	public void addListenerSeleccionKmhs(ActionListener listener) {
		SeleccionHsKm.addActionListener(listener);
	}
	
	public void addListenerSeleccionPlanta(ActionListener listener) {
		plantaDisponible.addActionListener(listener);
	}
	
	public Integer getRowTablaPedidos(Point point) {
		return tablaPedidos.rowAtPoint(point);
	}
	
	public Integer getRowTablaRuta(Point point) {
		return tablaRuta.rowAtPoint(point);
	}
	
	public Integer getRowTablaInsumos(Point point) {
		return tablaInsumos.rowAtPoint(point);
	}
	
	public void addTablaPedidos(Integer tamanioTablaActual) {
		DefaultTableModel tableModel = new DefaultTableModel( datosTablaPedidos, tamanioTablaActual) {
			private static final long serialVersionUID = 7365551733085502818L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		model = tableModel;		
		tablaPedidos.setModel(tableModel);
		
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment( JLabel.CENTER );
		
		tablaPedidos.getColumnModel().getColumn(0).setCellRenderer(centrado);
		tablaPedidos.getColumnModel().getColumn(1).setCellRenderer(centrado);
		tablaPedidos.getColumnModel().getColumn(2).setCellRenderer(centrado);
		tablaPedidos.getColumnModel().getColumn(3).setCellRenderer(centrado);
		
		tablaPedidos.getColumnModel().getColumn(0).setPreferredWidth(20);
		tablaPedidos.getColumnModel().getColumn(1).setPreferredWidth(20);
		tablaPedidos.getColumnModel().getColumn(2).setPreferredWidth(20);
		tablaPedidos.getColumnModel().getColumn(3).setPreferredWidth(20);
		
		tablaPedidos.getColumnModel().getColumn(0).setHeaderValue("ID");
		tablaPedidos.getColumnModel().getColumn(1).setHeaderValue("Nombre planta destino");
		tablaPedidos.getColumnModel().getColumn(2).setHeaderValue("Fecha solicitud");
		tablaPedidos.getColumnModel().getColumn(3).setHeaderValue("Fecha entrega");
		
		
		if(tamanioTablaActual > 0) {	
			tablaPedidos.setToolTipText("Doble click para seleccionar un pedido");
			tablaPedidos.setEnabled(true);
//			campoTotalFilas.setText(String.valueOf(tamanioTablaActual));
		}
		else {
			tablaPedidos.setToolTipText(null);
			tablaPedidos.setEnabled(false);
//			campoTotalFilas.setText("");
		}
	}
	
	
	public void addTablaRuta(Integer tamanioTablaActual) {
		DefaultTableModel tableModel = new DefaultTableModel( datosTablaRuta, tamanioTablaActual) {
			private static final long serialVersionUID = 7365551733085502818L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		model = tableModel;		
		tablaRuta.setModel(tableModel);
		
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment( JLabel.CENTER );
		
		tablaRuta.getColumnModel().getColumn(0).setCellRenderer(centrado);
		
		tablaRuta.getColumnModel().getColumn(0).setPreferredWidth(20);
		
		tablaRuta.getColumnModel().getColumn(0).setHeaderValue("Ruta");
		
		
		if(tamanioTablaActual > 0) {	
			tablaRuta.setToolTipText("Doble click para seleccionar la ruta");
			tablaRuta.setEnabled(true);
//			campoTotalFilas.setText(String.valueOf(tamanioTablaActual));
		}
		else {
			tablaRuta.setToolTipText(null);
			tablaRuta.setEnabled(false);
//			campoTotalFilas.setText("");
		}
	}
	
	public void addTablaInsumos(Integer tamanioTablaActual) {
		DefaultTableModel tableModel = new DefaultTableModel( datosTablaInsumos, tamanioTablaActual) {
			private static final long serialVersionUID = 7365551733085502818L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		model = tableModel;		
		tablaInsumos.setModel(tableModel);
		
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment( JLabel.CENTER );
		
		tablaInsumos.getColumnModel().getColumn(0).setCellRenderer(centrado);
		tablaInsumos.getColumnModel().getColumn(1).setCellRenderer(centrado);
		tablaInsumos.getColumnModel().getColumn(2).setCellRenderer(centrado);
		
		tablaInsumos.getColumnModel().getColumn(0).setPreferredWidth(20);
		tablaInsumos.getColumnModel().getColumn(1).setPreferredWidth(20);
		tablaInsumos.getColumnModel().getColumn(2).setPreferredWidth(20);
		
		tablaInsumos.getColumnModel().getColumn(0).setHeaderValue("Nombre");
		tablaInsumos.getColumnModel().getColumn(1).setHeaderValue("Cantidad de insumo");
		tablaInsumos.getColumnModel().getColumn(2).setHeaderValue("Precio");
		
		if(tamanioTablaActual > 0) {	
//			tablaInsumos.setToolTipText("Doble click para seleccionar un insumo");
			tablaInsumos.setEnabled(true);
//			campoTotalFilas.setText(String.valueOf(tamanioTablaActual));
		}
		else {
			tablaInsumos.setToolTipText(null);
			tablaInsumos.setEnabled(false);
//			campoTotalFilas.setText("");
		}
	}
	
	public void setValoresTablaPedidos(Integer fila, Integer id, String nombreDestino, String fechaSoli, String fechaEntrega) {
		model.setValueAt(id, fila, 0);
		model.setValueAt(nombreDestino, fila, 1);
		model.setValueAt(fechaSoli, fila, 2);
		model.setValueAt(fechaEntrega, fila, 3);
	}
	
	public void setValoresTablaRuta( Integer fila, String camino) {
		model.setValueAt(camino, fila, 0);

	}
	
	
	public void setValoresTablaInsumos(Integer fila, String nombre, Integer cant,Double precio) {
		model.setValueAt(nombre, fila, 0);
		model.setValueAt(cant, fila, 1);
		model.setValueAt(precio, fila, 2);
	}
	
	public void setSeleccionPlanta() {
		plantaDisponible.removeAll();
		this.addPlantasDisponibles(controller.listarTodosPlanta());
	}
	
	public void setSeleccionRuta(String text) {
		SeleccionHsKm.removeAll();
		this.SeleccionHsKm.setModel(new DefaultComboBoxModel<String>(new String[] {text}));
	}
	
	public void addPlantasDisponibles(List<Planta> aux) {
		String[] a = new String[aux.size()+1];
		a[0] = "Seleccionar Planta";
		int i =1;
		for(Planta b : aux) {
			a[i] = b.getNombre();
			i++;
		}
		plantaDisponible.setModel(new DefaultComboBoxModel<String>(a));
	}
	
	public void addCampos() {
		String[] a = new String[2];
		a[0] = "Ruta mas corta en Km";
		a[1] = "Ruta mas rapida en Hs";
		SeleccionHsKm.setModel(new DefaultComboBoxModel<String>(a));
	}

	public void habilitarCampos(boolean b) {
		plantaDisponible.setEnabled(b);
		SeleccionHsKm.setEnabled(b);
		tablaRuta.setEnabled(true);
	}
	public void habilitarGuardar(boolean b) {
		btnGuardar.setEnabled(b);
	}
	
	public String getSeleccion() {
		return SeleccionHsKm.getItemAt(SeleccionHsKm.getSelectedIndex());
	}
	
	public Integer getIndexOrigen() {
		if( plantaDisponible.getItemAt(plantaDisponible.getSelectedIndex()) == "Seleccionar Planta") {
			return -1;
		}
		else
			return plantaDisponible.getSelectedIndex()-1;
	}
}
