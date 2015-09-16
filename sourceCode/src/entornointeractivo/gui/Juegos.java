package entornointeractivo.gui;

import juegos.conectak.gui.entornointeractivo.InterfazJuegoCK;
import juegos.go.gui.entornointeractivo.InterfazJuegoGo;

/**
 * Definición de los juegos disponibles en la aplicación.
 * 
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 30/11/2011
 *
 */
public enum Juegos {
	CONECTAK (new InterfazJuegoCK()),
	GO (new InterfazJuegoGo());
	
	private InterfazJuego interfaz;
	
	Juegos(InterfazJuego i) {
		interfaz = i;
	}

	/**
	 * @return the interfaz
	 */
	public InterfazJuego getInterfaz() {
		return interfaz;
	}

	public String toString() {
		return interfaz.nombre();
	}

}