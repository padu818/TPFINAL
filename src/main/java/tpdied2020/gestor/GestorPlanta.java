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
		for(Double s : hora) {
			s = 0.0;
		}
		Map<Integer, Double> caminos = new HashMap<Integer, Double>();
		for(Planta p: auxiliar) {
			caminos.put(p.getIdPlanta(), 0.0);
		}

		for(List<Ruta> r: ruts) {
			for(Ruta o:r) {
				caminos.put(o.getOrigen().getIdPlanta(), o.getPesoMaxKg());
			}
		}
		Double resultado =0.0;
		for(List<Ruta> r: ruts) {
			for(Ruta o:r) {
				if(caminos.get(o.getOrigen().getIdPlanta()) <= resultado) {
					resultado += caminos.get(o.getOrigen().getIdPlanta());
					caminos.put(o.getOrigen().getIdPlanta(), 0.0);
					break;
				}
				if(o.getPesoMaxKg() > resultado)
					caminos.put(o.getOrigen().getIdPlanta(), o.getPesoMaxKg()-resultado);
				
			}
		}
		
		
		return resultado;
	}
	
}
