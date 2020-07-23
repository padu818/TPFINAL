package died.ejemplos.dominio;

import java.util.List;

import died.ejemplos.gui.ayuda.Grafo;

public class Planta extends Grafo<Planta>{//ver
	private Integer idPlanta;
	private String nombre;
	private List<Insumo> productos;
	private List<Camion> camionesDisponibles;
	
	
	
	
	
	
	public Integer getIdPlanta() {
		return idPlanta;
	}
	public void setIdPlanta(Integer idPlanta) {
		this.idPlanta = idPlanta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
