package interfaz;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import mundo.Puntaje;

public class PanelHighscores extends JPanel {

	private PanelPuntajes panelPuntajes;

	private PanelSuperior panelSuperior;
	private PanelInferior panelInferior;
	private PanelDerecha panelDerecha;
	private PanelIzquierda panelIzquierda;

	private interfazJuego principal;

	public PanelHighscores(interfazJuego principal) {

		this.principal = principal;
		setLayout(new BorderLayout());

		panelPuntajes = new PanelPuntajes(this);
		panelSuperior = new PanelSuperior();
		panelInferior = new PanelInferior();
		panelDerecha = new PanelDerecha();
		panelIzquierda = new PanelIzquierda();

		add(panelSuperior, BorderLayout.NORTH);
		add(panelPuntajes, BorderLayout.CENTER);
		add(panelInferior, BorderLayout.SOUTH);
		add(panelDerecha, BorderLayout.EAST);
		add(panelIzquierda, BorderLayout.WEST);
	}

	public void refrescarListadoFacil(ArrayList<Puntaje> puntajes) {
		panelPuntajes.refrescarListaPuntajesFacil(puntajes);
	}

	public void refrescarListadoDificil(ArrayList<Puntaje> puntajes) {
		panelPuntajes.refrescarListaPuntajesDificil(puntajes);
	}

	public void ordenarScorePorTiempo(){
		principal.ordenarScorePorTiempo();
	}
	
	public void ordenarScorePorNombre() {
		principal.ordenarScorePorNombre();
	}

	public void buscarJugador(String n, String nivel) {
		principal.buscarJugador(n, nivel);
	}
	
	public void regresar(){
		principal.remove(this);
		principal.mostrarPanelInicio();
	}

}
