package juegos.conectak.juego;
import juegos.*;
import juegos.util.Ficha;
import juegos.util.Movimiento;
import juegos.util.Tablero;

/**
 * Tablero para el juego del Conecta-K.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 18/08/2011
 * 
 */
public class TableroCK extends Tablero {
	
	/**
	 * Crea un tablero de dimensiones nFil x nCol con las casillas inicialmente vacías.
	 * 
	 * @param nFil Número de filas del tablero.
	 * @param nCol Número de columnas del tablero.
	 * @param ini  Contenido de las casillas vacías.
	 */
	public TableroCK(int nFil, int nCol, Ficha ini) {
		super(nFil, nCol, ini);
	}
	
	/**
	 * Crea un nuevo tablero copia del tablero proporcionado.
	 * 
	 * @param tab	Tablero a partir del cuál se construirá uno nuevo.
	 */
	public TableroCK(Tablero tab) {
		super(tab);
	}
	
	/**
	 * Inserta la ficha en la fila adecuada al soltarla por la columna c.
	 * Además devuelve el movimiento realizado.
	 * 
	 * @param c		Columna.
	 * @param ficha	Ficha.
	 * 
	 * @return Movimiento realizado.
	 */
	public Movimiento soltarFicha(int c, Ficha ficha) {
		int f = 0;
		int nFilas = nFilas();
		
		while (f < nFilas && contenido(f,c).equals(getPVacia())) {
			f++;
		}
		if (f != 0) {
			ponerFicha(f-1,c,ficha);
			return new Movimiento(f-1, c);
		} else {
			return null;
		}
	}
	
	/**
	 * Indica si la columna c está libre en el tablero.
	 * 
	 * @param c Columna.
	 * @return	Verdadero si es posible soltar alguna ficha en la columna c.
	 */
	public boolean columnaLibre(int c) {
		return contenido(0,c).equals(getPVacia()); 
	}
	
	/**
	 * Comprueba si se han podido conectar k fichas en el tablero a partir de una posición.
	 * 
	 * @param f 	Fila.
	 * @param c 	Columna.
	 * @param df	Dirección fila.
	 * @param dc	Dirección columna.
	 * @return		Número de fichas iguales conectadas con (f,c) en la dirección df, dc.
	 */
	public int conectadas(int f, int c, int df, int dc) {
		Ficha ficha = contenido(f,c);
		return contar(f,c,df,dc,ficha) + contar(f,c,-df,-dc,ficha) - 1;
	}
	
	/**
	 * Comprueba si se han podido conectar k fichas en el tablero en un dirección concreta.
	 * 
	 * @param f		Fila.
	 * @param c		Columna.
	 * @param df	Dirección fila.
	 * @param dc	Diracción columna.
	 * @param ficha	Ficha.
	 * @return		Número de fichas iguales conectadas con (f,c) en la dirección df, dc.
	 */
	private int contar(int f, int c, int df, int dc, Ficha ficha) {
		int ac = 0;
		
		while (posValida(f,c) && contenido(f,c).equals(ficha)) {
			ac++;
			f = f + df;
			c = c + dc;
		}
		return ac;
	}
	
	@Override
	public String toString() {
		String res = new String();
		
		for (int f = 0; f < nFilas(); f++) {
			for (int c = 0; c < nColumnas(); c++) {
				res = res.concat(contenido(f,c).toString()).concat(" ");
			}
			res = res.concat("\n"); 
		}
		for (int c = 0; c < nColumnas(); c++) {
			res = res.concat("==");
		}
		res = res.substring(0, res.length()-1);
		res = res.concat("\n");
		for (int c = 0; c < nColumnas(); c++) {
			res = res.concat(String.valueOf(c)).concat(" ");
		}
		res = res.concat("\n");
		return res;
	}
}
