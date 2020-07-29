package died.ejemplos.dominio;

public abstract class Insumo {

	protected Integer idInsumo;
	protected String nombre;
	protected String descripcion;
	protected Double costo;
	protected Unidad unidadMedida;
	
	
	
	
	public abstract Double pesoPorUnidad();
	
	
	
	
	
	public Unidad getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(Unidad medida) {
		this.unidadMedida = medida;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getIdProduto() {
		return idInsumo;
	}
	public void setIdProduto(Integer idProduto) {
		this.idInsumo = idProduto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	

}
