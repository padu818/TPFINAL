
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
import tpdied2020.view.ViewAgregarRuta;
import tpdied2020.view.ViewAnalisisCaminoMin;
import tpdied2020.view.ViewAnalisisFlujoMax;

public class AnalisisController {
	
	private GestorPlanta plantaService;
	private Planta p;
	private Integer origen;
	private Integer destino;
	private ViewAnalisisFlujoMax panel;
	private ViewAnalisisCaminoMin caminoPanel;
	private AnalisisController instancia;
	private GrafoPlanta grafo;
	private List<List<Ruta>> ruta;
	private List<Planta> auxi;
	
	public AnalisisController(ViewAnalisisFlujoMax p, GrafoPlanta p2) {
		this.plantaService = GestorPlanta.get();
		this.panel = p;
		origen =-1;
		destino =-1;
		panel.addListenerBtnCancelar(new ListenerBtnCancelar());
		panel.addListenerBtnAceptar(new ListenerBtnAceptar());
		auxi = new ArrayList<Planta>();
		//auxi = plantaService.buscarTodos();
		grafo = new GrafoPlanta();
		this.ruta = new ArrayList<List<Ruta>>();
		grafo = p2;
		for(Vertice<Planta> pl :grafo.getVertices()) {
			auxi.add(pl.getValor());
		}
		panel.addOrigen(auxi);
		panel.addDestino(auxi);
	}
	
	
	
	public AnalisisController(ViewAnalisisCaminoMin p, GrafoPlanta p2) {
		this.plantaService = new GestorPlanta();
		this.caminoPanel = p;

		caminoPanel.addListenerBtnCancelar(new ListenerBtnCancelar());
		caminoPanel.addListenerBtnBuscar(new ListenerBtnBuscar());

		grafo = new GrafoPlanta();
		this.ruta = new ArrayList<List<Ruta>>();
		grafo = p2;
		auxi = new ArrayList<Planta>();
		for(Vertice<Planta> pl :grafo.getVertices()) {
			auxi.add(pl.getValor());
		}
//		auxi = plantaService.buscarTodos();
		caminoPanel.addPlantas(auxi);
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
		
		if(panel.getPlantaDestino().equals(panel.getPlantaOrigen()) && !errorEnOrigen) {
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
	
	public Boolean verificarDatosCamino() {
		String textoErrorSeleccion = "";

		Boolean errorEnSeleccion = false;

		Integer errorNumero = 1;

		if (caminoPanel.getTipo().equals("-")) {
			errorEnSeleccion = true;
			textoErrorSeleccion = errorNumero+") No se ha seleccionado un valor en el campo tipo de camino.\n";
			errorNumero++;
		}
		
		
		
		String mensajeError = textoErrorSeleccion;
		
		if( errorEnSeleccion) {
			JOptionPane.showConfirmDialog(panel, mensajeError, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	public Boolean aceptar() throws DatosObligatoriosException, FormatoNumeroException, ControllerException {
		if(this.verificarDatos()) {
			origen = panel.getIndexOrigen();
			destino = panel.getIndexDestino();
			Planta or = auxi.get(origen);
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
			 
			 for(int i = 0;i<ruta.size();i++) {
				 if(ruta.get(i).get(ruta.get(i).size()-1).getDestino().getIdPlanta() != de.getIdPlanta() || 
						 ruta.get(i).get(0).getOrigen().getIdPlanta() != or.getIdPlanta() ) {
					 ruta.remove(i);
				 }
			 }
			 
			 List<List<Ruta>> auxiliar = new ArrayList<List<Ruta>>();
			 auxiliar.addAll(ruta);
			 

			 for(List<Ruta> s: ruta) {
				 if(s.get(s.size()-1).getDestino().getIdPlanta() != de.getIdPlanta())
					 auxiliar.remove(s);
				 if(s.get(0).getOrigen().getIdPlanta() != or.getIdPlanta())
					 	auxiliar.remove(s);
			 }

			 ruta.removeAll(ruta);
			 ruta.addAll(auxiliar);

			 
			 
			 
			 Double maximo = plantaService.pesoMaximo(ruta, auxi);
			 panel.setCampoMaximo(maximo.toString());
			return true;
				}
		return false;
	}
	
	
	
	public Boolean buscar() throws DatosObligatoriosException, FormatoNumeroException, ControllerException {
		if(this.verificarDatosCamino()) {
			Integer opcion = caminoPanel.getIndexOfTipo();
			caminoPanel.addtablaCamino(auxi.size());

			Double[] resultado = new Double[auxi.size()];
			for(int i = 0; i< resultado.length;i++) {
				resultado[i] =0.0;
			}
			List<Planta> ai = auxi;
			Integer contador1 = 0;
			Integer contador2 = 0;
			for(Planta plan :auxi) {
				Integer index = -1;
				Integer index2 = -1;
				contador2 =0;
				Boolean ay = false;
				for(Planta t :ai) {
					index2 = -1;
					Boolean b = false;
					 for(Vertice<Planta> r :grafo.getVertices()) {

						    if(plan.getIdPlanta() == r.getValor().getIdPlanta() && ay == false) {
						    	index = grafo.getVertices().indexOf(r);
						    	ay = true;
						    }
						    if(t.getIdPlanta() == r.getValor().getIdPlanta()) {
					    		index2 = grafo.getVertices().indexOf(r);
					    		b = true;
						    }
						    if(ay == true && b == true)
						    	break;
					 }
					 List<List<Vertice<Planta>>> p = grafo.caminos(grafo.getVertices().get(index), grafo.getVertices().get(index2));
					 if(contador1 == 1 && contador2 == 5) {
						for(List<Vertice<Planta>> r:p) {
							System.out.println("Camino");
							for(Vertice<Planta> z : r)
								System.out.println(z.getValor().getNombre());
						}
					 }
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
					 for(int i = 0;i<ruta.size();i++) {
						 if(ruta.get(i).get(ruta.get(i).size()-1).getDestino().getIdPlanta() != t.getIdPlanta() || 
								 ruta.get(i).get(0).getOrigen().getIdPlanta() != plan.getIdPlanta() ) {
							 ruta.remove(i);
						 }
					 }
					 
					 List<List<Ruta>> auxiliar = new ArrayList<List<Ruta>>();
					 auxiliar.addAll(ruta);
					 

					 for(List<Ruta> s: ruta) {
						 if(s.get(s.size()-1).getDestino().getIdPlanta() != t.getIdPlanta())
							 auxiliar.remove(s);
						 if(s.get(0).getOrigen().getIdPlanta() != plan.getIdPlanta())
							 	auxiliar.remove(s);
					 }
	
					 ruta.removeAll(ruta);
					 ruta.addAll(auxiliar);

					 
					 List<List<Ruta>> elegido = new ArrayList<List<Ruta>>();
				   	if(opcion == 0 && ruta.size() > 0) {
						elegido.addAll(plantaService.rutaMasCortaKm(ruta));
					}
					else if(opcion == 1 && ruta.size() > 0) {
						elegido.addAll(plantaService.rutaMasCortaHs(ruta));
					}			 
					Double[] min = {0.0,0.0};
	
					for(List<Ruta> l : elegido) {
						for(int i = 0;i < l.size();i++) {
							if(opcion == 0)
								min[0] +=l.get(i).getDistanciaKM();
							else if(opcion == 1)
								min[1] +=l.get(i).getDuracionHs();
						}
					}
					if(opcion == 0)
						resultado[contador2] = min[0];
					else if(opcion == 1)
						resultado[contador2] = min[1];
					contador2++;
				}
				caminoPanel.setValoresTablaCamino(contador1,resultado);
				ruta.removeAll(ruta);
				contador1++;
				}
			return true;
				}
		return false;
	}
	
	
	private class ListenerBtnCancelar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {	
				caminoPanel.setVisible(false);
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(caminoPanel, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ListenerBtnAceptar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				if(aceptar()) {
//					panel.limpiarFormulario();
//					panel.addOrigen(auxi);
//					panel.addDestino(auxi);
				}
			} catch (DatosObligatoriosException | FormatoNumeroException | ControllerException e1) {
				panel.mostrarError("Error al aceptar", e1.getMessage());
			}
			
		}
	}
	
	private class ListenerBtnBuscar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				if(buscar()) {
//					panel.limpiarFormulario();
//					panel.addOrigen(auxi);
//					panel.addDestino(auxi);
				}
			} catch (DatosObligatoriosException | FormatoNumeroException | ControllerException e1) {
				panel.mostrarError("Error al buscar", e1.getMessage());
			}
			
		}
	}
	
	
}