package estrategias.agentes.gui.aleatorio;

import entornointeractivo.gui.InterfazEstrategia;
import entornointeractivo.gui.estrategias.ControladorEstrategia;
import entornointeractivo.gui.interfaces.PanelConfiguracionEstrategia;
import estrategias.agentes.Jugador;
import estrategias.agentes.aleatorio.JugadorAleatorioEstadisticas;
import juegos.EstadoJuego;

public class InterfazEstrategiaAleatoria implements InterfazEstrategia {

	private static final String NOMBRE = "Aleatorio";
	
	@Override
	public String nombre() {
		return NOMBRE;
	}

	@Override
	public PanelConfiguracionEstrategia panelConfiguracion(EstadoJuego estadoJuego, ControladorEstrategia ctrEstrategia) {
		return null;
	}

	@Override
	public Jugador estrategia(EstadoJuego e) {
		return new JugadorAleatorioEstadisticas();
	}

	@Override
	public String informacion() {
		return "Realiza movimientos aleatorios.";
	}
	
	

}
