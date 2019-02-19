package hilo;

import interfaz.interfazJuego;
import mundo.Juego;
import mundo.Tiempo;

public class HiloTiempo extends Thread {

	private interfazJuego interfaz;
	private Juego j;
	private Tiempo t;
	
	public HiloTiempo(interfazJuego interfaz,Juego j) {
		this.interfaz=interfaz;
		this.j=j;
		this.t=j.getTime();
		
	}

	
	@Override
	public void run() {
		
		while (j.getEstado().equals(Juego.ACTIVO)) {
			
			interfaz.refrescarGalaxia();
			t.correrTiempo();
			try {
				sleep(10);
				j.fin();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
		interfaz.validarEstado();
		super.run();
	}

}
