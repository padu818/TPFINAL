package tpdied2020.gestor;

import java.util.List;

import tpdied2020.dao.CamionDao;
import tpdied2020.dao.CamionDaoMysql;
import tpdied2020.dominio.Camion;
import tpdied2020.dominio.Planta;

public class GestorCamion {
	
	private static GestorCamion instanciaGestor = null;
	
	public static  GestorCamion get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorCamion();
        }    
        return instanciaGestor;
    }
	
	private GestorPlanta plantaService = GestorPlanta.get();
	private CamionDao camionDao = new CamionDaoMysql();

	public Camion crearCamion(Camion c) {
		return this.camionDao.saveOrUpdate(c);
	}
	
	public List<Camion> buscarTodos() {
		List<Camion>  camiones = camionDao.buscarTodos();
		List<Planta>  plantas = plantaService.buscarTodos();
		for(Planta p :plantas) {
			for(Camion c :camiones) {
				if(c.getPlanta().getIdPlanta() == p.getIdPlanta() )
					c.setPlanta(p);
			}
		}
		return camiones;
	}
	
	public List<Camion> buscarTodos(List<Planta> plantas) {
		List<Camion>  camiones = camionDao.buscarTodos();
		for(Planta p :plantas) {
			for(Camion c :camiones) {
				if(c.getPlanta().getIdPlanta() == p.getIdPlanta() )
					c.setPlanta(p);
			}
		}
		return camiones;
	}
	
	public List<Camion> busqueda(String id){
		String condicionesConsulta = "SELECT ID,PATENTE,MARCA,MODELO,KM,COSTO_KM,COSTO_HORA,FECHA_COMPRA,IDPLANTA FROM CAMION where IDPLANTA = "+id;
		return camionDao.busqueda(condicionesConsulta);
	
	}
	

	public List<Camion> busqueda(String patente, String marca, String modelo, String kmr, String cosths, String costkm, String fecha) {
		String condicionesConsulta = "SELECT ID,PATENTE,MARCA,MODELO,KM,COSTO_KM,COSTO_HORA,FECHA_COMPRA,IDPLANTA FROM CAMION";
		Boolean primerConsulta = true;
    
    	
    	if(!patente.isEmpty()) {
    		if(primerConsulta) {
    			primerConsulta = false;
    			condicionesConsulta += " where ";
      		}
    		else {
    			condicionesConsulta += " and ";
    		}
    		condicionesConsulta += "PATENTE like '"+patente+"%' ";
    	}
    	
    	if(!marca.isEmpty()) {
    		if(primerConsulta) {
    			primerConsulta = false;
    			condicionesConsulta += " where ";
      		}
    		else {
    			condicionesConsulta += " and ";
    		}
   			condicionesConsulta += " MARCA like '"+marca+"%' ";
    	}
      	if(!modelo.isEmpty()) {
    		if(primerConsulta) {
    			primerConsulta = false;
    			condicionesConsulta += " where ";
      		}
    		else {
    			condicionesConsulta += " and ";
    		}
   			condicionesConsulta += " MODELO like '"+modelo+"%' ";
    	}
    	
    	if(kmr.equals("-") == false) {
    		System.out.println(kmr);
    		if(primerConsulta) {
    			primerConsulta = false;
    			condicionesConsulta += " where ";
      		}
    		else {
    			condicionesConsulta += " and ";
    		}
    		condicionesConsulta += " KM = '"+kmr+"' ";
    	}
    	
    	if(!cosths.isEmpty()) {
    		if(primerConsulta) {
    			condicionesConsulta += " where ";
    		}
    		else {
    			condicionesConsulta += " and ";
    		}
    		condicionesConsulta += " COSTO_HORA = '"+cosths+"' ";
    	}
     	if(!costkm.isEmpty()) {
    		if(primerConsulta) {
    			condicionesConsulta += " where ";
    		}
    		else {
    			condicionesConsulta += " and ";
    		}
    		condicionesConsulta += " COSTO_KM = '"+costkm+"' ";
    	}
    	if(!fecha.isEmpty()) {
    		if(primerConsulta) {
    			condicionesConsulta += " where ";
    		}
    		else {
    			condicionesConsulta += " and ";
    		}
    		condicionesConsulta += " FECHA_COMPRA = '"+fecha+"' ";
    	}
    	condicionesConsulta += " order by ID asc";
		List<Camion> camiones = camionDao.busqueda(condicionesConsulta);
		
		List<Planta>  plantas = plantaService.buscarTodos();
		for(Planta p :plantas) {
			for(Camion c :camiones) {
				if(c.getPlanta().getIdPlanta() == p.getIdPlanta() )
					c.setPlanta(p);
			}
		}
		return camiones;

	}

	public boolean validarPatente(String textoPatente) {
		List<Camion> camiones = this.camionDao.buscarTodos();
		for(Camion cam : camiones) {
			if (cam.getPatente().equals(textoPatente)) {
				return true;
			}
		}
		return false;
	}

	public void eliminar(Camion camion) {
		camionDao.borrar(camion.getPatente());
	}

}
