package tpdied2020.dominio;

public abstract class Insumo {

	protected Integer idInsumo;
	protected String nombre;
	protected String descripcion;
	protected Double costo;
	protected Unidad unidadMedida;
	
	
	
	
	public abstract Double pesoPorUnidad();
	

	public Insumo() {
		super();
	}



	public abstract String getUnidadMedida(); 
	
	

	
	public abstract void setUnidadMedida(Unidad medida);
	
	public abstract void setUnidadMedida(String medida) ;

	
	public abstract String getNombre();

	
	public abstract void setNombre(String nombre) ;
	
	
	public abstract Integer getIdProduto() ;

	
	public abstract void setIdProduto(Integer idProduto) ;
	public abstract String getDescripcion() ;
	public abstract void setDescripcion(String descripcion);
	public abstract Double getCosto();
	public abstract void setCosto(Double costo) ;




	public abstract String getTipoInsumo();
	
	

}
