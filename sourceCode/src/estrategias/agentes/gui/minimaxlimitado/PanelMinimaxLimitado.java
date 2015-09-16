package estrategias.agentes.gui.minimaxlimitado;

import entornointeractivo.gui.estrategias.ControladorEstrategia;
import entornointeractivo.gui.estrategias.PanelSeleccionEvaluador;
import entornointeractivo.gui.interfaces.PanelConfiguracionEstrategia;
import estrategias.agentes.Jugador;
import estrategias.agentes.alfabeta.JugadorAlfaBetaLimitadoEstadisticas;
import estrategias.agentes.minimax.JugadorMinimaxLimitadoEstadisticas;
import estrategias.agentes.tablatransposicion.JugadorAlfaBetaLimitadoTablaTransposicionEstadisticas;
import estrategias.agentes.tablatransposicion.JugadorMinimaxLimitadoTablaTransposicionEstadisticas;

import heuristicos.evaluadores.Evaluador;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import juegos.EstadoJuego;

public class PanelMinimaxLimitado extends PanelConfiguracionEstrategia {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTiempoLimite;
	private JCheckBox chckbxAlfaBeta;
	private JCheckBox chckbxTablaTransposicion;
	private PanelSeleccionEvaluador panelSeleccionEvaluador;
	private JLabel lblTiempoLimite;
	
	/**
	 * Create the panel.
	 */
	public PanelMinimaxLimitado(EstadoJuego estadoJuego, ControladorEstrategia ctrEstrategia) {
		super(estadoJuego, ctrEstrategia);
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelEvaluador = new JPanel();
		panelEvaluador.setLayout(new BorderLayout(0, 0));
		panelSeleccionEvaluador = new PanelSeleccionEvaluador(estadoJuego, ctrEstrategia);
		panelEvaluador.add(panelSeleccionEvaluador, BorderLayout.CENTER);
		add(panelEvaluador, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		
		lblTiempoLimite = new JLabel("Tiempo límite de búsqueda");
		panel_1.add(lblTiempoLimite);
		
		txtTiempoLimite = new JTextField();
		txtTiempoLimite.setText("1");
		panel_1.add(txtTiempoLimite);
		txtTiempoLimite.setColumns(4);
		
		JLabel lblSegundos = new JLabel("segundos");
		panel_1.add(lblSegundos);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		
		chckbxAlfaBeta = new JCheckBox("Incluir poda alfa-beta");
		panel_2.add(chckbxAlfaBeta);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		
		chckbxTablaTransposicion = new JCheckBox("Incluir tabla de transposición");
		panel_3.add(chckbxTablaTransposicion);

	}

	@Override
	public void registrarControladorInformacion(MouseListener ctr) {
		lblTiempoLimite.addMouseListener(ctr);
		txtTiempoLimite.addMouseListener(ctr);
		chckbxAlfaBeta.addMouseListener(ctr);
		chckbxTablaTransposicion.addMouseListener(ctr);
		panelSeleccionEvaluador.registrarControladorInformacion(ctr);
	}
	

	@Override
	public String infoComponente(Component c) {
		String res = null;
		
		if (c.equals(txtTiempoLimite) || c.equals(lblTiempoLimite)) {
			res = "Tiempo límite de búsqueda en el árbol de juego. En segundos.";
		} else if (c.equals(chckbxAlfaBeta)) {
			res = "Incluye la poda alfa-beta a la estrategia.";
		} else if (c.equals(chckbxTablaTransposicion)) {
			res = "Incluye una tabla de transposición.";
		} else {
			res = panelSeleccionEvaluador.infoComponente(c);
		}
		return res;
	}

	@Override
	public void actualizarInfo(String info) {
		// TODO Auto-generated method stub
	}

	@Override
	public Jugador estrategia() {
		int tiempoLimite = 1;
		Evaluador evaluador = panelSeleccionEvaluador.getEvaluador(); // COGER EL EVALUADOR.
		
		try {
			tiempoLimite = Integer.parseInt(this.txtTiempoLimite.getText());
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(new JFrame(), "Hay un error en los parámetros.");
			return null;
		}
		if (!controlErrores(tiempoLimite)) {
			return null;
		}
		if (chckbxAlfaBeta.isSelected() && chckbxTablaTransposicion.isSelected()) {
			return new JugadorAlfaBetaLimitadoTablaTransposicionEstadisticas(evaluador, tiempoLimite);
		} else if (chckbxAlfaBeta.isSelected()) {
			return new JugadorAlfaBetaLimitadoEstadisticas(evaluador, tiempoLimite);
		} else if (chckbxTablaTransposicion.isSelected()) {
			return new JugadorMinimaxLimitadoTablaTransposicionEstadisticas(evaluador, tiempoLimite);
		} else {
			return new JugadorMinimaxLimitadoEstadisticas(evaluador, tiempoLimite);
		}
	}
	
	/**
	 * Control de errores para los parámetros.
	 * 
	 * @param nFilas			Número de filas.
	 * @param nColumnas			Número de columnas.
	 * @param longGanadora		Longitud ganadora.
	 * @return					Verdadero si todos los parámetros son correctos, falso en caso contrario.
	 */
	private boolean controlErrores(int tiempoLimite) {
		boolean ok = true;
		if (tiempoLimite < 1) {
			JOptionPane.showMessageDialog(new JFrame(), "Tiempo límite incorrecto. Se expresa en segundos y debe ser mayor o igual a 1.");
			ok = false;
		}
		return ok;
	}

	/*@Override
	public void registrarControlador(ActionListener ctr) {
		
	}*/
}
