package died.ejemplos.gestor;


import java.util.List;

import died.ejemplos.dao.PlantaDao;
import died.ejemplos.dao.PlantaDaoSql;
import died.ejemplos.dominio.Planta;
import died.ejemplos.dominio.Ruta;
import died.ejemplos.gui.ayuda.Grafo;
import died.ejemplos.gui.ayuda.GrafoPlanta;

public class GestorPlanta {
	
	
	private PlantaDao plantaDao = new PlantaDaoSql();
	
	public GrafoPlanta armarGrafo(){
		GrafoPlanta gp = new GrafoPlanta();
		for(Planta p : buscarTodos()) {
			gp.addNodo(p);
		}
		for(Ruta r : buscarTodaRuta()) {
			gp.conectar(r.getOrigen(), r.getDestino(), r.getDuracionHs(), r.getDistanciaKM(), r.getPesoMaxKg());
		}
		return gp;
	}
	
	public Planta crearPlanta(Planta c) {
		// TODO Auto-generated method stub
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
	
}
