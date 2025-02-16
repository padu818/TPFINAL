package tpdied2020.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import tpdied2020.dominio.General;
import tpdied2020.dominio.Insumo;
import tpdied2020.dominio.Liquido;
import tpdied2020.gestor.GestorInsumo;
import tpdied2020.view.ViewAltaInsumo;
import tpdied2020.view.ViewVisualizarInsumo;

public class BuscarInsumoController {
	
private static final long serialVersionUID = 1L;
	
	
	private GestorInsumo insumoService;
	private Insumo i;
	private List<Insumo> lista;
	private ViewVisualizarInsumo panel;
	private JFrame ventana;
	private BuscarInsumoController instancia;
	
	public BuscarInsumoController(ViewVisualizarInsumo viewBuscarInsumo, JFrame v){
		this.instancia = this;
		this.insumoService = GestorInsumo.get();
		this.lista = new ArrayList<Insumo>();
		this.ventana =v;
		this.panel = viewBuscarInsumo;
		panel.addListenerBtnCancelar(new ListenerBtnCancelar());
		panel.addListenerTablaInsumos(new ListenerTablaInsumos());
		lista = listarTodos();
		panel.addTablaInsumos(lista.size());
		ventana.setContentPane(panel);
		cargarTabla(lista);
	}


	private void cargarTabla(List<Insumo> insumos) {
		if(insumos.isEmpty()) {
			panel.addTablaInsumos(0);
		}
		else {
			int cantInsumos = insumos.size();
			if(cantInsumos > 0){
				Integer[] stock = new Integer[cantInsumos];
				stock = insumoService.stockInsumos(cantInsumos,insumos);
				panel.addTablaInsumos(cantInsumos);
				for(int fila=0; fila<cantInsumos; fila++) {
					Insumo in = insumos.get(fila);
					if (insumos.get(fila).getTipoInsumo().equals("GENERAL")) {
						panel.setValoresTablaInsumos(fila, insumos.get(fila).getNombre(), insumos.get(fila).getDescripcion(), insumos.get(fila).getCosto(), insumos.get(fila).getUnidadMedida(), insumos.get(fila).getTipoInsumo(), ((General)insumos.get(fila)).getPeso(), 0.0, stock[fila]);
					}else {
						panel.setValoresTablaInsumos(fila, insumos.get(fila).getNombre(), insumos.get(fila).getDescripcion(), insumos.get(fila).getCosto(), insumos.get(fila).getUnidadMedida(), insumos.get(fila).getTipoInsumo(), 0.0,((Liquido)insumos.get(fila)).getDensidad(),stock[fila]);
					}
					
				}
			}
			else {
				panel.addTablaInsumos(0);
				JOptionPane.showMessageDialog(panel, "No se han encontrado insumos que cumplan con ese criterio de búsqueda.", "Insumo no encontrado", JOptionPane.INFORMATION_MESSAGE);	
			}
		}
	}
	
	public List<Insumo> listarTodos(){
		this.lista.clear();
		this.lista.addAll(insumoService.buscarTodos());
		return this.lista;
	}
	
	private class ListenerBtnCancelar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {	
				panel.setVisible(false);
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(panel, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ListenerTablaInsumos implements MouseListener{			
		@Override
		public void mousePressed(MouseEvent e) {
	        JTable table = (JTable) e.getSource();
	        Point point = e.getPoint();
	        int row = panel.getRowTablaInsumos(point);
	        if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
	    		i = lista.get(row);
	    		ViewAltaInsumo ca = new ViewAltaInsumo(i,ventana);
	    		ca.setVisible(true);
	    		panel.setVisible(false);
	        }
		}
		@Override public void mouseClicked(MouseEvent e) {} 
		@Override public void mouseReleased(MouseEvent e) {}
		@Override public void mouseEntered(MouseEvent e) {}
		@Override public void mouseExited(MouseEvent e) {}
	}

}
