package mundo;

/**
 * La clase Tiempo se encarga de modelar las caracteristicas y comportamientos de la entidad tiempo dentro del puntaje
 */
import java.io.Serializable;

public class Tiempo implements Serializable {

	private static final long serialVersionUID = 1L;

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Caracteristica que representa los milisegundos de juego
	 */
	private int milisegundos;

	/**
	 * Caracteristica que representa los segundos de juego
	 */
	private int segundos;

	/**
	 * Caracteristica que representa los minutos de juego
	 */
	private int minutos;

	/**
	 * Caracteristica que representa las horas de juego
	 */
	private int horas;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Constructor de la clase tiempo
	 */
	public Tiempo() {
		milisegundos = 00;
		segundos = 00;
		minutos = 00;
		horas = 00;
	}

	/**
	 * Constructor de la clase tiempo
	 * 
	 * @param milisegundos
	 * @param segundos
	 * @param minutos
	 * @param horas
	 */
	public Tiempo(int milisegundos, int segundos, int minutos, int horas) {
		super();
		this.milisegundos = milisegundos;
		this.segundos = segundos;
		this.minutos = minutos;
		this.horas = horas;
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Método que controla el avance del tiempo dentro de la partida
	 */
	public void correrTiempo() {

		milisegundos++;
		if (milisegundos > 99) {
			milisegundos = 0;
			segundos++;
		}
		if (segundos > 59) {
			segundos = 0;
			minutos++;
		}
		if (minutos > 59) {
			minutos = 0;
			horas++;
		}
	}

	/**
	 * Método que permite comparar dos tiempos
	 * 
	 * @param tm
	 *            Es el tiempo con el cual se comparará
	 * @return Un int indicando cual de los tiempos es mayor
	 */
	public int compareTo(Tiempo tm) {

		int com = 0;

		if (horas == tm.getHoras()) {
			if (minutos == tm.getMinutos()) {
				if (segundos == tm.getSegundos()) {
					if (milisegundos == tm.getMilisegundos()) {
						com = 0;
					} else if (milisegundos > tm.getMilisegundos()) {
						com = 1;
					} else {
						com = -1;
					}
				} else if (segundos > tm.getSegundos()) {
					com = 1;
				} else {
					com = -1;
				}
			} else if (minutos > tm.getMinutos()) {
				com = 1;
			} else {
				com = -1;
			}
		} else if (horas > tm.getHoras()) {
			com = 1;
		} else {
			com = -1;
		}
		return com;

	}

	/**
	 * Método que retorna los milisegundos del tiempo
	 * 
	 * @return milisegundos
	 */
	public int getMilisegundos() {
		return milisegundos;
	}

	/**
	 * Método que retorna los segundos del tiempo
	 * 
	 * @return segundos
	 */
	public int getSegundos() {
		return segundos;
	}

	/**
	 * Método que retorna los minutos del tiempo
	 * 
	 * @return minutos
	 */
	public int getMinutos() {
		return minutos;
	}

	/**
	 * Método que retorna las horas del tiempo
	 * 
	 * @return horas
	 */
	public int getHoras() {
		return horas;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return horas + ":" + minutos + ":" + segundos + ":" + milisegundos;
	}
}
