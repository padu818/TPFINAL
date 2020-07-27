package died.ejemplos.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import died.ejemplos.dominio.Camion;
import died.ejemplos.gestor.GestorCamion;
import died.ejemplos.gui.util.ControllerException;
import died.ejemplos.gui.util.DatosObligatoriosException;
import died.ejemplos.gui.util.FormatoNumeroException;
import died.ejemplos.view.ViewBuscarCamion;



public class BuscarCamionController {
	private GestorCamion camionService;
	private Camion c;
	private List<Camion> lista;
	private ViewBuscarCamion panel;
	private BuscarCamionController instancia;
	
	public BuscarCamionController(ViewBuscarCamion p) {
		this.camionService = new GestorCamion();
		this.lista = new ArrayList<Camion>();
		this.panel = p;
		c = new Camion();
		panel.addListenerBtnCancelar(new ListenerBtnCancelar());
		panel.addListenerBtnBuscar(new ListenerBtnBuscar());
//		panel.addListenerTablaClientes(new ListenerTablaClientes());
//		panel.addListenerCampoNumeroCliente(new ListenerCampoNumeroCliente());
//		panel.addListenerCampoApellido(new ListenerCampoApellido());
//		panel.addListenerCampoNombre(new ListenerCampoNombre());
//		panel.addListenerCampoNumeroDocumento(new ListenerCampoNumeroDocumento());
	}
	
	
	private void cargarTabla(List<Camion> camiones) {
		if(camiones.isEmpty()) {
			panel.addTablaCamiones(0);
		}
		else {
			int cantCamiones = camiones.size();
			if(cantCamiones > 0){
				panel.addTablaCamiones(cantCamiones);
				for(int fila=0; fila<cantCamiones; fila++) {
					Camion ca = camiones.get(fila);
					System.out.println(ca);

					panel.setValoresTablaCamiones(fila, ca.getPatente(), ca.getMarca(), ca.getModelo(), ca.getKm(), ca.getCostoHora(), ca.getCostoKM(),ca.getFechaCompra());
				}
			}
			else {
				panel.addTablaCamiones(0);
				JOptionPane.showMessageDialog(panel, "No se han encontrado camiones que cumplan con ese criterio de búsqueda.", "Camion no encontrado", JOptionPane.INFORMATION_MESSAGE);	
			}
		}
	}
	
//	public void actualizarModelo() throws DatosObligatoriosException,FormatoNumeroException,ControllerException {
//		try {
//			if(this.panel.getPatente()!=null) {
//				c.setPatente(this.panel.getTxtPatente().getText()); 
//			} else {
//				throw new DatosObligatoriosException("Patente", "La patente es obligatoria");
//			}
//			if(this.panel.getTxtModelo()!=null) c.setModelo(this.panel.getTxtModelo().getText()); 
//			if(this.panel.getTxtMarca()!=null) c.setMarca(this.panel.getTxtMarca().getText()); 
//			if(this.panel.getTxtKm()!=null) c.setKm(Integer.valueOf(this.panel.getTxtKm().getText())); 
//			//if(this.panel.getTxtFechaCompra()!=null) c.setPatente(this.panel.getFechaCompra.getText()); 
//		} catch(NumberFormatException nfe) {
//			nfe.printStackTrace();
//			throw new FormatoNumeroException("Kilometros", "Debe ingresar un valor numerico");
//		} catch(Exception e) {
//			e.printStackTrace();
//			throw new ControllerException("Error:"+e.getLocalizedMessage());
//		}
//	}
	public List<Camion> listarTodos(){
		this.lista.clear();
		this.lista.addAll(camionService.buscarTodos()); 
		System.out.println("Resultado res   "+lista);
		return this.lista;
	}



	
	//-------- LISTENER
	
	
	private class ListenerBtnBuscar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				
				String patente = panel.getPatente();
				String marca = panel.getMarca();
				String modelo = panel.getModelo();
				
				String kmRec = panel.getKmRecorrido();
				String cosths = panel.getCostoPorHora();
				String costkm = panel.getCostoPorKm();
				String fecha = panel.getFechaCompra();
				
				
				lista = camionService.busqueda(patente,marca,modelo,kmRec,cosths,costkm,fecha);
				cargarTabla(lista);
				System.out.println(lista);
				
//				panel.setPatente();
//				panel.setMarca();
//				panel.setModelo();
//				panel.setKmRecorrido();
//				panel.setCostoPorHora();
//				panel.setCostoPorKm();
//				panel.setFechaCompra();
					
			}catch(Exception ex) {
				 JOptionPane.showMessageDialog(null, "No se pudo obtener el camion desde la base de datos",
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