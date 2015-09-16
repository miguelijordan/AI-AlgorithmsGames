package Pruebas;

import juegos.conectak.heuristicos.MatrizPosibilidades;

/**
 * Prueba la matriz de posibilidades para un evaluador heurístico del ConectaK.
 * Muestra la matriz de posibilidades y el número de posibilidades.
 * 
 * @author José Miguel Horcas Aguilera
 *
 */
public class PruebaPosibilidades {

	private static final int N_FILAS = 6;
	private static final int N_COLUMNAS = 7;
	private static final int LONGITUD_GANADORA = 4;
	/**
	 * Ejecutable.
	 * 
	 * @param args	Argumentos.
	 */
	public static void main(String[] args) {
		MatrizPosibilidades matriz = new MatrizPosibilidades(N_FILAS, N_COLUMNAS, LONGITUD_GANADORA);
		
		for (int f = 0; f < N_FILAS; f++) {
			for (int c = 0; c < N_COLUMNAS; c++) {
				/*System.out.print("(");
				for (Integer i : matriz.posicion(f, c)) {
					System.out.print(i + " ");
				}
				System.out.print(")"   );*/
				System.out.print(matriz.posicion(f, c).size() + " ");
			}
			System.out.println();
		}
		System.out.println("num pos: " + matriz.numPosibilidades());
	}	
}
