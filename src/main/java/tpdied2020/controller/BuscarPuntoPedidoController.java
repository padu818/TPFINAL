package tpdied2020.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import tpdied2020.dominio.Insumo;
import tpdied2020.dominio.Planta;
import tpdied2020.dominio.StockInsumo;
import tpdied2020.gestor.GestorInsumo;
import tpdied2020.gestor.GestorPlanta;
import tpdied2020.view.ViewBuscarPuntoPedido;

public class BuscarPuntoPedidoController {


	
	private GestorInsumo insumoService;
	private GestorPlanta plantaService;
	private List<Planta> plantas;
	private List<Insumo> insumos;
	private List<StockInsumo> stocks;
	private ViewBuscarPuntoPedido panel;

	private BuscarPuntoPedidoController instancia;
	
	public BuscarPuntoPedidoController(ViewBuscarPuntoPedido viewBuscarPuntoPedido){
		this.instancia = this;
		this.plantaService = new GestorPlanta();
		this.insumoService = new GestorInsumo();
		this.plantas = new ArrayList<Planta>();
		this.insumos= new ArrayList<Insumo>();
		this.stocks = new ArrayList<StockInsumo>();
		plantas = listarTodaPlanta();
		insumos = listarTodoInsumo();
		this.panel = viewBuscarPuntoPedido;
		panel.addInsumos(insumos);
		panel.addPlantas(plantas);
		panel.addListenerBtnCancelar(new ListenerBtnCancelar());
		panel.addListenerBtnBuscar(new ListenerBtnBuscar());
	}
	
	
	private void cargarTabla(List<StockInsumo> ins) {
		if(stocks.isEmpty()) {
			panel.addTablaStock(0);
		}
		else {

			int cantStock = ins.size();
			if(cantStock > 0){
				panel.addTablaStock(cantStock);

				for(int fila=0; fila<cantStock; fila++) {
					StockInsumo s = ins.get(fila);
					panel.setValoresTablaStock(fila,s.getPlanta().getNombre(),s.getInsumo().getNombre(), s.getStock(),s.getPuntoReposicion());
				}
			}
			else {
				panel.addTablaStock(0);
				JOptionPane.showMessageDialog(panel, "No se han encontrado plantas con stock menor al punto de pedido que cumplan con ese criterio de búsqueda.", "Planta no encontrado", JOptionPane.INFORMATION_MESSAGE);	
			}
		}
	}
	

	public List<Planta> listarTodaPlanta(){
		this.plantas.clear();
		this.plantas.addAll(plantaService.buscarTodos()); 
		return this.plantas;
	}
	
	public List<Insumo> listarTodoInsumo(){
		this.insumos.clear();
		this.insumos.addAll(insumoService.buscarTodos()); 
		return this.insumos;
	}

	private class ListenerBtnBuscar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				if(!stocks.isEmpty()) {
					stocks.removeAll(stocks);
					cargarTabla(stocks);
				}
				String plant = "";
				String insum = "";
				if(panel.getIndexSeleccionPlanta() != -1)
					plant = plantas.get(panel.getIndexSeleccionPlanta()).getIdPlanta().toString();
				if(panel.getIndexSeleccionInsumo() != -1)
					insum = insumos.get(panel.getIndexSeleccionInsumo()).getIdProduto().toString();
				stocks = insumoService.busqueda(plant,insum);
				cargarTabla(stocks);
			}catch(Exception ex) {
				 JOptionPane.showMessageDialog(null, "No se pudo obtener el stock de la planta desde la base de datos",
                          "Error.", JOptionPane.ERROR_MESSAGE); 
			}
		}
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
	
	
	
}
