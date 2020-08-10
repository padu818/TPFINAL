package tpdied2020.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import tpdied2020.dominio.DetallesInsumoSolicitado;
import tpdied2020.dominio.EstadoPedido;
import tpdied2020.dominio.Insumo;
import tpdied2020.dominio.Pedido;
import tpdied2020.dominio.Planta;
import tpdied2020.gestor.GestorInsumo;
import tpdied2020.gestor.GestorPedido;
import tpdied2020.gestor.GestorPlanta;
import tpdied2020.gui.util.ControllerException;
import tpdied2020.gui.util.DatosObligatoriosException;
import tpdied2020.gui.util.FormatoNumeroException;
import tpdied2020.view.ViewAltaOrdenPedido;

public class AltaOrdenPedidoController {
	
	private AltaOrdenPedidoController instancia;
	private GestorPlanta plantaService;
	private GestorInsumo insumoService;
	private GestorPedido pedidoService;
	private List<Planta> plantas;
	private List<Insumo> insumos;
	private Pedido pedido;
	private List<DetallesInsumoSolicitado> insumosAgregados;
	private ViewAltaOrdenPedido panel;
	private JFrame ventana;
	private Point click;
	private Planta p;
	
	public AltaOrdenPedidoController(ViewAltaOrdenPedido view, JFrame v) {
		
		this.instancia = this;
		this.plantaService = new GestorPlanta();
		this.insumoService = new GestorInsumo();
		this.pedidoService = new GestorPedido();
		this.plantas = new ArrayList<Planta>();
		this.insumos= new ArrayList<Insumo>();
		pedido = new Pedido();
		this.insumosAgregados = new ArrayList<DetallesInsumoSolicitado>();
		this.ventana =v;
		this.panel = view;
		panel.addListenerBtnGuardar(new ListenerBtnGuardar());
		panel.addListenerBtnCancelar(new ListenerBtnCancelar());
		panel.addListenerBtnEliminar(new ListenerBtnEliminar());
		panel.addListenerBtnAgregar(new ListenerBtnAgregar());
		panel.addListenerTablaPlantas(new ListenerTablaPlantas());
		panel.addListenerTablaInsumos(new ListenerTablaInsumo());
		panel.addListenerCampoCantidadInsumo(new ListenerCampoCantidadInsumo());
		plantas = listarTodosPlanta();
		insumos = listarTodoInsumo();
		panel.addTablaPlantas(plantas.size());
		cargarTablaPlanta(plantas);
		panel.addTablaInsumos(insumosAgregados.size());
		cargarTablaInsumo(insumosAgregados);
		panel.addInsumos(insumos);
		ventana.setContentPane(panel);
		
	}
	
	private void cargarTablaPlanta(List<Planta> plantas) {
		if(plantas.isEmpty()) {
			panel.addTablaPlantas(0);
		}
		else {
			int cantPlantas = plantas.size();
			if(cantPlantas > 0){
				panel.addTablaPlantas(cantPlantas);
				for(int fila=0; fila<cantPlantas; fila++) {
					panel.setValoresTablaPlantas(fila, plantas.get(fila).getIdPlanta(), plantas.get(fila).getNombre());
					
				}
			}
			else {
				panel.addTablaPlantas(0);	
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
					panel.setValoresTablaInsumos(fila, insumosAgregados.get(fila).getInsumo().getNombre(), insumosAgregados.get(fila).getCantidad(), insumosAgregados.get(fila).getPrecio());
					
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
	
	public List<Insumo> listarTodoInsumo(){
			this.insumos.clear();
			this.insumos.addAll(insumoService.buscarTodos()); 
			return this.insumos;
	}
	
	public Boolean verificarDatos() {

		
		String textoErrorFechaMax = "";
		String textoErrorCantidad = "";
		String textoErrorInsumo = "";
		Boolean errorEnInsumo = false;
		Boolean errorEnCantidad= false;
		Boolean errorEnFechaMax= false;
		String textoCantidad = panel.getCantidadInsumo();
		String textoFechaMax = panel.getFechaMax();
		Integer errorNumero = 1;
		
		if(textoFechaMax.isEmpty()) {
			errorEnFechaMax = true;
			textoErrorFechaMax = errorNumero+") Debe completar el campo fecha\n";
			errorNumero++;
		}
		if (!textoFechaMax.isEmpty()) {
			try {
				LocalDate localDate1 = LocalDate.parse(textoFechaMax, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				LocalDate hoy = LocalDate.now();
				if (localDate1.isBefore(hoy)) {
					errorEnFechaMax = true;
					textoErrorFechaMax = errorNumero+") La fecha debe ser futura. \n";
					errorNumero++;
				}
			} catch (Exception localDate1) {
				errorEnFechaMax = true;
				textoErrorFechaMax = errorNumero+") Compruebe la fecha ingresada. \n";
				errorNumero++;
			}

		}
		if (panel.getSeleccionInsumo().equals("Seleccionar Insumo")) {
			errorEnInsumo = true;
			textoErrorInsumo = errorNumero+") No se ha seleccionado un valor del campo Seleccion Insumo.\n";
			errorNumero++;
		}
		if(textoCantidad.isEmpty()) {
			errorEnCantidad = true;
			textoErrorCantidad = errorNumero+") Debe completar el campo Cantidad de insumo. \n";
			errorNumero++;
		}

		String mensajeError = textoErrorFechaMax+textoErrorInsumo+textoErrorCantidad;
		
		if( errorEnInsumo||errorEnCantidad||errorEnFechaMax) {
			panel.noValido( errorEnInsumo,errorEnCantidad,errorEnFechaMax);
			JOptionPane.showConfirmDialog(panel, mensajeError, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			panel.textnormal();
			return false;
		}
		return true;
	}
	
	
	public Boolean guardar() throws DatosObligatoriosException, FormatoNumeroException, ControllerException {
			pedido.setItems(insumosAgregados);
			pedido.setEstado(EstadoPedido.CREADA);
			pedido.setFechaSolicitud(LocalDate.now());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate aux = LocalDate.parse(this.panel.getFechaMax(),formatter);
			pedido.setFechaEntrega(aux);
			pedido.setDestino(p);
			pedidoService.crearPedido(pedido);
			return true;
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
	        	p = plantas.get(row);
	        	panel.setCampos(p.getIdPlanta(),p.getNombre());
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
				if(guardar()) {
					panel.limpiarFormularioTodo();
					insumosAgregados.removeAll(insumosAgregados);
					cargarTablaPlanta(plantas);
					cargarTablaInsumo(insumosAgregados);
					panel.addInsumos(insumos);
				}
			} catch (DatosObligatoriosException | FormatoNumeroException | ControllerException e1) {
				panel.mostrarError("Error al guardar", e1.getMessage());
			}
			
		}
	}
	
	private class ListenerTablaInsumo implements MouseListener{			
		@Override
		public void mousePressed(MouseEvent e) {
	        JTable table = (JTable) e.getSource();
	        Point point = e.getPoint();
	        int row = panel.getRowTablaInsumos(point);
	        click = point;
	        if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
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
					
				int row = panel.getRowTablaInsumos(click);
				insumosAgregados.remove(row);
				cargarTablaInsumo(insumosAgregados);
				panel.habilitarEliminar(false);
				if (insumosAgregados.isEmpty()) {
					panel.habilitarGuardar(false);
				}
		}
	}
	
	private class ListenerBtnAgregar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				if(agregar()) {
					panel.limpiarFormulario();
					panel.habilitarGuardar(true);
				}
			} catch (DatosObligatoriosException | FormatoNumeroException | ControllerException e1) {
				panel.mostrarError("Error al agregar", e1.getMessage());
			}
		}
	}
	
	public Boolean agregar() throws DatosObligatoriosException, FormatoNumeroException, ControllerException {
		if(this.verificarDatos()) {
			for (Insumo insumo : insumos) {
				if (insumo.getNombre().equals(panel.getSeleccionInsumo())) {
					DetallesInsumoSolicitado p = new DetallesInsumoSolicitado();
					p.setCantidad(Integer.parseInt(panel.getCantidadInsumo()));
					p.setPrecio(insumo.getCosto()*p.getCantidad());
					p.setInsumo(insumo);
					p.setPedido(pedido);
					insumosAgregados.add(p);
				}
			}
			cargarTablaInsumo(insumosAgregados);
			return true;
		}
		return false;
	}

}
