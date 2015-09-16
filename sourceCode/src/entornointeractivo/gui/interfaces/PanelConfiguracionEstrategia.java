package entornointeractivo.gui.interfaces;

import entornointeractivo.gui.estrategias.ControladorEstrategia;
import estrategias.agentes.Jugador;

import javax.swing.JPanel;

import juegos.EstadoJuego;

/**
 * Interfaz que deben implementar los paneles de configuración de cada tipo de jugador.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 17/11/2011
 *
 */
public abstract class PanelConfiguracionEstrategia extends JPanel implements InformacionAyuda {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ControladorEstrategia ctrEstrategia;
	private EstadoJuego estadoJuego;
	
	public PanelConfiguracionEstrategia(EstadoJuego e, ControladorEstrategia ctrEstrategia) {
		this.ctrEstrategia = ctrEstrategia;
		this.estadoJuego = e;
	}
	
	/**
	 * Registra el controlador en los componentes de la interfaz que interactúan con el usuario.
	 * 
	 * @param ctr	Controlador.
	 */
	//public abstract void registrarControlador(ActionListener ctr);
	
	/**
	 * Instancia de la estrategia seleccionada y configurada.
	 * 
	 * @return	Instancia de la estrategia seleccionada.
	 */
	public abstract Jugador estrategia();

	/**
	 * @return the estadoJuego
	 */
	public EstadoJuego getEstadoJuego() {
		return estadoJuego;
	}
	
}
