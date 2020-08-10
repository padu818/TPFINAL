package tpdied2020.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JOptionPane;

import tpdied2020.dominio.Planta;
import tpdied2020.gestor.GestorPlanta;
import tpdied2020.gui.util.ControllerException;
import tpdied2020.gui.util.DatosObligatoriosException;
import tpdied2020.gui.util.FormatoNumeroException;
import tpdied2020.view.ViewAltaPlanta;

public class AltaPlantaController {
	private GestorPlanta plantaService;
	private Planta c;
	private ViewAltaPlanta panel;
	private AltaPlantaController instancia;
	
	public AltaPlantaController(ViewAltaPlanta p) {
		this.plantaService = GestorPlanta.get();
		this.panel = p;
		panel.addListenerBtnCancelar(new ListenerBtnCancelar());
		panel.addListenerBtnGuardar(new ListenerBtnGuardar());
	}
	
	public Boolean verificarDatos() {
		String textoErrorNombre = "";
		Boolean errorEnNombre = false;
		String textoNombre = panel.getCampoNombrePlanta();
		Integer errorNumero = 1;
		
		if(textoNombre.isEmpty()) {
			errorEnNombre = true;
        	textoErrorNombre = errorNumero+") Debe completar el campo Nombre.\n";
			errorNumero++;
		}
		String mensajeError =  textoErrorNombre;
		if( errorEnNombre) {
			JOptionPane.showConfirmDialog(panel, mensajeError, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	public Boolean guardar() throws DatosObligatoriosException, FormatoNumeroException, ControllerException {
		if(this.verificarDatos()) {
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
