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

import died.ejemplos.dominio.Camion;
import died.ejemplos.dominio.DetallesInsumoSolicitado;
import died.ejemplos.dominio.EstadoPedido;
import died.ejemplos.dominio.Insumo;
import died.ejemplos.dominio.Pedido;
import died.ejemplos.dominio.Planta;
import died.ejemplos.gestor.GestorCamion;
import died.ejemplos.gestor.GestorInsumo;
import died.ejemplos.gestor.GestorPedido;
import died.ejemplos.gestor.GestorPlanta;
import died.ejemplos.gui.ayuda.GrafoPlanta;
import died.ejemplos.gui.ayuda.Vertice;
import died.ejemplos.view.viewBuscarOrdenPedidoProcesada;

public class BuscarOrdenPedidoProcesadaController {
	
	private GestorPlanta plantaService;
	private GestorCamion camionService;
	private GestorInsumo insumoService;
	private GestorPedido pedidoService;
	private List<Pedido> pedidos;
	private List<Insumo> insumos;
	private List<Planta> plantas;
	private GrafoPlanta grafo;
	private Pedido pedido;
	private List<DetallesInsumoSolicitado> insumosAgregados;
	private viewBuscarOrdenPedidoProcesada panel;
	
	
public BuscarOrdenPedidoProcesadaController(viewBuscarOrdenPedidoProcesada view, GrafoPlanta p2) {
		
		this.panel = view;
		panel.setVisible(true);
		this.plantaService = new GestorPlanta();
		this.insumoService = new GestorInsumo();
		this.pedidoService = new GestorPedido();
		this.camionService = new GestorCamion();
		this.insumos= new ArrayList<Insumo>();
		this.pedidos= new ArrayList<Pedido>();
		List<Camion> camiones=  new ArrayList<Camion>();
		pedido = new Pedido();
		this.insumosAgregados = new ArrayList<DetallesInsumoSolicitado>();
		this.panel = view;
		panel.addListenerBtnGuardar(new ListenerBtnGuardar());
		panel.addListenerBtnCancelar(new ListenerBtnCancelar());
		plantas = plantaService.buscarTodos();
		panel.addListenerTablaPedidos(new ListenerTablaPedidos());
		grafo = p2;
//		for(Vertice<Planta> t : grafo.getVertices()) {
//			plantas.add(t.getValor());
//		}
		camiones.addAll(camionService.buscarTodos(plantas));
		insumos = listarTodoInsumo();
		pedidos = pedidoService.listarTodoPedidoProcesado(insumos);
		Boolean bandera1 = false;
		Boolean bandera2 = false;
		for(Pedido s:pedidos) {
			
			for(Planta r:plantas) {
				if(s.getDestino().getIdPlanta() == r.getIdPlanta()) {
					s.setDestino(r);
					bandera1 = true;
				}
				if(s.getOrigen().getIdPlanta() == r.getIdPlanta()) {
					s.setOrigen(r);
					bandera2 = true;
				}
				if(bandera1 && bandera2)
					break;
			}
			for(Camion c :camiones) {
				if(c.getId() == s.getCamionAsignado().getId()) {
					s.setCamionAsignado(c);
					break;
				}		
			}
		}
		
		panel.addTablaPedidos(plantas.size());
		cargarTablaPedidos(pedidos);
		panel.addTablaInsumos(insumosAgregados.size());
		cargarTablaInsumo(insumosAgregados);
			
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
	//				Planta in = plantas.get(fila);
					panel.setValoresTablaPedidos(fila, pedidos.get(fila).getIdPedido(), pedidos.get(fila).getOrigen().getNombre(),pedidos.get(fila).getDestino().getNombre(), 
								pedidos.get(fila).getFechaSolicitud().toString(), pedidos.get(fila).getFechaEntrega().toString(), pedidos.get(fila).getCamionAsignado().getPatente(), pedidos.get(fila).getCostoEnvio());
					
				}
			}
			else {
				panel.addTablaPedidos(0);
	//			JOptionPane.showMessageDialog(panel, "No se han encontrado plantas que cumplan con ese criterio de búsqueda.", "Planta no encontrada", JOptionPane.INFORMATION_MESSAGE);	
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
	
	        if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
	        	panel.habilitarGuardar(true);
	        	cargarTablaInsumo(pedidos.get(row).getItems());
	        	insumosAgregados = pedidos.get(row).getItems();
	        	pedido = pedidos.get(row);
	        }
		}
		@Override public void mouseClicked(MouseEvent e) {} 
		@Override public void mouseReleased(MouseEvent e) {}
		@Override public void mouseEntered(MouseEvent e) {}
		@Override public void mouseExited(MouseEvent e) {}
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
	
	private class ListenerBtnGuardar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {	
				pedido.setEstado(EstadoPedido.ENTREGADA);
				pedidoService.crearPedido(pedido);
				pedidos.remove(pedido);
				cargarTablaPedidos(pedidos);
				panel.habilitarGuardar(false);
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(panel, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
}
