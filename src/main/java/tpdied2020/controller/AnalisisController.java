package tpdied2020.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import tpdied2020.dominio.Planta;
import tpdied2020.dominio.Ruta;
import tpdied2020.gestor.GestorPlanta;
import tpdied2020.gui.auxiliar.Arista;
import tpdied2020.gui.auxiliar.GrafoPlanta;
import tpdied2020.gui.auxiliar.Vertice;
import tpdied2020.gui.util.ControllerException;
import tpdied2020.gui.util.DatosObligatoriosException;
import tpdied2020.gui.util.FormatoNumeroException;
import tpdied2020.view.ViewAnalisisFlujoMax;

public class AnalisisController {
	
	private GestorPlanta plantaService;
	private Planta p;
	private Integer origen;
	private Integer destino;
	private ViewAnalisisFlujoMax panel;
	private AnalisisController instancia;
	private GrafoPlanta grafo;
	private List<List<Ruta>> ruta;
	private List<Planta> auxi;
	
	public AnalisisController(ViewAnalisisFlujoMax p, GrafoPlanta p2) {
		this.plantaService = new GestorPlanta();
		this.panel = p;
		
		origen =-1;
		destino =-1;

		
		panel.addListenerBtnCancelar(new ListenerBtnCancelar());
		panel.addListenerBtnAceptar(new ListenerBtnAceptar());
		auxi = new ArrayList<Planta>();
		grafo = new GrafoPlanta();
		this.ruta = new ArrayList<List<Ruta>>();
		grafo = p2;
		for(Vertice<Planta> pl :grafo.getVertices()) {
			auxi.add(pl.getValor());
		}
		panel.addOrigen(auxi);
		panel.addDestino(auxi);

	}
	
	public Boolean verificarDatos() {
		String textoErrorOrigen = "";
		String textoErrorDestino = "";
		String textoErrorPlanta = "";
		Boolean errorEnOrigen = false;
		Boolean errorEnDestino = false;
		Boolean errorPlanta = false;
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
		
		
		String mensajeError = textoErrorOrigen+textoErrorDestino+textoErrorPlanta ;
		
		if( errorEnDestino||errorEnOrigen||errorPlanta) {
			JOptionPane.showConfirmDialog(panel, mensajeError, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	public Boolean aceptar() throws DatosObligatoriosException, FormatoNumeroException, ControllerException {
		if(this.verificarDatos()) {
			origen = panel.getIndexOrigen();
			destino = panel.getIndexDestino();
			Planta or = auxi.get(origen);;
			Planta de= auxi.get(destino);
			Integer index = -1,index2 = -1;
			Boolean ay = false;
			Boolean b = false;
			 for(Vertice<Planta> r :grafo.getVertices()) {
			    if(or.getIdPlanta() == r.getValor().getIdPlanta()) {
			    	index = grafo.getVertices().indexOf(r);
			    	ay = true;
			    }
			    if(de.getIdPlanta() == r.getValor().getIdPlanta()) {
		    		index2 = grafo.getVertices().indexOf(r);
		    		b = true;
			    }
			    if(ay && b)
			    	break;
			 }
			 List<List<Vertice<Planta>>> p = grafo.caminos(grafo.getVertices().get(index), grafo.getVertices().get(index2));
		
			    		
			 for(List<Vertice<Planta>> lis : p) {
				 List<Ruta> una = new ArrayList<Ruta>();
			   	for(int i = 0; i < lis.size()-1;i++) {
				    for(Arista<Planta> a: grafo.getAristas()) {
				    	if(a.getInicio().getValor().getIdPlanta() == lis.get(i).getValor().getIdPlanta() && 
				    			a.getFin().getValor().getIdPlanta() == lis.get(i+1).getValor().getIdPlanta()) {
				    			Ruta r = new Ruta();
				    			r.setOrigen(a.getInicio().getValor());
				    			r.setDestino(a.getFin().getValor());
				    			r.setDistanciaKM(a.getKm());
				    			r.setDuracionHs(a.getHs());
				    			r.setPesoMaxKg(a.getMax());
				    			una.add(r);
				    			break;
				    	}
				    }
			    }
			    ruta.add(una);
			   
			 }
			 Double maximo = plantaService.pesoMaximo(ruta);
			 panel.setCampoMaximo(maximo.toString());
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
	
	private class ListenerBtnAceptar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				if(aceptar()) {
				}
			} catch (DatosObligatoriosException | FormatoNumeroException | ControllerException e1) {
				panel.mostrarError("Error al guardar", e1.getMessage());
			}
			
		}
	}
	
	
}
