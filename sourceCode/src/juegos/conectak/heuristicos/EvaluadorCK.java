package juegos.conectak.heuristicos;


import heuristicos.evaluadores.Evaluador;
import juegos.EstadoJuego;
import juegos.conectak.juego.ConectaK;
import juegos.util.Ficha;
import juegos.util.Tablero;

/**
 * Evaluador heurístico para el problema del Conecta-K.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 18/08/2011
 *
 */
public class EvaluadorCK implements Evaluador {

	/**
	 * Matriz de posibilidades.
	 */
	private MatrizPosibilidades posibilidades;

	/**
	 * Crea la matriz de posibilidades.
	 * 
	 * @param nFilas		Número de filas.
	 * @param nColumnas		Número de columnas.
	 * @param k				Longitud ganadora.
	 */
	public EvaluadorCK(int nFilas, int nColumnas, int k) {
		posibilidades = new MatrizPosibilidades(nFilas, nColumnas, k);
	}

	@Override
	public double evaluacion(EstadoJuego estado, Ficha fmax, Ficha fmin) {
		int n = posibilidades.numPosibilidades();
		Tablero tab = ((ConectaK)estado).tablero();	
		int[] posiMax = new int[n];
		int[] posiMin = new int[n];
		
		for (int i = 0; i < n; i++) {
			posiMax[i] = 1;
			posiMin[i] = 1;
		}
		
		for (int f = 0; f < tab.nFilas(); f++) {
			for (int c = 0; c < tab.nColumnas(); c++) {
				for (Integer op : posibilidades.posicion(f, c)) {
					if (tab.contenido(f, c).equals(fmin)) {
						posiMax[op] = 0;
					} else if (tab.contenido(f, c).equals(fmax)) {
						posiMin[op] = 0;
					}
				}
			}
		}
		return sumatorioArray(posiMax) - sumatorioArray(posiMin);
	}
	
	/**
	 * 
	 * @param a Array.
	 * @return	Suma de todos los elementos del array unidimensional a.
	 */
	private int sumatorioArray(int[] a) {
		int suma = 0;
		
		for (int i = 0; i < a.length; i++) {
			suma += a[i];
		}
		return suma;
	}
	
	@Override
	public String toString() {
		String res = "Evaluador heurístico para el juego del Conecta-K que";
		res += " emplea una matriz de posibilidades para determinar el mejor movimiento.";
		return res;
	}	
}
