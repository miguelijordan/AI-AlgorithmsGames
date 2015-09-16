package entornointeractivo.gui.jugar;
import entornointeractivo.gui.interfaces.ControladorJuego;
import entornointeractivo.gui.interfaces.InformacionAyuda;
import entornointeractivo.gui.interfaces.PanelJuego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;


public class PantallaJuego extends JFrame implements InformacionAyuda {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextArea textAreaInfoAyuda;
	private JTextArea textAreaJug1;
	private JTextArea textAreaJug2;
	private JTextArea textAreaInfoPartida;
	private JPanel panelJug1;
	private JPanel panelJug2;
	private PanelJuego panelJuego;
	private JButton btnAnalizar;
	private JPanel panel_2;
	private JButton btnCancelarPartida;

	/**
	 * Create the frame.
	 */
	public PantallaJuego(String nombreJuego, PanelJuego pJuego) {
		this.panelJuego = pJuego;
		
		setTitle(nombreJuego);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		textAreaInfoAyuda = new JTextArea();
		textAreaInfoAyuda.setWrapStyleWord(true);
		textAreaInfoAyuda.setLineWrap(true);
		textAreaInfoAyuda.setEditable(false);
		textAreaInfoAyuda.setRows(3);
		JScrollPane pScrollInfo = new JScrollPane(textAreaInfoAyuda, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(pScrollInfo, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));
		
		panelJug1 = new JPanel();
		panelJug1.setBorder(new TitledBorder(null, "Jugador 1", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_1.add(panelJug1);
		panelJug1.setToolTipText("");
		panelJug1.setLayout(new BorderLayout(5, 0));
		
		textAreaJug1 = new JTextArea();
		textAreaJug1.setLineWrap(true);
		textAreaJug1.setEditable(false);
		textAreaJug1.setWrapStyleWord(true);
		textAreaJug1.setFont(new Font("Dialog", Font.PLAIN, 12));
		textAreaJug1.setRows(3);
		textAreaJug1.setColumns(10);
		JScrollPane pScrollInfoJug1 = new JScrollPane(textAreaJug1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelJug1.add(pScrollInfoJug1, BorderLayout.NORTH);
		
		panelJug2 = new JPanel();
		panelJug2.setBorder(new TitledBorder(null, "Jugador 2", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelJug2.setToolTipText("");
		panel_1.add(panelJug2);
		panelJug2.setLayout(new BorderLayout(5, 0));
		
		textAreaJug2 = new JTextArea();
		textAreaJug2.setWrapStyleWord(true);
		textAreaJug2.setRows(3);
		textAreaJug2.setLineWrap(true);
		textAreaJug2.setFont(new Font("Dialog", Font.PLAIN, 12));
		textAreaJug2.setEditable(false);
		textAreaJug2.setColumns(10);
		JScrollPane pScrollInfoJug2 = new JScrollPane(textAreaJug2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelJug2.add(pScrollInfoJug2, BorderLayout.NORTH);
		
		textAreaInfoPartida = new JTextArea();
		textAreaInfoPartida.setLineWrap(true);
		textAreaInfoPartida.setWrapStyleWord(true);
		textAreaInfoPartida.setColumns(10);
		textAreaInfoPartida.setEditable(false);
		JScrollPane pScrollInfoPartida = new JScrollPane(textAreaInfoPartida, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(pScrollInfoPartida, BorderLayout.CENTER);
		
		panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnAnalizar = new JButton("Analizar");
		panel_2.add(btnAnalizar);
		
		btnCancelarPartida = new JButton("Cancelar partida");
		panel_2.add(btnCancelarPartida);
		
		contentPane.add(panelJuego, BorderLayout.CENTER);
		setLocationRelativeTo(null);
	}

	@Override
	public String infoComponente(Component c) {
		String res = null;
		if (c.equals(panelJug1) || c.equals(textAreaJug1)) {
			res = "Información sobre la estrategia usada por el jugador 1.";
		} else if (c.equals(panelJug2) || c.equals(textAreaJug2)) {
			res = "Información sobre la estrategia usada por el jugador 1.";
		} else if (c.equals(textAreaInfoPartida)) {
			res = "Información sobre el desarrollo de la partida. Últimos movimientos, puntuación,...";
		} else if (c.equals(textAreaInfoAyuda)) {
			res = "Aquí se muestra información de ayuda sobre los componentes de la pantalla al pasar el ratón sobre ellos.";
		} else if (c.equals(btnAnalizar)) {
			res = "Coloque las fichas de ambos jugadores por turnos en el tablero para crear el estado a analizar.";
			res += " Al pulsar el botón 'Analizar' comenzará el análisis del estado actual realizando el siguiente movimiento cada jugador de forma independiente.";
		} else if (c.equals(btnCancelarPartida)) {
			res = "Cancela la partida actual y vuelve a la pantalla inicial.";
		} else {
			res = panelJuego.infoComponente(c);
		}
		return res;
	}

	@Override
	public void actualizarInfo(String info) {
		textAreaInfoAyuda.setText(info);
		textAreaInfoAyuda.setCaretPosition(0);
		textAreaInfoAyuda.update(textAreaInfoAyuda.getGraphics());
	}

	public void actualizarInfoPartida(String info) {
		textAreaInfoPartida.setText(info);
		textAreaInfoPartida.setCaretPosition(0);
		textAreaInfoPartida.update(textAreaInfoPartida.getGraphics());
	}
	
	public void actualizarInfoJug1(String info) {
		textAreaJug1.setText(info);
		textAreaJug1.setCaretPosition(0);
	}
	
	public void actualizarInfoJug2(String info) {
		textAreaJug2.setText(info);
		textAreaJug2.setCaretPosition(0);
	}
	
	@Override
	public void registrarControladorInformacion(MouseListener ctr) {
		panelJug1.addMouseListener(ctr);
		panelJug2.addMouseListener(ctr);
		textAreaJug1.addMouseListener(ctr);
		textAreaJug2.addMouseListener(ctr);
		textAreaInfoPartida.addMouseListener(ctr);
		btnAnalizar.addMouseListener(ctr);
		textAreaInfoAyuda.addMouseListener(ctr);
		btnCancelarPartida.addMouseListener(ctr);
		
		// registrar el controlador de información en el panel de juego.
		panelJuego.registrarControladorInformacion(ctr);
	}
	
	public void registrarControlador(ActionListener ctr) {
		btnAnalizar.addActionListener(ctr);
		btnAnalizar.setActionCommand("Analizar");
		btnCancelarPartida.addActionListener(ctr);
		btnCancelarPartida.setActionCommand("Cancelar");
	}

	/**
	 * Registra el controlador del juego encargado del desarrollo de la partida.
	 * 
	 * @param ctr	Controlador de juego.
	 */
	public void registrarControladorJuego(ControladorJuego ctr) {
		panelJuego.registrarControlador(ctr);
	}

	public JButton getBtnAnalizar() {
		return btnAnalizar;
	}
	
	public JButton getBtnCancelarPartida() {
		return btnCancelarPartida;
	}

	public void actualizarGraficosInfo() {
		textAreaInfoAyuda.update(textAreaInfoAyuda.getGraphics());		
	}

}
