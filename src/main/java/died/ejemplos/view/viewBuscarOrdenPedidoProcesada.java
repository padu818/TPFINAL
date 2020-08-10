package died.ejemplos.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import died.ejemplos.controller.BuscarOrdenPedidoController;
import died.ejemplos.controller.BuscarOrdenPedidoProcesadaController;
import died.ejemplos.gui.ayuda.GrafoPlanta;

public class viewBuscarOrdenPedidoProcesada extends JPanel {
		
	
	private JTable tablaPedidos = new JTable();
	private JScrollPane tablaPedidosScroll = new JScrollPane(tablaPedidos);
	private Object[][] datosTablaPedidos = {{""},{""},{""},{""},{""},{""},{""}};
	
	private JTable tablaInsumos = new JTable();
	private JScrollPane tablaInsumosScroll = new JScrollPane(tablaInsumos);
	private Object[][] datosTablaInsumos = {{""},{""},{""}};
	
	private JButton btnGuardar = new JButton("FINALIZAR");
	private JButton btnCancelar = new JButton("CANCELAR");
	private DefaultTableModel model;
	
private BuscarOrdenPedidoProcesadaController controller;
	
	public viewBuscarOrdenPedidoProcesada(JFrame v, GrafoPlanta p) {
		
		super();
		this.controller= new BuscarOrdenPedidoProcesadaController(this, p);
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
		btnCancelar.setEnabled(true);
//		btnEliminar.setEnabled(false);
		btnGuardar.setEnabled(false);
//		btnAgregar.setEnabled(false);
//		campoTotalFilas.setEnabled(false);
		tablaPedidosScroll.setPreferredSize(new Dimension(700, 200));
		tablaPedidosScroll.setVisible(true);
		tablaInsumosScroll.setPreferredSize(new Dimension(400, 200));
		tablaInsumosScroll.setVisible(true);
		tablaInsumosScroll.setEnabled(false);
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
		
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridy = 11;
		constraints.insets.set(20, 0, 0, 0);
		add(btnGuardar, constraints);
		constraints.gridy = 11;
		constraints.insets.set(20, 300, 0, 0);
		add(btnCancelar,constraints);

		
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
		tablaPedidos.getColumnModel().getColumn(4).setCellRenderer(centrado);
		tablaPedidos.getColumnModel().getColumn(5).setCellRenderer(centrado);
		tablaPedidos.getColumnModel().getColumn(6).setCellRenderer(centrado);
		
		tablaPedidos.getColumnModel().getColumn(0).setPreferredWidth(10);
		tablaPedidos.getColumnModel().getColumn(1).setPreferredWidth(40);
		tablaPedidos.getColumnModel().getColumn(2).setPreferredWidth(40);
		tablaPedidos.getColumnModel().getColumn(3).setPreferredWidth(20);	
		tablaPedidos.getColumnModel().getColumn(4).setPreferredWidth(20);
		tablaPedidos.getColumnModel().getColumn(5).setPreferredWidth(20);
		tablaPedidos.getColumnModel().getColumn(6).setPreferredWidth(20);
		
		tablaPedidos.getColumnModel().getColumn(0).setHeaderValue("ID");
		tablaPedidos.getColumnModel().getColumn(1).setHeaderValue("Nombre planta origen");
		tablaPedidos.getColumnModel().getColumn(2).setHeaderValue("Nombre planta destino");
		tablaPedidos.getColumnModel().getColumn(3).setHeaderValue("Fecha solicitud");
		tablaPedidos.getColumnModel().getColumn(4).setHeaderValue("Fecha entrega");
		tablaPedidos.getColumnModel().getColumn(5).setHeaderValue("Patente");
		tablaPedidos.getColumnModel().getColumn(6).setHeaderValue("Costo envio");
		
		
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
	
	public void setValoresTablaPedidos(Integer fila, Integer id,String nombreOrigen,String nombreDestino, String fechaSoli, String fechaEntrega, String patente, Double cost) {
		model.setValueAt(id, fila, 0);
		model.setValueAt(nombreOrigen, fila, 1);
		model.setValueAt(nombreDestino, fila, 2);
		model.setValueAt(fechaSoli, fila, 3);
		model.setValueAt(fechaEntrega, fila, 4);
		model.setValueAt(patente, fila, 5);
		model.setValueAt(cost, fila, 6);
	}
	

	
	
	public void setValoresTablaInsumos(Integer fila, String nombre, Integer cant,Double precio) {
		model.setValueAt(nombre, fila, 0);
		model.setValueAt(cant, fila, 1);
		model.setValueAt(precio, fila, 2);
	}
	
	public void habilitarGuardar(boolean b) {
		btnGuardar.setEnabled(b);
	}
	
	
	public Integer getRowTablaPedidos(Point point) {
		return tablaPedidos.rowAtPoint(point);
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
}
