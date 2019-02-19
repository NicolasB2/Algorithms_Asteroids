package mundo;

/**
 * Interface que permite calcular el dano de las clases que la implementan
 */
public interface Danio {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Constante que permite calcular el dano
	 */
	public final static int FACTOR_DANIO = 1700;

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Método que permite calcular el dano
	 */
	public void calcularDaño();
}
