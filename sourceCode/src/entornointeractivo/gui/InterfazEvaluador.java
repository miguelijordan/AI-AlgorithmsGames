package entornointeractivo.gui;

import heuristicos.evaluadores.Evaluador;
import entornointeractivo.gui.interfaces.PanelConfiguracionEvaluador;
import juegos.EstadoJuego;

public interface InterfazEvaluador {
	/**
	 * 
	 * @return Nombre del evaluador.
	 */
	public String nombre();
	
	/**
	 * Panel para configurar las distintas opciones del evaluador heurístico.
	 * Si el evaluador no necesita configurar nada devolverá null.
	 * 
	 * @return	Panel de opciones del evaluador.
	 */
	public PanelConfiguracionEvaluador panelConfiguracion();
	
	/**
	 * Controlador del juego. Controla el desarrollo de la partida e interactúa con el panel de juego.
	 * 
	 * @return	Controlador del juego.
	 */
	//public ControladorJuego controladorJuego();
	
	/**
	 * Instancia del evaluador heurístico.
	 * 
	 * @return	Evaluador heurístico.
	 */
	public Evaluador evaluador(EstadoJuego e);
	
	/**
	 * Subclase de EstadoJuego de la que depende el evaluador heurístico.
	 * Si el evaluador es independiente del tipo de juego debe devolver null.
	 * 
	 * @return Clase que representa los estados de los juegos o null si el evaluador es independiente del tipo de juego.
	 */
	public Class<? extends EstadoJuego> claseEstadoJuego();
	
	/**
	 * Indica si el evaluador es entrenable mediante entrenamiento con refuerzo.
	 * 
	 * @return Verdadero si el evaluador puede entrenarse.
	 */
	public boolean entrenable();
	
	/**
	 * Proporciona una descripción del evaluador que se mostrará en la interfaz de usuario.
	 * 
	 * @return	Información del evaluador.
	 */
	public String informacion();
}
