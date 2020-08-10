package died.ejemplos.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import died.ejemplos.controller.AnalisisController;
import died.ejemplos.dominio.Planta;
import died.ejemplos.gui.ayuda.GrafoPlanta;

public class ViewAnalisisFlujoMax extends JPanel{
	
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private JLabel lblPlantaOrigen = new JLabel("Planta Origen:");
	private JLabel lblPlantaDestino = new JLabel("Planta Destino:");
	
	private JComboBox<String> seleccionPlantaOrigen = new JComboBox<String>();
	private JComboBox<String> seleccionPlantaDestino = new JComboBox<String>();
	
	private JButton btnAceptar = new JButton("ACEPTAR");
	private JButton btnCancelar = new JButton("CANCELAR");
	
	private JLabel lblFlujo = new JLabel("Flujo Maximo:");
	private JTextField campoMaximo = new JTextField(16);
	
	private AnalisisController controller;
	
	public ViewAnalisisFlujoMax(GrafoPlanta p){
		super();
		this.controller= new AnalisisController(this,p);
		this.inicializarComponentes();
		this.ubicarComponentes();
	}
	
	private void inicializarComponentes() {		
		btnCancelar.setPreferredSize(new Dimension(160, 25));
		btnAceptar.setPreferredSize(new Dimension(160, 25));
		campoMaximo.setEnabled(false);
		campoMaximo.setVisible(true);
		seleccionPlantaDestino.setPreferredSize(new Dimension(180,20));
		seleccionPlantaOrigen.setPreferredSize(new Dimension(180,20));
		seleccionPlantaDestino.setEnabled(true);
		seleccionPlantaOrigen.setEnabled(true);
		seleccionPlantaDestino.setEnabled(true);
		btnCancelar.setEnabled(true);
		btnAceptar.setEnabled(true);

	}
	
	private void ubicarComponentes() {
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(0, 0, 0, 0);
		add(lblPlantaOrigen, constraints);
		constraints.insets.set(0, 200, 0, 0);
		add(seleccionPlantaOrigen, constraints);	
			
		
	//	constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets.set(20, 0, 0, 0);
		add(lblPlantaDestino, constraints);
		constraints.insets.set(20, 200, 0, 0);
		add(seleccionPlantaDestino, constraints);
			
	
			
		constraints.gridy = 3;
		constraints.insets.set(20, 0, 0, 0);
		add(lblFlujo, constraints);
		constraints.insets.set(20, 200, 0, 0);
		add(campoMaximo, constraints);
		
		constraints.gridy = 5;
		constraints.gridwidth = 4;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(30, 0, 0, 0);
		add(btnAceptar, constraints);
		constraints.insets.set(30, 200, 0, 0);
		add(btnCancelar, constraints);

	}
	
	
	public String getPlantaOrigen() {
		if( seleccionPlantaOrigen.getItemAt(seleccionPlantaOrigen.getSelectedIndex()) == "Seleccionar Planta") {
			return "Seleccionar Planta";
		}
		else
			return seleccionPlantaOrigen.getItemAt(seleccionPlantaOrigen.getSelectedIndex());
	}
	
	public Integer getIndexOrigen() {
		if( seleccionPlantaOrigen.getItemAt(seleccionPlantaOrigen.getSelectedIndex()) == "Seleccionar Planta") {
			return -1;
		}
		else
			return seleccionPlantaOrigen.getSelectedIndex()-1;
	}
	
	public String getPlantaDestino() {
		if( seleccionPlantaDestino.getItemAt(seleccionPlantaDestino.getSelectedIndex()) == "Seleccionar Planta") {
			return "Seleccionar Planta";
		}
		else
			return seleccionPlantaDestino.getItemAt(seleccionPlantaDestino.getSelectedIndex());
	}
	
	
	public void addListenerBtnCancelar(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}
	
	
	public void addListenerBtnAceptar(ActionListener listener) {
		btnAceptar.addActionListener(listener);
	}
	
	public void addOrigen(List<Planta> aux) {
		String[] a = new String[aux.size()+1];
		a[0] = "Seleccionar Planta";
		int i =1;
		for(Planta b : aux) {
			a[i] = b.getNombre();
			i++;
		}
		seleccionPlantaOrigen.setModel(new DefaultComboBoxModel<String>(a
		));
		
	}

	public void addDestino(List<Planta> aux) {
		String[] a = new String[aux.size()+1];
		a[0] = "Seleccionar Planta";
		int i =1;
		for(Planta b : aux) {
			a[i] = b.getNombre();
			i++;
		}
		seleccionPlantaDestino.setModel(new DefaultComboBoxModel<String>(a
		));
		
	}

	public int getIndexDestino() {
		if( seleccionPlantaDestino.getItemAt(seleccionPlantaDestino.getSelectedIndex()) == "Selecionar Planta") {
			return -1;
		}
		else
			return seleccionPlantaDestino.getSelectedIndex()-1;
	}
	
	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
			    detalle,titulo,
			    JOptionPane.ERROR_MESSAGE);
	}
	public void limpiarFormulario() {
		
		this.seleccionPlantaDestino.setToolTipText("");
		this.seleccionPlantaOrigen.setToolTipText("");
		this.campoMaximo.setText("");

	
	}

	public void setCampoMaximo(String string) {
		campoMaximo.setText(string);
	}
}
