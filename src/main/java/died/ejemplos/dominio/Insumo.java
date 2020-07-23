package died.ejemplos.dominio;

public class Insumo {

	private Integer idInsumo;
	private String nombre;
	private String descripcion;
	private double costo;
	private Unidad unidadMedida;
	
	
	
	
	
	
	
	
	
	
	public Unidad getMedida() {
		return unidadMedida;
	}
	public void setMedida(Unidad medida) {
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
	public void setCosto(double costo) {
		this.costo = costo;
	}
	

}
