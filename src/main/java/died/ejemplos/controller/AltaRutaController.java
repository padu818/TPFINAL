package died.ejemplos.controller;


import died.ejemplos.dominio.Camion;
import died.ejemplos.dominio.Planta;
import died.ejemplos.gestor.GestorCamion;
import died.ejemplos.gestor.GestorPlanta;
import died.ejemplos.view.ViewAltaCamion;
import died.ejemplos.view.ViewAgregarRuta;

public class AltaRutaController {

	private GestorPlanta plantaService;
	private Planta p;
	private ViewAgregarRuta panel;
	private AltaRutaController instancia;
	
	public AltaRutaController(ViewAgregarRuta p) {
		this.plantaService = new GestorPlanta();
		this.panel = p;
//		panel.addListenerBtnCancelar(new ListenerBtnCancelar());
//		panel.addListenerBtnGuardar(new ListenerBtnGuardar());
//		panel.addListenerBtnCancelar(new ListenerBtnGuardarRuta());
//		panel.addListenerBtnGuardar(new ListenerBtnEditarRuta());
//		panel.addListenerBtnCancelar(new ListenerBtnEliminarRuta());
//		panel.addListenerBtnGuardar(new ListenerBtnAceptar());
//		panel.addListenerCampoCostoHs(new ListenerCampoDistanciaHs());
//		panel.addListenerCampoCostoKm(new ListenerCampoDistanciaKm());
	}

}
