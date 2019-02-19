package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import mundo.Juego;

public class PanelSeleccionNivel extends JPanel implements MouseListener {

	public interfazJuego principal;
	public Juego mundo;

	public PanelSeleccionNivel(interfazJuego principal, Juego mundo) {
		this.principal = principal;
		this.mundo = mundo;
		setPreferredSize(new Dimension(Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA));
		setLayout(new BorderLayout());
		addMouseListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(new ImageIcon("./img/dificultad.jpg").getImage(), 0, 0, principal.getWidth(),
				principal.getHeight(), null);
		
		g2.setColor(Color.black);
		g2.setFont(new Font("Calibri", Font.BOLD, 60));
		g2.drawString("Facil",230,580);	
		g2.setColor(Color.white);
		g2.drawString("Dificil",880,280);
		g2.setFont(new Font("Calibri", Font.BOLD, 80));
		g2.drawString("Seleccione el Nivel", 270,130);
		
		int [] vx1 = {50, 130, 130};
        int [] vy1 = {90, 40, 130};
        g.fillPolygon (vx1, vy1, 3);
		
		g2.setColor(new Color(100, 100, 100));
		g2.fillOval(1070, 50, 100, 100);
		g2.setColor(new Color(60, 60, 60));
		g2.drawOval(1070, 50, 100, 100);
		g2.fillOval(1090, 70, 20, 20);
		g2.fillOval(1106, 125, 20, 20);
		g2.fillOval(1130, 90, 30, 30);
		
		g2.setColor(Color.BLACK);
		g2.drawOval(1090, 70, 20, 20);
		g2.drawOval(1106, 125, 20, 20);
		g2.drawOval(1130, 90, 30, 30);
		
		g2.setColor(Color.WHITE);
		g2.drawLine(101, 530, 61, 619);
		g2.drawLine(61, 619, 139, 566);
		g2.drawLine(139, 566, 45, 566);
		g2.drawLine(45, 566, 115, 616);
		g2.drawLine(115, 616, 101, 530);
		
	}

	@Override
	public void mouseClicked(MouseEvent m) {
		System.out.println(m.getX() +" - "+ m.getY());
		if (m.getY() > 240 && m.getY() < 640 && m.getX() > 190 && m.getX() < 555) {
			principal.iniciarJuego(Juego.RUTA_JUEGO_FACIL);
		} else if (m.getY() > 240 && m.getY() < 640 && m.getX() > 680 && m.getX() < 1040) {
			principal.iniciarJuego(Juego.RUTA_JUEGO_DIFICL);
		}else if (m.getX()>50&&m.getX()<130&&m.getY()>40&&m.getY()<130){
			principal.remove(this);
			principal.mostrarPanelInicio();
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		setCursor(new Cursor(Cursor.HAND_CURSOR));

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
