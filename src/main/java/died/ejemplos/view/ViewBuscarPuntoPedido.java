package died.ejemplos.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import died.ejemplos.controller.BuscarCamionController;
import died.ejemplos.controller.BuscarPuntoPedidoController;
import died.ejemplos.dominio.Insumo;
import died.ejemplos.dominio.Planta;

public class ViewBuscarPuntoPedido extends JPanel {
	
	
private static final long serialVersionUID = 1L;
	
	
	private JLabel lPlanta = new JLabel("Planta:");
	private JLabel lInsumo = new JLabel("Insumo:");
	
	private JLabel ltotalFilas = new JLabel("Total de filas:");
	
	private JTextField campoTotalFilas = new JTextField(3);
	
	private JButton btnBuscar = new JButton("BUSCAR");
	private JButton btnCancelar = new JButton("CANCELAR");
	

	private JComboBox<String> seleccionPlanta = new JComboBox<String>();
	private JComboBox<String> seleccionInsumo = new JComboBox<String>();
	private JTable tablaStock = new JTable();
	private JScrollPane tablaStockScroll = new JScrollPane(tablaStock);
	private Object[][] datosTabla = {{""},{""},{""},{""}};
	private DefaultTableModel model;
	private BuscarPuntoPedidoController controller;
	
	/*
	 * Nombre de la planta
• Nombre del Insumo
• Stock del insumo en la planta
• Punto de pedido del insumo en la planta
• Stock total del producto en toda la empresa
	 */
	
	
	
	public ViewBuscarPuntoPedido() {
		super();
		this.controller= new BuscarPuntoPedidoController(this);
		inicializarComponentes();
		ubicarComponentes();
		addTablaStock(0);	
	}

	private void inicializarComponentes() {		
		btnBuscar.setPreferredSize(new Dimension(160, 25));
		btnCancelar.setPreferredSize(new Dimension(160, 25));
		seleccionPlanta.setEnabled(true);
		seleccionInsumo.setEnabled(true);
		//this.addKmsAnio();
		tablaStock.setEnabled(true);
		btnBuscar.setEnabled(true);
		btnCancelar.setEnabled(true);
		campoTotalFilas.setEnabled(false);
		tablaStockScroll.setPreferredSize(new Dimension(680, 500));	
		
	}
	
	private void ubicarComponentes() {
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(10, 5, 10, 5);
		add(lPlanta, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 10, 10, 5);
		add(seleccionPlanta, constraints);	
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(10, 5, 10, 5);
		add(lInsumo, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 10, 10, 5);
		add(seleccionInsumo, constraints);	

		
		
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(30, 5, 5, 5);
		add(btnBuscar, constraints);
		
		constraints.gridy = 8;
		constraints.insets.set(30, 5, 5, 5);
		add(btnCancelar, constraints);	
		
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridheight = 6;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(5, 30, 5, 5);
		add(tablaStockScroll, constraints);	
		
		constraints.gridx = 2;
		constraints.gridy = 6;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 5, 5, 5);
		add(campoTotalFilas, constraints);	
		constraints.insets.set(5, 5, 5, 44);
		add(ltotalFilas, constraints);
	}
			
	public void addListenerBtnBuscar(ActionListener listener) {
		btnBuscar.addActionListener(listener);
	}

	public void addListenerBtnCancelar(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}
	
//	public void addListenerTablaCamiones(MouseListener listener) {
//		tablaStock.addMouseListener(listener);
//	}

	
	public void addItemTipoInsumo(String item) {
		seleccionInsumo.addItem(item);
	}
	
	public void addItemTipoPlanta(String item) {
		seleccionPlanta.addItem(item);
	}
	
	public Integer getRowTablaCamiones(Point point) {
		return tablaStock.rowAtPoint(point);
	}

	public String getSeleccionPlanta() {
		if( seleccionPlanta.getItemAt(seleccionPlanta.getSelectedIndex()) == "Seleccionar planta") {
			return "-";
		}
		else
			return seleccionPlanta.getItemAt(seleccionPlanta.getSelectedIndex());
	}
	
	public Integer getIndexSeleccionPlanta() {
		if( seleccionPlanta.getItemAt(seleccionPlanta.getSelectedIndex()) == "Seleccionar planta") {
			return -1;
		}
		else
			return seleccionPlanta.getSelectedIndex()-1;
	}
	
	public String getSeleccionInsumo() {
		if( seleccionInsumo.getItemAt(seleccionInsumo.getSelectedIndex()) == "Seleccionar insumo") {
			return "-";
		}
		else
			return seleccionInsumo.getItemAt(seleccionInsumo.getSelectedIndex());
	}
	
	public Integer getIndexSeleccionInsumo() {
		if( seleccionInsumo.getItemAt(seleccionInsumo.getSelectedIndex()) == "Seleccionar insumo") {
			return -1;
		}
		else
			return seleccionInsumo.getSelectedIndex()-1;
	}
	
	
	public void addTablaStock(Integer tamanioTablaActual) {
		DefaultTableModel tableModel = new DefaultTableModel( datosTabla, tamanioTablaActual) {
			private static final long serialVersionUID = 7365551733085502818L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		model = tableModel;		
		tablaStock.setModel(tableModel);
		
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment( JLabel.CENTER );
		
		tablaStock.getColumnModel().getColumn(0).setCellRenderer(centrado);
		tablaStock.getColumnModel().getColumn(1).setCellRenderer(centrado);
		tablaStock.getColumnModel().getColumn(2).setCellRenderer(centrado);
		tablaStock.getColumnModel().getColumn(3).setCellRenderer(centrado);
		
		tablaStock.getColumnModel().getColumn(0).setPreferredWidth(120);
		tablaStock.getColumnModel().getColumn(1).setPreferredWidth(120);
		tablaStock.getColumnModel().getColumn(2).setPreferredWidth(110);
		tablaStock.getColumnModel().getColumn(3).setPreferredWidth(120);
		
		tablaStock.getColumnModel().getColumn(0).setHeaderValue("Planta");
		tablaStock.getColumnModel().getColumn(1).setHeaderValue("Insumo");
		tablaStock.getColumnModel().getColumn(2).setHeaderValue("Stock");
		tablaStock.getColumnModel().getColumn(3).setHeaderValue("Punto de pedido");

		
		if(tamanioTablaActual > 0) {	
		//	tablaStock.setToolTipText("Doble click para seleccionar un camion");
			tablaStock.setEnabled(false);
			campoTotalFilas.setText(String.valueOf(tamanioTablaActual));
		}
		else {
		//	tablaCamiones.setToolTipText(null);
			tablaStock.setEnabled(false);
			campoTotalFilas.setText("");
		}
	}	
	
	public void setValoresTablaStock(Integer fila, String planta, String insumo, Integer stockinsumo, Integer puntopedido) {
		model.setValueAt(planta, fila, 0);
		model.setValueAt(insumo, fila, 1);
		model.setValueAt(stockinsumo, fila, 2);
		model.setValueAt(puntopedido, fila, 3);

	}
	
	public void addPlantas(List<Planta> aux) {
		String[] a = new String[aux.size()+1];
		a[0] = "Seleccionar planta";
		int i =1;
		for(Planta b : aux) {
			a[i] = b.getNombre();
			i++;
		}
		seleccionPlanta.setModel(new DefaultComboBoxModel<String>(a));
	}

	public void addInsumos(List<Insumo> aux) {
		String[] a = new String[aux.size()+1];
		a[0] = "Seleccionar insumo";
		int i =1;
		for(Insumo b : aux) {
			a[i] = b.getNombre();
			i++;
		}
		seleccionInsumo.setModel(new DefaultComboBoxModel<String>(a));
	}
}
