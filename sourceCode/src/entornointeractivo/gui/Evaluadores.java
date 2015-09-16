package entornointeractivo.gui;

import heuristicos.evaluadores.gui.tablavalor.InterfazEvaluadorTablaValor;
import juegos.conectak.gui.entornointeractivo.InterfazEvaluadorCK;
import juegos.conectak.gui.entornointeractivo.InterfazEvaluadorNNCK;
import juegos.go.gui.entornointeractivo.InterfazEvaluadorGoPuntosCH;
import juegos.go.gui.entornointeractivo.InterfazEvaluadorGoPuntosJP;
import juegos.go.gui.entornointeractivo.InterfazEvaluadorGoTerritorios;
import juegos.go.gui.entornointeractivo.InterfazEvaluadorNNGo;

/**
 * Definición de los juegos disponibles en la aplicación.
 * 
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 30/11/2011
 *
 */
public enum Evaluadores {
	CONECTAK (new InterfazEvaluadorCK()),
	NNCK (new InterfazEvaluadorNNCK()),
	GO_JP (new InterfazEvaluadorGoPuntosJP()),
	GO_CH (new InterfazEvaluadorGoPuntosCH()),
	GO_TERRITORIOS (new InterfazEvaluadorGoTerritorios()),
	NNGO (new InterfazEvaluadorNNGo()),
	TABLA_VALOR (new InterfazEvaluadorTablaValor());
	
	private InterfazEvaluador interfaz;
	
	Evaluadores(InterfazEvaluador i) {
		interfaz = i;
	}

	/**
	 * @return the interfaz
	 */
	public InterfazEvaluador getInterfaz() {
		return interfaz;
	}

	public String toString() {
		return interfaz.nombre();
	}

}