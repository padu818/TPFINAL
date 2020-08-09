package died.ejemplos.dominio;

import java.util.List;

import died.ejemplos.gui.ayuda.Grafo;


public class Planta{//ver
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
	

	@Override
	public String toString() {
		return nombre;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPlanta == null) ? 0 : idPlanta.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planta other = (Planta) obj;
		if (idPlanta == null) {
			if (other.idPlanta != null)
				return false;
		} else if (idPlanta != other.idPlanta)
			return false;
		return true;
	}
	
	
}
