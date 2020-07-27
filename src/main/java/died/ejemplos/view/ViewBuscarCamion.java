package died.ejemplos.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.time.LocalDate;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import died.ejemplos.controller.BuscarCamionController;


public class ViewBuscarCamion extends JPanel {

	
	
	
	/*
	 * Patente
- Modelo (marca y modelo)
- KM Recorridos
- Costo por KM
- Costo por Hora
- Fecha de compra
	 */
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private JLabel lPatente = new JLabel("Patente:");
	private JLabel lMarca = new JLabel("Marca:");
	private JLabel lModelo = new JLabel("Modelo:");
	private JLabel lKm = new JLabel("KM recorrido:");
	private JLabel lKmCosto = new JLabel("Costo por Km:");
	private JLabel lCostoPorHora = new JLabel("Costo por Hora:");
	private JLabel lFechaCompra = new JLabel("Fecha de Compra:");
	private JLabel ltotalFilas = new JLabel("Total de filas:");
	
	private JTextField campoPatente = new JTextField(16);
	private JTextField campoMarca = new JTextField(16);
	private JTextField campoModelo = new JTextField(16);
	private JTextField campoCostoPorKm = new JTextField(16);
	private JTextField campoCostoPorHora = new JTextField(16);
	private JTextField campoFechaCompra = new JTextField(16);
	private JTextField campoTotalFilas = new JTextField(3);
	
	private JButton btnBuscar = new JButton("BUSCAR");
	private JButton btnCancelar = new JButton("CANCELAR");
	
	private JComboBox<String> seleccionKmRecorrido = new JComboBox<String>();
	private JTable tablaCamiones = new JTable();
	private JScrollPane tablaCamionesScroll = new JScrollPane(tablaCamiones);
	private Object[][] datosTabla = {{""},{""},{""},{""},{""},{""},{""}};
	private DefaultTableModel model;
	private BuscarCamionController controller;
	
	
	
	
	
	public ViewBuscarCamion() {
		super();
		this.controller= new BuscarCamionController(this);
		inicializarComponentes();
		ubicarComponentes();
		addTablaCamiones(0);	
	}

	private void inicializarComponentes() {		
		btnBuscar.setPreferredSize(new Dimension(160, 25));
		btnCancelar.setPreferredSize(new Dimension(160, 25));
		campoPatente.setEnabled(true);
		campoMarca.setEnabled(true);
		campoModelo.setEnabled(true);
		campoCostoPorHora.setEnabled(true);
		campoCostoPorKm.setEnabled(true);
		campoFechaCompra.setEnabled(true);
		this.addKmsAnio();
		seleccionKmRecorrido.setEnabled(true);
		tablaCamiones.setEnabled(true);
		btnBuscar.setEnabled(true);
		btnCancelar.setEnabled(true);
		campoTotalFilas.setEnabled(false);
		tablaCamionesScroll.setPreferredSize(new Dimension(680, 500));	
		
		campoPatente.setToolTipText("LLL999 / LL999LL");
		campoFechaCompra.setToolTipText("dd/mm/YYYY");
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
		add(lPatente, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 10, 10, 5);
		add(campoPatente, constraints);	
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(10, 5, 10, 5);
		add(lMarca, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 10, 10, 5);
		add(campoMarca, constraints);	

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(10, 5, 10, 5);
		add(lModelo, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 10, 10, 5);
		add(campoModelo, constraints);	
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(10, 5, 10, 5);
		add(lKm, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 10, 10, 5);
		add(seleccionKmRecorrido, constraints);	

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(10, 5, 10, 5);
		add(lCostoPorHora, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 10, 10, 5);
		add(campoCostoPorHora, constraints);	
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(10, 5, 10, 5);
		add(lKmCosto, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 10, 10, 5);
		add(campoCostoPorKm, constraints);	
		
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(10, 5, 10, 5);
		add(lFechaCompra, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 10, 10, 5);
		add(campoFechaCompra, constraints);	
		
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(30, 5, 5, 5);
		add(btnBuscar, constraints);
		
		constraints.gridy = 8;
		constraints.insets.set(30, 5, 5, 5);
		add(btnCancelar, constraints);	
		
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridheight = 9;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(5, 30, 5, 5);
		add(tablaCamionesScroll, constraints);	
		
		constraints.gridx = 2;
		constraints.gridy = 9;
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
	
	public void addListenerTablaCamiones(MouseListener listener) {
		tablaCamiones.addMouseListener(listener);
	}
	
	public void addListenerCampoPatente(KeyListener listener) {
		campoPatente.addKeyListener(listener); 
	}
	
	public void addListenerCampoMarca(KeyListener listener) {
		campoMarca.addKeyListener(listener); 
	}
	
	public void addListenerCampoModelo(KeyListener listener) {
	    campoModelo.addKeyListener(listener);
	}
	
	public void addListenerCampoCostoPorHora(KeyListener listener) {
	    campoCostoPorHora.addKeyListener(listener);
	}
	
	public void addListenerCampoCostoPorKm(KeyListener listener) {
	    campoCostoPorKm.addKeyListener(listener);
	}
	
	public void addListenerCampoFechaCompra(KeyListener listener) {
	    campoFechaCompra.addKeyListener(listener);
	}
	
	public void addItemTipoCobertura(String item) {
		seleccionKmRecorrido.addItem(item);
	}

	public Integer getRowTablaCamiones(Point point) {
		return tablaCamiones.rowAtPoint(point);
	}

	public String getPatente() {
		return campoPatente.getText();
	}

	public String getMarca() {
		return campoMarca.getText();
	}

	public String getModelo() {
		return campoModelo.getText();
	}

	public String getCostoPorHora() {
		return campoCostoPorHora.getText();
	}
	
	public String getCostoPorKm() {
		return campoCostoPorKm.getText();
	}
	
	public String getFechaCompra() {
		return campoFechaCompra.getText();
	}

	public String getKmRecorrido() {
		if( seleccionKmRecorrido.getItemAt(seleccionKmRecorrido.getSelectedIndex()) == "Selecionar kilometraje") {
			return "-";
		}
		else
			return seleccionKmRecorrido.getItemAt(seleccionKmRecorrido.getSelectedIndex());
	}
	
	public void setPatente() {
		campoPatente.setText("");
	}

	public void setMarca() {
		campoMarca.setText("");
	}

	public void setModelo() {
		 campoModelo.setText("");
	}

	public void setCostoPorHora() {
		 campoCostoPorHora.setText("");
	}
	
	public void setCostoPorKm() {
		 campoCostoPorKm.setText("");
	}
	
	public void setFechaCompra() {
		 campoFechaCompra.setText("");
	}

	public void setKmRecorrido() {
		seleccionKmRecorrido.getItemAt(0);
	}
	
	public void addTablaCamiones(Integer tamanioTablaActual) {
		DefaultTableModel tableModel = new DefaultTableModel( datosTabla, tamanioTablaActual) {
			private static final long serialVersionUID = 7365551733085502818L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		model = tableModel;		
		tablaCamiones.setModel(tableModel);
		
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment( JLabel.CENTER );
		
		tablaCamiones.getColumnModel().getColumn(0).setCellRenderer(centrado);
		tablaCamiones.getColumnModel().getColumn(1).setCellRenderer(centrado);
		tablaCamiones.getColumnModel().getColumn(2).setCellRenderer(centrado);
		tablaCamiones.getColumnModel().getColumn(3).setCellRenderer(centrado);
		tablaCamiones.getColumnModel().getColumn(4).setCellRenderer(centrado);
		tablaCamiones.getColumnModel().getColumn(5).setCellRenderer(centrado);
		tablaCamiones.getColumnModel().getColumn(6).setCellRenderer(centrado);
		
		tablaCamiones.getColumnModel().getColumn(0).setPreferredWidth(120);
		tablaCamiones.getColumnModel().getColumn(1).setPreferredWidth(120);
		tablaCamiones.getColumnModel().getColumn(2).setPreferredWidth(110);
		tablaCamiones.getColumnModel().getColumn(3).setPreferredWidth(120);
		tablaCamiones.getColumnModel().getColumn(4).setPreferredWidth(120);
		tablaCamiones.getColumnModel().getColumn(5).setPreferredWidth(120);
		tablaCamiones.getColumnModel().getColumn(6).setPreferredWidth(120);
		
		tablaCamiones.getColumnModel().getColumn(0).setHeaderValue("Patente");
		tablaCamiones.getColumnModel().getColumn(1).setHeaderValue("Marca");
		tablaCamiones.getColumnModel().getColumn(2).setHeaderValue("Modelo");
		tablaCamiones.getColumnModel().getColumn(3).setHeaderValue("Km recorridos");
		tablaCamiones.getColumnModel().getColumn(4).setHeaderValue("Costo por Hora");
		tablaCamiones.getColumnModel().getColumn(5).setHeaderValue("Costo por Km");
		tablaCamiones.getColumnModel().getColumn(6).setHeaderValue("Fecha Compra");
		
		if(tamanioTablaActual > 0) {	
			tablaCamiones.setToolTipText("Doble click para seleccionar un camion");
			tablaCamiones.setEnabled(true);
			campoTotalFilas.setText(String.valueOf(tamanioTablaActual));
		}
		else {
			tablaCamiones.setToolTipText(null);
			tablaCamiones.setEnabled(false);
			campoTotalFilas.setText("");
		}
	}	
	
	public void setValoresTablaCamiones(Integer fila, String patente, String marca, String modelo, String km, Double costohs, Double costokm, LocalDate localDate) {
		model.setValueAt(patente, fila, 0);
		model.setValueAt(marca, fila, 1);
		model.setValueAt(modelo, fila, 2);
		model.setValueAt(km, fila, 3);
		model.setValueAt(costohs, fila, 4);
		model.setValueAt(costokm, fila, 5);
		model.setValueAt(localDate, fila, 6);
	}
	
	public void addKmsAnio() {
		seleccionKmRecorrido.setModel(new DefaultComboBoxModel<String>(new String[] {"Selecionar kilometraje",
				"0 - 9.999", "10.000 - 19.999", "20.000 - 29.999", "30.000 - 39.999", "40.000 - 49.999",
				"50.000 - 59.999", "60.000 - 69.999", "70.000 - 79.999", "80.000 - 89.999", "90.000 - 99.999",
				"100.00 - 109.999", "110.000 - 119.999", "120.000 - 129.999", "130.000 - 139.999", "140.000 - 149.999",
				"150.000 - 159.999", "160.000 - 169.999", "170.000 - 179.999", "180.000 - 189.999", "190.000 - 199.999",
				"200.000 - 209.999", "210.000 - 219.999", "220.000 - 229.999", "230.000 - 239.999", "240.000 - 249.999",
				"250.000 - 259.999", "260.000 - 269.999", "270.000 - 279.999", "280.000 - 289.999", "290.000 - 299.999",
				"Más de 300.000 km"
		}));
	}
	

	
}

	
	
	

