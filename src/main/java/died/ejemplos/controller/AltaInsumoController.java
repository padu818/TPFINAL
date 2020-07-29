package died.ejemplos.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


import died.ejemplos.dominio.General;
import died.ejemplos.dominio.Insumo;
import died.ejemplos.dominio.Liquido;
import died.ejemplos.dominio.Unidad;
import died.ejemplos.gestor.GestorInsumo;
import died.ejemplos.gui.util.ControllerException;
import died.ejemplos.gui.util.DatosObligatoriosException;
import died.ejemplos.gui.util.FormatoNumeroException;
import died.ejemplos.view.ViewAltaInsumo;

public class AltaInsumoController {
	
	private GestorInsumo insumoService;
	private Insumo i;
	private ViewAltaInsumo panel;
	private AltaInsumoController instancia;
	
	public AltaInsumoController(ViewAltaInsumo p) {
		this.insumoService = new GestorInsumo();
		this.panel = p;
		panel.addListenerBtnCancelar(new ListenerBtnCancelar());
		panel.addListenerBtnGuardar(new ListenerBtnGuardar());
	}
	
	public Boolean verificarDatos() {
		String textoErrorNombre = "";
		String textoErrorDescripcion = "";
		String textoErrorCosto = "";
		String textoErrorUnidadMedida = "";
		String textoErrorTipo = "";
		String textoErrorPeso = "";
		String textoErrorDensidad = "";
		Boolean errorEnNombre = false;
		Boolean errorEnDescripcion = false;
		Boolean errorEnCosto = false;
		Boolean errorEnUnidadMedida = false;
		Boolean errorEnTipo = false;
		Boolean errorEnPeso = false;
		Boolean errorEnDensidad = false;
		String textoNombre = panel.getCampoNombre();
		String textoDescripcion = panel.getCampoDescripcion();
		String textoCosto = panel.getCampoCosto();
		String textoPeso = panel.getCampoPeso();
		String textoDensidad = panel.getCampoDensidad();
		Integer errorNumero = 1;
		
		
		//---------- posible error en duplicado de nombre
		if(!textoNombre.isEmpty()) {
//			if(insumoService.validarNombre(textoNombre)) {
//				errorEnNombre = true;
//				textoErrorNombre = errorNumero+") El nombre ingresado del insumo, ya está registrado.\n";
//				errorNumero++;
//			} 
		} else if(textoNombre.isEmpty()) {
			errorEnNombre = true;
			textoErrorNombre = errorNumero+") Debe completar el campo nombre.\n";
			errorNumero++;
		}
		
		
		if(textoDescripcion.isEmpty()) {
			errorEnDescripcion = true;
			textoErrorDescripcion = errorNumero+") Debe completar el campo descripcion.\n";
			errorNumero++;
		}
		if(textoCosto.isEmpty()) {
			errorEnCosto = true;
			textoErrorCosto = errorNumero+") Debe completar el campo costo.\n";
			errorNumero++;
		}
		
		//---------- posible error en la no selección de una unidad de medida
		if (panel.getSeleccionUnidadMedida().equals("Selecionar unidad de medida")) {
			errorEnUnidadMedida = true;
			textoErrorUnidadMedida = errorNumero+") No se ha seleccionado un valor del campo unidad de medida.\n";
		}
		
		//---------- posible error en la no selección de un tipo
		if (panel.getSeleccionTipo().equals("Selecionar tipo de insumo")) {
			errorEnTipo = true;
			textoErrorTipo = errorNumero+") No se ha seleccionado un valor del campo tipo.\n";
		}
		
		
		if(textoPeso.isEmpty()) {
			errorEnPeso = true;
			textoErrorPeso = errorNumero+") Debe completar el campo peso.\n";
			errorNumero++;
		}
		if(textoDensidad.isEmpty()) {
			errorEnDensidad = true;
			textoErrorDensidad = errorNumero+") Debe completar el campo densidad.\n";
			errorNumero++;
		}
		
		String mensajeError =  textoErrorNombre+textoErrorDescripcion+textoErrorCosto+textoErrorUnidadMedida+textoErrorTipo+textoErrorPeso + textoErrorDensidad;
		
		if( errorEnNombre || errorEnDescripcion || errorEnCosto || errorEnUnidadMedida || errorEnTipo || errorEnPeso|| errorEnDensidad) {
			panel.noValido( errorEnNombre,errorEnDescripcion,errorEnCosto, errorEnUnidadMedida, errorEnTipo, errorEnPeso,errorEnDensidad);
			JOptionPane.showConfirmDialog(panel, mensajeError, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			panel.textnormal();
			return false;
		}
		return true;
	}
	
	public Boolean guardar() throws DatosObligatoriosException, FormatoNumeroException, ControllerException {
		if(this.verificarDatos()) {
			System.out.println(this.panel.getClass());
			if (this.panel.getSeleccionTipo().equals("GENERAL")) {
				General i = new General();
				i.setPeso(Double.valueOf(this.panel.getCampoPeso()));
			} else {
				Liquido i = new Liquido();
				i.setDensidad(Double.valueOf(this.panel.getCampoDensidad()));
			}
			i.setNombre(this.panel.getCampoNombre()); 
			i.setDescripcion(this.panel.getCampoDescripcion()); 
			i.setCosto(Double.valueOf(this.panel.getCampoCosto()));
			switch (this.panel.getSeleccionUnidadMedida()) {
			case "KILO":
				i.setUnidadMedida(Unidad.KILO);
				break;
			case "PIEZA":
				i.setUnidadMedida(Unidad.PIEZA);
				break;
			case "GRAMO":
				i.setUnidadMedida(Unidad.GRAMO);
				break;
			case "METRO":
				i.setUnidadMedida(Unidad.METRO);
				break;
			case "LITRO":
				i.setUnidadMedida(Unidad.LITRO);
				break;
			case "M3":
				i.setUnidadMedida(Unidad.M3);
				break;
			case "M2":
				i.setUnidadMedida(Unidad.M2);
				break;
			}
//			insumoService.crearInsumo(i);
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

}
