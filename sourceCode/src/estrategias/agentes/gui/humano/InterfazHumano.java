package estrategias.agentes.gui.humano;

import entornointeractivo.gui.InterfazEstrategia;
import entornointeractivo.gui.estrategias.ControladorEstrategia;
import entornointeractivo.gui.interfaces.PanelConfiguracionEstrategia;
import estrategias.agentes.Jugador;
import juegos.EstadoJuego;

public class InterfazHumano implements InterfazEstrategia {

	private final static String NOMBRE = "Humano";
	
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
		return null;
	}

	@Override
	public String informacion() {
		String i = "Representa un jugador humano.";
		i += "\nPide el movimiento a realizar mediante un dispositivo de entrada como el ratón o el teclado.";
		i += "\nEste jugador depende del juego seleccionado.";
		return i;
	}
	


}
