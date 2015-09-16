package estrategias.agentes.gui.minimax;

import entornointeractivo.gui.InterfazEstrategia;
import entornointeractivo.gui.estrategias.ControladorEstrategia;
import entornointeractivo.gui.interfaces.PanelConfiguracionEstrategia;
import estrategias.agentes.Jugador;
import juegos.EstadoJuego;

public class InterfazEstrategiaMinimax implements InterfazEstrategia {
	
	// CONSTANTES
	private static final String NOMBRE_ESTRATEGIA = "Minimax";
	
	@Override
	public String nombre() {
		return NOMBRE_ESTRATEGIA;
	}
	
	@Override
	public PanelConfiguracionEstrategia panelConfiguracion(EstadoJuego estadoJuego, ControladorEstrategia ctrEstrategia) {
		return new PanelMinimax(estadoJuego, ctrEstrategia);
	}

	@Override
	public Jugador estrategia(EstadoJuego e) {
		return null;
	}

	@Override
	public String informacion() {
		String i = "Estrategia minimax con límite de profundidad en la búsqueda.";
		i += "\nEste jugador evalua los estados de un juego hasta una determinada profundidad.";
		i += "\nPermite incluir una poda alfa-beta para reducir el tiempo de búsqueda y";
		i += " una tabla de transposición para almacenar la información sobre los estados visitados.";
		return i;
	}

}
