package entornointeractivo.gui.principal;

import entornointeractivo.gui.Juegos;
import entornointeractivo.gui.interfaces.InformacionAyuda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Pantalla principal de la aplicación.
 * Esta pantalla muestra y da acceso a las diferentes opciones de la aplicación.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 30/11/2011
 *
 */
public class PantallaPrincipal extends JFrame implements InformacionAyuda {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea txtInfo;
	private JButton btnJugar;
	private JButton btnSimular;
	private JButton btnAnalizarEstado;
	private JComboBox<Juegos> cmbJuego;
	private JButton btnOpcionesJuego;
	private JButton btnJug1;
	private JButton btnJug2;
	private JTextArea txtJug1;
	private JTextArea txtJug2;
	private JTextArea txtJuego;
	private JLabel lblJuego;
	private JPanel panelJuegos;
	private JPanel panelEstrategias;

	/**
	 * Create the frame.
	 */
	public PantallaPrincipal() {
		setTitle("Entorno interactivo para el estudio de estrategias de IA en juegos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 523);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblJuegosIa = new JLabel("Entorno interactivo para el estudio de estrategias de IA en juegos");
		lblJuegosIa.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblJuegosIa, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		panelJuegos = new JPanel();
		panelJuegos.setBorder(new TitledBorder(null, "Juegos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelJuegos.setToolTipText("");
		panel.add(panelJuegos, BorderLayout.NORTH);
		panelJuegos.setLayout(new BorderLayout(5, 0));
		
		JPanel panel_8 = new JPanel();
		panelJuegos.add(panel_8, BorderLayout.WEST);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_8.add(panel_9, BorderLayout.NORTH);
		
		lblJuego = new JLabel("Juego:");
		panel_9.add(lblJuego);
		
		cmbJuego = new JComboBox<Juegos>(Juegos.values());
		panel_9.add(cmbJuego);
		
		JPanel panel_14 = new JPanel();
		panel_8.add(panel_14, BorderLayout.CENTER);
		
		btnOpcionesJuego = new JButton("Opciones del juego");
		panel_14.add(btnOpcionesJuego);
		
		txtJuego = new JTextArea();
		txtJuego.setLineWrap(true);
		txtJuego.setWrapStyleWord(true);
		txtJuego.setTabSize(4);
		txtJuego.setEditable(false);
		JScrollPane pScrollJuego = new JScrollPane(txtJuego, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelJuegos.add(pScrollJuego, BorderLayout.CENTER);
		
		panelEstrategias = new JPanel();
		panelEstrategias.setBorder(new TitledBorder(null, "Estrategias", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panelEstrategias);
		panelEstrategias.setLayout(new GridLayout(0, 2, 25, 0));
		
		JPanel panel_10 = new JPanel();
		panelEstrategias.add(panel_10);
		panel_10.setLayout(new BorderLayout(0, 5));
		
		txtJug1 = new JTextArea();
		txtJug1.setWrapStyleWord(true);
		txtJug1.setTabSize(4);
		txtJug1.setLineWrap(true);
		txtJug1.setEditable(false);
		txtJug1.setColumns(20);
		JScrollPane pScrollJ1 = new JScrollPane(txtJug1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel_10.add(pScrollJ1, BorderLayout.CENTER);
		
		JPanel panel_12 = new JPanel();
		panel_10.add(panel_12, BorderLayout.NORTH);
		
		btnJug1 = new JButton("Jugador 1");
		panel_12.add(btnJug1);
		
		JPanel panel_11 = new JPanel();
		panelEstrategias.add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 5));
		
		txtJug2 = new JTextArea();
		txtJug2.setTabSize(4);
		txtJug2.setWrapStyleWord(true);
		txtJug2.setLineWrap(true);
		txtJug2.setEditable(false);
		txtJug2.setColumns(20);
		JScrollPane pScrollJ2 = new JScrollPane(txtJug2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel_11.add(pScrollJ2, BorderLayout.CENTER);
		
		JPanel panel_13 = new JPanel();
		panel_11.add(panel_13, BorderLayout.NORTH);
		
		btnJug2 = new JButton("Jugador 2");
		panel_13.add(btnJug2);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 5));
		
		txtInfo = new JTextArea();
		txtInfo.setRows(3);
		txtInfo.setEditable(false);
		txtInfo.setLineWrap(true);
		txtInfo.setTabSize(4);
		txtInfo.setWrapStyleWord(true);
		JScrollPane pScrollInfo = new JScrollPane(txtInfo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel_1.add(pScrollInfo, BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 5));
		
		btnJugar = new JButton("Jugar");
		btnJugar.setPreferredSize(new Dimension(150, 25));
		panel_5.add(btnJugar);
		btnJugar.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		btnSimular = new JButton("Simular");
		btnSimular.setPreferredSize(new Dimension(150, 25));
		panel_5.add(btnSimular);
		btnSimular.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		btnAnalizarEstado = new JButton("Analizar estado");
		btnAnalizarEstado.setPreferredSize(new Dimension(150, 25));
		panel_5.add(btnAnalizarEstado);
		btnAnalizarEstado.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		setLocationRelativeTo(null);
	}
	
	/**
	 * Registra un controlador en todos los componentes activos de la pantalla.
	 * 
	 * @param ctr	Controlador.
	 */
	public void registrarControlador(ActionListener ctr) {
		btnJugar.addActionListener(ctr);
		btnJugar.setActionCommand("Jugar");

		btnSimular.addActionListener(ctr);
		btnSimular.setActionCommand("Simular");
		
		btnAnalizarEstado.addActionListener(ctr);
		btnAnalizarEstado.setActionCommand("Analizar");
		
		btnJug1.addActionListener(ctr);
		btnJug1.setActionCommand("Jugador1");
		
		btnJug2.addActionListener(ctr);
		btnJug2.setActionCommand("Jugador2");

		btnOpcionesJuego.addActionListener(ctr);
		btnOpcionesJuego.setActionCommand("Opciones");

		cmbJuego.addActionListener(ctr);
		cmbJuego.setActionCommand("Juego");
	}
	
	@Override
	public void registrarControladorInformacion(MouseListener ctr) {
		btnJugar.addMouseListener(ctr);
		btnSimular.addMouseListener(ctr);
		btnAnalizarEstado.addMouseListener(ctr);
		btnJug1.addMouseListener(ctr);
		btnJug2.addMouseListener(ctr);
		btnOpcionesJuego.addMouseListener(ctr);
		cmbJuego.addMouseListener(ctr);	
		txtInfo.addMouseListener(ctr);
		txtJuego.addMouseListener(ctr);
		txtJug1.addMouseListener(ctr);
		txtJug2.addMouseListener(ctr);
		lblJuego.addMouseListener(ctr);
		panelEstrategias.addMouseListener(ctr);
		panelJuegos.addMouseListener(ctr);
		addMouseListener(ctr);
	}
	
	@Override
	public String infoComponente(Component c) {
		String res = null;
		if (c.equals(btnJugar)) {
			res = "Juega una partida o muestra el desarrollo de una partida entre dos jugadores controlados por el ordenador.";
		} else if (c.equals(btnSimular)) {
			res = "Simula un número determinado de partidas entre los dos jugadores.";
			res += "\nAmbos jugadores deben ser controlados por el ordenador.";
		} else if (c.equals(btnAnalizarEstado)) {
			res = "Analiza un estado del juego, comparando el movimiento realizado por cada jugador a partir de ese estado.";
			res += "\nAmbos jugadores deben ser controlados por el ordenador.";
		} else if (c.equals(btnJug1)) {
			res = "Selecciona y configura la estrategia del jugador 1.";
		} else if (c.equals(btnJug2)) {
			res = "Selecciona y configura la estrategia del jugador 2.";
		} else if (c.equals(btnOpcionesJuego)) {
			res = "Configura las opciones del juego seleccionado.";
		} else if (c.equals(cmbJuego) || c.equals(lblJuego)) {
			res = "Selecciona el juego.";
		} else if (c.equals(txtInfo)) {
			res = "Aquí se muestra información de ayuda sobre los componentes de la pantalla al pasar el ratón sobre ellos.";
		} else if (c.equals(txtJuego)) {
			res = "Información sobre los parámetros del juego seleccionado.";
		} else if (c.equals(txtJug1)) {
			res = "Información sobre la estrategia seleccionada del jugador 1.";
		} else if (c.equals(txtJug2)) {
			res = "Información sobre la estrategia seleccionada del jugador 2.";
		} else {
			res = "1º. Seleccione y configure el juego.";
			res += "\n2º. Seleccione y configure las estrategias de los jugadores.";
			res += "\n3º. Seleccione una de las posibles acciones: Jugar, Simular o Analizar estado.";
		}
		return res;		
	}
	
	@Override
	public void actualizarInfo(String info) {
		txtInfo.setText(info);
		txtInfo.setCaretPosition(0);
	}

	public void setTextoJug1(String texto) {
		txtJug1.setText(texto);
		txtJug1.setCaretPosition(0);
	}
	
	public void setTextoJug2(String texto) {
		txtJug2.setText(texto);
		txtJug2.setCaretPosition(0);
	}
	
	public void setTextoJuego(String texto) {
		txtJuego.setText(texto);
		txtJuego.setCaretPosition(0);
	}

	public JButton getBtnSimular() {
		return btnSimular;
	}

	public JButton getBtnAnalizarEstado() {
		return btnAnalizarEstado;
	}

	/**
	 * @return the cmbJuego
	 */
	public JComboBox<Juegos> getCmbJuego() {
		return cmbJuego;
	}

	/**
	 * @return the btnOpcionesJuego
	 */
	public JButton getBtnOpcionesJuego() {
		return btnOpcionesJuego;
	}
	
	
}

