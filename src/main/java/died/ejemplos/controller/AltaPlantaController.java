package died.ejemplos.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JOptionPane;


import died.ejemplos.dominio.Planta;

import died.ejemplos.gestor.GestorPlanta;
import died.ejemplos.gui.util.ControllerException;
import died.ejemplos.gui.util.DatosObligatoriosException;
import died.ejemplos.gui.util.FormatoNumeroException;

import died.ejemplos.view.ViewAltaPlanta;

public class AltaPlantaController {
	private GestorPlanta plantaService;
	private Planta c;
	private ViewAltaPlanta panel;
	private AltaPlantaController instancia;
	
	public AltaPlantaController(ViewAltaPlanta p) {
		this.plantaService = new GestorPlanta();
		this.panel = p;
		panel.addListenerBtnCancelar(new ListenerBtnCancelar());
		panel.addListenerBtnGuardar(new ListenerBtnGuardar());
	//	panel.addListenerCampoNombrePlanta(new ListenerCampoNombre());

		
	}
	
	public Boolean verificarDatos() {
		String textoErrorNombre = "";
		Boolean errorEnNombre = false;
		String textoNombre = panel.getCampoNombrePlanta();
		Integer errorNumero = 1;
		
		//---------- posible error en la introducción del nombre
//		if(!textoErrorNombre.isEmpty()) {
//				if(plantaService.validarNombre(textoErrorNombre)) {
//					errorEnNombre = true;
//					textoErrorNombre = errorNumero+") El nombre ingresado, ya está registrado.\n";
//					errorNumero++;
//				}
//					
//		}
		/*else */if(textoNombre.isEmpty()) {
			errorEnNombre = true;
        	textoErrorNombre = errorNumero+") Debe completar el campo Nombre.\n";
			errorNumero++;
		}
		
		String mensajeError =  textoErrorNombre;
		if( errorEnNombre) {
		//	panel.noValido( errorEnNombre);
			JOptionPane.showConfirmDialog(panel, mensajeError, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
		//	panel.textnormal();
			return false;
		}
		return true;
	}
	public Boolean guardar() throws DatosObligatoriosException, FormatoNumeroException, ControllerException {
		if(this.verificarDatos()) {
		//	System.out.println(this.panel.getClass());
			Planta c = new Planta();
			c.setNombre(this.panel.getCampoNombrePlanta());
			plantaService.crearPlanta(c);
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
			try {
				if(guardar())
					panel.limpiarFormulario();
			} catch (DatosObligatoriosException | FormatoNumeroException | ControllerException e1) {
				panel.mostrarError("Error al guardar", e1.getMessage());
			}
			
		}
	}
}
