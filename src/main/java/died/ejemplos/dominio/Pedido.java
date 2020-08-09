package died.ejemplos.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;



public class Pedido {
	/*
	 * 
	 */
	private Integer idPedido; //unique y secuencial, comenzando por el 1
	private Planta destino;
	private Planta origen;
	private LocalDate fechaSolicitud;
	private LocalDate fechaEntrega;
	private EstadoPedido estado;
	private List<DetallesInsumoSolicitado> items; //detalles d elos items, una descripcion de los item o whts
	private Camion camionAsignado;
	private Ruta rutaAsignado;
	private Double costoEnvio;
	
	
	
	
	
	
	
	
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public Planta getDestino() {
		return destino;
	}
	public void setDestino(Planta destino) {
		this.destino = destino;
	}
	
	public Planta getOrigen() {
		return destino;
	}
	public void setOrigen(Planta destino) {
		this.origen = destino;
	}
	
	public LocalDate getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(LocalDate localDate) {
		this.fechaSolicitud = localDate;
	}
	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public EstadoPedido getEstado() {
		return estado;
	}
	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}
	public List<DetallesInsumoSolicitado> getItems() {
		return items;
	}
	public void setItems(List<DetallesInsumoSolicitado> items) {
		this.items = items;
	}
	public Camion getCamionAsignado() {
		return camionAsignado;
	}
	public void setCamionAsignado(Camion camionAsignado) {
		this.camionAsignado = camionAsignado;
	}
	public Ruta getRutaAsignado() {
		return rutaAsignado;
	}
	public void setRutaAsignado(Ruta rutaAsignado) {
		this.rutaAsignado = rutaAsignado;
	}
	public double getCostoEnvio() {
		return costoEnvio;
	}
	public void setCostoEnvio(double costoEnvio) {
		this.costoEnvio = costoEnvio;
	}
	
}
