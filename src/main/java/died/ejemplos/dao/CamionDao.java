package died.ejemplos.dao;

import java.time.LocalDate;
import java.util.List;

import died.ejemplos.dominio.Camion;

public interface CamionDao {

	public Camion saveOrUpdate(Camion c);
	public Camion buscarPorId(Integer id);
	public void borrar(Integer id);
	public List<Camion> buscarTodos();
	public List<Camion> busqueda(String consulta, String marca, String modelo, String kmr, String cosths, String costkm, String fecha);
	public List<Camion> busqueda(String condicionesConsulta);
}
