package tpdied2020.gui.auxiliar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;



public class Grafo<T> {
	
	
	private List<Arista<T>> aristas;
	private List<Vertice<T>> vertices;

	
	
	
	public List<Arista<T>> getAristas() {
		return aristas;
	}

	public void setAristas(List<Arista<T>> aristas) {
		this.aristas = aristas;
	}

	public List<Vertice<T>> getVertices() {
		return vertices;
	}

	public void setVertices(List<Vertice<T>> vertices) {
		this.vertices = vertices;
	}


	public Grafo(){
		this.aristas = new ArrayList<Arista<T>>();
		this.vertices = new ArrayList<Vertice<T>>();
	}

	public void addNodo(T nodo){
		this.addNodo(new Vertice<T>(nodo));
	}

	private void addNodo(Vertice<T> nodo){
		this.vertices.add(nodo);
	}
	
	public void conectar(T n1,T n2){
		this.conectar(getNodo(n1), getNodo(n2), 0.0,0.0,0.0);
	}

	
	public void conectar(T n1,T n2,Double hs, Double km, Double maximo){
		this.conectar(new Vertice<T>(n1), new Vertice<T>(n2), hs,km,maximo);
	}

	private void conectar(Vertice<T> nodo1,Vertice<T> nodo2,Double hs, Double km, Double maximo){
		this.aristas.add(new Arista<T>(nodo1,nodo2,hs,km,maximo));
	}
	
	public Vertice<T> getNodo(T valor){
		return this.vertices.get(this.vertices.indexOf(new Vertice<T>(valor)));
	}
	

	public List<T> getAdyacentes(T valor){ 
		Vertice<T> unNodo = this.getNodo(valor);
		List<T> salida = new ArrayList<T>();
		for(Arista<T> enlace : this.aristas){
			if( enlace.getInicio().equals(unNodo)){
				salida.add(enlace.getFin().getValor());
			}
		}
		return salida;
	}
	

	public List<Vertice<T>> getAdyacentes(Vertice<T> unNodo){ 
		List<Vertice<T>> salida = new ArrayList<Vertice<T>>();
		for(Arista<T> enlace : this.aristas){
			if( enlace.getInicio().equals(unNodo)){
				salida.add(enlace.getFin());
			}
		}
		return salida;
	}
	
	public void imprimirAristas(){
		System.out.println(this.aristas.toString());
	}

	public Integer gradoEntrada(Vertice<T> vertice){
		Integer res =0;
		for(Arista<T> arista : this.aristas){
			if(arista.getFin().equals(vertice)) ++res;
		}
		return res;
	}

	public Integer gradoSalida(Vertice<T> vertice){
		Integer res =0;
		for(Arista<T> arista : this.aristas){
			if(arista.getInicio().equals(vertice)) ++res;
		}
		return res;
	}

	public List<T> recorridoAnchura(Vertice<T> inicio){
		List<T> resultado = new ArrayList<T>();
		Queue<Vertice<T>> pendientes = new LinkedList<Vertice<T>>();
		HashSet<Vertice<T>> marcados = new HashSet<Vertice<T>>();
		marcados.add(inicio);
		pendientes.add(inicio);
		
		while(!pendientes.isEmpty()){
			Vertice<T> actual = pendientes.poll();
			List<Vertice<T>> adyacentes = this.getAdyacentes(actual);
			resultado.add(actual.getValor());
			for(Vertice<T> v : adyacentes){
				if(!marcados.contains(v)){ 
					pendientes.add(v);
					marcados.add(v);
				}
			}
		}
		return resultado;
 	}
	

	
	public List<T> recorridoProfundidad(Vertice<T> inicio){
		List<T> resultado = new ArrayList<T>();
		Stack<Vertice<T>> pendientes = new Stack<Vertice<T>>();
		HashSet<Vertice<T>> marcados = new HashSet<Vertice<T>>();
		marcados.add(inicio);
		pendientes.push(inicio);
		
		while(!pendientes.isEmpty()){
			Vertice<T> actual = pendientes.pop();
			List<Vertice<T>> adyacentes = this.getAdyacentes(actual);
			resultado.add(actual.getValor());
			for(Vertice<T> v : adyacentes){
				if(!marcados.contains(v)){ 
					pendientes.push(v);
					marcados.add(v);
				}
			}
		}
		return resultado;
 	}
 	
	public List<T> recorridoTopologico(){
		List<T> resultado = new ArrayList<T>();
		Map<Vertice<T>,Integer> gradosVertice = new HashMap<Vertice<T>,Integer>();
		for(Vertice<T> vert : this.vertices){
			gradosVertice.put(vert, this.gradoEntrada(vert));
		}
		while(!gradosVertice.isEmpty()){
		
			List<Vertice<T>> nodosSinEntradas = gradosVertice.entrySet()
							.stream()
							.filter( x -> x.getValue()==0)
							.map( p -> p.getKey())
							.collect( Collectors.toList());
			
            if(nodosSinEntradas.isEmpty()) System.out.println("TIENE CICLOS");
            
			for(Vertice<T> v : nodosSinEntradas){
            	resultado.add(v.getValor());
            	gradosVertice.remove(v);
            	for(Vertice<T> ady: this.getAdyacentes(v)){
            		gradosVertice.put(ady,gradosVertice.get(ady)-1);
            	}
            }
		}
		
		System.out.println(resultado);
		return resultado;
 	}
        
    private boolean esAdyacente(Vertice<T> v1,Vertice<T> v2){
    	List<Vertice<T>> ady = this.getAdyacentes(v1);
        for(Vertice<T> unAdy : ady){
        	if(unAdy.equals(v2)) return true;
        }
        return false;
    }

     public Boolean hayCamino(Vertice<T> v1,Vertice<T> v2) {
    	List<Vertice<T>> adyacentes = getAdyacentes(v1);
    	for(Vertice<T> vAdy : adyacentes) {
    		if(vAdy.equals(v2)) {
    			return true;
    		} else {
    			return hayCamino(vAdy, v2);
    		}
    	}
    	return false;
    }
     
     public Boolean hayCamino(Vertice<T> v1,Vertice<T> v2, Integer n) {
    	List<Vertice<T>> adyacentes = getAdyacentes(v1);
    	for(Vertice<T> vAdy : adyacentes) {
    		Integer auxiliar = n;
    		if(vAdy.equals(v2) && auxiliar == 1) {
    				return true;
    		} 
    		else if(auxiliar > 1){
    			auxiliar--;
    			if(hayCamino(vAdy, v2,auxiliar))
    				return true;
    		}
    	}
    	return false;
    }
     
     public Boolean hayCaminoMenor(Vertice<T> v1,Vertice<T> v2, Integer n) {
    	List<Vertice<T>> adyacentes = getAdyacentes(v1);
    	for(Vertice<T> vAdy : adyacentes) {
    		Integer auxiliar = n;
    		if(vAdy.equals(v2) && auxiliar > 1) {
    				return true;
    		} 
    		else if(auxiliar > 1){
    			auxiliar--;
    			if(hayCaminoMenor(vAdy, v2,auxiliar))
    				return true;
    		}
    	}
    	return false;
    }
     
     public List<Vertice<T>> camino(Vertice<T> v1,Vertice<T> v2) {
     	List<Vertice<T>> nuevo = new ArrayList<Vertice<T>>();
     	nuevo.add(v1);
     	return camino(v1,v2,nuevo);
     }
     
     public List<Vertice<T>> camino(Vertice<T> v1,Vertice<T> v2, List<Vertice<T>> lista) {
    	List<Vertice<T>> adyacentes = getAdyacentes(v1);
    	for(Vertice<T> vAdy : adyacentes) {
    		if(vAdy.equals(v2)) {
    			lista.add(vAdy);
    			return lista;
    		} 
    		else {
    			lista.add(vAdy);
    			List<Vertice<T>> auxiliar = camino(vAdy, v2, lista);
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
     
     public Integer cantCaminos(Vertice<T> v1,Vertice<T> v2) {
     	List<Vertice<T>> adyacentes = getAdyacentes(v1);
     	Integer valor = 0;
     	for(Vertice<T> vAdy : adyacentes) {
     		if(vAdy.equals(v2)) {
     			valor++;
     		} 
     		else {

     			valor += cantCaminos(vAdy, v2);
   
     		}
     	}
     	return valor;
     }
     
     public Integer cantCaminos(Vertice<T> v1,Vertice<T> v2, Integer tam) {
      	List<Vertice<T>> adyacentes = getAdyacentes(v1);
      	Integer valor = 0;
      	for(Vertice<T> vAdy : adyacentes) {
      		Integer algun = tam;
      		if(vAdy.equals(v2) && algun == 1) {
      			valor++;
      		} 
      		else {
      			algun--;
      			valor += cantCaminos(vAdy, v2, algun);
    
      		}
      	}
      	return valor;
      }
      
     
     public List<Vertice<T>> camino(Vertice<T> v1,Vertice<T> v2,Integer n) {
      	List<Vertice<T>> nuevo = new ArrayList<Vertice<T>>();
      	nuevo.add(v1);
      	return camino(v1,v2,nuevo,n);
      }
      
      public List<Vertice<T>> camino(Vertice<T> v1,Vertice<T> v2, List<Vertice<T>> lista, Integer n) {
     	List<Vertice<T>> adyacentes = getAdyacentes(v1);
     	for(Vertice<T> vAdy : adyacentes) {
     		Integer auxiliar = n;
     		if(vAdy.equals(v2) && auxiliar == 1) {
     			lista.add(vAdy);
     			return lista;
     		} 
     		else if(auxiliar > 1){
     			auxiliar--;
     			lista.add(vAdy);
     			List<Vertice<T>> aux = camino(vAdy, v2, lista,auxiliar);
     			if(aux == null) {
     				lista.remove(vAdy);
     			}
     			else {
     				return aux;
     			}
     		}
     	}
     	return null;
     }

      public List<List<Vertice<T>>> caminos(Vertice<T> v1,Vertice<T> v2,Integer tam) {
        	List<Vertice<T>> nuevo = new LinkedList<Vertice<T>>();
        	nuevo.add(v1);
        	return caminos(v1,v2,nuevo, tam);
        }
        
        public List<List<Vertice<T>>> caminos(Vertice<T> v1,Vertice<T> v2, List<Vertice<T>> lista, Integer maximo) {

       	List<List<Vertice<T>>> salida = new ArrayList<List<Vertice<T>>>();
		
		List<Vertice<T>> resultado = new ArrayList<Vertice<T>>();
		Queue<Vertice<T>> pendientes = new LinkedList<Vertice<T>>();
		Queue<Integer> camino = new LinkedList<Integer>();
		pendientes.add(v1);
		camino.add(maximo);
		while(!pendientes.isEmpty()){
			Integer valor = camino.poll();
			Vertice<T> actual = pendientes.poll();
			List<Vertice<T>> adyacentes = this.getAdyacentes(actual);
			if(actual.equals(v2) && valor == 1)
				resultado.add(actual);
			else if(adyacentes.size() != 0 && valor > 1) {
				for(Vertice<T> v : adyacentes){
						pendientes.add(v);
						camino.add(valor);
					}
				}
			}

       	return salida;
       }
      
      
      
      
}