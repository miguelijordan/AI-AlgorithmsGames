package entornointeractivo.gui;

import entornointeractivo.gui.estrategias.ControladorEstrategia;
import entornointeractivo.gui.interfaces.PanelConfiguracionEstrategia;
import estrategias.agentes.Jugador;
import juegos.EstadoJuego;

/**
 * Interfaz que deben implementar todas las estrategias para la interfaz de usuario. 
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 14/12/2011
 *
 */
public interface InterfazEstrategia {

	/**
	 * 
	 * @return Nombre de la estrategia.
	 */
	public String nombre();
	
	/**
	 * Panel para configurar las distintas opciones de la estrategia.
	 * Se proporciona el estado actual del juego para el caso en el que se necesiten los parámetros del mismo.
	 * 
	 * @param estadoJuego 	Estado actual del juego.
	 * @return				Panel de configuración de la estrategia.
	 */
	public PanelConfiguracionEstrategia panelConfiguracion(EstadoJuego estadoJuego, ControladorEstrategia ctrEstrategia);
	
	/**
	 * Estrategia por defecto.
	 * Se proporciona el estado del juego para los parámetros necesarios.
	 * 
	 * @param e		Estado del juego.
	 * @return		Estrategia por defecto.
	 */
	public Jugador estrategia(EstadoJuego e);
	
	/**
	 * Controlador del juego. Controla el desarrollo de la partida e interactúa con el panel de juego.
	 * 
	 * @return	Controlador del juego.
	 */
//	public ControladorJuego controladorJuego();

	/**
	 * Proporciona una descripción de la estrategia que se mostrará en la interfaz de usuario.
	 * 
	 * @return	Información de la estrategia.
	 */
	public String informacion();

}
