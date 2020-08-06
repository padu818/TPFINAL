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
	
	public List<StockInsumo> busqueda(String plant,String ins){
		String busqueda = "SELECT IDPLANTA,IDINSUMO,STOCK,PUNTOREPOSICION FROM STOCKINSUMO";
		Boolean primerConsulta = true;
		List<StockInsumo> stockins = new ArrayList<StockInsumo>();
		List<Insumo> insumos = new ArrayList<Insumo>();
		List<Planta> plantas = new ArrayList<Planta>();
		

		if(!ins.isEmpty())
			insumos = buscarPorId("SELECT IDINSUMO,NOMBRE,DESCRIPCION,UNIDAD_MEDIDA,COSTO,TIPO,PESO,DENSIDAD FROM INSUMO where IDINSUMO = '"+ins+"'");
		else
			insumos = buscarTodos();

		if(!plant.isEmpty())
			plantas = plantaService.buscarPorId(plant);
		else
			plantas = plantaService.buscarTodos();
		System.out.println("cantidad de plantas "+plantas.size());
		System.out.println("cantidad de insumo "+insumos.size());
    	if(!plant.isEmpty()) {
    		if(primerConsulta) {
    			busqueda+= " where ";
    			primerConsulta = false;
    		}
    		else {
    			busqueda += " and ";
    		}
    		busqueda += " IDPLANTA = '"+plant+"' ";
    	}
       	if(!ins.isEmpty()) {
    		if(primerConsulta) {
    			busqueda+= " where ";
    			primerConsulta = false;
    		}
    		else {
    			busqueda += " and ";
    		}
    		busqueda += " IDINSUMO = '"+ins+"' ";
    	}
    	if(primerConsulta) {
			busqueda+= " where ";
		}
		else {
			busqueda += " and ";
		}
       	busqueda+= "STOCK < PUNTOREPOSICION";
       	System.out.println(busqueda);
       	System.out.println("cantidad de letras "+busqueda.length());
		stockins = insumoDao.busquedaStock(busqueda,insumos,plantas);
		return stockins;
	}
	
	public List<Insumo> buscarPorId(String texto) {
		return insumoDao.buscarPorId(texto);
	}
	
	public Integer[] stockInsumos(Integer cantInsumos, List<Insumo> insumos) {
		Integer[] aux = new Integer[cantInsumos];
	//	List<Planta> plantas = new ArrayList<Planta>();
//		plantas = plantaService.buscarTodos();
		
		for(int i = 0;i < cantInsumos; i++) {
			aux[i] = 0;
		}
		List<StockInsumo> st = insumoDao.busquedaStock(insumos);
		for(int i = 0; i < insumos.size(); i++) {
			for(StockInsumo s :st) {
				if(insumos.get(i).getIdProduto() == s.getInsumo().getIdProduto())
					aux[i] += s.getStock();
			}
		}
		return aux;
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
