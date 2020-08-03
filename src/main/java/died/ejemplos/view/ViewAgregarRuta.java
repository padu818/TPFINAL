package died.ejemplos.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import died.ejemplos.controller.AltaCamionController;
import died.ejemplos.controller.AltaRutaController;

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
	private JLabel lblNombre = new JLabel("Nombre Planta:");
	private JLabel lblRuta = new JLabel("Ruta:");
	private JLabel lblSeparacion = new JLabel("-------------------------------------------------------------------------------------");
	private JLabel lblPlantaOrigen = new JLabel("Planta Origen:");

	private JLabel lblPlantaDestino = new JLabel("Planta Destino:");
	private JLabel lblDistanciaKm = new JLabel("Distancia en Km:");
	private JLabel lblDistanciaHs = new JLabel("Distancia en Horas:");
	private JLabel lblMaximo = new JLabel("Peso de transporte maximo en KG:");
	private JLabel ltotalFilas = new JLabel("Total de filas:");
	

	private JTextField campoNombrePlanta = new JTextField(16);
	private JTextField campoPlantaOrigen = new JTextField(16);
	private JComboBox<String> seleccionPlantaDestino = new JComboBox<String>();

	private JTextField campoDistanciaKm = new JTextField(16);
	private JTextField campoDistanciaHs = new JTextField(16);
	private JTextField campoTotalFilas = new JTextField(16);
	private JTextField campoMaximo = new JTextField(16);
	private JButton btnAceptar = new JButton("ACEPTAR");
	private JButton btnAgregarRuta = new JButton("AGREGAR RUTA");
	private JButton btnEditarRuta = new JButton("EDITAR RUTA");
	private JButton btnEliminarRuta = new JButton("ELIMINAR RUTA");
	private JButton btnGuardarRuta = new JButton("GUARDAR RUTA");
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
	//	this.ubicarComponentes();
	//	addTablaPlanta(0);
	}
	
	private void inicializarComponentes() {		
		btnAceptar.setPreferredSize(new Dimension(160, 25));
		btnCancelar.setPreferredSize(new Dimension(160, 25));
		btnGuardarRuta.setPreferredSize(new Dimension(160, 25));
		btnAgregarRuta.setPreferredSize(new Dimension(160, 25));
		btnEditarRuta.setPreferredSize(new Dimension(160, 25));
		btnEliminarRuta.setPreferredSize(new Dimension(160, 25));
		btnGuardar.setPreferredSize(new Dimension(160, 25));
		campoNombrePlanta.setEnabled(true);
		campoDistanciaHs.setEnabled(false);
		campoDistanciaKm.setEnabled(false);
		campoMaximo.setEnabled(false);
		seleccionPlantaDestino.setEnabled(false);
		campoPlantaOrigen.setEnabled(false);
		//this.addPlantas();
		seleccionPlantaDestino.setEnabled(false);
		tablaRuta.setEnabled(true);
		btnAceptar.setEnabled(true);
		btnCancelar.setEnabled(true);
		btnGuardar.setEnabled(false);
		btnGuardarRuta.setEnabled(false);
		btnEditarRuta.setEnabled(false);
		btnEliminarRuta.setEnabled(false);
		btnAgregarRuta.setEnabled(false);
		campoTotalFilas.setEnabled(false);
		tablaRutaScroll.setPreferredSize(new Dimension(680, 500));	

	}
	/*
	private void ubicarComponentes() {
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(0, 0, 0, 0);
		add(lblPatente, constraints);
		constraints.insets.set(0, 120, 0, 0);
		add(campoPatente, constraints);	
			
		constraints.gridy = 2;
		constraints.insets.set(20, 0, 0, 0);
		add(lblMarca, constraints);
		constraints.insets.set(20, 120, 0, 0);
		add(campoMarca, constraints);
			
		constraints.gridy = 3;
		constraints.insets.set(20, 0, 0, 0);
		add(lblModelo, constraints);
		constraints.insets.set(20, 120, 0, 0);
		add(campoModelo, constraints);
			
		constraints.gridy = 4;
		constraints.insets.set(20, 0, 0, 0);
		add(lblKm, constraints);
		constraints.insets.set(20, 120, 0, 0);
		seleccionKm.setPreferredSize(new Dimension(180,20));
		add(seleccionKm, constraints);;
			
		constraints.gridy = 5;
		constraints.insets.set(20, 0, 0, 0);
		add(lblCostoHs, constraints);
		constraints.insets.set(20, 120, 0, 0);
		add(campoCostoHs, constraints);
			
		constraints.gridy = 6;
		constraints.insets.set(20, 0, 0, 0);
		add(lblCostoKm, constraints);
		constraints.insets.set(20, 120, 0, 0);
		add(campoCostoKm, constraints);
			
		
		constraints.gridy = 7;
		constraints.insets.set(20, 0, 0, 0);
		add(lblFecha, constraints);
		constraints.insets.set(20, 120, 0, 0);
		campoFechaCompra.setPreferredSize(new Dimension(180, 20));
		add(campoFechaCompra, constraints);	
		
			
		constraints.gridy = 9;
		constraints.gridwidth = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(30, 0, 0, 200);
		add(btnGuardar, constraints);
		constraints.insets.set(30, 0, 0, 0);
		add(btnCancelar, constraints);
		
		
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
	*/
			
	public void addListenerBtnAceptar(ActionListener listener) {
		btnAceptar.addActionListener(listener);
	}

	public void addListenerBtnCancelar(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}
	
	public void addListenerBtnEditarRuta(ActionListener listener) {
		btnEditarRuta.addActionListener(listener);
	}

	public void addListenerBtnEliminarRuta(ActionListener listener) {
		btnEliminarRuta.addActionListener(listener);
	}
	
	public void addListenerBtnAgregarRuta(ActionListener listener) {
		btnAgregarRuta.addActionListener(listener);
	}

	public void addListenerBtnGuardarRuta(ActionListener listener) {
		btnGuardarRuta.addActionListener(listener);
	}
	
	public void addListenerBtnGuardar(ActionListener listener) {
		btnGuardar.addActionListener(listener);
	}
	
	public void addListenerTablaCamiones(MouseListener listener) {
		tablaRuta.addMouseListener(listener);
	}
	
	public void addListenerCampoDistanciaHs(KeyListener listener) {
		campoDistanciaHs.addKeyListener(listener); 
	}
	
	public void addListenerCampoDistnaciaKm(KeyListener listener) {
		campoDistanciaKm.addKeyListener(listener); 
	}
	
	
	
}
