package died.ejemplos.dominio;

public class General extends Insumo {
	private Double peso;


	
	
//	public General(Integer idInsumo, String nombre, String descripcion, Double costo, Unidad unidadMedida,Double p ) {
//		super();
//		this.idInsumo = idInsumo;
//		this.nombre = nombre;
//		this.descripcion = descripcion;
//		this.costo = costo;
//		this.unidadMedida = unidadMedida;
//		this.peso = p;
//		// TODO Auto-generated constructor stub
//	}


	

	public General() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getUnidadMedida() {
		return unidadMedida.toString();
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
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}


	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	@Override
	public Double pesoPorUnidad() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public String getTipoInsumo() {
		return "GENERAL";
	}


	@Override
	public void setUnidadMedida(String medida) {
		switch (medida) {
		case "KILO":
			this.setUnidadMedida(Unidad.KILO);
			break;
		case "PIEZA":
			this.setUnidadMedida(Unidad.PIEZA);
			break;
		case "GRAMO":
			this.setUnidadMedida(Unidad.GRAMO);
			break;
		case "METRO":
			this.setUnidadMedida(Unidad.METRO);
			break;
		case "LITRO":
			this.setUnidadMedida(Unidad.LITRO);
			break;
		case "M3":
			this.setUnidadMedida(Unidad.M3);
			break;
		case "M2":
			this.setUnidadMedida(Unidad.M2);
			break;
		}
		
	}
	
}
