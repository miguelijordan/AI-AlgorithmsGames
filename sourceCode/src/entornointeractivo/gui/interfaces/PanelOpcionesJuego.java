package entornointeractivo.gui.interfaces;


import java.awt.event.ActionListener;

import javax.swing.JPanel;

import juegos.EstadoJuego;

/**
 * Interfaz que deben implementar los paneles de configuración de cada tipo de juego.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 30/11/2011
 *
 */
public abstract class PanelOpcionesJuego extends JPanel implements InformacionAyuda {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Registra el controlador en los componentes de la interfaz que interactúan con el usuario.
	 * 
	 * @param ctr	Controlador.
	 */
	abstract public void registrarControlador(ActionListener ctr);
	
	/**
	 * Crea una instancia del juego (estado inicial) en función de los parámetros dados en la configuración del mismo.
	 * Devuelve null en caso de error en los parámetros.
	 * 
	 * @return	Estado inicial del juego.
	 */
	abstract public EstadoJuego estadoJuego();
	
}
