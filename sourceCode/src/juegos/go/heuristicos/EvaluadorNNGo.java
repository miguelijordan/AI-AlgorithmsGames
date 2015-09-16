package juegos.go.heuristicos;

import heuristicos.evaluadores.EvaluadorNN;
import juegos.EstadoJuego;
import juegos.conectak.juego.ConectaK;
import juegos.go.juego.Go;
import juegos.util.Ficha;
import juegos.util.Tablero;

/**
 * Evaluador con red neuronal para el juego Go.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 10/10/2011
 *
 */
public class EvaluadorNNGo extends EvaluadorNN {
	
	/**
	 * Crea un evaluador con red neuronal para el Go.
	 * 
	 * @param nFilas				Número de filas del tablero.
	 * @param nColumnas				Número de columnas del tablero.
	 * @param nNeuronasIntermedias	Número de neuronas en la capa intermedia.
	 */
	public EvaluadorNNGo(int nFilas, int nColumnas, int nNeuronasIntermedias) {
		super(2*nFilas*nColumnas, 2, nNeuronasIntermedias);
	}

	public EvaluadorNNGo(int nFilas, int nColumnas, int nNeuronasIntermedias, double tasaAprendizaje, double momento) {
		super(2*nFilas*nColumnas, 2, nNeuronasIntermedias, tasaAprendizaje, momento);
	}

	/**
	 * Codifica cada posición del tablero mediante dos entradas a la red neuronal.
	 * La primera será 1 si hay una ficha del primer jugador y 0 en caso contrario.
	 * La segunda será 1 si hay una ficha del segundo jugador y 0 en caso contrario.
	 * Para un tablero de n x m se necesitan 2 x n x m entradas en la red neuronal.
	 * 
	 * @param e		Estado a codificar.
	 * @return		Codificación del estado (entrada de la red neuronal).
	 */
	public double[] codifica(EstadoJuego e) {
		Ficha fj1 = e.jug1() ? e.fichaActual() : e.fichaOtro();
		Ficha fj2 = e.jug1() ? e.fichaOtro() : e.fichaActual();
		Tablero tablero = ((Go) e).tablero();
		double[] entradaNN = new double[2*tablero.nFilas()*tablero.nColumnas()];
		int i = 0;
		for (int f = 0; f < tablero.nFilas(); f++) {
			for (int c = 0; c < tablero.nColumnas(); c++) {
				entradaNN[i] = tablero.contenido(f, c).equals(fj1) ? 1 : 0;
				entradaNN[i+1] = tablero.contenido(f, c).equals(fj2) ? 1 : 0;
				i = i + 2;
			}
		}
		return entradaNN;
	}
}
