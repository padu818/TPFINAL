package tpdied2020.dao;

import java.util.List;

import tpdied2020.dominio.Insumo;
import tpdied2020.dominio.Planta;
import tpdied2020.dominio.StockInsumo;


public interface InsumoDao {
	
	
	public Insumo saveOrUpdate(Insumo i);
	public List<Insumo> buscarPorId(String texto);
	public List<Insumo> buscarTodos();
	public List<Insumo> busqueda(String condicionesConsulta);
	void borrar(String nombre);
	public List<StockInsumo> busquedaStock(String busqueda, List<Insumo> insumos, List<Planta> plantas);
	public StockInsumo saveOrUpdate(StockInsumo s);
	public List<StockInsumo> busquedaStockInsumos(Planta p);
	public void borrar(Integer idPlanta, Integer idInsumo);
	public List<StockInsumo> busquedaStock( List<Insumo> insumos/*, List<Planta> plantas*/);
	public List<Planta> obtener(String consulta);
}
