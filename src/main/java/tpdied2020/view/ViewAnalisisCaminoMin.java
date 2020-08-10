package tpdied2020.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import tpdied2020.controller.AnalisisController;
import tpdied2020.controller.BuscarCamionController;
import tpdied2020.dominio.Planta;
import tpdied2020.gui.auxiliar.GrafoPlanta;

public class ViewAnalisisCaminoMin extends JPanel {
		
	private JLabel ltipo= new JLabel("Tipo:");
	private JComboBox<String> seleccionTipo = new JComboBox<String>();
	
	private JButton btnBuscar = new JButton("BUSCAR");
	private JButton btnCancelar = new JButton("CANCELAR");
	
	private JTable tablaCamino = new JTable();
	private JScrollPane tablaCaminoScroll = new JScrollPane(tablaCamino);
	private Object[][] datosTabla;
	private DefaultTableModel model;
	private AnalisisController controller;
	
	
	public ViewAnalisisCaminoMin(GrafoPlanta g) {
		super();
		this.controller= new AnalisisController(this, g);
		inicializarComponentes();
		ubicarComponentes();
		addtablaCamino(0);	
	}
	
	private void inicializarComponentes() {		
		btnBuscar.setPreferredSize(new Dimension(160, 25));
		btnCancelar.setPreferredSize(new Dimension(160, 25));
		this.addTipo();
		seleccionTipo.setEnabled(true);
		tablaCamino.setEnabled(false);
		btnBuscar.setEnabled(true);
		btnCancelar.setEnabled(true);
		tablaCaminoScroll.setPreferredSize(new Dimension(680, 500));	

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
		add(ltipo, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets.set(10, 10, 10, 5);
		add(seleccionTipo, constraints);	
		

		constraints.gridx = 0;
		constraints.gridy = 3;
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
		add(tablaCaminoScroll, constraints);	
	
	}
	
	public String getTipo() {
		if( seleccionTipo.getItemAt(seleccionTipo.getSelectedIndex()) == "Seleccionar tipo") {
			return "-";
		}
		else
			return seleccionTipo.getItemAt(seleccionTipo.getSelectedIndex());
	}
	
	public void addListenerBtnCancelar(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}
	
	
	public void addListenerBtnBuscar(ActionListener listener) {
		btnBuscar.addActionListener(listener);
	}
	
	public void addtablaCamino(Integer tamanioActual) {
		DefaultTableModel tableModel = new DefaultTableModel( datosTabla, tamanioActual) {
			private static final long serialVersionUID = 7365551733085502818L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		model = tableModel;		
		tablaCamino.setModel(tableModel);
		
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment( JLabel.CENTER );
		
		for(int i = 0; i< tamanioActual;i++) {
			tablaCamino.getColumnModel().getColumn(i).setCellRenderer(centrado);
			tablaCamino.getColumnModel().getColumn(i).setPreferredWidth(120);
			tablaCamino.getColumnModel().getColumn(i).setHeaderValue(datosTabla[i][0]);
		}
		
		
		
	}	
	
	public void setValoresTablaCamino(Integer fila, Double[] cam) {
		for(int i = 0; i < cam.length; i++) {
			model.setValueAt(cam[i], fila, i);
		}
	}
	
	public Integer getIndexOfTipo() {
		if( seleccionTipo.getItemAt(seleccionTipo.getSelectedIndex()) == "Seleccionar tipo") {
			return -1;
		}
		else
			return seleccionTipo.getSelectedIndex()-1;
	}
	
	public void addTipo() {
		seleccionTipo.setModel(new DefaultComboBoxModel<String>(new String[] {"Seleccionar tipo",
				"duracion", "distancia"
		}));
	}
	
	public void addPlantas(List<Planta> plant) {
		datosTabla = new Object[plant.size()][];
		Integer i = 0;
		for(Planta p: plant) {
			datosTabla[i][0] = p.getNombre();
		}
	}
}
