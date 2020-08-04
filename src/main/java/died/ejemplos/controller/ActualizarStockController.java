package died.ejemplos.controller;

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


import died.ejemplos.dominio.General;
import died.ejemplos.dominio.Insumo;
import died.ejemplos.dominio.Liquido;
import died.ejemplos.dominio.Planta;
import died.ejemplos.dominio.StockInsumo;
import died.ejemplos.gestor.GestorInsumo;
import died.ejemplos.gestor.GestorPlanta;
import died.ejemplos.view.ViewActualizarStock;
import died.ejemplos.view.ViewAltaInsumo;
import died.ejemplos.view.ViewVisualizarInsumo;

public class ActualizarStockController {
	
	private GestorPlanta plantaService;
	private Planta p;
	private List<Planta> lista;
	private List<StockInsumo> listaStock;
	private ViewActualizarStock panel;
	private JFrame ventana;
	private ActualizarStockController instancia;
	
	public ActualizarStockController(ViewActualizarStock view, JFrame v){
		this.instancia = this;
		this.plantaService = new GestorPlanta();
		this.lista = new ArrayList<Planta>();
		this.listaStock = new ArrayList<StockInsumo>();
		this.ventana =v;
		this.panel = view;
//		panel.addListenerBtnCancelar(new ListenerBtnCancelar());
//		panel.addListenerTablaPlantas(new ListenerTablaPlantas());
		lista = listarTodos();
		panel.addTablaPlantas(lista.size());
		cargarTabla(lista);
		panel.addTablaInsumos(lista.size());
		cargarTablaStock(listaStock);
		ventana.setContentPane(panel);
	}
	
	private void cargarTabla(List<Planta> plantas) {
		if(plantas.isEmpty()) {
			panel.addTablaPlantas(0);
		}
		else {
			int cantPlantas = plantas.size();
			if(cantPlantas > 0){
				panel.addTablaPlantas(cantPlantas);
				for(int fila=0; fila<cantPlantas; fila++) {
					Planta in = plantas.get(fila);
					panel.setValoresTablaPlantas(fila, plantas.get(fila).getIdPlanta(), plantas.get(fila).getNombre());
					
				}
			}
			else {
				panel.addTablaPlantas(0);
				JOptionPane.showMessageDialog(panel, "No se han encontrado plantas que cumplan con ese criterio de búsqueda.", "Planta no encontrada", JOptionPane.INFORMATION_MESSAGE);	
			}
		}
	}
	
	private void cargarTablaStock(List<StockInsumo> listaStockInsumo) {
		if(listaStockInsumo.isEmpty()) {
			panel.addTablaInsumos(0);
		}
		else {
			int cantStockInsumo = listaStockInsumo.size();
			if(cantStockInsumo > 0){
				panel.addTablaInsumos(cantStockInsumo);
				for(int fila=0; fila<cantStockInsumo; fila++) {
					StockInsumo in = listaStockInsumo.get(fila);
					panel.setValoresTablaInsumos(fila, listaStockInsumo.get(fila).getInsumo().getNombre(), listaStockInsumo.get(fila).getStock(), listaStockInsumo.get(fila).getPuntoReposicion());
					
				}
			}
			else {
				panel.addTablaInsumos(0);
//				JOptionPane.showMessageDialog(panel, "No se han encontrado plantas que cumplan con ese criterio de búsqueda.", "Planta no encontrada", JOptionPane.INFORMATION_MESSAGE);	
			}
		}
	}
	
	public List<Planta> listarTodos(){
		this.lista.clear();
		this.lista.addAll(plantaService.buscarTodos());
		return this.lista;
	}
	
//	private class ListenerBtnCancelar implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			try {	
//				panel.setVisible(false);
//			}catch(Exception ex) {
//			    JOptionPane.showMessageDialog(panel, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
//			}
//		}
//	}
	
//	private class ListenerTablaPlantas implements MouseListener{			
//		@Override
//		public void mousePressed(MouseEvent e) {
//	        JTable table = (JTable) e.getSource();
//	        Point point = e.getPoint();
//	        int row = panel.getRowTablaPlantas(point);
//	        if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
//	    		p = lista.get(row);
//	    		ViewAltaInsumo ca = new ViewAltaInsumo(i,ventana);
//	    		ca.setVisible(true);
//	    		panel.setVisible(false);
////	    		ventana.setContentPane(ca);
//	        }
//		}
//		@Override public void mouseClicked(MouseEvent e) {} 
//		@Override public void mouseReleased(MouseEvent e) {}
//		@Override public void mouseEntered(MouseEvent e) {}
//		@Override public void mouseExited(MouseEvent e) {}
//	}

}
