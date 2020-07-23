package died.ejemplos.dao;

import java.util.List;

import died.ejemplos.dominio.Camion;

public interface CamionDao {

	public Camion saveOrUpdate(Camion c);
	public Camion buscarPorId(Integer id);
	public void borrar(Integer id);
	public List<Camion> buscarTodos();
}
