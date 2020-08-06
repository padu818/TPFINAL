package died.ejemplos.dao;

import java.util.List;


import died.ejemplos.dominio.Insumo;
import died.ejemplos.dominio.Planta;
import died.ejemplos.dominio.Ruta;
import died.ejemplos.dominio.StockInsumo;


public interface InsumoDao {
	
	
	public Insumo saveOrUpdate(Insumo i);
	public List<Insumo> buscarPorId(String texto);
	//public void borrar(Integer id);
	public List<Insumo> buscarTodos();
	public List<Insumo> busqueda(String condicionesConsulta);
	void borrar(String nombre);
	public List<StockInsumo> busquedaStock(String busqueda, List<Insumo> insumos, List<Planta> plantas);
	public StockInsumo saveOrUpdate(StockInsumo s);
	public List<StockInsumo> busquedaStockInsumos(Planta p);
	public void borrar(Integer idPlanta, Integer idInsumo);
	public List<StockInsumo> busquedaStock( List<Insumo> insumos/*, List<Planta> plantas*/);
}
