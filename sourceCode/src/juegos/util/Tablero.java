package juegos.util;

/**
 * Representación de un tablero genérico para los juegos que utilicen uno.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 07/08/2011
 *
 */
public class Tablero {

	/**
	 * Matriz que representa las casillas del tablero.
	 */
	private Ficha[][] matriz;
	
	/**
	 * Contenido de una posición vacía.
	 */
	private Ficha pVacia;
	
	/**
	 * Crea un tablero de dimensiones nFil x nCol con las casillas inicialmente vacías.
	 * 
	 * @param nFil Número de filas del tablero.
	 * @param nCol Número de columnas del tablero.
	 * @param ini  Contenido de las casillas vacías.
	 */
	public Tablero(int nFil, int nCol, Ficha ini) {
		this.matriz = new Ficha[nFil][nCol];
		this.pVacia = ini;
		inicializar(pVacia);
	}
	
	/**
	 * Crea un nuevo tablero copia del tablero proporcionado.
	 * 
	 * @param tab Tablero.
	 */
	public Tablero(Tablero tab) {
		this.pVacia = tab.getPVacia();
		this.matriz = new Ficha[tab.nFilas()][tab.nColumnas()];
		for (int f = 0; f < tab.nFilas(); f++) {
			System.arraycopy(tab.matriz[f], 0, matriz[f], 0, tab.nColumnas());
		}
	}
	
	/**
	 * Inicializa todas las posiciones del tablero a la casilla vacía.
	 * 
	 * @param pVacia Contenido de una posición vacía.
	 */
	private void inicializar(Ficha pVacia) {
		for (int f = 0; f < nFilas(); f++) {
			for (int c = 0; c < nColumnas(); c++) {
				matriz[f][c] = pVacia;
			}
		}
	}
	
	/**
	 * @return Contenido de una posición vacía.
	 */
	public Ficha getPVacia() {
		return pVacia;
	}
	
	/**
	 * @return Número de filas del tablero.
	 */
	public int nFilas() {
		return matriz.length;
	}
	
	/**
	 * @return Número de columnas del tablero.
	 */
	public int nColumnas() {
		return matriz[0].length;
	}
	
	/**
	 * Devuelve el contenido de una posición del tablero.
	 * 
	 * @param f Fila.
	 * @param c Columna.
	 * @return	Contenido del tablero en la posición (f,c).
	 */
	public Ficha contenido(int f, int c) {
		return matriz[f][c];
	}
	
	/**
	 * Comprueba si una posición es válida.
	 * Una posición es válida si se encuentra dentro de las dimensiones del tablero.
	 * 
	 * @param f Fila.
	 * @param c Columna.
	 * @return	Verdadero si (f,c) es una posición válida del tablero.
	 */
	public boolean posValida(int f, int c) {
		return f >= 0 && f < nFilas() && c >= 0 && c < nColumnas();
	}
	
	/**
	 * Coloca la ficha en la posición (f,c) del tablero.
	 * 
	 * @param f		Fila.
	 * @param c		Columna.
	 * @param ficha	Ficha.
	 */
	public void ponerFicha(int f, int c, Ficha ficha) {
		matriz[f][c] = ficha;
	}
	
	@Override
	public String toString() {
		String res = new String();
		
		for (int f = 0; f < nFilas(); f++) {
			for (int c = 0; c < nColumnas(); c++) {
				res = res.concat(matriz[f][c].toString());
				res = res.concat(" ");
			}
			res = res.concat("\n"); 
		}
		return res;
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Tablero && this.toString().equals(((Tablero) o).toString());
	}
}