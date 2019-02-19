package interfaz;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import mundo.Puntaje;

public class PanelPuntajes extends JPanel implements ActionListener {

	private JList<Puntaje> listaPuntajesFacil;
	private JList<Puntaje> listaPuntajesDificil;
	private JScrollPane scrollPaneFacil;
	private JScrollPane scrollPaneDificil;

	private JLabel lbNivel;
	private JLabel lbNombre;
	private JTextField txtNombre;
	
	private JButton btRegreasar;
	private JButton btOrdenarNombre;
	private JButton btOrdenarTiempo;
	private JButton btBuscarJugador;
	
	
	private JComboBox<String> cbNivel;

	public final static String REGRESAR="REGRESAR";
	public final static String ORDENAR_POR_TIEMPO = "ORDENAR_POR_TIEMPO";
	public final static String ORDENAR_POR_NOMBRE = "Ordenar por nombre";
	public final static String BUSCAR_JUGADOR = "Buscar jugador";
	public final static String[] NIVELES = {"Fácil","Dificil"};
	
	private PanelHighscores highscores;
	
	DefaultListModel<Puntaje> modeloListaF;
	DefaultListModel<Puntaje> modeloListaD;
	
	public PanelPuntajes(PanelHighscores highscores) {

		this.highscores = highscores;

		setLayout(new BorderLayout());
		TitledBorder titulo = new TitledBorder("High scores");
		titulo.setTitleColor(Color.BLACK);
		setBorder(titulo);

		modeloListaF = new DefaultListModel<Puntaje>();
		modeloListaD = new DefaultListModel<Puntaje>();

		listaPuntajesFacil = new JList<>();
		listaPuntajesDificil = new JList<>();
		scrollPaneFacil = new JScrollPane(listaPuntajesFacil);
		scrollPaneDificil = new JScrollPane(listaPuntajesDificil);
		listaPuntajesFacil.setModel(modeloListaF);
		listaPuntajesDificil.setModel(modeloListaD);

		JPanel panelAux = new JPanel();
		panelAux.setSize(new Dimension(300, 100));
		panelAux.setLayout(new GridLayout(1, 5));
		
		TitledBorder titulo1 = new TitledBorder("Buscar jugador");
		titulo1.setTitleColor(Color.BLACK);
		panelAux.setBorder(titulo1);

		lbNombre = new JLabel("Nombre:");
		txtNombre = new JTextField();
		
		lbNivel = new JLabel("Nivel:");
		cbNivel = new JComboBox<>(NIVELES);
		
		btBuscarJugador = new JButton("BUSCAR");
		btBuscarJugador.setActionCommand(BUSCAR_JUGADOR);
		btBuscarJugador.addActionListener(this);
		

		panelAux.add(lbNombre);
		panelAux.add(txtNombre);
		panelAux.add(lbNivel);
		panelAux.add(cbNivel);
		panelAux.add(btBuscarJugador);

		btRegreasar = new JButton("<html><p>Regresar</p><p>al menu</p></html>");
		btRegreasar.setActionCommand(REGRESAR);
		btRegreasar.addActionListener(this);
		
		btOrdenarNombre = new JButton("<html><p>Ordenar puntajes</p><p>por nombre</p></html>");
		btOrdenarNombre.setActionCommand(ORDENAR_POR_NOMBRE);
		btOrdenarNombre.addActionListener(this);

		btOrdenarTiempo = new JButton("<html><p>Ordenar puntajes</p><p>por tiempo</p></html>");
		btOrdenarTiempo.setActionCommand(ORDENAR_POR_TIEMPO);
		btOrdenarTiempo.addActionListener(this);
		
		JPanel botones = new JPanel();
		botones.setLayout(new GridLayout(3,1));
		botones.add(btRegreasar);
		botones.add(btOrdenarNombre);
		botones.add(btOrdenarTiempo);
		botones.setPreferredSize(new Dimension(150, 0));
		
		JPanel panelAux2 = new JPanel();
		panelAux2.setLayout(new GridLayout(1, 2));
		panelAux2.add(scrollPaneFacil);
		panelAux2.add(scrollPaneDificil);

		add(panelAux2, BorderLayout.CENTER);
		add(panelAux, BorderLayout.SOUTH);
		add(botones, BorderLayout.WEST);
	}

	public void refrescarListaPuntajesFacil(ArrayList<Puntaje> puntajes) {
		modeloListaF.removeAllElements();
		for (int i = 0; i < puntajes.size(); i++) {
			modeloListaF.add(i, puntajes.get(i));
		}
	}

	public void refrescarListaPuntajesDificil(ArrayList<Puntaje> puntajes) {
		modeloListaD.removeAllElements();
		for (int i = 0; i < puntajes.size(); i++) {
			modeloListaD.add(i, puntajes.get(i));
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals(ORDENAR_POR_NOMBRE)) {
			highscores.ordenarScorePorNombre();
		} else if (comando.equals(BUSCAR_JUGADOR)) {
			highscores.buscarJugador(txtNombre.getText(), cbNivel.getSelectedItem()+"");
		} else if (comando.equals(ORDENAR_POR_TIEMPO)){
			highscores.ordenarScorePorTiempo();
		} else if (comando.equals(REGRESAR)){
			highscores.regresar();
		}
			
	}
}
