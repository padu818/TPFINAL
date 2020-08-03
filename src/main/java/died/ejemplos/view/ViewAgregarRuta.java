package died.ejemplos.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import died.ejemplos.controller.AltaCamionController;
import died.ejemplos.controller.AltaRutaController;
import died.ejemplos.dominio.Planta;

public class ViewAgregarRuta extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/*
	 * Planta Origen
o Planta Destino
o Distancia en KM
o Duración estimada en Horas
o Peso máximo en KG que se pueden transportar (asumimos que es el peso máximo por día que soporta la ruta)
	 */
//	private JLabel lblNombre = new JLabel("Nombre Planta:");
//	private JLabel lblRuta = new JLabel("Ruta:");
//	private JLabel lblSeparacion = new JLabel("-------------------------------------------------------------------------------------");
	private JLabel lblPlantaOrigen = new JLabel("Planta Origen:");

	private JLabel lblPlantaDestino = new JLabel("Planta Destino:");
	private JLabel lblDistanciaKm = new JLabel("Distancia en Km:");
	private JLabel lblDistanciaHs = new JLabel("Distancia en Horas:");
	private JLabel lblMaximo = new JLabel("Peso de transporte maximo en KG:");
	private JLabel ltotalFilas = new JLabel("Total de filas:");
	

//	private JTextField campoNombrePlanta = new JTextField(16);
	private JComboBox<String> seleccionPlantaOrigen = new JComboBox<String>();
	private JComboBox<String> seleccionPlantaDestino = new JComboBox<String>();

	private JTextField campoDistanciaKm = new JTextField(16);
	private JTextField campoDistanciaHs = new JTextField(16);
	private JTextField campoTotalFilas = new JTextField(16);
	private JTextField campoMaximo = new JTextField(16);
//	private JButton btnAceptar = new JButton("ACEPTAR");
//	private JButton btnAgregarRuta = new JButton("AGREGAR RUTA");
//	private JButton btnEditarRuta = new JButton("EDITAR RUTA");
//	private JButton btnEliminarRuta = new JButton("ELIMINAR RUTA");
//	private JButton btnGuardarRuta = new JButton("GUARDAR RUTA");
	private JButton btnGuardar = new JButton("GUARDAR");
	private JButton btnCancelar = new JButton("CANCELAR");
	private JTable tablaRuta = new JTable();
	private JScrollPane tablaRutaScroll = new JScrollPane(tablaRuta);
	private Object[][] datosTabla = {{""},{""},{""},{""},{""}};
	private DefaultTableModel model;
	private AltaRutaController controller;
	
	public ViewAgregarRuta(){
		super();
		this.controller= new AltaRutaController(this);
		this.inicializarComponentes();
		this.ubicarComponentes();
		addTablaRuta(0);
	}
	
	private void inicializarComponentes() {		
//		btnAceptar.setPreferredSize(new Dimension(160, 25));
		btnCancelar.setPreferredSize(new Dimension(160, 25));
//		btnGuardarRuta.setPreferredSize(new Dimension(160, 25));
//		btnAgregarRuta.setPreferredSize(new Dimension(160, 25));
//		btnEditarRuta.setPreferredSize(new Dimension(160, 25));
//		btnEliminarRuta.setPreferredSize(new Dimension(160, 25));
		btnGuardar.setPreferredSize(new Dimension(160, 25));
		campoDistanciaHs.setEnabled(true);
		campoDistanciaKm.setEnabled(true);
		campoMaximo.setEnabled(true);
		seleccionPlantaDestino.setPreferredSize(new Dimension(180,20));
		seleccionPlantaOrigen.setPreferredSize(new Dimension(180,20));
		seleccionPlantaDestino.setEnabled(true);
		seleccionPlantaOrigen.setEnabled(true);
//		this.addPlantasOrigen();
//		this.addPlantasDestino();
		seleccionPlantaDestino.setEnabled(false);
		tablaRuta.setEnabled(true);
		btnCancelar.setEnabled(true);
		btnGuardar.setEnabled(false);
	//	btnEditarRuta.setEnabled(false);
	//	btnEliminarRuta.setEnabled(false);
	//	btnAgregarRuta.setEnabled(false);
		campoTotalFilas.setEnabled(false);
		tablaRutaScroll.setPreferredSize(new Dimension(680, 500));	

	}
	
	private void ubicarComponentes() {
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(0, 0, 0, 0);
		add(lblPlantaOrigen, constraints);
		constraints.insets.set(0, 120, 0, 0);
		add(seleccionPlantaOrigen, constraints);	
			
		constraints.gridy = 2;
		constraints.insets.set(20, 0, 0, 0);
		add(lblPlantaDestino, constraints);
		constraints.insets.set(20, 120, 0, 0);
		add(seleccionPlantaDestino, constraints);
			
		constraints.gridy = 3;
		constraints.insets.set(20, 0, 0, 0);
		add(lblDistanciaHs, constraints);
		constraints.insets.set(20, 120, 0, 0);
		add(campoDistanciaHs, constraints);
			
		constraints.gridy = 4;
		constraints.insets.set(20, 0, 0, 0);
		add(lblDistanciaKm, constraints);
		constraints.insets.set(20, 120, 0, 0);
		
		add(campoDistanciaKm, constraints);;
			
		constraints.gridy = 5;
		constraints.insets.set(20, 0, 0, 0);
		add(lblMaximo, constraints);
		constraints.insets.set(20, 120, 0, 0);
		add(campoMaximo, constraints);
		
			
		constraints.gridy = 9;
		constraints.gridwidth = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(30, 0, 0, 200);
		add(btnGuardar, constraints);
		constraints.insets.set(30, 0, 0, 0);
		add(btnCancelar, constraints);
		
		
		constraints.gridx = 7;
		constraints.gridy = 0;
		constraints.gridheight = 9;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(5, 30, 5, 5);
		add(tablaRutaScroll, constraints);	
		
		constraints.gridx = 2;
		constraints.gridy = 9;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 5, 5, 5);
		add(campoTotalFilas, constraints);	
		constraints.insets.set(5, 5, 5, 44);
		add(ltotalFilas, constraints);
	}

	
	
	
	
	public String getPlantaOrigen() {
		if( seleccionPlantaOrigen.getItemAt(seleccionPlantaOrigen.getSelectedIndex()) == "Selecionar Planta") {
			return "-";
		}
		else
			return seleccionPlantaOrigen.getItemAt(seleccionPlantaOrigen.getSelectedIndex());
	}
	
	public Integer getIndexOrigen() {
		if( seleccionPlantaOrigen.getItemAt(seleccionPlantaOrigen.getSelectedIndex()) == "Selecionar Planta") {
			return -1;
		}
		else
			return seleccionPlantaOrigen.getSelectedIndex();
	}
	
	public String getPlantaDestino() {
		if( seleccionPlantaDestino.getItemAt(seleccionPlantaDestino.getSelectedIndex()) == "Selecionar Planta") {
			return "-";
		}
		else
			return seleccionPlantaDestino.getItemAt(seleccionPlantaDestino.getSelectedIndex());
	}
	
	public String getDistanciaKm() {
		return campoDistanciaKm.getText();
	}
	public String getDistanciaHs() {
		return campoDistanciaHs.getText();
	}
	public String getMaximo() {
		return campoMaximo.getText();
	}
	
	
//	public void addListenerBtnAceptar(ActionListener listener) {
//		btnAceptar.addActionListener(listener);
//	}

	public void addListenerBtnCancelar(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}
	
//	public void addListenerBtnEditarRuta(ActionListener listener) {
//		btnEditarRuta.addActionListener(listener);
//	}
//
//	public void addListenerBtnEliminarRuta(ActionListener listener) {
//		btnEliminarRuta.addActionListener(listener);
//	}
//	
//	public void addListenerBtnAgregarRuta(ActionListener listener) {
//		btnAgregarRuta.addActionListener(listener);
//	}
//
//	public void addListenerBtnGuardarRuta(ActionListener listener) {
//		btnGuardarRuta.addActionListener(listener);
//	}
	
	public void addListenerBtnGuardar(ActionListener listener) {
		btnGuardar.addActionListener(listener);
	}
	
	public void addListenerTablaCamiones(MouseListener listener) {
		tablaRuta.addMouseListener(listener);
	}
	
	public void addListenerCampoDistanciaHs(KeyListener listener) {
		campoDistanciaHs.addKeyListener(listener); 
	}
	
	public void addListenerCampoDistanciaKm(KeyListener listener) {
		campoDistanciaKm.addKeyListener(listener); 
	}
	
	public void addListenerCampoMaximo(KeyListener listener) {
		campoMaximo.addKeyListener(listener); 
	}
	
	
	public void addTablaRuta(Integer tamanioTablaActual) {
		DefaultTableModel tableModel = new DefaultTableModel( datosTabla, tamanioTablaActual) {
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
		tablaRuta.getColumnModel().getColumn(1).setCellRenderer(centrado);
		tablaRuta.getColumnModel().getColumn(2).setCellRenderer(centrado);
		tablaRuta.getColumnModel().getColumn(3).setCellRenderer(centrado);
		tablaRuta.getColumnModel().getColumn(4).setCellRenderer(centrado);
	
		
		tablaRuta.getColumnModel().getColumn(0).setPreferredWidth(120);
		tablaRuta.getColumnModel().getColumn(1).setPreferredWidth(120);
		tablaRuta.getColumnModel().getColumn(2).setPreferredWidth(110);
		tablaRuta.getColumnModel().getColumn(3).setPreferredWidth(120);
		tablaRuta.getColumnModel().getColumn(4).setPreferredWidth(120);

		
		tablaRuta.getColumnModel().getColumn(0).setHeaderValue("Planta origen");
		tablaRuta.getColumnModel().getColumn(1).setHeaderValue("Planta destino");
		tablaRuta.getColumnModel().getColumn(2).setHeaderValue("Distancia Hs");
		tablaRuta.getColumnModel().getColumn(3).setHeaderValue("Distancia Km");
		tablaRuta.getColumnModel().getColumn(4).setHeaderValue("Maximo transporte");
		
		
		if(tamanioTablaActual > 0) {	
//			tablaRuta.setToolTipText("Doble click para seleccionar una planta");
//			tablaRuta.setEnabled(true);
			campoTotalFilas.setText(String.valueOf(tamanioTablaActual));
		}
		else {
			tablaRuta.setToolTipText(null);
			tablaRuta.setEnabled(false);
			campoTotalFilas.setText("");
		}
	}	
	
	public void setValoresTablaPlanta(Integer fila, String origen, String destino, String costohs, Double costokm, Double maximo) {
		model.setValueAt(origen, fila, 0);
		model.setValueAt(destino, fila, 1);
		model.setValueAt(costokm, fila, 2);
		model.setValueAt(costohs, fila, 3);
		model.setValueAt(maximo, fila, 4);

	}

	public void addOrigen(List<Planta> aux) {
		String[] a = new String[aux.size()];
		int i =0;
		for(Planta b : aux) {
			a[i] = b.getNombre();
			i++;
		}
		seleccionPlantaOrigen.setModel(new DefaultComboBoxModel<String>(a
		));
		
	}

	public void addDestino(List<Planta> aux) {
		String[] a = new String[aux.size()];
		int i =0;
		for(Planta b : aux) {
			a[i] = b.getNombre();
			i++;
		}
		seleccionPlantaDestino.setModel(new DefaultComboBoxModel<String>(a
		));
		
	}
	
	
}
