package interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelIzquierda extends JPanel {
	private JLabel lbAuxiliar;

	public PanelIzquierda() {
		lbAuxiliar = new JLabel(".");
		add(lbAuxiliar);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);

		int i = 0;
		boolean control = true;
		while (i < 590) {

			if (control) {
				g2.drawRect(0, i, 10, 10);
				control = false;
			} else {
				g2.fillRect(0, i, 10, 10);
				control = true;
			}

			i += 10;
		}

	}
}
