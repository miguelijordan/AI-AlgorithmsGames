package entornointeractivo.gui.estrategias;

import heuristicos.aprendizaje.Entrenamiento;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

import entornointeractivo.gui.interfaces.InformacionAyuda;

public class PanelEntrenamiento extends JPanel implements InformacionAyuda {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// CONSTANTES
	private static final int N_PARTIDAS_ENTRENAMIENTO = 2000;
	private static final int CADA_N_PARTIDAS = 100;
	private static final int N_PRUEBAS = 100;
	
	private JTextField textFieldNPartidas;
	private JTextField textFieldPausaCada;
	private JTextField textFieldNPruebas;
	private JCheckBox chckbxJugOponente;
	private JButton btnEntrenarJugador;
	private JButton btnEntrenamientoSimultaneo;
	private JTextArea txtAprendizaje;
	private JTextArea textAreaOponente;
	private JPanel panelAprendizaje;
	private JLabel lblNPartidas;
	private JLabel lblPausa;
	private JLabel lblPartidas;
	private JLabel lblPruebas;
	private JPanel panel_5;
	private JLabel lblProbabilidadExploracion;
	private JTextField textFieldProbabilidadExploracion;
	private JScrollPane pScrollAprendizaje;
	
	/**
	 * Create the panel.
	 */
	public PanelEntrenamiento() {
		setBorder(new TitledBorder(new LineBorder(new Color(185, 209, 234)), "Entrenamiento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_4.add(panel_9, BorderLayout.SOUTH);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel_9.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_1);
		
		lblNPartidas = new JLabel("Nº de partidas:");
		panel_1.add(lblNPartidas);
		
		textFieldNPartidas = new JTextField();
		textFieldNPartidas.setText(String.valueOf(N_PARTIDAS_ENTRENAMIENTO));
		panel_1.add(textFieldNPartidas);
		textFieldNPartidas.setColumns(5);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel.add(panel_2);
		
		lblPausa = new JLabel("Pausa cada:");
		panel_2.add(lblPausa);
		
		textFieldPausaCada = new JTextField();
		textFieldPausaCada.setText(String.valueOf(CADA_N_PARTIDAS));
		panel_2.add(textFieldPausaCada);
		textFieldPausaCada.setColumns(5);
		
		lblPartidas = new JLabel("partidas");
		panel_2.add(lblPartidas);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_3.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel.add(panel_3);
		
		lblPruebas = new JLabel("Nº de pruebas:");
		panel_3.add(lblPruebas);
		
		textFieldNPruebas = new JTextField();
		textFieldNPruebas.setText(String.valueOf(N_PRUEBAS));
		panel_3.add(textFieldNPruebas);
		textFieldNPruebas.setColumns(5);
		
		panel_5 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_5.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel.add(panel_5);
		
		lblProbabilidadExploracion = new JLabel("Probabilidad de exploración:");
		panel_5.add(lblProbabilidadExploracion);
		
		textFieldProbabilidadExploracion = new JTextField();
		textFieldProbabilidadExploracion.setText(String.valueOf(Entrenamiento.getProbabilidad_exploracion()));
		panel_5.add(textFieldProbabilidadExploracion);
		textFieldProbabilidadExploracion.setColumns(5);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(new LineBorder(new Color(185, 209, 234)), "Oponente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_9.add(panel_10, BorderLayout.EAST);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		chckbxJugOponente = new JCheckBox("Usar el jugador contrario actual como oponente");
		panel_10.add(chckbxJugOponente, BorderLayout.SOUTH);
		
		textAreaOponente = new JTextArea();
		textAreaOponente.setRows(3);
		textAreaOponente.setColumns(10);
		textAreaOponente.setEditable(false);
		textAreaOponente.setWrapStyleWord(true);
		textAreaOponente.setLineWrap(true);
		JScrollPane pScrollOponente = new JScrollPane(textAreaOponente, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel_10.add(pScrollOponente, BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
		add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.NORTH);
		
		btnEntrenarJugador = new JButton("Entrenar jugador");
		panel_7.add(btnEntrenarJugador);
		
		btnEntrenamientoSimultaneo = new JButton("Entrenar jugador y oponente");
		btnEntrenamientoSimultaneo.setVisible(false);
		panel_7.add(btnEntrenamientoSimultaneo);
		
		panelAprendizaje = new JPanel();
		panelAprendizaje.setBorder(new TitledBorder(new LineBorder(new Color(185, 209, 234)), "Aprendizaje", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.add(panelAprendizaje, BorderLayout.CENTER);
		panelAprendizaje.setLayout(new BorderLayout(0, 0));
		
		txtAprendizaje = new JTextArea();
		txtAprendizaje.setLineWrap(true);
		txtAprendizaje.setWrapStyleWord(true);
		txtAprendizaje.setRows(10);
		pScrollAprendizaje = new JScrollPane(txtAprendizaje, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelAprendizaje.add(pScrollAprendizaje);
	}

	public void registrarControlador(ActionListener ctr) {
		btnEntrenarJugador.addActionListener(ctr);
		btnEntrenarJugador.setActionCommand("Entrenar");
		btnEntrenamientoSimultaneo.addActionListener(ctr);
		btnEntrenamientoSimultaneo.setActionCommand("Entrenar2");
		chckbxJugOponente.addActionListener(ctr);
		chckbxJugOponente.setActionCommand("UsarJug2");
	}

	@Override
	public String infoComponente(Component c) {
		String res = null;
		if (c.equals(btnEntrenarJugador)) {
			res = "Entrena al jugador frente al oponente indicado con los parámetros de entrenamiento fijados.";
		} else if (c.equals(btnEntrenamientoSimultaneo)) {
			res = "Entrena a los dos jugadores simultáneamente (uno frente a otro) con los parámetros de entrenamiento fijados.";
			res += "Esta opción sólo está activada si el jugador contrario usa un evaluador heurístico entrenable.";
		} else if (c.equals(lblNPartidas) || c.equals(textFieldNPartidas)) {
			res = "Número de partidas de entrenamiento.";
		} else if (c.equals(lblPausa) || c.equals(lblPartidas) || c.equals(textFieldPausaCada)) {
			res = "Indica cada cuántas partidas se hará una pausa en el entrenamiento para jugarse el número de partidas de prueba indicado en el siguiente campo.";
		} else if (c.equals(lblPruebas) || c.equals(textFieldNPruebas)) {
			res = "Número de partidas de prueba que se realizarán en cada pausa para ver el aprendizaje del jugador.";
		} else if (c.equals(lblProbabilidadExploracion) || c.equals(textFieldProbabilidadExploracion)) {
			res = "Componente aleatoria para los movimientos del jugador a entrenar.";
			res += " Cada movimiento del jugador tendrá una probabilidad de ser aleatorio (exploración).";
			res += " En caso de que el movimiento sea exploratorio, no se entrenará al evaluador.";
		} else if (c.equals(chckbxJugOponente)) {
			res = "Indica si se usará el jugador contrario actual como oponente durante el entrenamiento.";
			res += "Por defecto se usará una estrategia aleatoria como oponente.";
			res += "Esta opción sólo está activada si el jugador contrario no es un humano.";
		} else if (c.equals(textAreaOponente)) {
			res = "Información de la estrategia que se usará como oponente durante el entrenamiento.";
		} else if (c.equals(txtAprendizaje)) {
			res = "Muestra el desarrollo del entrenamiento mediante una estadística con los resultados de las pruebas realizadas durante el entrenamiento.";
		}
		return res;
	}

	@Override
	public void actualizarInfo(String info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarControladorInformacion(MouseListener ctr) {
		btnEntrenarJugador.addMouseListener(ctr);
		btnEntrenamientoSimultaneo.addMouseListener(ctr);
		lblNPartidas.addMouseListener(ctr);
		textFieldNPartidas.addMouseListener(ctr);
		lblPausa.addMouseListener(ctr);
		lblPartidas.addMouseListener(ctr);
		textFieldPausaCada.addMouseListener(ctr);
		lblPruebas.addMouseListener(ctr);
		textFieldNPruebas.addMouseListener(ctr);
		chckbxJugOponente.addMouseListener(ctr);
		txtAprendizaje.addMouseListener(ctr);
		textAreaOponente.addMouseListener(ctr);
		lblProbabilidadExploracion.addMouseListener(ctr);
		textFieldProbabilidadExploracion.addMouseListener(ctr);
		
		panelAprendizaje.addMouseListener(ctr);
	}
	
	public Integer nPartidasEntrenamiento() {
		Integer nPartidasEntrenamiento = null;
		
		try {
			nPartidasEntrenamiento = Integer.parseInt(textFieldNPartidas.getText());
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(new JFrame(), "Número de partidas de entrenamiento incorrecto.");
			return null;
		}
		
		if (error(nPartidasEntrenamiento))
			return null;
		
		return nPartidasEntrenamiento;
	}
	
	public Integer pausaCadaN() {
		Integer pausaCadaN = null;
		
		try {
			pausaCadaN = Integer.parseInt(textFieldPausaCada.getText());
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(new JFrame(), "El número de partidas entre las que se hace una pausa es incorrecto.");
			return null;
		}
		
		if (error(pausaCadaN))
			return null;
		Integer nPartidasEntrenamiento = nPartidasEntrenamiento();
		if (nPartidasEntrenamiento == null || pausaCadaN > nPartidasEntrenamiento) {
			JOptionPane.showMessageDialog(new JFrame(), "El número de partidas entre las que se hace una pausa no puede ser mayor que el número de partidas de entrenamiento.");
			return null;
		}
		
		return pausaCadaN;
	}
	
	public Integer nPruebas() {
		Integer nPruebas = null;
		
		try {
			nPruebas = Integer.parseInt(textFieldNPruebas.getText());
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(new JFrame(), "Número de partidas de prueba incorrecto.");
			return null;
		}
		
		if (error(nPruebas))
			return null;
		
		return nPruebas;
	}
	
	/**
	 * Control de errores para los parámetros.
	 * 
	 * @param x		Valor del parámetro a controlar.
	 * @return		Verdadero si hay error, falso si no hay ningún error.
	 */
	private boolean error(int x) {
		boolean error = false;
		if (x <= 0) {
			JOptionPane.showMessageDialog(new JFrame(), "Los valores deben ser positivos.");
			error = true;
		}
		return error;
	}
	
	public Double probabilidadExploracion() {
		Double pex = null;
		
		try {
			pex = Double.parseDouble(textFieldProbabilidadExploracion.getText());
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(new JFrame(), "Probabilidad de exploración incorrecta. [0,1].");
			return null;
		}
		
		if (pex < 0 || pex > 1) {
			JOptionPane.showMessageDialog(new JFrame(), "Probabilidad de exploración incorrecta. [0,1].");
			return null;
		}
		
		return pex;
	}
	
	
	/**
	 * 
	 * @return	Verdadero si se usa el jugador contrario como oponente, falso en caso contrario.
	 */
	public boolean oponenteJug() {
		return this.chckbxJugOponente.isSelected();
	}
	
	
	public JButton getBtnEntrenamientoSimultaneo() {
		return btnEntrenamientoSimultaneo;
	}

	public JCheckBox getChckbxJugOponente() {
		return chckbxJugOponente;
	}

	public JTextArea getTxtAprendizaje() {
		return txtAprendizaje;
	}
	
	public void actualizarAprendizaje(String msg) {
		txtAprendizaje.append(msg);
		//txtAprendizaje.setCaretPosition(txtAprendizaje.getDocument().getLength());
		//panelAprendizaje.update(panelAprendizaje.getGraphics());
		txtAprendizaje.update(txtAprendizaje.getGraphics());
		//pScrollAprendizaje.update(pScrollAprendizaje.getGraphics());
	}
	
	public void setTextoOponente(String msg, String entrenamiento) {
		textAreaOponente.setText(msg + "\n" + entrenamiento);
		textAreaOponente.setCaretPosition(0);
	}

	public void inicializarAprendizaje() {
		txtAprendizaje.setText(null);
		txtAprendizaje.setCaretPosition(0);
		panelAprendizaje.update(panelAprendizaje.getGraphics());
	}
	
}

