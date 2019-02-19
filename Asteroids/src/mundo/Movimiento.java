package mundo;

/**
 * Interface que permite calcular el moviemiento de las clases que la
 * implementan
 */
public interface Movimiento {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Constante que representa el punto cardinal norte
	 */
	public final static String ARRIBA = "Arriba";

	/**
	 * Constante que representa el punto cardinal sur
	 */
	public final static String ABAJO = "Abajo";

	/**
	 * Constante que representa el punto cardinal oeste
	 */
	public final static String IZQUIERDA = "Izquierda";

	/**
	 * Constante que representa el punto cardinal este
	 */
	public final static String DERECHA = "Derecha";

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------
	
	/**
	 * Método que permite calcular el movimiento de la clase que implemente esta interface
	 */
	public void mover();
}
