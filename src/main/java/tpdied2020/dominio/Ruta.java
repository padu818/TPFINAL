package tpdied2020.dominio;


public class Ruta {

	private Integer idRuta;
	private Planta origen;
	private Planta destino;
	private Double distanciaKM;
	private Double duracionHs;
	private Double cantMaxATransportar;
	
	public Integer getId() {
		return idRuta;
	}
	public void setId(Integer id) {
		this.idRuta = id;
	}
	public Double getPesoMaxKg() {
		return cantMaxATransportar;
	}
	public void setPesoMaxKg(Double pesoMaxKg) {
		this.cantMaxATransportar = pesoMaxKg;
	}
	public Planta getOrigen() {
		return origen;
	}
	public void setOrigen(Planta origen) {
		this.origen = origen;
	}
	public Planta getDestino() {
		return destino;
	}
	public void setDestino(Planta destino) {
		this.destino = destino;
	}
	public Double getDistanciaKM() {
		return distanciaKM;
	}
	public void setDistanciaKM(Double distanciaKM) {
		this.distanciaKM = distanciaKM;
	}
	public Double getDuracionHs() {
		return duracionHs;
	}
	public void setDuracionHs(Double double1) {
		this.duracionHs = double1;
	}
	
	
}
