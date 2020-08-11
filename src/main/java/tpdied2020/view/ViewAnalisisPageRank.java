package tpdied2020.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import tpdied2020.controller.AnalisisController;
import tpdied2020.gui.auxiliar.GrafoPlanta;

public class ViewAnalisisPageRank extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JButton btnCancelar = new JButton("CANCELAR");
	private JTable tablaPlantas = new JTable();
	private JScrollPane tablaPlantasScroll = new JScrollPane(tablaPlantas);
	private Object[][] datosTabla = {{""},{""},{""}};
	private DefaultTableModel model;
	private AnalisisController controller;
	
	
	public ViewAnalisisPageRank(GrafoPlanta p) {
		super();
	//	this.controller= new AnalisisController(this,p );
		inicializarComponentes();
		ubicarComponentes();	
	}
	
	private void inicializarComponentes() {		
		btnCancelar.setPreferredSize(new Dimension(160, 25));
		tablaPlantas.setEnabled(true);
		tablaPlantas.setVisible(true);
		btnCancelar.setEnabled(false);
		tablaPlantasScroll.setPreferredSize(new Dimension(880, 500));
		tablaPlantasScroll.setVisible(true);
	}
	
	private void ubicarComponentes() {
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridy = 8;
		constraints.insets.set(30, 5, 5, 5);
		add(btnCancelar, constraints);	
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridheight = 9;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets.set(5, 30, 5, 5);
		add(tablaPlantasScroll, constraints);
	}
	
	public void addListenerBtnCancelar(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}
	
	public void addTablaInsumos(Integer tamanioTablaActual) {
		DefaultTableModel tableModel = new DefaultTableModel( datosTabla, tamanioTablaActual) {
			private static final long serialVersionUID = 7365551733085502818L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		model = tableModel;		
		tablaPlantas.setModel(tableModel);
		
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment( JLabel.CENTER );
		
		tablaPlantas.getColumnModel().getColumn(0).setCellRenderer(centrado);
		tablaPlantas.getColumnModel().getColumn(1).setCellRenderer(centrado);
		tablaPlantas.getColumnModel().getColumn(2).setCellRenderer(centrado);
		
		tablaPlantas.getColumnModel().getColumn(0).setPreferredWidth(120);
		tablaPlantas.getColumnModel().getColumn(1).setPreferredWidth(120);
		tablaPlantas.getColumnModel().getColumn(2).setPreferredWidth(120);
		
		tablaPlantas.getColumnModel().getColumn(0).setHeaderValue("ID");
		tablaPlantas.getColumnModel().getColumn(1).setHeaderValue("Nombre");
		tablaPlantas.getColumnModel().getColumn(2).setHeaderValue("PageRank");
		
		if(tamanioTablaActual > 0) {
			tablaPlantas.setEnabled(true);
		}
		else {
			tablaPlantas.setToolTipText(null);
			tablaPlantas.setEnabled(false);
		}
	}
	
	public void setValoresTablaPlantas(Integer fila, String id, String nombre, Double pagerank) {
		model.setValueAt(id, fila, 0);
		model.setValueAt(nombre, fila, 1);
		model.setValueAt(pagerank, fila, 2);
	}


}
