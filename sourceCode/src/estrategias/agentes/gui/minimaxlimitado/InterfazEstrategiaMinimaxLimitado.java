package estrategias.agentes.gui.minimaxlimitado;

import entornointeractivo.gui.InterfazEstrategia;
import entornointeractivo.gui.estrategias.ControladorEstrategia;
import entornointeractivo.gui.interfaces.PanelConfiguracionEstrategia;
import estrategias.agentes.Jugador;
import juegos.EstadoJuego;

public class InterfazEstrategiaMinimaxLimitado implements InterfazEstrategia {
	
	// CONSTANTES
	private static final String NOMBRE_ESTRATEGIA = "Minimax Limitado";
	
	private static PanelConfiguracionEstrategia panelConfiguracion;
	
	@Override
	public String nombre() {
		return NOMBRE_ESTRATEGIA;
	}

	@Override
	public PanelConfiguracionEstrategia panelConfiguracion(EstadoJuego estadoJuego, ControladorEstrategia ctrEstrategia) {
		return new PanelMinimaxLimitado(estadoJuego, ctrEstrategia);
	}

	@Override
	public Jugador estrategia(EstadoJuego e) {
		return null;
	}

	@Override
	public String informacion() {
		String i = "Estrategia minimax con límite de tiempo.";
		i += "\nEste jugador dispone de un tiempo limitado para realizar la evaluación minimax de los estados de un juego.";
		i += "\nPermite incluir una poda alfa-beta para reducir el tiempo de búsqueda y";
		i += " una tabla de transposición para almacenar la información sobre los estados visitados.";
		return i;
	}

}
