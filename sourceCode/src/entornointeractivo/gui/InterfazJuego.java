package entornointeractivo.gui;

import entornointeractivo.gui.interfaces.ControladorJuego;
import entornointeractivo.gui.interfaces.PanelJuego;
import entornointeractivo.gui.interfaces.PanelOpcionesJuego;
import entornointeractivo.gui.jugar.ControladorPantallaJuego;
import estrategias.agentes.Jugador;
import juegos.EstadoJuego;

/**
 * Interfaz que deben implementar todos los juegos para la interfaz de usuario. 
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 6/12/2011
 *
 */
public interface InterfazJuego {

	/**
	 * 
	 * @return Nombre del juego.
	 */
	public String nombre();
	
	/**
	 * Panel para configurar las distintas opciones que permita cambiar el juego.
	 * 
	 * @return	Panel de opciones del juego.
	 */
	public PanelOpcionesJuego panelOpciones();
	
	/**
	 * Panel de juego donde se desarrolla la partida (tablero de juego).
	 * 
	 * params estadoJuego	Estado del juego.
	 * @return				Panel de juego.
	 */
	public PanelJuego panelJuego(EstadoJuego estadoJuego);
	
	/**
	 * Controlador del juego. Controla el desarrollo de la partida e interactúa con el panel de juego.
	 * 
	 * @return	Controlador del juego.
	 */
	public ControladorJuego controladorJuego(EstadoJuego estadoJuego, Jugador jug1, Jugador jug2, PanelJuego p, ControladorPantallaJuego ctrPantallaJuego);
	
	/**
	 * Estado inicial por defecto del juego, sin cambiar ninguna configuración del mismo.
	 * 
	 * @return	Estado inicial por defecto del juego.
	 */
	public EstadoJuego estadoJuego();
	
	/**
	 * Proporciona una estrategia humana en la que un jugador humano decide los movimientos.
	 * Este jugador proporciona los movimientos mediante el teclado, pero no debe confundirse con el controlador del juego,
	 * que es en definitiva quien controla los movimientos en la interfaz gráfica.
	 * 
	 * @return	Estrategia humana
	 */
	public Jugador estrategiaHumana();
	
	/**
	 * Proporciona información básica sobre el juego.
	 * (Parámetros básicos del juego como el tamaño del tablero,...).
	 * 
	 * @param e	Estado del juego.
	 * @return	Información del juego.
	 */
	public String informacion(EstadoJuego e);
	
	/**
	 * Proporciona información en detalle sobre el estado del juego.
	 * (Último movimiento, puntuación de los jugadores,...).
	 * 
	 * @param e	Estado del juego.
	 * @return	Información del juego.
	 */
	public String informacionDetalle(EstadoJuego e);
}
