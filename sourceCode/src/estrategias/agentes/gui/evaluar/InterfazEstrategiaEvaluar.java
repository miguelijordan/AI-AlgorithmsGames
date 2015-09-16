package estrategias.agentes.gui.evaluar;

import juegos.EstadoJuego;
import entornointeractivo.gui.InterfazEstrategia;
import entornointeractivo.gui.estrategias.ControladorEstrategia;
import entornointeractivo.gui.interfaces.PanelConfiguracionEstrategia;
import estrategias.agentes.Jugador;

public class InterfazEstrategiaEvaluar implements InterfazEstrategia {
	
	// CONSTANTES
	private static final String NOMBRE_ESTRATEGIA = "Evaluador heurístico";
	
	@Override
	public String nombre() {
		return NOMBRE_ESTRATEGIA;
	}

	@Override
	public PanelConfiguracionEstrategia panelConfiguracion(EstadoJuego estadoJuego, ControladorEstrategia ctrEstrategia) {
		return new PanelEvaluar(estadoJuego, ctrEstrategia);
	}

	@Override
	public Jugador estrategia(EstadoJuego e) {
		return null;
	}

	@Override
	public String informacion() {
		String i = "Jugador capaz de evaluar heurísticamente los estados de un juego.";
		i += "\nDada una situación, considera todos los movimientos inmediatos, los evalúa heurísticamente y escoge el mejor.";
		return i;
	}
}
