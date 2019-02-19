package mundo;

/**
 * La clase ObjetoVolador modela las caracter�sticas y comportamientos de la
 * entidad que representa los objetos voladores dentro del espacio
 * 
 */
public class ObjetosVolador implements Movimiento, Vida {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Constante que hace referencia a la ruta de la imagen de la explosion
	 */
	public final static String RUTA_EXPLOCION = "./img/bomb.gif";

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
	 * Esta caracteristica representa el ancho del objeto volador en pixeles
	 */
	private int ancho;

	/**
	 * Esta caracteristica representa el alto del objeto volador en pixeles
	 */
	private int alto;

	/**
	 * Esta caracteristica representa la vida actual del objeto volador
	 */
	private int vida;

	/**
	 * Esta caracteristica representa la direccion en la cual se encuentra
	 * mirando el objeto volador
	 */
	private String direccion;

	/**
	 * Esta caracteristica hace una referencia a la ruta de la imagen del objeto
	 * volador
	 */
	private String rutaImagen;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Constructor de la clase objeto volador
	 * 
	 * @param posX
	 *            Es la posicion inicial en el eje x del plano cartesiano
	 * @param posY
	 *            Es la posicion inicial en el eje y del plano cartesiano
	 * @param alto
	 *            Es el alto del objeto
	 * @param ancho
	 *            Es el ancho del objeto
	 * @param ruta
	 *            Es la ruta de la imagen del objeto
	 */
	public ObjetosVolador(int posX, int posY, int alto, int ancho, String ruta) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.alto = alto;
		this.ancho = ancho;
		this.rutaImagen = ruta;
	}

	/**
	 * M�todo que retorna la posicion en el eje x del objeto volador
	 * 
	 * @return posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * M�todo que permite modificar la posicion en el eje x del objeto volador
	 * por el valor del parametro
	 * 
	 * @param posX
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * M�todo que retorna la posicion en el eje y del objeto volador
	 * 
	 * @return posY
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * M�todo que permite modificar la posicion en el eje y del objeto volador
	 * por el valor del parametro
	 * 
	 * @param posY
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * M�todo que retorna la direccion en la cual se encuentra mirando el objeto
	 * volador
	 * 
	 * @return direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * M�todo que permite modificar la direccion en la cual mira el objeto
	 * volador por la del parametro
	 * 
	 * @param direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * M�todo que retorna el ancho el ancho del objeto volador
	 * 
	 * @return ancho
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * M�todo que permite modificar el ancho del objeto volador por el valor del
	 * parametro
	 * 
	 * @param ancho
	 */
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	/**
	 * M�todo que retorna el alto del objeto volador
	 * 
	 * @return alto
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * M�todo que permite modificar el alto del objeto volador por el valor del
	 * parametro
	 * 
	 * @param alto
	 */
	public void setAlto(int alto) {
		this.alto = alto;
	}

	/**
	 * M�todo que retorna la ruta de la imagen del objeto volador
	 * 
	 * @return rutaImagen
	 */
	public String getRutaImagen() {
		return rutaImagen;
	}

	/**
	 * M�todo que permite modificar la ruta de la imagen por la ruta del
	 * parametro
	 * 
	 * @param rutaImagen
	 */
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	/**
	 * M�todo que retorna la vida del objeto volador
	 * 
	 * @return vida
	 */
	public int getVida() {
		return vida;
	}

	/**
	 * M�todo que permite modificar el valor de la vida del objeto volador por
	 * el del parametro
	 * 
	 * @param vida
	 */
	public void setVida(int vida) {
		this.vida = vida;
	}

	/**
	 * M�todo que verifica que el objeto volador se encuentre dentro de la
	 * pantalla de juego
	 * 
	 * @return retorno
	 */
	public boolean verificarExistencia() {
		boolean retorno = false;

		if (posX >= 0 && posX <= Juego.ANCHO_PANTALLA && posY >= 0 && posY <= Juego.ALTO_PANTALLA) {
			retorno = true;
		}

		return retorno;
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
	}

	@Override
	public void calcularVida() {
		// TODO Auto-generated method stub
	}

	@Override
	public void perderVida(int da�o) {
		// TODO Auto-generated method stub
	}

}
