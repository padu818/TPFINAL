package died.ejemplos.gui.ayuda;
import died.ejemplos.gui.ayuda.Grafo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import died.ejemplos.dominio.Planta;
import died.ejemplos.dominio.Ruta;

public class GrafoPlanta extends Grafo<Planta>{
	

	
	   public List<Vertice<Planta>> camino(Planta v1,Planta v2) {
		   Vertice<Planta> p1 = new Vertice<Planta>(v1);
		   Vertice<Planta> p2 = new Vertice<Planta>(v2);
	    	return camino(p1, p2);
	   }
	   
	    public Boolean hayCamino(Planta v1,Planta v2) {
	    	System.out.println(v2.getNombre());
	    	List<Vertice<Planta>> adyacentes = this.getAdyacentes(new Vertice<Planta>(v1));
	    	for(Vertice<Planta> vAdy : adyacentes) {
	    		Vertice<Planta> other = new Vertice<Planta>(v2);
	    		if(vAdy.getValor().getIdPlanta().equals(v2.getIdPlanta())) {
	    			return true;
	    		} else {
	    			return hayCamino(vAdy.getValor(), v2);
	    		}
	    	}
	    	return false;
	    }
	    
	     public Boolean hayCamino(Vertice<Planta> v1,Vertice<Planta> v2) {
	     	List<Vertice<Planta>> adyacentes = getAdyacentes(v1);
	     	Boolean hay = false;
	     	for(Vertice<Planta> vAdy : adyacentes) {
//	     		System.out.println(vAdy.getValor().getNombre());
//	     		System.out.println(v2.getValor().getNombre());
	     		if(vAdy.getValor().equals(v2.getValor())) {
	     			return true;
	     		} else {
	     			hay = hayCamino(vAdy, v2);
	     			if(hay == true)
	     				return hay;
	     		}
	     	}
	     	return hay;
	     }
	   
	     public List<List<Vertice<Planta>>> caminos(Vertice<Planta> v1,Vertice<Planta> v2) {
	        	List<Planta> nuevo = new LinkedList<Planta>();
	     //   	nuevo.add(v1);
	        	return caminos(v1,v2,nuevo);
	        }
	        
	     public List<List<Vertice<Planta>>> caminos(Vertice<Planta> v1,Vertice<Planta> v2, List<Planta> lista) {

	       	List<List<Vertice<Planta>>> salida = new ArrayList<List<Vertice<Planta>>>();
			
			List<Vertice<Planta>> resultado = new ArrayList<Vertice<Planta>>();
			//estructuras auxiliares
			Queue<Vertice<Planta>> pendientes = new LinkedList<Vertice<Planta>>();
		//	Queue<Planta> camino = new LinkedList<Planta>();
		//	marcados.add(v1);
			pendientes.add(v1);
			
			while(!pendientes.isEmpty()){
				resultado.add(v1);
				Vertice<Planta> actual = pendientes.poll();
				List<Vertice<Planta>> adyacentes = this.getAdyacentes(actual);
				if(actual.getValor().getIdPlanta().equals(v2.getValor().getIdPlanta())) {
					resultado.add(actual);
					salida.add(resultado);
					resultado.remove(actual);
				//	resultado.remove(actual.getValor());
				}
				else if(adyacentes.size() != 0) {
					for(Vertice<Planta> v : adyacentes){
							pendientes.add(v);
						}
					}
				else if(adyacentes.size() == 0 ) {
					resultado.removeAll(resultado);
				}
	        }
	       	return salida;
	       }
	     
	     public List<Vertice<Planta>> camino(Vertice<Planta> v1,Vertice<Planta> v2) {
	      	List<Vertice<Planta>> nuevo = new ArrayList<Vertice<Planta>>();
	      	nuevo.add(v1);
	      	return camino(v1,v2,nuevo);
	      }
	      
	      public List<Vertice<Planta>> camino(Vertice<Planta> v1,Vertice<Planta> v2, List<Vertice<Planta>> lista) {
	     	List<Vertice<Planta>> adyacentes = getAdyacentes(v1);
	     	for(Vertice<Planta> vAdy : adyacentes) {
	     		if(vAdy.equals(v2)) {
	     			lista.add(vAdy);
	     			return lista;
	     		} 
	     		else {
	     			lista.add(vAdy);
	     			List<Vertice<Planta>> auxiliar = camino(vAdy, v2, lista);
	     			if(auxiliar == null) {
	     				lista.remove(v1);
	     			}
	     			else {
	     				return auxiliar;
	     			}
	     		}
	     	}
	     	return null;
	     }
	     
	     
}
