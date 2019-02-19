package mundo;

/**
 * Interface que permite calcular la vida de las clases que la implementan
 */
public interface Vida {
	
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------
	
	/**
	 * Constante que permite calcular la vida
	 */
	public final static int FACTOR_VIDA = 1000;

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------
	
	/**
	 * Método que permite calcular la vida de las clases que implementan la
	 * interface
	 */
	public void calcularVida();

	/**
	 * Método que permite disminuir la vida de las clases que implementan la
	 * interface
	 * 
	 * @param daño
	 */
	public void perderVida(int daño);
}
