package entornointeractivo.gui.interfaces;


import javax.swing.JPanel;

import juegos.EstadoJuego;

public abstract class PanelJuego extends JPanel implements InformacionAyuda {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract void registrarControlador(ControladorJuego ctr);
	
	/**
	 * Proporciona un panel de juego inicializado con los parámetros del estado de juego proporcionado.
	 * 
	 * @param e	Estado del juego.
	 * @return	Panel de juego.
	 */
	public abstract JPanel panelJuegoEstado(EstadoJuego e);
}
