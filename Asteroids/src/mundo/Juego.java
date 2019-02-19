package mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * La clase Juego modela las características y comportamientos de la entidad que
 * representa al juego
 * 
 */
public class Juego {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Constante que representa el maximo ancho de la pantalla
	 */
	public final static int ANCHO_PANTALLA = 1300;

	/**
	 * Constante que representa el maximo alto de la pantalla
	 */
	public final static int ALTO_PANTALLA = 700;

	/**
	 * Constante que representa cuando el estado del juego esta activo
	 */
	public final static String ACTIVO = "ACTIVO";

	/**
	 * Constante que representa cuando el estado del juego cuando ha sido
	 * completado
	 */
	public final static String COMPLETADO = "COMPLETADO";

	/**
	 * Constante que representa cuando el estado del juego esta en pausa
	 */
	public final static String PAUSA = "PAUSA";

	/**
	 * Constante que representa cuando el estado del juego cuando ha sido
	 * perdido
	 */
	public final static String DERROTA = "DERROTA";

	/**
	 * Constante que referencia la ruta del juego facil
	 */
	public final static String RUTA_JUEGO_FACIL = "./docs/nivel1.txt";

	/**
	 * Constante que referencia la ruta del juego dificil
	 */
	public final static String RUTA_JUEGO_DIFICL = "./docs/nivel2.txt";

	/**
	 * Constante que referencia la ruta del juego guardado
	 */
	public final static String RUTA_JUEGO_GUARDADO = "./docs/partida.txt";

	/**
	 * Constante que referencia la ruta del archivo de los puntajes del juego
	 * facil
	 */
	public final static String RUTA_PUNTAJE_FACIL = "./docs/pntFacil.txt";

	/**
	 * Constante que referencia la ruta del archivo de los puntajes del juego
	 * dificil
	 */
	public final static String RUTA_PUNTAJE_DIFICIL = "./docs/pntDificil.txt";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Relacion con la clase Nave
	 */
	private Nave principal;

	/**
	 * Contenedora de tamaño fijo que almacena los meteoritos
	 */
	private Meteorito[] meteoritos;

	/**
	 * Contenedora de tamaño fijo que almacena los enemigos
	 */
	private Nave[] enemigos;

	/**
	 * Esta caracteristica representa el tiempo de juego de la partida
	 */
	private Tiempo time;

	/**
	 * Esta caracteristica representa el nivel de dificultad del juego
	 */
	private int nivel;

	/**
	 * Esta caracteristica representa el estado en el que se encuentra la
	 * partida actual
	 */
	private String estado;

	/**
	 * Esta caracteristica representa la cantidad de meteoritos del juego
	 */
	private int numMeteoritos;

	/**
	 * Esta caracteristica representa la cantidad de enemigos del juego
	 */
	private int numEnemigos;

	/**
	 * Puntaje para la dificultad facil
	 */
	private Puntaje puntajeFacil;

	/**
	 * Puntaje para la dificultad dificil
	 */
	private Puntaje puntajeDificil;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Método constructor de la clase Juego
	 */
	public Juego() {
		principal = new Nave(ANCHO_PANTALLA / 2, ALTO_PANTALLA / 2, "./img/Nave/nave2", 20);
		estado = PAUSA;
		time = new Tiempo();
		numMeteoritos = 0;
		numEnemigos = 0;
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Guarda los puntajes del juego
	 */
	public void guardarPuntaje() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_PUNTAJE_FACIL));
			oos.writeObject(puntajeFacil);
			oos.close();

			oos = new ObjectOutputStream(new FileOutputStream(RUTA_PUNTAJE_DIFICIL));
			oos.writeObject(puntajeDificil);
			oos.close();
		} catch (IOException e) {
		}
	}

	/**
	 * Permite cargar los puntajes al Juego
	 */
	public void cargarPuntaje() {
		File archivo = new File(RUTA_PUNTAJE_FACIL);
		if (archivo.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
				puntajeFacil = (Puntaje) ois.readObject();
				getPuntajeFacil();
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		archivo = new File(RUTA_PUNTAJE_DIFICIL);
		if (archivo.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
				puntajeDificil = (Puntaje) ois.readObject();
				ois.close();
			} catch (Exception e) {
			}
		}

	}

	/**
	 * Método que permite agregar un nuevo puntaje
	 * 
	 * @param tiempo
	 *            Es el tiempo del nuevo puntaje
	 * @param nombre
	 *            Es el nombre del jugador del nuevo puntaje
	 */
	public void agregarPuntaje(Tiempo tiempo, String nombre) {

		Puntaje pt = new Puntaje(tiempo, nombre);

		if (nivel == 1) {
			if (puntajeFacil == null) {
				puntajeFacil = pt;
			} else
				puntajeFacil.agragarPuntaje(pt);
		} else if (nivel == 2) {
			if (puntajeDificil == null) {
				puntajeDificil = pt;
			} else
				puntajeDificil.agragarPuntaje(pt);
		}
	}

	/**
	 * Método que busca un puntaje
	 * 
	 * @param n
	 *            Es el nombre de la persona que consiguie el puntaje
	 * @param p
	 *            Es el nivel de dificultad sobre el cual se buscara el puntaje
	 * @return El puntaje correspondiente
	 * @throws ExceptionPuntaje
	 */
	public Puntaje buscarPuntaje(String n, Puntaje p) throws ExceptionPuntaje {

		ArrayList<Puntaje> puntajes = this.ordenarArbolPorNombre(p);

		int inicio = 0;
		int fin = puntajes.size() - 1;
		boolean encontro = false;
		Puntaje puntaje = null;

		while (inicio <= fin && !encontro) {
			int medio = (inicio + fin) / 2;

			if (puntajes.get(medio).compararPorNombre(n) == 0) {
				puntaje = puntajes.get(medio);
				encontro = true;
			} else if (puntajes.get(medio).compararPorNombre(n) == 1) {
				fin = medio - 1;
			} else if (puntajes.get(medio).compararPorNombre(n) == -1) {
				inicio = medio + 1;
			}

		}

		if (puntaje == null) {
			throw new ExceptionPuntaje("No existe un jugador bajo el nombre " + n);
		}

		return puntaje;

	}

	/**
	 * Método que permite cargar un nuevo archivo
	 * 
	 * @param archivo
	 *            Es el archivo a cargar
	 * @throws ExcepcionContinuar
	 */
	public void cargarArchivo(File archivo) throws ExcepcionContinuar {

		String linea = "";
		String[] valores = null;
		String[] valoresEnemigos = null;

		principal = new Nave(ANCHO_PANTALLA / 2, ALTO_PANTALLA / 2, "./img/Nave/nave2", 20);
		if (archivo.exists()) {

			try {
				BufferedReader br = new BufferedReader(new FileReader(archivo));

				linea = br.readLine();
				time = new Tiempo(0, 0, 0, 0);
				if (linea.isEmpty() == false && linea != null) {

					if (linea.equals("#nivel")) {
						linea = br.readLine();
						nivel = Integer.parseInt(linea);
						linea = br.readLine();
					}

					if (linea.equals("#principal: PosX PosY Vida")) {
						linea = br.readLine();
						valoresEnemigos = linea.split("\t");
						principal.setPosX(Integer.parseInt(valoresEnemigos[0]));
						principal.setPosY(Integer.parseInt(valoresEnemigos[1]));
						principal.setVida(Integer.parseInt(valoresEnemigos[2]));
						linea = br.readLine();
					}

					if (linea.equals("#tiempo")) {
						linea = br.readLine();
						valores = linea.split("\t");

						time = new Tiempo(Integer.parseInt(valores[0]), Integer.parseInt(valores[1]),
								Integer.parseInt(valores[2]), Integer.parseInt(valores[3]));
						linea = br.readLine();
					}

					if (linea.equals("#cantidadMeteoritos")) {
						linea = br.readLine();
						meteoritos = new Meteorito[Integer.parseInt(linea)];
						numMeteoritos = Integer.parseInt(linea);
						linea = br.readLine();
					}

					if (linea.equals("#PosX PosY Espera Ruta Direccion")) {
						linea = br.readLine();
						for (int i = 0; i < meteoritos.length; i++) {
							valores = linea.split("\t");

							meteoritos[i] = new Meteorito(Integer.parseInt(valores[0]), Integer.parseInt(valores[1]),
									Integer.parseInt(valores[2]), valores[3], valores[4]);

							linea = br.readLine();
						}
					}

					if (linea.equals("#cantidadEnemigos")) {
						linea = br.readLine();
						enemigos = new Nave[Integer.parseInt(linea)];
						numEnemigos = Integer.parseInt(linea);
						linea = br.readLine();
					} else {
						enemigos = null;
					}

					if (linea.equals("#PosX PosY Ruta velMovimiento")) {
						linea = br.readLine();
						for (int i = 0; i < enemigos.length; i++) {
							valoresEnemigos = linea.split("\t");
							enemigos[i] = new Nave(Integer.parseInt(valoresEnemigos[0]),
									Integer.parseInt(valoresEnemigos[1]), valoresEnemigos[2],
									Integer.parseInt(valoresEnemigos[3]));
							linea = br.readLine();
						}

					}
				}

				br.close();
				principal.reiniciarDisparo();
				estado = ACTIVO;
			} catch (NullPointerException e) {
				e.toString();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			throw new ExcepcionContinuar("No existe una partida guardada");
		}
	}

	/**
	 * Método que permite guardar es estado actual de la partida
	 */
	public void guardarArchivo() {

		File ruta = new File(RUTA_JUEGO_GUARDADO);

		try {
			PrintWriter pw = new PrintWriter(ruta);

			pw.write("#nivel\n" + nivel + "\n");
			pw.write("#principal: PosX PosY Vida\n");
			pw.write(principal.getPosX() + "\t" + principal.getPosY() + "\t" + principal.getVida() + "\n");
			pw.write("#tiempo\n");
			pw.write(time.getMilisegundos() + "\t" + time.getSegundos() + "\t" + time.getMinutos() + "\t"
					+ time.getHoras() + "\n");

			pw.write("#cantidadMeteoritos\n" + numMeteoritos + "\n");
			pw.write("#PosX PosY Espera Ruta Direccion\n");

			for (int i = 0; i < meteoritos.length; i++) {
				if (meteoritos[i] != null) {
					pw.write(meteoritos[i].getPosX() + "\t" + meteoritos[i].getPosY() + "\t" + meteoritos[i].getEspera()
							+ "\t" + meteoritos[i].getRutaImagen() + "\t" + meteoritos[i].getDireccion() + "\n");
				}
			}

			if (nivel == 2) {

				pw.write("#cantidadEnemigos\n");
				pw.write(numEnemigos + "\n");
				pw.write("#PosX PosY Ruta velMovimiento\n");

				for (int i = 0; i < enemigos.length; i++) {
					if (enemigos[i] != null) {
						pw.write(enemigos[i].getPosX() + "\t" + enemigos[i].getPosY() + "\t"
								+ enemigos[i].getRutaImagen() + "\t" + enemigos[i].getVelocidadMov() + "\n");
					}
				}

			}

			pw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Método que permite validar el choque entre los objetos voladores y una
	 * nave
	 * 
	 * @param nv
	 *            Es la nave con la cual se validara el choque
	 * @param m
	 *            Es el objeto volador con el cual se validara el choque
	 */
	public void choque(Nave nv, ObjetosVolador m) {
		int x = nv.getPosX();
		int x2 = x + 50;

		int y = nv.getPosY();
		int y2 = y + 80;

		boolean interseccionY = (y < m.getPosY() && y2 > m.getPosY() + m.getAlto())
				|| (m.getPosY() < y && m.getPosY() + m.getAlto() > y2)
				|| (y < m.getPosY() + m.getAlto() && y2 > m.getPosY() + m.getAlto())
				|| (m.getPosY() < y2 && m.getPosY() + m.getAlto() > y2) || (y < m.getPosY() && y2 > m.getPosY())
				|| (m.getPosY() < y && m.getPosY() + m.getAlto() > y);

		boolean interseccionX = (x < m.getPosX() && x2 > m.getPosX() + m.getAncho())
				|| (m.getPosX() < x && m.getPosX() + m.getAncho() > x2)
				|| (x < m.getPosX() + m.getAncho() && x2 > m.getPosX() + m.getAncho())
				|| (m.getPosX() < x2 && m.getPosX() + m.getAncho() > x2) || (x < m.getPosX() && x2 > m.getPosX())
				|| (m.getPosX() < x && m.getPosX() + m.getAncho() > x);

		if (interseccionY && interseccionX) {
			if (m.getVida() > 0) {
				nv.perderVida(1);
			}
			
			if (m instanceof Meteorito) {
				Meteorito name = (Meteorito) m;
				name.perderVida(name.getVida());
			}else if (m instanceof Nave) {
				Nave name = (Nave)m;
				name.perderVida(name.getVida());
			}
			
		}

	}

	/**
	 * Método que permite eliminar un meteorito
	 * 
	 * @param m
	 *            Es el meteorito a eliminar
	 */
	public void eliminarMeteorito(Meteorito m) {

		boolean b = false;
		for (int i = 0; i < meteoritos.length && !b; i++) {
			
				if (meteoritos[i]==(m)) {
					meteoritos[i] = null;
					b = true;
					numMeteoritos--;
				}
		}
	}

	/**
	 * Método que permite eliminar un enemigo
	 * 
	 * @param n
	 *            Es el enemigo a eliminar
	 */
	public void eliminarNave(Nave n) {
		boolean b = false;

		for (int i = 0; i < enemigos.length && !b; i++) {
			if (enemigos[i] != null) {
				if (enemigos[i]==(n)) {
					enemigos[i] = null;
					b = true;
					numEnemigos--;
				}
			}
		}
	}

	/**
	 * Método que verifica el final del juego
	 */
	public void fin() {
		if (principal.getVida() <= 0) {
			setEstado(DERROTA);
		} else if (numMeteoritos == 0 && principal.getVida() > 0) {
			if (nivel == 2 && numEnemigos == 0) {
				setEstado(COMPLETADO);
			} else if (nivel == 1)
				setEstado(COMPLETADO);
		}
	}

	/**
	 * Método que retorna un listado de puntajes ordenados segun su nombre
	 * 
	 * @param pt
	 *            Es una referencia al puntaje a ordenar
	 * @return Un listado de puntajes ordenados
	 * @throws ExceptionPuntaje
	 */
	public ArrayList<Puntaje> ordenarArbolPorNombre(Puntaje pt) throws ExceptionPuntaje {

		if (pt == null) {
			throw new ExceptionPuntaje("No existe un listado de highscores");
		}

		ArrayList<Puntaje> listado = new ArrayList<>();

		pt.inorden(listado);

		for (int i = listado.size(); i > 0; i--) {
			for (int j = 0; j < i - 1; j++) {

				if (listado.get(j).compararPorNombre(listado.get(j + 1)) == 1) {

					Puntaje temp = listado.get(j);
					listado.set(j, listado.get(j + 1));
					listado.set(j + 1, temp);

				}

			}

		}

		return listado;

	}

	/**
	 * Método que retorna la nave principal del juego
	 * 
	 * @return principal
	 */
	public Nave getPrincipal() {
		return principal;
	}

	/**
	 * Método que permite modificar la nave principal del juego por la pasada
	 * por parametro
	 * 
	 * @param principal
	 */
	public void setPrincipal(Nave principal) {
		this.principal = principal;
	}

	/**
	 * Método que retorna la contenedora de tamano fijo de los meteoritos
	 * 
	 * @return meteoritos
	 */
	public Meteorito[] getMeteoritos() {
		return meteoritos;
	}

	/**
	 * Método que permite modificar la contenedora de tamano fijo de los
	 * meteoritos por la contenedora del parametro
	 * 
	 * @param meteoritos
	 */
	public void setMeteoritos(Meteorito[] meteoritos) {
		this.meteoritos = meteoritos;
	}

	/**
	 * Método que retorna la contenedora de tamano fijo de los enemigos
	 * 
	 * @return enemigos
	 */
	public Nave[] getEnemigos() {
		return enemigos;
	}

	/**
	 * Método que permite modificar la contenedora de tamano fijo de los
	 * enemigos por la contenedora del parametro
	 * 
	 * @param enemigos
	 */
	public void setEnemigos(Nave[] enemigos) {
		this.enemigos = enemigos;
	}

	/**
	 * Método que retorna el nivel de dificultad del juego
	 * 
	 * @return nivel
	 */
	public int getNivel() {
		return nivel;
	}

	/**
	 * Método que permite modificar el nivel de dificultad del juego por el del
	 * parametro
	 * 
	 * @param nivel
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	/**
	 * Método que retorna el tiempo actual de la partida
	 * 
	 * @return time
	 */
	public Tiempo getTime() {
		return time;
	}

	/**
	 * Método que permite modificar el tiempo actual de la partida por el tiempo
	 * del parametro
	 * 
	 * @param time
	 */
	public void setTime(Tiempo time) {
		this.time = time;
	}

	/**
	 * Método que retorna la referencia al puntaje de la dificultad facil del
	 * juego
	 * 
	 * @return puntajeFacil
	 */
	public Puntaje getPuntajeFacil() {
		return puntajeFacil;
	}

	/**
	 * Método que permite modificar el puntaje de la dificultad facil por el
	 * nuevo puntaje del parametro
	 * 
	 * @param puntajeFacil
	 */
	public void setPuntajeFacil(Puntaje puntajeFacil) {
		this.puntajeFacil = puntajeFacil;
	}

	/**
	 * Método que retorna la referencia al puntaje de la dificultad dificil del
	 * juego
	 * 
	 * @return puntajeDificil
	 */
	public Puntaje getPuntajeDificil() {
		return puntajeDificil;
	}

	/**
	 * Método que permite modificar el puntaje de la dificultad dificil por el
	 * nuevo puntaje del parametro
	 * 
	 * @param puntajeDificil
	 */
	public void setPuntajeDificil(Puntaje puntajeDificil) {
		this.puntajeDificil = puntajeDificil;
	}

	/**
	 * Método que retorna el estado actual de la partida
	 * 
	 * @return estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Método que permite modificar el estado actual de la partida por el estado
	 * del parametro
	 * 
	 * @param estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Método que retorna la cantidad actual de meteoritos de la partida
	 * 
	 * @return
	 */
	public int getNumMeteoritos() {
		return numMeteoritos;
	}

	/**
	 * Método que permite modificar la cantidad actual de meteoritos de la
	 * partida
	 * 
	 * @param numMeteoritos
	 */
	public void setNumMeteoritos(int numMeteoritos) {
		this.numMeteoritos = numMeteoritos;
	}

	/**
	 * Método que retorna la cantidad actual de enemigos de la partida
	 * 
	 * @return
	 */
	public int getNumEnemigos() {
		return numEnemigos;
	}

	/**
	 * Método que permite modificar la cantidad actual de enemigos de la partida
	 * 
	 * @param numEnemigos
	 */
	public void setNumEnemigos(int numEnemigos) {
		this.numEnemigos = numEnemigos;
	}

}
