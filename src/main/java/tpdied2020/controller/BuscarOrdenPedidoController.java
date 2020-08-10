package tpdied2020.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import tpdied2020.dominio.Camion;
import tpdied2020.dominio.DetallesInsumoSolicitado;
import tpdied2020.dominio.EstadoPedido;
import tpdied2020.dominio.Insumo;
import tpdied2020.dominio.Pedido;
import tpdied2020.dominio.Planta;
import tpdied2020.dominio.Ruta;
import tpdied2020.dominio.StockInsumo;
import tpdied2020.gestor.GestorCamion;
import tpdied2020.gestor.GestorInsumo;
import tpdied2020.gestor.GestorPedido;
import tpdied2020.gestor.GestorPlanta;
import tpdied2020.gui.auxiliar.Arista;
import tpdied2020.gui.auxiliar.GrafoPlanta;
import tpdied2020.gui.auxiliar.Vertice;
import tpdied2020.view.ViewBuscarOrdenPedido;

public class BuscarOrdenPedidoController {
	
	private BuscarOrdenPedidoController instancia;
	
	private GestorPlanta plantaService;
	private GestorInsumo insumoService;
	private GestorPedido pedidoService;
	private GestorCamion camionService;

	private List<Planta> plantas;
	private List<Planta> pt;
	private List<Insumo> insumos;
	private List<Pedido> pedidos;
	private List<StockInsumo> stock;
	private List<List<Ruta>> ruts;
	private GrafoPlanta grafo;
	
	private Pedido pedido;
	private List<DetallesInsumoSolicitado> insumosAgregados;

	private ViewBuscarOrdenPedido panel;
	private JFrame ventana;
	private Point click;
	private Planta p;
	private Planta p2;
	private Integer seleccion;
	private Integer cami;
	private Double[] costos;
	
	public BuscarOrdenPedidoController(ViewBuscarOrdenPedido view, JFrame v, GrafoPlanta p2) {
		
		this.instancia = this;
		this.plantaService = new GestorPlanta();
		this.insumoService = new GestorInsumo();
		this.pedidoService = new GestorPedido();
		this.camionService = new GestorCamion();

		this.plantas = new ArrayList<Planta>();
		this.pt = new ArrayList<Planta>();
		this.insumos= new ArrayList<Insumo>();
		this.pedidos= new ArrayList<Pedido>();
		this.ruts = new ArrayList<List<Ruta>>();
		pedido = new Pedido();
		this.insumosAgregados = new ArrayList<DetallesInsumoSolicitado>();
		this.ventana =v;
		this.panel = view;
		panel.addListenerBtnGuardar(new ListenerBtnGuardar());
		panel.addListenerBtnCancelar(new ListenerBtnCancelar());
		panel.addListenerSeleccionKmhs(new ListenerSeleccion());
		panel.addListenerTablaPedidos(new ListenerTablaPedidos());
		panel.addListenerTablaRuta(new ListenerTablaRuta());
		panel.addListenerSeleccionPlanta(new ListenerSeleccionPlanta());
		plantas = listarTodosPlanta();
		grafo = p2;
		insumos = listarTodoInsumo();
		pedidos = listarTodoPedido();
		panel.addTablaPedidos(plantas.size());
		cargarTablaPedidos(pedidos);
		panel.addTablaInsumos(insumosAgregados.size());
		cargarTablaInsumo(insumosAgregados);
		ventana.setContentPane(panel);
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
					panel.setValoresTablaPedidos(fila, pedidos.get(fila).getIdPedido(), pedidos.get(fila).getDestino().getNombre(), pedidos.get(fila).getFechaSolicitud().toString(), pedidos.get(fila).getFechaEntrega().toString());
					
				}
			}
			else {
				panel.addTablaPedidos(0);					
			}
		}
	}
	
	private void cargarTablaRuta(List<String> listaRuta, Double min) {
		if( listaRuta.isEmpty()) {
			panel.addTablaRuta(0);
		}
		else {
			int cantRuta =  listaRuta.size();
			if(cantRuta > 0){
				panel.addTablaRuta(cantRuta);
				for(int fila=0; fila<cantRuta; fila++) {

					panel.setValoresTablaRuta(fila, listaRuta.get(fila),min);
					
				}
			}
			else {
				panel.addTablaRuta(0);	
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
					panel.setValoresTablaInsumos(fila, listaInsumo.get(fila).getInsumo().getNombre(), listaInsumo.get(fila).getCantidad(), listaInsumo.get(fila).getPrecio());
					
				}
			}
			else {
				panel.addTablaInsumos(0);	
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
			List<String> n = new ArrayList<String>();
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

	    		p = pedidos.get(row).getDestino();
	    		if(pt.isEmpty()) {
	    			pedidos.get(row).setEstado(EstadoPedido.CANCELADA);
	    			pedidoService.crearPedido(pedidos.get(row));
	    			pedidos.remove(row);
	    			cargarTablaPedidos(pedidos);

	    		}
	    		if(pt.isEmpty()) {
	    			JOptionPane.showMessageDialog(panel, "No se han encontrado plantas que tenga el stock disponible en todos los productos.", "Planta no encontrada", JOptionPane.INFORMATION_MESSAGE);	
	    			insumosAgregados.removeAll(insumosAgregados);
	    			panel.habilitarCampos(false);
	    			cargarTablaInsumo(insumosAgregados);
	    		}
	    		pedido = pedidos.get(row);
	    		List<Planta> auxiliar = new ArrayList<Planta>();
	    		Integer index2 = -1;
	    		for(Vertice<Planta> r :grafo.getVertices()) {
    				if(pedidos.get(row).getDestino().getIdPlanta() == r.getValor().getIdPlanta()) {
    					index2 = grafo.getVertices().indexOf(r);
    					break;
    				}
    			}
	    		

	    		if(!pt.isEmpty()) {
		    		for(Planta ps:pt) {
		    			Integer index = 0;
		    			for(Vertice<Planta> r :grafo.getVertices()) {
		    				if(ps.getIdPlanta() == r.getValor().getIdPlanta()) {
		    					index = grafo.getVertices().indexOf(r);
		    					break;
		    				}
		    			}		    			
		    			if(grafo.hayCamino(grafo.getVertices().get(index), grafo.getVertices().get(index2))) {
		    				auxiliar.add(ps);
		    			}

		    				
		    		}
	    		}
	    		pt.removeAll(pt);
	    		pt.addAll(auxiliar);
	    		if(auxiliar.isEmpty()) {
	    			JOptionPane.showMessageDialog(panel, "No se han encontrado rutas que permitan transportar el stock hacia la planta destino.", "Planta no encontrada", JOptionPane.INFORMATION_MESSAGE);	
	    		}
	    		panel.addPlantasDisponibles(auxiliar);
	        }
		}
		@Override public void mouseClicked(MouseEvent e) {} 
		@Override public void mouseReleased(MouseEvent e) {}
		@Override public void mouseEntered(MouseEvent e) {}
		@Override public void mouseExited(MouseEvent e) {}
	}
	
	private class ListenerTablaRuta implements MouseListener{			
		@Override
		public void mousePressed(MouseEvent e) {
	        JTable table = (JTable) e.getSource();
	        Point point = e.getPoint();
	        int row = panel.getRowTablaRuta(point);
	        cami = row;
	        click = point;
	        if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
	        	panel.habilitarGuardar(true);
	        	
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
	
	private class ListenerSeleccion implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {	
				if(!panel.getSeleccion().equals("Seleccione preferencia de ruta")){
					List<List<Ruta>> elegido = new ArrayList<List<Ruta>>();
					Boolean eligio = false;
					if(panel.getSeleccion().equals("Ruta mas corta en Km")) {
						elegido.addAll(plantaService.rutaMasCortaKm(ruts));
						
						eligio =true;
					}
					else if(panel.getSeleccion().equals("Ruta mas rapida en Hs")) {
						elegido.addAll(plantaService.rutaMasCortaHs(ruts));
						eligio =false;
					}
					
					List<String> imprimir = new ArrayList<String>();
					Double[] min = {0.0,0.0};
					for(List<Ruta> l : elegido) {
						String texto = "";
						for(int i = 0;i < l.size();i++) {
							min[0] +=l.get(i).getDistanciaKM();
							min[1] +=l.get(i).getDuracionHs();
							texto+= l.get(i).getOrigen().getNombre()+" --> ";
							if(i == l.size()-1)
								texto += l.get(i).getDestino().getNombre();
						}
						imprimir.add(texto);
					}
					costos = min;
					if(panel.getSeleccion().equals("Ruta mas corta en Km"))
						cargarTablaRuta(imprimir,min[0]);
					else if(panel.getSeleccion().equals("Ruta mas rapida en Hs"))
						cargarTablaRuta(imprimir, min[1]);
				}

			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(panel, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ListenerSeleccionPlanta implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				if(panel.getIndexOrigen() != -1) {
					Planta elegida = pt.get(panel.getIndexOrigen());
	
		    		Integer index = -1,index2 = -1;
		    		for(Vertice<Planta> r :grafo.getVertices()) {
		    			if(elegida.getIdPlanta() == r.getValor().getIdPlanta()) {
		    				index = grafo.getVertices().indexOf(r);
		    				break;
		    			}
		    		}
		    		for(Vertice<Planta> r :grafo.getVertices()) {
		    			if(pedidos.get(seleccion).getDestino().getIdPlanta() == r.getValor().getIdPlanta()) {
		    				index2 = grafo.getVertices().indexOf(r);
		    				break;
		    			}
		    		}
	   		
		    		List<List<Vertice<Planta>>> p = grafo.caminos(grafo.getVertices().get(index), grafo.getVertices().get(index2));
	
		    		
		    		for(List<Vertice<Planta>> lis : p) {
		    			List<Ruta> una = new ArrayList<Ruta>();
		    			for(int i = 0; i < lis.size()-1;i++) {
			    			for(Arista<Planta> a: grafo.getAristas()) {
			    				if(a.getInicio().getValor().getIdPlanta() == lis.get(i).getValor().getIdPlanta() && 
			    						a.getFin().getValor().getIdPlanta() == lis.get(i+1).getValor().getIdPlanta()) {
			    					Ruta r = new Ruta();
			    					r.setOrigen(a.getInicio().getValor());
			    					r.setDestino(a.getFin().getValor());
			    					r.setDistanciaKM(a.getKm());
			    					r.setDuracionHs(a.getHs());
			    					r.setPesoMaxKg(a.getMax());
			    					una.add(r);
			    					break;
			    				}
			    			}
		    			}
		    			ruts.add(una);
		    		}
					 for(int i = 0;i<ruts.size();i++) {
						 if(ruts.get(i).get(ruts.get(i).size()-1).getDestino().getIdPlanta() != pedidos.get(seleccion).getDestino().getIdPlanta() || 
								 ruts.get(i).get(0).getOrigen().getIdPlanta() != elegida.getIdPlanta() ) {
							 ruts.remove(i);
						 }
					 }
					 
					 List<List<Ruta>> auxiliar = new ArrayList<List<Ruta>>();
					 auxiliar.addAll(ruts);
					 

					 for(List<Ruta> s: ruts) {
						 if(s.get(s.size()-1).getDestino().getIdPlanta() != pedidos.get(seleccion).getDestino().getIdPlanta())
							 auxiliar.remove(s);
						 if(s.get(0).getOrigen().getIdPlanta() != elegida.getIdPlanta())
							 	auxiliar.remove(s);
					 }

					 ruts.removeAll(ruts);
					 ruts.addAll(auxiliar);

		    		List<String> x = new ArrayList<String>();
		    		panel.addCampos();
		    		cargarTablaRuta(x, 0.0);
				}
	    		
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(panel, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private class ListenerBtnGuardar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				pedido = pedidos.get(seleccion);
				pedido.setEstado(EstadoPedido.PROCESADA);
				pedido.setOrigen(ruts.get(cami).get(0).getOrigen());
				List<Pedido> procesados = pedidoService.listarTodoPedidoProcesado(insumos);
				PriorityQueue<Camion> auxilia =
						ruts.get(cami).get(0).getOrigen().getCamionesDisponibles();
				auxilia.addAll(camionService.busqueda(ruts.get(cami).get(0).getOrigen().getIdPlanta().toString()));
			
				Camion o = auxilia.poll();
				for(Pedido ps :procesados) {
					if(o.getId() == ps.getCamionAsignado().getId()) {
						o = auxilia.poll();
						break;
					}
				}
				Integer km =o.getKmRecorridosA();
				Integer a = costos[0].intValue();
				km += a;
				o.setKmRecorridos(km.toString());
				Double costo = 0.0;
				pedido.setCamionAsignado(o);
				if(panel.getSeleccion().equals("Ruta mas corta en Km")) {
					costo+= o.getCostoKM()*costos[0];
				}
				else if(panel.getSeleccion().equals("Ruta mas rapida en Hs"))
					costo+= o.getCostoHora()*costos[1];;
				pedido.setCostoEnvio(costo);
				pedidoService.crearPedido(pedido);
				pedidos.remove(pedido);
				insumosAgregados.removeAll(insumosAgregados);
				ruts.removeAll(ruts);
				List<String> pr = new ArrayList<String>();
				cargarTablaRuta(pr, 0.0);
				cargarTablaPedidos(pedidos);
				cargarTablaInsumo(insumosAgregados);
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(panel, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
	
	



