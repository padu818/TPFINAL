package died.ejemplos.gestor;


import died.ejemplos.dao.PlantaDao;
import died.ejemplos.dao.PlantaDaoSql;
import died.ejemplos.dominio.Planta;

public class GestorPlanta {
	
	
	private PlantaDao plantaDao = new PlantaDaoSql();
	
	public Planta crearPlanta(Planta c) {
		// TODO Auto-generated method stub
		return this.plantaDao.saveOrUpdate(c);
	}

}
