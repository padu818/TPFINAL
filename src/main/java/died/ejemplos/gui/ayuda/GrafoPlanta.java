package died.ejemplos.gui.ayuda;
import died.ejemplos.gui.ayuda.Grafo;

import died.ejemplos.dominio.Planta;

public class GrafoPlanta extends Grafo<Planta>{
	
	
	
	   public Boolean hayCamino(Planta v1,Planta v2) {
		   Vertice<Planta> p1 = new Vertice<Planta>();
		   p1.setValor(v1);
		   Vertice<Planta> p2 = new Vertice<Planta>();
		   p2.setValor(v2);
	    	return hayCamino(p1, p2);
	   }
	
}
