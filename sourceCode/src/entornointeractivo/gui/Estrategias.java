package entornointeractivo.gui;

import estrategias.agentes.gui.aleatorio.InterfazEstrategiaAleatoria;
import estrategias.agentes.gui.evaluar.InterfazEstrategiaEvaluar;
import estrategias.agentes.gui.humano.InterfazHumano;
import estrategias.agentes.gui.minimax.InterfazEstrategiaMinimax;
import estrategias.agentes.gui.minimaxlimitado.InterfazEstrategiaMinimaxLimitado;
import estrategias.agentes.gui.montecarlo.InterfazEstrategiaMonteCarlo;
import estrategias.agentes.gui.montecarlolimitado.InterfazEstrategiaMonteCarloLimitado;


public enum Estrategias {
	HUMANO (new InterfazHumano()),
	ALEATORIO (new InterfazEstrategiaAleatoria()),
	EVALUAR (new InterfazEstrategiaEvaluar()),
	MINIMAX (new InterfazEstrategiaMinimax()),
	MINIMAX_LIMITADO (new InterfazEstrategiaMinimaxLimitado()),
	MONTECARLO (new InterfazEstrategiaMonteCarlo()),
	MONTECARLO_LIMITADO (new InterfazEstrategiaMonteCarloLimitado());
	
	private InterfazEstrategia interfaz;
	
	private Estrategias(InterfazEstrategia i) {
		interfaz = i;
	}

	/**
	 * @return the interfaz
	 */
	public InterfazEstrategia getInterfaz() {
		return interfaz;
	}

	public String toString() {
		return interfaz.nombre();
	}
	
}