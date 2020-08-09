package died.ejemplos.controller;


import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;


import died.ejemplos.dominio.Camion;
import died.ejemplos.dominio.Planta;
import died.ejemplos.dominio.Ruta;
import died.ejemplos.gestor.GestorCamion;
import died.ejemplos.gestor.GestorPlanta;
import died.ejemplos.gui.ayuda.Arista;
import died.ejemplos.gui.ayuda.GrafoPlanta;
import died.ejemplos.gui.util.ControllerException;
import died.ejemplos.gui.util.DatosObligatoriosException;
import died.ejemplos.gui.util.FormatoNumeroException;
import died.ejemplos.view.ViewAltaCamion;
import died.ejemplos.view.ViewCamion;
import died.ejemplos.view.ViewAgregarRuta;

public class AltaRutaController {

	private GestorPlanta plantaService;
	private Planta p;
	private Integer origen;
	private Integer destino;
	private Point click;
	private List<Planta> auxi;
	private ViewAgregarRuta panel;
	private AltaRutaController instancia;
	private GrafoPlanta plantas;
	
	public AltaRutaController(ViewAgregarRuta p, GrafoPlanta p2) {
		this.plantaService = new GestorPlanta();
		this.panel = p;
		
		origen =-1;
		destino =-1;

		
		panel.addListenerBtnEliminarRuta(new ListenerBtnEliminarRuta());
		panel.addListenerTablaRuta(new ListenerTablaRuta());
		panel.addListenerBtnCancelar(new ListenerBtnCancelar());
		panel.addListenerBtnGuardar(new ListenerBtnGuardar());
		panel.addListenerCampoDistanciaHs(new ListenerCampoDistanciaHs());
		panel.addListenerCampoDistanciaKm(new ListenerCampoDistanciaKm());
		panel.addListenerCampoMaximo(new ListenerCampoMaximo());
		auxi = plantaService.buscarTodos();
		plantas = new GrafoPlanta();
		plantas = p2;
		panel.addOrigen(auxi);
		panel.addDestino(auxi);
		cargarTabla(plantas);
	}
	
	
	
	public Boolean verificarDatos() {
		String textoErrorOrigen = "";
		String textoErrorDestino = "";
		String textoErrorHs = "";
		String textoErrorKm = "";
		String textoErrorMaximo = "";
		String textoErrorPlanta = "";
		Boolean errorEnOrigen = false;
		Boolean errorEnDestino= false;
		Boolean errorEnHs = false;
		Boolean errorEnKm = false;
		Boolean errorEnMaximo = false;
		Boolean errorPlanta = false;
		

		String textoHs = panel.getDistanciaHs();
		String textokm = panel.getDistanciaKm();
		String textoMaximo = panel.getMaximo();
		Integer errorNumero = 1;
		
		

		if (panel.getPlantaOrigen().equals("Seleccionar Planta")) {
			errorEnOrigen = true;
			textoErrorOrigen = errorNumero+") No se ha seleccionado un valor del campo Planta origen.\n";
			errorNumero++;
		}
		
		if (panel.getPlantaDestino().equals("Seleccionar Planta")) {
			errorEnDestino = true;
			textoErrorDestino = errorNumero+") No se ha seleccionado un valor del campo Planta destino.\n";
			errorNumero++;
		}
		
		if(panel.getPlantaDestino().equals(panel.getPlantaOrigen())) {
			errorPlanta = true;
			textoErrorPlanta = errorNumero+") La planta origen y la planta destino no pueden ser la misma. \n";
			errorNumero++;
		}
		
		if(textoHs.isEmpty()) {
			errorEnHs = true;
			textoErrorHs = errorNumero+") Debe completar el campo Distancia Hs\n";
			errorNumero++;
		}
		
		
		if(textokm.isEmpty()) {
			errorEnKm = true;
			textoErrorKm = errorNumero+") Debe completar el campo Distancia Km\n";
			errorNumero++;
		}
		if(textoMaximo.isEmpty()) {
			errorEnMaximo = true;
			textoErrorMaximo = errorNumero+") Debe completar el campo Maximo\n";
			errorNumero++;
		}
		String mensajeError = textoErrorOrigen+textoErrorDestino+textoErrorPlanta+ textoErrorHs+textoErrorKm+textoErrorMaximo;
		
		if( errorEnDestino||errorEnHs||errorEnKm||errorEnMaximo||errorEnOrigen||errorPlanta) {
			JOptionPane.showConfirmDialog(panel, mensajeError, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
}
	
	public Boolean guardar() throws DatosObligatoriosException, FormatoNumeroException, ControllerException {
		if(this.verificarDatos()) {
		//	System.out.println(this.panel.getClass());
			Ruta r = new Ruta();
			r.setOrigen(auxi.get(this.panel.getIndexOrigen())); 
		//	System.out.println(auxi.get(this.panel.getIndexOrigen()).getNombre());
			r.setDestino(auxi.get(this.panel.getIndexDestino()));
			r.setDuracionHs(Double.valueOf(this.panel.getDistanciaHs())); 
			r.setDistanciaKM(Double.valueOf(this.panel.getDistanciaKm())); 
			r.setPesoMaxKg(Double.valueOf(this.panel.getMaximo()));
			plantas.conectar(r.getOrigen(), r.getDestino(), r.getDuracionHs(), r.getDistanciaKM(), r.getPesoMaxKg());
			plantaService.crearRuta(r);
			cargarTabla(plantas);
			return true;
		}
		return false;
	}
	
	
	
	private void cargarTabla(GrafoPlanta p) {
		if(p.getAristas().isEmpty()) {
			panel.addTablaRuta(0);
		}
		else {
			int cantRuta = p.getAristas().size();
			if(cantRuta > 0){
				panel.addTablaRuta(cantRuta);
				int fila = 0;
				for(Arista<Planta> a: p.getAristas()) {
					panel.setValoresTablaRuta(fila, a.getInicio().getValor().getNombre(), a.getFin().getValor().getNombre(), a.getHs(),a.getKm(),a.getMax());
					fila++;
				}
			}
			else {
				panel.addTablaRuta(0);
				JOptionPane.showMessageDialog(panel, "No se han encontrado camiones que cumplan con ese criterio de búsqueda.", "Camion no encontrado", JOptionPane.INFORMATION_MESSAGE);	
			}
		}
	}
	
	
	
	
	
	private class ListenerTablaRuta implements MouseListener{			
		@Override
		public void mousePressed(MouseEvent e) {
	        JTable table = (JTable) e.getSource();
	        Point point = e.getPoint();
	        int row = panel.getRowTablaRuta(point);
	        click = point;
	        if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
	        	origen = plantas.getAristas().get(row).getInicio().getValor().getIdPlanta();
	        	destino = plantas.getAristas().get(row).getFin().getValor().getIdPlanta();
	    		panel.habilitarEliminar(true);
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
				if(guardar())
					panel.limpiarFormulario();
			} catch (DatosObligatoriosException | FormatoNumeroException | ControllerException e1) {
				panel.mostrarError("Error al guardar", e1.getMessage());
			}
			
		}
	}
	
	private class ListenerBtnEliminarRuta implements ActionListener{
		public void actionPerformed(ActionEvent e) {
				if(origen != -1 && destino != -1) {
					plantaService.borrar(origen,destino);

				    int row = panel.getRowTablaRuta(click);
					plantas.getAristas().remove(row);
					cargarTabla(plantas);
					
				}
				panel.habilitarEliminar(false);
		}
	}
	
		private class ListenerCampoDistanciaKm implements KeyListener{
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if( ( Character.isDigit(caracter)  )
						&& panel.getDistanciaKm().length() < 10 ){

					e.setKeyChar(caracter);
				}
				else{
					e.consume();
				}
			}
			public void keyPressed(KeyEvent e) { }
			public void keyReleased(KeyEvent e) { }
				
			} 
	private class ListenerCampoDistanciaHs implements KeyListener{
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			if( ( Character.isDigit(caracter)  )
					&& panel.getDistanciaHs().length() < 10 ){

				e.setKeyChar(caracter);
			}
			else{
				e.consume();
			}
		}
		public void keyPressed(KeyEvent e) { }
		public void keyReleased(KeyEvent e) { }
			
		} 
	private class ListenerCampoMaximo implements KeyListener{
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			if( ( Character.isDigit(caracter)  )
					&& panel.getMaximo().length() < 10 ){

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
