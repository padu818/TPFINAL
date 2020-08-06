package died.ejemplos.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import died.ejemplos.dominio.Ruta;
import died.ejemplos.dominio.StockInsumo;
import died.ejemplos.gestor.GestorInsumo;
import died.ejemplos.gestor.GestorPlanta;
import died.ejemplos.gui.util.ControllerException;
import died.ejemplos.gui.util.DatosObligatoriosException;
import died.ejemplos.gui.util.FormatoNumeroException;
import died.ejemplos.view.ViewActualizarStock;
import died.ejemplos.view.ViewAltaInsumo;
import died.ejemplos.view.ViewVisualizarInsumo;

public class ActualizarStockController {
	
	private GestorPlanta plantaService;
	private GestorInsumo insumoService;
	private Planta p;
	private Integer idInsumo;
	private Integer idPlanta;
	private Point click;
	private List<Planta> lista;
	private List<Insumo> insumos;
	private List<StockInsumo> listaStock;
	private ViewActualizarStock panel;
	private JFrame ventana;
	private ActualizarStockController instancia;
	
	public ActualizarStockController(ViewActualizarStock view, JFrame v){
		this.instancia = this;
		this.plantaService = new GestorPlanta();
		this.insumoService = new GestorInsumo();
		this.lista = new ArrayList<Planta>();
		this.insumos= new ArrayList<Insumo>();
		this.listaStock = new ArrayList<StockInsumo>();
		this.ventana =v;
		this.panel = view;
		panel.addListenerBtnGuardar(new ListenerBtnGuardar());
		panel.addListenerBtnCancelar(new ListenerBtnCancelar());
		panel.addListenerBtnEliminar(new ListenerBtnEliminar());
		panel.addListenerTablaPlantas(new ListenerTablaPlantas());
		panel.addListenerTablaInsumos(new ListenerTablaStockInsumo());
		panel.addListenerCampoCantidadInsumo(new ListenerCampoCantidadInsumo());
		panel.addListenerCampoPuntoPedido(new ListenerCampoPuntoPedido());
		lista = listarTodos();
		insumos = listarTodoInsumo();
		panel.addTablaPlantas(lista.size());
		cargarTabla(lista);
		panel.addTablaInsumos(lista.size());
		cargarTablaStock(listaStock);
		panel.addInsumos(insumos);
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
	
	public List<Insumo> listarTodoInsumo(){
		//	this.insumos.clear();
			this.insumos.addAll(insumoService.buscarTodos()); 
			return this.insumos;
	}
	
	public Boolean verificarDatos() {
		String textoErrorInsumo = "";
		String textoErrorCantidad = "";
		String textoErrorPunto = "";
		Boolean errorEnInsumo = false;
		Boolean errorEnCantidad= false;
		Boolean errorEnPunto= false;
		String textoCantidad = panel.getCantidadInsumo();
		String textoPunto = panel.getPuntoPedido();
		Integer errorNumero = 1;
		
		

		if (panel.getSeleccionInsumo().equals("Seleccionar Planta")) {
			errorEnInsumo = true;
			textoErrorInsumo = errorNumero+") No se ha seleccionado un valor del campo Seleccion Insumo.\n";
			errorNumero++;
		}
		
		if(textoCantidad.isEmpty()) {
			errorEnCantidad = true;
			textoErrorCantidad = errorNumero+") Debe completar el campo Cantidad de insumo. \n";
			errorNumero++;
		}
		
		if(textoPunto.isEmpty()) {
			errorEnPunto = true;
			textoErrorPunto = errorNumero+") Debe completar el campo Punto de pedido. \n";
			errorNumero++;
		}

		String mensajeError = textoErrorInsumo+textoErrorCantidad+textoErrorPunto;
		
		if( errorEnInsumo||errorEnCantidad||errorEnPunto) {
			JOptionPane.showConfirmDialog(panel, mensajeError, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	public Boolean guardar() throws DatosObligatoriosException, FormatoNumeroException, ControllerException {
		if(this.verificarDatos()) {
			StockInsumo s = new StockInsumo();
			s.setCantidad(Integer.valueOf(this.panel.getCantidadInsumo()));
			s.setPuntoReposicion(Integer.valueOf(this.panel.getPuntoPedido()));
			s.setPlanta(p);
			s.setInsumo(insumos.get(this.panel.getIndexInsumo()));
			insumoService.crearSockInsumo(s);
			listaStock = insumoService.busquedaStockInsumos(p);
			
//			listaStock = insumoService.busqueda(insum,p.getIdPlanta().toString());
			cargarTablaStock(listaStock);
			return true;
		}
		return false;
	}
	
	
	private class ListenerTablaPlantas implements MouseListener{			
		@Override
		public void mousePressed(MouseEvent e) {
	        JTable table = (JTable) e.getSource();
	        Point point = e.getPoint();
	        int row = panel.getRowTablaPlantas(point);
	        click = point;
	        if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
	        	panel.habilitarCampos(true);
	        	p = lista.get(row);
	        	panel.setCampos(p.getIdPlanta(),p.getNombre());
	        	listaStock = insumoService.busquedaStockInsumos(p);
	        	cargarTablaStock(listaStock);
	        }
		}
		@Override public void mouseClicked(MouseEvent e) {} 
		@Override public void mouseReleased(MouseEvent e) {}
		@Override public void mouseEntered(MouseEvent e) {}
		@Override public void mouseExited(MouseEvent e) {}
	}
	
	private class ListenerCampoCantidadInsumo implements KeyListener{
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			if( ( Character.isDigit(caracter)  )
					&& panel.getCantidadInsumo().length() < 10 ){

				e.setKeyChar(caracter);
			}
			else{
				e.consume();
			}
		}
		public void keyPressed(KeyEvent e) { }
		public void keyReleased(KeyEvent e) { }
			
	}
	
	private class ListenerCampoPuntoPedido implements KeyListener{
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			if( ( Character.isDigit(caracter)  )
					&& panel.getPuntoPedido().length() < 10 ){

				e.setKeyChar(caracter);
			}
			else{
				e.consume();
			}
		}
		public void keyPressed(KeyEvent e) { }
		public void keyReleased(KeyEvent e) { }
			
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
				if(guardar())
					panel.limpiarFormulario();
			} catch (DatosObligatoriosException | FormatoNumeroException | ControllerException e1) {
				panel.mostrarError("Error al guardar", e1.getMessage());
			}
			
		}
	}
	
	private class ListenerTablaStockInsumo implements MouseListener{			
		@Override
		public void mousePressed(MouseEvent e) {
	        JTable table = (JTable) e.getSource();
	        Point point = e.getPoint();
	        int row = panel.getRowTablaInsumos(point);
	        click = point;
	        if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
//	        	System.out.println(listaStock.get(row));
	        	idInsumo = listaStock.get(row).getInsumo().getIdProduto();
	        	idPlanta = listaStock.get(row).getPlanta().getIdPlanta();
//	        	origen = plantas.getAristas().get(row).getInicio().getValor().getIdPlanta();
//	        	destino = plantas.getAristas().get(row).getFin().getValor().getIdPlanta();
	    		panel.habilitarEliminar(true);
	        }
		}
		@Override public void mouseClicked(MouseEvent e) {} 
		@Override public void mouseReleased(MouseEvent e) {}
		@Override public void mouseEntered(MouseEvent e) {}
		@Override public void mouseExited(MouseEvent e) {}
	}
	
	private class ListenerBtnEliminar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
				if(idInsumo != -1 && idPlanta != -1) {
					insumoService.borrar(idPlanta,idInsumo);

				    int row = panel.getRowTablaInsumos(click);
					listaStock.remove(row);
					cargarTablaStock(listaStock);
					
				}
				panel.habilitarEliminar(false);
		}
	}
	
}
