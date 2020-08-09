package died.ejemplos.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import died.ejemplos.controller.ActualizarStockController;
import died.ejemplos.controller.AltaOrdenPedidoController;
import died.ejemplos.dominio.Insumo;

public class ViewAltaOrdenPedido extends JPanel {
	
	private static Color COLOR_FONDO_TEXTO; 
	private static Color COLOR_LETRA;
	private static Color COLOR_LETRA_ERRONEA;
	private static Color COLOR_FONDO_ERRONEO;

	//private JLabel lblTitulo = new JLabel("Administracion de insumos:");
	
	private DateFormat df = new SimpleDateFormat("dd/mm/yyyy");

	private JLabel lblNombrePlanta = new JLabel("Planta:");
	private JLabel lblIdPlanta = new JLabel("Id Planta:");
	private JLabel lblFechaMax = new JLabel("Fecha maxima de entrega:");
	private JLabel lblSeleccionInsumo = new JLabel("Seleccionar insumo:");
	private JLabel lblCantidad = new JLabel("Cantidad:");
//	private JLabel lblSeparador = new JLabel("__________________________________________________________________________________________________");
	
	private JTextField campoNombrePlanta = new JTextField(16);
	private JTextField campoIdPlanta = new JTextField(16);
	private JTextField campoCantidadInsumo = new JTextField(16);
	private JFormattedTextField campoFechaMax = new JFormattedTextField(df);
	
	private JComboBox<String> seleccionInsumo = new JComboBox<String>();
	
	private JButton btnCancelar = new JButton("CANCELAR");
	private JButton btnEliminar = new JButton("ELIMINAR");
	private JButton btnGuardar = new JButton("GUARDAR");
	private JButton btnAgregar = new JButton("AGREGAR");
	
	private JTable tablaPlantas = new JTable();
	private JScrollPane tablaPlantasScroll = new JScrollPane(tablaPlantas);
	private Object[][] datosTablaPlantas = {{""},{""}};
	private DefaultTableModel model;
	
	private JTable tablaInsumos = new JTable();
	private JScrollPane tablaInsumosScroll = new JScrollPane(tablaInsumos);
	private Object[][] datosTablaInsumos = {{""},{""},{""}};
	
	private AltaOrdenPedidoController controller;
	
	public ViewAltaOrdenPedido(JFrame v) {
		
		super();
		this.controller= new AltaOrdenPedidoController(this, v);
		inicializarComponentes();
		ubicarComponentes();
		//addTablaInsumos(0);
		
	}
	
	private void inicializarComponentes() {		
		btnCancelar.setPreferredSize(new Dimension(160, 25));
		btnEliminar.setPreferredSize(new Dimension(160, 25));
		btnGuardar.setPreferredSize(new Dimension(160, 25));
		btnAgregar.setPreferredSize(new Dimension(160, 25));
		tablaPlantas.setEnabled(true);
		tablaPlantas.setVisible(true);
		tablaInsumos.setEnabled(false);
		tablaInsumos.setVisible(true);
		btnCancelar.setEnabled(true);
		btnEliminar.setEnabled(false);
		btnGuardar.setEnabled(false);
		btnAgregar.setEnabled(false);
//		campoTotalFilas.setEnabled(false);
		seleccionInsumo.setEnabled(false);
		campoIdPlanta.setEnabled(false);
		campoCantidadInsumo.setEnabled(false);
		campoNombrePlanta.setEnabled(false);
		campoFechaMax.setEnabled(false);
		tablaPlantasScroll.setPreferredSize(new Dimension(200, 120));
		tablaPlantasScroll.setVisible(true);
		tablaInsumosScroll.setPreferredSize(new Dimension(400, 200));
		tablaInsumosScroll.setVisible(true);
		campoFechaMax.setToolTipText("dd/mm/YYYY");
	}
	
	private void ubicarComponentes() {
		setLayout (new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
			
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(0,0, 0, 0);
		add(tablaPlantasScroll, constraints);
		
		constraints.gridy = 4;
		constraints.insets.set(20, 0, 0, 0);
		add(lblIdPlanta, constraints);
		constraints.insets.set(20, 180, 0, 0);
		add(campoIdPlanta, constraints);	
			
		constraints.gridy = 5;
		constraints.insets.set(20, 0, 0, 0);
		add(lblNombrePlanta, constraints);
		constraints.insets.set(20, 180, 0, 0);
		add(campoNombrePlanta, constraints);
			
		constraints.gridy = 6;
		constraints.insets.set(20, 0, 0, 0);
		add(lblFechaMax, constraints);
		constraints.insets.set(20, 180, 0, 0);
		campoFechaMax.setPreferredSize(new Dimension(180, 20));
		add(campoFechaMax, constraints);
			
		constraints.gridy = 7;
		constraints.insets.set(20, 0, 0, 0);
		add(lblSeleccionInsumo, constraints);
		constraints.insets.set(20, 180, 0, 0);
		seleccionInsumo.setPreferredSize(new Dimension(180,20));
		add(seleccionInsumo, constraints);
		
		constraints.gridy = 8;
		constraints.insets.set(20, 0, 0, 0);
		add(lblCantidad, constraints);
		constraints.insets.set(20, 180, 0, 0);
		add(campoCantidadInsumo,constraints);
			
		constraints.gridx = 3;
		constraints.gridy = 4;
		constraints.gridheight = 8;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(10, 30, 5, 5);
		add(tablaInsumosScroll, constraints);
		
		constraints.gridy = 9;
		constraints.insets.set(20, 0, 0, 0);
		add(btnGuardar, constraints);
		constraints.gridy = 10;
		constraints.insets.set(50, 0, 0, 0);
		add(btnCancelar,constraints);
			
		constraints.gridx = 0;	
		constraints.gridy = 9;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(20, 0, 0, 0);
		add(btnAgregar, constraints);
		constraints.insets.set(20, 200, 0, 0);
		add(btnEliminar,constraints);
		
	}
	
	public void limpiarFormulario() {
		
		this.campoCantidadInsumo.setText("");
		this.campoFechaMax.setEnabled(false);
//		this.campoFechaMax.setText("");
//		this.setSeleccionInsumo();
//		this.seleccionInsumo.setEnabled(true);
//		this.campoTotalFilas.setText("");
	
	}
	
	
	public void limpiarFormularioTodo() {
		
		this.campoCantidadInsumo.setText("");
		this.campoFechaMax.setEnabled(false);
		this.campoFechaMax.setText("");
	//	this.setSeleccionInsumo();
		this.seleccionInsumo.setEnabled(false);
		this.setCampoIdPlanta("");
		this.setCampoNombrePlanta("");
		this.setCampoFecha("");
		this.campoCantidadInsumo.setEnabled(false);
	}
	
	private void setCampoFecha(String string) {
		campoFechaMax.setText(string);
		
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
		return tablaInsumos.rowAtPoint(point);
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
//			campoTotalFilas.setText(String.valueOf(tamanioTablaActual));
		}
		else {
			tablaPlantas.setToolTipText(null);
			tablaPlantas.setEnabled(false);
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
			tablaInsumos.setToolTipText("Doble click para seleccionar un insumo");
			tablaInsumos.setEnabled(true);
//			campoTotalFilas.setText(String.valueOf(tamanioTablaActual));
		}
		else {
			tablaInsumos.setToolTipText(null);
			tablaInsumos.setEnabled(false);
//			campoTotalFilas.setText("");
		}
	}
	
	public void setValoresTablaPlantas(Integer fila, Integer id, String nombre) {
		model.setValueAt(id, fila, 0);
		model.setValueAt(nombre, fila, 1);
	}
	
	public void setValoresTablaInsumos(Integer fila, String nombre, Integer cant,Double precio) {
		model.setValueAt(nombre, fila, 0);
		model.setValueAt(cant, fila, 1);
		model.setValueAt(precio, fila, 2);
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
	
	public void addListenerBtnAgregar(ActionListener listener) {
		btnAgregar.addActionListener(listener);
	}
	
	public void addListenerCampoCantidadInsumo(KeyListener listener) {
		campoCantidadInsumo.addKeyListener(listener); 
	}
	
	public void habilitarCampos(Boolean t) {
//		campoIdPlanta.setEnabled(t);
//		campoNombrePlanta.setEnabled(t);
		campoCantidadInsumo.setEnabled(t);
		campoFechaMax.setEnabled(t);
		seleccionInsumo.setEnabled(t);
		btnAgregar.setEnabled(t);
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
	
	public String getFechaMax() {
		return campoFechaMax.getText();
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
	public void habilitarGuardar(boolean b) {
		btnGuardar.setEnabled(b);
		
	}
	
	public void noValido(Boolean fecha, Boolean insumo, Boolean cantidad) {

		if(fecha) {
			this.erroneo(campoFechaMax);
		}
		if(insumo) {
			this.erroneo(seleccionInsumo);
		}
		if(cantidad) {
			this.erroneo(campoCantidadInsumo);
		}
		
	}
	
	public void erroneo(Component componente) {
    	COLOR_FONDO_ERRONEO = new Color(255,83,77);
    	COLOR_LETRA_ERRONEA = COLOR_FONDO_ERRONEO.darker().darker();
		componente.setBackground(COLOR_FONDO_ERRONEO);
		componente.setForeground(COLOR_LETRA_ERRONEA);
	}
	
	public void normal(Component componente) {
		COLOR_LETRA = Color.WHITE;
		COLOR_FONDO_TEXTO = Color.BLACK;
		componente.setBackground(COLOR_LETRA);
		componente.setForeground(COLOR_FONDO_TEXTO);
	}
	
	public void textnormal() {
		this.normal(campoFechaMax);
		this.normal(seleccionInsumo);
		this.normal(campoCantidadInsumo);
	}

}
