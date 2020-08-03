package died.ejemplos.gestor;

import died.ejemplos.dominio.Camion;
import died.ejemplos.dominio.Insumo;

import java.util.Collection;
import java.util.List;

import died.ejemplos.dao.InsumoDao;
import died.ejemplos.dao.InsumoDaosql;

public class GestorInsumo {

	private InsumoDao insumoDao = new InsumoDaosql();
	
	public Insumo crearInsumo(Insumo i) {
		// TODO Auto-generated method stub
		return this.insumoDao.saveOrUpdate(i);
	}

	public List<Insumo> buscarTodos() {
		return insumoDao.buscarTodos();
	}
	
	public void eliminar(Insumo insumo) {
		insumoDao.borrar(insumo.getNombre());
	}

}
