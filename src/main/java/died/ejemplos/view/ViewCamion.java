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

import died.ejemplos.controller.AltaCamionController;
import died.ejemplos.controller.BuscarCamionController2;
import died.ejemplos.dominio.Camion;

public class ViewCamion extends JPanel{
	
		
		/**
		 * 
		 */
		
		private DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		private JLabel lblID = new JLabel("ID:");
		private JLabel lblPatente = new JLabel("Patente:");
		private JLabel lblModelo = new JLabel("Modelo:");
		private JLabel lblMarca = new JLabel("Marca:");
		private JLabel lblFecha = new JLabel("Fecha:");
		private JLabel lblKm = new JLabel("KMs:");
		private JLabel lblCostoHs = new JLabel("Costo por Hora:");
		private JLabel lblCostoKm = new JLabel("Costo por Km:");
		
		private JTextField campoID = new JTextField(16);
		private JFormattedTextField campoFechaCompra = new JFormattedTextField(df);	
		private JTextField campoCostoHs = new JTextField(16);
		private JTextField campoCostoKm = new JTextField(16);
		private JTextField campoModelo = new JTextField(16);
		private JComboBox<String> seleccionKm = new JComboBox<String>();
		private JTextField campoMarca = new JTextField(16);
		private JTextField campoPatente = new JTextField(16);
		private JTextField campoSeleccionKm = new JTextField(16);
		private JButton btnGuardar = new JButton("GUARDAR");
		private JButton btnEditar = new JButton("EDITAR");
		private JButton btnEliminar = new JButton("ELIMINAR");
		private JButton btnVolver = new JButton("VOLVER");
		private BuscarCamionController2 controller;
		
		public ViewCamion(Camion c,JFrame v){
			super();
			this.controller= new BuscarCamionController2(this, c, v);
			this.ubicarComponentes();
			this.inicializarComponentes();
		}
		
		private void inicializarComponentes() {		
			btnEditar.setPreferredSize(new Dimension(160, 25));
			btnEliminar.setPreferredSize(new Dimension(160, 25));
			btnVolver.setPreferredSize(new Dimension(160, 25));
			btnGuardar.setPreferredSize(new Dimension(160, 25));
			campoID.setEnabled(false);
			campoPatente.setEnabled(false);
			campoMarca.setEnabled(false);
			campoModelo.setEnabled(false);
			campoCostoHs.setEnabled(false);
			campoCostoKm.setEnabled(false);
			campoFechaCompra.setEnabled(false);
			campoSeleccionKm.setEnabled(false);
			this.addKms();
			seleccionKm.setEnabled(false);
			seleccionKm.setVisible(false);
			btnEditar.setEnabled(true);
			btnEliminar.setEnabled(true);
			btnVolver.setEnabled(true);
			btnGuardar.setVisible(false);
			btnVolver.setVisible(true);
			btnEliminar.setVisible(true);
			
//			campoPatente.setToolTipText("LLL999 / LL999LL");
//			campoFechaCompra.setToolTipText("dd/mm/YYYY");
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
			add(lblID,constraints);
			constraints.insets.set(0, 120, 0, 0);
			add(campoID,constraints);
			
			constraints.gridy = 1;
			constraints.insets.set(20, 0, 0, 0);
			add(lblPatente, constraints);
			constraints.insets.set(20, 120, 0, 0);
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
			add(seleccionKm, constraints);
			add(campoSeleccionKm,constraints);
				
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
			constraints.gridx = 1;
			constraints.insets.set(30, 0, 0, 100);
			add(btnEditar, constraints);
			add(btnGuardar,constraints);
			constraints.gridx = 3;
			constraints.insets.set(30, 0, 0, 100);
			add(btnEliminar, constraints);
			constraints.gridy = 10;
			constraints.insets.set(30, 0, 0, 100);
			add(btnVolver,constraints);
			
			}

		
		public void limpiarFormulario() {
			this.campoPatente.setText("");
			this.campoModelo.setText("");
			this.campoMarca.setText("");
			this.campoCostoHs.setText("");
			this.campoCostoKm.setText("");
			this.setSeleccionKm();
			this.campoFechaCompra.setText("");
		}
		
		
		
		public void addListenerBtnEditar(ActionListener listener) {
			btnEditar.addActionListener(listener);
		}
		
		public void addListenerBtnGuardar(ActionListener listener) {
			btnGuardar.addActionListener(listener);
		}
		
		public void addListenerBtnEliminar(ActionListener listener) {
			btnEliminar.addActionListener(listener);
		}
		
		public void addListenerBtnVolver(ActionListener listener) {
			btnVolver.addActionListener(listener);
		}
		
		public String getCampoFechaCompra() {
			return campoFechaCompra.getText();
		}

		public void setCampoFechaCompra(String campoFechaCompra) {
			this.campoFechaCompra.setText(campoFechaCompra);
		}
		
		public String getID() {
			return campoID.getText();
		}

		public void setCampoID(String campod) {
			this.campoID.setText(campod);
		}

		public String getCampoModelo() {
			return campoModelo.getText();
		}

		public void setCampoModelo(String campoModelo) {
			this.campoModelo.setText(campoModelo);
		}


		public String getCampoMarca() {
			return campoMarca.getText();
		}

		public void setCampoMarca(String campoMarca) {
			this.campoMarca.setText(campoMarca);
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


		public void addListenerCampoPatente(KeyListener listener) { 
			   campoPatente.addKeyListener(listener);
		}


		public String getCampoCostoHs() {
			return campoCostoHs.getText();
		}

		public void setCampoCostoHs(String campoCostoHs) {
			this.campoCostoHs.setText(campoCostoHs);
		}

		public String getCampoCostoKm() {
			return campoCostoKm.getText();
		}

		public void setCampoCostoKm(String campoCostokm) {
			this.campoCostoKm.setText(campoCostokm);
		}



		public String getSeleccionKm() {
			if( seleccionKm.getItemAt(seleccionKm.getSelectedIndex()) == "Selecionar kilometraje") {
				return "-";
			}
			else
				return seleccionKm.getItemAt(seleccionKm.getSelectedIndex());
		}

		public void setSeleccionKm() {
			seleccionKm.removeAll();
			this.addKms();
		}
		
		public void addKms() {
			seleccionKm.setModel(new DefaultComboBoxModel<String>(new String[] {"Selecionar kilometraje",
					"0 - 9.999", "10.000 - 19.999", "20.000 - 29.999", "30.000 - 39.999", "40.000 - 49.999",
					"50.000 - 59.999", "60.000 - 69.999", "70.000 - 79.999", "80.000 - 89.999", "90.000 - 99.999",
					"100.00 - 109.999", "110.000 - 119.999", "120.000 - 129.999", "130.000 - 139.999", "140.000 - 149.999",
					"150.000 - 159.999", "160.000 - 169.999", "170.000 - 179.999", "180.000 - 189.999", "190.000 - 199.999",
					"200.000 - 209.999", "210.000 - 219.999", "220.000 - 229.999", "230.000 - 239.999", "240.000 - 249.999",
					"250.000 - 259.999", "260.000 - 269.999", "270.000 - 279.999", "280.000 - 289.999", "290.000 - 299.999",
					"Más de 300.000 km"
			}));
		}
		
		public void editar() {
			campoPatente.setEnabled(true);
			campoMarca.setEnabled(true);
			campoModelo.setEnabled(true);
			campoCostoHs.setEnabled(true);
			campoCostoKm.setEnabled(true);
			campoFechaCompra.setEnabled(true);
			this.addKms();
			campoSeleccionKm.setEnabled(false);
			campoSeleccionKm.setVisible(false);
			seleccionKm.setVisible(true);
			seleccionKm.setEnabled(true);
			btnEditar.setEnabled(false);
			btnEditar.setVisible(false);
			btnEliminar.setEnabled(false);
			btnEliminar.setVisible(false);
			btnVolver.setEnabled(true);
			btnGuardar.setVisible(true);
			btnGuardar.setEnabled(true);
		}

		public void setCampoPatente(String patente) {
			campoPatente.setText(patente);
		}

		public void setCampoSeleccionKm(String km) {
			campoSeleccionKm.setText(km);
		}


}
