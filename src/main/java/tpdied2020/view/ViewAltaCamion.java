package tpdied2020.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import tpdied2020.controller.AltaCamionController;
import tpdied2020.dominio.Planta;


public class ViewAltaCamion extends JPanel{
	

	private static final long serialVersionUID = 1L;
	
	private static Color COLOR_FONDO_TEXTO; 
	private static Color COLOR_LETRA;
	private static Color COLOR_LETRA_ERRONEA;
	private static Color COLOR_FONDO_ERRONEO;
	private DateFormat df = new SimpleDateFormat("dd/mm/yyyy");

	private JLabel lblPatente = new JLabel("Patente:");
	private JLabel lblModelo = new JLabel("Modelo:");
	private JLabel lblMarca = new JLabel("Marca:");
	private JLabel lblFecha = new JLabel("Fecha:");
	private JLabel lblKm = new JLabel("KMs:");
	private JLabel lblCostoHs = new JLabel("Costo por Hora:");
	private JLabel lblCostoKm = new JLabel("Costo por Km:");
	private JLabel lblPlanta = new JLabel("Planta:");
	
	
	private JFormattedTextField campoFechaCompra = new JFormattedTextField(df);	
	private JTextField campoCostoHs = new JTextField(16);
	private JTextField campoCostoKm = new JTextField(16);
	private JTextField campoModelo = new JTextField(16);
	private JComboBox<String> seleccionKm = new JComboBox<String>();
	private JTextField campoMarca = new JTextField(16);
	private JTextField campoPatente = new JTextField(16);
	private JButton btnGuardar = new JButton("GUARDAR");
	private JButton btnCancelar = new JButton("CANCELAR");
	private AltaCamionController controller;
	private JComboBox<String> seleccionPlanta = new JComboBox<String>();
	
	public ViewAltaCamion(){
		super();
		this.controller= new AltaCamionController(this);
		this.ubicarComponentes();
		this.inicializarComponentes();
	}
	
	
	
	private void inicializarComponentes() {		
		btnGuardar.setPreferredSize(new Dimension(160, 25));
		btnCancelar.setPreferredSize(new Dimension(160, 25));
		campoPatente.setEnabled(true);
		campoMarca.setEnabled(true);
		campoModelo.setEnabled(true);
		campoCostoHs.setEnabled(true);
		campoCostoKm.setEnabled(true);
		campoFechaCompra.setEnabled(true);
		this.addKms();
		seleccionKm.setEnabled(true);
		btnGuardar.setEnabled(true);
		btnCancelar.setEnabled(true);
		campoPatente.setToolTipText("LLL999 / LL999LL");
		campoFechaCompra.setToolTipText("dd/mm/YYYY");
		seleccionPlanta.setEnabled(true);
	}
	
	private void ubicarComponentes() {
		setLayout (new GridBagLayout());
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
		
		constraints.gridy = 8;
		constraints.insets.set(20, 0, 0, 0);
		add(lblPlanta, constraints);
		constraints.insets.set(20, 120, 0, 0);
		seleccionPlanta.setPreferredSize(new Dimension(180, 20));
		add(seleccionPlanta, constraints);	
		
			
		constraints.gridy = 9;
		constraints.gridwidth = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(30, 0, 0, 200);
		add(btnGuardar, constraints);
		constraints.insets.set(30, 0, 0, 0);
		add(btnCancelar, constraints);
		}

	
	public void limpiarFormulario() {
		this.campoPatente.setText("");
		this.campoModelo.setText("");
		this.campoMarca.setText("");
		this.campoCostoHs.setText("");
		this.campoCostoKm.setText("");
		this.setSeleccionKm();
		this.setSeleccionPlanta();
		this.campoFechaCompra.setText("");
	}

	
	public String getCampoFechaCompra() {
		return campoFechaCompra.getText();
	}

	public void setCampoFechaCompra(String fecha) {
		this.campoFechaCompra.setText(fecha);
	}

	public String getCampoModelo() {
		return campoModelo.getText();
	}

	public void setCampoModelo(String modelo) {
		this.campoModelo.setText(modelo);
	}

	public Integer getIndexSeleccionPlanta() {
		if( seleccionPlanta.getItemAt(seleccionPlanta.getSelectedIndex()) == "Seleccionar Planta") {
			return -1;
		}
		else
			return seleccionPlanta.getSelectedIndex()-1;
	}
	
	
	
	public String getCampoMarca() {
		return campoMarca.getText();
	}

	public void setCampoMarca(String marca) {
		this.campoMarca.setText(marca);
	}

	public String getCampoPatente() {
		return campoPatente.getText();
	}


	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
			    detalle,titulo,
			    JOptionPane.ERROR_MESSAGE);
	}

		public void addListenerBtnGuardar(ActionListener listener) {
		btnGuardar.addActionListener(listener);
	}
		
	public void addListenerCampoPatente(KeyListener listener) { 
		   campoPatente.addKeyListener(listener);
	}

	public void addListenerBtnCancelar(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}
	
	public void addListenerCampoCostoKm(KeyListener listener) { 
		   campoCostoKm.addKeyListener(listener);
	}
	public void addListenerCampoCostoHs(KeyListener listener) { 
		   campoCostoHs.addKeyListener(listener);
	}

	public String getCampoCostoHs() {
		return campoCostoHs.getText();
	}

	public void setCampoCostoHs(String costohs) {
		this.campoCostoHs.setText(costohs);
	}

	public String getCampoCostoKm() {
		return campoCostoKm.getText();
	}

	public void setCampoCostoKm(String costokm) {
		this.campoCostoKm.setText(costokm);
	}

	public void noValido(Boolean patente, Boolean km, Boolean costokm, Boolean fecha, Boolean marca, Boolean modelo, Boolean costohs, Boolean planta) {

		if(patente) {
			this.erroneo(campoPatente);
		}
		if(km) {
			this.erroneo(seleccionKm);
		}
		if(costokm) {
			this.erroneo(campoCostoKm);
		}
		if(costohs) {
			this.erroneo(campoCostoHs);
		}
		if(marca) {
			this.erroneo(campoMarca);
		}
		if(modelo) {
			this.erroneo(campoModelo);
		}
		if(fecha) {
			this.erroneo(campoFechaCompra);
		}
		if(planta) {
			this.erroneo(seleccionPlanta);
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
			this.normal(campoPatente);
			this.normal(seleccionKm);
			this.normal(campoCostoKm);
			this.normal(campoCostoHs);
			this.normal(campoMarca);
			this.normal(campoModelo);
			this.normal(campoFechaCompra);
			this.normal(seleccionPlanta);
	}

	public String getSeleccionKm() {
		if( seleccionKm.getItemAt(seleccionKm.getSelectedIndex()) == "Seleccionar kilometraje") {
			return "-";
		}
		else
			return seleccionKm.getItemAt(seleccionKm.getSelectedIndex());
	}

	public void setSeleccionKm() {
		seleccionKm.removeAll();
		this.addKms();
	}
	
	public void setSeleccionKm(String text) {
		seleccionKm.removeAll();
		seleccionKm.setModel(new DefaultComboBoxModel<String>(new String[] {text}));
	}
	
	public void addKms() {
		seleccionKm.setModel(new DefaultComboBoxModel<String>(new String[] {"Seleccionar kilometraje",
				"0 - 9.999", "10.000 - 19.999", "20.000 - 29.999", "30.000 - 39.999", "40.000 - 49.999",
				"50.000 - 59.999", "60.000 - 69.999", "70.000 - 79.999", "80.000 - 89.999", "90.000 - 99.999",
				"100.00 - 109.999", "110.000 - 119.999", "120.000 - 129.999", "130.000 - 139.999", "140.000 - 149.999",
				"150.000 - 159.999", "160.000 - 169.999", "170.000 - 179.999", "180.000 - 189.999", "190.000 - 199.999",
				"200.000 - 209.999", "210.000 - 219.999", "220.000 - 229.999", "230.000 - 239.999", "240.000 - 249.999",
				"250.000 - 259.999", "260.000 - 269.999", "270.000 - 279.999", "280.000 - 289.999", "290.000 - 299.999",
				"Más de 300.000 km"
		}));
	}
	
	public String getSeleccionPlanta() {
		if( seleccionPlanta.getItemAt(seleccionPlanta.getSelectedIndex()) == "Seleccionar Planta") {
			return "-";
		}
		else
			return seleccionPlanta.getItemAt(seleccionPlanta.getSelectedIndex());
	}
	
	public void setSeleccionPlanta() {
		seleccionPlanta.removeAll();
	}
	
	public void setSeleccionPlanta(String text) {
		seleccionPlanta.removeAll();
		seleccionPlanta.setModel(new DefaultComboBoxModel<String>(new String[] {text}));
	}
	
	public void addSeleccionPlanta(List<Planta> aux) {
		String[] a = new String[aux.size()+1];
		int i =1;
		a[0]= "Seleccionar Planta";
		for(Planta b : aux) {
			a[i] = b.getNombre();
			i++;
		}
		seleccionPlanta.setModel(new DefaultComboBoxModel<String>(a
		));
		
	}

	public void setCampoPatente(String patente) {
		campoPatente.setText(patente);
	}
}
