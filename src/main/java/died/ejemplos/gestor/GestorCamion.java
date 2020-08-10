package died.ejemplos.gestor;

import java.time.LocalDate;
import java.util.List;

import died.ejemplos.dao.CamionDao;
import died.ejemplos.dao.CamionDaoMysql;
import died.ejemplos.dao.PlantaDaoSql;
import died.ejemplos.dominio.Camion;
import died.ejemplos.dominio.Planta;

public class GestorCamion {
	
	private GestorPlanta plantaService = new GestorPlanta();
	private CamionDao camionDao = new CamionDaoMysql();

	public Camion crearCamion(Camion c) {
		// si hay alguna regla de negocio que indque que no se 
		// puede agregar un camion si no se cumplen determinadas
		// condiciones en otras entidades o reglas 
		// se valida aquí
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
