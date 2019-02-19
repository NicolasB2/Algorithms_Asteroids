package pruebas;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import mundo.ExcepcionContinuar;
import mundo.ExceptionPuntaje;
import mundo.Juego;
import mundo.Meteorito;
import mundo.Movimiento;
import mundo.Nave;
import mundo.Puntaje;
import mundo.Tiempo;

public class testJuego {

	private Juego juego;

	@Test
	public void setUpEscenario1() {
		juego = new Juego();
	}

	@Test
	public void setUpEscenario2() {
		juego = new Juego();
		juego.cargarPuntaje();
	}

	@Test
	public void setUpEscenario3() throws ExcepcionContinuar {
		juego = new Juego();
		juego.cargarPuntaje();
		juego.cargarArchivo(new File(Juego.RUTA_JUEGO_FACIL));
		juego.agregarPuntaje(new Tiempo(43, 13, 0, 0), "Arturo");
		juego.agregarPuntaje(new Tiempo(58, 7, 0, 0), "Xilena");
		juego.agregarPuntaje(new Tiempo(96, 24, 0, 0), "Fabian");

		juego.cargarArchivo(new File(Juego.RUTA_JUEGO_DIFICL));
		juego.agregarPuntaje(new Tiempo(68, 30, 0, 0), "Jeisson");
		juego.agregarPuntaje(new Tiempo(98, 54, 0, 0), "León");
		juego.agregarPuntaje(new Tiempo(44, 47, 0, 0), "Ivan");
		
	}

	/**
	 * Verifica que el método cargarArchivo de la clase juego esta en capacidad
	 * de cargar cualquier archivo
	 */
	@Test
	public void testCargarArchivo1() {
		setUpEscenario1();

		try {
			juego.cargarArchivo(new File(Juego.RUTA_JUEGO_FACIL));

			assertTrue(juego.getNivel() == 1);
			assertTrue(juego.getMeteoritos() != null);
			assertTrue(juego.getNumMeteoritos() == 4);
			assertTrue(juego.getEstado().equals(Juego.ACTIVO));

			juego.cargarArchivo(new File(Juego.RUTA_JUEGO_DIFICL));

			assertTrue(juego.getNivel() == 2);
			assertTrue(juego.getMeteoritos() != null);
			assertTrue(juego.getNumMeteoritos() == 10);
			assertTrue(juego.getEnemigos() != null);
			assertTrue(juego.getNumEnemigos() == 6);
			assertTrue(juego.getEstado().equals(Juego.ACTIVO));

		} catch (Exception e) {
			fail("");
		}
	}

	/**
	 * Verifica que el método cargarArchivo maneja la excepcion propia cuando se
	 * trata de cargar una partida pero no ha sido guardada previamente
	 */
	@Test
	public void testCargarArchivo2() {
		setUpEscenario1();
		try {
			juego.cargarArchivo(new File("./docs/archivoPrueba.txt"));
			fail("");
		} catch (ExcepcionContinuar e) {
		}
	}

	@Test
	public void testGuardarArchivo() {
		setUpEscenario1();

		try {
			juego.cargarArchivo(new File(Juego.RUTA_JUEGO_FACIL));
			juego.eliminarMeteorito(juego.getMeteoritos()[0]);
			juego.eliminarMeteorito(juego.getMeteoritos()[1]);
			juego.guardarArchivo();

			juego.cargarArchivo(new File(Juego.RUTA_JUEGO_GUARDADO));
			assertTrue(juego.getNumMeteoritos() == 2);

			juego.cargarArchivo(new File(Juego.RUTA_JUEGO_DIFICL));
			juego.eliminarMeteorito(juego.getMeteoritos()[0]);
			juego.eliminarMeteorito(juego.getMeteoritos()[1]);
			juego.eliminarMeteorito(juego.getMeteoritos()[2]);

			juego.eliminarNave(juego.getEnemigos()[0]);
			juego.eliminarNave(juego.getEnemigos()[1]);

			juego.guardarArchivo();
			juego.cargarArchivo(new File(Juego.RUTA_JUEGO_GUARDADO));
			System.out.println(juego.getNumEnemigos());
			assertTrue(juego.getNumEnemigos() == 4);
			assertTrue(juego.getNumMeteoritos() == 7);

		} catch (Exception e) {
		}

	}

	/**
	 * Verifica que el método cargarPuntaje de la clase juego esta en capacidad
	 * de cargar los archivos
	 */
	@Test
	public void testCargarPuntaje() {
		setUpEscenario1();
		juego.cargarPuntaje();

		assertTrue(juego!=null);
		assertTrue(juego.getPuntajeFacil()!=null);
		assertTrue(juego.getPuntajeDificil()!=null);

	}
	
	/**
	 * Verifica que el método agregarPuntaje esta en capacidad de agregar un
	 * nuevo puntaje dependiendo del puntaje al cual se le desee hacer dicho
	 * proceso
	 * @throws ExceptionPuntaje 
	 */
	@Test
	public void testAgregarPuntaje() throws ExceptionPuntaje {
		setUpEscenario2();

		try {
			juego.cargarArchivo(new File(Juego.RUTA_JUEGO_FACIL));
			Tiempo t1 = new Tiempo(0, 15, 0, 0);
			String n1 = "Alejandra";

			juego.agregarPuntaje(t1, n1);
			assertTrue(juego.buscarPuntaje(n1, juego.getPuntajeFacil()).getTiempo()==t1);

			juego.cargarArchivo(new File(Juego.RUTA_JUEGO_DIFICL));
			Tiempo t2 = new Tiempo(0, 20, 0, 0);
			String n2 = "Arturo";

			juego.agregarPuntaje(t2, n2);
			assertTrue(juego.buscarPuntaje(n2, juego.getPuntajeDificil()).getTiempo()==t2);

		} catch (ExcepcionContinuar e) {
			fail("");
		}

	}

	/**
	 * Verifica que el método choque se encuentra en capacidad de alterar las
	 * caracteristicas de los objetos implicados
	 */
	@Test
	public void testChoque() {
		setUpEscenario1();

		Nave nv = new Nave(50, 50, "./img/Nave/nave2", 50);
		Meteorito mt = new Meteorito(50, 50, 1, "./img/asteroide3", Movimiento.ARRIBA);

		mt.setAncho(Meteorito.MAX_TAMANIO);
		mt.setAlto(Meteorito.MAX_TAMANIO);
		mt.calcularDaño();

		int vidaAntes = nv.getVida();
		juego.choque(nv, mt);
		int vidaDespues = nv.getVida();

		assertTrue(vidaAntes == ++vidaDespues);
		assertTrue(mt.getVida() == 0);

	}

	/**
	 * Verifica que el método eliminarMeteoritos es capaz de eliminar los
	 * meteoritos del arreglo del juego
	 */
	@Test
	public void testEliminarMeteorito() {
		setUpEscenario1();

		try {
			juego.cargarArchivo(new File(Juego.RUTA_JUEGO_FACIL));

			Meteorito mt0 = juego.getMeteoritos()[0];
			Meteorito mt1 = juego.getMeteoritos()[1];
			Meteorito mt2 = juego.getMeteoritos()[2];

			juego.eliminarMeteorito(mt0);
			juego.eliminarMeteorito(mt1);
			juego.eliminarMeteorito(mt2);

			assertTrue(juego.getMeteoritos()[0] == null);
			assertTrue(juego.getMeteoritos()[1] == null);
			assertTrue(juego.getMeteoritos()[2] == null);
			assertTrue(juego.getNumMeteoritos() == 1);

		} catch (ExcepcionContinuar e) {
			e.printStackTrace();
		}
	}

	/**
	 * Verifica que el método eliminarNave es capaz de eliminar las naves
	 * enemigas del arreglo
	 */
	@Test
	public void testEliminarNave() {
		setUpEscenario1();

		try {
			juego.cargarArchivo(new File(Juego.RUTA_JUEGO_DIFICL));

			Nave nv0 = juego.getEnemigos()[0];
			Nave nv1 = juego.getEnemigos()[1];

			juego.eliminarNave(nv0);
			juego.eliminarNave(nv1);

			assertTrue(juego.getEnemigos()[0] == null);
			assertTrue(juego.getEnemigos()[1] == null);
			assertTrue(juego.getNumEnemigos() == 4);

		} catch (ExcepcionContinuar e) {
			e.printStackTrace();
		}
	}

	/**
	 * Verifica que el método fin es capaz de cambiar el estado actual del juego
	 * dependiendo de las caracteristicas que este tenga en dicho momento
	 */
	@Test
	public void testFin() {
		setUpEscenario1();

		juego.getPrincipal().setVida(0);
		juego.fin();
		assertTrue(juego.getEstado().equals(Juego.DERROTA));

		try {
			juego.cargarArchivo(new File(Juego.RUTA_JUEGO_FACIL));
			juego.getPrincipal().setVida(1);
			juego.setNumMeteoritos(0);
			juego.fin();
			assertTrue(juego.getEstado().equals(Juego.COMPLETADO));

			juego.cargarArchivo(new File(Juego.RUTA_JUEGO_DIFICL));
			juego.getPrincipal().setVida(1);
			juego.setNumMeteoritos(0);
			juego.setNumEnemigos(0);
			juego.fin();
			assertTrue(juego.getEstado().equals(Juego.COMPLETADO));

		} catch (ExcepcionContinuar e) {
			e.printStackTrace();
		}
	}

	/**
	 * Verifica que el método ordenarArbolPorNombre es capaz de ordenar un
	 * arreglo no nulo
	 */
	@Test
	public void testOrdenarArbolPorNombre1() {
		try {
			setUpEscenario3();
			ArrayList<Puntaje> ptFacil = juego.ordenarArbolPorNombre(juego.getPuntajeFacil());
			ArrayList<Puntaje> ptDificil = juego.ordenarArbolPorNombre(juego.getPuntajeDificil());
			
			assertTrue(ptFacil.get(0).compararPorNombre(ptFacil.get(1).getNombre())<=0);
			assertTrue(ptDificil.get(0).compararPorNombre(ptDificil.get(1))<=0);

		} catch (ExcepcionContinuar e) {
			e.printStackTrace();
		} catch (ExceptionPuntaje e) {
			e.printStackTrace();
		}

	}

	/**
	 * Verifica que el método ordenarArbolPorNombre es capaz de manejar la
	 * excepcion cuando no es posible ordenar un listado de puntajes, pues no
	 * existe
	 */
	@Test
	public void testOrdenarArbolPorNombre2() {
		setUpEscenario1();

		try {
			juego.ordenarArbolPorNombre(juego.getPuntajeFacil());
			fail("");
		} catch (ExceptionPuntaje e) {
		}
	}

	/**
	 * Verifica que el método buscarPuntaje esta en capacidad de buscar puntajes
	 * segun el nombre de los jugadores que lo consiguieron
	 */
	@Test
	public void testBuscarPuntaje1() {
		try {
			setUpEscenario3();

			Puntaje pt0 = juego.buscarPuntaje("Fabian", juego.getPuntajeFacil());
			Tiempo tm0 = new Tiempo(96, 24, 0, 0);

			Puntaje pt1 = juego.buscarPuntaje("Jeisson", juego.getPuntajeDificil());
			Tiempo tm1 = new Tiempo(68, 30, 0, 0);

			assertTrue(pt0.getTiempo().compareTo(tm0) == 0);
			assertTrue(pt1.getTiempo().compareTo(tm1) == 0);

		} catch (ExcepcionContinuar e) {
			e.printStackTrace();
		} catch (ExceptionPuntaje e) {
			e.printStackTrace();
		}
	}

	/**
	 * verifica que el metodo buscarPuntaje es capaz de manejar la excepcion
	 * cuando no existe un puntaje registrado
	 */
	@Test
	public void testBuscarPuntaje2() {
		try {
			setUpEscenario3();
			juego.buscarPuntaje("Brayan", juego.getPuntajeFacil());
			fail("");

		} catch (ExcepcionContinuar e) {
		} catch (ExceptionPuntaje e) {
		}
	}

}
