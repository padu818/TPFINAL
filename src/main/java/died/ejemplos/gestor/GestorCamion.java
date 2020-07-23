package died.ejemplos.gestor;

import java.util.List;

import died.ejemplos.dao.CamionDao;
import died.ejemplos.dao.CamionDaoMysql;
import died.ejemplos.dominio.Camion;

public class GestorCamion {
	
	private CamionDao camionDao = new CamionDaoMysql();

	public Camion crearCamion(Camion c) {
		// si hay alguna regla de negocio que indque que no se 
		// puede agregar un camion si no se cumplen determinadas
		// condiciones en otras entidades o reglas 
		// se valida aquí
			return this.camionDao.saveOrUpdate(c);
	}
	
	public List<Camion> buscarTodos() {
		return camionDao.buscarTodos();
	}

}
