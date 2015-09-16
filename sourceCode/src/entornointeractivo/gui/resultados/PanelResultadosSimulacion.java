package entornointeractivo.gui.resultados;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import entornointeractivo.gui.interfaces.InformacionAyuda;
import estrategias.agentes.estadisticas.Util;

public class PanelResultadosSimulacion extends JPanel implements InformacionAyuda {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JProgressBar progressBarJ1;
	private JProgressBar progressBarEmpate;
	private JProgressBar progressBarJ2;
	private JLabel lblPorcentajeJ1;
	private JLabel lblPorcentajeEmpate;
	private JLabel lblPorcentajeJ2;
	private JPanel panelBarras;
	private JPanel panelPorcentajes;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNPartidas2;
	private JLabel lblNPartidas;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel lblTiempoTotal;
	private JLabel lblTiempo;
	private JPanel panel_5;
	private JLabel lblTiempoPartida2;
	private JLabel lblTiempoPartida;
	private JLabel lblGanaElJugador1;
	private JLabel lblEmpate;
	private JLabel lblGanaElJugador2;

	/**
	 * Create the panel.
	 */
	public PanelResultadosSimulacion() {
		setBorder(new TitledBorder(new LineBorder(new Color(185, 209, 234)), "Resultados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(5, 0));
		
		panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(5, 0));
		
		JPanel panel = new JPanel();
		panel_1.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		lblGanaElJugador1 = new JLabel("Gana el jugador 1");
		panel.add(lblGanaElJugador1);
		
		lblEmpate = new JLabel("Empate");
		panel.add(lblEmpate);
		
		lblGanaElJugador2 = new JLabel("Gana el jugador 2");
		panel.add(lblGanaElJugador2);
		
		panelBarras = new JPanel();
		panel_1.add(panelBarras, BorderLayout.CENTER);
		panelBarras.setLayout(new GridLayout(3, 1, 0, 0));
		
		progressBarJ1 = new JProgressBar();
		panelBarras.add(progressBarJ1);
		progressBarJ1.setToolTipText("");
		
		progressBarEmpate = new JProgressBar();
		panelBarras.add(progressBarEmpate);
		
		progressBarJ2 = new JProgressBar();
		panelBarras.add(progressBarJ2);
		
		panelPorcentajes = new JPanel();
		panel_1.add(panelPorcentajes, BorderLayout.EAST);
		panelPorcentajes.setLayout(new GridLayout(3, 1, 0, 0));
		
		lblPorcentajeJ1 = new JLabel("0 %");
		panelPorcentajes.add(lblPorcentajeJ1);
		
		lblPorcentajeEmpate = new JLabel("0 %");
		panelPorcentajes.add(lblPorcentajeEmpate);
		
		lblPorcentajeJ2 = new JLabel("0 %");
		panelPorcentajes.add(lblPorcentajeJ2);
		
		panel_2 = new JPanel();
		add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_3);
		
		lblNPartidas2 = new JLabel("Nº partidas jugadas:");
		panel_3.add(lblNPartidas2);
		
		lblNPartidas = new JLabel("");
		panel_3.add(lblNPartidas);
		
		panel_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_4);
		
		lblTiempo = new JLabel("Tiempo total:");
		panel_4.add(lblTiempo);
		
		lblTiempoTotal = new JLabel("");
		panel_4.add(lblTiempoTotal);
		
		panel_5 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_5.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_5);
		
		lblTiempoPartida2 = new JLabel("Tiempo por partida:");
		panel_5.add(lblTiempoPartida2);
		
		lblTiempoPartida = new JLabel("");
		panel_5.add(lblTiempoPartida);

	}
	
	public void actualizarResultados(double[] res, int nPartidas, long tiempo) {
		lblNPartidas.setText(String.valueOf(nPartidas));
		lblTiempoTotal.setText(Util.formatearTiempo(tiempo));
		lblTiempoPartida.setText(Util.formatearTiempo(tiempo/nPartidas));
		progressBarJ1.setValue((int)res[0]);
		progressBarEmpate.setValue((int)res[1]);
		progressBarJ2.setValue((int)res[2]);
		lblPorcentajeJ1.setText(String.valueOf((int)res[0]) + " %");
		lblPorcentajeEmpate.setText(String.valueOf((int)res[1]) + " %");
		lblPorcentajeJ2.setText(String.valueOf((int)res[2]) + " %");
		update(getGraphics());
	}

	@Override
	public String infoComponente(Component c) {
		String res = null;
		if (c.equals(lblGanaElJugador1) || c.equals(lblPorcentajeJ1) || c.equals(progressBarJ1)) {
			res = "Porcentaje de partidas ganadas por el jugador 1.";
		} else if (c.equals(lblGanaElJugador2) || c.equals(lblPorcentajeJ2) || c.equals(progressBarJ2)) {
			res = "Porcentaje de partidas ganadas por el jugador 2.";
		} else if (c.equals(lblEmpate) || c.equals(lblPorcentajeEmpate) || c.equals(progressBarEmpate)) {
			res = "Porcentaje de partidas terminadas en empate.";
		} else if (c.equals(lblNPartidas) || c.equals(lblNPartidas2)) {
			res = "Número de partidas jugadas.";
		} else if (c.equals(lblTiempo) || c.equals(lblTiempoTotal)) {
			res = "Tiempo total empleado en la simulación.";
		} else if (c.equals(lblTiempoPartida) || c.equals(lblTiempoPartida2)) {
			res = "Tiempo medio por partida.";
		}
		return res;
	}

	@Override
	public void actualizarInfo(String info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarControladorInformacion(MouseListener ctr) {
		lblGanaElJugador1.addMouseListener(ctr);
		lblPorcentajeJ1.addMouseListener(ctr);
		progressBarJ1.addMouseListener(ctr);
		lblGanaElJugador2.addMouseListener(ctr);
		lblPorcentajeJ2.addMouseListener(ctr);
		progressBarJ2.addMouseListener(ctr);
		lblEmpate.addMouseListener(ctr);
		lblPorcentajeEmpate.addMouseListener(ctr);
		progressBarEmpate.addMouseListener(ctr);
		lblNPartidas.addMouseListener(ctr);
		lblNPartidas2.addMouseListener(ctr);
		lblTiempoPartida.addMouseListener(ctr);
		lblTiempoPartida2.addMouseListener(ctr);
		lblTiempo.addMouseListener(ctr);
		lblTiempoTotal.addMouseListener(ctr);
	}
}
