package tpdied2020.gestor;


import tpdied2020.dao.InsumoDao;
import tpdied2020.dao.InsumoDaosql;
import tpdied2020.dominio.DetallesInsumoSolicitado;
import tpdied2020.dominio.Insumo;
import tpdied2020.dominio.Planta;
import tpdied2020.dominio.StockInsumo;

import java.util.ArrayList;
import java.util.List;

public class GestorInsumo {
	
private static GestorInsumo instanciaGestor = null;
	
	public static  GestorInsumo get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorInsumo();
        }    
        return instanciaGestor;
    }
	
	
	private InsumoDao insumoDao = new InsumoDaosql();
	private GestorPlanta plantaService = GestorPlanta.get();
	
	public Insumo crearInsumo(Insumo i) {
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

	public List<StockInsumo> buscarTodoStock(List<Insumo> insumos, List<Planta> plantas) {
		List<StockInsumo> st = insumoDao.busquedaStock(insumos);
		for(StockInsumo s :st) {
			for(Planta p : plantas) {
				if(p.getIdPlanta() ==s.getPlanta().getIdPlanta()) {
					s.setPlanta(p);
					break;
				}
					
			}
		}
		return st;
	}
	
	public List<Planta> tieneStock(List<DetallesInsumoSolicitado> insumosSolicitado, List<Planta> planta){
		String consulta = "";
		List<Planta> resultado = new ArrayList<Planta>();
		Integer i = 0;
		Integer max = insumosSolicitado.size();
		for(DetallesInsumoSolicitado d: insumosSolicitado) {
			consulta += " select pl.IDPLANTA,pl.NOMBRE from planta pl,stockinsumo st where pl.idplanta = st.idplanta and " + 
					"st.idinsumo = "+d.getInsumo().getIdProduto().toString()+
					" and pl.idplanta <> "+d.getPedido().getDestino().getIdPlanta()+
					" and st.stock >= "+ d.getCantidad()+" ";
			i++;
			if(i < max) {
				consulta += " INTERSECT ";
			}
		}
		consulta += " order by idplanta ";
		resultado.addAll(insumoDao.obtener(consulta));
		
		return resultado;
		
	}
	
	
}
