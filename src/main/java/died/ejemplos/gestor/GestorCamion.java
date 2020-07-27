package died.ejemplos.gestor;

import java.time.LocalDate;
import java.util.List;

import died.ejemplos.dao.CamionDao;
import died.ejemplos.dao.CamionDaoMysql;
import died.ejemplos.dominio.Camion;

public class GestorCamion {
	
	private CamionDao camionDao = new CamionDaoMysql();

	public Camion crearCamion(Camion c) {
		// si hay alguna regla de negocio que indque que no se 
		// puede agregar un camion si no se cumplen determinadas
		// condiciones en otras entidades o reglas 
		// se valida aquí
		return this.camionDao.saveOrUpdate(c);
	}
	
	public List<Camion> buscarTodos() {
		return camionDao.buscarTodos();
	}

	public List<Camion> busqueda(String patente, String marca, String modelo, String kmr, String cosths, String costkm, String fecha) {
		String condicionesConsulta = "SELECT ID,PATENTE,MARCA,MODELO,KM,COSTO_KM,COSTO_HORA,FECHA_COMPRA FROM CAMION";
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
		return camionDao.busqueda(condicionesConsulta);

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

}
