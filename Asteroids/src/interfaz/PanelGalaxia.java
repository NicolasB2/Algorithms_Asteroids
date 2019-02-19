package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import mundo.Disparo;
import mundo.Juego;
import mundo.Meteorito;
import mundo.Nave;

public class PanelGalaxia extends JPanel implements KeyListener {

	private interfazJuego interfaz;
	private Juego juego;

	public PanelGalaxia(interfazJuego interfaz, Juego juego) {
		this.juego = juego;
		this.interfaz = interfaz;
		setPreferredSize(new Dimension(Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA));
		addKeyListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		pintarPrincipales(g2);
		pintarMeteoritos(g2);
		pintarDisparos(g2);

		if (juego.getNivel() == 2) {
			pintarEnemigos(g2);
			pintarDisparosEnemigos(g2);
		}

		this.requestFocus();
	}

	public void pintarPrincipales(Graphics2D g2) {
		g2.drawImage(new ImageIcon("./img/fondo3.jpg").getImage(), 0, 0, Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA,
				null);
		String rutaN = juego.getPrincipal().getRutaImagen() + juego.getPrincipal().getDireccion() + ".png";
		g2.drawImage(new ImageIcon(rutaN).getImage(), juego.getPrincipal().getPosX(), juego.getPrincipal().getPosY(),
				juego.getPrincipal().getAncho(), juego.getPrincipal().getAlto(), null);
		g2.setColor(Color.white);
		g2.setFont(new Font("Calibri", Font.BOLD, 40));
		g2.drawString(juego.getTime().toString(), 40, 40);
		int a = 0;
		for (int i = 0; i < juego.getPrincipal().getVida(); i++) {
			g2.drawImage(new ImageIcon("./img/corazon.png").getImage(), 40 + a, 50, 40, 40, null);
			a += 40;
		}

	}

	public void pintarMeteoritos(Graphics2D g2) {
		for (int i = 0; i < juego.getMeteoritos().length; i++) {
			if (juego.getMeteoritos()[i] != null) {
				Meteorito m = juego.getMeteoritos()[i];
				
				g2.drawImage(new ImageIcon(m.getRutaImagen()).getImage(), m.getPosX(), m.getPosY(), m.getAncho(),
						m.getAlto(), null);
				juego.choque(juego.getPrincipal(), juego.getMeteoritos()[i]);
			}
		}
	}

	public void pintarDisparos(Graphics2D g2) {

		if (juego.getPrincipal().getDisparo() != null) {
			Disparo dis = juego.getPrincipal().getDisparo();
			while (dis != null) {
				g2.drawImage(new ImageIcon(dis.getRuta()).getImage(), dis.getPosX(), dis.getPosY(), 50, 50, null);
				if (dis.choque(juego.getMeteoritos())) {
					g2.drawImage(new ImageIcon("./img/bomb.gif").getImage(), dis.getPosX(), dis.getPosY(), 50, 50,
							null);
				}
				if (juego.getEnemigos() != null) {
					if (dis.choque(juego.getEnemigos())) {
						g2.drawImage(new ImageIcon("./img/bomb.gif").getImage(), dis.getPosX(), dis.getPosY(), 50, 50,
								null);
					}
				}
				dis = dis.getSiguiente();
				juego.getPrincipal().desconectarDisparo();
			}
		}
	}

	public void pintarDisparosEnemigos(Graphics2D g2) {
		if (juego.getEnemigos() != null) {
			for (int i = 0; i < juego.getEnemigos().length; i++) {

				if (juego.getEnemigos()[i] != null) {
					Disparo dis = juego.getEnemigos()[i].getDisparo();
					while (dis != null) {
						g2.drawImage(new ImageIcon(dis.getRuta()).getImage(), dis.getPosX(), dis.getPosY(), 50, 50,
								null);
						if (juego.getPrincipal() != null) {
							Nave[] p = new Nave[1];
							p[0] = juego.getPrincipal();
							if (dis.choque(p))
								g2.drawImage(new ImageIcon("./img/bomb.gif").getImage(), dis.getPosX(), dis.getPosY(),
										50, 50, null);
						}
						dis = dis.getSiguiente();
						juego.getEnemigos()[i].desconectarDisparo();
					}

				}
			}
		}
	}

	public void pintarEnemigos(Graphics2D g2) {
		for (int i = 0; i < juego.getEnemigos().length; i++) {
			if (juego.getEnemigos()[i] != null) {
				g2.drawImage(new ImageIcon(juego.getEnemigos()[i].getRutaImagen()).getImage(),
						juego.getEnemigos()[i].getPosX(), juego.getEnemigos()[i].getPosY(),
						juego.getEnemigos()[i].getAncho(), juego.getEnemigos()[i].getAlto(), null);
				juego.choque(juego.getPrincipal(), juego.getEnemigos()[i]);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		int key = ke.getKeyCode();
		if (key != 32 && juego.getEstado().equals(Juego.ACTIVO)) {
			interfaz.mover(key);
		}
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		int key = ke.getKeyCode();
		if (key == 32 && juego.getEstado().equals(Juego.ACTIVO)) {
			interfaz.mover(key);
		}
	}

	@Override
	public void keyTyped(KeyEvent ke) {

	}

}
