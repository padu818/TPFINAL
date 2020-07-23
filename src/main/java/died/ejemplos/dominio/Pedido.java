package died.ejemplos.dominio;

import java.time.LocalDateTime;
import java.util.List;



public class Pedido {
	/*
	 * 
	 */
	private Integer idPedido; //unique y secuencial, comenzando por el 1
	private Planta destino;
	private LocalDateTime fechaSolicitud;
	private LocalDateTime fechaEntrega;
	private EstadoPedido estado;
	private List<Insumo> items; //detalles d elos items, una descripcion de los item o whts
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
	public LocalDateTime getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public LocalDateTime getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(LocalDateTime fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public EstadoPedido getEstado() {
		return estado;
	}
	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}
	public List<Insumo> getItems() {
		return items;
	}
	public void setItems(List<Insumo> items) {
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
