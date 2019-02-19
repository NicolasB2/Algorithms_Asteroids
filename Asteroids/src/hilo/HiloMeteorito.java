package hilo;

import interfaz.interfazJuego;
import mundo.Juego;
import mundo.Meteorito;

public class HiloMeteorito extends Thread {

	private interfazJuego  principal;
	private Meteorito m;
	private Juego j;
	

	public HiloMeteorito(interfazJuego principal, Meteorito m,Juego j) {
		super();
		this.principal = principal;
		this.m = m;
		this.j=j;
	}


	@Override
	public void run() {
		super.run();
		while (m.getVida()>0&&j.getEstado().equals(Juego.ACTIVO)) {
			principal.refrescarGalaxia();
			m.mover();
			try {
				sleep(m.getEspera());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		if(m.getVida()<=0){
		try {
			sleep(600);
			j.eliminarMeteorito(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
	}
}
