package juegos.go.juego;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import juegos.util.Ficha;
import juegos.util.Movimiento;
import juegos.util.Tablero;

public class TableroGo extends Tablero {

	
	public TableroGo(int nFil, int nCol, Ficha ini) {
		super(nFil, nCol, ini);
	}
	
	/**
	 * Crea un nuevo tablero copia del tablero proporcionado.
	 * 
	 * @param tab Tablero.
	 */
	public TableroGo(TableroGo tab) {
		super(tab);
	}
	
	/**
	 * Cuenta el número de libertades que tiene una ficha o grupo de fichas que se encuentran a partir de la posición del tablero indicada.
	 * 
	 * @param f	Fila.
	 * @param c	Columna.
	 * @return	Número de libertades de la ficha o grupo de fichas a partir de la posición (f,c).
	 */
	public int nLibertades(int f, int c) {
		int nLibertades = 0;
		Ficha ficha = contenido(f,c);
		if (!ficha.equals(getPVacia())) {
			boolean[][] posicionesVisitadas = new boolean[nFilas()][nColumnas()];
			posicionesVisitadas[f][c] = true;
			nLibertades = libertadesGrupo(ficha, f, c, posicionesVisitadas);	
		}
		return nLibertades;
	}
	
	/**
	 * Cuenta de manera recursiva el número de libertades de un grupo de fichas a partir de una ficha dada y su posición,
	 * evitando las posiciones ya visitadas.
	 * 
	 * @param ficha					Ficha.
	 * @param f						Fila.
	 * @param c						Columna.
	 * @param posicionesVisitadas	Posiciones visitadas.
	 * @return						Número de libertades.
	 */
	private int libertadesGrupo(Ficha ficha, int f, int c, boolean[][] posicionesVisitadas) {
		ArrayList<Movimiento> grupo = new ArrayList<Movimiento>();
		int nLibertades = 0;
		
		nLibertades = libertad(ficha, f, c-1, posicionesVisitadas, grupo);
		nLibertades += libertad(ficha, f, c+1, posicionesVisitadas, grupo);
		nLibertades += libertad(ficha, f-1, c, posicionesVisitadas, grupo);
		nLibertades += libertad(ficha, f+1, c, posicionesVisitadas, grupo);
		for (Movimiento m : grupo) {
			nLibertades += libertadesGrupo(ficha, m.getFila(), m.getColumna(), posicionesVisitadas);
		}
		return nLibertades;
	}
	
	/**
	 * Comprueba si la posición (f,c) es una libertad para la ficha dada.
	 * En caso contrario si la posición (f,c) forma parte de un grupo de fichas, la añade al grupo.
	 * En cualquier caso la posición queda marcada como visitada.
	 * 
	 * @param ficha					Ficha actual.
	 * @param f						Fila.
	 * @param c						Columna.
	 * @param posicionesVisitadas	Posiciones visitadas.
	 * @param grupo					Grupo de fichas.
	 * @return						1 si la posición (f,c) es una libertad para la ficha, 0 en caso contrario.
	 */
	private int libertad(Ficha ficha, int f, int c, boolean[][] posicionesVisitadas, ArrayList<Movimiento> grupo) {
		int libertad = 0;
		
		if (posValida(f,c)) {
			if (!posicionesVisitadas[f][c]) {
				posicionesVisitadas[f][c] = true;
				if (contenido(f,c).equals(getPVacia())) {
					libertad = 1;
				} else if (contenido(f,c).equals(ficha)) {
					grupo.add(new Movimiento(f,c));
				}	
			}
		}
		return libertad;
	}
	
	/**
	 * Número de fichas que se pueden capturar a partir de la posición (f,c).
	 * 
	 * @param f	Fila.
	 * @param c	Columna.
	 * @return	Número de posibles capturas.
	 */
	public int nCapturas(int f, int c) {
		return capturarFichas(f, c, false).size();
	}
	
	/**
	 * Captura la ficha o grupo de fichas a partir de la posición (f,c).
	 * La ficha de la posición (f,c) es la ficha capturadora.
	 * 
	 * @param f			Fila.
	 * @param c			Columna.
	 * @return			Posiciones de las fichas capturadas.
	 */
	public List<Movimiento> capturarFichas(int f, int c) {
		return capturarFichas(f, c, true);
	}
	
	/**
	 * Cuenta el número de fichas que se capturan a partir de la posición (f,c),
	 * y opcionalmente captura las fichas o grupos de fichas a partir de esa posición.
	 * La ficha de la posición (f,c) es la ficha capturadora.
	 * 
	 * @param f			Fila.
	 * @param c			Columna.
	 * @param capturar	Indica si las fichas deben ser capturadas o no.
	 * @return			Posiciones de las fichas capturadas.
	 */
	private List<Movimiento> capturarFichas(int f, int c, boolean capturar) {
		//int nFichasCapturadas = 0;
		List<Movimiento> fichasCapturadas = new LinkedList<Movimiento>();
		Ficha ficha = contenido(f, c);
		if (!ficha.equals(getPVacia())) {
			boolean[][] posicionesVisitadas = new boolean[nFilas()][nColumnas()];
			posicionesVisitadas[f][c] = true;
			if (posValida(f,c-1) && nLibertades(f,c-1) == 0) {
				fichasCapturadas.addAll(capturarGrupo(ficha, f, c-1, posicionesVisitadas, capturar));
			}
			if (posValida(f,c+1) && nLibertades(f,c+1) == 0) {
				fichasCapturadas.addAll(capturarGrupo(ficha, f, c+1, posicionesVisitadas, capturar));
			}
			if (posValida(f-1,c) && nLibertades(f-1,c) == 0) {
				fichasCapturadas.addAll(capturarGrupo(ficha, f-1, c, posicionesVisitadas, capturar));
			}
			if (posValida(f+1,c) && nLibertades(f+1,c) == 0) {
				fichasCapturadas.addAll(capturarGrupo(ficha, f+1, c, posicionesVisitadas, capturar));
			}
		}
		return fichasCapturadas;
	}
	
	/**
	 * Cuenta el número de fichas de un grupo a capturar a partir de la posición (f,c),
	 * y opcionalmente captura el grupo de fichas a partir de esa posición.
	 * 
	 * @param ficha					Ficha que captura a las demás.
	 * @param f						Fila de la ficha a capturar.
	 * @param c						Columna de la ficha a capturar.
	 * @param posicionesVisitadas	Posiciones ya visitadas.
	 * @param capturar				Indica si las fichas deben ser capturadas o no.
	 * @return						Posiciones de las fichas capturadas.
	 */
	private List<Movimiento> capturarGrupo(Ficha ficha, int f, int c, boolean[][] posicionesVisitadas, boolean capturar) {
		//int nFichasCapturadas = 0;
		List<Movimiento> fichasCapturadas = new LinkedList<Movimiento>();
		if (posValida(f,c)) {
			if (!posicionesVisitadas[f][c]) {
				posicionesVisitadas[f][c] = true;
				Ficha fichaCapturada = contenido(f,c);
				if (!fichaCapturada.equals(getPVacia()) && !fichaCapturada.equals(ficha)) {
					if (capturar) {
						this.ponerFicha(f, c, getPVacia());
					}
					fichasCapturadas.add(new Movimiento(f,c));
					//nFichasCapturadas++;
					fichasCapturadas.addAll(capturarGrupo(ficha, f, c-1, posicionesVisitadas, capturar));
					fichasCapturadas.addAll(capturarGrupo(ficha, f, c+1, posicionesVisitadas, capturar));
					fichasCapturadas.addAll(capturarGrupo(ficha, f-1, c, posicionesVisitadas, capturar));
					fichasCapturadas.addAll(capturarGrupo(ficha, f+1, c, posicionesVisitadas, capturar));
				}	
			}
		}
		return fichasCapturadas;
	}

	@Override
	public String toString() {
		String res = new String();
		
		for (int f = 0; f < nFilas(); f++) {
			for (int c = 0; c < nColumnas(); c++) {
				res = res.concat(contenido(f,c).toString());
				if (c != nColumnas()-1) {
					res = res.concat("__");	
				}
			}
			res = res.concat(" " + f + "\n");
			if (f != nFilas()-1) {
				for (int c = 0; c < nColumnas(); c++) {
					res = res.concat("|  ");
				}
				res = res.concat("\n");	
			}
		}
		for (int c = 0; c < nColumnas(); c++) {
			res = res.concat(c + "  ");
		}
		res = res.concat("\n");	
		return res;
	}
	
	/**
	 * Cuenta el número de fichas colocadas en el tablero iguales a la ficha dada.
	 * 
	 * @param ficha	Ficha a contar.
	 * @return		Número de fichas en el tablero.
	 */
	public int contarFichasColocadas(Ficha ficha) {
		int nFichas = 0;
		for (int f = 0; f < nFilas(); f++) {
			for (int c = 0; c < nColumnas(); c++) {
				if (contenido(f,c).equals(ficha)) {
					nFichas++;
				}
			}
		}
		return nFichas;
	}
	
	/**
	 * Cuenta el tamaño de los territorios (número de posiciones) controlados por el jugador al que pertenece la ficha indicada.
	 * 
	 * @param ficha	Ficha.
	 * @return		Número de posiciones del tablero controladas por el jugador con la ficha indicada.
	 */
	public int contarTerritorios(Ficha ficha) {
		int nPosiciones = 0;
		boolean[][] territoriosVisitados = new boolean[nFilas()][nColumnas()];
		for (int f = 0; f < nFilas(); f++) {
			for (int c = 0; c < nColumnas(); c++) {
				Territorio t = contarTerritorio(f, c, ficha, territoriosVisitados, new Territorio());
				//nPosiciones += t.getnBlancas() > t.getnNegras() ? t.getnPosiciones() : 0;
				nPosiciones += t.getnNegras() == 0 ? t.getnPosiciones() : 0;
			}
		}
		return nPosiciones;
	}
	
	/**
	 * Cuenta el número de posiciones de un territorio pertenecientes a la ficha indicada a partir de la posición (f,c).
	 * 
	 * @param f						Fila.
	 * @param c						Columna.
	 * @param ficha					Ficha.
	 * @param territoriosVisitados	Territorios vacios ya visitados.
	 * @return						El territorio ocupado a partir de la posición (f,c) por la ficha dada.
	 */
	private Territorio contarTerritorio(int f, int c, Ficha ficha, boolean[][] territoriosVisitados, Territorio t) {
		int nPosiciones = 0;
		if (posValida(f,c)) {
			if (contenido(f,c).equals(getPVacia())) {
				if (!territoriosVisitados[f][c]) {
					territoriosVisitados[f][c] = true;
					nPosiciones++;
					nPosiciones += contarTerritorio(f, c-1, ficha, territoriosVisitados, t).getnPosiciones();
					nPosiciones += contarTerritorio(f, c+1, ficha, territoriosVisitados, t).getnPosiciones();
					nPosiciones += contarTerritorio(f-1, c, ficha, territoriosVisitados, t).getnPosiciones();
					nPosiciones += contarTerritorio(f+1, c, ficha, territoriosVisitados, t).getnPosiciones();	
				}
			} else if (contenido(f,c).equals(ficha)) {
				t.incBlancas();
			} else {
				t.incNegras();
			}
		}
		t.setnPosiciones(nPosiciones);
		return t;
	}
	
}
