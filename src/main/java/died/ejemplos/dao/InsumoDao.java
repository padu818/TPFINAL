package died.ejemplos.dao;

import java.util.List;


import died.ejemplos.dominio.Insumo;


public interface InsumoDao {
	
	
	public Insumo saveOrUpdate(Insumo i);
	public Insumo buscarPorId(Integer id);
	//public void borrar(Integer id);
	public List<Insumo> buscarTodos();
	public List<Insumo> busqueda(String condicionesConsulta);
	void borrar(String nombre);

}
