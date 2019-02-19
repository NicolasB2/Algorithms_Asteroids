package hilo;

import mundo.Disparo;
import mundo.Juego;

public class HiloDisparo extends Thread {

	private Disparo disparo;
	private Juego juego;

	public HiloDisparo(Juego juego, Disparo disparo) {
		super();
		this.juego = juego;

		this.disparo = disparo;
	}

	@Override
	public void run() {

		while (disparo.isActivo() && juego.getEstado().equals(Juego.ACTIVO)) {
			disparo.mover();
			try {
				sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
