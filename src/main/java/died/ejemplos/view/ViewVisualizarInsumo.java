package died.ejemplos.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import died.ejemplos.controller.BuscarCamionController;
import died.ejemplos.controller.BuscarInsumoController;

public class ViewVisualizarInsumo extends JPanel {
	
	private JButton btnCancelar = new JButton("CANCELAR");
	private JTextField campoTotalFilas = new JTextField(3);
	private JTable tablaInsumos = new JTable();
	private JScrollPane tablaInsumosScroll = new JScrollPane(tablaInsumos);
	private Object[][] datosTabla = {{""},{""},{""},{""},{""},{""},{""},{""}};
	private DefaultTableModel model;
	private BuscarInsumoController controller;
	private JLabel ltotalFilas = new JLabel("Total de filas:");
	
	public ViewVisualizarInsumo(JFrame v) {
		super();
		this.controller= new BuscarInsumoController(this, v);
		inicializarComponentes();
		ubicarComponentes();
		//addTablaInsumos(0);	
	}
	
	private void inicializarComponentes() {		
		btnCancelar.setPreferredSize(new Dimension(160, 25));
		tablaInsumos.setEnabled(true);
		tablaInsumos.setVisible(true);
		btnCancelar.setEnabled(true);
		campoTotalFilas.setEnabled(false);
		tablaInsumosScroll.setPreferredSize(new Dimension(880, 500));
		tablaInsumosScroll.setVisible(true);
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
		add(tablaInsumosScroll, constraints);	
		
		constraints.gridx = 2;
		constraints.gridy = 9;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets.set(5, 5, 5, 5);
		add(campoTotalFilas, constraints);	
		constraints.insets.set(5, 5, 5, 44);
		add(ltotalFilas, constraints);
	}
	
	public void addListenerBtnCancelar(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}
	
	public void addListenerTablaInsumos(MouseListener listener) {
		tablaInsumos.addMouseListener(listener);
	}
	
	public Integer getRowTablaInsumos(Point point) {
		return tablaInsumos.rowAtPoint(point);
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
		tablaInsumos.setModel(tableModel);
		
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment( JLabel.CENTER );
		
		tablaInsumos.getColumnModel().getColumn(0).setCellRenderer(centrado);
		tablaInsumos.getColumnModel().getColumn(1).setCellRenderer(centrado);
		tablaInsumos.getColumnModel().getColumn(2).setCellRenderer(centrado);
		tablaInsumos.getColumnModel().getColumn(3).setCellRenderer(centrado);
		tablaInsumos.getColumnModel().getColumn(4).setCellRenderer(centrado);
		tablaInsumos.getColumnModel().getColumn(5).setCellRenderer(centrado);
		tablaInsumos.getColumnModel().getColumn(6).setCellRenderer(centrado);
		tablaInsumos.getColumnModel().getColumn(7).setCellRenderer(centrado);
		
		tablaInsumos.getColumnModel().getColumn(0).setPreferredWidth(120);
		tablaInsumos.getColumnModel().getColumn(1).setPreferredWidth(120);
		tablaInsumos.getColumnModel().getColumn(2).setPreferredWidth(110);
		tablaInsumos.getColumnModel().getColumn(3).setPreferredWidth(220);
		tablaInsumos.getColumnModel().getColumn(4).setPreferredWidth(120);
		tablaInsumos.getColumnModel().getColumn(5).setPreferredWidth(120);
		tablaInsumos.getColumnModel().getColumn(6).setPreferredWidth(120);
		tablaInsumos.getColumnModel().getColumn(7).setPreferredWidth(120);
		
		tablaInsumos.getColumnModel().getColumn(0).setHeaderValue("Nombre");
		tablaInsumos.getColumnModel().getColumn(1).setHeaderValue("Descripcion");
		tablaInsumos.getColumnModel().getColumn(2).setHeaderValue("Costo");
		tablaInsumos.getColumnModel().getColumn(3).setHeaderValue("Unidad de medida");
		tablaInsumos.getColumnModel().getColumn(4).setHeaderValue("Tipo");
		tablaInsumos.getColumnModel().getColumn(5).setHeaderValue("Peso");
		tablaInsumos.getColumnModel().getColumn(6).setHeaderValue("Densidad");
		tablaInsumos.getColumnModel().getColumn(7).setHeaderValue("Stock total");
		
		if(tamanioTablaActual > 0) {	
			tablaInsumos.setToolTipText("Doble click para seleccionar un insumo");
			tablaInsumos.setEnabled(true);
			campoTotalFilas.setText(String.valueOf(tamanioTablaActual));
		}
		else {
			tablaInsumos.setToolTipText(null);
			tablaInsumos.setEnabled(false);
			campoTotalFilas.setText("");
		}
	}
	
	public void setValoresTablaInsumos(Integer fila, String nombre, String descripcion, Double costo, String unidadMedida, String tipo, Double peso, Double densidad) {
		model.setValueAt(nombre, fila, 0);
		model.setValueAt(descripcion, fila, 1);
		model.setValueAt(costo, fila, 2);
		model.setValueAt(unidadMedida, fila, 3);
		model.setValueAt(tipo, fila, 4);
		model.setValueAt(peso, fila, 5);
		model.setValueAt(densidad, fila, 6);
	}
	

}
