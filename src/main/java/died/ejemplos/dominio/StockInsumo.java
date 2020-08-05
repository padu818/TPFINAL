package died.ejemplos.dominio;

public class StockInsumo{
	private Integer IDRegistro;
	private Insumo insumo;
	private Integer stock;
	private Integer puntoReposicion; // lo utilizo?
	//private Integer cantidadMax; // 

	private Planta planta;
	/*
	 * Cuando se registra el stock de cada producto en cada planta, se almacena el ID del registro,
	 *  el insumo que se representa, la cantidad del insumo que hay en stock según las unidades determinadas,
	 *   y cuál es el punto de reposición o punto de pedido (es decir un valor que determina que si la cantidad de unidades
	 *  es menor al punto de reposición esta planta necesita reponer el producto, antes de que su stock llegue a cero)
	 */
	
	public Integer getIDRegistro() {
		return IDRegistro;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
//	public Integer getCantidadMax() {
//		return cantidadMax;
//	}
//	public void setCantidadMax(Integer cantidadMax) {
//		this.cantidadMax = cantidadMax;
//	}
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
	public Integer getCantidadMaximaDeAlmacenamiento() {
		return puntoReposicion;
	}
	public void setCantidadMaximaDeAlmacenamiento(Integer cantidadMaximaDeAlmacenamiento) {
		this.puntoReposicion = cantidadMaximaDeAlmacenamiento;
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
