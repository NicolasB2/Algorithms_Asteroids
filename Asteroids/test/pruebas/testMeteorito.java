package pruebas;

import org.junit.Test;

import junit.framework.TestCase;
import mundo.Meteorito;

public class testMeteorito extends TestCase {

	public Meteorito m;
	
	@Test
	public void setUpEscenario1(){
		m=new Meteorito(-1000, 300, 4, "./img/asteroide3.gif", Meteorito.ARRIBA);
	}
	@Test
	public void testCalcularDirecion(){
		setUpEscenario1();
		m.calcularDireccion();
		assertTrue(m.getDireccion().equals(Meteorito.DERECHA));
	}
	@Test
	public void testMover(){
		setUpEscenario1();
		m.mover();m.mover();m.mover();m.mover();m.mover();
		assertTrue(m.getPosX()==-995);
	}
}
