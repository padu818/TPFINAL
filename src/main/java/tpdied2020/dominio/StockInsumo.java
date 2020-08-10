package tpdied2020.dominio;

public class StockInsumo{
	
	private Integer IDRegistro;
	private Insumo insumo;
	private Integer stock;
	private Integer puntoReposicion;
	private Planta planta;
	
	public Integer getIDRegistro() {
		return IDRegistro;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public void setIDRegistro(Integer iDRegistro) {
		IDRegistro = iDRegistro;
	}
	public Insumo getInsumo() {
		return insumo;
	}
	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}
	public Integer getCantidad() {
		return stock;
	}
	public void setCantidad(Integer cantidad) {
		this.stock = cantidad;
	}
	public Integer getPuntoReposicion() {
		return puntoReposicion;
	}
	public void setPuntoReposicion(Integer puntoReposicion) {
		this.puntoReposicion = puntoReposicion;
	}
	public Planta getPlanta() {
		return planta;
	}
	public void setPlanta(Planta planta) {
		this.planta = planta;
	}
	
}
