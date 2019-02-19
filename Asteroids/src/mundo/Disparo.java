package mundo;

/**
 * La clase Disparo modela las caracter�sticas y comportamientos de la entidad
 * que representa al disparo
 * 
 */

public class Disparo implements Danio, Movimiento {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Esta caracteristica representa la posicion en el eje X del plano
	 * cartesiano
	 */
	private int posX;

	/**
	 * Esta caracteristica representa la posicion en el eje Y del plano
	 * cartesiano
	 */
	private int posY;

	/**
	 * Esta caracteristica representa la velocidad de movimiento del disparo
	 */
	private int espera;

	/**
	 * Esta caracteristica representa el alcance maximo del disparo
	 */
	private int alcance;

	/**
	 * Esta caracteristica representa el estado actual del disparo
	 */
	private boolean activo;

	/**
	 * Esta caracteristica representa la direccion en la cual se encuentra el
	 * disparo
	 */
	private String direccion;

	/**
	 * Esta caracteristica representa el dano que hace el disparo sobre otros
	 * objetos
	 */
	private int danio;

	/**
	 * Esta caracteristica es una referencia a la ruta de la imagen del disparo
	 */
	private String ruta;

	/**
	 * Esta caracteristica representa el siguiente disparo
	 */
	private Disparo siguiente;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * M�todo constructor de la clase disparo
	 * 
	 * @param posX
	 *            Es la posicion en el eje X del disparo
	 * @param posY
	 *            Es la posicion en el eje X del disparo
	 * @param direccion
	 *            Es la direccion en la cual se encuentra orientado el disparo
	 */
	public Disparo(int posX, int posY, String direccion) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.direccion = direccion;
		activo = true;
		alcance = 400;
		calcularDa�o();
		ruta = "/img/Dis/disparoArriba.png";
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * M�todo que retorna la posicion en el eje x del disparo
	 * 
	 * @return
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * M�todo que permite modificar la posicion en el eje x del disparo por el
	 * del parametro
	 * 
	 * @param posX
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * M�todo que retorna la posicion en el eje y del disparo
	 * 
	 * @return posY
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * M�todo que permite modificar la posicion en el eje y del disparo por el
	 * del parametro
	 * 
	 * @param posY
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * M�todo que retorna el tiempo de espera del disparo
	 * 
	 * @return espera
	 */
	public int getEspera() {
		return espera;
	}

	/**
	 * M�todo que permite modificar el tiempo de espera del disparo por el del
	 * parametro
	 * 
	 * @param espera
	 */
	public void setEspera(int espera) {
		this.espera = espera;
	}

	/**
	 * M�todo que retorna el alcance maximo del disparo
	 * 
	 * @return alcance
	 */
	public int getAlcance() {
		return alcance;
	}

	/**
	 * M�todo que permite modificar el alcance actual del disparo por el del
	 * parametro
	 * 
	 * @param alcance
	 */
	public void setAlcance(int alcance) {
		this.alcance = alcance;
	}

	/**
	 * M�todo que retorna el estado actual del disparo
	 * 
	 * @return
	 */
	public boolean isActivo() {
		return activo;
	}

	/**
	 * M�todo que permite modificar el estado del disparo por el del parametro
	 * 
	 * @param activo
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	/**
	 * M�todo que retorna la direccion actual del disparo
	 * 
	 * @return direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * M�todo que permite modificar la direccion actual del disparo por la del
	 * parametro
	 * 
	 * @param direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * M�todo que retorna el dano del disparo
	 * 
	 * @return
	 */
	public int getDanio() {
		return danio;
	}

	/**
	 * M�todo que calcula el dano del meteorito
	 */
	@Override
	public void calcularDa�o() {
		danio = FACTOR_DANIO / (alcance * 2);
	}

	/**
	 * M�todo que permite modificar el dano del meteorito por el del parametro
	 * 
	 * @param danio
	 */
	public void setDanio(int danio) {
		this.danio = danio;
	}

	/**
	 * M�todo que retorna la ruta de la imagen del meteorito
	 * 
	 * @return ruta
	 */
	public String getRuta() {
		return ruta;
	}

	/**
	 * M�todo que permite modificar la ruta de la imagen del disparo
	 * 
	 * @param ruta
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	/**
	 * M�todo que retorna el siguiente disparo
	 * 
	 * @return
	 */
	public Disparo getSiguiente() {
		return siguiente;
	}

	/**
	 * M�todo que permite modificar el siguiente disparo por el del parametro
	 * 
	 * @param siguiente
	 */
	public void setSiguiente(Disparo siguiente) {
		this.siguiente = siguiente;
	}

	/**
	 * M�todo que permite agregar un nuevo disparo al listado de disparos
	 * 
	 * @param dis
	 */
	public void agregarDisparo(Disparo dis) {
		if (siguiente == null) {
			siguiente = dis;
		} else {
			siguiente.agregarDisparo(dis);
		}
	}

	/**
	 * M�todo que permite validar el contacto entre el disparo y los distintos
	 * objetos que conforman el juego
	 * 
	 * @param mts
	 * @return
	 */
	public boolean choque(ObjetosVolador[] mts) {

		boolean c = false;
		for (int i = 0; i < mts.length; i++) {

			if (mts[i] != null) {
				ObjetosVolador m = mts[i];
				int x = getPosX();
				int y = getPosY();

				boolean interseccionY = (y > m.getPosY() && y < m.getPosY() + m.getAlto());
				boolean interseccionX = (x > m.getPosX() && x < m.getPosX() + m.getAncho());

				if (interseccionY && interseccionX) {
					mts[i].perderVida(danio);
					activo = false;
					c = true;
				}

			}
		}
		return c;
	}

	/**
	 * M�todo que permite modificar la ruta de la imagen dependiendo de la
	 * direccion en la cual se encuentre el disparo
	 */
	@Override
	public void mover() {
		switch (direccion) {
		case IZQUIERDA:
			posX--;
			ruta = "./img/Dis/disparoIZQUIERDA.png";
			break;
		case DERECHA:
			posX++;
			ruta = "./img/Dis/disparoDERECHA.png";
			break;
		case ARRIBA:
			posY--;
			ruta = "./img/Dis/disparoARRIBA.png";
			break;
		case ABAJO:
			posY++;
			ruta = "./img/Dis/disparoABAJO.png";
			break;
		}
		alcance--;

		if (alcance <= 0) {
			activo = false;
		}
	}

}
