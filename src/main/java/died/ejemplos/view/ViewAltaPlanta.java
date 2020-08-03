package died.ejemplos.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


import died.ejemplos.controller.AltaPlantaController;

public class ViewAltaPlanta extends JPanel{
	
	
	private JLabel lblNombre = new JLabel("Nombre Planta:");

	private JTextField campoNombrePlanta = new JTextField(16);
	private JButton btnGuardar = new JButton("GUARDAR");
	private JButton btnCancelar = new JButton("CANCELAR");
	private AltaPlantaController controller;
	
	public ViewAltaPlanta(){
		super();
		this.controller= new AltaPlantaController(this);
		this.ubicarComponentes();
		this.inicializarComponentes();
	}
	
	private void inicializarComponentes() {		
		btnGuardar.setPreferredSize(new Dimension(160, 25));
		btnCancelar.setPreferredSize(new Dimension(160, 25));
		campoNombrePlanta.setEnabled(true);
		
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
		add(campoNombrePlanta, constraints);	
			
		constraints.gridy = 5;
		constraints.gridwidth = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(30, 0, 0, 200);
		add(btnGuardar, constraints);
		constraints.insets.set(30, 0, 0, 0);
		add(btnCancelar, constraints);
		}


	public String getCampoNombrePlanta() {
		return campoNombrePlanta.getText();
	}
	
	public void limpiarFormulario() {
		this.campoNombrePlanta.setText("");

	}
	
	public void setCampoNombrePlanta(String patente) {
		campoNombrePlanta.setText(patente);
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
}
