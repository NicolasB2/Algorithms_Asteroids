package mundo;

/**
 * La clase Meteorito modela las características y comportamientos de la entidad que
 * representa al meteorito
 * 
 */

public class Meteorito extends ObjetosVolador implements Danio {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Constante que representa el tamano maximo del meteorito
	 */
	public final static int MAX_TAMANIO = 100;

	/**
	 * Constante que representa el tamano minimo del meteorito
	 */
	public final static int MIN_TAMANIO = 50;

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Esta caracteristica representa el daño del meteorito
	 */
	private int danio;

	/**
	 * Esta caracteristica representa el tiempo de espera entre el movimiento
	 * del meteorito
	 */
	private int espera;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Constructor de la clase meteorito
	 * 
	 * @param posX
	 *            Es la posicion inicial en el eje x del meteorito
	 * @param posY
	 *            Es la posicion inicial en el eje y del meteorito
	 * @param espera
	 * @param ruta
	 *            Es la ruta de la imagen del meteorito
	 * @param direccion
	 *            Es la direccion inicial del meteorito
	 */
	public Meteorito(int posX, int posY, int espera, String ruta, String direccion) {
		super(posX, posY, 0, 0, ruta);
		this.espera = espera;

		this.setAncho((int) Math.floor(Math.random() * (MAX_TAMANIO - MIN_TAMANIO + 1) + MIN_TAMANIO));
		this.setAlto((int) Math.floor(Math.random() * (MAX_TAMANIO - MIN_TAMANIO + 1) + MIN_TAMANIO));
		setDireccion(direccion);

		calcularVida();
		calcularDaño();
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Método que permite alterar el movimiento del meteorito
	 */
	@Override
	public void mover() {

		if (!this.verificarExistencia()) {
			calcularDireccion();
		}

		super.mover();
		switch (getDireccion()) {
		case IZQUIERDA:
			setPosX(getPosX() - 1);
			// posX--;
			break;
		case DERECHA:
			setPosX(getPosX() + 1);
			// posX++;
			break;
		case ARRIBA:
			setPosY(getPosY() - 1);
			// posY--;
			break;
		case ABAJO:
			setPosY(getPosY() + 1);
			// posY++;
			break;
		}
	}

	/**
	 * Método que permite modificar la dirección del meteorito cuando este cruza
	 * los limites de la pantalla
	 */
	public void calcularDireccion() {
		if (getPosX() > Juego.ANCHO_PANTALLA + getAncho() && getPosY() <= Juego.ALTO_PANTALLA && getPosY() > 0) {
			setDireccion(ObjetosVolador.IZQUIERDA);
		} else if (getPosX() < 0 - getAncho() && getPosY() <= Juego.ALTO_PANTALLA && getPosY() > 0) {
			setDireccion(ObjetosVolador.DERECHA);
		} else if (getPosX() > 0 && getPosX() < Juego.ANCHO_PANTALLA && getPosY() < 0 - getAlto()) {
			setDireccion(ObjetosVolador.ABAJO);
		} else if (getPosX() > 0 && getPosX() < Juego.ANCHO_PANTALLA && getPosY() > Juego.ALTO_PANTALLA) {
			setDireccion(ObjetosVolador.ARRIBA);
		}
	}

	/**
	 * Método que calcula la vida del meteorito
	 */
	@Override
	public void calcularVida() {
		setVida(getAncho() * getAlto() / FACTOR_VIDA);
	}

	/**
	 * Método que calcula el dano del meteorito
	 */
	@Override
	public void calcularDaño() {
		danio = getAncho() * getAlto() / FACTOR_DANIO;
	}

	/**
	 * Método que retorna el dano del meteorito
	 * 
	 * @return
	 */
	public int getDanio() {
		return danio;
	}

	/**
	 * Método que retorna la espera del meteorito
	 * 
	 * @return
	 */
	public int getEspera() {
		return espera;
	}

	/**
	 * Método que permite disminuir la vida del meteorito
	 */
	@Override
	public void perderVida(int danio) {
		this.setVida(this.getVida() - danio);

		if (this.getVida() <= 0) {
			setRutaImagen(RUTA_EXPLOCION);
		}
	}

}
