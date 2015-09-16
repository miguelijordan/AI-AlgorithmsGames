package juegos.conectak.juego;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import juegos.*;
import juegos.util.Ficha;
import juegos.util.Movimiento;
import juegos.util.Tablero;


/**
 * Juego del Conecta-K con dos jugadores que usan fichas diferentes y se alternan en sus movimientos.
 * Esta clase representa los distintos estados en que puede encontrarse el juego del Conecta-K.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 18/08/2011
 *
 */
public class ConectaK implements EstadoJuego {

	/**
	 * Contenido de una posición vacía.
	 */
	public static final Ficha P_VACIA = new Ficha('_');
	
	/**
	 * Longitud ganadora.
	 */
	private int longGanadora;
	
	/**
	 * Número de movimientos realizados (iteraciones del juego).
	 */
	private int it;
	
	/**
	 * Tablero del juego.
	 */
	private TableroCK tablero;
	
	/**
	 * Último movimiento realizado.
	 */
	private Movimiento ultimoMov;
	
	/**
	 * ¿Le toca mover al jugador 1?
	 */
	private boolean jug1;
	
	/**
	 * Ficha del jugador 1.
	 */
	public static final Ficha ficha1 = new Ficha('X');
	
	/**
	 * Ficha del jugador 2.
	 */
	public static final Ficha ficha2 = new Ficha('O');
	
	/**
	 * Crea un estado inicial del juego (tablero vacío, le toca al primer jugador).
	 * 
	 * @param nFil 	Número de filas.
	 * @param nCol 	Número de columnas.
	 * @param k		Longitud ganadora.
	 */
	public ConectaK(int nFil, int nCol, int k) {
		longGanadora = k;
		it = 0;
		jug1 = true;
		tablero = new TableroCK(nFil, nCol, P_VACIA);
		ultimoMov = null;
	}
	
	/**
	 * Crea un nuevo estado a partir de los parámetros proporcionados.
	 * 
	 * @param it		Número de jugadas.
	 * @param jug1		¿Le toca jugar al primer jugador?
	 * @param tablero	Tablero.
	 * @param ultimoMov	Último movimiento realizado.
	 * @param k			Longitud ganadora.
	 */
	private ConectaK(int it, boolean jug1, TableroCK tablero, Movimiento ultimoMov, int k) {
		this.longGanadora = k;
		this.it = it;
		this.jug1 = jug1;
		this.tablero = tablero;
		this.ultimoMov = ultimoMov;
	}
	
	/**
	 * @return Longitud Ganadora.
	 */
	public int longGanadora() {
		return longGanadora;
	}
	
	/**
	 * @return Último movimiento realizado.
	 */
	public Movimiento ultimoMovimiento() {
		return ultimoMov;
	}
	
	@Override
	public Ficha fichaActual() {
		return jug1 ? ficha1 : ficha2;
	}
	
	@Override
	public Ficha fichaOtro() {
		return jug1 ? ficha2 : ficha1;
	}
	
	/**
	 * Comprueba si es posible soltar una ficha en la columna indicada.
	 * 
	 * @param c Columna.
	 * @return	Verdadero si es posible soltar una ficha en la columna c.
	 */
	public boolean posibleP(int c) {
		return 0 <= c && c < tablero.nColumnas() && tablero.columnaLibre(c);
	}

	/**
	 * Devuelve el estado resultante de mover en la columna indicada.
	 * 
	 * @param c Columna.
	 * @return	Estado resultado de mover en la columna c.
	 */
	public EstadoJuego elegirSucNth(int c) {
		ConectaK nuevoEstado = new ConectaK(it+1, !jug1, new TableroCK(tablero), null, longGanadora);
		nuevoEstado.ultimoMov = nuevoEstado.tablero.soltarFicha(c, fichaActual());
		return nuevoEstado;
	}
	
	@Override
	public boolean agotado() {
		return it == tablero.nFilas()*tablero.nColumnas();
	}

	@Override
	public String clave() {
		return toString() + jug1;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof ConectaK && this.clave().equals(((ConectaK)obj).clave());
	}

	@Override
	public Ficha ganador() {
		Ficha fichaGanador = null;
		
		if (ultimoMov != null) {
			int f = ultimoMov.getFila();
			int c = ultimoMov.getColumna();
			int n1,n2,n3,n4;
			n1 = tablero.conectadas(f, c, 1, 0);
			n2 = tablero.conectadas(f, c, 0, 1);
			n3 = tablero.conectadas(f, c, -1, 1);
			n4 = tablero.conectadas(f, c, 1, 1);
			int max = Math.max(Math.max(Math.max(n1, n2), n3), n4);
			if (longGanadora <= max) {
				fichaGanador = fichaOtro();
			}
		}
		return fichaGanador;
	}

	@Override
	public List<EstadoJuego> hijos() {
		List<EstadoJuego> h = new ArrayList<EstadoJuego>();
		
		for (int i = 0; i < tablero.nColumnas(); i++) {
			if (posibleP(i)) {
				h.add(elegirSucNth(i));
			}
		}
		return h;
	}

	@Override
	public boolean jug1() {
		return jug1;
	}

	@Override
	public String toString () {
		String res = new String();
		res += "It: " + it + '\n';
		res += tablero.toString() + '\n';
		res += "Longitud ganadora: " + longGanadora + '\n';
		res += "Último movimiento: " + ultimoMov + '\n';
		res += "Ficha jugador 1: " + ficha1.toString() + "  Ficha jugador 2: " + ficha2.toString() + '\n';
		res += "Le toca jugar al jugador ";
		res += jug1 ? "1" : "2";
		res += '\n';
		return res;
	}

	/**
	 * @return	Tablero del juego.
	 */
	public TableroCK tablero() {
		return tablero;
	}	
}
