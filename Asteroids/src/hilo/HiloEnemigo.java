package hilo;

import interfaz.interfazJuego;
import mundo.Juego;
import mundo.Nave;

public class HiloEnemigo extends Thread {

	private interfazJuego interfaz;
	private Juego juego;
	private Nave n;
	private int contador;

	public HiloEnemigo(interfazJuego interfaz, Juego juego, Nave n) {
		super();
		this.interfaz = interfaz;
		this.juego = juego;
		this.n = n;
		contador = 0;
	}

	@Override
	public void run() {
		while (juego.getEstado().equals(Juego.ACTIVO) && n.getVida() > 0) {
			try {
				sleep(30);
				n.mover();
				contador++;
				if (contador == 20) {
					contador = 0;
					interfaz.crearDisparo(n);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			interfaz.refrescarGalaxia();
		}

		if (n.getVida() <= 0) {
			try {
				sleep(600);
				juego.eliminarNave(n);
				;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
