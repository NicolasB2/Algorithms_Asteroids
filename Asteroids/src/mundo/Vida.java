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
	// M�todos
	// -----------------------------------------------------------------
	
	/**
	 * M�todo que permite calcular la vida de las clases que implementan la
	 * interface
	 */
	public void calcularVida();

	/**
	 * M�todo que permite disminuir la vida de las clases que implementan la
	 * interface
	 * 
	 * @param da�o
	 */
	public void perderVida(int da�o);
}
