package mundo;

/**
 * La clase Nave modela las características y comportamientos de la entidad que
 * representa la nave
 * 
 */
public class Nave extends ObjetosVolador implements Vida {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Esta caracteristica representa la velocidad de movimiento de la nave
	 */
	private int velocidadMovimiento;

	/**
	 * Esta caracteristica representa la relacion con la clase Disparo
	 */
	private Disparo disparo;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Método constructor de la clase Nave
	 * 
	 * @param posX
	 *            Es la posicion inicial en eje X de la nave
	 * @param posY
	 *            Es la posicion inicial en eje y de la nave
	 * @param ruta
	 *            Es la ruta inicial de la imagen de la nave
	 * @param velocidadMovimiento
	 */
	public Nave(int posX, int posY, String ruta, int velocidadMovimiento) {
		super(posX, posY, 120, 90, ruta);
		this.velocidadMovimiento = velocidadMovimiento;
		setDireccion(Movimiento.IZQUIERDA);
		calcularVida();
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Método que retorna la velocidad de movimiento de la nave
	 * 
	 * @return
	 */
	public int getVelocidadMov() {
		return velocidadMovimiento;
	}

	/**
	 * Método que retorna el disparo actual de la nave
	 * 
	 * @return El disparo actual de la nave
	 */
	public Disparo getDisparo() {
		return disparo;
	}

	/**
	 * Método que permite alterar el movimiento de la nave
	 */
	@Override
	public void mover() {
		movLimite();
		super.mover();
		switch (this.getDireccion()) {
		case IZQUIERDA:
			this.setPosX(this.getPosX() - getVelocidadMov());
			break;
		case DERECHA:
			this.setPosX(this.getPosX() + getVelocidadMov());
			break;
		case ARRIBA:
			this.setPosY(this.getPosY() - getVelocidadMov());
			break;
		case ABAJO:
			this.setPosY(this.getPosY() + getVelocidadMov());
			break;
		}
	}

	/**
	 * Método que permite limitar el movimiento de la nave
	 */
	public void movLimite() {
		if (getPosY() < 0) {
			setDireccion(ABAJO);
		} else if (getPosY() > Juego.ALTO_PANTALLA - getAlto()) {
			setDireccion(ARRIBA);
		} else if (getPosX() < 0) {
			setDireccion(DERECHA);
		} else if (getPosX() > Juego.ANCHO_PANTALLA - getAlto()) {
			setDireccion(IZQUIERDA);
		}
	}

	/**
	 * Método que permite a la nave disparar
	 * 
	 * @param dis
	 *            Es el nuevo disparod e su arsenal
	 */
	public void disparar(Disparo dis) {

		if (disparo == null) {
			disparo = dis;
		} else {
			disparo.agregarDisparo(dis);
		}
	}

	/**
	 * Método que permite desconectar el disparo actual de la nave
	 */
	public void desconectarDisparo() {
		if (disparo.isActivo() == false) {
			disparo = disparo.getSiguiente();
		}
	}

	/**
	 * Método que reinicia los disparos de la nave
	 */
	public void reiniciarDisparo() {
		disparo = null;
	}

	/**
	 * Método que permite calcular la vida de la nave
	 */
	@Override
	public void calcularVida() {
		setVida(FACTOR_VIDA / (velocidadMovimiento * 10));

	}

	/**
	 * Método que permite a la nave perder vida danio Es la vida que perdera la
	 * nave
	 */
	@Override
	public void perderVida(int danio) {
		setVida(getVida() - danio);
		if(getVida()<=0){
			setRutaImagen(RUTA_EXPLOCION);
		}
	}

}
