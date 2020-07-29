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


import died.ejemplos.dominio.Camion;
import died.ejemplos.gestor.GestorCamion;
import died.ejemplos.gui.ayuda.PanelAyuda;
import died.ejemplos.view.ViewBuscarCamion;
import died.ejemplos.view.ViewCamion;




public class BuscarCamionController{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private GestorCamion camionService;
	private Camion c;
	private List<Camion> lista;
	private ViewBuscarCamion panel;
//	private ViewCamion panel2;
	private JFrame ventana;
	private BuscarCamionController instancia;
	
	public BuscarCamionController(ViewBuscarCamion viewBuscarCamion, JFrame v){
		this.instancia = this;
		this.camionService = new GestorCamion();
		this.lista = new ArrayList<Camion>();
		this.ventana =v;
		this.panel = viewBuscarCamion;
		c = new Camion();
		panel.addListenerBtnCancelar(new ListenerBtnCancelar());
		panel.addListenerBtnBuscar(new ListenerBtnBuscar());
		panel.addListenerTablaCamiones(new ListenerTablaCamiones());
		panel.addListenerCampoPatente(new ListenerCampoPatente());
		ventana.setContentPane(panel);
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
		//System.out.println("Resultado res   "+lista);
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
	
	private class ListenerTablaCamiones implements MouseListener{			
		@Override
		public void mousePressed(MouseEvent e) {
	        JTable table = (JTable) e.getSource();
	        Point point = e.getPoint();
	        int row = panel.getRowTablaCamiones(point);
	        if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
	    		c = lista.get(row);
	    		System.out.println(c);
	    		ViewCamion ca = new ViewCamion(c,ventana);
	    		ca.setVisible(true);
	    		panel.setVisible(false);
	    		ventana.setContentPane(ca);	
	    		System.out.println("LISTENER 3");
	        }
		}
		@Override public void mouseClicked(MouseEvent e) {} 
		@Override public void mouseReleased(MouseEvent e) {}
		@Override public void mouseEntered(MouseEvent e) {}
		@Override public void mouseExited(MouseEvent e) {}
	}
	
	private class ListenerCampoPatente implements KeyListener{
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			if( ( Character.isDigit(caracter) || (caracter >='a' && caracter <= 'z') || (caracter >='A' && caracter <= 'Z') || caracter == 'ñ' || caracter == 'Ñ' )
					&& panel.getPatente().length() < 7 ){
				if(Character.isLowerCase(caracter)){
			    	 caracter = Character.toUpperCase(caracter);
				}
				e.setKeyChar(caracter);
			}
			else{
				e.consume();
			}
		}
		public void keyPressed(KeyEvent e) { }
		public void keyReleased(KeyEvent e) { }
			
		} 
	

}