package entornointeractivo.gui.interfaces;

import entornointeractivo.gui.estrategias.ControladorEstrategia;

import heuristicos.evaluadores.Evaluador;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import juegos.EstadoJuego;

public abstract class PanelConfiguracionEvaluador extends JPanel implements InformacionAyuda {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ControladorEstrategia ctrEstrategia;
	
	
	public abstract Evaluador evaluador(EstadoJuego e);
	
	/**
	 * Registra el controlador en los componentes de la interfaz que interactuan con el usuario.
	 * 
	 * @param ctr	Controlador.
	 */
	public abstract void registrarControlador(ActionListener ctr);
	
}
