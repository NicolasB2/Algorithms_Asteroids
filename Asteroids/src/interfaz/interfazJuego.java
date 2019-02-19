package interfaz;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import hilo.HiloDisparo;
import hilo.HiloEnemigo;
import hilo.HiloMeteorito;
import hilo.HiloTiempo;
import mundo.Disparo;
import mundo.ExcepcionContinuar;
import mundo.ExceptionPuntaje;
import mundo.Juego;
import mundo.Movimiento;
import mundo.Nave;
import mundo.Puntaje;

public class interfazJuego extends JFrame {

	private Juego juego;

	private PanelGalaxia panelGalaxia;
	private PanelInicio panelInicio;
	private PanelSeleccionNivel panelSeleccionNivel;
	private PanelHighscores panelHighscores;

	private VentanaPausa ventanaPausa;
	private HiloTiempo hT;

	public interfazJuego() {
		setTitle("Asteroids");
		setPreferredSize(new Dimension(Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		juego = new Juego();
		panelInicio = new PanelInicio(this);
		panelSeleccionNivel = new PanelSeleccionNivel(this, juego);
		ventanaPausa = new VentanaPausa(this);
		juego.cargarPuntaje();
		add(panelInicio);
		pack();
	}

	public void refrescarGalaxia() {
		panelGalaxia.repaint();
	}

	public void mostrarPanelNivel() {
		remove(panelInicio);
		add(panelSeleccionNivel);
		repaint();
		revalidate();
	}

	public void mostrarPanelInicio() {
		add(panelInicio);
		repaint();
		revalidate();
		pack();
		setLocationRelativeTo(null);
	}

	public void mostrarPanelGalaxia() {
		remove(panelInicio);
		remove(panelSeleccionNivel);
		add(panelGalaxia);
		repaint();
		revalidate();
	}

	public void mostrarVentanaPausa() {
		
		juego.setEstado(Juego.PAUSA);
		juego.getPrincipal().reiniciarDisparo();
		if (juego.getNivel() == 2) {
			for (int i = 0; i < juego.getEnemigos().length; i++) {
				if (juego.getEnemigos()[i] != null) {
					juego.getEnemigos()[i].reiniciarDisparo();
				}
			}
		}
		ventanaPausa.setVisible(true);
		ventanaPausa.requestFocus();
	}

	public void continuarJuego() {
		add(panelGalaxia);
		juego.setEstado(Juego.ACTIVO);
		crearCronometro();
		crearMeteoritos();
		if (juego.getNivel() == 2) {
			crearEnemigos();
		}

		ventanaPausa.setVisible(false);
	}

	public void iniciarJuego(String ruta) {

		try {
			juego.cargarArchivo(new File(ruta));
			remove(panelSeleccionNivel);
			remove(panelInicio);
			panelGalaxia = new PanelGalaxia(this, juego);
			add(panelGalaxia);
			repaint();
			revalidate();
			crearMeteoritos();
			crearCronometro();

			if (juego.getNivel() == 2) {
				crearEnemigos();
			}

		} catch (ExcepcionContinuar e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void crearCronometro() {
		hT = new HiloTiempo(this, juego);
		hT.start();
	}

	public void crearMeteoritos() {
		for (int i = 0; i < juego.getMeteoritos().length; i++) {
			if (juego.getMeteoritos()[i] != null) {
				HiloMeteorito hilom = new HiloMeteorito(this, juego.getMeteoritos()[i], juego);
				hilom.start();
			}
		}
	}

	public void crearEnemigos() {
		for (int i = 0; i < juego.getEnemigos().length; i++) {
			if (juego.getEnemigos()[i] != null) {
				HiloEnemigo hE = new HiloEnemigo(this, juego, juego.getEnemigos()[i]);
				hE.start();
			}
		}
	}

	public void crearDisparo(Nave n) {
		Disparo dis = null;
		if (n == juego.getPrincipal()) {
			dis = (new Disparo(n.getPosX() - 7 + n.getAncho() / 3, n.getPosY() + n.getAlto() / 3 - 7,
					n.getDireccion()));
		} else {
			dis = (new Disparo(n.getPosX() - 7 + n.getAncho() / 3, n.getPosY() + n.getAlto() / 3 - 7,
					Movimiento.ABAJO));
		}
		n.disparar(dis);
		HiloDisparo hilod = new HiloDisparo(juego, dis);
		hilod.start();
	}

	public void mover(int kP) {

		switch (kP) {
		case KeyEvent.VK_UP:
			juego.getPrincipal().setDireccion(Movimiento.ARRIBA);
			juego.getPrincipal().mover();

			break;
		case KeyEvent.VK_DOWN:
			juego.getPrincipal().setDireccion(Movimiento.ABAJO);
			juego.getPrincipal().mover();

			break;
		case KeyEvent.VK_LEFT:
			juego.getPrincipal().setDireccion(Movimiento.IZQUIERDA);
			juego.getPrincipal().mover();

			break;
		case KeyEvent.VK_RIGHT:
			juego.getPrincipal().setDireccion(Movimiento.DERECHA);
			juego.getPrincipal().mover();

			break;
		case 32:
			crearDisparo(juego.getPrincipal());

			break;

		case 27:
			remove(panelGalaxia);
			mostrarVentanaPausa();
			break;
		default:
			break;
		}
	}

	public void validarEstado() {

		if (juego.getEstado().equals(Juego.COMPLETADO)) {

			String nom = JOptionPane.showInputDialog(null, "Digite su nombre");

			if (nom != null) {
				juego.agregarPuntaje(juego.getTime(), nom);
				juego.guardarPuntaje();
			}

			int opt = JOptionPane.showConfirmDialog(null, "volver a jugar", "opcion", JOptionPane.YES_NO_OPTION);
			if (opt == JOptionPane.NO_OPTION) {
				dispose();
			} else {
				remove(panelGalaxia);
				add(panelSeleccionNivel);
				revalidate();
				repaint();
			}
		} else if (juego.getEstado().equals(Juego.DERROTA)) {
			int opt = JOptionPane.showConfirmDialog(null, "Has perdido Quieres volver a intentarlo", "opcion",
					JOptionPane.YES_NO_OPTION);
			if (opt == JOptionPane.NO_OPTION) {
				dispose();
			} else {
				remove(panelGalaxia);
				add(panelSeleccionNivel);
				revalidate();
				repaint();
			}
		}
	}

	public void mostrarPuntajes() {

		panelHighscores = new PanelHighscores(this);
		remove(panelInicio);
		this.setSize(new Dimension(600, 600));
		setLocationRelativeTo(null);
		ordenarScorePorTiempo();
		add(panelHighscores);
		revalidate();
		repaint();
	}

	public void guardarProgreso() {
		int op = JOptionPane.showConfirmDialog(null,
				"Si guarda el progreso actual \n se borraran los datos de la pratida anterior,", "Guardar",
				JOptionPane.YES_NO_OPTION);
		if (op == JOptionPane.YES_OPTION) {
			juego.guardarArchivo();
		}
	}

	public void buscarJugador(String n, String nivel) {
		Puntaje pt = null;
		if (nivel.equals("Fácil")) {
			pt = juego.getPuntajeFacil();
		} else if (nivel.equals("Dificil")) {
			pt = juego.getPuntajeDificil();
		}

		try {
			Puntaje ptBuscado = juego.buscarPuntaje(n, pt);
			System.out.println(ptBuscado.getTiempo());
			JOptionPane.showMessageDialog(null,
					"Nombre jugador: " + ptBuscado.getNombre() + "\nPuntaje jugador: " + ptBuscado.getTiempo());
		} catch (ExceptionPuntaje e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void ordenarScorePorNombre() {
		Puntaje ptf = juego.getPuntajeFacil();
		Puntaje ptd = juego.getPuntajeDificil();

		try {
			panelHighscores.refrescarListadoFacil(juego.ordenarArbolPorNombre(ptf));
			panelHighscores.refrescarListadoDificil(juego.ordenarArbolPorNombre(ptd));
		} catch (ExceptionPuntaje e) {
			JOptionPane.showMessageDialog(null, "No es posible ordenar el listado de puntajes");
		}
	}

	public void ordenarScorePorTiempo() {

		if (juego.getPuntajeFacil() != null) {
			ArrayList<Puntaje> listadoFacil = new ArrayList<>();
			juego.getPuntajeFacil().inorden(listadoFacil);
			panelHighscores.refrescarListadoFacil(listadoFacil);
		}

		if (juego.getPuntajeDificil() != null) {

			ArrayList<Puntaje> listadoDificil = new ArrayList<>();
			juego.getPuntajeDificil().inorden(listadoDificil);
			panelHighscores.refrescarListadoDificil(listadoDificil);
		}
	}

	public static void main(String[] args) {
		interfazJuego interfaz = new interfazJuego();
		interfaz.setLocationRelativeTo(null);
		interfaz.setVisible(true);
	}

}
