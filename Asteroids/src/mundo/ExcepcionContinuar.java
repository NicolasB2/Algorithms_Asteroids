package mundo;

/**
 * La excepcion ExcepcionContinuar modela la excepcion cuando se desea cargar
 * una partida, pero no ha sido guardada previamente
 * 
 */
public class ExcepcionContinuar extends Exception {
	public ExcepcionContinuar(String mensaje) {
		super(mensaje);
	}
}
