package died.ejemplos.dao;


import java.util.List;


import died.ejemplos.dominio.Planta;

public interface PlantaDao {
	public Planta saveOrUpdate(Planta c);
	public Planta buscarPorId(Integer id);
	public List<Planta> buscarTodos();
}
