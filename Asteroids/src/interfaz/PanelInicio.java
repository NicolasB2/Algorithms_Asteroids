package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mundo.Juego;

public class PanelInicio extends JPanel implements ActionListener {

	private interfazJuego principal;

	public final static String NUEVO_JUEGO = "Nuevo juego";
	public final static String CONTINUAR_JUEGO = "Continuar juego";
	public final static String PUNTAJES = "Puntajes";
	public final static String SALIR="SALIR";
	
	private JButton btNuevo;
	private JButton btContinuar;
	private JButton btPuntajes;
	private JButton btSalir;

	public PanelInicio(interfazJuego principal) {
		setLayout(new BorderLayout());
		this.principal = principal;
		
		JLabel lbFondo = new JLabel("");
		ImageIcon img = new ImageIcon("./img/bannerPrincipal.jpg");
		lbFondo.setIcon(img);

		JPanel panelBotones = new JPanel();
		panelBotones.setPreferredSize(new Dimension(500, 200));
		panelBotones.setLayout(new FlowLayout());
		
		btNuevo = new JButton();
		btNuevo.setIcon(new ImageIcon("./img/menu/start.jpg"));
		btNuevo.setRolloverIcon(new ImageIcon("./img/menu/start2.jpg"));
		
		btContinuar = new JButton();
		btContinuar.setIcon(new ImageIcon("./img/menu/continue.jpg"));
		btContinuar.setRolloverIcon(new ImageIcon("./img/menu/continue2.jpg"));
		
		btPuntajes = new JButton();
		btPuntajes.setIcon(new ImageIcon("./img/menu/score.jpg"));
		btPuntajes.setRolloverIcon(new ImageIcon("./img/menu/score2.jpg"));
		
		btSalir = new JButton();
		btSalir.setIcon(new ImageIcon("./img/menu/exit.jpg"));
		btSalir.setRolloverIcon(new ImageIcon("./img/menu/exit2.jpg"));
		
		btNuevo.setActionCommand(NUEVO_JUEGO);
		btContinuar.setActionCommand(CONTINUAR_JUEGO);
		btPuntajes.setActionCommand(PUNTAJES);
		btSalir.setActionCommand(SALIR);
		
		btNuevo.addActionListener(this);
		btContinuar.addActionListener(this);
		btPuntajes.addActionListener(this);
		btSalir.addActionListener(this);

		panelBotones.add(btNuevo);
		panelBotones.add(btContinuar);
		panelBotones.add(btPuntajes);
		panelBotones.add(btSalir);
		
		add(panelBotones, BorderLayout.WEST);
		add(lbFondo, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals(NUEVO_JUEGO)) {
			principal.mostrarPanelNivel();
		} else if (comando.equals(CONTINUAR_JUEGO)) {
			principal.iniciarJuego(Juego.RUTA_JUEGO_GUARDADO);
		} else if (comando.equals(PUNTAJES)) {
			principal.mostrarPuntajes();
		}else{
			principal.dispose();
		}
	}

}
