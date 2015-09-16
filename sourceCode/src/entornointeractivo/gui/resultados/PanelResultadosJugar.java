package entornointeractivo.gui.resultados;

import entornointeractivo.gui.interfaces.InformacionAyuda;
import estrategias.agentes.estadisticas.Util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class PanelResultadosJugar extends JPanel implements InformacionAyuda {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panel_2;
	private JPanel panel_5;
	private JLabel lblTiempoPartida2;
	private JLabel lblTiempoPartida;
	private JLabel lblGanador;
	private JPanel panel;
	private JLabel lblEstadoFinal;
	private JPanel panelEstadoFinal;
	private JPanel panel_3;
	private JPanel panelInfoPartida;
	private JTextArea textAreaInfoPartida;

	/**
	 * Create the panel.
	 */
	public PanelResultadosJugar() {
		setBorder(new TitledBorder(new LineBorder(new Color(185, 209, 234)), "Resultados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(5, 0));
		
		panel_2 = new JPanel();
		add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		panel_5 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_5.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_5);
		
		lblTiempoPartida2 = new JLabel("Tiempo de la partida:");
		panel_5.add(lblTiempoPartida2);
		
		lblTiempoPartida = new JLabel("");
		panel_5.add(lblTiempoPartida);
		
		panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblEstadoFinal = new JLabel("Estado final:");
		lblEstadoFinal.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblEstadoFinal, BorderLayout.NORTH);
		
		panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.CENTER);
		
		panelEstadoFinal = new JPanel();
		panel_3.add(panelEstadoFinal);
		panelEstadoFinal.setPreferredSize(new Dimension(100, 100));
		panelEstadoFinal.setLayout(new BorderLayout(0, 0));
		
		panelInfoPartida = new JPanel();
		panel_3.add(panelInfoPartida);
		panelInfoPartida.setPreferredSize(new Dimension(200, 100));
		panelInfoPartida.setLayout(new BorderLayout(0, 0));
		
		textAreaInfoPartida = new JTextArea();
		textAreaInfoPartida.setWrapStyleWord(true);
		textAreaInfoPartida.setLineWrap(true);
		textAreaInfoPartida.setEditable(false);
		JScrollPane pScrollInfoPartida = new JScrollPane(textAreaInfoPartida, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelInfoPartida.add(pScrollInfoPartida, BorderLayout.CENTER);
		
		lblGanador = new JLabel();
		lblGanador.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblGanador, BorderLayout.SOUTH);

	}
	
	public void actualizarResultados(JPanel estadoFinal, String ganador, long tiempo) {
		panelEstadoFinal.add(estadoFinal, BorderLayout.CENTER);
		lblGanador.setText(ganador);
		lblTiempoPartida.setText(Util.formatearTiempo(tiempo));
		update(getGraphics());
	}

	@Override
	public String infoComponente(Component c) {
		String res = null;
		if (c.equals(lblTiempoPartida) || c.equals(lblTiempoPartida2)) {
			res = "Tiempo de juego de la partida.";
			res += "\nNOTA: Este tiempo incluye 1 segundo de pausa entre cada movimiento con el objetivo de poder visualizar los movimientos realizados por el ordenador.";
		} else if (c.equals(lblEstadoFinal)) {
			res = "Estado final del juego.";
		} else if (c.equals(lblGanador)) {
			res = "Ganador de la partida.";
		} else if (c.equals(panelEstadoFinal)) {
			res = "Estado final de la partida.";
		} else if (c.equals(textAreaInfoPartida)) {
			res = "Proporciona información detallada del estado del juego final.";
		}
		return res;
	}

	@Override
	public void actualizarInfo(String info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarControladorInformacion(MouseListener ctr) {
		lblTiempoPartida.addMouseListener(ctr);
		lblTiempoPartida2.addMouseListener(ctr);
		lblEstadoFinal.addMouseListener(ctr);
		lblGanador.addMouseListener(ctr);
		panelEstadoFinal.addMouseListener(ctr);
		textAreaInfoPartida.addMouseListener(ctr);
	}
	
	public void actualizarInfoPartida(String info) {
		textAreaInfoPartida.setText(info);
		textAreaInfoPartida.setCaretPosition(0);
	}
	
}
