package died.ejemplos.dao;


import died.ejemplos.dominio.Planta;

public interface PlantaDao {
	public Planta saveOrUpdate(Planta c);
	public Planta buscarPorId(Integer id);
}
