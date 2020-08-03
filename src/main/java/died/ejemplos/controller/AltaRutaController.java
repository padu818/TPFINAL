package died.ejemplos.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import died.ejemplos.dominio.Camion;
import died.ejemplos.dominio.Planta;
import died.ejemplos.dominio.Ruta;
import died.ejemplos.gestor.GestorCamion;
import died.ejemplos.gestor.GestorPlanta;
import died.ejemplos.gui.ayuda.GrafoPlanta;
import died.ejemplos.gui.util.ControllerException;
import died.ejemplos.gui.util.DatosObligatoriosException;
import died.ejemplos.gui.util.FormatoNumeroException;
import died.ejemplos.view.ViewAltaCamion;
import died.ejemplos.view.ViewAgregarRuta;

public class AltaRutaController {

	private GestorPlanta plantaService;
	private Planta p;
	private ViewAgregarRuta panel;
	private AltaRutaController instancia;
	private GrafoPlanta plantas;
	
	public AltaRutaController(ViewAgregarRuta p) {
		this.plantaService = new GestorPlanta();
		this.panel = p;
//		panel.addListenerBtnCancelar(new ListenerBtnCancelar());
//		panel.addListenerBtnGuardar(new ListenerBtnGuardar());
//		panel.addListenerBtnCancelar(new ListenerBtnGuardarRuta());
//		panel.addListenerBtnGuardar(new ListenerBtnEditarRuta());
		panel.addListenerBtnCancelar(new ListenerBtnCancelar());
		panel.addListenerBtnGuardar(new ListenerBtnGuardar());
		panel.addListenerCampoDistanciaHs(new ListenerCampoDistanciaHs());
		panel.addListenerCampoDistanciaKm(new ListenerCampoDistanciaKm());
		panel.addListenerCampoMaximo(new ListenerCampoMaximo());
		List<Planta> aux = plantaService.buscarTodos();
//		for(Planta i : aux)
//			plantas.addNodo(i);
		panel.addOrigen(aux);
		panel.addDestino(aux);
	}
	
	
	
	public Boolean verificarDatos() {
		String textoErrorOrigen = "";
		String textoErrorDestino = "";
		String textoErrorHs = "";
		String textoErrorKm = "";
		String textoErrorMaximo = "";
		Boolean errorEnOrigen = false;
		Boolean errorEnDestino= false;
		Boolean errorEnHs = false;
		Boolean errorEnKm = false;
		Boolean errorEnMaximo = false;

		String textoHs = panel.getDistanciaHs();
		String textokm = panel.getDistanciaKm();
		String textoMaximo = panel.getMaximo();
		Integer errorNumero = 1;
		
		

		if (panel.getPlantaOrigen().equals("Selecionar Planta")) {
			errorEnOrigen = true;
			textoErrorOrigen = errorNumero+") No se ha seleccionado un valor del campo Planta origen.\n";
		}
		
		if (panel.getPlantaDestino().equals("Selecionar Planta")) {
			errorEnDestino = true;
			textoErrorDestino = errorNumero+") No se ha seleccionado un valor del campo Planta destino.\n";
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
		String mensajeError = textoErrorOrigen+textoErrorDestino+ textoErrorHs+textoErrorKm+textoErrorMaximo;
		
		if( errorEnDestino||errorEnHs||errorEnKm||errorEnMaximo||errorEnOrigen) {
			JOptionPane.showConfirmDialog(panel, mensajeError, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
}
	
	public Boolean guardar() throws DatosObligatoriosException, FormatoNumeroException, ControllerException {
		if(this.verificarDatos()) {
			System.out.println(this.panel.getClass());
			Ruta r = new Ruta();
			if(this.panel.getIndexOrigen() != -1)
			//	r.setOrigen(plantas.getNodo(this.panel.getIndexOrigen())); 
			//r.setDestino(this.panel.getPlantaDestino());
			r.setDuracionHs(Double.valueOf(this.panel.getDistanciaHs())); 
			r.setDistanciaKM(Double.valueOf(this.panel.getDistanciaKm())); 
			r.setPesoMaxKg(Double.valueOf(this.panel.getMaximo()));
	
			plantaService.crearRuta(r);
			return true;
		}
		return false;
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
//			try {
//				if(guardar())
//		//			panel.limpiarFormulario();
//			} catch (DatosObligatoriosException | FormatoNumeroException | ControllerException e1) {
//		//		panel.mostrarError("Error al guardar", e1.getMessage());
//			}
//			
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
