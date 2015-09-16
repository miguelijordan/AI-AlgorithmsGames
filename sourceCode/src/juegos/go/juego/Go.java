package juegos.go.juego;

import java.util.ArrayList;
import java.util.List;

import juegos.EstadoJuego;
import juegos.util.Ficha;
import juegos.util.Movimiento;

/**
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 19/09/2011
 *
 */
public class Go implements EstadoJuego {

	private static final int N_FICHAS_JUG1 = 181;
	
	private static final int N_FICHAS_JUG2 = 180;
	
	private int nFichasJug1;
	
	private int nFichasJug2;
	
	/**
	 * Constante para seleccionar las reglas de puntuación japonesas (por defecto);
	 */
	public static final int REGLAS_JAPONESAS = 1;
	
	/**
	 * Constante para seleccionar las reglas de puntuación chinas.
	 */
	public static final int REGLAS_CHINAS = 2;
	
	/**
	 * Reglas de puntuación del juego.
	 */
	private int reglas;
		
	/**
	 * Valor por defecto para el komi.
	 * El valor de komi en una partida estándar de Go (19x19) entre jugadores nivelados es de 5.5 moku (puntos).
	 */
	public static final double KOMI_19x19 = 5.5;

	/**
	 * Valor de komi para una partida de Go en un tablero 13x13.
	 */
	public static final double KOMI_13x13 = 4.5;
	
	/**
	 * Valor de komi para una partida de Go en un tablero 9x9.
	 */
	public static final double KOMI_9x9 = 3.5;
	
	/**
	 * Valor de compensación para el jugador blanco (jug2) por el hecho de jugar en segundo lugar.
	 */
	private double komi;
	
	/**
	 * Valor de handicap por defecto (número de piezas colocadas al inicio de la partida).
	 */
	private static final int HANDICAP = 0;
	
	/**
	 * Valor de handicap.
	 */
	private int handicap;

	/**
	 * Contenido de una posición vacía.
	 */
	public static final Ficha P_VACIA = new Ficha('.');
	
	/**
	 * Número de movimientos realizados (iteraciones del juego).
	 */
	private int it;
	
	/**
	 * Tablero del juego.
	 */
	private TableroGo tablero;
	
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
	public static Ficha ficha1 = new Ficha('X');
	
	/**
	 * Ficha del jugador 2.
	 */
	public static Ficha ficha2 = new Ficha('O');

	/**
	 * Estado anterior (necesario para la regla del Ko).
	 * La regla del Ko prohíbe la repetición de posiciones (i.e. ¡el tablero entero!) durante una partida.
	 */
	private Go estadoAnterior;
	
	/**
	 * Número de fichas capturadas del jugador 1.
	 */
	private int nFichasCapturadasJug1;
	
	/**
	 * Número de fichas capturadas del jugador 2.
	 */
	private int nFichasCapturadasJug2;
	
	private List<Movimiento> ultimasFichasCapturadas;
	
	/**
	 * Crea un estado inicial del juego Go con un tablero vacío de dimensiones nFil x nCol.
	 * La partida tiene un valor de komi por defecto de 5.5 puntos y un hándicap de 0.
	 * La puntuación se hará según las reglas japonesas.
	 * 
	 * @param nFil	Número de filas.
	 * @param nCol	Número de columnas.
	 */
	public Go(int nFil, int nCol) {
		it = 0;
		nFichasCapturadasJug1 = 0;
		nFichasCapturadasJug2 = 0;
		reglas = REGLAS_JAPONESAS;
		jug1 = true;
		ultimoMov = null;
		estadoAnterior = null;
		tablero = new TableroGo(nFil, nCol, P_VACIA);
		handicap = HANDICAP;
		komi = nFil == 19 && nCol == 19 ? KOMI_19x19 : (nFil == 13 && nCol == 13 ? KOMI_13x13 : (nFil == 9 && nCol == 9 ? KOMI_9x9 : 0));
		this.nFichasJug1 = nFil*nCol/2 + nFil*nCol%2;
		this.nFichasJug2 = nFil*nCol/2;
		
		ultimasFichasCapturadas = null;
	}
	
	/**
	 * Crea un estado inicial del juego Go con un tablero de dimensiones nFil x nCol y
	 * el número de fichas negras indicado (hándicap) ya colocadas sobre el tablero para el jugador 1.
	 * 
	 * @param nFil		Número de filas.
	 * @param nCol		Número de columnas.
	 * @param komi		Puntos de compensación para el segundo jugador.
	 * @param handicap	Valor de hándicap (número de fichas negras ya colocadas) para igualar la partida entre jugadores de distinto nivel.
	 * @param jug1		Indica si comienza a jugar el jugador 1 (negras) o el jugador 2 (blancas).
	 */
/*	public Go(int nFil, int nCol, double komi, int handicap, boolean jug1) {
		it = 0;
		nFichasCapturadasJug1 = 0;
		nFichasCapturadasJug2 = 0;
		this.komi = komi;
		this.handicap = handicap;
		this.jug1 = jug1;
		ultimoMov = null;
		estadoAnterior = null;
		tablero = new TableroGo(nFil, nCol, P_VACIA);
		colocarHandicap();
	}*/
	
	/**
	 * Crea un estado inicial del juego Go con un tablero de dimensiones nFil x nCol 
	 * inicializado con las fichas de hándicap indicadas en el tablero proporcionado y
	 * las reglas de puntuación indicadas.
	 * 
	 * @param nFil			Número de filas.
	 * @param nCol			Número de columnas.
	 * @param komi			Puntos de compensación para el segundo jugador.
	 * @param handicap		Tablero con las fichas de hándicap (negras) ya colocadas.
	 * @param jug1			Indica si comienza a jugar el jugador 1 (negras) o el jugador 2 (blancas).
	 * @param reglas		Indica que reglas (japonesas o chinas) se usan para la puntuación final.
	 */
	public Go(int nFil, int nCol, double komi, TableroGo handicap, boolean jug1, int reglas) {
		it = 0;
		nFichasCapturadasJug1 = 0;
		nFichasCapturadasJug2 = 0;
		this.komi = komi;
		this.jug1 = jug1;
		ultimoMov = null;
		estadoAnterior = null;
		this.reglas = reglas;
		this.nFichasJug1 = nFil*nCol/2 + nFil*nCol%2;
		this.nFichasJug2 = nFil*nCol/2;
		if (handicap != null) {
			tablero = handicap;
			this.handicap = handicap.contarFichasColocadas(ficha1);	
		} else {
			tablero = new TableroGo(nFil, nCol, P_VACIA);
			this.handicap = HANDICAP;
		}
		
		ultimasFichasCapturadas = null;
	}
	
	/**
	 * Crea un estado inicial del juego Go con un tablero de dimensiones nFil x nCol 
	 * inicializado con las fichas de hándicap indicadas en el tablero proporcionado y
	 * las reglas de puntuación indicadas.
	 * 
	 * @param nFil			Número de filas.
	 * @param nCol			Número de columnas.
	 * @param komi			Puntos de compensación para el segundo jugador.
	 * @param handicap		Tablero con las fichas de hándicap (negras) ya colocadas.
	 * @param jug1			Indica si comienza a jugar el jugador 1 (negras) o el jugador 2 (blancas).
	 * @param reglas		Indica que reglas (japonesas o chinas) se usan para la puntuación final.
	 * @param nFichasJug1 	Número de fichas iniciales para el jugador 1.
	 * @param nFichasJug2 	Número de fichas iniciales para el jugador 2.
	 */
	public Go(int nFil, int nCol, double komi, TableroGo handicap, boolean jug1, int reglas, int nFichasJug1, int nFichasJug2) {
		it = 0;
		nFichasCapturadasJug1 = 0;
		nFichasCapturadasJug2 = 0;
		this.komi = komi;
		this.jug1 = jug1;
		ultimoMov = null;
		estadoAnterior = null;
		this.reglas = reglas;
		this.nFichasJug1 = nFichasJug1;
		this.nFichasJug2 = nFichasJug2;
		if (handicap != null) {
			tablero = handicap;
			this.handicap = handicap.contarFichasColocadas(ficha1);	
		} else {
			tablero = new TableroGo(nFil, nCol, P_VACIA);
			this.handicap = HANDICAP;
		}
		
		ultimasFichasCapturadas = null;
	}
	
	/**
	 * Crea un nuevo estado a partir de los parámetros proporcionados.
	 * 
	 * @param it				Número de jugadas.
	 * @param jug1				¿Le toca jugar al primer jugador?
	 * @param tablero			Tablero.
	 * @param ultimoMov			Último movimiento realizado.
	 * @param estadoAnterior	Estado anterior.
	 */
	private Go(int it, boolean jug1, TableroGo tablero, Movimiento ultimoMov, Go estadoAnterior) {
		this.it = it;
		this.jug1 = jug1;
		this.tablero = tablero;
		this.ultimoMov = ultimoMov;
		this.estadoAnterior = estadoAnterior;
		this.komi = estadoAnterior.komi;
		this.handicap = estadoAnterior.handicap;
		this.reglas = estadoAnterior.reglas;
		nFichasCapturadasJug1 = estadoAnterior.nFichasCapturadasJug1;
		nFichasCapturadasJug2 = estadoAnterior.nFichasCapturadasJug2;
		nFichasJug1 = estadoAnterior.nFichasJug1;
		nFichasJug2 = estadoAnterior.nFichasJug2;
		
		ultimasFichasCapturadas = null;
	}
	
	@Override
	public List<EstadoJuego> hijos() {
		List<EstadoJuego> h = new ArrayList<EstadoJuego>();
		
		if (jug1 && nFichasJug1 > 0 || !jug1 && nFichasJug2 > 0) {
			for (int f = 0; f < tablero.nFilas(); f++) {
				for (int c = 0; c < tablero.nColumnas(); c++) {
					if (movimientoValido(f,c)) {
						//System.out.println("pos correcta");
						h.add(elegirSuc(f,c));
					}
				}
			}	
		}	
		// El jugador pasa el turno.
		h.add(new Go(it+1, !jug1, new TableroGo(tablero), null, this));
		return h;
	}
	
	@Override
	public Ficha ganador() {
		if (!estadoFinal()) {
			return null;
		}
		double puntos1;
		double puntos2;
		Ficha fichaGanador = null;
		
		if (reglas == REGLAS_CHINAS) {
			puntos1 = puntuacionReglasChinas(ficha1);
			puntos2 = puntuacionReglasChinas(ficha2);
		} else {
			puntos1 = puntuacionReglasJaponesas(ficha1);
			puntos2 = puntuacionReglasJaponesas(ficha2);
		}
		
		if (puntos1 > puntos2) {
			fichaGanador = ficha1;
		} else if (puntos2 > puntos1) {
			fichaGanador = ficha2;
		}
		return fichaGanador;
	}

	@Override
	public boolean agotado() {
		return estadoFinal() && empatePuntos();
	}

	/**
	 * Un estado de Go es final si no quedan fichas por jugarse ó se ha pasado dos veces seguidas de turno.
	 * 
	 * @return Verdadero si el estado actual es un estado final del juego, falso en otro caso.
	 */
	private boolean estadoFinal() {
		return pasaTurnoDoble() || (nFichasJug1 == 0 && nFichasJug2 == 0);
	}
	
	private boolean empatePuntos() {
		if (reglas == Go.REGLAS_JAPONESAS) {
			return puntuacionReglasJaponesas(ficha1) == puntuacionReglasJaponesas(ficha2);
		} else {
			return puntuacionReglasChinas(ficha1) == puntuacionReglasChinas(ficha2);
		}
	}
	
	/**
	 * 
	 * @return	Verdadero si se ha pasado turno dos veces seguidas, falso en caso contrario.
	 */
	private boolean pasaTurnoDoble() {
		return estadoAnterior != null && ultimoMov == null && estadoAnterior.estadoAnterior != null && estadoAnterior.ultimoMov == null;
	}
	
	@Override
	public boolean jug1() {
		return jug1;
	}

	@Override
	public String clave() {
		return toString() + jug1;
	}

	@Override
	public Ficha fichaActual() {
		return jug1 ? ficha1 : ficha2;
	}

	@Override
	public Ficha fichaOtro() {
		return jug1 ? ficha2 : ficha1;
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Go && this.clave().equals(((Go)o).clave());
	}
	
	@Override
	public String toString() {
		String res = new String();
		res += "It: " + it + '\n';
		res += tablero.toString() + '\n';
		res += "Nº fichas del jug. 1: " + nFichasJug1 + '\n';
		res += "Nº fichas del jug. 2: " + nFichasJug2  + '\n';
		res += "Nº fichas del jug. 1 en el tablero: " + tablero.contarFichasColocadas(ficha1) + '\n';
		res += "Nº fichas del jug. 2 en el tablero: " + tablero.contarFichasColocadas(ficha2)  + '\n';
		res += "Nº fichas capturadas del jug. 1: " + nFichasCapturadasJug1 + '\n';
		res += "Nº fichas capturadas del jug. 2: " + nFichasCapturadasJug2 + '\n';
		res += "Territorios jug. 1: " + tablero.contarTerritorios(ficha1) + '\n';
		res += "Territorios jug. 2: " + tablero.contarTerritorios(ficha2) + '\n';
		res += "Puntos (ch) jug. 1: " + puntuacionReglasChinas(ficha1) + '\n';
		res += "Puntos (ch) jug. 2: " + puntuacionReglasChinas(ficha2) + '\n';
		res += "Puntos (jp) jug. 1: " + puntuacionReglasJaponesas(ficha1) + '\n';
		res += "Puntos (jp) jug. 2: " + puntuacionReglasJaponesas(ficha2) + '\n';
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
	public TableroGo tablero() {
		return tablero;
	}	
	
	/**
	 * Comprueba si una posición dada es válida.
	 * Una posición es válida si:
	 *  se encuentra dentro de las dimensiones del tablero,
	 *  es una posición vacía (no hay fichas colocadas),
	 *  no se trata de un suicidio y
	 *  no viola la regla del Ko.
	 * 
	 * @param f	Fila.
	 * @param c	Columna.
	 * @return	Verdadero si la posición (f,c) es válida, falso en otro caso.
	 */
	public boolean movimientoValido(int f, int c) {
		boolean ok = tablero.posValida(f, c) && tablero.contenido(f, c).equals(P_VACIA);
		if (ok) {
			ok = jug1 ? nFichasJug1 > 0 : nFichasJug2 > 0;	
		}
		if (ok) {
			TableroGo tab = new TableroGo(tablero);
			tab.ponerFicha(f, c, this.fichaActual());
			ok = tab.nLibertades(f, c) > 0 || tab.capturarFichas(f, c).size() > 0;	// Suicidio.
			//System.out.println("pos: " + f + "," + c + ", libertades: " + tab.nLibertades(f, c));
			if (ok && estadoAnterior != null) {	// Comprueba la regla del Ko.
				ok = !estadoAnterior.tablero().equals(tab);	
			}	
		}
		return ok;
	}
	
	/**
	 * Devuelve el estado resultante de mover en la fila y columna indicadas.
	 * 
	 * @param f	Fila.
	 * @param c Columna.
	 * @return	Estado resultado de mover en la posición (f,c).
	 */
	public EstadoJuego elegirSuc(int f, int c) {
		TableroGo tab = new TableroGo(tablero);
		tab.ponerFicha(f, c, fichaActual());
		List<Movimiento> fichasCapturadas = tab.capturarFichas(f, c);
		int nFichasCapturadas = fichasCapturadas.size();
		Go nuevoEstado = new Go(it+1, !jug1, tab, new Movimiento(f,c), this);
		nuevoEstado.ultimasFichasCapturadas = fichasCapturadas;
		if (jug1) {
			nuevoEstado.nFichasCapturadasJug2 += nFichasCapturadas;
			nuevoEstado.nFichasJug1--;
		} else {
			nuevoEstado.nFichasCapturadasJug1 += nFichasCapturadas;
			nuevoEstado.nFichasJug2--;
		}
		return nuevoEstado;
	}
	
	/**
	 * Devuelve el estado resultante de pasar turno.
	 * 
	 * @return	Estado resultado de pasar turno.
	 */
	public EstadoJuego pasarTurno() {
		return new Go(it+1, !jug1, new TableroGo(tablero), null, this);
	}
	
	/**
	 * Puntuación según las reglas chinas.
	 * Se cuentan el número de fichas del jugador sobre el tablero y
	 * y el número de intersecciones (posiciones) vacías que un jugador ha encerrado. 
	 * 
	 * @param ficha	Ficha.
	 * @return		Número de puntos del jugador que juega con la ficha indicada.
	 */
	public double puntuacionReglasChinas(Ficha ficha) {
		double puntos = tablero.contarFichasColocadas(ficha) + tablero.contarTerritorios(ficha);
		if (ficha.equals(ficha2)) {
			puntos += komi;
		}
		return puntos;
	}
	
	/**
	 * Puntuación según las reglas japonesas.
	 * Las fichas capturadas se colocan en el territorio del jugador rival y
	 * se cuentan el número de intersecciones (posiciones) vacía que un jugador ha encerrado.
	 * 
	 * @param ficha	Ficha.
	 * @return		Número de puntos del jugador que juega con la ficha indicada.
	 */
	public double puntuacionReglasJaponesas(Ficha ficha) {
		double puntos = tablero.contarTerritorios(ficha);
		if (ficha.equals(ficha1)) {
			puntos -= nFichasCapturadasJug1;
		} else {
			puntos -= nFichasCapturadasJug2;
			puntos += komi;
		}
		return puntos;
	}

	public Movimiento getUltimoMov() {
		return ultimoMov;
	}

	public int getReglas() {
		return reglas;
	}

	public double getKomi() {
		return komi;
	}

	public int getHandicap() {
		return handicap;
	}

	/**
	 * @return the nFichasJug1
	 */
	public int getnFichasJug1() {
		return nFichasJug1;
	}

	/**
	 * @return the nFichasJug2
	 */
	public int getnFichasJug2() {
		return nFichasJug2;
	}

	/**
	 * @return Número de fichas capturadas del jugador 1.
	 */
	public int getnFichasCapturadasJug1() {
		return nFichasCapturadasJug1;
	}

	/**
	 * @return Número de fichas capturadas del jugador 2.
	 */
	public int getnFichasCapturadasJug2() {
		return nFichasCapturadasJug2;
	}

	/**
	 * @return the ficha1
	 */
	public static Ficha getFicha1() {
		return ficha1;
	}

	/**
	 * @return the ficha2
	 */
	public static Ficha getFicha2() {
		return ficha2;
	}

	/**
	 * @return the ultimasFichasCapturadas
	 */
	public List<Movimiento> getUltimasFichasCapturadas() {
		return ultimasFichasCapturadas;
	}
}
