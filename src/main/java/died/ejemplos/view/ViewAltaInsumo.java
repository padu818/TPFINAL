package died.ejemplos.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

import died.ejemplos.controller.AltaInsumoController;

public class ViewAltaInsumo extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private static Color COLOR_FONDO_TEXTO; 
	private static Color COLOR_LETRA;
	private static Color COLOR_LETRA_ERRONEA;
	private static Color COLOR_FONDO_ERRONEO;

	//private JLabel lblTitulo = new JLabel("Administracion de insumos:");

	private JLabel lblNombre = new JLabel("Nombre:");
	private JLabel lblDescripcion = new JLabel("Descripcion:");
	private JLabel lblCosto = new JLabel("Costo:");
	private JLabel lblUnidadMedida = new JLabel("UnidadMedida:");
	private JLabel lblTipo = new JLabel("Tipo:");
	private JLabel lblPeso = new JLabel("Peso:");
	private JLabel lblDensidad = new JLabel("Densidad:");
	
	
	private JTextField campoNombre = new JTextField(16);
	private JTextField campoDescripcion = new JTextField(16);
	private JTextField campoCosto = new JTextField(16);
	private JComboBox<String> seleccionUnidadMedida = new JComboBox<String>();
	private JComboBox<String> seleccionTipo = new JComboBox<String>();
	private JTextField campoPeso = new JTextField(16);
	private JTextField campoDensidad = new JTextField(16);
	private JButton btnGuardar = new JButton("GUARDAR");
	private JButton btnCancelar = new JButton("CANCELAR");
	private AltaInsumoController controller; 
	
	public ViewAltaInsumo(){
		super();
		this.controller= new AltaInsumoController(this);
		this.ubicarComponentes();
		this.inicializarComponentes();
	}
	
	private void inicializarComponentes() {		
		btnGuardar.setPreferredSize(new Dimension(160, 25));
		btnCancelar.setPreferredSize(new Dimension(160, 25));
		campoNombre.setEnabled(true);
		campoDescripcion.setEnabled(true);
		campoCosto.setEnabled(true);
		campoPeso.setEnabled(true);
		campoDensidad.setEnabled(true);
		this.addUnidadMedida();
		this.addTipo();
		seleccionUnidadMedida.setEnabled(true);
		seleccionTipo.setEnabled(true);
		btnGuardar.setEnabled(true);
		btnCancelar.setEnabled(true);
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
		add(lblNombre, constraints);
		constraints.insets.set(0, 120, 0, 0);
		add(campoNombre, constraints);	
			
		constraints.gridy = 2;
		constraints.insets.set(20, 0, 0, 0);
		add(lblDescripcion, constraints);
		constraints.insets.set(20, 120, 0, 0);
		add(campoDescripcion, constraints);
			
		constraints.gridy = 3;
		constraints.insets.set(20, 0, 0, 0);
		add(lblCosto, constraints);
		constraints.insets.set(20, 120, 0, 0);
		add(campoCosto, constraints);
			
		constraints.gridy = 4;
		constraints.insets.set(20, 0, 0, 0);
		add(lblUnidadMedida, constraints);
		constraints.insets.set(20, 120, 0, 0);
		seleccionUnidadMedida.setPreferredSize(new Dimension(180,20));
		add(seleccionUnidadMedida, constraints);
		
		constraints.gridy = 5;
		constraints.insets.set(20, 0, 0, 0);
		add(lblTipo, constraints);
		constraints.insets.set(20, 120, 0, 0);
		seleccionTipo.setPreferredSize(new Dimension(180,20));
		add(seleccionTipo, constraints);
			
		constraints.gridy = 6;
		constraints.insets.set(20, 0, 0, 0);
		add(lblPeso, constraints);
		constraints.insets.set(20, 120, 0, 0);
		add(campoPeso, constraints);
			
		constraints.gridy = 7;
		constraints.insets.set(20, 0, 0, 0);
		add(lblDensidad, constraints);
		constraints.insets.set(20, 120, 0, 0);
		add(campoDensidad, constraints);
			
		constraints.gridy = 9;
		constraints.gridwidth = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(30, 0, 0, 200);
		add(btnGuardar, constraints);
		constraints.insets.set(30, 0, 0, 0);
		add(btnCancelar, constraints);
		}
	
	public void limpiarFormulario() {
		this.campoNombre.setText("");
		this.campoDescripcion.setText("");
		this.campoCosto.setText("");
		this.setSeleccionUnidadMedida();
		this.setSeleccionTipo();
		this.campoPeso.setText("");
		this.campoDensidad.setText("");
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

	public void addListenerBtnCancelar(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}

	public void noValido(Boolean nombre, Boolean descripcion, Boolean costo, Boolean unidadMedida, Boolean tipo, Boolean peso, Boolean densidad) {

		if(nombre) {
			this.erroneo(campoNombre);
		}
		if(descripcion) {
			this.erroneo(campoDescripcion);
		}
		if(costo) {
			this.erroneo(campoCosto);
		}
		if(unidadMedida) {
			this.erroneo(seleccionUnidadMedida);
		}
		if(tipo) {
			this.erroneo(seleccionTipo);
		}
		if(peso) {
			this.erroneo(campoPeso);
		}
		if(densidad) {
			this.erroneo(campoDensidad);
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
		this.normal(campoNombre);
		this.normal(campoDescripcion);
		this.normal(campoCosto);
		this.normal(seleccionUnidadMedida);
		this.normal(seleccionTipo);
		this.normal(campoPeso);
		this.normal(campoDensidad);
		}
	
	public String getSeleccionUnidadMedida() {
		if( seleccionUnidadMedida.getItemAt(seleccionUnidadMedida.getSelectedIndex()) == "Selecionar unidad de medida") {
			return "-";
		}
		else
			return seleccionUnidadMedida.getItemAt(seleccionUnidadMedida.getSelectedIndex());
	}
	
	public void setSeleccionUnidadMedida() {
		seleccionUnidadMedida.removeAll();
		this.addUnidadMedida();
	}
	
	public void setSeleccionUnidadMedida(String text) {
		seleccionUnidadMedida.removeAll();
		seleccionUnidadMedida.setModel(new DefaultComboBoxModel<String>(new String[] {text}));
	}
	
	public void addUnidadMedida() {
		seleccionUnidadMedida.setModel(new DefaultComboBoxModel<String>(new String[] {"Selecionar unidad de medida",
				"KILO", "PIEZA", "GRAMO", "METRO", "LITRO", "M3", "M2"
		}));
	}
	
	public String getSeleccionTipo() {
		if( seleccionTipo.getItemAt(seleccionTipo.getSelectedIndex()) == "Selecionar tipo de insumo") {
			return "-";
		}
		else
			return seleccionTipo.getItemAt(seleccionTipo.getSelectedIndex());
	}
	
	public void setSeleccionTipo() {
		seleccionTipo.removeAll();
		this.addTipo();
	}
	
	public void setSeleccionTipo(String text) {
		seleccionTipo.removeAll();
		seleccionTipo.setModel(new DefaultComboBoxModel<String>(new String[] {text}));
	}
	
	public void addTipo() {
		seleccionTipo.setModel(new DefaultComboBoxModel<String>(new String[] {"Selecionar tipo de insumo",
				"GENERAL", "LIQUIDO"
		}));
	}
	
	public String getCampoNombre() {
		return campoNombre.getText();
	}
	
	public void setCampoNombre(String nombre) {
		campoNombre.setText(nombre);
	}
	
	public String getCampoDescripcion() {
		return campoDescripcion.getText();
	}
	
	public void setCampoDescripcion(String descripcion) {
		campoDescripcion.setText(descripcion);
	}
	
	public String getCampoCosto() {
		return campoCosto.getText();
	}
	
	public void setCampoCosto(String costo) {
		campoCosto.setText(costo);
	}
	
	public String getCampoPeso() {
		return campoPeso.getText();
	}
	
	public void setCampoPeso(String peso) {
		campoPeso.setText(peso);
	}
	
	public String getCampoDensidad() {
		return campoDensidad.getText();
	}
	
	public void setCampoDensidad(String densidad) {
		campoDensidad.setText(densidad);
	}
	
}
