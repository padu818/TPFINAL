package tpdied2020.dominio;

public class General extends Insumo {
	
	private Double peso;

	public General() {
		super();
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
			switch (this.getUnidadMedida()) {
				case "KILO":
					return this.getPeso();
				case "PIEZA":
					return this.getPeso();
				case "GRAMO":
					return 1000*this.getPeso();
				case "METRO":
					return this.getPeso();
				case "LITRO":
					return this.getPeso();
				case "M3":
					return this.getPeso();
				case "M2":
					return this.getPeso();
				default:
					return this.getPeso();
			}
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
