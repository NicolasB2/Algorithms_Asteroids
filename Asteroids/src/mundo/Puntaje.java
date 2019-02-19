package mundo;

/**
 * La clase Puntaje modela las características y comportamientos de la entidad que
 * representa el puntaje dentro del juego
 * 
 */
import java.io.Serializable;
import java.util.ArrayList;

public class Puntaje implements Serializable {
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Caracteristica que representa el tiempo en el cual el puntaje ha sido
	 * obtenido
	 */
	private Tiempo tiempo;

	/**
	 * Caracteristica que representa el nombre del jugador que obtuvo el puntaje
	 */
	private String nombre;

	/**
	 * Referencia hacie el puntaje hijo de la derecha
	 */
	private Puntaje der;

	/**
	 * Referencia hacia el puntaje hijo de la izqueirda
	 */
	private Puntaje izq;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Constructor de la clase puntaje
	 * 
	 * @param tiempo
	 *            Tiempo en el que fue obtenido el puntaje
	 * @param nombre
	 *            Nombre del jugador que obtuvo el puntaje
	 */
	public Puntaje(Tiempo tiempo, String nombre) {
		super();
		this.tiempo = tiempo;
		this.nombre = nombre;
	}

	/**
	 * 
	 * @param listado
	 */
	public void inorden(ArrayList<Puntaje> listado) {

		if (izq != null)
			izq.inorden(listado);

		listado.add(this);

		if (der != null)
			der.inorden(listado);

	}

	/**
	 * Método que permite agregar un nuevo puntaje al arbol binario
	 * 
	 * @param pt
	 */
	public void agragarPuntaje(Puntaje pt) {
		if (tiempo.compareTo(pt.getTiempo()) > 0) {
			if (izq == null) {
				izq = pt;
			} else {
				izq.agragarPuntaje(pt);
			}
		} else {
			if (der == null) {
				der = pt;
			} else {
				der.agragarPuntaje(pt);
			}
		}
	}

	/**
	 * Método que permite comparar el puntaje actual con el del parametro
	 * 
	 * @param p
	 *            Es el puntaje con el cual se comparará
	 * @return Un int indicando a quien era mayor
	 */
	public int compararPorNombre(Puntaje p) {
		
		if(p==null){
			return 1;
		}
		int valor = nombre.compareToIgnoreCase(p.getNombre());

		if (valor > 0) {
			valor = 1;
		} else if (valor < 0) {
			valor = -1;
		}

		return valor;
	}

	/**
	 * Método que permite comprar el puntaje actual con el del parametro segun
	 * los nombres de los jugadores
	 * 
	 * @param n
	 *            Es el nombre del jugador con el cual se compara
	 * @return un int indicando quien es mayor
	 */
	public int compararPorNombre(String n) {
		int valor = nombre.compareToIgnoreCase(n);

		if (valor > 0) {
			valor = 1;
		} else if (valor < 0) {
			valor = -1;
		}

		return valor;
	}

	/**
	 * Método que retorna el tiempo del puntaje
	 * 
	 * @return tiempo
	 */
	public Tiempo getTiempo() {
		return tiempo;
	}

	/**
	 * Método que permite modificar el tiempo del puntaje actual por el del
	 * parametro
	 * 
	 * @param tiempo
	 */
	public void setTiempo(Tiempo tiempo) {
		this.tiempo = tiempo;
	}

	/**
	 * Método que retorna el nombre del usuario del puntaje
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método que permite modificar el nombre del jugador del puntaje por el
	 * nombre del parametro
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Método que retorna una referencia al puntaje hijo de la derecha
	 * 
	 * @return der
	 */
	public Puntaje getDer() {
		return der;
	}

	/**
	 * Método que permite modificar la referencia al puntaje hijo de la derecha
	 * 
	 * @param der
	 */
	public void setDer(Puntaje der) {
		this.der = der;
	}

	/**
	 * Método que retorna una referencia al puntaje hijo de la izquierda
	 * 
	 * @return izq
	 */
	public Puntaje getIzq() {
		return izq;
	}

	/**
	 * Método que permite modificar la referencia al puntaje hijo de la
	 * izquierda
	 * 
	 * @param izq
	 */
	public void setIzq(Puntaje izq) {
		this.izq = izq;
	}

	@Override
	public String toString() {
		return nombre + "  " + tiempo.toString() + "\n";
	}

}
