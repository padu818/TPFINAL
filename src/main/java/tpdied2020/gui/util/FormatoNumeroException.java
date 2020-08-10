package tpdied2020.gui.util;

public class FormatoNumeroException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String nombreCampo;
	private String mensaje;
	
	public FormatoNumeroException(String nc,String msg) {
		super("Error en el campo: "+nc+ " El dato debe ser numerico");
		this.nombreCampo = nc;
		this.mensaje= msg;
	}

	public String getNombreCampo() {
		return nombreCampo;
	}

	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
