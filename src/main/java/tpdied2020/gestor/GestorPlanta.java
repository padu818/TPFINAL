package tpdied2020.gestor;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tpdied2020.dao.PlantaDao;
import tpdied2020.dao.PlantaDaoSql;
import tpdied2020.dominio.Planta;
import tpdied2020.dominio.Ruta;
import tpdied2020.gui.auxiliar.GrafoPlanta;

public class GestorPlanta {
	
	private static GestorPlanta instanciaGestor = null;
	
	public static  GestorPlanta get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorPlanta();
        }    
        return instanciaGestor;
    }
	
	private PlantaDao plantaDao = new PlantaDaoSql();
	
	public GrafoPlanta armarGrafo(){
		return plantaDao.armarGrafo();
	}
	
	public GrafoPlanta armarGrafo(List<Planta> plantas){
		GrafoPlanta gp = new GrafoPlanta();
		for(Planta p : plantas) {
			gp.addNodo(p);
		}
		for(Ruta r : buscarTodaRuta()) {
			gp.conectar(r.getOrigen(), r.getDestino(), r.getDuracionHs(), r.getDistanciaKM(), r.getPesoMaxKg());
		}
		return gp;
	}
	
	public Planta crearPlanta(Planta c) {
		return this.plantaDao.saveOrUpdate(c);
	}
	
	public List<Planta> buscarTodos() {
		return plantaDao.buscarTodos();
	}

	public Ruta crearRuta(Ruta r) {
		return this.plantaDao.saveOrUpdate(r);
	}
	
	public List<Ruta> buscarTodaRuta() {
		return plantaDao.buscarTodaRuta();
	}

	public void borrar(Integer origen, Integer destino) {
		plantaDao.borrar(origen,destino);
		
	}

	public List<Planta> buscarPorId(String string) {
		Planta p = plantaDao.buscarPorId(Integer.valueOf(string));
		List<Planta> planta = new ArrayList<Planta>();
		planta.add(p);
		return planta;
	}

	public List<List<Ruta>> rutaMasCortaHs(List<List<Ruta>> ruts) {
		Double[] hora = new Double[ruts.size()];
		for(Double s : hora) {
			s = 0.0;
		}
		Double min =-1.0;
		Integer con =0;
		List<List<Ruta>> resultado = new ArrayList<List<Ruta>>();
		for(List<Ruta> ruta :ruts) {
			Double contador =0.0;
			for(Ruta r:ruta) {
				contador+=r.getDuracionHs();
			}
			if(min == -1.0)
				min = contador;
			if(min> contador)
				min = contador;
			hora[con] = contador;
			con++;
		}
		for(int i = 0;i< hora.length;i++) {
			if(hora[i] == min) {
				resultado.add(ruts.get(i));
			}
		}
		return resultado;
	}

	public List<List<Ruta>> rutaMasCortaKm(List<List<Ruta>> ruts) {
		Double[] kmetros = new Double[ruts.size()];
		for(Double s : kmetros) {
			s = 0.0;
		}
		Double min =-1.0;
		Integer con =0;
		List<List<Ruta>> resultado = new ArrayList<List<Ruta>>();
		for(List<Ruta> ruta :ruts) {
			Double contador =0.0;
			for(Ruta r:ruta) {
				contador+=r.getDistanciaKM();
			}
			if(min == -1.0)
				min = contador;
			else if(min> contador)
				min = contador;
			kmetros[con] = contador;
			con++;
		}
		for(int i = 0;i< kmetros.length;i++) {
			if(kmetros[i] == min) {
				resultado.add(ruts.get(i));
			}
		}
		return resultado;
	}
	
	public Double pesoMaximo(List<List<Ruta>> ruts, List<Planta> auxiliar) {
		Double[] hora = new Double[ruts.size()];
		for(int i =0; i < ruts.size();i++) {
			hora[i] = 0.0;
		}
		Map<Integer, Double> caminos = new HashMap<Integer, Double>();
		Map<Integer, Double> plant = new HashMap<Integer, Double>();
		for(Planta p: auxiliar) {
			plant.put(p.getIdPlanta(), 0.0);
		}
		for(List<Ruta> r: ruts) {
			for(Ruta o:r) {
				caminos.put(o.getId(), o.getPesoMaxKg());
				System.out.println("Ruta"+o.getOrigen().getNombre()+"    "+o.getDestino().getNombre()+ "   "+o.getPesoMaxKg());
			}
		}
		for(List<Ruta> r: ruts) {
			for(Ruta o:r) {
				plant.put(o.getDestino().getIdPlanta(),plant.get(o.getDestino().getIdPlanta())+ o.getPesoMaxKg());
	
			}
		}

		Double resultado =-1.0;
		Integer i = 0;
		for(List<Ruta> r: ruts) {
			Ruta destino = r.get(r.size()-1);
			for(Ruta o:r) {
				if(resultado == -1.0 && caminos.get(o.getId()) > 0.0)
					resultado = o.getPesoMaxKg();
				System.out.println(o.getPesoMaxKg());
				System.out.println(o.getOrigen().getNombre()+" "+caminos.get(o.getId())+"  "+o.getDestino().getNombre());
				if(caminos.get(o.getId()) <= 0.0 || plant.get(o.getDestino().getIdPlanta()) <= 0.0 ) {
					if(destino.getDestino().getIdPlanta() != o.getDestino().getIdPlanta())
						resultado =0.0;
					break;
				}
				else if(caminos.get(o.getId()) <= resultado && plant.get(o.getDestino().getIdPlanta()) <= resultado  ) {
					if(caminos.get(o.getId()) > plant.get(o.getDestino().getIdPlanta())) {
						resultado = plant.get(o.getDestino().getIdPlanta());
						caminos.put(o.getId(),caminos.get(o.getId())-plant.get(o.getDestino().getIdPlanta()) );
						plant.put(o.getDestino().getIdPlanta(), 0.0);
					}
					else {
						resultado = caminos.get(o.getId());
						plant.put(o.getDestino().getIdPlanta(),plant.get(o.getDestino().getIdPlanta())-caminos.get(o.getId()) );
						caminos.put(o.getId(), 0.0);
					}
				}	
				else if( caminos.get(o.getId()) > resultado && plant.get(o.getDestino().getIdPlanta()) > resultado) {
						caminos.put(o.getId(), caminos.get(o.getId())-resultado);
						plant.put(o.getDestino().getIdPlanta(), caminos.get(o.getId())-resultado);
				}
				else if( caminos.get(o.getId()) <= resultado && plant.get(o.getDestino().getIdPlanta()) > resultado) {
					resultado = caminos.get(o.getId());
					plant.put(o.getDestino().getIdPlanta(),plant.get(o.getDestino().getIdPlanta())-caminos.get(o.getId()) );
					caminos.put(o.getId(), 0.0);
				}
				else if( caminos.get(o.getId()) > resultado && plant.get(o.getDestino().getIdPlanta()) <= resultado) {
					resultado = plant.get(o.getDestino().getIdPlanta());
					caminos.put(o.getId(),caminos.get(o.getId())-plant.get(o.getDestino().getIdPlanta()) );
					plant.put(o.getDestino().getIdPlanta(), 0.0);
				}
				
			}
			hora[i] = resultado;
			i++;
			resultado =0.0;
		}
		for(int j = 0;j< ruts.size();j++)
			resultado+= hora[j];
		
		return resultado;
	}
	
}
