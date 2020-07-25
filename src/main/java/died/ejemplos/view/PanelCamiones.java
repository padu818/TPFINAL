package died.ejemplos.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import died.ejemplos.controller.CamionController;
import died.ejemplos.gui.util.ControllerException;
import died.ejemplos.gui.util.DatosObligatoriosException;
import died.ejemplos.gui.util.FormatoNumeroException;

public class PanelCamiones extends JPanel{
	private JLabel lblTitulo = new JLabel("Administracion de camiones:");

	private JLabel lblPatente = new JLabel("Patente:");
	private JTextField txtPatente;
	private JLabel lblModelo = new JLabel("Modelo:");
	private JTextField txtModelo;
	private JLabel lblMarca = new JLabel("Marca:");
	private JTextField txtMarca;
	private JLabel lblFecha = new JLabel("Fecha:");
	private DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	private JFormattedTextField txtFechaCompra = new JFormattedTextField(df);	
	private JLabel lblKm = new JLabel("KMs:");
	private JTextField txtKm;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnActualizar;
	private JTable tablaCamiones;
	private CamionTableModel modeloTablaCamion; 
	private CamionController controller;
	
	public PanelCamiones(){
		super();
		this.controller= new CamionController(this);
		this.armarPanel();
	}
	
	private void armarPanel() {
		this.setBackground(Color.GRAY);
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		
		
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 11;
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTitulo.setForeground(Color.BLUE);
		this.add(lblTitulo,constraints);
		constraints.gridwidth = 1;

		constraints.gridx = 0;
		constraints.gridy = 1;		
		this.add(lblPatente,constraints);

		constraints.gridx = 1;
		constraints.gridy = 1;		
		this.txtPatente = new JTextField(20);
		this.add(txtPatente,constraints);

		constraints.gridx = 2;
		constraints.gridy = 1;		
		this.add(lblModelo,constraints);

		constraints.gridx = 3;
		constraints.gridy = 1;		
		this.txtModelo = new JTextField(20);		
		this.add(txtModelo,constraints);
		
		constraints.gridx = 4;
		constraints.gridy = 1;		
		this.add(lblMarca,constraints);
		constraints.gridx = 5;
		constraints.gridy = 1;		
		this.txtMarca = new JTextField(20);
		this.add(txtMarca,constraints);
		

		constraints.gridx = 6;
		constraints.gridy = 1;		
		this.add(lblFecha,constraints);
		this.txtFechaCompra = new JFormattedTextField(80);
		constraints.gridx = 7;
		constraints.gridy = 1;		
		this.add(txtFechaCompra,constraints);
		constraints.gridx = 8;
		constraints.gridy = 1;		
		this.add(lblKm,constraints);
		constraints.gridx = 9;
		constraints.gridy = 1;		
		this.txtKm = new JTextField(20);		
		this.add(txtKm,constraints);		
		constraints.gridx = 8;
		constraints.gridy = 2;	
		
		this.btnGuardar = new JButton("Guardar");
		this.btnGuardar.addActionListener( e ->
			{
				try {
					controller.guardar();
				} catch (DatosObligatoriosException | FormatoNumeroException | ControllerException e1) {
					this.mostrarError("Error al guardar", e1.getMessage());
				}
				this.limpiarFormulario();
				modeloTablaCamion.fireTableDataChanged();
			}
		);
		this.btnActualizar = new JButton("Actualizar");
		this.btnActualizar.addActionListener( e ->
			{
	//			try {
					controller.listarTodos();
//				} catch ( ControllerException e1) {
//					this.mostrarError("Error al actualizar", e1.getMessage());
//				}
				
				modeloTablaCamion.fireTableDataChanged();
			}
		);
		this.add(btnGuardar,constraints);
		constraints.gridx = 9;
		constraints.gridy = 2;		
		constraints.weightx=2;
		constraints.anchor = GridBagConstraints.LINE_START;
		constraints.ipadx = 25;
		constraints.insets = new Insets(0, 50, 0, 5);
		this.btnCancelar = new JButton("Cancelar");
		this.add(btnCancelar,constraints);
		constraints.gridx = 10;
		constraints.gridy = 2;		
		this.add(btnActualizar,constraints);
		constraints.weightx=0;
		modeloTablaCamion = new CamionTableModel(controller.listarTodos());
		tablaCamiones = new JTable();
		tablaCamiones.setModel(modeloTablaCamion);

		JScrollPane scrollPane = new JScrollPane(tablaCamiones);
		tablaCamiones.setFillsViewportHeight(true);
		constraints.gridx = 0;
		constraints.gridy = 3;		
		constraints.gridwidth = 11;
		constraints.weighty=1;
		constraints.weightx=2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTH;
		this.add(scrollPane,constraints);
	}
	
	private void limpiarFormulario() {
		this.txtPatente.setText("");
		this.txtModelo.setText("");
		this.txtMarca.setText("");
		this.txtKm.setText("");
		this.txtFechaCompra.setText("");
	}

	public JTextField getTxtPatente() {
		return txtPatente;
	}

	public void setTxtPatente(JTextField txtPatente) {
		this.txtPatente = txtPatente;
	}

	public JTextField getTxtModelo() {
		return txtModelo;
	}

	public void setTxtModelo(JTextField txtModelo) {
		this.txtModelo = txtModelo;
	}

	public JTextField getTxtMarca() {
		return txtMarca;
	}

	public void setTxtMarca(JTextField txtMarca) {
		this.txtMarca = txtMarca;
	}

	public DateFormat getDf() {
		return df;
	}

	public void setDf(DateFormat df) {
		this.df = df;
	}

	public JFormattedTextField getTxtFechaCompra() {
		return txtFechaCompra;
	}

	public void setTxtFechaCompra(JFormattedTextField txtFechaCompra) {
		this.txtFechaCompra = txtFechaCompra;
	}

	public JTextField getTxtKm() {
		return txtKm;
	}

	public void setTxtKm(JTextField txtKm) {
		this.txtKm = txtKm;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public CamionController getController() {
		return controller;
	}

	public void setController(CamionController controller) {
		this.controller = controller;
	}


	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
			    detalle,titulo,
			    JOptionPane.ERROR_MESSAGE);
	}
	
	
	
}
