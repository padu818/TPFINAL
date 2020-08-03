package died.ejemplos.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import died.ejemplos.dominio.Camion;
import died.ejemplos.dominio.Planta;
import died.ejemplos.gestor.GestorCamion;
import died.ejemplos.gestor.GestorPlanta;
import died.ejemplos.view.ViewBuscarCamion;
import died.ejemplos.view.ViewCamion;




public class BuscarCamionController2{
	
	private GestorCamion camionService;
	private ViewCamion panel;
	private BuscarCamionController controller1;
	private BuscarCamionController2 controller2;
	private GestorPlanta plantaService;
	
	private JFrame ventana;
	private JPanel panelAnterior;
	private JPanel panelEditar;
	private Boolean editando = false;
	private Camion camion;
		
	public BuscarCamionController2(ViewCamion v , Camion c, JFrame k) {
		this.camionService = new GestorCamion();
		this.camion = c;
		this.panel = v;
		this.panelEditar = v;
		this.ventana = k;
		panelAnterior = (JPanel) k.getContentPane();
		this.plantaService = new GestorPlanta();
		List<Planta> aux = plantaService.buscarTodos();
		panel.addSeleccionPlanta(aux);
		setView2();
	}
	
	private void setView2() {
		//System.out.println(this.camion);
		cargarCamionSeleccionado(this.camion);
		panel.addListenerBtnVolver(new ListenerVolver());
		panel.addListenerBtnEditar(new ListenerEditar());
		panel.addListenerBtnGuardar(new ListenerGuardar());
		panel.addListenerBtnEliminar(new ListenerEliminar());
		panel.addListenerCampoPatente(new ListenerCampoPatente());
		panel.addListenerCampoCostoKm(new ListenerCampoCostoKm());
		panel.addListenerCampoCostoHs(new ListenerCampoCostoHs());
	//	ventana.setContentPane(panel);
	}
	

	private void cargarCamionSeleccionado(Camion camion) {
		panel.setCampoPatente(camion.getPatente());
		panel.setCampoMarca(camion.getMarca());
		panel.setCampoModelo(camion.getModelo());
		panel.setCampoSeleccionKm(camion.getKm());
		panel.setCampoCostoHs(camion.getCostoHora().toString());
		panel.setCampoCostoKm(camion.getCostoKM().toString());
		panel.setCampoFechaCompra(camion.getFechaCompra().toString());
		panel.setCampoID(camion.getId().toString());
//		panel.setSeleccionPlanta(camion.getSeleccionPlanta());
	}


	public void setBuscarCamionController(BuscarCamionController instancia) {
		this.controller1 = controller1;
	}
	
	
	//listener usados
	
	//VER
	private class ListenerVolver implements ActionListener{



		public void actionPerformed(ActionEvent e) {
				if(editando == true ) {
					panel.setVisible(false);
					ventana.setContentPane(panelAnterior);
					ViewCamion ca = new ViewCamion(camion,ventana);
					ca.setVisible(true);
			   		panel.setVisible(false);
			   		ventana.setContentPane(ca);	
				//	panel.setVisible(false);
				}
				else {	
					panelAnterior.setVisible(true);
					editando = false;
					ventana.setContentPane(panelAnterior);
				}
				panel.setVisible(false);
		}
	}
	
	private class ListenerEditar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				editando = true;
				panelEditar = panel;
				panel.editar();
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ListenerEliminar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				if(editando == true)
					editando = false;
				camionService.eliminar(camion);
				panel.setVisible(false);
				ventana.setContentPane(panelAnterior);
				panelAnterior.setVisible(true);
				ViewBuscarCamion h = new ViewBuscarCamion(ventana);
				ventana.setContentPane(h);
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR no se pudo borrar el camion", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ListenerGuardar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(guardar(camion)) {
				if(editando == true)
					editando = false;
				ventana.setContentPane(panelAnterior);
				panelAnterior.setVisible(true);
				ViewCamion ca = new ViewCamion(camion,ventana);
				ca.setVisible(true);
		   		panel.setVisible(false);
		   		ventana.setContentPane(ca);	
		   		System.out.println("LISTENER 3");
			}
		}
	}

	public boolean guardar(Camion camion2) {
		if(verificarDatos()) {
			System.out.println("llegue aca?");
			camion.setPatente(this.panel.getCampoPatente()); 
			camion.setModelo(this.panel.getCampoModelo());
			camion.setMarca(this.panel.getCampoMarca()); 
			camion.setCostoHora(Double.valueOf(this.panel.getCampoCostoHs()));
			camion.setCostoKM(Double.valueOf(this.panel.getCampoCostoKm()));
			camion.setKm(this.panel.getSeleccionKm());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			System.out.println(this.panel.getCampoFechaCompra());
			LocalDate aux = LocalDate.parse(this.panel.getCampoFechaCompra().toString());
			camion.setFechaCompra(aux);
			System.out.println(aux);
			camionService.crearCamion(camion);
			System.out.println("se guardo?");
			return true;
		}
		return false;
	}

	public Boolean verificarDatos() {
		String textoErrorMarca = "";
		String textoErrorModelo = "";
		String textoErrorCostoHs = "";
		String textoErrorCostoKm = "";
		String textoErrorFecha = "";
		String textoErrorPatente = "";
		String textoErrorKm = "";
		String textoErrorPlanta = "";
		Boolean errorEnPatente = false;
		Boolean errorEnkm = false;
		Boolean errorEnModelo = false;
		Boolean errorEnMarca = false;
		Boolean errorEnFecha = false;
		Boolean errorEnCostokm = false;
		Boolean errorEnCostohs = false;
		Boolean errorEnPlanta = false;
		String textoPatente = panel.getCampoPatente();
		String textoMarca = panel.getCampoMarca();
		String textoModelo = panel.getCampoModelo();
		String textoCostoHs = panel.getCampoCostoHs();
		String textoCostokm = panel.getCampoCostoKm();
		String textoFecha = panel.getCampoFechaCompra();
		Integer errorNumero = 1;
		Boolean igualPatente = false;
		if(this.camion.getPatente().equals(textoPatente))
			igualPatente = true;
		
		System.out.println("aqui1");
		//---------- posible error en la introducción del número de patente
		if(!textoPatente.isEmpty() && !igualPatente) {
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
		
		System.out.println("aqui2");
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
		if (panel.getSeleccionKm().equals("Selecionar kilometraje") || panel.getSeleccionKm().equals("-")) {
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
		if (panel.getSeleccionPlanta().equals("Selecionar Planta")) {
			errorEnPlanta = true;
			textoErrorPlanta = errorNumero+") No se ha seleccionado un valor del campo Planta.\n";
		}
		System.out.println("aqui3");
		String mensajeError =  textoErrorPatente+textoErrorMarca+textoErrorModelo+textoErrorKm+textoErrorCostoHs+textoErrorCostoKm + textoErrorFecha+textoErrorPlanta;
		
		if( errorEnPatente || errorEnkm || errorEnCostohs || errorEnCostokm || errorEnFecha || errorEnMarca|| errorEnModelo ||errorEnCostohs||errorEnPlanta) {
			//panel.noValido( errorEnPatente,errorEnCostohs,errorEnCostokm, errorEnFecha, errorEnMarca, errorEnModelo,errorEnCostohs, errorEnPlanta);
			JOptionPane.showConfirmDialog(panel, mensajeError, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
		//	panel.textnormal();
			return false;
		}
		return true;
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
		
		private class ListenerCampoCostoHs implements KeyListener{
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if( ( Character.isDigit(caracter)  )
						&& panel.getCampoCostoKm().length() < 10 ){

					e.setKeyChar(caracter);
				}
				else{
					e.consume();
				}
			}
			public void keyPressed(KeyEvent e) { }
			public void keyReleased(KeyEvent e) { }
				
			} 
		private class ListenerCampoCostoKm implements KeyListener{
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if( ( Character.isDigit(caracter)  )
						&& panel.getCampoCostoHs().length() < 10 ){

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
