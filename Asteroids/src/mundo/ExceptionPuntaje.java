package mundo;

/**
 * La excepcion ExceptionPuntaje modela la excepcion cuando se realiza una
 * busqueda o consulta sobre los puntajes y estos no existen
 * 
 */

public class ExceptionPuntaje extends Exception {

	public ExceptionPuntaje(String mensaje) {
		super(mensaje);
	}
}
