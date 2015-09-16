package estrategias.agentes.gui.minimax;

import entornointeractivo.gui.estrategias.ControladorEstrategia;
import entornointeractivo.gui.estrategias.PanelSeleccionEvaluador;
import entornointeractivo.gui.interfaces.PanelConfiguracionEstrategia;
import estrategias.agentes.Jugador;
import estrategias.agentes.alfabeta.JugadorAlfaBetaEstadisticas;
import estrategias.agentes.minimax.JugadorMinimaxEstadisticas;
import estrategias.agentes.tablatransposicion.JugadorAlfaBetaTablaTransposicionEstadisticas;
import estrategias.agentes.tablatransposicion.JugadorMinimaxTablaTransposicionEstadisticas;

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

public class PanelMinimax extends PanelConfiguracionEstrategia {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtProfMax;
	private JCheckBox chckbxAlfaBeta;
	private JCheckBox chckbxTablaTransposicion;
	private PanelSeleccionEvaluador panelSeleccionEvaluador;
	private JLabel lblProfMax;
	
	/**
	 * Create the panel.
	 */
	public PanelMinimax(EstadoJuego estadoJuego, ControladorEstrategia ctrEstrategia) {
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
		
		lblProfMax = new JLabel("Profundidad máxima de búsqueda:");
		panel_1.add(lblProfMax);
		
		txtProfMax = new JTextField();
		txtProfMax.setText("3");
		panel_1.add(txtProfMax);
		txtProfMax.setColumns(4);
		
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
		lblProfMax.addMouseListener(ctr);
		txtProfMax.addMouseListener(ctr);
		chckbxAlfaBeta.addMouseListener(ctr);
		chckbxTablaTransposicion.addMouseListener(ctr);
		panelSeleccionEvaluador.registrarControladorInformacion(ctr);
	}
	

	@Override
	public String infoComponente(Component c) {
		String res = null;
		
		if (c.equals(txtProfMax) || c.equals(lblProfMax)) {
			res = "Profundidad máxima de búsqueda en el árbol de estados.";
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
		int profMax = 3;
		Evaluador evaluador = panelSeleccionEvaluador.getEvaluador(); // COGER EL EVALUADOR.
		
		try {
			profMax = Integer.parseInt(this.txtProfMax.getText());
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(new JFrame(), "Hay un error en los parámetros.");
			return null;
		}
		if (!controlErrores(profMax)) {
			return null;
		}
		if (chckbxAlfaBeta.isSelected() && chckbxTablaTransposicion.isSelected()) {
			return new JugadorAlfaBetaTablaTransposicionEstadisticas(evaluador, profMax);
		} else if (chckbxAlfaBeta.isSelected()) {
			return new JugadorAlfaBetaEstadisticas(evaluador, profMax);
		} else if (chckbxTablaTransposicion.isSelected()) {
			return new JugadorMinimaxTablaTransposicionEstadisticas(evaluador, profMax);
		} else {
			return new JugadorMinimaxEstadisticas(evaluador, profMax);
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
	private boolean controlErrores(int profMax) {
		boolean ok = true;
		if (profMax <= 0) {
			JOptionPane.showMessageDialog(new JFrame(), "Profundidad máxima incorrecta. Debe ser mayor o igual a 1.");
			ok = false;
		}
		return ok;
	}

	/*@Override
	public void registrarControlador(ActionListener ctr) {
		
	}*/
}
