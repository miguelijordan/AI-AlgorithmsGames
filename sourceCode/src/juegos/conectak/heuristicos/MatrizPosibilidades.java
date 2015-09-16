package juegos.conectak.heuristicos;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa la matriz de posibilidades del tablero para el Conecta-K.
 * 
 * @author José Miguel Horcas Aguilera
 *
 */
public class MatrizPosibilidades {

	// ATRIBUTOS
	private Posibilidades[][] matriz;
	private int numPosibilidades;
	private int k;
	
	/**
	 * Clase interna Posibilidades.
	 * Representa las posibilidades en la que interviene una posición del tablero.
	 * 
	 * @author José Miguel Horcas Aguilera
	 *
	 */
	public class Posibilidades {
		// ATRIBUTOS
		private List<Integer> posicion;
		
		/**
		 * Constructor.
		 */
		public Posibilidades() {
			posicion = new ArrayList<Integer>();
		}

		/**
		 * Añade una posibilidad.
		 * 
		 * @param e	Posibilidad.
		 */
		public void add(int e) {
			posicion.add(e);
		}

		/**
		 * 
		 * @return Lista con todas las posibilidades.
		 */
		public List<Integer> getPosicion() {
			return posicion;
		}	
	}
	
	/**
	 * Constructor.
	 * 
	 * @param nFilas		Número de filas.
	 * @param nColumnas		Número de columnas.
	 * @param k				Longitud ganadora.
	 */
	public MatrizPosibilidades(int nFilas, int nColumnas, int k) {
		matriz = new Posibilidades[nFilas][nColumnas];
		for (int f = 0; f < nFilas; f++) {
			for (int c = 0; c < nColumnas; c++) {
				matriz[f][c] = new Posibilidades();		
			}
		}
		this.k = k;
		numPosibilidades = crearMatrizPosibilidades(); 
	}
	
	/**
	 * Crea la matriz de posibilidades.
	 * 
	 * @return	Número de posibilidades.
	 */
	private int crearMatrizPosibilidades() {
		int n = 0;
		for (int f = 0; f < matriz.length; f++) {
			for (int c = 0; c < matriz[0].length; c++) {
				if (marcaPosibilidades(f, c, 0, 1, n)) {	// Horizontal derecha.
					n++;
				}
				if (marcaPosibilidades(f, c, 1, 0, n)) {	// Vertical arriba.
					n++;
				}
				if (marcaPosibilidades(f, c, 1, 1, n)) {	// Diagonal derecha ascendente.
					n++;
				}
				if (marcaPosibilidades(f, c, -1, 1, n)) {	// Diagonal derecha descendente.
					n++;
				}
			}
		}
		return n;
	}
	
	/**
	 * 
	 * @return Número de posibilidades.
	 */
	public int numPosibilidades() {
		return numPosibilidades;
	}
	
	/**
	 * 
	 * @return Longitud ganadora.
	 */
	public int longGanadora() {
		return k;
	}
	
	/**
	 * 
	 * @param f	Fila.
	 * @param c	Columna.
	 * @return	Lista de posibilidades de la posición (f, c).
	 */
	public List<Integer> posicion(int f, int c) {
		return matriz[f][c].getPosicion();
	}
	
	/**
	 * Si puede, inserta 'clave' en las k casillas desde (f,c) en la dirección df,dc.
	 * 
	 * @param f			Fila.
	 * @param c			Columna.
	 * @param df		Dirección de la fila.
	 * @param dc		Dirección de la columna.
	 * @param clave		Valor a insertar.
	 * @return			Verdadero si es posible insertar 'clave' en las k casillas; falso en otro caso. 
	 */
	private boolean marcaPosibilidades(int f, int c, int df, int dc, int clave) {
		if (posValida(f+(k-1)*df, c+(k-1)*dc)) {
			marcaLinea(f, c, df, dc, k, clave);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Marca con 'clave' k posiciones de la matriz desde (f,c) en la dirección df,dc.
	 * 
	 * @param f			Fila.
	 * @param c			Columna.
	 * @param df		Dirección de la fila.
	 * @param dc		Dirección de la columna.
	 * @param k			Número de posiciones.
	 * @param clave		Valor a marcar.
	 */
	private void marcaLinea(int f, int c, int df, int dc, int k, int clave) {
		if (k != 0) {
			matriz[f][c].add(clave);
			marcaLinea(f+df, c+dc, df, dc, k-1, clave);
		}
	}
	
	/**
	 * Comprueba si la posición (f,c) de la matriz es válida.
	 * 
	 * @param f	Fila.
	 * @param c	Columna.
	 * @return	Verdadero si la posición (f,c) es válida; falso en otro caso.
	 */
	private boolean posValida(int f, int c) {
		return 0 <= f && f < matriz.length && 0 <= c && c < matriz[0].length;
	}
}
