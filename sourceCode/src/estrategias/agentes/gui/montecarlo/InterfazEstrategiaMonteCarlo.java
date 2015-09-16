package estrategias.agentes.gui.montecarlo;

import entornointeractivo.gui.InterfazEstrategia;
import entornointeractivo.gui.estrategias.ControladorEstrategia;
import entornointeractivo.gui.interfaces.PanelConfiguracionEstrategia;
import estrategias.agentes.Jugador;
import estrategias.agentes.montecarlo.JugadorMonteCarlo;
import estrategias.agentes.montecarlo.JugadorMonteCarloEstadisticas;
import juegos.EstadoJuego;

public class InterfazEstrategiaMonteCarlo implements InterfazEstrategia{

	private static final String NOMBRE = "Monte-Carlo";
	
	@Override
	public String nombre() {
		return NOMBRE;
	}

	@Override
	public PanelConfiguracionEstrategia panelConfiguracion(EstadoJuego estadoJuego, ControladorEstrategia ctrEstrategia) {
		return new PanelMontecarlo(estadoJuego, ctrEstrategia);
	}

	@Override
	public Jugador estrategia(EstadoJuego e) {
		return new JugadorMonteCarloEstadisticas(JugadorMonteCarlo.N_SIMULACIONES);
	}

	@Override
	public String informacion() {
		String i = "Estrategia que emplea el método de Monte-Carlo para determinar el mejor movimiento.";
		i += "\nEste jugador realiza un número determinado de simulaciones para decidir el movimiento a realizar.";
		i += "\nPermite incluir un árbol de búsqueda para almacenar la información de los nodos visitados durante las simulaciones, ";
		i += " (Monte-Carlo Tree Search). Este árbol de búsqueda se mantiene de una partida a otra.";
		return i;
	}

}
