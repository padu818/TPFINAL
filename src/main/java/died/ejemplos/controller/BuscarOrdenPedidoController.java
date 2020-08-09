package died.ejemplos.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import died.ejemplos.dominio.DetallesInsumoSolicitado;
import died.ejemplos.dominio.EstadoPedido;
import died.ejemplos.dominio.Insumo;
import died.ejemplos.dominio.Pedido;
import died.ejemplos.dominio.Planta;
import died.ejemplos.dominio.StockInsumo;
import died.ejemplos.gestor.GestorInsumo;
import died.ejemplos.gestor.GestorPedido;
import died.ejemplos.gestor.GestorPlanta;
import died.ejemplos.gui.ayuda.GrafoPlanta;
import died.ejemplos.gui.ayuda.Vertice;
import died.ejemplos.view.ViewAltaOrdenPedido;
import died.ejemplos.view.ViewBuscarOrdenPedido;

public class BuscarOrdenPedidoController {
	
	private BuscarOrdenPedidoController instancia;
	
	private GestorPlanta plantaService;
	private GestorInsumo insumoService;
	private GestorPedido pedidoService;

	private List<Planta> plantas;
	private List<Planta> pt;
	private List<Insumo> insumos;
	private List<Pedido> pedidos;
	private List<StockInsumo> stock;
	private GrafoPlanta grafo;
	
	private Pedido pedido;
	private List<DetallesInsumoSolicitado> insumosAgregados;

	private ViewBuscarOrdenPedido panel;
	private JFrame ventana;
	private Point click;
	private Planta p;
	private Integer seleccion;
	
	public BuscarOrdenPedidoController(ViewBuscarOrdenPedido view, JFrame v, GrafoPlanta p2) {
		
		this.instancia = this;
		this.plantaService = new GestorPlanta();
		this.insumoService = new GestorInsumo();
		this.pedidoService = new GestorPedido();

		this.plantas = new ArrayList<Planta>();
		this.pt = new ArrayList<Planta>();
		this.insumos= new ArrayList<Insumo>();
		this.pedidos= new ArrayList<Pedido>();
		pedido = new Pedido();
		this.insumosAgregados = new ArrayList<DetallesInsumoSolicitado>();
//		this.listaStock = new ArrayList<StockInsumo>();
		this.ventana =v;
		this.panel = view;
//		panel.addListenerBtnGuardar(new ListenerBtnGuardar());
//		panel.addListenerBtnCancelar(new ListenerBtnCancelar());
//		panel.addListenerBtnEliminar(new ListenerBtnEliminar());
//		panel.addListenerBtnAgregar(new ListenerBtnAgregar());
		panel.addListenerTablaPedidos(new ListenerTablaPedidos());
//		panel.addListenerTablaInsumos(new ListenerTablaInsumo());
		plantas = listarTodosPlanta();
		grafo = p2;
		insumos = listarTodoInsumo();
		pedidos = listarTodoPedido();
		panel.addTablaPedidos(plantas.size());
		cargarTablaPedidos(pedidos);
		panel.addTablaInsumos(insumosAgregados.size());
		cargarTablaInsumo(insumosAgregados);
		
		ventana.setContentPane(panel);
	//	stock = insumoService.buscarTodoStock(insumos,plantas);
	//	plantas.removeAll(plantas);

		panel.addPlantasDisponibles(pt);
		
	}
	
	private List<Planta> tieneStock(List<StockInsumo> stock2) {
		return insumoService.tieneStock(insumosAgregados,plantas);
	}

	private void cargarTablaPedidos(List<Pedido> pedidos) {
		if(pedidos.isEmpty()) {
			panel.addTablaPedidos(0);
		}
		else {
			int cantPedidos = pedidos.size();
			if(cantPedidos > 0){
				panel.addTablaPedidos(cantPedidos);
				for(int fila=0; fila<cantPedidos; fila++) {
//					Planta in = plantas.get(fila);
					panel.setValoresTablaPedidos(fila, pedidos.get(fila).getIdPedido(), pedidos.get(fila).getDestino().getNombre(), pedidos.get(fila).getFechaSolicitud().toString(), pedidos.get(fila).getFechaEntrega().toString());
					
				}
			}
			else {
				panel.addTablaPedidos(0);
//				JOptionPane.showMessageDialog(panel, "No se han encontrado plantas que cumplan con ese criterio de búsqueda.", "Planta no encontrada", JOptionPane.INFORMATION_MESSAGE);	
			}
		}
	}
	
	private void cargarTablaInsumo(List<DetallesInsumoSolicitado> listaInsumo) {
		if(listaInsumo.isEmpty()) {
			panel.addTablaInsumos(0);
		}
		else {
			int cantInsumo = listaInsumo.size();
			if(cantInsumo > 0){
				panel.addTablaInsumos(cantInsumo);
				for(int fila=0; fila<cantInsumo; fila++) {
//					Insumo in = listaInsumo.get(fila);
					//REVISAR!!!!!!!
					panel.setValoresTablaInsumos(fila, listaInsumo.get(fila).getInsumo().getNombre(), listaInsumo.get(fila).getCantidad(), listaInsumo.get(fila).getPrecio());
					
				}
			}
			else {
				panel.addTablaInsumos(0);
//				JOptionPane.showMessageDialog(panel, "No se han encontrado plantas que cumplan con ese criterio de búsqueda.", "Planta no encontrada", JOptionPane.INFORMATION_MESSAGE);	
			}
		}
	}
	
	public List<Planta> listarTodosPlanta(){
		this.plantas.clear();
		this.plantas.addAll(plantaService.buscarTodos());
		return this.plantas;
	}
	
	public List<Pedido> listarTodoPedido(){
			this.pedidos.clear();
			this.pedidos.addAll(pedidoService.buscarTodos(insumos)); 
			for(Pedido p :pedidos) {
				for(Planta pl :plantas)
				if(p.getDestino().getIdPlanta() == pl.getIdPlanta()) {
					p.setDestino(pl);
					break;
				}
			}
				
			return this.pedidos;
	}
	
	public List<Insumo> listarTodoInsumo(){
		this.insumos.clear();
		this.insumos.addAll(insumoService.buscarTodos()); 
		return this.insumos;
	}
	
	private class ListenerTablaPedidos implements MouseListener{			
		@Override
		public void mousePressed(MouseEvent e) {
	        JTable table = (JTable) e.getSource();
	        Point point = e.getPoint();
	        int row = panel.getRowTablaPedidos(point);
	        seleccion = row;
	        click = point;
	        if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
	        	panel.habilitarCampos(true);
	        	cargarTablaInsumo(pedidos.get(row).getItems());
	        	insumosAgregados = pedidos.get(row).getItems();
	    		pt = tieneStock(stock);
	    		
	    		if(pt.isEmpty()) {
	    			pedidos.get(row).setEstado(EstadoPedido.CANCELADA);
	    			System.out.println(pedidos.get(row).getEstado());
	    			pedidoService.crearPedido(pedidos.get(row));
	    			pedidos.remove(row);
	    			cargarTablaPedidos(pedidos);

	    		}
	    		if(pt.isEmpty()) {
	    			JOptionPane.showMessageDialog(panel, "No se han encontrado plantas que tenga el stock disponible en todos los productos.", "Planta no encontrada", JOptionPane.INFORMATION_MESSAGE);	
	    			insumosAgregados.removeAll(insumosAgregados);
	    			cargarTablaInsumo(insumosAgregados);
	    		}
	    		if(!pt.isEmpty()) {
		    		for(int i = 0; i < pt.size();i++) {
		    			if(!grafo.hayCamino(pt.get(i), pedidos.get(row).getDestino())) {
		    				pt.remove(p);
		    			}
		    		}
	    		}
	    		panel.addPlantasDisponibles(pt);
//	        	System.out.println(pedidos.get(seleccion).getItems());
//	        	listaStock = insumoService.busquedaStockInsumos(p);
//	        	cargarTablaStock(listaStock);
	        }
		}
		@Override public void mouseClicked(MouseEvent e) {} 
		@Override public void mouseReleased(MouseEvent e) {}
		@Override public void mouseEntered(MouseEvent e) {}
		@Override public void mouseExited(MouseEvent e) {}
	}

//	public List<Planta> listarPlantas() {
//		return plantaService.;
//	}

}
