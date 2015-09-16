package juegos.go.gui.entornointeractivo;

import heuristicos.evaluadores.Evaluador;
import entornointeractivo.gui.InterfazEvaluador;
import entornointeractivo.gui.interfaces.PanelConfiguracionEvaluador;
import juegos.EstadoJuego;
import juegos.go.heuristicos.EvaluadorNNGo;
import juegos.go.juego.Go;


public class InterfazEvaluadorNNGo implements InterfazEvaluador {

	// CONSTANTES
	private static final String NOMBRE_EVALUADOR = "Evaluador Red Neuronal Go";
	public static final int N_NEURONAS_INTERMEDIAS = 9;
	public static final double TASA_APRENDIZAJE = 0.1;
	public static final double MOMENTO = 0.8;
	
	@Override
	public String nombre() {
		return NOMBRE_EVALUADOR;
	}

	@Override
	public PanelConfiguracionEvaluador panelConfiguracion() {
		return new PanelConfiguracionNNGo();
	}

	@Override
	public Evaluador evaluador(EstadoJuego e) {
		Go go = (Go) e;
		return new EvaluadorNNGo(go.tablero().nFilas(), go.tablero().nColumnas(), N_NEURONAS_INTERMEDIAS);
	}
	
	@Override
	public Class<? extends EstadoJuego> claseEstadoJuego() {
		return Go.class;
	}

	@Override
	public boolean entrenable() {
		return true;
	}
	
	@Override
	public String informacion() {
		String i = "Red neuronal multicapa para el juego del Go,";
		i += " entrenable empleando aprendizaje con refuerzo mediante diferencias temporales.";
		return i;
	}
}
