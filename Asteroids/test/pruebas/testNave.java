package pruebas;

import org.junit.Test;

import junit.framework.TestCase;
import mundo.Disparo;
import mundo.Nave;

public class testNave extends TestCase {

	public Nave n;
	public Nave n1,n2,n3,n4;
	
	@Test
	public void setUpEscenario1(){
		n = new Nave(800, 300, "./img/Enemigo/enemigo1.png", 3);			
	}
	
	@Test
	public void setUpEscenario2(){
		n = new Nave(800, 300, "./img/Enemigo/enemigo1.png", 3);	
		n.disparar(new Disparo(100, 100, Nave.ABAJO));
		n.disparar(new Disparo(200, 200, Nave.ARRIBA));
		n.disparar(new Disparo(300, 300, Nave.DERECHA));
		n.disparar(new Disparo(400, 400, Nave.IZQUIERDA));
	}
	@Test 
	public void setUpEscenario3(){
		n1 = new Nave(-1000, 3, "./img/Enemigo/enemigo1.png", 3);
		n2 = new Nave(3000, 3, "./img/Enemigo/enemigo1.png", 3);
		n3 = new Nave(500, -300, "./img/Enemigo/enemigo1.png", 3);
		n4 = new Nave(800, 3000, "./img/Enemigo/enemigo1.png", 3);
	}
	
	@Test
	public void testMovLimite(){
		setUpEscenario3();
		n1.movLimite();
		n2.movLimite();
		n3.movLimite();
		n4.movLimite();
		assertTrue(n1.getDireccion().equals(Nave.DERECHA));
		assertTrue(n2.getDireccion().equals(Nave.IZQUIERDA));
		assertTrue(n3.getDireccion().equals(Nave.ABAJO));
		assertTrue(n4.getDireccion().equals(Nave.ARRIBA));
	}
	
	@Test
	public void testMover(){
		setUpEscenario1();
		n.mover();n.mover();n.mover();n.mover();n.mover();
		assertTrue(n.getPosX()==800-(5*n.getVelocidadMov()));
	}
	@Test
	public void testDisparar(){
		setUpEscenario1();
		n.disparar(new Disparo(100, 200, Nave.DERECHA));
		assertTrue(n.getDisparo().getPosX()==100&&n.getDisparo().getPosY()==200);
	}
	
	@Test
	public void testDesconectarDisparo(){
		setUpEscenario2();
		n.getDisparo().setActivo(false);
		n.desconectarDisparo();
		assertTrue(n.getDisparo().getDireccion().equals(Nave.ARRIBA));
		n.getDisparo().setActivo(false);
		n.desconectarDisparo();
		assertTrue(n.getDisparo().getDireccion().equals(Nave.DERECHA));
	}
}
