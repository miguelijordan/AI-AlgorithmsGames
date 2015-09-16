package entornointeractivo.gui.interfaces;

import java.awt.Component;
import java.awt.event.MouseListener;

/**
 * Intefaz que deben implementar las interfaces de usuario (GUIs) para proporcionar información de ayuda.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 6/12/2011
 *
 */
public interface InformacionAyuda {

	/**
	 * Devuelve la información de ayuda sobre el componente indicado.
	 * 
	 * @param c	Componente.
	 * @return	Información de ayuda sobre el componente dado.
	 */
	public String infoComponente(Component c);
	
	/**
	 * Actualiza la información del componente activo en este momento.
	 * 
	 * @param info	Información.
	 */
	public void actualizarInfo(String info);
	
	/**
	 * Registra un controlador específico para el sistema de presentación de la ayuda.
	 * 
	 * @param ctr	Controlador.
	 */
	public void registrarControladorInformacion(MouseListener ctr);
	
}
