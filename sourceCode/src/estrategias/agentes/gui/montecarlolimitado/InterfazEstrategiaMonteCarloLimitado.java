package estrategias.agentes.gui.montecarlolimitado;

import entornointeractivo.gui.InterfazEstrategia;
import entornointeractivo.gui.estrategias.ControladorEstrategia;
import entornointeractivo.gui.interfaces.PanelConfiguracionEstrategia;
import estrategias.agentes.Jugador;
import estrategias.agentes.montecarlo.JugadorMonteCarloLimitadoEstadisticas;
import juegos.EstadoJuego;

public class InterfazEstrategiaMonteCarloLimitado implements InterfazEstrategia{

	private static final String NOMBRE = "Monte-Carlo Limitado";
	
	@Override
	public String nombre() {
		return NOMBRE;
	}

	@Override
	public PanelConfiguracionEstrategia panelConfiguracion(EstadoJuego estadoJuego, ControladorEstrategia ctrEstrategia) {
		return new PanelMontecarloLimitado(estadoJuego, ctrEstrategia);
	}

	@Override
	public Jugador estrategia(EstadoJuego e) {
		return new JugadorMonteCarloLimitadoEstadisticas(1);
	}

	@Override
	public String informacion() {
		String i = "Estrategia que emplea el método de Monte-Carlo con límite de tiempo para determinar el mejor movimiento.";
		i += "\nEste jugador dispone de un tiempo límite para realizar simulaciones para decidir el movimiento a realizar.";
		i += "\nPermite incluir un árbol de búsqueda para almacenar la información de los nodos visitados durante las simulaciones, ";
		i += " (Monte-Carlo Tree Search). Este árbol de búsqueda se mantiene de una partida a otra.";
		return i;
	}

}
