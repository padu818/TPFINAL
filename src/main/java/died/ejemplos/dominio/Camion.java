package died.ejemplos.dominio;

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
	public Integer getKmRecorridos() {
		return kmRecorridos;
	}
	public void setKmRecorridos(Integer kmRecorridos) {
		this.kmRecorridos = kmRecorridos;
	}
	private String patente;
	private String marca;
	private String modelo;
	private Double costoPorKM;
	private Double costoPorHora;
	private Integer kmRecorridos;
	private LocalDate fechaCompra;
	

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
	public Integer getKm() {
		return kmRecorridos;
	}
	public void setKm(Integer km) {
		this.kmRecorridos = km;
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
	
	
}
