package entornointeractivo.gui.resultados;

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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Pantalla para configurar las opciones del juego.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 1/12/2011
 *
 */
public class PantallaEstadisticas extends JFrame implements InformacionAyuda {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * &Aacute;rea de texto para la informaci&oacute;n de ayuda.
	 */
	private JTextArea txtInfo;
	
	/**
	 * Botón aceptar.
	 */
	private JButton btnOK;
	private JTextArea textAreaJug1;
	private JTextArea textAreaEstadisticasJug1;
	private JTextArea textAreaJug2;
	private JTextArea textAreaEstadisticasJug2;

	private JPanel pResultados;
	private JTextArea textAreaJuego;
	private JButton btnGenerarInforme;
	
	/**
	 * Crea la pantalla de opciones del juego.
	 * 
	 * @param nombreJuego		Nombre del juego.
	 * @param panelOpciones		Panel de configuración de opciones del juego en concreto.
	 */
	public PantallaEstadisticas(JPanel pResultados) {
		this.pResultados = pResultados;
		
		setTitle("Estadísticas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 523);
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
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
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setHgap(75);
		panel_2.add(panel_3, BorderLayout.WEST);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setHgap(75);
		panel_2.add(panel_4, BorderLayout.EAST);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6);
		
		btnGenerarInforme = new JButton("Generar informe");
		panel_6.add(btnGenerarInforme);
		
		JPanel panel = new JPanel();
		panel_5.add(panel);
		
		btnOK = new JButton("OK");
		panel.add(btnOK);
		btnOK.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BorderLayout(0, 0));
		panelNorte.add(this.pResultados, BorderLayout.CENTER);
		
		JPanel panelJuego = new JPanel();
		panelJuego.setBorder(new TitledBorder(new LineBorder(new Color(185, 209, 234)), "Juego", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelNorte.add(panelJuego, BorderLayout.NORTH);
		panelJuego.setLayout(new BorderLayout(0, 0));
		
		textAreaJuego = new JTextArea();
		textAreaJuego.setRows(3);
		textAreaJuego.setWrapStyleWord(true);
		textAreaJuego.setLineWrap(true);
		textAreaJuego.setEditable(false);
		JScrollPane pScrollJuego = new JScrollPane(textAreaJuego, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelJuego.add(pScrollJuego);
	
		
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelJ1 = new JPanel();
		panelJ1.setBorder(new TitledBorder(new LineBorder(new Color(185, 209, 234)), "Jugador 1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCentro.add(panelJ1);
		panelJ1.setLayout(new BorderLayout(0, 5));
		
		textAreaJug1 = new JTextArea();
		textAreaJug1.setRows(3);
		textAreaJug1.setWrapStyleWord(true);
		textAreaJug1.setLineWrap(true);
		textAreaJug1.setEditable(false);
		JScrollPane pScrollJug1 = new JScrollPane(textAreaJug1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelJ1.add(pScrollJug1, BorderLayout.NORTH);
		
		textAreaEstadisticasJug1 = new JTextArea();
		textAreaEstadisticasJug1.setRows(5);
		textAreaEstadisticasJug1.setWrapStyleWord(true);
		textAreaEstadisticasJug1.setEditable(false);
		textAreaEstadisticasJug1.setLineWrap(true);
		JScrollPane pScrollEstadisticasJug1 = new JScrollPane(textAreaEstadisticasJug1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pScrollEstadisticasJug1.setPreferredSize(new Dimension(250, 200));
		panelJ1.add(pScrollEstadisticasJug1, BorderLayout.CENTER);
		
		JPanel panelJ2 = new JPanel();
		panelJ2.setBorder(new TitledBorder(new LineBorder(new Color(185, 209, 234)), "Jugador 2", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCentro.add(panelJ2);
		panelJ2.setLayout(new BorderLayout(0, 5));
		
		textAreaJug2 = new JTextArea();
		textAreaJug2.setRows(3);
		textAreaJug2.setWrapStyleWord(true);
		textAreaJug2.setLineWrap(true);
		textAreaJug2.setEditable(false);
		JScrollPane pScrollJug2 = new JScrollPane(textAreaJug2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelJ2.add(pScrollJug2, BorderLayout.NORTH);
		
		textAreaEstadisticasJug2 = new JTextArea();
		textAreaEstadisticasJug2.setWrapStyleWord(true);
		textAreaEstadisticasJug2.setRows(5);
		textAreaEstadisticasJug2.setLineWrap(true);
		textAreaEstadisticasJug2.setEditable(false);
		JScrollPane pScrollEstadisticasJug2 = new JScrollPane(textAreaEstadisticasJug2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pScrollEstadisticasJug2.setPreferredSize(new Dimension(250, 200));
		panelJ2.add(pScrollEstadisticasJug2, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
	}
	
	/**
	 * Registra un controlador en todos los componentes activos de la pantalla.
	 * 
	 * @param ctr	Controlador.
	 */
	public void registrarControlador(ActionListener ctr) {
		btnOK.addActionListener(ctr);
		btnOK.setActionCommand("OK");
		btnGenerarInforme.addActionListener(ctr);
		btnGenerarInforme.setActionCommand("Generar informe");
	}
	
	@Override
	public void registrarControladorInformacion(MouseListener ctr) {
		btnOK.addMouseListener(ctr);
		textAreaJug1.addMouseListener(ctr);
		textAreaJug2.addMouseListener(ctr);
		textAreaEstadisticasJug1.addMouseListener(ctr);
		textAreaEstadisticasJug2.addMouseListener(ctr);
		txtInfo.addMouseListener(ctr);
		textAreaJuego.addMouseListener(ctr);
		btnGenerarInforme.addMouseListener(ctr);
		
		try {
			((InformacionAyuda) pResultados).registrarControladorInformacion(ctr);	
		} catch (Exception c) {}
		
	}
	
	@Override
	public void actualizarInfo(String info) {
		txtInfo.setText(info);
		txtInfo.setCaretPosition(0);
	}

	@Override
	public String infoComponente(Component c) {
		String res = null;
		if (c.equals(btnOK)) {
			res = "Vuelve a la pantalla principal de la aplicación.";
		} else if (c.equals(textAreaJug1)) {
			res = "Estrategia usada por el jugador 1.";
		} else if (c.equals(textAreaJug2)) {
			res = "Estrategia usada por el jugador 2.";
		} else if (c.equals(textAreaEstadisticasJug1)) {
			res = "Estadísticas sobre el jugador 1.";
		} else if (c.equals(textAreaEstadisticasJug2)) {
			res = "Estadísticas sobre el jugador 2.";
		} else if (c.equals(textAreaJuego)) {
			res = "Información del juego.";
		} else if (c.equals(txtInfo)) {
			res = "Aquí se muestra información de ayuda sobre los componentes de la pantalla al pasar el ratón sobre ellos.";
		} else if (c.equals(btnGenerarInforme)) {
			res = "Crea un archivo en formato texto plano con las estadísticas generadas.";
		} else {
			try {
				res = ((InformacionAyuda) pResultados).infoComponente(c);
			} catch (Exception e) {
				res = null;
			}
		}
		return res;
	}
	
	public void setTextoJug1(String texto) {
		textAreaJug1.setText(texto);
		textAreaJug1.setCaretPosition(0);
	}
	
	public void setTextoJug2(String texto) {
		textAreaJug2.setText(texto);
		textAreaJug2.setCaretPosition(0);
	}
	
	public void setTextoEstadisticasJug1(String texto) {
		textAreaEstadisticasJug1.setText(texto);
		textAreaEstadisticasJug1.setCaretPosition(0);
	}
	
	public void setTextoEstadisticasJug2(String texto) {
		textAreaEstadisticasJug2.setText(texto);
		textAreaEstadisticasJug2.setCaretPosition(0);
	}

	public JPanel getpResultados() {
		return pResultados;
	}
	
	public void setTextoJuego(String texto) {
		textAreaJuego.setText(texto);
		textAreaJuego.setCaretPosition(0);
	}
}
