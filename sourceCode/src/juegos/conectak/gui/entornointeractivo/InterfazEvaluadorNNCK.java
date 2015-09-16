package juegos.conectak.gui.entornointeractivo;

import heuristicos.evaluadores.Evaluador;
import entornointeractivo.gui.InterfazEvaluador;
import entornointeractivo.gui.interfaces.PanelConfiguracionEvaluador;
import juegos.EstadoJuego;
import juegos.conectak.heuristicos.EvaluadorNNCK;
import juegos.conectak.juego.ConectaK;


public class InterfazEvaluadorNNCK implements InterfazEvaluador {

	// CONSTANTES
	private static final String NOMBRE_EVALUADOR = "Evaluador Red Neuronal Conecta-K";
	public static final int N_NEURONAS_INTERMEDIAS = 1;
	public static final double TASA_APRENDIZAJE = 0.1;
	public static final double MOMENTO = 0.8;
	
	@Override
	public String nombre() {
		return NOMBRE_EVALUADOR;
	}

	@Override
	public PanelConfiguracionEvaluador panelConfiguracion() {
		return new PanelConfiguracionNNCK();
	}

	@Override
	public Evaluador evaluador(EstadoJuego e) {
		ConectaK ck = (ConectaK) e;
		return new EvaluadorNNCK(ck.tablero().nFilas(), ck.tablero().nColumnas(), N_NEURONAS_INTERMEDIAS);
	}
	
	@Override
	public Class<? extends EstadoJuego> claseEstadoJuego() {
		return ConectaK.class;
	}

	@Override
	public boolean entrenable() {
		return true;
	}
	
	@Override
	public String informacion() {
		String i = "Red neuronal multicapa para el juego del ConectaK,";
		i += " entrenable empleando aprendizaje con refuerzo mediante diferencias temporales.";
		return i;
	}
}
