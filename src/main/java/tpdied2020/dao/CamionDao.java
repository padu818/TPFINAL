package tpdied2020.dao;

import java.util.List;

import tpdied2020.dominio.Camion;

public interface CamionDao {

	public Camion saveOrUpdate(Camion c);
	public Camion buscarPorId(Integer id);
	public List<Camion> buscarTodos();
	public List<Camion> busqueda(String condicionesConsulta);
	void borrar(String patente);
}
