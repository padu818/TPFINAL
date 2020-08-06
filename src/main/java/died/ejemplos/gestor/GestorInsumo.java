package died.ejemplos.gestor;


import died.ejemplos.dominio.Insumo;
import died.ejemplos.dominio.Planta;
import died.ejemplos.dominio.Ruta;
import died.ejemplos.dominio.StockInsumo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import died.ejemplos.dao.InsumoDao;
import died.ejemplos.dao.InsumoDaosql;

public class GestorInsumo {

	private InsumoDao insumoDao = new InsumoDaosql();
	private GestorPlanta plantaService = new GestorPlanta();
	
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
	
	public List<StockInsumo> busqueda(String text1,String text2){
		String busqueda = "SELECT IDPLANTA,IDINSUMO,STOCK,PUNTOREPOSICION FROM STOCKINSUMO";
		Boolean primerConsulta = true;
		List<Insumo> insumos = new ArrayList<Insumo>();
		List<StockInsumo> stockins = new ArrayList<StockInsumo>();
		List<Planta> plantas = new ArrayList<Planta>();
		
		
		if(!text1.isEmpty())
			insumos = buscarPorId("SELECT IDINSUMO,NOMBRE,DESCRIPCION,UNIDAD_MEDIDA,COSTO,TIPO,PESO,DENSIDAD FROM INSUMO where IDINSUMO = '"+text1+"'");
		else
			insumos = buscarTodos();
		if(!text2.isEmpty())
			plantas = plantaService.buscarPorId("SELECT IDPLANTA,NOMBRE FROM PLANTA where IDPLANTA = '"+text2+"'");
		else
			plantas = plantaService.buscarTodos();
		
    	if(!text1.isEmpty()) {
    		if(primerConsulta) {
    			busqueda+= " where ";
    			primerConsulta = false;
    		}
    		else {
    			busqueda += " and ";
    		}
    		busqueda += " IDPLANTA = '"+text1+"' ";
    	}
       	if(!text2.isEmpty()) {
    		if(primerConsulta) {
    			busqueda+= " where ";
    			primerConsulta = false;
    		}
    		else {
    			busqueda += " and ";
    		}
    		busqueda += " IDINSUMO = '"+text1+"' ";
    	}
    	if(primerConsulta) {
			busqueda+= " where ";
		}
		else {
			busqueda += " and ";
		}
       	busqueda+= "STOCK < PUNTOREPOSICION";
       	for(Planta p : plantas)
       		System.out.println(p.getNombre());
		stockins = insumoDao.busquedaStock(busqueda,insumos,plantas);
		return stockins;
	}
	
	public List<Insumo> buscarPorId(String texto) {
		return insumoDao.buscarPorId(texto);
	}
	
	public StockInsumo crearSockInsumo(StockInsumo s) {
		return this.insumoDao.saveOrUpdate(s);
	}
	
	public List<StockInsumo> busquedaStockInsumos(Planta p) {
		return insumoDao.busquedaStockInsumos(p);
	}

	public void borrar(Integer idPlanta, Integer idInsumo) {
		insumoDao.borrar(idPlanta,idInsumo);
		
	}
	
}
