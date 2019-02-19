package pruebas;

import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;


import mundo.Juego;
import mundo.Puntaje;
import mundo.Tiempo;

public class testPuntaje {

	
	private 
	Puntaje pnt;
	
	
	
	@Test
	public void setUpeEscenario1(){
		pnt = new Puntaje(new Tiempo(3, 4, 5, 0), "Nicolas");
	}
	@Test
	public void setUpeEscenario2(){
		
		pnt = new Puntaje(new Tiempo(3, 4, 5, 0), "Nicolas");
		pnt.agragarPuntaje(new Puntaje(new Tiempo(33, 24, 3, 0), "Christian"));
		pnt.agragarPuntaje(new Puntaje(new Tiempo(34, 16, 8, 0), "Diana"));
		pnt.agragarPuntaje(new Puntaje(new Tiempo(6, 3, 2, 0), "Victor"));
		pnt.agragarPuntaje(new Puntaje(new Tiempo(53, 55, 0, 0), "Alejandra"));
		
	}
	
	@Test
	public void testagragarPuntaje(){
		setUpeEscenario1();
		pnt.agragarPuntaje(new Puntaje(new Tiempo(33, 24, 3, 0), "Christian"));
		pnt.agragarPuntaje(new Puntaje(new Tiempo(34, 16, 8, 0), "Diana"));
		
		assertTrue(pnt.getIzq().getNombre().equals("Christian"));
		assertTrue(pnt.getDer().getNombre().equals("Diana"));
	}
	@Test
	public void testInorden(){
		ArrayList<Puntaje> puntajes = new ArrayList<Puntaje>();
		setUpeEscenario2();
		pnt.inorden(puntajes);
		assertTrue(puntajes.get(0).getNombre().equals("Alejandra"));
	}
	
	@Test
	public void compararPorNombre(){
		setUpeEscenario2();
		assertTrue(pnt.compararPorNombre("zzzz")<0);
		assertTrue(pnt.compararPorNombre("aaaa")>0);
		assertTrue(pnt.compararPorNombre("Nicolas")==0);
	}
}
