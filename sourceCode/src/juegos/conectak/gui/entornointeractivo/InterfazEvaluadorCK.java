package juegos.conectak.gui.entornointeractivo;

import heuristicos.evaluadores.Evaluador;
import entornointeractivo.gui.InterfazEvaluador;
import entornointeractivo.gui.interfaces.PanelConfiguracionEvaluador;
import juegos.EstadoJuego;
import juegos.conectak.heuristicos.EvaluadorCK;
import juegos.conectak.juego.ConectaK;


public class InterfazEvaluadorCK implements InterfazEvaluador {

	// CONSTANTES
	private static final String NOMBRE_EVALUADOR = "Evaluador Conecta-K";
		
	@Override
	public String nombre() {
		return NOMBRE_EVALUADOR;
	}

	@Override
	public PanelConfiguracionEvaluador panelConfiguracion() {
		return null;
	}

	@Override
	public Evaluador evaluador(EstadoJuego e) {
		ConectaK ck = (ConectaK) e;
		return new EvaluadorCK(ck.tablero().nFilas(), ck.tablero().nColumnas(), ck.longGanadora());
	}

	@Override
	public Class<? extends EstadoJuego> claseEstadoJuego() {
		return ConectaK.class;
	}

	@Override
	public boolean entrenable() {
		return false;
	}

	@Override
	public String informacion() {
		String i = "Evaluador heurístico para el juego del ConectaK.";
		i += "\nEmplea una matriz de posibilidades para evaluar los estados del juego.";
		return i;
	}
}
