package died.ejemplos.gui.ayuda;
import died.ejemplos.gui.ayuda.Grafo;

import java.util.ArrayList;
import java.util.List;

import died.ejemplos.dominio.Planta;
import died.ejemplos.dominio.Ruta;

public class GrafoPlanta extends Grafo<Planta>{
	
	
	
	   public List<Vertice<Planta>> camino(Planta v1,Planta v2) {
		   Vertice<Planta> p1 = new Vertice<Planta>();
		   p1.setValor(v1);
		   Vertice<Planta> p2 = new Vertice<Planta>();
		   p2.setValor(v2);
	    	return camino(p1, p2);
	   }
	   
	    public Boolean hayCamino(Planta v1,Planta v2) {
	    	System.out.println(v2.getNombre());
	    	List<Planta> adyacentes = getAdyacentes(v1);
	    	for(Planta vAdy : adyacentes) {
	    		if(vAdy.equals(v2)) {
	    			return true;
	    		} else {
	    			return hayCamino(vAdy, v2);
	    		}
	    	}
	    	return false;
	    }
	    

	   
//	    public List<Ruta> caminoKm(Planta v1,Planta v2) {
//	     	List<Planta> nuevo = new ArrayList<Planta>();
//	     	nuevo.add(v1);
//	     	return caminoKm(v1,v2,nuevo);
//	     }
//	     
//	     public List<Arista>> camino(Vertice<T> v1,Vertice<T> v2, List<Vertice<T>> lista) {
//	    	List<Vertice<T>> adyacentes = getAdyacentes(v1);
//	    	for(Vertice<T> vAdy : adyacentes) {
//	    		if(vAdy.equals(v2)) {
//	    			lista.add(vAdy);
//	    			return lista;
//	    		} 
//	    		else {
//	    			lista.add(vAdy);
//	    			List<Vertice<T>> auxiliar = camino(vAdy, v2, lista);
//	    			if(auxiliar == null) {
//	    				lista.remove(v1);
//	    			}
//	    			else {
//	    				return auxiliar;
//	    			}
//	    		}
//	    	}
//	    	return null;
//	    }
}
