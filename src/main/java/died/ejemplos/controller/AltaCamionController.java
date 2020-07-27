package died.ejemplos.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import died.ejemplos.dominio.Camion;
import died.ejemplos.gestor.GestorCamion;
import died.ejemplos.gui.util.ControllerException;
import died.ejemplos.gui.util.DatosObligatoriosException;
import died.ejemplos.gui.util.FormatoNumeroException;
import died.ejemplos.view.ViewAltaCamion;

public class AltaCamionController {
	
	private GestorCamion camionService;
	private Camion c;
	private ViewAltaCamion panel;
	private AltaCamionController instancia;
	
	public AltaCamionController(ViewAltaCamion p) {
		this.camionService = new GestorCamion();
		this.panel = p;
		panel.addListenerBtnCancelar(new ListenerBtnCancelar());
		panel.addListenerBtnGuardar(new ListenerBtnGuardar());
		panel.addListenerCampoPatente(new ListenerCampoPatente());
	}
	
	public Boolean verificarDatos() {
			String textoErrorMarca = "";
			String textoErrorModelo = "";
			String textoErrorCostoHs = "";
			String textoErrorCostoKm = "";
			String textoErrorFecha = "";
			String textoErrorPatente = "";
			String textoErrorKm = "";
			Boolean errorEnPatente = false;
			Boolean errorEnkm = false;
			Boolean errorEnModelo = false;
			Boolean errorEnMarca = false;
			Boolean errorEnFecha = false;
			Boolean errorEnCostokm = false;
			Boolean errorEnCostohs = false;
			String textoPatente = panel.getCampoPatente();
			String textoMarca = panel.getCampoMarca();
			String textoModelo = panel.getCampoModelo();
			String textoCostoHs = panel.getCampoCostoHs();
			String textoCostokm = panel.getCampoCostoKm();
			String textoFecha = panel.getCampoFechaCompra();
			Integer errorNumero = 1;
			
			
			
			
			
			//---------- posible error en la introducción del número de patente
			if(!textoPatente.isEmpty()) {
				switch (textoPatente.length()) {
			        case 6: //para patente longitud 6
			        	for(int i = 0; i < 6; i++) {	        		
				        	switch(i) {
				        		case 0: case 1: case 2:
				    				if(!Character.isLetter(textoPatente.charAt(i))) {
				    					errorEnPatente = true;
				    					textoErrorPatente = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 6 es LLL999, donde\n"
				    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";
				    					errorNumero++;
				    				}
				        		break;
				        		
				        		case 3: case 4: case 5:
				    				if(!Character.isDigit(textoPatente.charAt(i))) {
				    					errorEnPatente = true;
				    					textoErrorPatente = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 6 es LLL999, donde\n"
				    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";
				    					errorNumero++;
				    				}
				        		break;
				        	}
				        	if(errorEnPatente) {
				        		break;
				        	}
			        	}
						if(!errorEnPatente) {
							if(camionService.validarPatente(textoPatente)) {
								errorEnPatente = true;
								textoErrorPatente = errorNumero+") El valor ingresado del número de patente, ya está registrado.\n";
								errorNumero++;
							}
						}
			        break;     
			        
			        case 7:  //para patente longitud 7
			        	for(int j = 0; j < 7; j++) {
				        	switch(j) {
				        		case 0: case 1: case 5: case 6:
				    				if(Character.isDigit(textoPatente.charAt(j))) {
				    					errorEnPatente = true;
				    					textoErrorPatente = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 7 es LL999LL, donde\n"
				    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";
				    					errorNumero++;
				    				}
				        		break;
				        
				        		case 2: case 3: case 4:
				    				if(Character.isLetter(textoPatente.charAt(j))) {
				    					errorEnPatente = true;
				    					textoErrorPatente = errorNumero+") Formato de patente incorrecto. El formato de una patente con longitud 7 es LL999LL, donde\n"
				    							+ "las L indican que debe escribirse un letra y los 9 indican que debe escribirse un dígito.\n";	
				    					errorNumero++;
				    				}
				        		break;
				        	}		    
				        	if(errorEnPatente) {
				        		break;
				        	}
			        	}
						if(!errorEnPatente) {
							if(camionService.validarPatente(textoPatente)) {
								errorEnPatente = true;
								textoErrorPatente = errorNumero+") El valor ingresado del número de patente, ya está registrado.\n";
								errorNumero++;
							}
						}
			        break;
		
			        default:
			        	errorEnPatente = true;
			        	textoErrorPatente = errorNumero+") La definición de una patente debe ser de longitud 6 o 7.\n";
						errorNumero++;
					break;
				}	
			}
			else if(textoPatente.isEmpty()) {
				errorEnPatente = true;
	        	textoErrorPatente = errorNumero+") La definición de una patente debe ser de longitud 6 o 7.\n";
				errorNumero++;
			}
			
			
			if(textoMarca.isEmpty()) {
				errorEnMarca = true;
				textoErrorMarca = errorNumero+") Debe completar el campo marca\n";
				errorNumero++;
			}
			if(textoModelo.isEmpty()) {
				errorEnModelo = true;
				textoErrorModelo = errorNumero+") Debe completar el campo modelo\n";
				errorNumero++;
			}
			
			//---------- posible error en la no selección de un kilometraje
			if (panel.getSeleccionKm().equals("Selecionar kilometraje")) {
				errorEnkm = true;
				textoErrorKm = errorNumero+") No se ha seleccionado un valor del campo km.\n";
			}
			
			
			if(textoCostoHs.isEmpty()) {
				errorEnCostohs = true;
				textoErrorCostoHs = errorNumero+") Debe completar el campo Costo por Hora\n";
				errorNumero++;
			}
			if(textoCostokm.isEmpty()) {
				errorEnCostokm = true;
				textoErrorCostoKm = errorNumero+") Debe completar el campo Costo por km\n";
				errorNumero++;
			}
			if(textoFecha.isEmpty()) {
				errorEnFecha = true;
				textoErrorFecha = errorNumero+") Debe completar el campo fecha\n";
				errorNumero++;
			}
			
			String mensajeError =  textoErrorPatente+textoErrorMarca+textoErrorModelo+textoErrorKm+textoErrorCostoHs+textoErrorCostoKm + textoErrorFecha;
			
			if( errorEnPatente || errorEnkm || errorEnCostohs || errorEnCostokm || errorEnFecha || errorEnMarca|| errorEnModelo ||errorEnCostohs) {
				panel.noValido( errorEnPatente,errorEnCostohs,errorEnCostokm, errorEnFecha, errorEnMarca, errorEnModelo,errorEnCostohs);
				JOptionPane.showConfirmDialog(panel, mensajeError, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
				panel.textnormal();
				return false;
			}
			return true;
	}
	
	public Boolean guardar() throws DatosObligatoriosException, FormatoNumeroException, ControllerException {
		if(this.verificarDatos()) {
			Camion c = new Camion();
			c.setPatente(this.panel.getCampoPatente()); 
			c.setModelo(this.panel.getCampoModelo());
			c.setMarca(this.panel.getCampoMarca()); 
			c.setCostoHora(Double.valueOf(this.panel.getCampoCostoHs()));
			c.setCostoKM(Double.valueOf(this.panel.getCampoCostoKm()));
			c.setKm(this.panel.getSeleccionKm());
			camionService.crearCamion(c);
			return true;
		}
		return false;
	}
	
	
	
	
	//Listener
	
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
	
	private class ListenerCampoPatente implements KeyListener{
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			if( ( Character.isDigit(caracter) || (caracter >='a' && caracter <= 'z') || (caracter >='A' && caracter <= 'Z') || caracter == 'ñ' || caracter == 'Ñ' )
					&& panel.getCampoPatente().length() < 7 ){
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
