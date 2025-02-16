package tpdied2020.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import tpdied2020.controller.ActualizarStockController;
import tpdied2020.dominio.Insumo;

public class ViewActualizarStock extends JPanel {
	
	
	private static final long serialVersionUID = 1L;
	private JLabel lblIdPlanta = new JLabel("ID:");
	private JLabel lblNombrePlanta = new JLabel("Nombre Planta:");
	private JLabel lblSeleccionInsumo = new JLabel("Seleccionar insumo:");
	private JLabel lblCantidadDeInsumo = new JLabel("Cantidad de insumo:");
	private JLabel lblPuntoPedido = new JLabel("Punto de pedido:");
	private JLabel lblSeparador = new JLabel("__________________________________________________________________________________________________");	
	private JLabel ltotalFilas = new JLabel("Total de filas:");
	
	private JTextField campoTotalFilas = new JTextField(3);
	private JTextField campoCantidadInsumo = new JTextField(16);
	private JTextField campoPuntoPedido = new JTextField(16);
	private JTextField campoNombrePlanta = new JTextField(16);
	private JTextField campoIdPlanta = new JTextField(16);
	
	private JComboBox<String> seleccionInsumo = new JComboBox<String>();
	
	private JButton btnCancelar = new JButton("CANCELAR");
	private JButton btnEliminar = new JButton("ELIMINAR");
	private JButton btnGuardar = new JButton("GUARDAR");
	
	private JTable tablaPlantas = new JTable();
	private JScrollPane tablaPlantasScroll = new JScrollPane(tablaPlantas);
	private Object[][] datosTablaPlantas = {{""},{""}};
	private DefaultTableModel model;
	
	private JTable tablaInsumos = new JTable();
	private JScrollPane tablaInsumosScroll = new JScrollPane(tablaInsumos);
	private Object[][] datosTablaInsumos = {{""},{""},{""}};
	
	private ActualizarStockController controller;
	
	public ViewActualizarStock(JFrame v) {
		super();
		this.controller= new ActualizarStockController(this, v);
		inicializarComponentes();
		ubicarComponentes();
	}
	
	private void inicializarComponentes() {		
		btnCancelar.setPreferredSize(new Dimension(160, 25));
		btnEliminar.setPreferredSize(new Dimension(160, 25));
		btnGuardar.setPreferredSize(new Dimension(160, 25));
		tablaPlantas.setEnabled(true);
		tablaPlantas.setVisible(true);
		tablaInsumos.setEnabled(false);
		tablaInsumos.setVisible(true);
		btnCancelar.setEnabled(true);
		btnEliminar.setEnabled(false);
		btnGuardar.setEnabled(true);
		campoTotalFilas.setEnabled(false);
		seleccionInsumo.setEnabled(false);
		campoIdPlanta.setEnabled(false);
		campoCantidadInsumo.setEnabled(false);
		campoNombrePlanta.setEnabled(false);
		campoPuntoPedido.setEnabled(false);
		tablaPlantasScroll.setPreferredSize(new Dimension(200, 120));
		tablaPlantasScroll.setVisible(true);
		tablaInsumosScroll.setPreferredSize(new Dimension(400, 200));
		tablaInsumosScroll.setVisible(true);
	}
	
	private void ubicarComponentes() {
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridheight = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(0,0, 0, 0);
		add(tablaPlantasScroll, constraints);	
		
		constraints.gridx = 2;
		constraints.gridy = 4;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 5, 5, 5);
		add(campoTotalFilas, constraints);	
		constraints.insets.set(5, 5, 5, 44);
		add(ltotalFilas, constraints);
		
		constraints.gridy = 6;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(0,0, 0, 0);
		add(lblSeparador, constraints);
		
		constraints.gridx =0;
		constraints.gridy = 7;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 0, 0, 0);
		add(lblIdPlanta, constraints);
		constraints.insets.set(10, 120, 0, 0);
		add(campoIdPlanta, constraints);
		
		constraints.gridy = 8;
		constraints.insets.set(20, 0, 0, 0);
		add(lblNombrePlanta, constraints);
		constraints.insets.set(20, 120, 0, 0);
		add(campoNombrePlanta, constraints);
		
		constraints.gridy = 9;
		constraints.insets.set(20, 0, 0, 0);
		add(lblSeleccionInsumo, constraints);
		constraints.insets.set(20, 120, 0, 0);
		seleccionInsumo.setPreferredSize(new Dimension(180,20));
		add(seleccionInsumo, constraints);
		
		constraints.gridy = 10;
		constraints.insets.set(20, 0, 0, 0);
		add(lblCantidadDeInsumo, constraints);
		constraints.insets.set(20, 120, 0, 0);
		add(campoCantidadInsumo, constraints);
		
		constraints.gridy = 11;
		constraints.insets.set(20, 0, 0, 0);
		add(lblPuntoPedido, constraints);
		constraints.insets.set(20, 120, 0, 0);
		add(campoPuntoPedido, constraints);
		
		constraints.gridy = 12;
		constraints.gridwidth = 4;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(30, 0, 0, 0);
		add(btnGuardar, constraints);
		constraints.insets.set(30, 200, 0, 0);
		add(btnCancelar, constraints);
		constraints.insets.set(30, 400, 0, 0);
		add(btnEliminar, constraints);
		
		constraints.gridx = 3;
		constraints.gridy = 7;
		constraints.gridheight = 8;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 30, 5, 5);
		add(tablaInsumosScroll, constraints);
		
	}
	
	public void limpiarFormulario() {
		
		this.campoCantidadInsumo.setText("");
		this.campoPuntoPedido.setText("");
	
	}
	
	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
			    detalle,titulo,
			    JOptionPane.ERROR_MESSAGE);
	}
	
	public void addListenerTablaPlantas(MouseListener listener) {
		tablaPlantas.addMouseListener(listener);
	}
	
	public void addListenerTablaInsumos(MouseListener listener) {
		tablaInsumos.addMouseListener(listener);
	}
	
	public Integer getRowTablaPlantas(Point point) {
		return tablaPlantas.rowAtPoint(point);
	}
	
	public Integer getRowTablaInsumos(Point point) {
		return tablaPlantas.rowAtPoint(point);
	}
	
	public void addTablaPlantas(Integer tamanioTablaActual) {
		DefaultTableModel tableModel = new DefaultTableModel( datosTablaPlantas, tamanioTablaActual) {
			private static final long serialVersionUID = 7365551733085502818L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		model = tableModel;		
		tablaPlantas.setModel(tableModel);
		
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment( JLabel.CENTER );
		
		tablaPlantas.getColumnModel().getColumn(0).setCellRenderer(centrado);
		tablaPlantas.getColumnModel().getColumn(1).setCellRenderer(centrado);
		
		tablaPlantas.getColumnModel().getColumn(0).setPreferredWidth(20);
		tablaPlantas.getColumnModel().getColumn(1).setPreferredWidth(20);
		
		tablaPlantas.getColumnModel().getColumn(0).setHeaderValue("ID");
		tablaPlantas.getColumnModel().getColumn(1).setHeaderValue("Nombre");
		
		if(tamanioTablaActual > 0) {	
			tablaPlantas.setToolTipText("Doble click para seleccionar una planta");
			tablaPlantas.setEnabled(true);
			campoTotalFilas.setText(String.valueOf(tamanioTablaActual));
		}
		else {
			tablaPlantas.setToolTipText(null);
			tablaPlantas.setEnabled(false);
			campoTotalFilas.setText("");
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
		tablaInsumos.getColumnModel().getColumn(2).setHeaderValue("Punto de pedido");
		
		if(tamanioTablaActual > 0) {	
			tablaInsumos.setToolTipText("Doble click para seleccionar un insumo");
			tablaInsumos.setEnabled(true);
		}
		else {
			tablaInsumos.setToolTipText(null);
			tablaInsumos.setEnabled(false);
		}
	}
	
	public void setValoresTablaPlantas(Integer fila, Integer id, String nombre) {
		model.setValueAt(id, fila, 0);
		model.setValueAt(nombre, fila, 1);
	}
	
	public void setValoresTablaInsumos(Integer fila, String nombre, Integer cant,Integer ptoPedido) {
		model.setValueAt(nombre, fila, 0);
		model.setValueAt(cant, fila, 1);
		model.setValueAt(ptoPedido, fila, 2);
	}
	
	public void addListenerBtnCancelar(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}
	
	
	public void addListenerBtnGuardar(ActionListener listener) {
		btnGuardar.addActionListener(listener);
	}
	
	public void addListenerBtnEliminar(ActionListener listener) {
		btnEliminar.addActionListener(listener);
	}
	
	public void addListenerCampoCantidadInsumo(KeyListener listener) {
		campoCantidadInsumo.addKeyListener(listener); 
	}
	
	public void addListenerCampoPuntoPedido(KeyListener listener) {
		campoPuntoPedido.addKeyListener(listener); 
	}
	
	public void habilitarCampos(Boolean t) {
		campoCantidadInsumo.setEnabled(t);
		campoPuntoPedido.setEnabled(t);
		seleccionInsumo.setEnabled(t);
	}
	
	public void setCampos(Integer id, String nombre) {
		setCampoIdPlanta(id.toString());
		setCampoNombrePlanta(nombre);
	}

	public void setCampoNombrePlanta(String nombre) {
		this.campoNombrePlanta.setText(nombre);
	}

	public void setCampoIdPlanta(String id) {
		this.campoIdPlanta.setText(id);
	}
	
	public String getCantidadInsumo() {
		return campoCantidadInsumo.getText();
	}
	
	public String getPuntoPedido() {
		return campoPuntoPedido.getText();
	}
	
	public String getSeleccionInsumo() {
		if( seleccionInsumo.getItemAt(seleccionInsumo.getSelectedIndex()) == "Seleccionar insumo") {
			return "Seleccionar insumo";
		}
		else
			return seleccionInsumo.getItemAt(seleccionInsumo.getSelectedIndex());
	}
	
	public Integer getIndexInsumo() {
		if( seleccionInsumo.getItemAt(seleccionInsumo.getSelectedIndex()) == "Seleccionar insumo") {
			return -1;
		}
		else
			return seleccionInsumo.getSelectedIndex()-1;
	}
	
	public void setSeleccionInsumo() {
		seleccionInsumo.removeAll();
		this.addInsumos(controller.listarTodoInsumo());
	}
	
	public void addInsumos(List<Insumo> aux) {
		String[] a = new String[aux.size()+1];
		a[0] = "Seleccionar Insumo";
		int i =1;
		for(Insumo b : aux) {
			a[i] = b.getNombre();
			i++;
		}
		seleccionInsumo.setModel(new DefaultComboBoxModel<String>(a));
	}

	public void habilitarEliminar(boolean b) {
		btnEliminar.setEnabled(b);
		
	}

}
