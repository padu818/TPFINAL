package tpdied2020.gui.util;

public class ControllerException extends Exception {
	
	
	private static final long serialVersionUID = 1L;

	public ControllerException() {
		super("Error actualizando el modelo");
	}

	public ControllerException(String msg) {
		super(msg);
	}

	
}
