package died.ejemplos.gestor;


import java.util.List;

import died.ejemplos.dao.PlantaDao;
import died.ejemplos.dao.PlantaDaoSql;
import died.ejemplos.dominio.Planta;
import died.ejemplos.dominio.Ruta;

public class GestorPlanta {
	
	
	private PlantaDao plantaDao = new PlantaDaoSql();
	
	public Planta crearPlanta(Planta c) {
		// TODO Auto-generated method stub
		return this.plantaDao.saveOrUpdate(c);
	}
	
	public List<Planta> buscarTodos() {
		return plantaDao.buscarTodos();
	}

	public void crearRuta(Ruta r) {
		// TODO Auto-generated method stub
		
	}

}
