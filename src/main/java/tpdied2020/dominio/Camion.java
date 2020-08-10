package tpdied2020.dominio;

import java.time.LocalDate;

public class Camion {
	
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getCostoPorKM() {
		return costoPorKM;
	}
	
	public void setCostoPorKM(Double costoPorKM) {
		this.costoPorKM = costoPorKM;
	}
	public Double getCostoPorHora() {
		return costoPorHora;
	}
	public void setCostoPorHora(Double costoPorHora) {
		this.costoPorHora = costoPorHora;
	}
	public String getKmRecorridos() {
		return kmRecorridos;
	}
	public void setKmRecorridos(String kmRecorridos) {
		this.kmRecorridos = kmRecorridos;
	}
	private String patente;
	private String marca;
	private String modelo;
	private Double costoPorKM;
	private Double costoPorHora;
	private String kmRecorridos;
	private LocalDate fechaCompra;
	private Planta plantaPerteneciente;
	

	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Double getCostoKM() {
		return costoPorKM;
	}
	public void setCostoKM(Double costoKM) {
		this.costoPorKM = costoKM;
	}
	public Double getCostoHora() {
		return costoPorHora;
	}
	public void setCostoHora(Double costoHora) {
		this.costoPorHora = costoHora;
	}
	public String getKm() {
		return kmRecorridos;
	}
	public void setKm(String string) {
		this.kmRecorridos = string;
	}
	public LocalDate getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	@Override
	public String toString() {
		return " patente=" + patente + "; marca=" + marca + "; modelo=" + modelo + "]";
	}
	public Planta getPlanta() {
		return plantaPerteneciente;
	}
	
	public void setPlanta(Planta p) {
		plantaPerteneciente = p;
	}
	
	public int getKmRecorridosA() {
		String ks = kmRecorridos;

		if(ks.equals("0 - 9.999")) {
			return 9999;
		}
		if(ks.equals("10.000 - 19.999")) {
			return 19999;
		}
		if(ks.equals("20.000 - 29.999")) {
			return 29999;
		}
		if(ks.equals("30.000 - 39.999")) {
			return 39999;
		}
		if(ks.equals("40.000 - 49.999")) {
			return 49999;
		}
		
		if(ks.equals("50.000 - 59.999")) {
			return 59999;
		}
		if(ks.equals("60.000 - 69.999")) {
			return 69999;
		}
		if(ks.equals("70.000 - 79.999")) {
			return 79999;
		}
		if(ks.equals("80.000 - 89.999")) {
			return 89999;
		}
		if(ks.equals("90.000 - 99.999")) {
			return 99999;
		}
		if(ks.equals("100.00 - 109.999")) {
			return 109000;
		}
		if(ks.equals("120.000 - 129.999")) {
			return 129999;
		}
		if(ks.equals("130.000 - 139.999")) {
			return 139999;
		}
		if(ks.equals("140.000 - 149.999")) {
			return 149999;
		}
		if(ks.equals("150.000 - 159.999")) {
			return 159999;
		}
		if(ks.equals("160.000 - 169.999")) {
			return 169999;
		}
		if(ks.equals("170.000 - 179.999")) {
			return 179999;
		}
		if(ks.equals("Más de 300.000 km")){
			return 300000;
		}
		return Integer.valueOf(kmRecorridos);
	}
	
	
}
