package died.ejemplos.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import died.ejemplos.dominio.Camion;
import died.ejemplos.dominio.General;
import died.ejemplos.dominio.Insumo;
import died.ejemplos.dominio.Liquido;
import died.ejemplos.dominio.Unidad;
import died.ejemplos.gestor.GestorCamion;
import died.ejemplos.gestor.GestorInsumo;
import died.ejemplos.gui.util.ControllerException;
import died.ejemplos.gui.util.DatosObligatoriosException;
import died.ejemplos.gui.util.FormatoNumeroException;
import died.ejemplos.view.ViewAltaInsumo;
import died.ejemplos.view.ViewBuscarCamion;
import died.ejemplos.view.ViewCamion;
import died.ejemplos.view.ViewVisualizarInsumo;

public class AltaInsumoController {
	
	private GestorInsumo insumoService;
	private Insumo i;
	private ViewAltaInsumo panel;
	private AltaInsumoController instancia;
	private JPanel panelAnterior;
	private JFrame ventana;
	private Boolean editando = false;
	private JPanel panelEditar;
	
	public AltaInsumoController(ViewAltaInsumo p, JFrame v) {
		this.insumoService = new GestorInsumo();
		this.ventana =v;
		this.panel = p;
		panel.addListenerBtnCancelar(new ListenerBtnCancelar());
		panel.addListenerBtnGuardar(new ListenerBtnGuardarAlta());
		panel.addListenerSeleccionTipo(new ListenerSeleccionTipo());
		panel.addListenerCampoCosto(new ListenerCampoCosto());
		panel.addListenerCampoPeso(new ListenerCampoPeso());
		panel.addListenerCampoDensidad(new ListenerCampoDensidad());
	}
	
	
	
	public AltaInsumoController(ViewAltaInsumo v, Insumo i2, JFrame f) {
		this.insumoService = new GestorInsumo();
		this.i = i2;
		this.panel = v;
		this.ventana = f;
		this.panelEditar = v;
		panelAnterior = (JPanel) f.getContentPane();
		setView();
	}
	private void setView() {
		
		cargarInsumoSeleccionado(this.i);
		panel.addListenerBtnVolver(new ListenerBtnVolver());
		panel.addListenerBtnEditar(new ListenerBtnEditar());
		panel.addListenerBtnGuardar(new ListenerBtnGuardarModificacion());
		panel.addListenerBtnEliminar(new ListenerBtnEliminar());
		panel.addListenerSeleccionTipo(new ListenerSeleccionTipo());
		panel.addListenerCampoCosto(new ListenerCampoCosto());
		panel.addListenerCampoPeso(new ListenerCampoPeso());
		panel.addListenerCampoDensidad(new ListenerCampoDensidad());
		ventana.setContentPane(panel);
	}

	private void cargarInsumoSeleccionado(Insumo i2) {
		panel.setCampoId(i2.getIdProduto().toString());
		panel.setCampoNombre(i2.getNombre());
		panel.setCampoDescripcion(i2.getDescripcion());
		panel.setCampoCosto(i2.getCosto().toString());
		panel.setCampoUnidadMedida(i2.getUnidadMedida());
		
		if (i2.getTipoInsumo().equals("GENERAL")) {
			panel.setCampoTipoInsumo("GENERAL");
			panel.setCampoPeso(((General)i2).getPeso().toString());
		}else {
			panel.setCampoTipoInsumo("LIQUIDO");
			panel.setCampoDensidad(((Liquido)i2).getDensidad().toString());
		}
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
		if (panel.getSeleccionUnidadMedida().equals("Seleccionar unidad de medida")) {
			errorEnUnidadMedida = true;
			textoErrorUnidadMedida = errorNumero+") No se ha seleccionado un valor del campo unidad de medida.\n";
		}
		
		//---------- posible error en la no selección de un tipo
		if (panel.getSeleccionTipo().equals("Seleccionar tipo de insumo")) {
			errorEnTipo = true;
			textoErrorTipo = errorNumero+") No se ha seleccionado un valor del campo tipo.\n";
		}
		
		
		if(panel.getSeleccionTipo().equals("GENERAL") && textoPeso.isEmpty()) {
			errorEnPeso = true;
			textoErrorPeso = errorNumero+") Debe completar el campo peso.\n";
			errorNumero++;
		}
		if(panel.getSeleccionTipo().equals("LIQUIDO") && textoDensidad.isEmpty()) {
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
			if (this.panel.getSeleccionTipo().equals("GENERAL")) {
				i = new General();
				((General) i).setPeso(Double.valueOf(this.panel.getCampoPeso()));
			} else {
				 i = new Liquido();
				((Liquido) i).setDensidad(Double.valueOf(this.panel.getCampoDensidad()));
			}
			System.out.println(this.panel.getCampoNombre());
			i.setNombre(this.panel.getCampoNombre()); 
			i.setDescripcion(this.panel.getCampoDescripcion()); 
			i.setCosto(Double.valueOf(this.panel.getCampoCosto()));
			//mejorar?
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
			
			insumoService.crearInsumo(i);
			return true;
		}
		return false;
	}
	
	public boolean guardar(Insumo i2) throws DatosObligatoriosException, FormatoNumeroException, ControllerException {
		if(verificarDatos()) {
			if (this.panel.getSeleccionTipo().equals("GENERAL")) {
				((General) i).setPeso(Double.valueOf(this.panel.getCampoPeso()));
			} else {
				((Liquido) i).setDensidad(Double.valueOf(this.panel.getCampoDensidad()));
			}
			i.setNombre(this.panel.getCampoNombre()); 
			i.setDescripcion(this.panel.getCampoDescripcion()); 
			i.setCosto(Double.valueOf(this.panel.getCampoCosto()));
			//mejorar?
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
			insumoService.crearInsumo(i);
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
	
	private class ListenerSeleccionTipo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {	
				if(!panel.getSeleccionTipo().equals("Seleccionar tipo de insumo")) {
						panel.setTipo(panel.getSeleccionTipo());
				}
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(panel, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ListenerBtnGuardarAlta implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				if(guardar())
					panel.limpiarFormulario();
			} catch (DatosObligatoriosException | FormatoNumeroException | ControllerException e1) {
				panel.mostrarError("Error al guardar", e1.getMessage());
			}
			
		}
	}
	
	private class ListenerBtnGuardarModificacion implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				if(guardar(i)) {
					if(editando == true)
						editando = false;
					ventana.setContentPane(panelAnterior);
					panelAnterior.setVisible(true);
					ViewAltaInsumo ca = new ViewAltaInsumo(i,ventana);
					ca.setVisible(true);
					panel.setVisible(false);
					ventana.setContentPane(ca);	
				}
			}  catch (DatosObligatoriosException | FormatoNumeroException | ControllerException e1) {
				panel.mostrarError("Error al guardar", e1.getMessage());
			}
		}
	}
	
	private class ListenerCampoCosto implements KeyListener{
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			if( ( Character.isDigit(caracter)  )
					&& panel.getCampoCosto().length() < 10 ){

				e.setKeyChar(caracter);
			}
			else{
				e.consume();
			}
		}
		public void keyPressed(KeyEvent e) { }
		public void keyReleased(KeyEvent e) { }
			
		}
	
	private class ListenerCampoPeso implements KeyListener{
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			if( ( Character.isDigit(caracter)  )
					&& panel.getCampoPeso().length() < 10 ){

				e.setKeyChar(caracter);
			}
			else{
				e.consume();
			}
		}
		public void keyPressed(KeyEvent e) { }
		public void keyReleased(KeyEvent e) { }
			
		}
	
	private class ListenerCampoDensidad implements KeyListener{
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			if( ( Character.isDigit(caracter)  )
					&& panel.getCampoDensidad().length() < 10 ){

				e.setKeyChar(caracter);
			}
			else{
				e.consume();
			}
		}
		public void keyPressed(KeyEvent e) { }
		public void keyReleased(KeyEvent e) { }
			
		}
	
	private class ListenerBtnVolver implements ActionListener{

		public void actionPerformed(ActionEvent e) {
				if(editando == true ) {
					panel.setVisible(false);
					ventana.setContentPane(panelAnterior);
					ViewAltaInsumo ca = new ViewAltaInsumo(i,ventana);
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
	
	private class ListenerBtnEditar implements ActionListener{
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
	
	private class ListenerBtnEliminar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				if(editando == true)
					editando = false;
				insumoService.eliminar(i);
				panel.setVisible(false);
				ventana.setContentPane(panelAnterior);
				panelAnterior.setVisible(true);
				ViewVisualizarInsumo h = new ViewVisualizarInsumo(ventana);
				ventana.setContentPane(h);
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "ERROR no se pudo borrar el insumo", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
