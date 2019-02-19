package interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class VentanaPausa extends JDialog implements ActionListener{

	private interfazJuego interfaz;
	private JButton continuar;
	private JButton guardar;
	private JButton salir;
	private JButton menu;
	
	private final static String CONTINUAR= "CONTINUAR";
	private final static String SALIR= "SALIR";
	private final static String GUARDAR= "GUARDAR";
	private final static String MENU= "MENU";
	
	public  VentanaPausa(interfazJuego interfaz) {

		this.interfaz=interfaz;
		setLayout(new GridLayout(4,1));
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setPreferredSize(new Dimension(450,300));
		
		continuar=new JButton("Continuar Partida");
		continuar.addActionListener(this);
		continuar.setActionCommand(CONTINUAR);
		
		guardar = new JButton("guardar Partida");
		guardar.addActionListener(this);
		guardar.setActionCommand(GUARDAR);
		
		salir = new JButton("salir");
		salir.addActionListener(this);
		salir.setActionCommand(SALIR);
		
		menu = new JButton("regresar al menu");
		menu.addActionListener(this);
		menu.setActionCommand(MENU);
		
		add(continuar);
		add(guardar);
		add(menu);
		add(salir);
		
		setLocation(300,200);
		pack();
		
	}

	@Override
	public void actionPerformed(ActionEvent arg) {
		
		String a = arg.getActionCommand();
		if(a.equals(CONTINUAR)){
			interfaz.continuarJuego();
		}else if(a.equals(GUARDAR)){
			interfaz.guardarProgreso();
			interfaz.mostrarPanelInicio();
		}else if (a.equals(MENU)){
			interfaz.mostrarPanelInicio();
		}else{
			interfaz.dispose();
		}
		this.setVisible(false);
	}
}
